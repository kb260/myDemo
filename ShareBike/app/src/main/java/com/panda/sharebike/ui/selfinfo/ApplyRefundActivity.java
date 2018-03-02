package com.panda.sharebike.ui.selfinfo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.model.entity.RefunddepositBean;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 申请退押金
 */
public class ApplyRefundActivity extends BaseActivity {
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.textView)
    TextView textView;
    private double money;
    private String mReason = "其他";

    @Override
    protected int getLayoutView() {
        return R.layout.activity_apply_refund;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        money = getIntent().getDoubleExtra("money", 199);
        tvCount.setText(money + "元");
    }


    @OnClick({R.id.rb_money_more, R.id.rb_bike_no_ride, R.id.rb_bike_less, R.id.rb_charge_more, R.id.rb_bike_ride_less, R.id.rb_bike_ride_other, R.id.rb_other, R.id.btn_refund})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_money_more:
                mReason = "车费太贵了";
                break;
            case R.id.rb_bike_no_ride:
                mReason = "车子难骑";
                break;
            case R.id.rb_bike_less:
                mReason = "车子太少了";
                break;
            case R.id.rb_charge_more:
                mReason = "押金有点高";
                break;
            case R.id.rb_bike_ride_less:
                mReason = "很少骑车了";
                break;
            case R.id.rb_bike_ride_other:
                mReason = "骑其他车子了";
                break;
            case R.id.rb_other:
                mReason = "其他";
                break;
            case R.id.btn_refund:
                getRefund(mReason);
//                startActivity(new Intent(this, RefundSuccessActivity.class));
//                finish();
                break;
        }
    }

    /**
     * 申请退款
     */
    private void getRefund(String reason) {
        Api.getInstance().getDefault().getRefundDeposit(Config.TOKEN, reason)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<RefunddepositBean>>(new SubscriberListener<HttpResult<RefunddepositBean>>() {


                    @Override
                    public void onSuccess(HttpResult<RefunddepositBean> model) {
                        if (model.isOk()) {
                            SharedPreferences sp_real = getSharedPreferences("is_real_recharge", MODE_PRIVATE);//判断是否充值过押金.是否实名
                            SharedPreferences.Editor editor_real = sp_real.edit();
                            editor_real.putBoolean("is_recharge", false);
                            editor_real.commit();
                            // Logger.e(model.getData().getDepositCount() + "我的押金退回了");
                            Intent intent = new Intent(ApplyRefundActivity.this, RefundSuccessActivity.class);
                            intent.putExtra("deposit", model.getData().getDepositCount());
                            startActivity(intent);
                            finish();
                        } else if (EmptyUtils.isNotEmpty(model.getMsg())) {
                            ToastUtils.showShort(model.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, true));
    }
}
