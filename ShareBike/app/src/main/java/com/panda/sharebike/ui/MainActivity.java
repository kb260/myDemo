package com.panda.sharebike.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
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
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.dialog.AdviseDialogFragment;
import com.panda.sharebike.dialog.LoadDialog;
import com.panda.sharebike.kb260.KbData;
import com.panda.sharebike.lib.LocationTask;
import com.panda.sharebike.lib.OnLocationGetListener;
import com.panda.sharebike.lib.PositionEntity;
import com.panda.sharebike.lib.RegeocodeTask;
import com.panda.sharebike.lib.RouteTask;
import com.panda.sharebike.lib.Utils;
import com.panda.sharebike.model.MessageEventInt;
import com.panda.sharebike.model.entity.BikeLocationBean;
import com.panda.sharebike.model.entity.Bock;
import com.panda.sharebike.model.entity.CreateBean;
import com.panda.sharebike.model.entity.GuideBean;
import com.panda.sharebike.model.entity.IndexBean;
import com.panda.sharebike.model.entity.LoginDataBean;
import com.panda.sharebike.model.entity.StopPointBean;
import com.panda.sharebike.overlay.WalkRouteOverlay;
import com.panda.sharebike.receiver.NetBroadcastReceiver;
import com.panda.sharebike.service.SocketServices;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.deblocking.DeBlockingActivity;
import com.panda.sharebike.ui.login.CertificationActivity;
import com.panda.sharebike.ui.login.LoginByPhoneActivity;
import com.panda.sharebike.ui.login.RechargeActivity;
import com.panda.sharebike.ui.maintenance.FaultRepairActivity;
import com.panda.sharebike.ui.ride.RideBikeStayStillActivity;
import com.panda.sharebike.ui.ride.RideEndActivity;
import com.panda.sharebike.ui.search.DestinationActivity;
import com.panda.sharebike.ui.selfinfo.PersonalActivity;
import com.panda.sharebike.ui.unlock.ScanUnlockActivity;
import com.panda.sharebike.ui.widget.CountDownProgress;
import com.panda.sharebike.ui.widget.dialog.ConfirmDialog;
import com.panda.sharebike.util.AMapUtil;
import com.panda.sharebike.util.NetUtil;
import com.panda.sharebike.util.SpannableStringUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements AMap.OnCameraChangeListener, AMap.OnMapLoadedListener, OnLocationGetListener, RouteTask.OnRouteCalculateListener,
        AMap.OnMapTouchListener, RouteSearch.OnRouteSearchListener, AMap.OnMapClickListener, AMap.InfoWindowAdapter, NetBroadcastReceiver.NetEvevtListener {

    public static final String STATE_NORMAL = "normal";//显示找车界面
    public static final String STATE_SUBSCRIBING = "subscribing";//显示当前预约界面
    public static final String STATE_RIDING = "riding";//显示当前骑行界面
    public static final String STATE_AWAIT = "await";//点击后，显示当前骑行界面(未还车强行弹这个界面，点击market就弹，或者进入退押金就弹)
    public static final String STATE_DONE = "done";//点击后，显示当前骑行界面(未还车强行弹这个界面，点击market就弹，或者进入退押金就弹)
    public final int START_DISTANCE = 1000;//骑行距离
    public final int START_TIME = 2000;//骑行时间
    public final int START_EROOY = 3000;//骑行时间
    private String error = "发生错误";
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

    boolean bikeIsDone;

    private AMap aMap;
    @BindView(R.id.map_view)
    MapView mMapView;  //地图view
    //  private GeocodeSearch geocodeSearch;
    public static final String TAG = "MainActivity";
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

    //就第一次显示位置
    private boolean mIsFirstShow = true;
    private boolean mIsFirstLocation = true;
    private boolean mIsFirstBikeLocation = true;
    //定义一个状态值，如果有无网络就是true，调用接口，否则就是false不调用
    private boolean mIsNetWorkConnect = false;
    //经纬度
    private LatLng initLocation;
    private LatLng startLocation;//计算骑行起始位置
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
    private String address;
    private String bikeId = null;//单车ID
    private int leftTime = 0;//倒计时剩余时间

    private List<BikeLocationBean> locationList;
    private List<StopPointBean> mStopPointBeen;
    private String orderId = null;
    // private Handler handler = new Handler();
    private boolean run = true;
    private long rideTime;
    private int nowLenth;//当前计算的距离
    private int nowDistance;//已经骑行的距离
    private Runnable myRunnable = new Runnable() {//计算时间
        @Override
        public void run() {
            rideTime += 1000;
            // long nowTime = rideTime;
            //  nowTime += 1000;
            int userTime = (int) (rideTime / 1000);
            String time = AMapUtil.getFriendlyTimeForSecond(userTime);
            Message message = handler.obtainMessage();
            message.obj = time;
            message.what = START_TIME;
            handler.sendMessage(message);
            handler.postDelayed(myRunnable, 1000);
        }
    };
    private Runnable distanceRunnable = new Runnable() {
        @Override
        public void run() {
            // 首先获得一个消息结
            Message msg = handler.obtainMessage();
            try {
                msg.what = START_DISTANCE;
                handler.sendMessage(msg);
            } catch (Exception e) {
                msg.obj = "发生错误";
                msg.what = START_EROOY;
                handler.sendMessage(msg);
            }

        }


    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START_TIME:
                    String rideTime = (String) msg.obj;
                    SpannableStringBuilder spanTime = new SpannableStringBuilder(rideTime);
                    ForegroundColorSpan foregroundColorSpan1 = new ForegroundColorSpan(Color.parseColor("#FF333333"));
                    StyleSpan styleSpan1 = new StyleSpan(Typeface.BOLD);
                    RelativeSizeSpan relativeSizeSpan1 = new RelativeSizeSpan(1.5f);
                    spanTime.setSpan(relativeSizeSpan1, 0, rideTime.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spanTime.setSpan(foregroundColorSpan1, 0, rideTime.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spanTime.setSpan(styleSpan1, 0, rideTime.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spanTime.append("\n\n");
                    spanTime.append("骑行时间");
                    tvTimeIng.setText(spanTime);
                    break;
                case START_DISTANCE:
                    if (myRunnable != null) {
                        handler.removeCallbacks(myRunnable);
                    }
                    if (distanceRunnable != null) {
                        handler.removeCallbacks(distanceRunnable);
                    }
                    getIndex(initLocation.longitude, initLocation.latitude, 5000);//每隔1分钟调用一次正在骑行接口获取数据
                    break;
                case START_EROOY:
                    String erooy = (String) msg.obj;
                    ToastUtils.showShort(erooy);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected int getLayoutView() {
        return R.layout.activity_main2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!NetworkUtils.isConnected()){
            ToastUtils.showLong("网络错误！");
        }

        mMapView = (MapView) findViewById(R.id.map_view);
        mMapView.onCreate(savedInstanceState);
        mAlertDialog = new AlertDialog.Builder(this).create();
        mTv = (TextView) findViewById(R.id.tv_mt);
        //带有接口，必须要动态启动
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        NetBroadcastReceiver receiver = new NetBroadcastReceiver();
        registerReceiver(receiver, intentFilter);

        receiver.setmNetEvevtListener(this);


        initBitmap();
        initAMap();
        initLocation();
        RouteTask.getInstance(getApplicationContext()).addRouteCalculateListener(this);

        getGuideType();
    }


    /**
     * 从其他界面进入重新定位
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        //   Utils.removeMarkers();90
        if (myRunnable != null) {
            handler.removeCallbacks(myRunnable);//每次刷新地图时候，当正在运行时候，先取消线程，在重新打开线程
        }
        if (distanceRunnable != null) {
            handler.removeCallbacks(distanceRunnable);//每次刷新地图时候，当正在运行时候，先取消线程，在重新打开线程
        }
        if (EmptyUtils.isNotEmpty(initLocation)) {
            getIndex(initLocation.longitude, initLocation.latitude, 5000);
        }
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        hideTitleBar();
    }

    @Override
    public void onStop() {
        super.onStop();
        //  Utils.removeMarkers();
    }

    /**
     * 网络状态变化时的操作
     */
    @Override
    public void onNetChange(int netMobile) {

        if (NetUtil.NETWORK_NONE == netMobile) {
            mTv.setVisibility(View.VISIBLE);
            mIsNetWorkConnect = true;
        } else {
            //有网络连接了，在拉取一次呗，老板要求滴
            mTv.setVisibility(View.GONE);
            //    Utils.removeMarkers();
            if (myRunnable != null) {
                handler.removeCallbacks(myRunnable);//每次刷新地图时候，当正在运行时候，先取消线程，在重新打开线程
            }
            if (distanceRunnable != null) {
                handler.removeCallbacks(distanceRunnable);//每次刷新地图时候，当正在运行时候，先取消线程，在重新打开线程
            }
            if (EmptyUtils.isNotEmpty(initLocation) && mIsNetWorkConnect) {//这里大问题，一直调用
                getIndex(initLocation.longitude, initLocation.latitude, 5000);
            }
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBusEvent(String messageEvent) {
        Logger.e(messageEvent);
    }

    @OnClick({R.id.tv_mt, R.id.iv_main_self, R.id.iv_main_search, R.id.iv_location, R.id.llayout_scan, R.id.iv_refresh, R.id.iv_repair, R.id.btn_order_tip, R.id.btn_un_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mt:
                Intent intent2 = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
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
                // Utils.removeMarkers();
                if (myRunnable != null) {
                    handler.removeCallbacks(myRunnable);//每次刷新地图时候，当正在运行时候，先取消线程，在重新打开线程
                }
                if (distanceRunnable != null) {
                    handler.removeCallbacks(distanceRunnable);//每次刷新地图时候，当正在运行时候，先取消线程，在重新打开线程
                }
                if (EmptyUtils.isNotEmpty(initLocation)) {
                    getIndex(initLocation.longitude, initLocation.latitude, 5000);
                }
                break;
            case R.id.iv_repair:
                getStatusFapuit();
                break;
            case R.id.btn_order_tip://预约用车
                setOrderBike(bikeId, initLocation.longitude, initLocation.latitude, 5000);
                break;
            case R.id.btn_un_order:
                setCancelBike();//取消用车
                break;
            default:
                break;
        }
    }


    //预约用车
    private void showOrderTip(String money, String time, String distance) {
        if (distanceRunnable != null) {
            handler.removeCallbacks(distanceRunnable);
        }
        cv_unOrderTip.setVisibility(View.GONE);//取消用车tip不可见
        cv_oederTip.setVisibility(View.VISIBLE);//预约用车tip可见
        rl_viewRidingSticker.setVisibility(View.GONE);//正在骑行tip不可见
        rl_viewRidingSticker.setVisibility(View.GONE);//正在骑行tip隐藏
        ivMainTitle.setVisibility(View.VISIBLE);//标题图片可见
        llayoutScan.setVisibility(View.VISIBLE);//扫描控件可见
        //  tvOrderAddress.setText(address);
        SpannableStringUtil.setMainOrderTipString(money, time, distance, tvOrderCostTip, tvOrderTimeTip, tvOrderDistanceTip);//配置文字类型

    }

    //正在骑行
    private void showTipRide(String money, String time, String distance, String number) {
        // Thread thread = null;
        //  thread = new Thread(rideTimeTask);
        cv_unOrderTip.setVisibility(View.GONE);//取消用车tip不可见
        cv_oederTip.setVisibility(View.GONE);//预约用车tip不可见
        rl_viewRidingSticker.setVisibility(View.VISIBLE);//正在骑行ti不可见
        ivMainTitle.setVisibility(View.GONE);//标题图片隐藏
        tvMainTitle.setVisibility(View.VISIBLE);//标题文字显示
        llayoutScan.setVisibility(View.GONE);//扫描控件隐藏
        tvInfoBike.setText(number);
        SpannableStringUtil.setMainTipString(money, time, distance, tvRidingIng, tvTimeIng, tvDistanceIng);//配置文字类型
//        if (EmptyUtils.isNotEmpty(rideTime) && View.VISIBLE == rl_viewRidingSticker.getVisibility()) {
//            //每次间隔一分钟执行一次
//            new Thread(timeTask).start();
//            //修改，每一分钟调用一次接口，比自己计算更节省性能，自己计算还要算还要开辟多个线程
//        }
        if (EmptyUtils.isNotEmpty(initLocation) && View.VISIBLE == rl_viewRidingSticker.getVisibility()) {
            //每次间隔一分钟执行一次
            handler.postDelayed(distanceRunnable, 1000 * 90);
        } else {
            handler.removeCallbacks(distanceRunnable);
        }

    }

    //取消用车
    private void shopUnOrderTip(int leftTime, String bikeId) {
        if (distanceRunnable != null) {
            handler.removeCallbacks(distanceRunnable);
        }
        cv_unOrderTip.setVisibility(View.VISIBLE);//取消用车tip可见
        cv_oederTip.setVisibility(View.GONE);//预约用车tip不可见
        rl_viewRidingSticker.setVisibility(View.GONE);//正在骑行tip不可见
        rl_viewRidingSticker.setVisibility(View.GONE);//正在骑行tip隐藏
        ivMainTitle.setVisibility(View.VISIBLE);//标题图片可见
        llayoutScan.setVisibility(View.VISIBLE);//扫描控件可见

        Utils.unClickMarkers();//设置不可点击marker
        tvUnOrderBikeId.setText(bikeId);
        //tvUnOrderAddress.setText(address);
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
        mLocationTask = LocationTask.getInstance(getApplicationContext());
        mLocationTask.setOnLocationGetListener(this);
        mRegeocodeTask = new RegeocodeTask(getApplicationContext());
        mRegeocodeTask.setOnLocationGetListener(this);
    }

    // 定义 Marker 点击事件监听
    AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {

        // marker 对象被点击时回调的接口
        // 返回 true 则表示接口已响应事件，否则返回false
        @Override
        public boolean onMarkerClick(final Marker marker) {
            Log.e(TAG, "点击的Marker");

            isClickIdentification = true;
            LogUtils.e(marker.getPeriod());
            if (tempMark != null) {
                if (1 == marker.getPeriod()) {
                    marker.setIcon(smallIdentificationBitmap);
                } else {
                    marker.setIcon(stopIdentificationBitmap);
                }
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
                        // mStartPoint = new LatLonPoint(mRecordPositon.latitude, mRecordPositon.longitude);
                        mPositionMark.setPosition(mRecordPositon);
                        mEndPoint = new LatLonPoint(marker.getPosition().latitude, marker.getPosition().longitude);
                        if (1 == tempMark.getPeriod()) {
                            tempMark.setIcon(bigIdentificationBitmap);
                            mRegeocodeTask.search(tempMark.getPosition().latitude, tempMark.getPosition().longitude, 2);//反地理编码
                        } else {
                            tempMark.setIcon(stopIdentificationBitmap);
                        }
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
        handler.removeCallbacks(myRunnable);//移除线程
        handler.removeCallbacks(distanceRunnable);
        mMapView.onDestroy();
        mLocationTask.onDestroy();
        if (AdviseDialogFragment.getInstance().isVisible()) {
            AdviseDialogFragment.getInstance().dismiss();
        }
        RouteTask.getInstance(getApplicationContext()).removeRouteCalculateListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

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
        //   mRegeocodeTask.setOnLocationGetListener(this);
        mRegeocodeTask.search(mStartPosition.latitude, mStartPosition.longitude, 1);
//        Utils.removeMarkers();
        if (mIsFirst) {
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
        if (EmptyUtils.isEmpty(entity)) {
            handler.removeCallbacks(distanceRunnable);//如果定位为空，解除距离测算
        }

        RouteTask.getInstance(getApplicationContext()).setStartPoint(entity);
        mStartPosition = new LatLng(entity.latitue, entity.longitude);
        mStartPoint = new LatLonPoint(entity.latitue, entity.longitude);//改成从当前位置定位而不是杠杆定位
        if (mIsFirstShow) {
            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                    mStartPosition, 17);
            aMap.animateCamera(cameraUpate);
            mIsFirstShow = false;
        }
        mInitialMark.setPosition(mStartPosition);
        initLocation = mStartPosition;

        if (mIsFirstLocation && EmptyUtils.isNotEmpty(initLocation)) {
            getIndex(initLocation.longitude, initLocation.latitude, 5000);
            CameraUpdate cameraUpate = CameraUpdateFactory.newLatLngZoom(
                    mStartPosition, 17);
            aMap.animateCamera(cameraUpate);
            mIsFirstLocation = false;
        }
    }
    /**
     * 定位成功回调
     */
    @Override
    public void onRegecodeGet(PositionEntity entity) {
        Log.e(TAG, "onRegecodeGet" + entity.address);

        if (mIsFirstBikeLocation) {
            tvOrderAddress.setText(entity.address);
            tvUnOrderAddress.setText(entity.address);
            mRegeocodeTask.search(entity.latitue, entity.longitude, 2);
            mIsFirstBikeLocation = false;
        }
        entity.latitue = mStartPosition.latitude;
        entity.longitude = mStartPosition.longitude;
        RouteTask.getInstance(getApplicationContext()).setStartPoint(entity);
        RouteTask.getInstance(getApplicationContext()).search();
        Log.e(TAG, "onRegecodeGet" + mStartPosition);
    }

    /**
     * 地图上车的点定位成功回调
     */
    @Override
    public void onRegecodeGetBike(PositionEntity entity) {
        //   LogUtils.e("单车单车的定位：" + entity.address);
        //  address = entity.address;
        tvOrderAddress.setText(entity.address);
        tvUnOrderAddress.setText(entity.address);
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
        if (animator != null && animator.isRunning()){
            animator.end();
        }
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
                        //   tempMark.setIcon(smallIdentificationBitmap);
                        bikeId = tempMark.getSnippet();//单车ID
                        showOrderTip(tempMark.getTitle(), AMapUtil.getFriendlyTime(dur), AMapUtil.getFriendlyLength(dis));//展示预约界面
                    } else if (2 == tempMark.getPeriod()) {
                        //    tempMark.setIcon(stopIdentificationBitmap);
                        cv_oederTip.setVisibility(View.GONE);
                    }
//                    if (2 == tempMark.getPeriod()) {
//                        cv_oederTip.setVisibility(View.GONE);
//                    }
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

    private void showAdviseDialog(String tag) {
        boolean is = SPUtils.getInstance("dialog").getBoolean("first_dialog");
        if (is) {
            //  AdviseDialogFragment adviseDialog = AdviseDialogFragment.getInstance();
            AdviseDialogFragment.getInstance().show(getSupportFragmentManager(), tag);
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        clickMap();
    }


    private void clickRefresh() {
        clickInitInfo();
        if (initLocation != null) {
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
        //  mIsFirstBikeLocation = false;
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
            ToastUtils.showShort("再点一次退出OKbike");
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
                            if (!model.getData().isRealnameAuth()) {//未实名不可报修
                                startActivity(CertificationActivity.class);
                                return;
                            }
                            startActivity(new Intent(MainActivity.this, FaultRepairActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
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
                            //实名认证
                            if (!model.getData().isRealnameAuth()) {
                                startActivity(CertificationActivity.class);
                                return;
                            }
                            //充值押金
                            if (!model.getData().isHasPayedDeposit()) {
                                startActivity(RechargeActivity.class);
                                return;
                            }

                            if (EmptyUtils.isNotEmpty(initLocation)) {
                                getScanIsAwait(initLocation.longitude, initLocation.latitude, 5000);//判断是否有需要还的车
                            }
// else {
//                                getScanIsAwait(mRecordPositon.longitude, mRecordPositon.latitude, 5000);//定位不成功折衷啊
//                            }
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
    private void setOrderBike(final String bikeId1, double longtitude, double lattude, int maxDistance) {
        Api.getInstance().getDefault().getCreate(Config.TOKEN, bikeId1, longtitude, lattude, maxDistance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<CreateBean>>(new SubscriberListener<HttpResult<CreateBean>>() {
                    @Override
                    public void onSuccess(HttpResult<CreateBean> model) {
                        if (501 == model.getCode() && model.getMsg().equals("无押金")) {
                            Intent intent = new Intent(MainActivity.this, RechargeActivity.class);//无押金前往充值
                            startActivity(intent);
                            return;
                        }
                        if (501 == model.getCode() && model.getMsg().equals("会员未实名认证")) {
                            Intent intent = new Intent(MainActivity.this, CertificationActivity.class);//未实名前往实名
                            startActivity(intent);
                            return;
                        }
                        if (501 == model.getCode() && model.getMsg().equals("单车被预约")) {
                            ToastUtils.showShort(model.getMsg());
                            return;
                        }
                        if (401 == model.getCode()) {
                            Intent intent = new Intent(MainActivity.this, LoginByPhoneActivity.class);//未实名前往实名
                            startActivity(intent);
                            return;
                        }
                        if (model.isOk()) {//倒计时

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
                        if (model.getData().getOrder().getSuperzone()){
                            showDialogError(model.getData().getOrder().getBike().getId());
                        }
                        mIsNetWorkConnect = false;
                        //  Utils.removeMarkers();
                        mStopPointBeen = new ArrayList<StopPointBean>();
                        locationList = new ArrayList<BikeLocationBean>();
                        //得到单车的地理位置的数组
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
                        if (EmptyUtils.isNotEmpty(model.getData().getNearBikes())) {
                            for (int i = 0; i < model.getData().getNearBikes().size(); i++) {
                                BikeLocationBean bikeLocationBean = new BikeLocationBean();
                                bikeLocationBean.setId(model.getData().getNearBikes().get(i).getBikeId());//ID
                                bikeLocationBean.setLongtitude(model.getData().getNearBikes().get(i).getPosition().get(0));//第一个经度longtitude
                                bikeLocationBean.setLatitude(model.getData().getNearBikes().get(i).getPosition().get(1));//第二个纬度lattitude
                                bikeLocationBean.setMoney(model.getData().getMemberRideConfig().getRental());//预计费用
                                locationList.add(bikeLocationBean);

                            }
                            Utils.addEmulateList(aMap, locationList);//添加marker车
                        }
                        //通过state判断tip状态
                        String state = model.getData().getState();
                        Logger.e(state + "当前状态");//获得倒计时时间
                        if (STATE_NORMAL.equals(state)) {
                            ridingSticker();//如何在其他账号登录，把正在骑行状态取消掉
                        }
                        if (STATE_SUBSCRIBING.equals(state)) {//预约中

                            if (EmptyUtils.isNotEmpty(model.getData().getLeftTime())) {
                                // bikeLatitude = model.getData().getOrder().getBike().getBikeLock().getPosition().get(1);
                                //  bikeLongitude = model.getData().getOrder().getBike().getBikeLock().getPosition().get(0);
                                leftTime = model.getData().getLeftTime();
                                bikeId = model.getData().getOrder().getBike().getId();
                                shopUnOrderTip(leftTime, bikeId);//取消用车tip
                            }
                        }
                        if (STATE_RIDING.equals(state)) {//正在骑行
                            Utils.unClickMarkers();
                            rideTime = (model.getData().getDuration());//起始的骑行时间(时间戳)
                            if (EmptyUtils.isNotEmpty(model.getData().getOrder().getBike())) {
                                //bikeLongitude = model.getData().getOrder().getBike().getBikeLock().getPosition().get(0);//
                                //  bikeLatitude = model.getData().getOrder().getBike().getBikeLock().getPosition().get(1);//
                            }
                            if (EmptyUtils.isNotEmpty(rideTime)) {
                                handler.post(myRunnable);
                            }
                            String time = AMapUtil.getFriendlyTime((int) (model.getData().getDuration() / 1000));//骑行时间
                            if (EmptyUtils.isNotEmpty(initLocation)) {
                                //  handler.post(distanceRunnable);//开始计算距离

                                String str = String.valueOf(model.getData().getOrder().getDistance());
                                int idx = str.lastIndexOf(".");//查找小数点的位置
                                String strNum = str.substring(0, idx);
                                int distance = Integer.valueOf(strNum);
                                String distances = AMapUtil.getFriendlyLength(distance);//骑行距离

                                showTipRide(model.getData().getOrder().getCost() + "元", time, distances, model.getData().getOrder().getBike().getNum());
                            }
                        }
                        if (STATE_AWAIT.equals(state)) {//待还车
                            orderId = model.getData().getOrder().getId();
                            ridingSticker();
                            showTakePhotoDlg("您还有一辆待还车辆，请完成还车!", "取消", "去还车");
                        }
                    }
                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, false));
    }

    /**
     * 判断状态,在扫码还车的时候调用，看看是否需要还车
     */
    private void getScanIsAwait(double longtitude, double latitude, final int maxDistance) {
        Api.getInstance().getDefault().getIndex(Config.TOKEN, longtitude, latitude, maxDistance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<IndexBean>>(new SubscriberListener<HttpResult<IndexBean>>() {
                    @Override
                    public void onSuccess(final HttpResult<IndexBean> model) {
                        //通过state判断tip状态
                        if (model.isOk()) {
                            String state = model.getData().getState();
                            if (STATE_AWAIT.equals(state)) {//待还车
                                showTakePhotoDlg("您还有一辆待还车辆，请完成还车!", "取消", "去还车");
                            } else {
                                startActivityForResultSingle(ScanUnlockActivity.class, 2, 1002);//直接跳转
                            }
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
    public void showTakePhotoDlg(String title, String cancle, String ok) {
        //  Utils.removeMarkers();//每次单车待还的时候移除所有的marker
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
                Intent intent = new Intent(MainActivity.this, RideBikeStayStillActivity.class);
                LogUtils.e(orderId + "订单id");
                intent.putExtra(Config.SCANUNLOCK_KEY, orderId);//将对应位置订单ID传给另一个界面
                startActivity(intent);
            }
        });
    }//蓝

    /**
     * 从faultrepairactivity传过来的数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBusFaultRepair(MessageEventInt msg) {
        Logger.e(msg.getType() + "<----------->");
        if (1 == msg.getType()) {
            ToastUtils.showShort("关锁成功");
            ridingSticker();
            // showTakePhotoDlg("您还有一辆待还车辆，请完成还车!", "取消", "去还车");
            //    Utils.removeMarkers();
            if (myRunnable != null) {
                handler.removeCallbacks(myRunnable);//每次刷新地图时候，当正在运行时候，先取消线程，在重新打开线程
            }
            if (distanceRunnable != null) {
                handler.removeCallbacks(distanceRunnable);//每次刷新地图时候，当正在运行时候，先取消线程，在重新打开线程
            }
            getIndex(initLocation.longitude, initLocation.latitude, 5000);
            if (msg.getOrderId()!= null){
                Intent intent = new Intent(MainActivity.this, RideEndActivity.class);
                intent.putExtra(Config.SCANUNLOCK_KEY, msg.getOrderId());//将对应位置订单ID传给另一个界面
                startActivity(intent);
            }
        } else if (3 == msg.getType()) {
            //后台结束骑行，socket返回值done，改为3，显示normal页面
            if (mAlertDialog.isShowing()) {
                mAlertDialog.dismiss();
            }
            rl_viewRidingSticker.setVisibility(View.GONE);//正在骑行不可见
            ivMainTitle.setVisibility(View.VISIBLE);//标题图片隐藏
            tvMainTitle.setVisibility(View.GONE);//标题文字显示
            llayoutScan.setVisibility(View.VISIBLE);//扫描控件显示
            if (EmptyUtils.isNotEmpty(initLocation)){
                getIndex(initLocation.longitude, initLocation.latitude, 5000);
            }
            if (myRunnable != null) {
                handler.removeCallbacks(myRunnable);
            }
            if (distanceRunnable != null) {
                handler.removeCallbacks(distanceRunnable);
            }
        }else if (4 == msg.getType()) {
            getIndex(initLocation.longitude, initLocation.latitude, 5000);
        }else if (5 == msg.getType()) {
            getIndex(initLocation.longitude, initLocation.latitude, 5000);
        }
    }

    private void ridingSticker() {
        cv_oederTip.setVisibility(View.GONE);

        rl_viewRidingSticker.setVisibility(View.GONE);//全部状态隐藏
        ivMainTitle.setVisibility(View.VISIBLE);//标题图片隐藏
        tvMainTitle.setVisibility(View.GONE);//标题文字显示
        llayoutScan.setVisibility(View.VISIBLE);//扫描控件显示
        if (View.VISIBLE == cv_unOrderTip.getVisibility()) {
            countdownProgress.setCountdownTimeCancel();
            cv_unOrderTip.setVisibility(View.GONE);
            Utils.clickMarkers();//设置maker可以点击
        }

    }
    /**
     * 判断状态,登录态,用于维修按钮
     */
    private void getGuideType() {
        Api.getInstance().getDefault().getGuide(Config.TOKEN,"header")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<GuideBean>>(new SubscriberListener<HttpResult<GuideBean>>() {

                    @Override
                    public void onSuccess(HttpResult<GuideBean> model) {
                        if (401 == model.getCode()) {
                            startActivity(LoginByPhoneActivity.class);
                            return;
                        }
                        if (model.isOk()) {
                            String content = model.getData().getContent();
                            if (content!=null) {//未实名不可报修
                                showAdviseDialog(content);//公告
                            }
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                    }
                }, this, false));

    }

    public void showDialogError(final String bikeId){
        ConfirmDialog.newInstance(new ConfirmDialog.OkListener(){
            @Override
            public void isOk() {
                getVersion(bikeId);
            }
        })
                .setMargin(50)
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }


    /**
     * 判断状态,登录态,用于维修按钮
     */
    private void getVersion(String id) {
        Api.getInstance().getDefault().getLock(Config.TOKEN, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<Bock>>(new SubscriberListener<HttpResult<Bock>>() {

                    @Override
                    public void onSuccess(HttpResult<Bock> model) {
                        if (401 == model.getCode()) {
                            startActivity(LoginByPhoneActivity.class);
                            return;
                        }
                        if (501 == model.getCode()) {
                            ToastUtils.showShort(model.getMsg());
                            return;
                        }
                        if (502 == model.getCode()) {
                            showTakePhotoDlg("该车锁有故障，换一辆再扫吧！", "取消", "去报修");
                            return;
                        }
                        if (model.isOk()) {
                            String  ps = model.getData().getBikeLock().getPassword();
                            String mac =  model.getData().getBikeLock().getMacAddress();
                            if (!mac.contains(":") && mac.length() == 12){
                                mac = addSpace(mac);
                            }

                            Intent intent = new Intent(MainActivity.this, DeBlockingActivity.class);
                            intent.putExtra("ps",ps);
                            intent.putExtra("mac",mac);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                    }
                }, this, false));

    }

    private String addSpace(String bankAccountNumber) {
        if (bankAccountNumber==null){
            return "";
        }
        char[] strs=bankAccountNumber.toCharArray();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
            if (i!=0&&(i+1)%2==0 && i != strs.length-1){
                sb.append(":");
            }
        }
        String trim = sb.toString().trim();
        return trim;

    }

}
