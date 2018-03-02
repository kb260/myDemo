package com.panda.sharebike.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.ScaleAnimation;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkPath;
import com.amap.api.services.route.WalkRouteResult;
import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.app.AppApplication;
import com.panda.sharebike.dialog.LoadDialog;
import com.panda.sharebike.lib.LocationTask;
import com.panda.sharebike.lib.OnLocationGetListener;
import com.panda.sharebike.lib.PositionEntity;
import com.panda.sharebike.lib.RegeocodeTask;
import com.panda.sharebike.lib.RouteTask;
import com.panda.sharebike.lib.Utils;
import com.panda.sharebike.model.MessageEventInt;
import com.panda.sharebike.model.entity.BikeLocationBean;
import com.panda.sharebike.model.entity.CreateBean;
import com.panda.sharebike.model.entity.IndexBean;
import com.panda.sharebike.model.entity.LoginDataBean;
import com.panda.sharebike.model.entity.StopPointBean;
import com.panda.sharebike.overlay.WalkRouteOverlay;
import com.panda.sharebike.service.SocketServices;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.login.CertificationActivity;
import com.panda.sharebike.ui.login.LoginByPhoneActivity;
import com.panda.sharebike.ui.login.RechargeActivity;
import com.panda.sharebike.ui.maintenance.FaultRepairActivity;
import com.panda.sharebike.ui.ride.RideBikeStayStillActivity;
import com.panda.sharebike.ui.search.DestinationActivity;
import com.panda.sharebike.ui.selfinfo.PersonalActivity;
import com.panda.sharebike.ui.unlock.ScanUnlockActivity;
import com.panda.sharebike.ui.widget.CountDownProgress;
import com.panda.sharebike.util.AMapUtil;
import com.panda.sharebike.util.SpannableStringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Main2Activity extends BaseActivity implements AMap.OnCameraChangeListener, AMap.OnMapLoadedListener, OnLocationGetListener, RouteTask.OnRouteCalculateListener,
        AMap.OnMapTouchListener, RouteSearch.OnRouteSearchListener, AMap.OnMapClickListener, AMap.InfoWindowAdapter {

    public static final String STATE_NORMAL = "normal";//显示找车界面
    public static final String STATE_SUBSCRIBING = "subscribing";//显示当前预约界面
    public static final String STATE_RIDING = "riding";//显示当前骑行界面
    public static final String STATE_AWAIT = "await";//点击后，显示当前骑行界面(未还车强行弹这个界面，点击market就弹，或者进入退押金就弹)
    private String BIKE_STATE = "normal";

    public static final int CODE_BIKE_RETURN = 1002;
    /**
     * 预约用车
     */
    @BindView(R.id.order_cardView)
    CardView cv_oederTip;
    @BindView(R.id.tv_order_cost_tip)
    TextView tvOrderCostTip;
    @BindView(R.id.tv_order_time_tip)
    TextView tvOrderTimeTip;
    @BindView(R.id.tv_order_distance_tip)
    TextView tvOrderDistanceTip;
    @BindView(R.id.btn_order_tip)
    Button btnOrderTip;
    /**
     * 取消用车
     */
    @BindView(R.id.tv_un_order_address)
    TextView tvUnOrderAddress;
    @BindView(R.id.tv_un_order_bike_id)
    TextView tvUnOrderBikeId;
    @BindView(R.id.countdownProgress)
    CountDownProgress countdownProgress;
    @BindView(R.id.btn_un_order)
    Button btnUnOrder;
    @BindView(R.id.un_order_cardView)
    CardView cv_unOrderTip;


    @BindView(R.id.iv_main_self)
    ImageView ivMainSelf;
    @BindView(R.id.iv_main_search)
    ImageView ivMainSearch;
    @BindView(R.id.iv_location)
    ImageView iv_scan_code;//刷新定位
    @BindView(R.id.llayout_scan)
    LinearLayout llayoutScan;
    @BindView(R.id.iv_refresh)
    ImageView iv_refresh;//刷新定位

    @BindView(R.id.iv_repair)
    ImageView ivRepair;
    //
    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;//标题栏--中间文字（可替换）
    @BindView(R.id.iv_main_title)
    ImageView ivMainTitle;//标题栏--中间图片---（可替换）

    @BindView(R.id.view_riding_sticker)
    CardView rl_viewRidingSticker;//正在骑行tip
    //正在骑行文本
    @BindView(R.id.tv_riding_ing)
    TextView tvRidingIng;
    @BindView(R.id.tv_time_ing)
    TextView tvTimeIng;
    @BindView(R.id.tv_distance_ing)
    TextView tvDistanceIng;
    @BindView(R.id.tv_info_bike)
    TextView tvInfoBike;//正在骑行的车的单车编号
    TextView mTv;//有无网络显示文本
    @BindView(R.id.tv_order_address)
    TextView tvOrderAddress;

    private AlertDialog mAlertDialog;
    //tip

    private AMap aMap;
    @BindView(R.id.map_view)
    MapView mMapView;  //地图view
    public static final int REQUEST_CODE = 1;
    public static final String TAG = "MainActivity";
    private long lastTime;
    //定位
    private LocationTask mLocationTask;
    //逆地理编码功能
    private RegeocodeTask mRegeocodeTask;
    //绘制点标记
    private Marker mPositionMark, mInitialMark, tempMark;//可移动、圆点、点击
    //初始坐标、移动记录坐标
    private LatLng mStartPosition, mRecordPositon;
    //默认添加一次
    private boolean mIsFirst = true;
    private boolean mIsFirstList = true;
    //就第一次显示位置
    private boolean mIsFirstShow = true;
    //判断是否是进行倒计时还是切换页面
    private boolean mIsCountDownShow = true;
    //经纬度
    private LatLng initLocation;
    //预约用车，车的坐标点
    private double bikeLongitude, bikeLatitude;
    private ValueAnimator animator = null;//坐标动画
    private BitmapDescriptor initBitmap, moveBitmap, smallIdentificationBitmap, bigIdentificationBitmap, stopIdentificationBitmap;//定位圆点、可移动、所有标识（车）
    private RouteSearch mRouteSearch;
    private WalkRouteResult mWalkRouteResult;
    private LatLonPoint mStartPoint = null;//起点，116.335891,39.942295
    private LatLonPoint mEndPoint = null;//终点，116.481288,39.995576
    private final int ROUTE_TYPE_WALK = 3;
    private boolean isClickIdentification = false;
    WalkRouteOverlay walkRouteOverlay;//路线
    private String[] time;
    private String distance;
    private String address = "当前位置";
    private String bikeId = null;//单车ID
    private int leftTime = 0;//倒计时剩余时间

    private List<BikeLocationBean> locationList;
    private List<StopPointBean> mStopPointBeen;
    private String orderId = null;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_main2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMapView = (MapView) findViewById(R.id.map_view);
        mMapView.onCreate(savedInstanceState);
        mAlertDialog = new AlertDialog.Builder(this).create();
        mTv = (TextView) findViewById(R.id.tv_mt);
        //启动时判断网络状态
        boolean netConnect = this.isNetConnect();
        if (netConnect) {
            mTv.setVisibility(View.GONE);
        } else {
            mTv.setVisibility(View.VISIBLE);
        }
        //app = (AppApplication) AppApplication.getAppContext();
        // countdownProgress.setCountdownTime(900);

        initBitmap();
        initAMap();
        initLocation();
        RouteTask.getInstance(getApplicationContext()).addRouteCalculateListener(this);
    }

    /**
     * 从其他界面进入重新定位
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        clickRefresh();
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        hideTitleBar();
    }

//    @Override
//    public void onNetChange(int netMobile) {
//        super.onNetChange(netMobile);
//        //网络状态变化时的操作
//        if (netMobile == NetUtil.NETWORK_NONE) {
//            mTv.setVisibility(View.VISIBLE);
//        } else {
//            mTv.setVisibility(View.GONE);
//        }
//    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBusEvent(String messageEvent) {
        Logger.e(messageEvent);
    }

    @OnClick({R.id.tv_mt, R.id.iv_main_self, R.id.iv_main_search, R.id.iv_location, R.id.llayout_scan, R.id.iv_refresh, R.id.iv_repair, R.id.btn_order_tip, R.id.btn_un_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mt:
                Intent intent2 = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                startActivity(intent2);
                break;
            case R.id.iv_main_self:
                startActivity(new Intent(this, PersonalActivity.class));
                break;
            case R.id.iv_main_search:
                Intent intent = new Intent(this, DestinationActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_location:
                clickRefresh();
                break;
            case R.id.llayout_scan:
                //进入扫码之前判断有无实名，有无押金，没有充值
                //创建2个sp，用于存储值，判断是否实名，是否认证
                getStatus();
                break;
            case R.id.iv_refresh:
                Utils.removeMarkers();
                getIndex(mRecordPositon.longitude, mRecordPositon.latitude, 5000);
                break;
            case R.id.iv_repair:
                getStatusFapuit();
                break;
            case R.id.btn_order_tip://预约用车
                setOrderBike(bikeId, bikeLongitude, bikeLatitude, 5000, address);
                break;
            case R.id.btn_un_order:
                setCancelBike();//取消用车
                break;
            default:
                break;
        }
    }

    //预约用车
    private void showOrderTip(String money, String time, String distance, String address) {
        cv_unOrderTip.setVisibility(View.GONE);//取消用车tip不可见
        cv_oederTip.setVisibility(View.VISIBLE);//预约用车tip可见
        rl_viewRidingSticker.setVisibility(View.GONE);//正在骑行tip不可见
        rl_viewRidingSticker.setVisibility(View.GONE);//正在骑行tip隐藏
        ivMainTitle.setVisibility(View.VISIBLE);//标题图片可见
        llayoutScan.setVisibility(View.VISIBLE);//扫描控件可见
        tvOrderAddress.setText(address);
        SpannableStringUtil.setMainOrderTipString(money, time, distance, tvOrderCostTip, tvOrderTimeTip, tvOrderDistanceTip);//配置文字类型
    }


    //正在骑行
    private void showTipRide(String money, String time, String distance, String number) {
        cv_unOrderTip.setVisibility(View.GONE);//取消用车tip不可见
        cv_oederTip.setVisibility(View.GONE);//预约用车tip不可见
        rl_viewRidingSticker.setVisibility(View.VISIBLE);//正在骑行ti不可见
        ivMainTitle.setVisibility(View.GONE);//标题图片隐藏
        tvMainTitle.setVisibility(View.VISIBLE);//标题文字显示
        llayoutScan.setVisibility(View.GONE);//扫描控件隐藏
        tvInfoBike.setText(number);
        SpannableStringUtil.setMainTipString(money, time, distance, tvRidingIng, tvTimeIng, tvDistanceIng);//配置文字类型
    }

    //取消用车
    private void shopUnOrderTip(int leftTime, String bikeId) {

        cv_unOrderTip.setVisibility(View.VISIBLE);//取消用车tip可见
        cv_oederTip.setVisibility(View.GONE);//预约用车tip不可见
        rl_viewRidingSticker.setVisibility(View.GONE);//正在骑行tip不可见
        rl_viewRidingSticker.setVisibility(View.GONE);//正在骑行tip隐藏
        ivMainTitle.setVisibility(View.VISIBLE);//标题图片可见
        llayoutScan.setVisibility(View.VISIBLE);//扫描控件可见

        Utils.unClickMarkers();//设置不可点击marker
        tvUnOrderBikeId.setText(bikeId);
        tvUnOrderAddress.setText(address);
        countdownProgress.setCountdownTime(leftTime);
        countdownProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countdownProgress.startCountDownTime(new CountDownProgress.OnCountdownFinishListener() {
                    @Override
                    public void countdownFinished() {
                        cv_unOrderTip.setVisibility(View.GONE);
                    }
                });
            }
        });
        countdownProgress.performClick();//进来默认点击
    }

    private void initBitmap() {
        initBitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.icon_location_marker);
        moveBitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_loaction_start);
        smallIdentificationBitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.stable_cluster_marker_one_normal);
        bigIdentificationBitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.stable_cluster_marker_one_normal);
        stopIdentificationBitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.p_car);
    }

    /**
     * 初始化地图控制器对象
     */
    private void initAMap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            mRouteSearch = new RouteSearch(this);
            mRouteSearch.setRouteSearchListener(this);
            aMap.getUiSettings().setZoomControlsEnabled(false);
            aMap.getUiSettings().setGestureScaleByMapCenter(true);
