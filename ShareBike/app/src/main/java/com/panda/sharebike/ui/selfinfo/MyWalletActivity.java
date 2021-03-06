package com.panda.sharebike.ui.selfinfo;

import android.content.Intent;
import android.view.View;
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
import com.panda.sharebike.model.entity.LoginDataBean;
import com.panda.sharebike.model.entity.MemberwalletBean;
import com.panda.sharebike.model.entity.RechargeBean;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.login.CertificationActivity;
import com.panda.sharebike.ui.login.LoginByPhoneActivity;
import com.panda.sharebike.ui.login.RechargeMoneyActivity;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 我的钱包
 */
public class MyWalletActivity extends BaseActivity {
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_deposit)
    TextView tvDeposit;
    @BindView(R.id.rl_deposit)
    RelativeLayout rlvDeposit;
    private double money;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_my_wallet;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        getMemberWallet();
        getStatusFapuit1();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getMemberWallet();
        getStatusFapuit1();
    }

    @OnClick({R.id.btn_recharge, R.id.rl_deposit, R.id.rl_balance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_recharge:
                //  startActivity(new Intent(this, RechargeMoneyActivity.class));
                getStatusFapuit();
                break;
            case R.id.rl_deposit:
                Intent intent = new Intent(this, ApplyRefundActivity.class);
                intent.putExtra("money", money);
                startActivity(intent);
                // finish();
                break;
            case R.id.rl_balance:
                startActivity(new Intent(this, BalanceDetailActivity.class));
                break;
        }
    }

    /**
     * 个人钱包页
     */
    private void getMemberWallet() {
        Api.getInstance().getDefault().getMemberWallet(Config.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<MemberwalletBean>>(new SubscriberListener<HttpResult<MemberwalletBean>>() {
                    @Override
                    public void onSuccess(HttpResult<MemberwalletBean> model) {
                        if (401 == model.getCode()) {
                            startActivity(LoginByPhoneActivity.class);
                            return;
                        }
                        Logger.e(model.getData().getDepositConfig() + "");
                        if (model.isOk()) {
                            money = model.getData().getMemberDeposit();
                            tvBalance.setText(model.getData().getMemberBeenz() + "");
                            tvDeposit.setText(model.getData().getMemberDeposit() + "元, 退押金");
                        } else if (EmptyUtils.isEmpty(model.getMsg())) {
                            ToastUtils.showShort(model.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, true));
    }

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
                            if (!model.getData().isRealnameAuth()) {
                                startActivity(CertificationActivity.class);
                            } else {
                                startActivity(new Intent(MyWalletActivity.this, RechargeMoneyActivity.class));
                            }

                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        //  ToastUtils.showShort(msg);
                    }
                }, this, false));

    }

    private void getStatusFapuit1() {

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
                        //充值押金
                        if (!model.getData().isHasPayedDeposit()) {
                            rlvDeposit.setVisibility(View.VISIBLE);
                        }else {
                            rlvDeposit.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        //  ToastUtils.showShort(msg);
                    }
                }, this, false));

    }
    /**
     * 充值
     */
    private void getRecharge(double money) {
        Api.getInstance().getDefault().getRecharge(Config.TOKEN, money)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<RechargeBean>>(new SubscriberListener<HttpResult<RechargeBean>>() {
                    @Override
                    public void onSuccess(HttpResult<RechargeBean> model) {
                        if (model.isOk()) {

                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, true));
    }
}
