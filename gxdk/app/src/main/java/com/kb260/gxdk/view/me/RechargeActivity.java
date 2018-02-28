package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.ChargeScore;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.ImageLoader;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.widget.CashierInputFilter;
import com.kb260.gxdk.view.widget.XEditText;
import com.orhanobut.logger.Logger;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author  KB260
 * Created on  2017/11/9
 */
public class RechargeActivity extends BaseActivity {
    @BindView(R.id.a_recharge_toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_recharge_integral)
    TextView tvIntegral;
    @BindView(R.id.a_recharge_etMoney)
    EditText etMoney;
    @BindView(R.id.a_recharge_ivUser)
    CircleImageView ivUser;
    String integral;

    public static void start(Context context){
        Intent intent = new Intent(context,RechargeActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context,double a){
        Intent intent = new Intent(context,RechargeActivity.class);
        intent.putExtra(Action.MONEY,a);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initData();
        /**
         * 第二种方法
         */
        etMoney.setFilters(new InputFilter[]{new CashierInputFilter()});
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edt) {
                if (!StringUtils.isEmpty(edt.toString())){
                    double b = Double.valueOf(edt.toString());
                    if (b<=20000){
                        double a = b * 100;
                        tvIntegral.setText(String.valueOf(a));
                    }else {
                        ToastUtil.showInfo("最多充值2000000积分！");
                    }
                }else {
                    tvIntegral.setText("0.0");
                }
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
        });

        double a = getIntent().getDoubleExtra(Action.MONEY,0);
        if (a != 0 ){
            double b = a/100;
            //double b = a * 100;
            if (b<=20000){
                etMoney.setText(String.valueOf(b));
            }else {
                etMoney.setText("20000");
                ToastUtil.showInfoLong("单笔交易最高充值￥20000！");
            }
            //tvIntegral.setText(String.valueOf(b));
        }
    }


    @Override
    public void initToolbar() {
        initThisToolbarHaveBack(toolbar,this);
    }

    //历史记录
    @OnClick(R.id.a_recharge_toolbarRight)
    public void log(){
        IntegralLogActivity.start(this);
    }

    //确认
    @OnClick(R.id.a_recharge_btConfirm)
    public void confirm(){
        integral = etMoney.getText().toString();
        if (StringUtils.isEmpty(integral)){
            ToastUtil.showInfo("请输入充值金额！");
            return;
        }
        double a = Double.valueOf(integral);
        if (a<=20000){
            RechargePayActivity.start(this,a,"0",null);
        }else {
            ToastUtil.showInfo("最多充值2000000积分！");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 24){
            finish();
        }else if (resultCode == 23){
            initData();
            etMoney.setText("");
        }
    }

    private void initData(){
        Api.getDefault().shenscore(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<ChargeScore>(this) {
                    @Override
                    protected void success(ChargeScore list) {
                        //tvIntegral.setText(list.getChargescore());
                        ImageLoader.showImage(ivUser,list.getPictures());
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }
}
