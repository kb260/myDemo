package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.AliPay;
import com.kb260.gxdk.entity.ChargeScore;
import com.kb260.gxdk.payutil.IPayBean;
import com.kb260.gxdk.payutil.PayFactory;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.base.WebViewDataActivity;
import com.kb260.gxdk.view.widget.pay.PayDialog;
import com.orhanobut.logger.Logger;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 * Created on  2017/11/9
 */
public class PayIntegralActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.a_payIntegral_tvIntegral)
    TextView tvIntegral;
    @BindView(R.id.a_payIntegral_tvMoney)
    TextView tvMoney;
    @BindView(R.id.a_payIntegral_tvSyIntegral)
    TextView tvSyIntegral;

    @BindView(R.id.a_payIntegral_rg)
    RadioGroup rg;

    PayDialog payDialog;

    double score,money,syInteger;
    int id;
    String password;

    public static void start(Context context, String score,int id) {
        Intent intent = new Intent(context, PayIntegralActivity.class);
        intent.putExtra(Contact.SCORE, score);
        intent.putExtra(Contact.ID, id);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_integral;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initIntent() {
        payDialog = new PayDialog(this, str -> {
            password = str;
            payDialog.clear();
            scorePay();
        });

        Intent intent = getIntent();
        String a = intent.getStringExtra(Contact.SCORE);
        id = intent.getIntExtra(Contact.ID,-1);
        score = Double.valueOf(a);
        money = score/100;

        String b = a+"积分";
        tvIntegral.setText(b);
        tvMoney.setText(String.valueOf(money));
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText("支付");
        initThisToolbarHaveBack(toolbar, this);
    }

    //支付
    @OnClick(R.id.a_payIntegral_btPay)
    public void pay() {
        switch (rg.getCheckedRadioButtonId()){
            case R.id.a_payIntegral_rb1://积分支付
                selPayWord();
                break;
            case R.id.a_payIntegral_rb2://支付宝
                aliPay();
                break;
            case R.id.a_payIntegral_rb3://银行
                cash();
                break;
            default:
                ToastUtil.showInfo("请选择支付方式！");
                break;
        }
    }

    /**
     * 积分支付
     */
    private void scorePay() {
        Api.getDefault().scorepay(KBApplication.token,KBApplication.userid,id,score,password)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        if (payDialog != null && payDialog.isShowing()){
                            payDialog.cancel();
                            payDialog.clear();
                        }
                        ToastUtil.showSuccess("支付成功！");
                        finish();
                    }

                    @Override
                    protected void error(String msg) {
                        if (payDialog != null && payDialog.isShowing()){
                            payDialog.cancel();
                        }
                        Logger.d(msg);
                        switch (msg) {
                            case "你的余额不足，请先充值":
                                ToastUtil.showInfo("你的余额不足，请先充值！");
                                RechargeActivity.start(PayIntegralActivity.this,score-syInteger);
                                break;
                            default:
                                ToastUtil.showError(msg);
                                break;
                        }
                    }
                });
    }

    /**
     * 查询设置支付密码
     */
    private void selPayWord() {
        Api.getDefault().selpayword(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        switch (list) {
                            case "已设置支付密码":
                                if (payDialog != null && !payDialog.isShowing()){
                                    payDialog.show();
                                }
                                break;
                            case "未设置支付密码":
                                ToastUtil.showInfo("请设置支付密码！");
                                SetNewPayPasswordActivity.start(PayIntegralActivity.this);
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void initData(){
        Api.getDefault().shenscore(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<ChargeScore>(this) {
                    @Override
                    protected void success(ChargeScore list) {
                        syInteger = list.getChargescore();
                        String a = syInteger+"积分";
                        tvSyIntegral.setText(a);
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    /**
     * 支付宝支付
     */
    private void aliPay(){
        Api.getDefault().ali(KBApplication.token,KBApplication.userid,money,"1",String.valueOf(id))
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<AliPay>(this) {
                    @Override
                    protected void success(AliPay list) {
                        ali(list);
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void ali(AliPay list){
        PayFactory.createPay(PayFactory.ALPAY, this, list.getBody())
                .setOnResultListener(new IPayBean.OnResultListener() {
                    @Override
                    public void onPaySuccess() {
                        ToastUtil.showSuccess("支付成功！");
                        finish();
                    }

                    @Override
                    public void onPayFail() {
                        ToastUtil.showError("支付失败！");
                    }
                });
    }

    /**
     * 银行卡支付
     */
    private void cash(){
        Api.getDefault().cash(KBApplication.token,KBApplication.userid,money,"1",String.valueOf(id))
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        WebViewDataActivity.start(PayIntegralActivity.this,list,"银行卡支付");
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

}
