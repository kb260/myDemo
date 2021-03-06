package com.panda.sharebike.ui.selfinfo;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.widget.ClearEditText;
import com.panda.sharebike.ui.widget.CountDownTV;
import com.panda.sharebike.util.RegexUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 修改密码
 */
public class UpdatePasswordActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_code)
    ClearEditText etCode;
    @BindView(R.id.tv_get_code)
    CountDownTV tvGetCode;
    @BindView(R.id.et_password)
    ClearEditText etPassword;
    @BindView(R.id.btn_update)
    Button btnUpdate;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_update_password;
    }

    @OnClick({R.id.tv_get_code, R.id.btn_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                initGetCode();
                break;
            case R.id.btn_update:
                initGetButton();
                break;
        }
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
        getRegMsg(etPhone.getText().toString(), etCode.getText().toString(), etPassword.getText().toString());


    }

    private void initGetCode() {
        //执行验证码
        if (TextUtils.isEmpty(etPhone.getText().toString()) || !RegexUtils.isMobileSimple(etPhone.getText().toString())) {
            ToastUtils.showShort("请输入正确的手机号码");
        } else {
            tvGetCode.setEnabled(false);
            getHttpRegcode(etPhone.getText().toString());
        }

    }

    /**
     * 获取验证码
     */
    private void getHttpRegcode(String phone) {
        Api.getInstance().getDefault().getEditCode(Config.TOKEN, phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult>(new SubscriberListener<HttpResult>() {
                    @Override
                    public void onSuccess(HttpResult model) {

                        if (401 == model.getCode()) {
                            ToastUtils.showShort("请先登录");
                        }
                        if (406 == model.getCode()) {
                            ToastUtils.showShort(model.getMsg());
                            tvGetCode.setEnabled(true);
                        }
                        if (model.isOk()) {
                            tvGetCode.start(60);
                            ToastUtils.showShort("验证码已发送");
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
    private void getRegMsg(String phone, String code, String newPwd) {
        Api.getInstance().getDefault().getEdit(Config.TOKEN, phone, code, newPwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult>(new SubscriberListener<HttpResult>() {
                    @Override
                    public void onSuccess(HttpResult model) {
                        if (model.getCode() == 200) {
                            Intent intent = new Intent(UpdatePasswordActivity.this, PersonalActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            UpdatePasswordActivity.this.finish();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
                btnUpdate.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_btn_enable));
                btnUpdate.setEnabled(true);
            } else {
                btnUpdate.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_btn_unenable));
                btnUpdate.setEnabled(false);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
