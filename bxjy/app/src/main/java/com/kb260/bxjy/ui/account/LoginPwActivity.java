package com.kb260.bxjy.ui.account;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import com.kb260.bxjy.R;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.entity.UserData;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.ui.mian.MainActivity;
import com.kb260.bxjy.utils.StringUtils;
import com.kb260.bxjy.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/5
 */
public class LoginPwActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_loginPw_etPhone)
    EditText etPhone;
    @BindView(R.id.a_loginPw_etPw)
    EditText etPw;

    String phone,pw;

    /**
     *开启页面
     */
    public static void start(Context context){
        Intent intent = new Intent(context,LoginPwActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_pw;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        etPhone.setText("14727002600");
        etPw.setText("123456");
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.account_toolbar_login);
        initThisToolbarHaveBack(toolbar,this);
    }

    @OnClick(R.id.a_loginPw_forgetPw)
    public void forgetPw(){
        ChangePwFirstActivity.start(this);
    }

    @OnClick(R.id.a_loginPw_login)
    public void login(){
        if (checkInput()){
            Api.getDefault().loginCheckPassword(KbApplication.token,phone, pw)
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<UserData>(this) {
                        @Override
                        protected void success(UserData list) {
                            KbApplication.token = list.getToken();
                            ToastUtil.showShout("登录成功！");
                            MainActivity.start(LoginPwActivity.this);
                        }

                        @Override
                        protected void error(String msg) {
                            Logger.d(msg);
                            ToastUtil.showShout(msg);
                        }

                    });
        }
    }

    @OnClick(R.id.a_loginPw_register)
    public void register(){
        RegisterFirstActivity.start(this);
    }

    @OnClick(R.id.a_loginPw_loginByCode)
    public void loginByCode(){
        LoginCodeActivity.start(this);
        finish();
    }

    private boolean checkInput(){
        phone = etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)){
            ToastUtil.showShout("请输入手机号");
            return false;
        }

        pw = etPw.getText().toString();
        if (StringUtils.isEmpty(pw)){
            ToastUtil.showShout("请输入密码");
            return false;
        }

        return true;
    }

}
