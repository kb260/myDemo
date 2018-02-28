package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.JsonBean;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.CountDownTimerUtils;
import com.kb260.gxdk.utils.GetJsonDataUtil;
import com.kb260.gxdk.utils.RegexUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;

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
 * @author  KB260
 * Created on  2017/11/2
 */
public class

InviteRegisterActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_inviteRegister_sp)
    Spinner sp;
    @BindView(R.id.a_inviteRegister_llIdCard)
    LinearLayout llIdCard;
    @BindView(R.id.a_inviteRegister_llCompanyName)
    LinearLayout llCompanyName;
    @BindView(R.id.a_inviteRegister_llCompanyAddress)
    LinearLayout llCompanyAddress;
    @BindView(R.id.a_inviteRegister_llBink)
    LinearLayout llBink;
    @BindView(R.id.a_inviteRegister_llBranch)
    LinearLayout llBranch;
    @BindView(R.id.a_inviteRegister_llBankAddress)
    LinearLayout llBankAddress;
    @BindView(R.id.a_inviteRegister_llSp)
    LinearLayout llSp;
    @BindView(R.id.a_inviteRegister_tvAddress)
    TextView tvAddress;

    @BindView(R.id.a_inviteRegister_etName)
    EditText etName;
    @BindView(R.id.a_inviteRegister_etPhone)
    EditText etPhone;
    @BindView(R.id.a_inviteRegister_etCode)
    EditText etCode;
    @BindView(R.id.a_inviteRegister_etPassword)
    EditText etPassword;
    @BindView(R.id.a_inviteRegister_etIdCard)
    EditText etIdCard;
    @BindView(R.id.a_inviteRegister_etCompanyName)
    EditText etCompanyName;
    @BindView(R.id.a_inviteRegister_etCompanyAddress)
    EditText etCompanyAddress;
    @BindView(R.id.a_inviteRegister_bank)
    EditText etBank;
    @BindView(R.id.a_inviteRegister_branch)
    EditText etBranch;
    @BindView(R.id.a_inviteRegister_bankAddress)
    EditText etBankAddress;
    @BindView(R.id.a_inviteRegister_btGetCode)
    Button btGetCode;

    GetJsonDataUtil getJsonDataUtil;
    OptionsPickerView  pvOptions;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    String phone,code,rightCode;
    String realname,branchname,bank,bankaddress,curentaddrress,password,status,company,companyaddress,idcard;

    public static void start(Context context,int type) {
        Intent intent = new Intent(context, InviteRegisterActivity.class);
        intent.putExtra(Contact.REGISTER_TYPE,type);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_invite_register;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
        initSp();
        initAddress();

    }

    private void initAddress() {
        getJsonDataUtil = new GetJsonDataUtil();
        pvOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            String tx = options1Items.get(options1).getPickerViewText()+" "+
                    options2Items.get(options1).get(options2)+" "+
                    options3Items.get(options1).get(options2).get(options3);
            tvAddress.setText(tx);

        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
    }

    private void initIntent() {
        int type = getIntent().getIntExtra(Contact.REGISTER_TYPE,0);
        if (type != -1){
            llSp.setVisibility(View.GONE);
            currentType(type);
        }else {
            status = "0";
        }

    }

    private void initSp() {
        // 声明一个ArrayAdapter用于存放简单数据

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.register_type));
        // 把定义好的Adapter设定到spinner中
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_inviteFriend_friendRegister);
        initThisToolbarHaveBack(toolbar, this);
    }

    //注册
    @OnClick(R.id.a_inviteRegister_btRegister)
    public void register() {
        if (!getInput()){
            initData();
        }
    }

    private void initData(){
        Api.getDefault().helpreg(KBApplication.token,phone,realname,branchname,bank,bankaddress,
                curentaddrress,password,KBApplication.userid,status,company,companyaddress,idcard)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("注册成功！");
                        finish();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    //获取验证码
    @OnClick(R.id.a_inviteRegister_btGetCode)
    public void getCode() {
        if (getInputPhone()){
            return;
        }
        Api.getDefault().registrationcode(phone)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showInfo("验证码已发送！");
                        rightCode = list;
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

    //所在位置
    @OnClick(R.id.a_inviteRegister_address)
    public void address() {
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            if (getJsonDataUtil.isLoaded){
                e.onNext(1);
            }else {
                getJsonDataUtil.initJsonData(InviteRegisterActivity.this,options1Items,options2Items,options3Items,e);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        showPickerView();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        currentType(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void currentType(int i){
        switch (i) {
            case 0:
                status = "0";
                llIdCard.setVisibility(View.GONE);
                llCompanyName.setVisibility(View.GONE);
                llCompanyAddress.setVisibility(View.GONE);
                llBink.setVisibility(View.GONE);
                llBranch.setVisibility(View.GONE);
                llBankAddress.setVisibility(View.GONE);
                break;
            case 1:
                status = "1";
                llIdCard.setVisibility(View.VISIBLE);
                llCompanyName.setVisibility(View.VISIBLE);
                llCompanyAddress.setVisibility(View.VISIBLE);
                llBink.setVisibility(View.GONE);
                llBranch.setVisibility(View.GONE);
                llBankAddress.setVisibility(View.GONE);
                break;
            case 2:
                status = "2";
                llIdCard.setVisibility(View.VISIBLE);
                llCompanyName.setVisibility(View.GONE);
                llCompanyAddress.setVisibility(View.GONE);
                llBink.setVisibility(View.VISIBLE);
                llBranch.setVisibility(View.VISIBLE);
                llBankAddress.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private void showPickerView() {// 弹出选择器
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
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
        realname = etName.getText().toString().trim();
        if (StringUtils.isEmpty(phone)){
            ToastUtil.showInfo("请输入姓名！");
            return true;
        }
        // 只能输入汉字或者英文
        if (!realname.matches("[\u4e00-\u9fa5]+") && !realname.matches("[a-zA-Z /]+")) {
            ToastUtil.showInfo("姓名只能输入汉字或者英文！");
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

        if (StringUtils.isEmpty(rightCode)){
            ToastUtil.showInfo("请获取验证码！");
            return true;
        }

        code = etCode.getText().toString().trim();
        if (StringUtils.isEmpty(code)){
            ToastUtil.showInfo("请输入验证码！");
            return true;
        }

        if (!code.equals(rightCode)){
            ToastUtil.showInfo("请输入正确的验证码！");
            return true;
        }

        code = etCode.getText().toString().trim();
        if (StringUtils.isEmpty(code)){
            ToastUtil.showInfo("请输入验证码！");
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


        curentaddrress = tvAddress.getText().toString().trim();
        if (StringUtils.isEmpty(curentaddrress)){
            ToastUtil.showInfo("请选择所处位置！");
            return true;
        }


        switch (status){
            case "1":
                idcard = etIdCard.getText().toString().trim();
                if (StringUtils.isEmpty(idcard)){
                    ToastUtil.showInfo("请输入身份证！");
                    return true;
                }
                if (!RegexUtils.isIDCard18(idcard)){
                    ToastUtil.showInfo("请输入正确的身份证号码！");
                    return true;
                }
                company = etCompanyName.getText().toString().trim();
                if (StringUtils.isEmpty(curentaddrress)){
                    ToastUtil.showInfo("请选择所处位置！");
                    return true;
                }
                companyaddress = etCompanyAddress.getText().toString().trim();
                if (StringUtils.isEmpty(curentaddrress)){
                    ToastUtil.showInfo("请选择所处位置！");
                    return true;
                }
                break;
            case "2":
                idcard = etIdCard.getText().toString().trim();
                if (StringUtils.isEmpty(idcard)){
                    ToastUtil.showInfo("请输入身份证！");
                    return true;
                }

                if (!RegexUtils.isIDCard18(idcard)){
                    ToastUtil.showInfo("请输入正确的身份证号码！");
                    return true;
                }
                bank = etBank.getText().toString().trim();
                if (StringUtils.isEmpty(bank)){
                    ToastUtil.showInfo("请输入所属银行名称！");
                    return true;
                }
                branchname = etBranch.getText().toString().trim();
                if (StringUtils.isEmpty(branchname)){
                    ToastUtil.showInfo("请输入支行名称！");
                    return true;
                }
                bankaddress = etBankAddress.getText().toString().trim();
                if (StringUtils.isEmpty(bankaddress)){
                    ToastUtil.showInfo("请输入银行地址！");
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
}
