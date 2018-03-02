package com.panda.sharebike.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.model.entity.LoginBean;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.MainActivity;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.selfinfo.UserProtocolActivity;
import com.panda.sharebike.ui.widget.ClearEditText;
import com.panda.sharebike.util.RegexUtil;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_password)
    ClearEditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_login_phone)
    TextView tvLoginPhone;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        textChange textChange = new textChange();
        etPhone.addTextChangedListener(textChange);
        etPassword.addTextChangedListener(textChange);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        setTvRight("注册", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });
    }

    @OnClick({R.id.btn_login, R.id.tv_login_phone, R.id.tv_forget_password, R.id.tv_user_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                initGetButton();
                break;
            case R.id.tv_login_phone:
                startActivity(new Intent(this, LoginByPhoneActivity.class));
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
            case R.id.tv_user_protocol:
                startActivity(new Intent(this, UserProtocolActivity.class));
                break;
        }
    }

    private void initGetButton() {
        if (TextUtils.isEmpty(etPhone.getText().toString()) || !RegexUtils.isMobileSimple(etPhone.getText().toString())) {
            ToastUtils.showShort("请输入正确的手机号码");
            return;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString()) || !RegexUtil.isStringNumTen(etPassword.getText().toString())) {
            ToastUtils.showShort("请输入正确的密码");
            return;
        }
        String RegistrationId = SPUtils.getInstance("RegistrationId").getString("RegistrationId");
        if (!RegistrationId.equals("null")) {
            getRegMsg(etPhone.getText().toString(), etPassword.getText().toString(), RegistrationId);
        }
    }

    /**
     * 立即登录
     */
    private void getRegMsg(String phone, String password, String deviceld) {
        LogUtils.e(Config.TOKEN);
        Api.getInstance().getDefault().getLoginInfo(Config.TOKEN, phone, password, deviceld)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<LoginBean>>(new SubscriberListener<HttpResult<LoginBean>>() {
                    @Override
                    public void onSuccess(HttpResult<LoginBean> model) {

                        if (501 == model.getCode()) {
                            ToastUtils.showShort(model.getMsg());
                            return;
                        }
                        if (406 == model.getCode()) {
                            ToastUtils.showShort(model.getMsg());
                            return;
                        }
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        LoginActivity.this.finish();
                        //创建2个sp，用于存储值，判断是否实名，是否认证
//                        SharedPreferences sp_real = getSharedPreferences("is_real_recharge", MODE_PRIVATE);//判断是否充值过押金.是否实名
//                        SharedPreferences.Editor editor_real = sp_real.edit();
//                        editor_real.putBoolean("is_real", model.getData().isRealnameAuth());//
//                        editor_real.putBoolean("is_recharge", model.getData().isHasPayedDeposit());
//                        editor_real.putBoolean("is_login", true);
//                        editor_real.commit();
                        //    boolean first = sp.getBoolean("first_login", true);
//                        boolean first = SPUtils.getInstance("is_login").getBoolean("first_login", true);
//                        LogUtils.e(first);
//                        if (first) {
//                            if (model.getCode() == 200) {//嵌套有点多
//                                setUserInfo(model.getData().getSession().getStatus());
//                                if (model.getData().isRealnameAuth()) {
//                                    if (!model.getData().isHasPayedDeposit()) {
//                                        startActivity(RechargeActivity.class);
//                                    } else {
//                                        startActivity(MainActivity.class);
//                                    }
//                                } else {
//                                    startActivity(CertificationActivity.class);
//                                }
//                            }
//                        } else {
                        //    }
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
            boolean Sign3 = etPassword.getText().length() > 6;
            if (Sign1 & Sign3) {
                btnLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_btn_enable));
                btnLogin.setEnabled(true);
            } else {
                btnLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_btn_unenable));
                btnLogin.setEnabled(false);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    @Override
    public void onStop() {
        super.onStop();
//        SharedPreferences sp = getSharedPreferences("is_login", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putBoolean("first_login", false);//debug模式下传true进入引导页，上线前记得修改
//        editor.commit();
    }

    /**
     * 在登录页面LoginActivity里的登录成功回调方法里进行全局登录凭证的赋值和本地持久化
     */
    private void setUserInfo(String status) {
        //Config.USER_ID = obj.getString("memberId");
        SPUtils.getInstance("USER_INFO").put("USER_ID", status);

    }
}
