package com.kb260.gxdk.view.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.LoginSuccess;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.RegexUtils;
import com.kb260.gxdk.utils.SPUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.main.MainActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * @author  KB260
 * Created on  2017/12/13
 */
public class ThreeLoginActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_threeLogin_etPhone)
    EditText etPhone;
    @BindView(R.id.a_threeLogin_etPassword)
    EditText etPassword;

    String username,password,uid,qqid;

    public static void start(Context context,String uid,String qqId){
        Intent intent = new Intent(context,ThreeLoginActivity.class);
        intent.putExtra(Action.ID,uid);
        intent.putExtra(Action.QQID,qqId);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_three_login;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        uid = getIntent().getStringExtra(Action.ID);
        qqid = getIntent().getStringExtra(Action.QQID);
    }

    //登录
    @OnClick(R.id.a_threeLogin_btLogin)
    public void login(){
        if (!getInput()){
            if (KBApplication.registrationId == null){
                KBApplication.registrationId = JPushInterface.getRegistrationID(this);
            }
            Api.getDefault().applogin(username,password,KBApplication.registrationId,uid,qqid)
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<LoginSuccess>(this) {
                        @Override
                        protected void success(LoginSuccess list) {
                            KBApplication.userid = list.getId();
                            KBApplication.setLoginType(list.getType());
                            KBApplication.currentPhone = list.getUsername();
                            KBApplication.token = list.getToken();

                            SPUtils.getInstance().put(Action.SP_USERNAME,username);
                            SPUtils.getInstance().put(Action.SP_PASSWORD,password);
                            MainActivity.start(ThreeLoginActivity.this);
                        }

                        @Override
                        protected void error(String msg) {
                            Logger.d(msg);
                            ToastUtil.showError(msg);
                            if (msg.equals("用户不存在")){
                                RegisterActivity.start(ThreeLoginActivity.this);
                            }
                        }
                    });
        }
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText("完善信息");
        initThisToolbarHaveBack(toolbar,this);
    }

    private boolean getInput(){
        username = etPhone.getText().toString().trim();
        if (StringUtils.isEmpty(username)){
            ToastUtil.showInfo("请输入手机号码！");
            return true;
        }

        if (RegexUtils.isMobileSimple(username)){
            ToastUtil.showInfo("请输入正确的手机号码！");
            return true;
        }

        password = etPassword.getText().toString().trim();
        if (StringUtils.isEmpty(password)){
            ToastUtil.showInfo("请输入密码！");
            return true;
        }
        return false;
    }
}