//            aMap.getUiSettings().setScrollGesturesEnabled(false);
            aMap.setOnMapTouchListener(this);
            aMap.setOnMapLoadedListener(this);
            aMap.setOnCameraChangeListener(this);
            aMap.setOnMapClickListener(this);
            // 绑定 Marker 被点击事件
            aMap.setOnMarkerClickListener(markerClickListener);

            aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
        }
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocationTask = LocationTask.getInstance(AppApplication.getAppContext());
        mLocationTask.setOnLocationGetListener(this);
        mRegeocodeTask = new RegeocodeTask(AppApplication.getAppContext());
    }

    // 定义 Marker 点击事件监听
    AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {

        // marker 对象被点击时回调的接口
        // 返回 true 则表示接口已响应事件，否则返回false
        @Override
        public boolean onMarkerClick(final Marker marker) {
            Log.e(TAG, "点击的Marker");

            isClickIdentification = true;
            if (tempMark != null) {
                if (1 == marker.getPeriod()) {
                    marker.setIcon(bigIdentificationBitmap);
                }
                if (2 == marker.getPeriod()) {
                    marker.setIcon(stopIdentificationBitmap);
                }
                //    tempMark.setIcon(smallIdentificationBitmap);
                walkRouteOverlay.removeFromMap();
                tempMark = null;
            }
            startAnim(marker);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(300);
                        tempMark = marker;

                        Log.e(TAG, mPositionMark.getPosition().latitude + "===" + mPositionMark.getPosition().longitude);
                        mStartPoint = new LatLonPoint(mRecordPositon.latitude, mRecordPositon.longitude);
                        mPositionMark.setPosition(mRecordPositon);
                        mEndPoint = new LatLonPoint(marker.getPosition().latitude, marker.getPosition().longitude);
                        if (1 == tempMark.getPeriod()) {
                            tempMark.setIcon(smallIdentificationBitmap);
                        }
                        if (2 == tempMark.getPeriod()) {
                            tempMark.setIcon(stopIdentificationBitmap);
                        }
                        //   marker.setIcon(bigIdentificationBitmap);
                        tempMark.setPosition(tempMark.getPosition());
                        searchRouteResult(ROUTE_TYPE_WALK, RouteSearch.WalkDefault);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return true;
        }
    };


    private void startAnim(Marker marker) {
        ScaleAnimation anim = new ScaleAnimation(1.0f, 1.3f, 1.0f, 1.3f);
        anim.setDuration(300);
        marker.setAnimation(anim);
        marker.startAnimation();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        Utils.removeMarkers();
        if (View.VISIBLE == cv_unOrderTip.getVisibility()) {
            countdownProgress.setCountdownTimeCancel();
        }
        mMapView.onDestroy();
        mLocationTask.onDestroy();
        RouteTask.getInstance(getApplicationContext()).removeRouteCalculateListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
//        Intent startIntent = new Intent(this, SocketServices.class);
        startService(new Intent(this, SocketServices.class));

        mMapView.onResume();
        if (mInitialMark != null) {
            mInitialMark.setToTop();
        }
        if (mPositionMark != null) {
            mPositionMark.setToTop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }


    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        Log.e(TAG, "onCameraChangeFinish" + cameraPosition.target);
        if (!isClickIdentification) {
            mRecordPositon = cameraPosition.target;
        }
        mStartPosition = cameraPosition.target;
        mRegeocodeTask.setOnLocationGetListener(this);
        mRegeocodeTask
                .search(mStartPosition.latitude, mStartPosition.longitude, 1);
//        Utils.removeMarkers();
        if (mIsFirst) {
            getIndex(mRecordPositon.longitude, mRecordPositon.latitude, 5000);
            //  Utils.addEmulateData(aMap, mStartPosition);//模拟测试的车点
            iv_refresh.setVisibility(View.VISIBLE);
            iv_scan_code.setVisibility(View.VISIBLE);
            createInitialPosition(cameraPosition.target.latitude, cameraPosition.target.longitude);
            createMovingPosition();
            mIsFirst = false;
        }
        if (mInitialMark != null) {
            mInitialMark.setToTop();
        }
        if (mPositionMark != null) {
            mPositionMark.setToTop();
            if (!isClickIdentification) {
                animMarker();
            }
        }
    }


    /**
     * 地图加载完成
     */
    @Override
    public void onMapLoaded() {
        mLocationTask.startLocate();
    }

    /**
     * 创建初始位置图标
     */
    private void createInitialPosition(double lat, double lng) {
        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.setFlat(true);
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.position(new LatLng(lat, lng));
        markerOptions.icon(initBitmap);
        mInitialMark = aMap.addMarker(markerOptions);
        mInitialMark.setClickable(false);
    }

    /**
     * 创建移动位置图标
     * 就是那个标杆
     */
    private void createMovingPosition() {
        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.setFlat(true);
//        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.position(new LatLng(0, 0));
        markerOptions.icon(moveBitmap);
        mPositionMark = aMap.addMarker(markerOptions);
        mPositionMark.setPositionByPixels(mMapView.getWidth() / 2,
                mMapView.getHeight() / 2);
        mPositionMark.setClickable(false);
    }

    @Override
    public void onLocationGet(PositionEntity entity) {
        // todo 这里在网络定位时可以减少一个逆地理编码
        Log.e("onLocationGet", "onLocationGet" + entity.address);
        RouteTask.getInstance(getApplicationContext()).setStartPoint(entity);
        mStartPosition = new LatLng(entity.latitue, entity.longitude);
        if (mIsFirstShow) {
            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                    mStartPosition, 17);
            aMap.animateCamera(cameraUpate);
            mIsFirstShow = false;
        }
        mInitialMark.setPosition(mStartPosition);
        initLocation = mStartPosition;
        Log.e("onLocationGet", "onLocationGet" + mStartPosition);
    }


    /**
     * 定位成功回调
     */
    @Override
    public void onRegecodeGet(PositionEntity entity) {
        Log.e(TAG, "onRegecodeGet" + entity.address);
        address = entity.address;
        entity.latitue = mStartPosition.latitude;
        entity.longitude = mStartPosition.longitude;
        RouteTask.getInstance(getApplicationContext()).setStartPoint(entity);
        RouteTask.getInstance(getApplicationContext()).search();
        Log.e(TAG, "onRegecodeGet" + mStartPosition);
    }

    @Override
    public void onRegecodeGetBike(PositionEntity entity) {

    }

    @Override
    public void onRouteCalculate(float cost, float distance, int duration) {
        Log.e(TAG, "cost" + cost + "---" + "distance" + distance + "---" + "duration" + duration);
        PositionEntity endPoint = RouteTask.getInstance(getApplicationContext()).getEndPoint();
        mRecordPositon = new LatLng(endPoint.latitue, endPoint.longitude);
        clickMap();
        RouteTask.getInstance(getApplicationContext()).setEndPoint(null);
    }

    @Override
    public void onTouch(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() >= 2) {
            aMap.getUiSettings().setScrollGesturesEnabled(false);
        } else {
            aMap.getUiSettings().setScrollGesturesEnabled(true);
        }
    }

    private void animMarker() {
        if (animator != null) {
            animator.start();
            return;
        }
        animator = ValueAnimator.ofFloat(mMapView.getHeight() / 2, mMapView.getHeight() / 2 - 30);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(150);
        animator.setRepeatCount(1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                mPositionMark.setPositionByPixels(mMapView.getWidth() / 2, Math.round(value));
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mPositionMark.setIcon(moveBitmap);
            }
        });
        animator.start();
    }

    private void endAnim() {
        if (animator != null && animator.isRunning())
            animator.end();
    }


    @Override
    public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

    }

    @Override
    public void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i) {

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult result, int errorCode) {
        LoadDialog.getInstance().dismiss();
        if (errorCode == AMapException.CODE_AMAP_SUCCESS) {
            if (result != null && result.getPaths() != null) {
                if (result.getPaths().size() > 0) {
                    mWalkRouteResult = result;
                    final WalkPath walkPath = mWalkRouteResult.getPaths()
                            .get(0);
                    walkRouteOverlay = new WalkRouteOverlay(
                            this, aMap, walkPath,
                            mWalkRouteResult.getStartPos(),
                            mWalkRouteResult.getTargetPos());
                    walkRouteOverlay.removeFromMap();
                    walkRouteOverlay.addToMap();
                    walkRouteOverlay.zoomToSpan();

                    int dis = (int) walkPath.getDistance();
                    int dur = (int) walkPath.getDuration();
                    time = AMapUtil.getFriendlyTimeArray(dur);
                    distance = AMapUtil.getFriendlyLength(dis);
                    String des = AMapUtil.getFriendlyTime(dur) + "(" + AMapUtil.getFriendlyLength(dis) + ")";
                    // tempMark.setTitle(des);
                    // tempMark.showInfoWindow();
                    //LogUtils.e(tempMark.getPeriod());

                    /**
                     *点击marker之后回调显示预约tip，点击地图消失,获取当前车的坐标点
                     */
                    if (1 == tempMark.getPeriod()) {
                        tempMark.setIcon(smallIdentificationBitmap);
                        bikeLongitude = tempMark.getPosition().longitude;
                        bikeLatitude = tempMark.getPosition().latitude;
                        bikeId = tempMark.getSnippet();//单车ID
                        showOrderTip(tempMark.getTitle(), AMapUtil.getFriendlyTime(dur), AMapUtil.getFriendlyLength(dis), address);//展示预约界面
                        return;
                    }
                    if (2 == tempMark.getPeriod()) {
                        cv_oederTip.setVisibility(View.GONE);
                        return;
                    }
                    Log.e(TAG, des);
                } else if (result != null && result.getPaths() == null) {
                    ToastUtils.showShort(R.string.no_result);
                }
            } else {
                ToastUtils.showShort(R.string.no_result);
            }
        } else {
            ToastUtils.showShort(errorCode);
        }
    }

    @Override
    public void onRideRouteSearched(RideRouteResult rideRouteResult, int i) {
    }

    /**
     * 开始搜索路径规划方案
     */
    public void searchRouteResult(int routeType, int mode) {
        if (mStartPoint == null) {
            ToastUtils.showShort("定位中，稍后再试...");
            return;
        }
        if (mEndPoint == null) {
            ToastUtils.showShort("终点未设置");
        }
        showDialog();
        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                mStartPoint, mEndPoint);
        if (routeType == ROUTE_TYPE_WALK) {// 步行路径规划
            RouteSearch.WalkRouteQuery query = new RouteSearch.WalkRouteQuery(fromAndTo, mode);
            mRouteSearch.calculateWalkRouteAsyn(query);// 异步路径规划步行模式查询
        }
    }


    private void showDialog() {
        LoadDialog loadDialog = LoadDialog.getInstance();
        loadDialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.load_dialog);
        LoadDialog.getInstance().show(getSupportFragmentManager(), "");
    }

    @Override
    public void onMapClick(LatLng latLng) {
        clickMap();
    }


    private void clickRefresh() {
//        LogUtils.e(initLocation.longitude + initLocation.latitude);
        clickInitInfo();
        if (initLocation != null) {
            LogUtils.e(initLocation.longitude);
            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                    initLocation, 17f);
            aMap.animateCamera(cameraUpate);
        }
    }

    private void clickMap() {
        clickInitInfo();
        if (View.VISIBLE == cv_oederTip.getVisibility()) {//点击地图的时候，如果tip还在，则让它隐藏
            cv_oederTip.setVisibility(View.GONE);
        }
        if (mRecordPositon != null) {
            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                    mRecordPositon, 17f);
            aMap.animateCamera(cameraUpate);
        }
    }

    private void clickInitInfo() {
        isClickIdentification = false;
        if (null != tempMark) {
            if (2 == tempMark.getPeriod()) {
                tempMark.setIcon(stopIdentificationBitmap);
            } else {
                tempMark.setIcon(smallIdentificationBitmap);
            }
            tempMark.hideInfoWindow();
            tempMark = null;
        }
        if (null != walkRouteOverlay) {
            walkRouteOverlay.removeFromMap();
        }
    }


    @Override
    public View getInfoWindow(Marker marker) {
        Log.e(TAG, "getInfoWindow" + marker.getPeriod());
        View infoWindow = getLayoutInflater().inflate(
                R.layout.info_window, null);
        render(marker, infoWindow);
        return infoWindow;
    }


    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view) {
        TextView tv_time = (TextView) view.findViewById(R.id.tv_time);
        TextView tv_time_info = (TextView) view.findViewById(R.id.tv_time_info);
        TextView tv_distance = (TextView) view.findViewById(R.id.tv_distance);
        tv_time.setText(time[0]);
        tv_time_info.setText(time[1]);
        tv_distance.setText(distance);
    }


    @Override
    public View getInfoContents(Marker marker) {
        Log.e(TAG, "getInfoContents");
        return null;
    }


    //退出时的时间
    private long mExitTime;

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showShort("再点一次退出巧骑单车");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    /**
     * 判断状态,登录态,用于维修按钮
     */
    private void getStatusFapuit() {

        Api.getInstance().getDefault().getState(Config.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<LoginDataBean>>(new SubscriberListener<HttpResult<LoginDataBean>>() {

                    @Override
                    public void onSuccess(HttpResult<LoginDataBean> model) {
                        if (401 == model.getCode()) {
                            startActivity(LoginByPhoneActivity.class);
                            return;
                        }
                        if (model.isOk()) {
                            startActivity(new Intent(Main2Activity.this, FaultRepairActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        //  ToastUtils.showShort(msg);
                    }
                }, this, false));

    }

    /**
     * 判断状态,登录态
     */
    private void getStatus() {

        Api.getInstance().getDefault().getState(Config.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<LoginDataBean>>(new SubscriberListener<HttpResult<LoginDataBean>>() {

                    @Override
                    public void onSuccess(HttpResult<LoginDataBean> model) {
                        if (401 == model.getCode()) {
                            startActivity(LoginByPhoneActivity.class);
                            return;
                        }
                        if (model.isOk()) {
                            if (!model.getData().isRealnameAuth()) {
                                startActivity(CertificationActivity.class);
                                return;
                            }
                            if (!model.getData().isHasPayedDeposit()) {
                                startActivity(RechargeActivity.class);
                                return;
                            }
                            startActivityForResultSingle(ScanUnlockActivity.class, 2, 1002);
                        }

                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }, this, false));

    }

    /**
     * 预约用车
     */
    private void setOrderBike(final String bikeId1, double longtitude, double lattude, int maxDistance, final String address) {
        Api.getInstance().getDefault().getCreate(Config.TOKEN, bikeId1, longtitude, lattude, maxDistance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<CreateBean>>(new SubscriberListener<HttpResult<CreateBean>>() {
                    @Override
                    public void onSuccess(HttpResult<CreateBean> model) {
                        if (501 == model.getCode() && model.getMsg().equals("无押金")) {
                            Intent intent = new Intent(Main2Activity.this, RechargeActivity.class);//无押金前往充值
                            startActivity(intent);
                            return;
                        }
                        if (501 == model.getCode() && model.getMsg().equals("会员未实名认证")) {
                            Intent intent = new Intent(Main2Activity.this, CertificationActivity.class);//未实名前往实名
                            startActivity(intent);
                            return;
                        }
                        if (501 == model.getCode() && model.getMsg().equals("单车被预约")) {
                            ToastUtils.showShort(model.getMsg());
                            return;
                        }
                        if (401 == model.getCode()) {
                            // ToastUtils.showShort("请先登录");
                            Intent intent = new Intent(Main2Activity.this, LoginByPhoneActivity.class);//未实名前往实名
                            startActivity(intent);
                            return;
                        }
                        if (model.isOk()) {//倒计时
//                            Bundle bundle = new Bundle();
//                            bundle.putInt("leftTime", model.getData().getLeftTime());
//                            bundle.putString("address", address);
//                            bundle.putString("bikeId", model.getData().getOrder().getBike().getId());
//                            Intent intent = new Intent(Main2Activity.this, CancelDialogActivity.class);
//                            startActivity(intent);
//                            if (tempMark != null) {
//                                tempMark.setClickable(false);
//                            }
                            leftTime = model.getData().getLeftTime();
                            bikeId = model.getData().getOrder().getBike().getId();
                            shopUnOrderTip(leftTime, bikeId);//取消用车tip


                        } else if (EmptyUtils.isNotEmpty(model.getMsg())) {
                            ToastUtils.showShort(model.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, false));
    }

    /**
     * 取消预约
     */
    private void setCancelBike() {
        Api.getInstance().getDefault().getCancel(Config.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult>(new SubscriberListener<HttpResult>() {
                    @Override
                    public void onSuccess(HttpResult model) {
                        if (model.isOk()) {
                            //取消预约成功，倒计时结束
                            if (View.VISIBLE == cv_unOrderTip.getVisibility()) {
                                countdownProgress.setCountdownTimeCancel();
                                cv_unOrderTip.setVisibility(View.GONE);
                                Utils.clickMarkers();//设置maker可以点击

                            }
                        } else {
                            if (EmptyUtils.isNotEmpty(model.getMsg())) {
                                ToastUtils.showShort(model.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, false));
    }

    /**
     * 判断状态
     */
    private void getIndex(double longtitude, double latitude, final int maxDistance) {
        Api.getInstance().getDefault().getIndex(Config.TOKEN, longtitude, latitude, maxDistance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<IndexBean>>(new SubscriberListener<HttpResult<IndexBean>>() {
                    @Override
                    public void onSuccess(final HttpResult<IndexBean> model) {
                        mStopPointBeen = new ArrayList<StopPointBean>();
                        locationList = new ArrayList<BikeLocationBean>();
                        //得到单车的地理位置的数组
                        //通过state判断tip状态
                        String state = model.getData().getState();
                        //    BIKE_STATE = state;
                        Logger.e(state + "当前状态");//获得倒计时时间
                        //    locationList = new ArrayList<BikeLocationBean>();
                        if (STATE_NORMAL.equals(state)) {
                            if (EmptyUtils.isNotEmpty(model.getData().getNearBikes())) {
                                for (int i = 0; i < model.getData().getNearBikes().size(); i++) {
                                    //        Logger.e(model.getData().getNearBikes().size() + "qAq");
                                    BikeLocationBean bikeLocationBean = new BikeLocationBean();
                                    bikeLocationBean.setId(model.getData().getNearBikes().get(i).getBikeId());//ID
                                    bikeLocationBean.setLongtitude(model.getData().getNearBikes().get(i).getPosition().get(0));//第一个经度longtitude
                                    bikeLocationBean.setLatitude(model.getData().getNearBikes().get(i).getPosition().get(1));//第二个经度lattitude
                                    bikeLocationBean.setMoney(model.getData().getMemberRideConfig().getRental());//预计费用
                                    locationList.add(bikeLocationBean);

                                }
                                Utils.addEmulateList(aMap, locationList);//添加marker车
                            }
                            //     mStopPointBeen = new ArrayList<StopPointBean>();
                            if (EmptyUtils.isNotEmpty(model.getData().getParks())) {

                                for (int i = 0; i < model.getData().getParks().size(); i++) {
                                    StopPointBean stopPointBean = new StopPointBean();
                                    stopPointBean.setName(model.getData().getParks().get(i).getBikePark().getName());
                                    stopPointBean.setId(model.getData().getParks().get(i).getBikePark().getId());
                                    stopPointBean.setLongtitude(model.getData().getParks().get(i).getBikePark().getPosition().get(0));
                                    stopPointBean.setLatitude(model.getData().getParks().get(i).getBikePark().getPosition().get(1));
                                    mStopPointBeen.add(stopPointBean);
                                }
                                Utils.addEmulateStopPoint(aMap, mStopPointBeen);//添加停放点
                            }
//                            if (mIsFirstList) {
//                                Utils.addEmulateList(aMap, locationList);//添加marker车
//                                mIsFirstList = true;
//                            }
                        }
                        if (STATE_SUBSCRIBING.equals(state)) {//预约中

                            if (EmptyUtils.isNotEmpty(model.getData().getNearBikes())) {
                                for (int i = 0; i < model.getData().getNearBikes().size(); i++) {
                                    //        Logger.e(model.getData().getNearBikes().size() + "qAq");
                                    BikeLocationBean bikeLocationBean = new BikeLocationBean();
                                    bikeLocationBean.setId(model.getData().getNearBikes().get(i).getBikeId());//ID
                                    bikeLocationBean.setLongtitude(model.getData().getNearBikes().get(i).getPosition().get(0));//第一个经度longtitude
                                    bikeLocationBean.setLatitude(model.getData().getNearBikes().get(i).getPosition().get(1));//第二个经度lattitude
                                    bikeLocationBean.setMoney(model.getData().getMemberRideConfig().getRental());//预计费用
                                    locationList.add(bikeLocationBean);

                                }
                                Utils.addEmulateList(aMap, locationList);//添加marker车
                            }
                            //     mStopPointBeen = new ArrayList<StopPointBean>();
                            if (EmptyUtils.isNotEmpty(model.getData().getParks())) {

                                for (int i = 0; i < model.getData().getParks().size(); i++) {
                                    StopPointBean stopPointBean = new StopPointBean();
                                    stopPointBean.setName(model.getData().getParks().get(i).getBikePark().getName());
                                    stopPointBean.setId(model.getData().getParks().get(i).getBikePark().getId());
                                    stopPointBean.setLongtitude(model.getData().getParks().get(i).getBikePark().getPosition().get(0));
                                    stopPointBean.setLatitude(model.getData().getParks().get(i).getBikePark().getPosition().get(1));
                                    mStopPointBeen.add(stopPointBean);
                                }
                                Utils.addEmulateStopPoint(aMap, mStopPointBeen);//添加停放点
                            }
                            if (EmptyUtils.isNotEmpty(model.getData().getLeftTime())) {

//                                Intent intent = new Intent(Main2Activity.this, CancelDialogActivity.class);
//                                Bundle bundle = new Bundle();
//                                bundle.putInt("leftTime", model.getData().getLeftTime());
//                                bundle.putString("address", address);
//                                bundle.putString("bikeId", model.getData().getOrder().getBike().getId());
//                                intent.putExtras(bundle);
//                                startActivity(intent);
                                leftTime = model.getData().getLeftTime();
                                bikeId = model.getData().getOrder().getBike().getId();
                                shopUnOrderTip(leftTime, bikeId);//取消用车tip
                            }
                        } else if (STATE_RIDING.equals(state)) {//正在骑行
                            //  long timeSpan = TimeUtils.getTimeSpan(model.getData().getOrder().getEndTime(), model.getData().getOrder().getStartTime(), TimeConstants.SEC);
                            //  int time = (int) timeSpan;//获得秒
//                            bikeId = model.getData().getOrder().getBike().getId();//获得骑行单车编号

                            double start_Longtitude = model.getData().getOrder().getBike().getBikeLock().getPosition().get(0);
                            double start_Latitude = model.getData().getOrder().getBike().getBikeLock().getPosition().get(1);
                            LatLng start = new LatLng(start_Longtitude, start_Latitude);
                            LogUtils.e("计算起始车点跟当前车点的距离d" + initLocation);
                            if (EmptyUtils.isNotEmpty(initLocation)) {
                                //     double v = (double) AMapUtils.calculateArea(start, initLocation);//计算起始车点跟当前车点的距离
                                float v = AMapUtils.calculateArea(start, initLocation);
                                String str = String.valueOf(v);
                                int idx = str.lastIndexOf(".");//查找小数点的位置
                                String strNum = str.substring(0, idx);
                                double num = Double.valueOf(strNum);
                                LogUtils.e("计算起始车点跟当前车点的距离" + str + "---" + num);
                                showTipRide(model.getData().getMemberRideConfig().getRental() + "", AMapUtil.getFriendlyTimeByMe((int) (model.getData().getDuration() / 1000)) + "", num + "", model.getData().getOrder().getBike().getId());
                            } //else {
//                                showTipRide(model.getData().getMemberRideConfig().getRental() + "", AMapUtil.getFriendlyTimeByMe((int) (model.getData().getDuration() / 1000)) + "", 0, model.getData().getOrder().getBike().getId());
//
//                            }
                        } else if (STATE_AWAIT.equals(state)) {//待还车
                            orderId = model.getData().getOrder().getId();
                            rl_viewRidingSticker.setVisibility(View.GONE);//正在骑行可见
                            ivMainTitle.setVisibility(View.VISIBLE);//标题图片隐藏
                            tvMainTitle.setVisibility(View.GONE);//标题文字显示
                            llayoutScan.setVisibility(View.GONE);//扫描控件隐藏
                            showTakePhotoDlg("您还有一辆待还车辆，请完成还车!", "取消", "去还车");
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, false));
    }


    /**
     * @param title
     * @param cancle
     * @param ok     单车待还提示dialog
     */

    private void showTakePhotoDlg(String title, String cancle, String ok) {
        mAlertDialog.show();
        mAlertDialog.getWindow().setContentView(R.layout.dialog_photo_source);
        ((TextView) (mAlertDialog.getWindow().findViewById(R.id.photo_source_info))).setText(title);
        ((TextView) (mAlertDialog.getWindow().findViewById(R.id.photo_source_taking_picture))).setText(cancle);
        ((TextView) (mAlertDialog.getWindow().findViewById(R.id.photo_source_photo_graph))).setText(ok);
        mAlertDialog.getWindow().findViewById(R.id.photo_source_taking_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
            }
        });//灰

        mAlertDialog.getWindow().findViewById(R.id.photo_source_photo_graph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAlertDialog.dismiss();
                //执行去还车操作--->单车待还
                Intent intent = new Intent(Main2Activity.this, RideBikeStayStillActivity.class);
                intent.putExtra(Config.SCANUNLOCK_KEY, orderId);//将对应位置订单ID传给另一个界面
                startActivity(intent);
            }
        });
    }//蓝

//    //单车待还之后返回重新定位
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (null != data) {
//            int result = data.getIntExtra("activity_scan", 0);
//            if (1002 == result) {
//                getIndex(mStartPosition.longitude, mStartPosition.latitude, 5000);
//
//            }
//        }
//
//
//    }

    /**
     * 从faultrepairactivity传过来的数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBusFaultRepair(MessageEventInt msg) {
        Logger.e(msg.getType() + "<----------->");
        if (1 == msg.getType()) {
            ToastUtils.showShort("关锁成功");
            rl_viewRidingSticker.setVisibility(View.GONE);//正在骑行不可见
            ivMainTitle.setVisibility(View.VISIBLE);//标题图片隐藏
            tvMainTitle.setVisibility(View.GONE);//标题文字显示
            llayoutScan.setVisibility(View.GONE);//扫描控件隐藏
            showTakePhotoDlg("您还有一辆待还车辆，请完成还车!", "取消", "去还车");
        } else if (3 == msg.getType()) {
            //后台结束骑行，socket返回值done，改为3，显示normal页面
            if (mAlertDialog.isShowing()) {
                mAlertDialog.dismiss();
            }
            //  rl_viewRidingSticker.setVisibility(View.GONE);//正在骑行不可见
            //  ivMainTitle.setVisibility(View.VISIBLE);//标题图片隐藏
            //    tvMainTitle.setVisibility(View.GONE);//标题文字显示
            llayoutScan.setVisibility(View.VISIBLE);//扫描控件隐藏
        }
    }

}
