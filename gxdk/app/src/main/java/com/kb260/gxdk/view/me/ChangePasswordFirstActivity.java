package com.kb260.gxdk.view.me;

import android.content.Context;
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
import com.kb260.gxdk.utils.Action;
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
 * @author  KB260
 */
public class ChangePasswordFirstActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_me_meDetail_payPassword_change_btGetCode)
    Button btCode;
    @BindView(R.id.a_me_meDetail_payPassword_change_phone)
    EditText etPhone;
    @BindView(R.id.a_me_meDetail_payPassword_change_etCode)
    EditText etCode;

    String phone,code,rightCode;

    int type;

    public static void start(Context context,int type){
        Intent intent = new Intent(context,ChangePasswordFirstActivity.class);
        intent.putExtra(Action.PASSWORD_TYPE,type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_password_first;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    //获取验证码
    @OnClick(R.id.a_me_meDetail_payPassword_change_btGetCode)
    public void getCode(){
        if (getInputPhone()){
            return;
        }
        Api.getDefault().forgetcode(phone)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                    }

                    @Override
                    protected void success(String list) {
                        rightCode = list;
                        ToastUtil.showInfo("验证码已发送！");
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

    private boolean getInputPhone(){
        phone = etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            ToastUtil.showInfo("请输入手机号！");
            return true;
        }
        if (KBApplication.currentPhone != null){
            if (!phone.equals(KBApplication.currentPhone)){
                ToastUtil.showInfo("请输入正确的手机号码！");
                return true;
            }
        }
        return false;
    }

    @Override
    public void initToolbar() {
        type = getIntent().getIntExtra(Action.PASSWORD_TYPE,0);
        toolbarTitle.setText(R.string.verify_code);
        /*switch (type){
            case Action.PASSWORD_CHANGE:
                toolbarTitle.setText(R.string.a_me_meDetail_payPassword_change);
                break;
            case Action.PASSWORD_FORGET:
                toolbarTitle.setText(R.string.a_me_meDetail_payPassword_forget_toolbar);
                break;
            case Action.LOGIN_PASSWORD_FORGET:
                toolbarTitle.setText(R.string.a_me_meDetail_passwordSet_forget);
                break;
            default:
                break;
        }*/
        initThisToolbarHaveBack(toolbar,this);
    }

    //下一步
    @OnClick(R.id.a_me_meDetail_payPassword_change_btNext)
    public void next(){
        if (!getInput()){
            switch (type){
                case Action.LOGIN_PASSWORD_FORGET:
                    ChangeLoginPasswordActivity.start(this, Action.PASSWORD_FORGET,phone);
                    break;
                default:
                    ChangePasswordSecondActivity.start(this,type);
                    break;
            }
        }
    }

    public boolean getInput(){
        phone = etPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            ToastUtil.showInfo("请输入手机号！");
            return true;
        }
        if (KBApplication.currentPhone != null){
            if (!phone.equals(KBApplication.currentPhone)){
                ToastUtil.showInfo("请输入正确的手机号码！");
                return true;
            }
        }

        code = etCode.getText().toString();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 24){
            finish();
        }
    }
}
