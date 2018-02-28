package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.AboutMe;
import com.kb260.gxdk.model.Version;
import com.kb260.gxdk.payutil.IPayBean;
import com.kb260.gxdk.payutil.PayFactory;
import com.kb260.gxdk.utils.AppUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2017/11/14
 */
public class AboutMeActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_aboutMe_tvPhone)
    TextView tvPhone;
    @BindView(R.id.a_aboutMe_tvQQ)
    TextView tvQQ;
    @BindView(R.id.a_aboutMe_tvWx)
    TextView tvWx;
    @BindView(R.id.a_set_version)
    TextView tvVersion;


    public static void start(Context context){
        Intent intent = new Intent(context,AboutMeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_me;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        String version = "V" + AppUtils.getAppVersionName();
        tvVersion.setText(version);
        initData();
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_aboutMe_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    private void initData() {
        Api.getDefault().selaboutus(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<AboutMe>(this) {
                    @Override
                    protected void success(AboutMe list) {
                        if (list != null){
                            tvPhone.setText(list.getLinktel());
                            tvQQ.setText(list.getQq());
                            tvWx.setText(list.getWeixin()
                            );
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    public void a(){
        PayFactory.createPay(PayFactory.ALPAY, this, "")
                .setOnResultListener(new IPayBean.OnResultListener() {
                    @Override
                    public void onPaySuccess() {

                    }

                    @Override
                    public void onPayFail() {

                    }
                });

        PayFactory.createPay(PayFactory.WXPAY, this, "");
    }

    //关于
    @OnClick(R.id.a_set_about)
    public void about(){
        AboutAppActivity.start(this);
    }

    //版本
    @OnClick(R.id.a_set_llVersion)
    public void version(){
        initData1();
    }

    private void initData1() {
        Api.getDefault().selversion(KBApplication.token,KBApplication.userid,"android")
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<Version>(this) {
                    @Override
                    protected void success(Version list) {
                        if (list != null){
                            ToastUtil.showInfo("最新版本为："+ list.getCurrentversion());
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }
}
