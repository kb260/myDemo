package com.kb260.bxjy.ui.account;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.kb260.bxjy.R;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.entity.CodeData;
import com.kb260.bxjy.model.entity.UserData;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.ui.mian.MainActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.Contact;
import com.kb260.bxjy.utils.CountDownTimerUtils;
import com.kb260.bxjy.utils.KeyBordUtil;
import com.kb260.bxjy.utils.StringUtils;
import com.kb260.bxjy.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/5
 */
public class LoginCodeActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_loginCode_etPhone)
    EditText etPhone;
    @BindView(R.id.a_loginCode_etCode)
    EditText etCode;
    @BindView(R.id.a_loginCode_getCode)
    TextView tvGetCode;

    CountDownTimerUtils countDownTimerUtils;

    String phone,code;

    /**
     *开启页面
     */
    public static void start(Context context){
        Intent intent = new Intent(context,LoginCodeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login_code;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        countDownTimerUtils = new CountDownTimerUtils(tvGetCode, Contact.CODE_TIME, 1000,R.color.price_red);
        etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == Action.CODE_LENGTH){
                    KeyBordUtil.hitKeyBord(etCode,LoginCodeActivity.this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.account_toolbar_login);
        initThisToolbarHaveBack(toolbar,this);
    }

    @OnClick(R.id.a_loginCode_getCode)
    public void getCode(){
        if (checkInputPhone()){
            Api.getDefault().sendCode(phone, Action.GET_CODE_TYPE_LOGIN)
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<CodeData>(this) {
                        @Override
                        protected void success(CodeData list) {
                            ToastUtil.showShout("验证码已发送！");
                            code = list.getCode();
                            countDownTimerUtils.start();
                            KeyBordUtil.showKB(etCode,LoginCodeActivity.this);
                        }

                        @Override
                        protected void error(String msg) {
                            Logger.d(msg);
                            ToastUtil.showShout(msg);
                        }
                    });
        }
    }


    @OnClick(R.id.a_loginCode_loginByPw)
    public void loginByPw(){
        LoginPwActivity.start(this);
        finish();
    }

    @OnClick(R.id.a_loginCode_login)
    public void login(){
        if (checkInput()){
            Api.getDefault().loginCheckCode(KbApplication.token,phone, code)
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<UserData>(this) {
                        @Override
                        protected void success(UserData list) {
                            ToastUtil.showShout("登录成功！");
                            MainActivity.start(LoginCodeActivity.this);
                        }

                        @Override
                        protected void error(String msg) {
                            Logger.d(msg);
                            ToastUtil.showShout(msg);
                        }

                    });
        }
    }

    @OnClick(R.id.a_loginCode_register)
    public void register(){
        RegisterFirstActivity.start(this);
    }

    private boolean checkInputPhone(){
        phone = etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)){
            ToastUtil.showShout("请输入手机号");
            return false;
        }
        return true;
    }

    private boolean checkInput(){
        phone = etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)){
            ToastUtil.showShout("请输入手机号");
            return false;
        }

        code = etCode.getText().toString();
        if (StringUtils.isEmpty(code)){
            ToastUtil.showShout("请输入验证码");
            return false;
        }
        return true;
    }
}
