package com.kb260.gxdk.view.me;

import android.app.Activity;
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
import com.kb260.gxdk.payutil.IPayBean;
import com.kb260.gxdk.payutil.PayFactory;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.base.WebViewDataActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2017/11/9
 */
public class RechargePayActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_rechargePay_tvIntegral)
    TextView tvIntegral;
    @BindView(R.id.a_rechargePay_tvMoney)
    TextView tvMoney;
    @BindView(R.id.a_rechargePay_rg)
    RadioGroup group;


    String type,id;
    double money;

    public static void start(Activity context,Double money,String type,String id){
        Intent intent = new Intent(context,RechargePayActivity.class);
        intent.putExtra(Contact.INTEGRAL,money);
        intent.putExtra(Contact.TYPE,type);
        intent.putExtra(Contact.ID,id);
        context.startActivityForResult(intent,24);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge_pay;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        money = intent.getDoubleExtra(Contact.INTEGRAL,0);
        type = intent.getStringExtra(Contact.TYPE);
        id = intent.getStringExtra(Contact.ID);
        if (money>0){
            tvMoney.setText(String.valueOf(money));
            tvIntegral.setText(String.valueOf(money*100));
        }
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_rechargePay_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    @OnClick(R.id.a_rechargePay_ll1)
    public void click1(){
        group.check(R.id.a_rechargePay_rb1);
    }

    @OnClick(R.id.a_rechargePay_ll2)
    public void click2(){
        group.check(R.id.a_rechargePay_rb2);
    }

    @OnClick(R.id.a_rechargePay_ll3)
    public void click3(){
        group.check(R.id.a_rechargePay_rb3);
    }


    @OnClick(R.id.a_rechargePay_btConfirm)
    public void ok(){
        switch (group.getCheckedRadioButtonId()){
            case R.id.a_rechargePay_rb1:
                aliPay();
                break;
            case R.id.a_rechargePay_rb2:
                break;
            case R.id.a_rechargePay_rb3:
                cash();
                break;
            default:
                ToastUtil.showInfo("请选择支付方式！");
                break;
        }
    }

    /**
     * 支付宝支付
     */
    private void aliPay(){
        Api.getDefault().ali(KBApplication.token,KBApplication.userid,money,type,id)
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
                        setResult(24);
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
        String type = "1";
        if (id == null){
            type = "0";
        }
        Api.getDefault().cash(KBApplication.token,KBApplication.userid,money,type,id)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        WebViewDataActivity.start(RechargePayActivity.this,list,"银行卡支付");
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }
}
