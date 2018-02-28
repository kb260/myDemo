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
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.JsonBean;
import com.kb260.gxdk.entity.MortgageFirst;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.GetJsonDataUtil;
import com.kb260.gxdk.utils.Kb260Utils;
import com.kb260.gxdk.utils.RegexUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.base.FindHouseActivity;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;

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

/**
 * @author  KB260
 */
public class FirstApplicationLoanActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_firstApplicationLoan_etName)
    EditText etName;
    @BindView(R.id.a_firstApplicationLoan_tvAge)
    TextView tvAge;
    @BindView(R.id.a_firstApplicationLoan_etSfz)
    EditText etSfz;
    @BindView(R.id.a_firstApplicationLoan_etPhone)
    EditText etPhone;
    @BindView(R.id.a_firstApplicationLoan_tvMarriage)
    TextView tvMarriage;
    @BindView(R.id.a_firstApplicationLoan_etSpouseName)
    EditText etSpouseName;
    @BindView(R.id.a_firstApplicationLoan_etSpousePhone)
    EditText etSpousePhone;
    @BindView(R.id.a_firstApplicationLoan_tvAddress)
    TextView tvAddress;
    @BindView(R.id.a_firstApplicationLoan_tvHouseAddress)
    TextView tvHouseAddress;
    @BindView(R.id.a_firstApplicationLoan_tvHouseArea)
    TextView tvHouseArea;
    @BindView(R.id.a_firstApplicationLoan_tvHouseNature)
    TextView tvHouseNature;
    @BindView(R.id.a_firstApplicationLoan_tvHouseStatus)
    TextView tvHouseStatus;
    @BindView(R.id.a_firstApplicationLoan_tvIsHaveCar)
    TextView tvIsHaveCar;
    @BindView(R.id.a_firstApplicationLoan_tvIsHaveHouse)
    TextView tvIsHaveHouse;
    @BindView(R.id.a_firstApplicationLoan_llIsHaveCarAndHouse)
    LinearLayout llIsHaveCarAndHouse;
    @BindView(R.id.a_firstApplicationLoan_llIsMarriage)
    LinearLayout llIsMarriage;
    @BindView(R.id.a_firstApplicationLoan_llHouseStatusType)
    LinearLayout llHouseStatusType;
    @BindView(R.id.a_firstApplicationLoan_tvBank)
    TextView tvBank;
    @BindView(R.id.a_firstApplicationLoan_tvSywk)
    TextView tvSywk;

    @BindView(R.id.a_firstApplicationLoan_llAddressDetail)
    LinearLayout llAddressDetail;
    @BindView(R.id.a_firstApplicationLoan_llHouseAddressDetail)
    LinearLayout llHouseAddressDetail;
    @BindView(R.id.a_firstApplicationLoan_etAddress1)
    EditText etAddress1;
    @BindView(R.id.a_firstApplicationLoan_etAddress2)
    EditText etAddress2;
    @BindView(R.id.a_firstApplicationLoan_etAddress3)
    EditText etAddress3;
    @BindView(R.id.a_firstApplicationLoan_etAddress4)
    EditText etAddress4;
    @BindView(R.id.a_firstApplicationLoan_etHouseAddress1)
    EditText etHouseAddress1;
    @BindView(R.id.a_firstApplicationLoan_etHouseAddress2)
    EditText etHouseAddress2;
    @BindView(R.id.a_firstApplicationLoan_etHouseAddress3)
    EditText etHouseAddress3;
    @BindView(R.id.a_firstApplicationLoan_etHouseAddress4)
    EditText etHouseAddress4;

    @BindView(R.id.a_secondApplicationLoan_llSfdz)
    LinearLayout llSfdz;
    @BindView(R.id.a_secondApplicationLoan_tvSfdz)
    TextView tvSfdz;

    @BindView(R.id.a_firstApplicationLoan_tvHouseAge)
    TextView tvHouseAge;

    @BindView(R.id.a_firstApplicationLoan_llAddressWrite)
    LinearLayout llAddressWrite;
    @BindView(R.id.a_firstApplicationLoan_llAddressPicker)
    LinearLayout llAddressPicker;
    @BindView(R.id.a_firstApplicationLoan_tvAddressHouseDetail)
    TextView tvAddressHouseDetail;

    @BindView(R.id.a_firstApplicationLoan_llHouseAddressWrite)
    LinearLayout llHouseAddressWrite;
    @BindView(R.id.a_firstApplicationLoan_llHouseAddressPicker)
    LinearLayout llHouseAddressPicker;
    @BindView(R.id.a_firstApplicationLoan_tvHouseAddressHouseDetail)
    TextView tvHouseAddressHouseDetail;

    int type,fourType,twoType;
    List<String> list,listNumber;
    String name,age,sfz,phone,marry,spouseName,spousePhone,address,houseAddress,houseArea,houseNature,houseStatus
            ,province,city,zone,bank,sywk,sfdz,houseAge,communityid , communityname,workplace,houseDetail,
            provinceAddress,cityAddress,zoneAddress;

    String jd,xq,z,s;
    String jdHouse,xqHouse,zHouse,sHouse;

    WheelAdapter wheelAdapter;
    GetJsonDataUtil getJsonDataUtil;
    OptionsPickerView  pvOptions;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    public static void start(Context context){
        Intent intent = new Intent(context,FirstApplicationLoanActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_first_application_loan;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initAddressDialog();

        llIsHaveCarAndHouse.setVisibility(View.GONE);
        list = new ArrayList<>();
        listNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listNumber.add(String.valueOf(i));
        }
        wheelAdapter = new ArrayWheelAdapter(listNumber);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.f_home_fdsq_toolbarTitle);
        initThisToolbarHaveBack(toolbar,this);
    }

    //选择年龄
    @OnClick(R.id.a_firstApplicationLoan_llAge)
    public void selectAge(){
        twoType = 1;
        initAgeOptionsPicker();
    }

    //选择婚姻状况
    @OnClick(R.id.a_firstApplicationLoan_llMarriage)
    public void selectMarriage(){
        type = 1;
        list.clear();
        list.add("已婚");
        list.add("未婚");
        pickerView(list);
    }

    //选择是否有车
    @OnClick(R.id.a_firstApplicationLoan_llIsHaveCar)
    public void selectIsHaveCar(){
        type = 2;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);
    }

    //选择是否有房
    @OnClick(R.id.a_firstApplicationLoan_llIsHaveHouse)
    public void selectIsHaveHouse(){
        type = 3;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);
    }

    //选择常住地址
    @OnClick(R.id.a_firstApplicationLoan_llAddress)
    public void selectAddress(){
        type = 1;
        initCityOptionPicker();
    }

    //选择房屋所在地
    @OnClick(R.id.a_firstApplicationLoan_llHouseAddress)
    public void selectHouseAddress(){
        type = 2;
        initCityOptionPicker();
    }

    //选择房屋面积
    @OnClick(R.id.a_firstApplicationLoan_llHouseArea)
    public void selectHouseArea(){
        fourType = 2;
        showDialog();
    }

    //选择房屋性质选择房屋现状
    @OnClick(R.id.a_firstApplicationLoan_llHouseStatus)
    public void selectHouseStatus(){
        type = 5;
        list.clear();
        list.add("全款");
        list.add("按揭");
        pickerView(list);
    }

    //按揭银行
    @OnClick(R.id.a_firstApplicationLoan_llBank)
    public void selectBank(){
        type = 6;
        list.clear();
        list = new GetJsonDataUtil().initJsonBank(this);
        pickerView(list);
    }

    //选择房屋性质选择房屋现状
    @OnClick(R.id.a_firstApplicationLoan_llHouseNature)
    public void selectHouseNature(){
        type = 4;
        list.clear();
        list.add("住宅");
        list.add("酒店式公寓");
        list.add("别墅");
        list.add("排屋");
        list.add("商铺");
        list.add("写字楼");
        list.add("厂房");
        list.add("土地");
        pickerView(list);
    }

    //剩余尾款
    @OnClick(R.id.a_firstApplicationLoan_llSywk)
    public void sywk(){
        fourType = 1;
        showDialog();
    }

    //需要垫资
    @OnClick(R.id.a_secondApplicationLoan_llSfdz)
    public void llSfdz(){
        type =7;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);
    }

    //房龄
    @OnClick(R.id.a_firstApplicationLoan_llHouseAge)
    public void llHouseAge(){
        twoType = 2;
        initAgeOptionsPicker();
    }

    //下一步
    @OnClick(R.id.a_firstApplicationLoan_btNext)
    public void next(){
        if (!getInput()){
            MortgageFirst mortgageFirst = new MortgageFirst(name,age,sfz,phone,marry,spouseName,spousePhone,address,
                    houseAddress,houseArea,houseNature, houseStatus,province,city,zone,bank,sywk,sfdz,
                    houseAge,communityid , communityname,workplace,houseDetail);
            SecondApplicationLoanActivity.start(this,mortgageFirst);
        }
    }

    public boolean getInput(){
        name = etName.getText().toString().trim();
        if (StringUtils.isEmpty(name)){
            ToastUtil.showInfo("请输入姓名！");
            return true;
        }

        age = tvAge.getText().toString();
        if (StringUtils.isEmpty(age)){
            ToastUtil.showInfo("请选择年龄！");
            return true;
        }
        sfz = etSfz.getText().toString().trim();
        if (StringUtils.isEmpty(sfz)){
            ToastUtil.showInfo("请输入身份证号码！");
            return true;
        }
        if (!RegexUtils.isIDCard18(sfz)){
            ToastUtil.showInfo("请输入正确的身份证号码！");
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

        marry = tvMarriage.getText().toString();
        if (StringUtils.isEmpty(marry)){
            ToastUtil.showInfo("请选择婚姻状况！");
            return true;
        }

        switch (marry){
            case "已婚":
                marry = "1";
                break;
            case "未婚":
                marry = "0";
                break;
            default:
                break;
        }

        if (marry.equals("1")){
            spouseName = etSpouseName.getText().toString().trim();
            if (StringUtils.isEmpty(spouseName)){
                ToastUtil.showInfo("请输入配偶姓名！");
                return true;
            }
            spousePhone = etSpousePhone.getText().toString().trim();
            if (StringUtils.isEmpty(spousePhone)){
                ToastUtil.showInfo("请输入配偶联系方式！");
                return true;
            }
        }

        address = tvAddress.getText().toString();
        if (StringUtils.isEmpty(address)){
            ToastUtil.showInfo("请选择常住地址！");
            return true;
        }
        jd = etAddress1.getText().toString().trim();
        if (StringUtils.isEmpty(jd)){
            ToastUtil.showInfo("请选择常住地址街道/镇！");
            return true;
        }

        if (StringUtils.isEmpty(tvAddressHouseDetail.toString())){
            xq = etAddress2.getText().toString().trim();
            if (StringUtils.isEmpty(xq)){
                ToastUtil.showInfo("请选择常住地址小区！");
                return true;
            }
            z = etAddress3.getText().toString().trim();
            if (StringUtils.isEmpty(z)){
                ToastUtil.showInfo("请选择常住地址幢！");
                return true;
            }
            s = etAddress4.getText().toString().trim();
            if (StringUtils.isEmpty(s)){
                ToastUtil.showInfo("请选择常住地址室！");
                return true;
            }
            address = address + jd + "街道/镇" + xq + "小区" + z + "幢" + s + "室";
        }else {
            address = address + jd + "街道/镇" +tvAddressHouseDetail.getText().toString();
        }

        houseAddress = tvHouseAddress.getText().toString();
        if (StringUtils.isEmpty(houseAddress)){
            ToastUtil.showInfo("请选择房子所在地！");
            return true;
        }

        jdHouse = etHouseAddress1.getText().toString().trim();
        if (StringUtils.isEmpty(jdHouse)){
            ToastUtil.showInfo("请选择房子所在地街道/镇！");
            return true;
        }

        if (StringUtils.isEmpty(tvAddressHouseDetail.toString())){
            xqHouse = etHouseAddress2.getText().toString().trim();
            if (StringUtils.isEmpty(xqHouse)){
                ToastUtil.showInfo("请选择房子所在地小区！");
                return true;
            }
            zHouse = etHouseAddress3.getText().toString().trim();
            if (StringUtils.isEmpty(zHouse)){
                ToastUtil.showInfo("请选择房子所在地幢！");
                return true;
            }
            sHouse = etHouseAddress4.getText().toString().trim();
            if (StringUtils.isEmpty(sHouse)){
                ToastUtil.showInfo("请选择房子所在地室！");
                return true;
            }
            houseAddress = houseAddress + jdHouse + "街道/镇" + xqHouse + "小区" + zHouse + "幢" + sHouse + "室";
        }else {
            houseAddress = houseAddress + jdHouse + "街道/镇" +tvHouseAddressHouseDetail.getText().toString();
        }

        houseArea = tvHouseArea.getText().toString();
        if (StringUtils.isEmpty(houseArea)){
            ToastUtil.showInfo("请选择房屋面积！");
            return true;
        }

        houseNature = tvHouseNature.getText().toString();
        if (StringUtils.isEmpty(houseNature)){
            ToastUtil.showInfo("请选择房屋性质！");
            return true;
        }

        houseStatus = tvHouseStatus.getText().toString();
        if (StringUtils.isEmpty(houseStatus)){
            ToastUtil.showInfo("请选择房屋现状！");
            return true;
        }
        if (houseStatus.equals("按揭")){
            bank = tvBank.getText().toString();
            if (StringUtils.isEmpty(bank)){
                ToastUtil.showInfo("请选择按揭银行！");
                return true;
            }
            sywk = tvSywk.getText().toString().trim();
            if (StringUtils.isEmpty(sywk)){
                ToastUtil.showInfo("请选择剩余尾款！");
                return true;
            }
            sfdz = tvSfdz.getText().toString();
            if (StringUtils.isEmpty(sfdz)){
                ToastUtil.showInfo("请选择是否需要垫资！");
                return true;
            }
        }

        houseAge = tvHouseAge.getText().toString();
        if (StringUtils.isEmpty(houseAge)){
            ToastUtil.showInfo("请选择房龄！");
            return true;
        }
        return false;
    }

    public void pickerView(final List<String> list){
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            switch (list.get(options1)){
                case "已婚":
                    llIsMarriage.setVisibility(View.VISIBLE);
                    tvMarriage.setText("已婚");
                    break;
                case "未婚":
                    llIsMarriage.setVisibility(View.GONE);
                    tvMarriage.setText("未婚");
                    break;
                default:
                    break;
            }
            String a = list.get(options1);
            switch (type){
                case 1:
                    tvMarriage.setText(a);
                    break;
                case 2:
                    tvIsHaveCar.setText(a);
                    break;
                case 3:
                    tvIsHaveHouse.setText(a);
                    break;
                case 4:
                    tvHouseNature.setText(a);
                    break;
                case 5:
                    tvHouseStatus.setText(a);
                    switch (a){
                        case "按揭":
                            llHouseStatusType.setVisibility(View.VISIBLE);
                            break;
                        case "全款":
                            llHouseStatusType.setVisibility(View.GONE);
                            break;
                        default:
                            break;
                    }
                    break;
                case 6:
                    tvBank.setText(a);
                    break;
                case 7:
                    tvSfdz.setText(a);
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

    private void initAgeOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2);
            String newStr = Kb260Utils.removeFrontZero(str);
            switch (twoType){
                case 1:
                    tvAge.setText(newStr);
                    break;
                case 2:
                    tvHouseAge.setText(newStr);
                    break;
                default:
                    break;
            }

        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        pvNoLinkOptions.setNPicker(listNumber, listNumber,null);
        pvNoLinkOptions.show();
    }

    private void initAreaOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2) + listNumber.get(options3);
            String newStr = Kb260Utils.removeFrontZero(str)+ "平方米";
            tvHouseArea.setText(newStr);
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
                getJsonDataUtil.initJsonData(FirstApplicationLoanActivity.this,options1Items,options2Items,options3Items,e);
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
            switch (type){
                case 1:
                    provinceAddress = province;
                    cityAddress = city;
                    zoneAddress = zone;
                    tvAddress.setText(tx);
                    llAddressDetail.setVisibility(View.VISIBLE);
                    llAddressPicker.setVisibility(View.VISIBLE);
                    llAddressWrite.setVisibility(View.GONE);
                    break;
                case 2:
                    //返回的分别是三个级别的选中位置
                    this.province = province;
                    this.city = city;
                    this.zone = zone;
                    tvHouseAddress.setText(tx);
                    llHouseAddressDetail.setVisibility(View.VISIBLE);
                    llHouseAddressPicker.setVisibility(View.VISIBLE);
                    llHouseAddressWrite.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }

        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
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
                            String newStr = Kb260Utils.removeFrontZero(a);
                            switch (fourType){
                                case 1:
                                    tvSywk.setText(newStr);
                                    break;
                                case 2:
                                    tvHouseArea.setText(newStr);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Action.FINISH_ACTIVITY){
            finish();
        }else if (resultCode == Action.FIND_HOUSE_1){
            switch (requestCode){
                case Action.FIND_HOUSE_ADDRESS:
                    llAddressPicker.setVisibility(View.GONE);
                    llAddressWrite.setVisibility(View.VISIBLE);
                    xq = data.getStringExtra(Action.XQ);
                    etAddress2.setText(xq);
                    break;
                case Action.FIND_HOUSE_HOUSE:
                    llHouseAddressPicker.setVisibility(View.GONE);
                    llHouseAddressWrite.setVisibility(View.VISIBLE);
                    xqHouse = data.getStringExtra(Action.XQ);
                    etHouseAddress2.setText(xqHouse);
                    break;
                default:
                    break;

            }


        }else if (resultCode == Action.FIND_HOUSE){
            switch (requestCode){
                case Action.FIND_HOUSE_ADDRESS:
                    llAddressPicker.setVisibility(View.VISIBLE);
                    llAddressWrite.setVisibility(View.GONE);

                    String a = data.getStringExtra(Action.HOUSE_DETAIL);
                    tvAddressHouseDetail.setText(a);
                    break;
                case Action.FIND_HOUSE_HOUSE:
                    llHouseAddressPicker.setVisibility(View.VISIBLE);
                    llHouseAddressWrite.setVisibility(View.GONE);

                    communityid = data.getStringExtra(Action.COMMUNITYID);
                    communityname = data.getStringExtra(Action.COMMUNITYNAME);
                    workplace = data.getStringExtra(Action.WORKPLACE);
                    String b = data.getStringExtra(Action.HOUSE_DETAIL);
                    tvHouseAddressHouseDetail.setText(b);
                    break;
                default:
                    break;

            }

        }
    }

    @OnClick(R.id.a_firstApplicationLoan_llAddressPicker)
    public void pickerHouse(){
        FindHouseActivity.start(this,provinceAddress,cityAddress,zoneAddress,Action.FIND_HOUSE_ADDRESS);
    }
    @OnClick(R.id.a_firstApplicationLoan_llHouseAddressPicker)
    public void llHouseAddressPicker(){
        FindHouseActivity.start(this,province,city,zone,Action.FIND_HOUSE_HOUSE);
    }
}
