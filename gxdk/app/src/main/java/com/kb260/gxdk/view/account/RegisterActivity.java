package com.kb260.gxdk.view.account;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.JsonBean;
import com.kb260.gxdk.model.AboutMe;
import com.kb260.gxdk.model.Protocol;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.CountDownTimerUtils;
import com.kb260.gxdk.utils.GetJsonDataUtil;
import com.kb260.gxdk.utils.RegexUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author kb260
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_register_etPhone)
    EditText etPhone;
    @BindView(R.id.a_register_etPassword)
    EditText etPassword;
    @BindView(R.id.a_register_btGetCode)
    TextView btGetCode;
    @BindView(R.id.a_register_tvAddress)
    TextView tvAddress;
    @BindView(R.id.a_register_etCode)
    EditText etCode;
    @BindView(R.id.a_register_etName)
    EditText etName;
    @BindView(R.id.a_register_etInvitationCode)
    EditText etInvitationCode;

    String province,city,zone,phone,code,name,password,address,rightCode,invitationCode;

    GetJsonDataUtil getJsonDataUtil;
    OptionsPickerView pvOptions;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    public static void start(Context context){
        Intent intent = new Intent(context,RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initAddressDialog();
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.register_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    //注册
    @OnClick(R.id.a_register_btRegister)
    public void register(){
        if (!getInput()){
            Api.getDefault().registration(province,city,zone,phone,name,password,invitationCode)
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<String>(this) {
                        @Override
                        public void onSubscribe(Disposable d) {
                            super.onSubscribe(d);
                        }

                        @Override
                        protected void success(String list) {
                            ToastUtil.showInfo("注册成功！");
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

    //获取验证码
    @OnClick(R.id.a_register_btGetCode)
    public void getCode(){
        if (getInputPhone()){
            return;
        }
        Api.getDefault().registrationcode(phone)
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
                        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(btGetCode, Contact.CODE_TIME, 1000);
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
        phone = etPhone.getText().toString().trim();
        if (StringUtils.isEmpty(phone)){
            ToastUtil.showInfo("请输入手机号码！");
            return true;
        }
        if (RegexUtils.isMobileSimple(phone)){
            ToastUtil.showInfo("请输入正确的手机号码！");
            return true;
        }
        return false;
    }

    private boolean getInput(){
        if (StringUtils.isEmpty(address)){
            ToastUtil.showInfo("请选择地址！");
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

        if (StringUtils.isEmpty(rightCode)){
            ToastUtil.showInfo("请获取验证码！");
            return true;
        }
        if (!code.equals(rightCode)){
            ToastUtil.showInfo("请输入正确验证码！");
            return true;
        }

        name = etName.getText().toString().trim();
        if (StringUtils.isEmpty(name)){
            ToastUtil.showInfo("请输入姓名！");
            return true;
        }
        // 只能输入汉字或者英文
        if (!name.matches("[\u4e00-\u9fa5]+") && !name.matches("[a-zA-Z /]+")) {
            ToastUtil.showInfo("姓名只能输入汉字或者英文！");
            return true;
        }

        password = etPassword.getText().toString().trim();
        if (StringUtils.isEmpty(password)){
            ToastUtil.showInfo("请输入密码！");
            return true;
        }
        if (password.length()<6 || password.length()>16){
            ToastUtil.showInfo("密码应设置为6-16位数字加字符");
            return true;
        }

        invitationCode = etInvitationCode.getText().toString().trim();
        if (StringUtils.isEmpty(invitationCode)){
            ToastUtil.showInfo("请输入邀请码！");
            return true;
        }
        return false;
    }

    //用户注册协议
    @OnClick(R.id.a_register_tvAgree)
    public void agree(){
        ProtocolActivity.start(this);
    }



   /* //QQ登陆
    @OnClick(R.id.a_register_ivQQ)
    public void qq(){
        MainActivity.start(this);
    }

    //微信登录
    @OnClick(R.id.a_register_ivWx)
    public void wx(){
        MainActivity.start(this);
    }*/

    @OnClick(R.id.a_register_tvAddress)
    public void address(){
        initCityOptionPicker();
    }

    private void initCityOptionPicker() {
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            if (getJsonDataUtil.isLoaded){
                e.onNext(1);
            }else {
                getJsonDataUtil.initJsonData(RegisterActivity.this,options1Items,options2Items,options3Items,e);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
                        pvOptions.show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initAddressDialog() {
        getJsonDataUtil = new GetJsonDataUtil();
        pvOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            province = options1Items.get(options1).getPickerViewText();
            city = options2Items.get(options1).get(options2);
            zone = options3Items.get(options1).get(options2).get(options3);
            address = province+" " +city+" " +zone;
            tvAddress.setText(address);

        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
    }

    @OnClick(R.id.a_register_call)
    public void call(){
        initData();

    }

    private void initData() {
        Api.getDefault().selaboutus(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<AboutMe>(this) {
                    @Override
                    protected void success(AboutMe list) {
                        if (list != null){
                            call1(list.getLinktel());
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }
    public void call1(String realPhone){
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CALL_PHONE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + realPhone));
                        startActivity(intent);
                    }else {
                        ToastUtil.showInfo("无法获取拨打电话权限！");
                    }
                });
    }
}
