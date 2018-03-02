package com.panda.sharebike.ui.dialogactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.model.entity.CreateBean;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.login.RechargeActivity;
import com.panda.sharebike.util.SpannableStringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderDialogActivity extends Activity {

    @BindView(R.id.order_title)
    TextView orderTitle;
    @BindView(R.id.tv_riding_order)
    TextView tvRidingOrder;
    @BindView(R.id.tv_time_order)
    TextView tvTimeOrder;
    @BindView(R.id.tv_distance_order)
    TextView tvDistanceOrder;
    @BindView(R.id.btn_order_sticker)
    Button btnOrderSticker;
    @BindView(R.id.view_order_sticker)
    RelativeLayout viewOrderSticker;
    private String address = null;
    private String money = null;
    private String time = null;
    private String distance = null;
    private String bikeId = null;
    private double longitude;
    private double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.view_order_sticker);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        try {
            Bundle bundle = getIntent().getExtras();
            money = bundle.getString("money");
            time = bundle.getString("time");
            distance = bundle.getString("distance");
            address = bundle.getString("address");
            bikeId = bundle.getString("bikeId");
            longitude = bundle.getDouble("longitude");
            latitude = bundle.getDouble("latitude");
            Logger.e(bikeId + "---" + latitude + "---" + longitude + "-->" + distance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        orderTitle.setText(address);
        SpannableStringUtil.setMainOrderTipString(money, time, distance, tvRidingOrder, tvTimeOrder, tvDistanceOrder);//配置文字类型
    }

    @OnClick({R.id.btn_order_sticker, R.id.view_order_sticker})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_order_sticker:
                OrderDialogActivity.this.finish();
                break;
            case R.id.btn_order_sticker:
                setOrderBike(bikeId, longitude, latitude, 5000);

                break;
        }
    }

    /**
     * 预约用车
     */
    private void setOrderBike(String bikeId, double longtitude, double lattude, int maxDistance) {
        Api.getInstance().getDefault().getCreate(Config.TOKEN, bikeId, longtitude, lattude, maxDistance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<CreateBean>>(new SubscriberListener<HttpResult<CreateBean>>() {
                    @Override
                    public void onSuccess(HttpResult<CreateBean> model) {
                        if (501 == model.getCode() && model.getMsg().equals("无押金")) {
                            Intent intent = new Intent(OrderDialogActivity.this, RechargeActivity.class);//无押金前往充值
                            startActivity(intent);
                        }
                        if (401 == model.getCode()) {
                            ToastUtils.showShort("请先登录");
                        }
                        if (model.isOk()) {//倒计时
                            Intent intent = new Intent(OrderDialogActivity.this, CancelDialogActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("leftTime", model.getData().getLeftTime());
                            bundle.putString("address", address);
                            bundle.putString("bikeId", model.getData().getOrder().getBike().getId());
                            intent.putExtras(bundle);
                            startActivity(intent);
                            OrderDialogActivity.this.finish();
                        } else if (EmptyUtils.isNotEmpty(model.getMsg())) {
                            ToastUtils.showShort(model.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, false));
    }
}
