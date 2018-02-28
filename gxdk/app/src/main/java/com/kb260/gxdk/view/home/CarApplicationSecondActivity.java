package com.kb260.gxdk.view.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.kb260.gxdk.adapter.BCheckedAdapter;
import com.kb260.gxdk.entity.CarLoanFirst;
import com.kb260.gxdk.entity.CarLoanSecond;
import com.kb260.gxdk.entity.CheckedData;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 * @date 2017/10/18
 */
public class CarApplicationSecondActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.a_secondApplicationLoan_tvCxz)
    TextView tvCxz;
    @BindView(R.id.a_secondApplicationLoan_tvAjyh)
    TextView tvAjyh;
    @BindView(R.id.a_secondApplicationLoan_tvXydz)
    TextView tvXydz;
    @BindView(R.id.a_secondApplicationLoan_tvSfyyq)
    TextView tvSfyyq;
    @BindView(R.id.a_secondApplicationLoan_tvExpectation)
    TextView tvExpectation;
    @BindView(R.id.a_secondApplicationLoan_tvDkqx)
    TextView tvDkqx;
    @BindView(R.id.a_secondApplicationLoan_tvApplicationMoney)
    TextView tvApplicationMoney;
    @BindView(R.id.a_secondApplicationLoan_tvSywk)
    TextView tvSywk;
    @BindView(R.id.a_secondCarApplicationLoan_tvMinInterestRate)
    TextView tvMinInterestRate;
    @BindView(R.id.a_secondCarApplicationLoan_tvMaxInterestRate)
    TextView tvMaxInterestRate;
    @BindView(R.id.a_secondApplicationLoan_tvTime)
    TextView tvTime;
    @BindView(R.id.a_secondApplicationLoan_tvOutMoney)
    TextView tvOutMoney;
    @BindView(R.id.a_secondApplicationLoan_etDescription)
    EditText etDescription;

    @BindView(R.id.a_secondApplicationLoan_llIsSfyyq)
    LinearLayout llIsSfyyq;
    @BindView(R.id.a_secondApplicationLoan_llCxzType)
    LinearLayout llCxzType;

    @BindView(R.id.a_secondCarApplicationLoan_tvRepaymentTime)
    TextView tvRepaymentTime;
    @BindView(R.id.a_secondCarApplicationLoan_tvRepaymentType)
    TextView tvRepaymentType;

    List<String> list, listNumber, listPoint;
    int type, rateType, fiveType, manyType;
    WheelAdapter wheelAdapter;
    WheelAdapter rateAdapter;
    TimePickerView timePickerView;

    String cxz, ajyh, sywk, xydz, sqje, minJsll, jsll, dkqx, sfyyq, yqx, yqsj, je, qtzcsm, payTime, monthpay;

    List<CheckedData> needMaterials;
    BCheckedAdapter bCheckedAdapter;

    BCheckedAdapter needTypeAdapter;
    List<CheckedData> needType;

    public static void start(Activity context, CarLoanFirst carLoanFirst) {
        Intent intent = new Intent(context, CarApplicationSecondActivity.class);
        intent.putExtra(Contact.CAR_LOAN_FIRST, carLoanFirst);
        context.startActivityForResult(intent, 24);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_application_second_activity;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initTimeOptionsPicker();
        initMoneyDialog();
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
        needMaterials.add(new CheckedData("3个月", false));
        needMaterials.add(new CheckedData("6个月", false));
        needMaterials.add(new CheckedData("一年一转", false));
        needMaterials.add(new CheckedData("三年一转", false));
        needMaterials.add(new CheckedData("五年一转", false));
        needMaterials.add(new CheckedData("随借随还", false));
        needMaterials.add(new CheckedData("10年", false));
        needMaterials.add(new CheckedData("20年", false));
        needMaterials.add(new CheckedData("30年", false));
        bCheckedAdapter = new BCheckedAdapter(needMaterials);

        needType = new ArrayList<>();
        needType.add(new CheckedData("等额本息", false));
        needType.add(new CheckedData("先息后本", false));
        needType.add(new CheckedData("随借随还", false));
        needTypeAdapter = new BCheckedAdapter(needType);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_home_cdsqFirst_toolbarTitle);
        initThisToolbarHaveBack(toolbar, this);
    }

    //车状况
    @OnClick(R.id.a_secondApplicationLoan_llCxz)
    public void selectCxz() {
        type = 1;
        list.clear();
        list.add("按揭");
        list.add("全款");
        pickerView(list);
    }

    //剩余尾款
    @OnClick(R.id.a_secondApplicationLoan_llSywk)
    public void sywk() {
        rateType = 3;
        showRate();
    }


    //按揭银行
    @OnClick(R.id.a_secondApplicationLoan_llAjyh)
    public void selectAjyh() {
        type = 2;
        list.clear();
        list = new GetJsonDataUtil().initJsonBank(this);
        pickerView(list);
    }

    //需要垫资
    @OnClick(R.id.a_secondApplicationLoan_llXydz)
    public void selectXydz() {
        type = 3;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);
    }

    //申请金额
    @OnClick(R.id.a_secondApplicationLoan_llApplicationMoney)
    public void selectApplicationMoney() {
        showDialog();
    }

    //最大利率
    @OnClick(R.id.a_secondCarApplicationLoan_tvMaxInterestRate)
    public void maxInterestRate() {
        rateType = 2;
        showRate();
    }

    //还款周期
    @OnClick(R.id.a_secondCarApplicationLoan_llRepaymentTime)
    public void llRepaymentTime() {
        manyType = 1;
        showBottomCheckedDialog(bCheckedAdapter);
    }

    //还款方式
    @OnClick(R.id.a_secondCarApplicationLoan_llRepaymentType)
    public void llRepaymentType() {
        manyType = 2;
        showBottomCheckedDialog(needTypeAdapter);
    }


    //贷款期限
    @OnClick(R.id.a_secondApplicationLoan_llDkqx)
    public void selectDkqx() {
        initMouthOptionsPicker();
    }

    //是否有逾期
    @OnClick(R.id.a_secondApplicationLoan_llSfyyq)
    public void selectSfyyq() {
        type = 4;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);
    }

    //逾期时间
    @OnClick(R.id.a_secondApplicationLoan_llTime)
    public void yqTime() {
        if (timePickerView != null) {
            timePickerView.show();
        }
    }

    //逾期金额
    @OnClick(R.id.a_secondApplicationLoan_tvOutMoney)
    public void outMoney() {
        fiveType = 2;
        showFiveDialog();
    }

    //预期项
    @OnClick(R.id.a_secondApplicationLoan_llExpectation)
    public void selectExpectation() {
        type = 5;
        list.clear();
        list.add("信用卡");
        list.add("贷款");
        pickerView(list);
    }

    //确认提交
    @OnClick(R.id.a_secondApplicationLoan_btSubmit)
    public void application() {
        if (!getInput()) {
            CarLoanFirst carLoanFirst = (CarLoanFirst) getIntent().getSerializableExtra(Contact.CAR_LOAN_FIRST);
            CarLoanSecond carLoanSecond = new CarLoanSecond(cxz, ajyh, sywk, xydz, sqje, minJsll, jsll, dkqx, sfyyq, yqx,
                    yqsj, je, qtzcsm, payTime, monthpay);
            InformationPerfectActivity.start(this, carLoanFirst, carLoanSecond);
        }

    }

    public boolean getInput() {
        cxz = tvCxz.getText().toString();
        if (StringUtils.isEmpty(cxz)) {
            ToastUtil.showInfo("请选择车现状！");
            return true;
        }
        if (cxz.equals("按揭")) {
            ajyh = tvAjyh.getText().toString();
            if (StringUtils.isEmpty(ajyh)) {
                ToastUtil.showInfo("请选择按揭银行！");
                return true;
            }
            sywk = tvSywk.getText().toString();
            if (StringUtils.isEmpty(sywk)) {
                ToastUtil.showInfo("请选择剩余尾款！");
                return true;
            }
            xydz = tvXydz.getText().toString();
            if (StringUtils.isEmpty(xydz)) {
                ToastUtil.showInfo("请选择需要垫资！");
                return true;
            }
        }

        sqje = tvApplicationMoney.getText().toString();
        if (StringUtils.isEmpty(sqje)) {
            ToastUtil.showInfo("请选择申请金额！");
            return true;
        }
        minJsll = tvMinInterestRate.getText().toString().trim();
        if (StringUtils.isEmpty(minJsll)) {
            ToastUtil.showInfo("请选择最小接受利率！");
            return true;
        }
        jsll = tvMaxInterestRate.getText().toString().trim();
        if (StringUtils.isEmpty(jsll)) {
            ToastUtil.showInfo("请选择最大接受利率！");
            return true;
        }
        if (Double.valueOf(jsll) < 4.7 || Double.valueOf(jsll) > 15) {
            ToastUtil.showInfo("最大利率范围为：4.7~15！");
            return true;
        }

        payTime = tvRepaymentTime.getText().toString();
        if (StringUtils.isEmpty(payTime)) {
            ToastUtil.showInfo("请选择还款周期！");
            return true;
        }

        monthpay = tvRepaymentType.getText().toString();
        if (StringUtils.isEmpty(monthpay)) {
            ToastUtil.showInfo("请选择还款方式！");
            return true;
        }

        dkqx = tvDkqx.getText().toString();
        if (StringUtils.isEmpty(dkqx)) {
            ToastUtil.showInfo("请选择贷款期限！");
            return true;
        }
        sfyyq = tvSfyyq.getText().toString();
        if (StringUtils.isEmpty(sfyyq)) {
            ToastUtil.showInfo("请选择是否有逾期！");
            return true;
        }
        switch (sfyyq){
            case "是":
                sfyyq = "1";
                break;
            case "否":
                sfyyq = "0";
                break;
            default:
                break;
        }
        if (sfyyq.equals("1")) {
            yqx = tvExpectation.getText().toString();
            if (StringUtils.isEmpty(yqx)) {
                ToastUtil.showInfo("请选择逾期项！");
                return true;
            }
            yqsj = tvTime.getText().toString();
            if (StringUtils.isEmpty(yqsj)) {
                ToastUtil.showInfo("请选择逾期时间！");
                return true;
            }
            je = tvOutMoney.getText().toString();
            if (StringUtils.isEmpty(je)) {
                ToastUtil.showInfo("请选择逾期金额！");
                return true;
            }
        }

        qtzcsm = etDescription.getText().toString();
        return false;
    }

    public void pickerView(final List<String> list) {
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String a = list.get(options1);
                switch (type) {
                    case 1:
                        tvCxz.setText(a);
                        switch (a) {
                            case "按揭":
                                llCxzType.setVisibility(View.VISIBLE);
                                break;
                            case "全款":
                                llCxzType.setVisibility(View.GONE);
                                break;
                            default:
                                break;
                        }
                        break;
                    case 2:
                        tvAjyh.setText(a);
                        break;
                    case 3:
                        tvXydz.setText(a);
                        break;
                    case 4:
                        tvSfyyq.setText(a);
                        if (a.equals("是")) {
                            llIsSfyyq.setVisibility(View.VISIBLE);
                        } else if (a.equals("否")) {
                            llIsSfyyq.setVisibility(View.GONE);
                        }
                        break;
                    case 5:
                        tvExpectation.setText(a);
                        break;
                    default:
                        break;
                }
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
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String str = listNumber.get(options1) + listNumber.get(options2) + listNumber.get(options3);
                String newStr = Kb260Utils.removeFrontZero(str);
                tvDkqx.setText(newStr);
            }
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        pvNoLinkOptions.setNPicker(listNumber, listNumber, listNumber);
        pvNoLinkOptions.show();
    }

    public void showDialog() {
        KBDialog.init()
                .setLayoutId(R.layout.bottom_money)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        final WheelView wv1 = holder.getView(R.id.b_options1);
                        final WheelView wv2 = holder.getView(R.id.b_options2);
                        final WheelView wv3 = holder.getView(R.id.b_options3);
                        final WheelView wv4 = holder.getView(R.id.b_options4);

                        wv1.setAdapter(wheelAdapter);
                        wv2.setAdapter(wheelAdapter);
                        wv3.setAdapter(wheelAdapter);
                        wv4.setAdapter(wheelAdapter);
                        wv1.setCurrentItem(0);
                        wv2.setCurrentItem(0);
                        wv3.setCurrentItem(0);
                        wv4.setCurrentItem(0);

                        holder.setOnClickListener(R.id.b_btnSubmit, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();

                                String a = String.valueOf(wv1.getCurrentItem()) + String.valueOf(wv2.getCurrentItem())
                                        + String.valueOf(wv3.getCurrentItem()) + String.valueOf(wv4.getCurrentItem());
                                String newStr = a.replaceFirst("^0*", "");
                                tvApplicationMoney.setText(newStr);
                            }
                        });
                        holder.setOnClickListener(R.id.b_btnCancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
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
                        final WheelView wv1 = holder.getView(R.id.b_options1);
                        final WheelView wv2 = holder.getView(R.id.b_options2);
                        final WheelView wv3 = holder.getView(R.id.b_options3);
                        final WheelView wv4 = holder.getView(R.id.b_options4);

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

                            String a = "." + String.valueOf(wv4.getCurrentItem());
                            String b = String.valueOf(wv1.getCurrentItem()) + String.valueOf(wv2.getCurrentItem());
                            String newStr = Kb260Utils.removeFrontZero(b);
                            String c = newStr + a;
                            switch (rateType) {
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
                                case 3:
                                    tvSywk.setText(c);
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

    public void showFiveDialog() {
        KBDialog.init()
                .setLayoutId(R.layout.bottom_five)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        final WheelView wv1 = holder.getView(R.id.b_options1);
                        final WheelView wv2 = holder.getView(R.id.b_options2);
                        final WheelView wv3 = holder.getView(R.id.b_options3);
                        final WheelView wv4 = holder.getView(R.id.b_options4);
                        final WheelView wv5 = holder.getView(R.id.b_options5);

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
                                    + String.valueOf(wv3.getCurrentItem()) + String.valueOf(wv4.getCurrentItem())
                                    + String.valueOf(wv5.getCurrentItem());
                            String newStr = a.replaceFirst("^0*", "");
                            switch (fiveType) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Action.FINISH_ACTIVITY) {
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
                        rv.setLayoutManager(new LinearLayoutManager(CarApplicationSecondActivity.this));
                        rv.setAdapter(adapter);

                        List<CheckedData> checkedDatas = adapter.datas;
                        int length = checkedDatas.size();
                        for (int i = 0; i < length; i++) {
                            checkedDatas.get(i).setChecked(false);
                        }

                        holder.setOnClickListener(R.id.b_checked_btnSubmit, view -> {
                            dialog.dismiss();

                            StringBuilder stringBuilder = new StringBuilder();
                            List<CheckedData> checkedDatas1 = adapter.datas;
                            int length1 = checkedDatas1.size();
                            for (int i = 0; i < length1; i++) {
                                if (checkedDatas1.get(i).isChecked()) {
                                    if (stringBuilder.length() != 0) {
                                        stringBuilder.append(",");
                                    }
                                    stringBuilder.append(checkedDatas1.get(i).getName());
                                }
                            }
                            String a = stringBuilder.toString();
                            switch (manyType) {
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
