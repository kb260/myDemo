package com.kb260.gxdk.view.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.CreditFirst;
import com.kb260.gxdk.entity.CreditSecond;
import com.kb260.gxdk.entity.JsonBean;
import com.kb260.gxdk.entity.ProvinceBean;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.GetJsonDataUtil;
import com.kb260.gxdk.utils.Kb260Utils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.bigkoo.pickerview.OptionsPickerView.*;

/**
 * @author  KB260
 * @date  2017/10/18
 */
public class CreditApplicationSecondActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.a_secondCredit_tvWageType)
    TextView tvWageType;
    @BindView(R.id.a_secondCredit_tvProvidentFund)
    TextView tvProvidentFund;
    @BindView(R.id.a_secondCredit_tvJccs)
    TextView tvJccs;
    @BindView(R.id.a_secondCredit_tvBd)
    TextView tvBd;
    @BindView(R.id.a_secondApplicationLoan_tvExpectation)
    TextView tvExpectation;
    @BindView(R.id.a_secondCredit_tvWorkTime)
    TextView tvWorkTime;
    @BindView(R.id.a_secondCredit_tvYjqx)
    TextView tvYjqx;

    @BindView(R.id.a_secondCredit_etWage)
    EditText etWage;
    @BindView(R.id.a_secondCredit_etDbjc)
    EditText etDbjc;
    @BindView(R.id.a_secondCredit_etTbgs)
    EditText etTbgs;
    @BindView(R.id.a_secondCredit_etYj)
    EditText etYj;
    @BindView(R.id.a_secondCredit_etNj)
    EditText etNj;
    @BindView(R.id.a_secondApplicationLoan_etDescription)
    EditText etDescription;
    @BindView(R.id.a_secondApplicationLoan_tvTime)
    TextView tvTime;
    @BindView(R.id.a_secondApplicationLoan_tvOutMoney)
    TextView tvOutMoney;
    @BindView(R.id.a_secondApplicationLoan_llIsSfyyq)
    LinearLayout llIsSfyyq;
    @BindView(R.id.a_secondCredit_llIsProvidentFund)
    LinearLayout llIsProvidentFund;
    @BindView(R.id.a_secondCredit_llIsBd)
    LinearLayout llIsBd;
    @BindView(R.id.a_secondCredit_tvSfyyq)
    TextView tvSfyyq;



    List<String> list,listNumber;
    int type,mouthType,fiveType;

    TimePickerView timePickerView;
    WheelAdapter wheelAdapter;
    GetJsonDataUtil getJsonDataUtil;
    OptionsPickerView  pvOptions;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    CreditFirst creditFirst;
    String workTime,wage,wageType,providentFund,unilateralDeposit,depositCity,depositTime,
            policy,policyCompany,policyMouth,policyYear,dueoption,duetime,amount,other,sfyyq;

    public static void start(Activity context, CreditFirst creditFirst){
        Intent intent = new Intent(context,CreditApplicationSecondActivity.class);
        intent.putExtra(Contact.CREDITFIRST,creditFirst);
        context.startActivityForResult(intent,24);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_credit_application_second;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        init();
        initIntent();
        initTimeOptionsPicker();
        initAddressDialog();
    }

    private void init() {
        list = new ArrayList<>();
        listNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listNumber.add(String.valueOf(i));
        }
        wheelAdapter = new ArrayWheelAdapter(listNumber);
    }

    private void initIntent() {
        Intent intent = getIntent();
        creditFirst = (CreditFirst)intent.getSerializableExtra(Contact.CREDITFIRST);
    }

    @Override
    public void initToolbar() {
        if (creditFirst.getKind() == Action.KIND_DZD){
            toolbarTitle.setText(R.string.home_jqqd);
        }else {
            toolbarTitle.setText(R.string.a_home_xdsqSecond_toolbarTitle);
        }
        initThisToolbarHaveBack(toolbar,this);
    }

    //工作发放形式
    @OnClick(R.id.a_secondCredit_llWorkTime)
    public void selectWorkTime(){
        mouthType = 1;
        initMouthOptionsPicker();
    }

    //已缴期限
    @OnClick(R.id.a_secondCredit_llYjqx)
    public void selectYjqx(){
        mouthType = 2;
        initMouthOptionsPicker();
    }


    //工作发放形式
    @OnClick(R.id.a_secondCredit_llWageType)
    public void selectWageType(){
        type = 1;
        list.clear();
        list.add("现金");
        list.add("工资卡");
        list.add("混合");
        pickerView(list);
    }

    //公积金
    @OnClick(R.id.a_secondCredit_llProvidentFund)
    public void selectProvidentFund(){
        type = 2;
        list.clear();
        list.add("无");
        list.add("有");
        pickerView(list);
    }

    //缴存城市
    @OnClick(R.id.a_secondCredit_llJccs)
    public void selectJccs(){
        initCityOptionPicker();
    }

    //保单
    @OnClick(R.id.a_secondCredit_llBd)
    public void selectBd(){
        type = 3;
        list.clear();
        list.add("无");
        list.add("有");
        pickerView(list);
    }

    //是否有逾期
    @OnClick(R.id.a_secondCredit_llSfyyq)
    public void llSfyyq(){
        type = 5;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);
    }

    //逾期金额
    @OnClick(R.id.a_secondApplicationLoan_tvOutMoney)
    public void outMoney(){
        fiveType = 2;
        showFiveDialog();
    }


    //逾期项
    @OnClick(R.id.a_secondApplicationLoan_llExpectation)
    public void selectExpectation(){
        type = 4;
        list.clear();
        list.add("信用卡");
        list.add("贷款");
        pickerView(list);
    }

    //逾期时间
    @OnClick(R.id.a_secondApplicationLoan_tvTime)
    public void yqTime(){
        if (timePickerView != null){
            timePickerView.show();
        }
    }




    //确认提交
    @OnClick(R.id.a_secondApplicationLoan_btSubmit)
    public void submit(){
        if (!getInput()){
            creditSubmit();
        }

    }

    private void creditSubmit() {
        Api.getDefault().savecredi(KBApplication.token,KBApplication.userid, creditFirst.getName(), creditFirst.getAge(), creditFirst.getSfz(),
                creditFirst.getPhone(), creditFirst.getAddress(), creditFirst.getSpouseName(), creditFirst.getSpousePhone(),
                creditFirst.getApplicationMoney(), dueoption,duetime,amount,creditFirst.getMarry(),
                sfyyq, other, creditFirst.getHaveCar(), creditFirst.getHaveHouse(),
                creditFirst.getWorkAddress(), creditFirst.getIndustry(), creditFirst.getCareer(),
                wage, workTime, wageType, providentFund,
                unilateralDeposit, depositCity, depositTime,
                policy, policyCompany, policyMouth,
                policyYear, creditFirst.getProvince(), creditFirst.getCity(), creditFirst.getZone(),
                creditFirst.getHouseAge(),creditFirst.getCjh(),creditFirst.getCpp(),creditFirst.getCp(),
                creditFirst.getCl(),creditFirst.getXsjl(),creditFirst.getHouseArea(),creditFirst.getHouseStatus(),
                creditFirst.getHouseNature(),creditFirst.getHouseAddress(),null,creditFirst.getCk(),
                creditFirst.getUseddate(),creditFirst.getUseddateMonth(),creditFirst.getGcyj(),creditFirst.getCppId(),
                creditFirst.getBank(),creditFirst.getSywk(),creditFirst.getSfdz(),creditFirst.getType(),creditFirst.getKind())
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("提交成功！");
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

    public boolean getInput(){
        workTime = tvWorkTime.getText().toString();
        if (StringUtils.isEmpty(workTime)){
            ToastUtil.showInfo("请选择当前单位工作时间！");
            return true;
        }
        wage = etWage.getText().toString().trim();
        if (StringUtils.isEmpty(wage)){
            ToastUtil.showInfo("请输入目前工资！");
            return true;
        }
        wageType = tvWageType.getText().toString().trim();
        if (StringUtils.isEmpty(wageType)){
            ToastUtil.showInfo("请选择工资发放形式！");
            return true;
        }
        providentFund = tvProvidentFund.getText().toString().trim();
        if (StringUtils.isEmpty(providentFund)){
            ToastUtil.showInfo("请选择公积金！");
            return true;
        }
        if (providentFund.equals("有")){
            unilateralDeposit = etDbjc.getText().toString().trim();
            if (StringUtils.isEmpty(unilateralDeposit)){
                ToastUtil.showInfo("请输入单边缴存！");
                return true;
            }
            depositCity = tvJccs.getText().toString().trim();
            if (StringUtils.isEmpty(depositCity)){
                ToastUtil.showInfo("请选择缴存城市！");
                return true;
            }
            depositTime = tvYjqx.getText().toString().trim();
            if (StringUtils.isEmpty(depositTime)){
                ToastUtil.showInfo("请选择已缴期限！");
                return true;
            }
        }

        policy = tvBd.getText().toString().trim();
        if (StringUtils.isEmpty(policy)){
            ToastUtil.showInfo("请选择保单！");
            return true;
        }

        if (policy.equals("有")){
            policyCompany = etTbgs.getText().toString().trim();
            if (StringUtils.isEmpty(policyCompany)){
                ToastUtil.showInfo("请输入投保公司！");
                return true;
            }
            policyMouth = etYj.getText().toString().trim();
            if (StringUtils.isEmpty(policyMouth)){
                ToastUtil.showInfo("请输入月缴！");
                return true;
            }
            policyYear = etNj.getText().toString().trim();
            if (StringUtils.isEmpty(policyYear)){
                ToastUtil.showInfo("请输入年缴！");
                return true;
            }
        }

        sfyyq = tvSfyyq.getText().toString();
        if (StringUtils.isEmpty(sfyyq)){
            ToastUtil.showInfo("请选择是否有逾期！");
            return true;
        }

        if (sfyyq.equals("是")){
            dueoption = tvExpectation.getText().toString().trim();
            if (StringUtils.isEmpty(dueoption)){
                ToastUtil.showInfo("请选择逾期项！");
                return true;
            }
            duetime = tvTime.getText().toString().trim();
            if (StringUtils.isEmpty(duetime)){
                ToastUtil.showInfo("请选择逾期时间！");
                return true;
            }
            amount = tvOutMoney.getText().toString().trim();
            if (StringUtils.isEmpty(amount)){
                ToastUtil.showInfo("请选择逾期金额！");
                return true;
            }
        }

        other = etDescription.getText().toString().trim();
        return false;
    }

    public void pickerView(final List<String> list){
        OptionsPickerView optionsPickerView = new Builder(this, (options1, options2, options3, v) -> {
            String a = list.get(options1);
            switch (type){
                case 1:
                    tvWageType.setText(a);
                    break;
                case 2:
                    switch (a){
                        case "有":
                            llIsProvidentFund.setVisibility(View.VISIBLE);
                            break;
                        case "无":
                            llIsProvidentFund.setVisibility(View.GONE);
                            break;
                        default:
                            break;
                    }
                    tvProvidentFund.setText(a);
                    break;
                case 3:
                    switch (a){
                        case "有":
                            llIsBd.setVisibility(View.VISIBLE);
                            break;
                        case "无":
                            llIsBd.setVisibility(View.GONE);
                            break;
                        default:
                            break;
                    }
                    tvBd.setText(a);
                    break;
                case 4:
                    tvExpectation.setText(a);
                    break;
                case 5:
                    switch (a){
                        case "是":
                            llIsSfyyq.setVisibility(View.VISIBLE);
                            break;
                        case "否":
                            llIsSfyyq.setVisibility(View.GONE);
                            break;
                        default:
                            break;
                    }
                    tvSfyyq.setText(a);
                    break;
                default:
                    break;
            }
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        optionsPickerView.setPicker(list);
        optionsPickerView.show();
    }

    private void initMouthOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new Builder(this, (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2) + listNumber.get(options3);
            String newStr = Kb260Utils.removeFrontZero(str)+ "个月";
            switch (mouthType){
                case 1:
                    tvWorkTime.setText(newStr );
                    break;
                case 2:
                    tvYjqx.setText(newStr);
                    break;
                default:
                    break;
            }
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        pvNoLinkOptions.setNPicker(listNumber, listNumber, listNumber);
        pvNoLinkOptions.show();
    }

    private void initCityOptionPicker() {
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            if (getJsonDataUtil.isLoaded){
                e.onNext(1);
            }else {
                getJsonDataUtil.initJsonData(CreditApplicationSecondActivity.this,options1Items,options2Items,options3Items,e);
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
            String province = options1Items.get(options1).getPickerViewText();
            String city = options2Items.get(options1).get(options2);
            String zone = options3Items.get(options1).get(options2).get(options3);
            String tx = province+" "+ city+" "+zone;
            tvJccs.setText(tx);
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
    }

    public void showFiveDialog() {
        KBDialog.init()
                .setLayoutId(R.layout.bottom_five)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        final WheelView wv1 =holder.getView(R.id.b_options1);
                        final WheelView wv2 =holder.getView(R.id.b_options2);
                        final WheelView wv3 =holder.getView(R.id.b_options3);
                        final WheelView wv4 =holder.getView(R.id.b_options4);
                        final WheelView wv5 =holder.getView(R.id.b_options5);

                        wv1.setAdapter(wheelAdapter);
                        wv2.setAdapter(wheelAdapter);
                        wv3.setAdapter(wheelAdapter);
                        wv4.setAdapter(wheelAdapter);
                        wv5.setAdapter(wheelAdapter);
                        wv1.setCurrentItem(0);
                        wv2.setCurrentItem(0);
                        wv3.setCurrentItem(0);
                        wv4.setCurrentItem(0);
                        wv5.setCurrentItem(0);

                        holder.setOnClickListener(R.id.b_btnSubmit, view -> {
                            dialog.dismiss();

                            String a = String.valueOf(wv1.getCurrentItem()) + String.valueOf(wv2.getCurrentItem())
                                    + String.valueOf(wv3.getCurrentItem())+ String.valueOf(wv4.getCurrentItem())
                                    + String.valueOf(wv5.getCurrentItem());
                            String newStr = a.replaceFirst("^0*", "");
                            switch (fiveType){
                                case 1:
                                    tvTime.setText(newStr);
                                    break;
                                case 2:
                                    tvOutMoney.setText(newStr);
                                    break;
                                default:
                                    break;
                            }
                        });
                        holder.setOnClickListener(R.id.b_btnCancel, view -> dialog.dismiss());
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private void initTimeOptionsPicker() {// 不联动的多级选项
        timePickerView = new TimePickerView.Builder(this, (date, v) -> {
            tvTime.setText(Kb260Utils.getTime(date));
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "")
                .isCenterLabel(false)
                .setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
    }
}
