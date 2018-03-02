package com.panda.sharebike.ui.dialogactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
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
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.widget.CountDownProgress;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CancelDialogActivity extends Activity {

    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.tv_unorder_car)
    TextView tvUnorderCar;
    @BindView(R.id.countdownProgress)
    CountDownProgress countdownProgress;
    @BindView(R.id.btn_cancelorder)
    Button btnCancelorder;
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.rl_un_order_view)
    RelativeLayout rlUnOrderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_un_order_sticker);
        ButterKnife.bind(this);
        // setFinishOnTouchOutside(false);
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        try {
            int leftTime = bundle.getInt("leftTime");
            String address = bundle.getString("address");
            String bikeId = bundle.getString("bikeId");
            tvUnorderCar.setText(bikeId);
            Logger.e(address + "---->");
            tvAddress.setText(address);
            countdownProgress.setCountdownTime(leftTime);
            countdownProgress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countdownProgress.startCountDownTime(new CountDownProgress.OnCountdownFinishListener() {
                        @Override
                        public void countdownFinished() {
                            CancelDialogActivity.this.finish();
                        }
                    });
                }
            });
            countdownProgress.performClick();//进来默认点击
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.btn_cancelorder, R.id.rl_un_order_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancelorder:
                setCancelBike();
                break;
            case R.id.rl_un_order_view:
                CancelDialogActivity.this.finish();
                break;
        }
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
                            //  countdownProgress.setCountdownTimeCancel();
                            CancelDialogActivity.this.finish();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countdownProgress.setCountdownTimeCancel();
    }
}