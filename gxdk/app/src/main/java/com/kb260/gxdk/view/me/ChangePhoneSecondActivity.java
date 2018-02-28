package com.kb260.gxdk.view.me;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.CountDownTimerUtils;
import com.kb260.gxdk.utils.RegexUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

/**
 * @author KB260
 */
public class ChangePhoneSecondActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_changePhone_etNewPhone)
    EditText etNewPhone;
    @BindView(R.id.a_changePhone_etNewCode)
    EditText etCode;
    @BindView(R.id.a_changePhone_btNewGetCode)
    Button btCode;

    String rightCode;
    String phone, code;

    public static void start(Activity context) {
        Intent intent = new Intent(context, ChangePhoneSecondActivity.class);
        context.startActivityForResult(intent, 1);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_phone_second;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_meDetail_changePhone_toolbar);
        initThisToolbarHaveBack(toolbar, this);
    }


    //获取验证码
    @OnClick(R.id.a_changePhone_btNewGetCode)
    public void getCode() {
        if (getInputPhone()) {
            return;
        }
        Api.getDefault().registrationcode(phone)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showInfo("验证码已发送！");
                        rightCode = list;
                        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(btCode, Contact.CODE_TIME, 1000);
                        countDownTimerUtils.start();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });

    }

    //保存
    @OnClick(R.id.a_changePhone_btSave)
    public void save() {
        if (getInput()) {
            return;
        }
        Api.getDefault().upperon(KBApplication.token,KBApplication.userid, null, null, phone)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("修改成功!");
                        KBApplication.currentPhone = phone;
                        setResult(24);
                        finish();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    public boolean getInput() {
        phone = etNewPhone.getText().toString().trim();
        if (StringUtils.isEmpty(phone)) {
            ToastUtil.showInfo("请输入手机号！");
            return true;
        }
        if (RegexUtils.isMobileSimple(phone)){
            ToastUtil.showInfo("请输入正确的手机号码！");
            return true;
        }
        code = etCode.getText().toString().trim();
        if (StringUtils.isEmpty(code)) {
            ToastUtil.showInfo("请输入验证码！");
            return true;
        }
        if (StringUtils.isEmpty(rightCode)){
            ToastUtil.showInfo("请获取验证码！");
            return true;
        }
        if (!code.equals(rightCode)){
            ToastUtil.showInfo("请输入正确验证码！");
            return true;
        }
        return false;
    }

    private boolean getInputPhone(){
        phone = etNewPhone.getText().toString().trim();
        if (StringUtils.isEmpty(phone)) {
            ToastUtil.showInfo("请输入手机号！");
            return true;
        }
        if (RegexUtils.isMobileSimple(phone)){
            ToastUtil.showInfo("请输入正确的手机号码！");
            return true;
        }
        return false;
    }

}
