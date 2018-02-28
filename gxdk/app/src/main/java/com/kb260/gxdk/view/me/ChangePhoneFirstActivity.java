package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * @author  KB260
 * Created on  2017/11/1
 */
public class ChangePhoneFirstActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_changePhone_etOldPhone)
    EditText etOldPhone;
    @BindView(R.id.a_changePhone_etCode)
    EditText etCode;
    @BindView(R.id.a_changePhone_btGetCode)
    Button btCode;

    String phone,code,rightCode;

    public static void start(Context context){
        Intent intent = new Intent(context,ChangePhoneFirstActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_phone_first;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_meDetail_changePhone_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }


    //获取验证码
    @OnClick(R.id.a_changePhone_btGetCode)
    public void getCode(){
        if (getInputPhone()){
            return;
        }
        Api.getDefault().forgetcode(phone)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        rightCode = list;
                        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(btCode, Contact.CODE_TIME, 1000);
                        countDownTimerUtils.start();
                        ToastUtil.showInfo("验证码已发送！");
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    //下一步
    @OnClick(R.id.a_changePhone_btNext)
    public void next(){
        if (!getInput()){
            ChangePhoneSecondActivity.start(this);
        }
    }

    public boolean getInput(){
        phone = etOldPhone.getText().toString().trim();
        if (StringUtils.isEmpty(phone)) {
            ToastUtil.showInfo("请输入手机号！");
            return true;
        }
        if (!phone.equals(KBApplication.currentPhone)){
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 24){
            finish();
        }
    }

    private boolean getInputPhone(){
        phone = etOldPhone.getText().toString();
        if (StringUtils.isEmpty(phone)) {
            ToastUtil.showInfo("请输入手机号！");
            return true;
        }
        if (!phone.equals(KBApplication.currentPhone)){
            ToastUtil.showInfo("请输入正确的手机号码！");
            return true;
        }
        return false;
    }
}
