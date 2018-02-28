package com.kb260.gxdk.view.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.BCheckedAdapter;
import com.kb260.gxdk.entity.CheckedData;
import com.kb260.gxdk.entity.MortgageFirst;
import com.kb260.gxdk.entity.MortgageSecond;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.Kb260Utils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2017/11/7
 */
public class SecondApplicationLoanActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    /*@BindView(R.id.a_secondApplicationLoan_etApplicationMoney)
    EditText etApplicationMoney;*/
    /*@BindView(R.id.a_secondApplicationLoan_tvMinInterestRate)
    TextView tvMinInterestRate;*/
    @BindView(R.id.a_secondApplicationLoan_tvMaxInterestRate)
    TextView tvMaxInterestRate;
    @BindView(R.id.a_secondApplicationLoan_tvMinInterestRate)
    TextView tvMinInterestRate;
    @BindView(R.id.a_secondApplicationLoan_tvMouth)
    TextView tvMouth;
    @BindView(R.id.a_secondApplicationLoan_tvCompany)
    TextView tvCompany;
    @BindView(R.id.a_secondApplicationLoan_tvExpectation)
    TextView tvExpectation;
    @BindView(R.id.a_secondApplicationLoan_tvIdentity)
    TextView tvIdentity;
    @BindView(R.id.a_secondApplicationLoan_tvZgbl)
    TextView tvZgbl;
    @BindView(R.id.a_secondApplicationLoan_tvSite)
    TextView tvSite;
    @BindView(R.id.a_secondApplicationLoan_tvTime)
    TextView tvTime;
    @BindView(R.id.a_secondApplicationLoan_tvOutMoney)
    TextView tvOutMoney;
    @BindView(R.id.a_secondApplicationLoan_etDescription)
    EditText etDescription;
    @BindView(R.id.a_secondApplicationLoan_tvApplicationMoney)
    TextView tvApplicationMoney;
    @BindView(R.id.a_secondHouseApplicationLoan_tvSfyyq)
    TextView tvSfyyq;
    @BindView(R.id.a_secondApplicationLoan_tvRepaymentTime)
    TextView tvRepaymentTime;
    @BindView(R.id.a_secondApplicationLoan_tvRepaymentType)
    TextView tvRepaymentType;

    @BindView(R.id.a_secondApplicationLoan_llIsCompany)
    LinearLayout llIsCompany;
    @BindView(R.id.a_secondApplicationLoan_llIsSfyyq)
    LinearLayout llIsSfyyq;
    @BindView(R.id.a_secondApplicationLoan_llZgbl)
    LinearLayout llZgbl;
    @BindView(R.id.a_secondApplicationLoan_llIsZgbl)
    LinearLayout llIsZgbl;


    List<String> list,listNumber,listPoint;

    int type,rateType,threeType,manyType;

    WheelAdapter wheelAdapter;
    WheelAdapter rateAdapter;
    TimePickerView timePickerView;

    List<CheckedData> needMaterials;
    BCheckedAdapter bCheckedAdapter;

    BCheckedAdapter needTypeAdapter;
    List<CheckedData> needType;

    String sfdz,applicationMoney,rate,minRate,loanTime,haveCompany,identity,proportion,haveSite,haveOverdue,
            overdue,overdueTime,overdueMoney,other,paytime,monthpay;

    public static void start(Activity context, MortgageFirst mortgageFirst){
        Intent intent = new Intent(context,SecondApplicationLoanActivity.class);
        intent.putExtra(Contact.MORTGAGE_FIRST,mortgageFirst);
        context.startActivityForResult(intent,24);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_second_application;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initMoneyDialog();
        initTimeOptionsPicker();

        list = new ArrayList<>();
        listNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listNumber.add(String.valueOf(i));
        }
        wheelAdapter = new ArrayWheelAdapter(listNumber);

        listPoint = new ArrayList<>();
        listPoint.add(".");
        rateAdapter = new ArrayWheelAdapter(listPoint);
    }

    private void initMoneyDialog() {
        needMaterials = new ArrayList<>();
        needMaterials.add(new CheckedData("3个月",false));
        needMaterials.add(new CheckedData("6个月",false));
        needMaterials.add(new CheckedData("一年一转",false));
        needMaterials.add(new CheckedData("三年一转",false));
        needMaterials.add(new CheckedData("五年一转",false));
        needMaterials.add(new CheckedData("随借随还",false));
        needMaterials.add(new CheckedData("10年",false));
        needMaterials.add(new CheckedData("20年",false));
        needMaterials.add(new CheckedData("30年",false));
        bCheckedAdapter = new BCheckedAdapter(needMaterials);

        needType = new ArrayList<>();
        needType.add(new CheckedData("等额本息",false));
        needType.add(new CheckedData("先息后本",false));
        needType.add(new CheckedData("随借随还",false));
        needTypeAdapter = new BCheckedAdapter(needType);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.f_home_fdsq_toolbarTitle);
        initThisToolbarHaveBack(toolbar,this);
    }


    //申请金额
    @OnClick(R.id.a_secondApplicationLoan_llApplicationMoney)
    public void applicationMoney(){
        showDialog();
    }

    //最大利率
    @OnClick(R.id.a_secondApplicationLoan_tvMaxInterestRate)
    public void minInterestRate(){
        rateType =2;
        showRate();
    }

    //还款周期
    @OnClick(R.id.a_secondApplicationLoan_llRepaymentTime)
    public void llRepaymentTime(){
        manyType =1;
        showBottomCheckedDialog(bCheckedAdapter);
    }

    //还款方式
    @OnClick(R.id.a_secondApplicationLoan_llRepaymentType)
    public void llRepaymentType(){
        manyType =2;
        showBottomCheckedDialog(needTypeAdapter);
    }


    //贷款期限
    @OnClick(R.id.a_secondApplicationLoan_llMouth)
    public void mouth(){
        initMouthOptionsPicker();
    }

    //是否有公司
    @OnClick(R.id.a_secondApplicationLoan_llCompany)
    public void company(){
        type =1;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);
    }

    //预期项
    @OnClick(R.id.a_secondApplicationLoan_llExpectation)
    public void expectation(){
        type =4;
        list.clear();
        list.add("信用卡");
        list.add("贷款");
        pickerView(list);
    }

    //身份
    @OnClick(R.id.a_secondApplicationLoan_llIdentity)
    public void identity(){
        type =2;
        list.clear();
        list.add("法人");
        list.add("股东");
        list.add("合伙人");
        pickerView(list);
    }

    //身份
    @OnClick(R.id.a_secondApplicationLoan_llZgbl)
    public void llZgbl(){
        initTwoOptionsPicker();
    }

    //是否有场地
    @OnClick(R.id.a_secondApplicationLoan_llSite)
    public void site(){
        type =3;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);
    }

    //是否逾期
    @OnClick(R.id.a_secondHouseApplicationLoan_llSfyyq)
    public void Sfyyq(){
        type =5;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);
    }

    //逾期时间
    @OnClick(R.id.a_secondApplicationLoan_llTime)
    public void yqTime(){
        if (timePickerView != null){
            timePickerView.show();
        }
    }

    //逾期金额
    @OnClick(R.id.a_secondApplicationLoan_tvOutMoney)
    public void outMoney(){
        threeType = 2;
        showFiveDialog();
    }

    //确认提交
    @OnClick(R.id.a_secondApplicationLoan_btSubmit)
    public void submit(){
        if (!getInput()){
            MortgageFirst mortgageFirst= (MortgageFirst) getIntent().getSerializableExtra(Contact.MORTGAGE_FIRST);
            MortgageSecond mortgageSecond = new MortgageSecond(applicationMoney,rate,minRate,loanTime,haveCompany,
                    identity,proportion,haveSite,haveOverdue,
                    overdue,overdueTime,overdueMoney,other,paytime,monthpay);
            InformationPerfectActivity.start(this,mortgageFirst,mortgageSecond);
        }
    }

    public boolean getInput(){
        applicationMoney = tvApplicationMoney.getText().toString().trim();
        if (StringUtils.isEmpty(applicationMoney)){
            ToastUtil.showInfo("请选择贷款金额！");
            return true;
        }

        rate = tvMaxInterestRate.getText().toString().trim();
        if (StringUtils.isEmpty(rate)){
            ToastUtil.showInfo("请选择最大接受利率！");
            return true;
        }
        if (Double.valueOf(rate)<4.7 || Double.valueOf(rate)>15){
            ToastUtil.showInfo("最大利率范围为：4.7~15！");
            return true;
        }

        minRate = tvMinInterestRate.getText().toString().trim();
        if (StringUtils.isEmpty(minRate)){
            ToastUtil.showInfo("请选择最小接受利率！");
            return true;
        }

        paytime = tvRepaymentTime.getText().toString();
        if (StringUtils.isEmpty(paytime)){
            ToastUtil.showInfo("请选择还款周期！");
            return true;
        }

        monthpay = tvRepaymentType.getText().toString();
        if (StringUtils.isEmpty(monthpay)){
            ToastUtil.showInfo("请选择还款方式！");
            return true;
        }

        loanTime = tvMouth.getText().toString().trim();
        if (StringUtils.isEmpty(loanTime)){
            ToastUtil.showInfo("请选择贷款期限！");
            return true;
        }

        haveCompany = tvCompany.getText().toString().trim();
        if (StringUtils.isEmpty(haveCompany)){
            ToastUtil.showInfo("请选择是否有公司！");
            return true;
        }
        switch (haveCompany){
            case "是":
                haveCompany = "1";
                break;
            case "否":
                haveCompany = "0";
                break;
            default:
                break;
        }

        if (haveCompany.equals("1")){
            identity = tvIdentity.getText().toString().trim();
            if (StringUtils.isEmpty(identity)){
                ToastUtil.showInfo("请选择身份！");
                return true;
            }

            if (identity.equals("股东")){
                proportion = tvZgbl.getText().toString().trim();
                if (StringUtils.isEmpty(proportion)){
                    ToastUtil.showInfo("请选择占股比例！");
                    return true;
                }
            }

            haveSite = tvSite.getText().toString().trim();
            if (StringUtils.isEmpty(haveSite)){
                ToastUtil.showInfo("请选择是否有场地！");
                return true;
            }
        }

        haveOverdue = tvSfyyq.getText().toString().trim();
        if (StringUtils.isEmpty(haveOverdue)){
            ToastUtil.showInfo("请选择是否有逾期！");
            return true;
        }
        switch (haveOverdue){
            case "是":
                haveOverdue = "1";
                break;
            case "否":
                haveOverdue = "0";
                break;
            default:
                break;
        }

        if (haveOverdue.equals("1")){
            overdue = tvExpectation.getText().toString().trim();
            if (StringUtils.isEmpty(overdue)){
                ToastUtil.showInfo("请选择逾期项！");
                return true;
            }

            overdueTime = tvTime.getText().toString().trim();
            if (StringUtils.isEmpty(overdueTime)){
                ToastUtil.showInfo("请选择逾期时间！");
                return true;
            }

            overdueMoney = tvOutMoney.getText().toString().trim();
            if (StringUtils.isEmpty(overdueMoney)){
                ToastUtil.showInfo("请输入金额！");
                return true;
            }
        }

        other = etDescription.getText().toString().trim();

        return false;
    }


    public void pickerView(final List<String> list){
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this,
                (options1, options2, options3, v) -> {
            String a = list.get(options1);
            switch (type){
                case 1:
                    tvCompany.setText(a);
                    if (a.equals("是")){
                        llIsCompany.setVisibility(View.VISIBLE);
                    }else if (a.equals("否")){
                        llIsCompany.setVisibility(View.GONE);
                    }
                    break;
                case 2:
                    tvIdentity.setText(a);
                    if (a.equals("股东")){
                        llIsZgbl.setVisibility(View.VISIBLE);
                    }else {
                        llIsZgbl.setVisibility(View.GONE);
                    }
                    break;
                case 3:
                    tvSite .setText(a);
                    break;
                case 4:
                    tvExpectation.setText(a);
                    break;
                case 5:
                    tvSfyyq.setText(a);
                    if (a.equals("是")){
                        llIsSfyyq.setVisibility(View.VISIBLE);
                    }else if (a.equals("否")){
                        llIsSfyyq.setVisibility(View.GONE);
                    }
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
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this,
                (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2) + listNumber.get(options3);
            String newStr = Kb260Utils.removeFrontZero(str);
            tvMouth.setText(newStr) ;

        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        pvNoLinkOptions.setNPicker(listNumber, listNumber, listNumber);
        pvNoLinkOptions.show();
    }

    private void initTwoOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this,
                (options1, options2, options3, v) -> {
                    String str = listNumber.get(options1) + listNumber.get(options2);
                    String newStr = Kb260Utils.removeFrontZero(str);
                    tvZgbl.setText(newStr) ;
                }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        pvNoLinkOptions.setNPicker(listNumber, listNumber, null);
        pvNoLinkOptions.show();
    }

    public void showDialog() {
        KBDialog.init()
                .setLayoutId(R.layout.bottom_money)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        final WheelView wv1 =holder.getView(R.id.b_options1);
                        final WheelView wv2 =holder.getView(R.id.b_options2);
                        final WheelView wv3 =holder.getView(R.id.b_options3);
                        final WheelView wv4 =holder.getView(R.id.b_options4);

                        wv1.setAdapter(wheelAdapter);
                        wv2.setAdapter(wheelAdapter);
                        wv3.setAdapter(wheelAdapter);
                        wv4.setAdapter(wheelAdapter);
                        wv1.setCurrentItem(0);
                        wv2.setCurrentItem(0);
                        wv3.setCurrentItem(0);
                        wv4.setCurrentItem(0);

                        holder.setOnClickListener(R.id.b_btnSubmit, view -> {
                            dialog.dismiss();

                            String a = String.valueOf(wv1.getCurrentItem()) + String.valueOf(wv2.getCurrentItem())
                                    + String.valueOf(wv3.getCurrentItem())+ String.valueOf(wv4.getCurrentItem());
                            String newStr = a.replaceFirst("^0*", "");
                            tvApplicationMoney.setText(newStr);
                        });
                        holder.setOnClickListener(R.id.b_btnCancel, view -> dialog.dismiss());
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
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
                            switch (threeType){
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

    public void showRate() {
        KBDialog.init()
                .setLayoutId(R.layout.bottom_money)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        final WheelView wv1 =holder.getView(R.id.b_options1);
                        final WheelView wv2 =holder.getView(R.id.b_options2);
                        final WheelView wv3 =holder.getView(R.id.b_options3);
                        final WheelView wv4 =holder.getView(R.id.b_options4);

                        wv1.setAdapter(wheelAdapter);
                        wv2.setAdapter(wheelAdapter);
                        wv3.setAdapter(rateAdapter);
                        wv4.setAdapter(wheelAdapter);
                        wv1.setCurrentItem(0);
                        wv2.setCurrentItem(0);
                        wv3.setCurrentItem(0);
                        wv3.setCyclic(false);
                        wv4.setCurrentItem(0);

                        holder.setOnClickListener(R.id.b_btnSubmit, view -> {
                            dialog.dismiss();

                            String a = "."+ String.valueOf(wv4.getCurrentItem());
                            String b = String.valueOf(wv1.getCurrentItem()) + String.valueOf(wv2.getCurrentItem());
                            String newStr =Kb260Utils.removeFrontZero(b);
                            String c = newStr+a;
                            switch (rateType){
                                case 1:
                                    tvMinInterestRate.setText(c);
                                    break;
                                case 2:
                                    double maxMax = Double.valueOf(c);
                                    if (maxMax < 4.7 || maxMax > 15) {
                                        ToastUtil.showInfo("最大利率范围为：4.7~15！");
                                        break;
                                    } else {
                                        tvMaxInterestRate.setText(c);
                                    }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==Action.FINISH_ACTIVITY){
            setResult(Action.FINISH_ACTIVITY);
            finish();
        }
    }

    public void showBottomCheckedDialog(BCheckedAdapter adapter) {
        KBDialog.init()
                .setLayoutId(R.layout.bottom_checked)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final BaseKBDialog dialog) {
                        RecyclerView rv = holder.getView(R.id.b_checked_rv);
                        rv.setLayoutManager(new LinearLayoutManager(SecondApplicationLoanActivity.this));
                        rv.setAdapter(adapter);

                        List<CheckedData> checkedDatas = adapter.datas;
                        int length = checkedDatas.size();
                        for (int i =0;i<length;i++){
                            checkedDatas.get(i).setChecked(false);
                        }

                        holder.setOnClickListener(R.id.b_checked_btnSubmit, view -> {
                            dialog.dismiss();

                            StringBuilder stringBuilder = new StringBuilder();
                            List<CheckedData> checkedDatas1 = adapter.datas;
                            int length1 = checkedDatas1.size();
                            for (int i = 0; i< length1; i++){
                                if (checkedDatas1.get(i).isChecked()){
                                    if (stringBuilder.length() != 0){
                                        stringBuilder.append(",");
                                    }
                                    stringBuilder.append(checkedDatas1.get(i).getName());
                                }
                            }
                            String a = stringBuilder.toString();

                            switch (manyType){
                                case 1:
                                    tvRepaymentTime.setText(a);
                                    break;
                                case 2:
                                    tvRepaymentType.setText(a);
                                    break;
                                default:
                                    break;
                            }
                        });
                        holder.setOnClickListener(R.id.b_checked_btnCancel, view -> dialog.dismiss());
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }
}
