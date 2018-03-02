package com.panda.sharebike.ui.ride;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.LogUtils;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.app.AppApplication;
import com.panda.sharebike.model.entity.RideBikeStayStillBean;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.maintenance.FaultRepairActivity;
import com.panda.sharebike.ui.unlock.ScanUnlockActivity;
import com.panda.sharebike.util.AMapUtil;
import com.panda.sharebike.util.ImageLoaderUtils;
import com.panda.sharebike.util.ScreenUtil;
import com.panda.sharebike.util.SpannableStringUtil;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 单车待还
 */
public class RideBikeStayStillActivity extends BaseActivity {

    public final int START_TIME = 2000;//骑行时间

    @BindView(R.id.circleImageView)
    ImageView circleImageView;
    @BindView(R.id.cardView)
    CardView cardView;

    @BindView(R.id.rl_ride_view)
    RelativeLayout rlRideView;
    @BindView(R.id.mv_end)
    MapView mapView;
    @BindView(R.id.textView3)
    TextView tv_bikeId;
    @BindView(R.id.tv_riding)
    TextView tvRiding;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_distance)
    TextView tvDistance;
    @BindView(R.id.view_bike_waiting)
    RelativeLayout rl_viewBikeWaiting;//贴纸
    @BindView(R.id.llayout_scan)
    LinearLayout ll_LayoutScan;
    private AMap aMap;
    //绘制一条实线
    private double Lat_A = 30.274742;
    private double Lon_A = 119.987131;

    private double Lat_B = 30.274742;
    private double Lon_B = 119.997531;

    private double Lat_C = 30.285042;
    private double Lon_C = 119.997531;

    private double Lat_D = 30.285042;
    private double Lon_D = 119.999999;
    private BitmapDescriptor lineBitmap = null;
    private LatLonPoint mStartPoint = null;//起点，116.335891,39.942295
    private LatLonPoint mEndPoint = null;//终点，116.481288,39.995576
    private int code = 1000;
    private AppApplication mApplication;
    private AMapLocationClient locationClientSingle;
    private AMapLocation aMapLocation;
    private String id;
    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            rideTime += (1000 * 60);
            long nowTime = rideTime;
            nowTime = nowTime + (1000 * 60);
            int userTime = (int) (nowTime / 1000);
            String time = AMapUtil.getFriendlyTime(userTime);
            Message message = handler.obtainMessage();
            message.obj = time;
            message.what = START_TIME;
            handler.sendMessage(message);
            handler.postDelayed(myRunnable, 1000 * 60);
        }
    };

    private long rideTime;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_ride_bike_stay_still;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView = (MapView) findViewById(R.id.mv_end);
        mapView.onCreate(savedInstanceState);

        id = getIntent().getStringExtra(Config.SCANUNLOCK_KEY);
        LogUtils.e(id + "单车待还id");

        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.getUiSettings().setZoomControlsEnabled(false);
            //  aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mApplication.getLatitude(), mApplication.getLongitude()), 15));
        }
        //   mStartPoint = new LatLonPoint(Lat_A, Lon_A);
        //    mEndPoint = new LatLonPoint(Lat_D, Lon_D);
        //     setfromandtoMarker();
        //     addPolylinessoild();
        initLocation();//定位
    }

    private void initLocation() {
        locationClientSingle = new AMapLocationClient(this.getApplicationContext());
        AMapLocationClientOption locationClientSingleOption = new AMapLocationClientOption();
        //获取一次定位结果：
        //该方法默认为false。
        locationClientSingleOption.setOnceLocation(true);
        //关闭缓存机制
        locationClientSingleOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        locationClientSingle.setLocationOption(locationClientSingleOption);
        //启动定位
        locationClientSingle.startLocation();
        locationClientSingle.setLocationListener(locationSingleListener);
    }

    /**
     * 单次客户端的定位监听
     */
    AMapLocationListener locationSingleListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (location.getErrorCode() == 0) {
                aMapLocation = location;
                if (id != null) {
                    getIndex(aMapLocation);
                }
                zoomToSpan(new LatLng(location.getLatitude(), location.getLongitude()));
            }
        }
    };


    @Override
    protected void setUpView() {
        super.setUpView();

        aMap = mapView.getMap();

        ll_LayoutScan.setVisibility(View.VISIBLE);

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START_TIME:
                    String rideTime = (String) msg.obj;
                    LogUtils.e("time---" + rideTime);
                    SpannableStringBuilder spanTime = new SpannableStringBuilder(rideTime);
                    ForegroundColorSpan foregroundColorSpan1 = new ForegroundColorSpan(Color.parseColor("#FF333333"));
                    StyleSpan styleSpan1 = new StyleSpan(Typeface.BOLD);
                    RelativeSizeSpan relativeSizeSpan1 = new RelativeSizeSpan(1.5f);
                    spanTime.setSpan(relativeSizeSpan1, 0, rideTime.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spanTime.setSpan(foregroundColorSpan1, 0, rideTime.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spanTime.setSpan(styleSpan1, 0, rideTime.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spanTime.append("\n\n");
                    spanTime.append("骑行时间");
                    tvTime.setText(spanTime);

                    break;
            }
        }
    };

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, RideDetailActivity.class);
        mContext.startActivity(intent);
    }

    private void setfromandtoMarker(LatLonPoint mStartPoint) {
        aMap.addMarker(new MarkerOptions()
                .position(AMapUtil.convertToLatLng(mStartPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.qidian)));
    }

    /**
     * 移动镜头到当前的视角。
     *
     * @since V2.1.0
     */
    public void zoomToSpan(LatLng latLng) {
        try {
            LatLngBounds bounds = getLatLngBounds(latLng);
            aMap.animateCamera(CameraUpdateFactory
                    .newLatLngBounds(bounds, ScreenUtil.sysWidth(), ScreenUtil.sysHeight(), 50));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    protected LatLngBounds getLatLngBounds(LatLng latLng) {
        LatLngBounds.Builder b = LatLngBounds.builder();

        b.include(latLng);
        return b.build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
        handler.removeCallbacks(myRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    @OnClick({R.id.llayout_scan, R.id.iv_repair})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llayout_scan:
                startActivityForResultSingle(ScanUnlockActivity.class, 4, 1002);
                break;
            case R.id.iv_repair:
                startActivity(new Intent(RideBikeStayStillActivity.this, FaultRepairActivity.class));
                break;
        }

    }

    /**
     * 单车待还界面，使用的是index
     */
    private void getIndex(AMapLocation aMapLocation) {
        Api.getInstance().getDefault().getRideBikeStay(Config.TOKEN, aMapLocation.getLongitude(), aMapLocation.getLatitude(), 5000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<RideBikeStayStillBean>>(new SubscriberListener<HttpResult<RideBikeStayStillBean>>() {
                    @Override
                    public void onSuccess(HttpResult<RideBikeStayStillBean> model) {
                        if (model.isOk()) {
                            //骑行开始时间
                            ImageLoaderUtils.displayRound(RideBikeStayStillActivity.this, circleImageView, model.getData().getOrder().getMember().getAvatar());
                            tv_bikeId.setText(model.getData().getOrder().getBike().getId());
                            rideTime = model.getData().getDuration();//获得骑行时间
                            if (EmptyUtils.isNotEmpty(rideTime)) {
                                handler.post(myRunnable);
                            }
                            //         int time = (int) ((model.getData().getDuration() / 1000));//获得分钟
                            String time = AMapUtil.getFriendlyTime((int) ((model.getData().getDuration()) / 1000));
                            ;
                            //  int distance = (int) model.getData().getOrder().getDistance();//获得距离
                            //  model.getData().getOrder().getDistance();
                            String str = String.valueOf(model.getData().getOrder().getDistance());
                            int idx = str.lastIndexOf(".");//查找小数点的位置
                            String strNum = str.substring(0, idx);
//                            LogUtils.e(model.getData().getOrder().getDistance()
//                                    + "xxx" + str + "sss" + strNum + "xxxxx" + Integer.parseInt(strNum));
                            String length = AMapUtil.getFriendlyLength(Integer.parseInt(strNum));
                            SpannableStringUtil.setTipString(model.getData().getOrder().getCost() + "元", time, length, tvRiding, tvTime, tvDistance);
                            //绘制起始坐标和终点坐标
                            Lat_A = model.getData().getLocations().get(0).getPosition().get(1);
                            Lon_A = model.getData().getLocations().get(0).getPosition().get(0);
                            //     Lat_D = model.getData().getLocations().get(1).getPosition().get(1);
                            //      Lon_D = model.getData().getLocations().get(1).getPosition().get(0);
                            //   Logger.e(Lat_A + "s" + Lon_A + "s" + Lat_D + "S" + Lon_D);
                            //     mEndPoint = new LatLonPoint(Lat_D, Lon_D);
                            mStartPoint = new LatLonPoint(Lat_A, Lon_A);
                            if (EmptyUtils.isNotEmpty(mStartPoint))
                                setfromandtoMarker(mStartPoint);
                            //   addPolylinessoild();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, true));
    }

}
