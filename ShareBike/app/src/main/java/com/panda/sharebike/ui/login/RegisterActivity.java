package com.panda.sharebike.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.model.entity.RegisteredBean;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.selfinfo.UserProtocolActivity;
import com.panda.sharebike.ui.widget.ClearEditText;
import com.panda.sharebike.ui.widget.CountDownTV;
import com.panda.sharebike.util.RegexUtil;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_code)
    ClearEditText etCode;
    @BindView(R.id.tv_get_code)
    CountDownTV tvGetCode;
    @BindView(R.id.et_password)
    ClearEditText etPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

    }

    private void initView() {

        textChange textChange = new textChange();
        etPhone.addTextChangedListener(textChange);
        etCode.addTextChangedListener(textChange);
        etPassword.addTextChangedListener(textChange);
        //
        tvGetCode.setListener(new CountDownTV.CountListener() {
            @Override
            public void onFinishListener() {

            }
        });
    }

    private void initGetButton() {
        if (TextUtils.isEmpty(etPhone.getText().toString()) || !RegexUtils.isMobileSimple(etPhone.getText().toString())) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }
        if (TextUtils.isEmpty(etCode.getText().toString())) {
            ToastUtils.showShort("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString()) || !RegexUtil.isStringNumTen(etPassword.getText().toString())) {
            ToastUtils.showShort("请输入正确的密码");
            return;
        }
        String RegistrationId = SPUtils.getInstance("RegistrationId").getString("RegistrationId");
        if (!RegistrationId.equals("null")) {
            getRegMsg(etPhone.getText().toString(), etPassword.getText().toString(), etCode.getText().toString(), RegistrationId);
        }

    }

    private void initGetCode() {
        //执行验证码
        if (TextUtils.isEmpty(etPhone.getText().toString()) || !RegexUtils.isMobileSimple(etPhone.getText().toString())) {
            ToastUtils.showShort("请输入正确的手机号码");
        } else {

//            tvGetCode.start(60);
//            tvGetCode.setEnabled(false);
            getHttpRegcode(etPhone.getText().toString());
        }

    }

    @Override
    protected void setUpView() {
        super.setUpView();
        setTvRight("登录", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RegisterActivity.this, LoginByPhoneActivity.class));
            }
        });
    }

    @OnClick({R.id.tv_get_code, R.id.btn_register, R.id.tv_user_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                initGetCode();
                break;
            case R.id.btn_register:
                initGetButton();
                break;
            case R.id.tv_user_protocol:
                startActivity(new Intent(this, UserProtocolActivity.class));
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getHttpRegcode(String phone) {
        Api.getInstance().getDefault().getRegCode(Config.TOKEN, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult>(new SubscriberListener<HttpResult>() {
                    @Override
                    public void onSuccess(HttpResult model) {

                        if (501 == model.getCode()) {
                            ToastUtils.showShort(model.getMsg());
                            return;
                        }
                        if (406 == model.getCode()) {
                            ToastUtils.showShort(model.getMsg());
                            return;
                        }
                        if (model.isOk()) {
                            tvGetCode.start(60);
                            tvGetCode.setEnabled(false);
                            ToastUtils.showShort("验证码已发送");
                        } else {
                            if (!EmptyUtils.isEmpty(model.getMsg())) {
                                ToastUtils.showShort(model.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtils.showShort(msg);

                    }
                }, this, false));
    }

    /**
     * 注册
     */
    private void getRegMsg(String phone, String password, String code, String deviceld) {
        Api.getInstance().getDefault().getRegInfo(Config.TOKEN, phone, password, code, deviceld)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<RegisteredBean>>(new SubscriberListener<HttpResult<RegisteredBean>>() {
                    @Override
                    public void onSuccess(HttpResult<RegisteredBean> model) {
                        if (model.getCode() == 200) {
                            startActivity(new Intent(RegisterActivity.this, CertificationActivity.class));
                            RegisterActivity.this.finish();
                        } else {
                            ToastUtils.showShort(model.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }, this, true));
    }

    //EditText监听器
    class textChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            boolean Sign1 = etPhone.getText().length() > 10;
            boolean Sign2 = etCode.getText().length() > 3;
            boolean Sign3 = etPassword.getText().length() > 6;
            if (Sign1) {
                tvGetCode.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_count_tv_enable));
                tvGetCode.setEnabled(true);
            } else {
                tvGetCode.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_count_tv_un_enable));
                tvGetCode.setEnabled(false);
            }
            if (Sign1 & Sign2 & Sign3) {
                btnRegister.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_btn_enable));
                btnRegister.setEnabled(true);
            } else {
                btnRegister.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_btn_unenable));
                btnRegister.setEnabled(false);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
        }
    }

