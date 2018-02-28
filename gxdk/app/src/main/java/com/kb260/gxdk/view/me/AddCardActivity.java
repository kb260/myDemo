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
import com.kb260.gxdk.model.LoginSuccess;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.CountDownTimerUtils;
import com.kb260.gxdk.utils.RegexUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.account.LoginActivity;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.main.MainActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 */
public class AddCardActivity extends BaseActivity{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_addCard_btGetCode)
    Button btCode;
    @BindView(R.id.a_addCard_etName)
    EditText etCardholder;
    @BindView(R.id.a_addCard_etIdCard)
    EditText etIdCard;
    @BindView(R.id.a_addCard_etCardNum)
    EditText etCardNum;
    @BindView(R.id.a_addCard_etBank)
    EditText etBank;
    @BindView(R.id.a_addCard_etBranch)
    EditText etBranch;
    @BindView(R.id.a_addCard_etPhone)
    EditText etPhone;
    @BindView(R.id.a_addCard_etCode)
    EditText etCode;

    String cardholder,idCard,cardNum,bank,branch,phone,code,rightCode;
    CountDownTimerUtils countDownTimerUtils;

    public static void start(Activity context){
        Intent intent = new Intent(context,AddCardActivity.class);
        context.startActivityForResult(intent,1);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_card;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        countDownTimerUtils = new CountDownTimerUtils(btCode, Contact.CODE_TIME, 1000);

    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_meDetail_addCard_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    //获取验证码
    @OnClick(R.id.a_addCard_btGetCode)
    public void getCode(){
        if (getInputPhone()) {
            return;
        }
        Api.getDefault().code(phone)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showInfo("验证码已发送！");
                        rightCode = list;
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
    @OnClick(R.id.a_addCard_btSave)
    public void save(){
        if (!getInput()){
            save1();
        }
    }

    public boolean getInput(){
        cardholder = etCardholder.getText().toString().trim();
        if (StringUtils.isEmpty(cardholder)){
            ToastUtil.showInfo("请输入持卡人！");
            return true;
        }
        idCard = etIdCard.getText().toString().trim();
        if (StringUtils.isEmpty(idCard)){
            ToastUtil.showInfo("请输入身份证号码！");
            return true;
        }
        if (!RegexUtils.isIDCard18(idCard)){
            ToastUtil.showInfo("请输入正确的身份证号码！");
            return true;
        }
        cardNum = etCardNum.getText().toString().trim();
        if (StringUtils.isEmpty(cardNum)){
            ToastUtil.showInfo("请输入银行卡号！");
            return true;
        }
        if (cardNum.length()<16 || cardNum.length()>19){
            ToastUtil.showInfo("请输入正确的银行卡号！");
            return true;
        }
        bank = etBank.getText().toString().trim();
        if (StringUtils.isEmpty(bank)){
            ToastUtil.showInfo("请输入银行！");
            return true;
        }
        branch = etBranch.getText().toString().trim();
        if (StringUtils.isEmpty(branch)){
            ToastUtil.showInfo("请输入银行卡支行！");
            return true;
        }
        phone = etPhone.getText().toString().trim();
        if (StringUtils.isEmpty(phone)){
            ToastUtil.showInfo("请输入手机号码！");
            return true;
        }
        if (RegexUtils.isMobileSimple(phone)){
            ToastUtil.showInfo("请输入正确的手机号码！");
            return true;
        }
        code = etCode.getText().toString().trim();
        if (StringUtils.isEmpty(code)){
            ToastUtil.showInfo("请输入验证码！");
            return true;
        }
        if (StringUtils.isEmpty(rightCode) || !code.equals(rightCode)){
            ToastUtil.showInfo("请输入正确验证码！");
            return true;
        }
        return false;
    }

    private boolean getInputPhone(){
        phone = etPhone.getText().toString().trim();
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

    private void save1(){
        Api.getDefault().savebank(KBApplication.token,KBApplication.userid,cardholder,cardNum,bank,branch,phone,idCard)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("添加成功！");
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
}
