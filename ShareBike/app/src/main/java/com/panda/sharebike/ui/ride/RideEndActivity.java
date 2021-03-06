package com.panda.sharebike.ui.ride;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.app.AppApplication;
import com.panda.sharebike.model.entity.DetailBean;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.MainActivity;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.util.AMapUtil;
import com.panda.sharebike.util.ImageLoaderUtils;
import com.panda.sharebike.util.ScreenUtil;
import com.panda.sharebike.util.SpannableStringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 骑行结束(还车成功之后进入)
 */
public class RideEndActivity extends BaseActivity {


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

    @Override
    protected int getLayoutView() {
        return R.layout.activity_ride_end;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView = (MapView) findViewById(R.id.mv_end);
        mapView.onCreate(savedInstanceState);
        circleImageView.bringToFront();//需要设置这个属性，将背后的图片前置
        //android4.4之前的版本需要让view的父控件调用这两个方法使其重绘。
        rlRideView.requestLayout();
        rlRideView.invalidate();
        mApplication = (AppApplication) AppApplication.getAppContext();
        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.getUiSettings().setZoomControlsEnabled(false);
            aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mApplication.getLatitude(), mApplication.getLongitude()), 15));
        }
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        setLeftIcon(R.drawable.icon_title_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RideEndActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                RideEndActivity.this.finish();
            }
        });
        String id = getIntent().getStringExtra(Config.SCANUNLOCK_KEY);
        aMap = mapView.getMap();
        setTitle("结束骑行");
        ll_LayoutScan.setVisibility(View.GONE);
        if (id != null) {
            getHttpDetail(id);
        }

    }

    //绘制一条实线A->D,如果需要曲线，则需要多个点
    private void addPolylinessoild() {
        LatLng A = new LatLng(Lat_A, Lon_A);
        LatLng D = new LatLng(Lat_D, Lon_D);
        if (lineBitmap == null) {
            lineBitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_map_go);
        }
        aMap.addPolyline((new PolylineOptions())
                .add(A, D)
                .width(18f)
                .setCustomTexture(lineBitmap));
        zoomToSpan();
    }

    private void setfromandtoMarker() {
        aMap.addMarker(new MarkerOptions()
                .position(AMapUtil.convertToLatLng(mStartPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.qidian)));
        aMap.addMarker(new MarkerOptions()
                .position(AMapUtil.convertToLatLng(mEndPoint))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.zhongdian)));
    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, RideDetailActivity.class);
        mContext.startActivity(intent);
    }

    /**
     * 移动镜头到当前的视角。
     *
     * @since V2.1.0
     */
    public void zoomToSpan() {
        try {
            LatLngBounds bounds = getLatLngBounds();
            aMap.animateCamera(CameraUpdateFactory
                    .newLatLngBounds(bounds, ScreenUtil.sysWidth(), ScreenUtil.sysHeight(), 50));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    protected LatLngBounds getLatLngBounds() {
        LatLngBounds.Builder b = LatLngBounds.builder();
        b.include(new LatLng(Lat_A, Lon_A));
        b.include(new LatLng(Lat_D, Lon_D));
        return b.build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
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

    /**
     * 行程详情
     *
     * @param bikeId
     */
    private void getHttpDetail(final String bikeId) {
        Api.getInstance().getDefault().getDetailRide(Config.TOKEN, bikeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<DetailBean>>(new SubscriberListener<HttpResult<DetailBean>>() {
                    @Override
                    public void onSuccess(HttpResult<DetailBean> model) {
                        if (model.isOk()) {
                            //骑行开始时间
                            ImageLoaderUtils.displayRound(RideEndActivity.this, circleImageView, model.getData().getOrder().getMember().getAvatar());
                            tv_bikeId.setText(model.getData().getOrder().getBike().getNum());
                            // long timeSpan = TimeUtils.getTimeSpan(model.getData().getOrder().getEndTime(), model.getData().getOrder().getStartTime(), TimeConstants.MIN);
                            //   int time = (int) (timeSpan);//获得分钟
                            String time = AMapUtil.getFriendlyTime((int) ((model.getData().getOrder().getDuration()) / 1000));
                            //  int distance = (int) model.getData().getOrder().getDistance();//获得距离
                            //获得距离
                            String str = String.valueOf(model.getData().getOrder().getDistance());
                            int idx = str.lastIndexOf(".");//查找小数点的位置
                            String strNum = str.substring(0, idx);
                            String length = AMapUtil.getFriendlyLength(Integer.parseInt(strNum));

                            SpannableStringUtil.setTipString(model.getData().getOrder().getCost() + "元", time, length, tvRiding, tvTime, tvDistance);
                            //绘制起始坐标和终点坐标
                            List<DetailBean.LocationsBean> locations = new ArrayList<DetailBean.LocationsBean>();
                            locations = model.getData().getLocations();//获得所有的点
                            Lat_A = model.getData().getLocations().get(1).getPosition().get(1);
                            Lon_A = model.getData().getLocations().get(1).getPosition().get(0);
                            Lat_D = model.getData().getLocations().get(locations.size() - 1).getPosition().get(1);
                            Lon_D = model.getData().getLocations().get(locations.size() - 1).getPosition().get(0);
                            //   Logger.e(Lat_A + "s" + Lon_A + "s" + Lat_D + "S" + Lon_D);
                            mStartPoint = new LatLonPoint(Lat_A, Lon_A);
                            mEndPoint = new LatLonPoint(Lat_D, Lon_D);
                            setfromandtoMarker();
                            addPolylinessoild();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, true));
    }

}
