package com.kb260.gxdk.view.home;

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
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.CarLoanFirst;
import com.kb260.gxdk.entity.JsonBean;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.GetJsonDataUtil;
import com.kb260.gxdk.utils.Kb260Utils;
import com.kb260.gxdk.utils.RegexUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.account.LoginActivity;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.base.FindHouseActivity;
import com.kb260.gxdk.view.base.SelectActivity;
import com.kb260.gxdk.view.base.SelectNewActivity;
import com.kb260.gxdk.view.main.MainActivity;
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

import static com.bigkoo.pickerview.OptionsPickerView.*;

/**
 * @author KB260
 * @date 2017/10/18
 */
public class CarApplicationFirstActivity extends BaseActivity {
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
    @BindView(R.id.a_firstApplicationLoan_tvAddress)
    TextView tvAddress;
    @BindView(R.id.a_firstApplicationLoan_tvIsHaveCar)
    TextView tvIsHaveCar;
    @BindView(R.id.a_firstApplicationLoan_tvIsHaveHouse)
    TextView tvIsHaveHouse;
    @BindView(R.id.a_carFirstApplication_etCjh)
    EditText etCjh;
    @BindView(R.id.a_carFirstApplication_tvCpp)
    TextView tvCpp;
    @BindView(R.id.a_carFirstApplication_tvCp)
    TextView tvCp;
    @BindView(R.id.a_carFirstApplication_tvCl)
    TextView tvCl;
    @BindView(R.id.a_carFirstApplication_tvXsjl)
    TextView tvXsjl;
    @BindView(R.id.a_firstApplicationLoan_etSpouseName)
    EditText etSpouseName;
    @BindView(R.id.a_firstApplicationLoan_etSpousePhone)
    EditText etSpousePhone;

    @BindView(R.id.a_firstApplicationLoan_llIsHaveCarAndHouse)
    LinearLayout llIsHaveCarAndHouse;
    @BindView(R.id.a_firstApplicationLoan_llIsMarriage)
    LinearLayout llIsMarriage;

    @BindView(R.id.a_carFirstApplication_etCp)
    EditText etCp;

    @BindView(R.id.a_firstApplicationLoan_llAddressDetail)
    LinearLayout llAddressDetail;
    @BindView(R.id.a_firstApplicationLoan_etAddress1)
    EditText etAddress1;
    @BindView(R.id.a_firstApplicationLoan_etAddress2)
    EditText etAddress2;
    @BindView(R.id.a_firstApplicationLoan_etAddress3)
    EditText etAddress3;
    @BindView(R.id.a_firstApplicationLoan_etAddress4)
    EditText etAddress4;

    @BindView(R.id.a_carFirstApplication_tvCk)
    TextView tvCk;
    @BindView(R.id.a_carFirstApplication_tvGcsj)
    TextView tvGcsj;
    @BindView(R.id.a_carFirstApplication_tvGcyj)
    TextView tvGcyj;

    @BindView(R.id.a_firstApplicationLoan_llAddressWrite)
    LinearLayout llAddressWrite;
    @BindView(R.id.a_firstApplicationLoan_llAddressPicker)
    LinearLayout llAddressPicker;
    @BindView(R.id.a_firstApplicationLoan_tvAddressHouseDetail)
    TextView tvAddressHouseDetail;

    String jd, xq, z, s;

    List<String> list, listNumber;
    int type, timeType, threeType;

    String name, age, sfz, phone, marry, spouseName, spousePhone, address, cjh, cpp, cp, cl, xsjl, province, city, zone,
            ck, useddate, useddateMonth, gcyj, cppId , communityid ,communityname,workplace,houseDetail;

    GetJsonDataUtil getJsonDataUtil;
    OptionsPickerView pvOptions, pvOptionsCar;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    private ArrayList<JsonBean> options1ItemsCar = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2ItemsCar = new ArrayList<>();

    TimePickerView timePickerView;

    public static void start(Context context) {
        Intent intent = new Intent(context, CarApplicationFirstActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_application_first;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        initAddressDialog();
        initTimeOptionsPicker();
        llIsHaveCarAndHouse.setVisibility(View.GONE);
        list = new ArrayList<>();
        listNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listNumber.add(String.valueOf(i));
        }
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_home_cdsqFirst_toolbarTitle);
        initThisToolbarHaveBack(toolbar, this);
    }

    //选择年龄
    @OnClick(R.id.a_firstApplicationLoan_llAge)
    public void selectAge() {
        timeType = 1;
        initAgeOptionsPicker();
    }

    //选择婚姻状况
    @OnClick(R.id.a_firstApplicationLoan_llMarriage)
    public void selectMarriage() {
        type = 1;
        list.clear();
        list.add("已婚");
        list.add("未婚");
        pickerView(list);
    }

    //选择是否有车
    @OnClick(R.id.a_firstApplicationLoan_llIsHaveCar)
    public void selectIsHaveCar() {
        type = 2;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);
    }

    //选择是否有房
    @OnClick(R.id.a_firstApplicationLoan_llIsHaveHouse)
    public void selectIsHaveHouse() {
        type = 3;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);
    }

    //选择房屋所在地
    @OnClick(R.id.a_firstApplicationLoan_llAddress)
    public void selectAddress() {
        initCityOptionPicker();
    }


    //车品牌
    @OnClick(R.id.a_carFirstApplication_llCpp)
    public void selectCpp() {
        SelectNewActivity.start(this);
        /*type =5;
        list.clear();
        list.add("奔驰");
        list.add("宝马");
        pickerView(list);*/
    }

    //车牌
    @OnClick(R.id.a_carFirstApplication_llCp)
    public void selectCp() {
        initCarOptionPicker();
    }

    //车龄
    @OnClick(R.id.a_carFirstApplication_llCl)
    public void selectCl() {
        timeType = 2;
        initAgeOptionsPicker();
    }

    //行驶距离
    @OnClick(R.id.a_carFirstApplication_llXsjl)
    public void selectXsjl() {
        threeType = 1;
        initDistanceOptionsPicker();

    }

    //车况
    @OnClick(R.id.a_carFirstApplication_llCk)
    public void llCk() {
        type = 8;
        list.clear();
        list.add("较差");
        list.add("一般");
        list.add("优秀");
        pickerView(list);
    }

    //购车时间
    @OnClick(R.id.a_carFirstApplication_llGcsj)
    public void llGcsj() {
        if (timePickerView != null) {
            timePickerView.show();
        }
    }

    //购车原价
    @OnClick(R.id.a_carFirstApplication_llGcyj)
    public void llGcyj() {
        threeType = 2;
        initDistanceOptionsPicker();
    }

    //下一步
    @OnClick(R.id.a_carFirstApplication_btNext)
    public void next() {
        if (!getInput()) {
            CarLoanFirst carLoanFirst = new CarLoanFirst(name, age, sfz, phone, marry, spouseName,
                    spousePhone, houseDetail, cjh, cpp,
                    cp, cl, xsjl, province, city, zone, cppId, ck, gcyj, useddate, useddateMonth,communityid ,
                    communityname,workplace,houseDetail);
            CarApplicationSecondActivity.start(this, carLoanFirst);
        }
    }

    public boolean getInput() {
        name = etName.getText().toString().trim();
        if (StringUtils.isEmpty(name)) {
            ToastUtil.showInfo("请输入姓名！");
            return true;
        }
        age = tvAge.getText().toString().trim();
        if (StringUtils.isEmpty(age)) {
            ToastUtil.showInfo("请选择年龄！");
            return true;
        }
        sfz = etSfz.getText().toString().trim();
        if (StringUtils.isEmpty(sfz)) {
            ToastUtil.showInfo("请输入身份证号码！");
            return true;
        }
        if (!RegexUtils.isIDCard18(sfz)) {
            ToastUtil.showInfo("请输入正确的身份证号码！");
            return true;
        }
        phone = etPhone.getText().toString().trim();
        if (StringUtils.isEmpty(phone)) {
            ToastUtil.showInfo("请输入联系方式！");
            return true;
        }
        if (RegexUtils.isMobileSimple(phone)) {
            ToastUtil.showInfo("请输入正确的手机号码！");
            return true;
        }
        marry = tvMarriage.getText().toString().trim();
        if (StringUtils.isEmpty(marry)) {
            ToastUtil.showInfo("请选择婚姻状况！");
            return true;
        }
        switch (marry) {
            case "已婚":
                marry = "1";
                break;
            case "未婚":
                marry = "0";
                break;
            default:
                break;
        }
        if (marry.equals("1")) {
            spouseName = etSpouseName.getText().toString().trim();
            if (StringUtils.isEmpty(spouseName)) {
                ToastUtil.showInfo("请输入配偶姓名！");
                return true;
            }
            spousePhone = etSpousePhone.getText().toString().trim();
            if (StringUtils.isEmpty(spousePhone)) {
                ToastUtil.showInfo("请输入配偶联系方式！");
                return true;
            }
            if (RegexUtils.isMobileSimple(spousePhone)) {
                ToastUtil.showInfo("请输入正确的手机号码！");
                return true;
            }
        }

        address = tvAddress.getText().toString().trim();
        if (StringUtils.isEmpty(address)) {
            ToastUtil.showInfo("请选择常用地址！");
            return true;
        }
        jd = etAddress1.getText().toString().trim();
        if (StringUtils.isEmpty(jd)) {
            ToastUtil.showInfo("请选择常住地址街道/镇！");
            return true;
        }

        if (StringUtils.isEmpty(tvAddressHouseDetail.toString())){
            xq = etAddress2.getText().toString().trim();
            if (StringUtils.isEmpty(xq)) {
                ToastUtil.showInfo("请选择常住地址小区！");
                return true;
            }
            z = etAddress3.getText().toString().trim();
            if (StringUtils.isEmpty(z)) {
                ToastUtil.showInfo("请选择常住地址幢！");
                return true;
            }
            s = etAddress4.getText().toString().trim();
            if (StringUtils.isEmpty(s)) {
                ToastUtil.showInfo("请选择常住地址室！");
                return true;
            }
            houseDetail = address + jd + "街道/镇" + xq + "小区" + z + "幢" + s + "室";
        }else {
            houseDetail = address + jd + "街道/镇" +tvAddressHouseDetail.getText().toString();
        }

        cjh = etCjh.getText().toString().trim();
        if (StringUtils.isEmpty(cjh)) {
            ToastUtil.showInfo("请输入车架号！");
            return true;
        }
        if (cjh.length() != 17) {
            ToastUtil.showInfo("请输入17位车架号！");
            return true;
        }

        cpp = tvCpp.getText().toString();
        if (StringUtils.isEmpty(cppId) || StringUtils.isEmpty(cpp)) {
            ToastUtil.showInfo("请选择车品牌！");
            return true;
        }

        cp = tvCp.getText().toString().trim();
        if (StringUtils.isEmpty(cp)) {
            ToastUtil.showInfo("请输入车牌前缀！");
            return true;
        }
        /*if (!RegexUtils.isIDCar(cp)){
            ToastUtil.showInfo("请输入正确车牌前缀（类似浙A）！");
            return true;
        }*/

        cl = tvCl.getText().toString();
        if (StringUtils.isEmpty(cl)) {
            ToastUtil.showInfo("请选择车龄！");
            return true;
        }
        xsjl = tvXsjl.getText().toString();
        if (StringUtils.isEmpty(xsjl)) {
            ToastUtil.showInfo("请选择行驶距离！");
            return true;
        }

        ck = tvCk.getText().toString().trim();
        if (StringUtils.isEmpty(ck)) {
            ToastUtil.showInfo("请选择车况！");
            return true;
        }
        switch (ck) {
            case "优秀":
                ck = "1";
                break;
            case "一般":
                ck = "2";
                break;
            case "较差":
                ck = "3";
                break;
            default:
                break;
        }

        if (StringUtils.isEmpty(useddate) || StringUtils.isEmpty(useddateMonth)) {
            ToastUtil.showInfo("请选择购车时间！");
            return true;
        }

        gcyj = tvGcyj.getText().toString().trim();
        if (StringUtils.isEmpty(gcyj)) {
            ToastUtil.showInfo("请选择购车原价！");
            return true;
        }
        return false;
    }

    public void pickerView(final List<String> list) {
        OptionsPickerView optionsPickerView = new Builder(this, (options1, options2, options3, v) -> {
            switch (list.get(options1)) {
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
            switch (type) {
                case 1:
                    tvMarriage.setText(a);
                    break;
                case 2:
                    tvIsHaveCar.setText(a);
                    break;
                case 3:
                    tvIsHaveHouse.setText(a);
                    break;
                /*case 4:
                    tvCjh.setText(a);
                    break;*/
                case 5:
                    tvCpp.setText(a);
                    break;
                case 6:
                    tvCp.setText(a);
                    break;
                case 7:
                    tvXsjl.setText(a);
                    break;
                case 8:
                    tvCk.setText(a);
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
        OptionsPickerView pvNoLinkOptions = new Builder(this, (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2);
            String newStr = Kb260Utils.removeFrontZero(str);
            switch (timeType) {
                case 1:
                    tvAge.setText(newStr);
                    break;
                case 2:
                    tvCl.setText(newStr);
                    break;
                default:
                    break;
            }
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        pvNoLinkOptions.setNPicker(listNumber, listNumber, null);
        pvNoLinkOptions.show();
    }

    private void initCityOptionPicker() {//条件选择器初始化
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            if (getJsonDataUtil.isLoaded) {
                e.onNext(1);
            } else {
                getJsonDataUtil.initJsonData(CarApplicationFirstActivity.this, options1Items,
                        options2Items, options3Items, e);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
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
            String tx = province + " " + city + " " + zone;
            tvAddress.setText(tx);
            llAddressDetail.setVisibility(View.VISIBLE);
            llAddressPicker.setVisibility(View.VISIBLE);
            llAddressWrite.setVisibility(View.GONE);
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();

        pvOptionsCar = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            String a = options1ItemsCar.get(options1).getPickerViewText();
            String b = options2ItemsCar.get(options1).get(options2);
            String tx = a + b;
            tvCp.setText(tx);
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
    }

    /**
     * 行驶距离
     */
    private void initDistanceOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new Builder(this, (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2) + listNumber.get(options3);
            String newStr = Kb260Utils.removeFrontZero(str);

            switch (threeType) {
                case 1:
                    tvXsjl.setText(newStr);
                    break;
                case 2:
                    tvGcyj.setText(newStr);
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

    private void initTimeOptionsPicker() {// 不联动的多级选项
        timePickerView = new TimePickerView.Builder(this, (date, v) -> {
            useddate = Kb260Utils.getTimeY(date);
            useddateMonth = Kb260Utils.getTimeM(date);
            tvGcsj.setText(Kb260Utils.getTimeYM(date));
        }).setType(new boolean[]{true, true, false, false, false, false})
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
        if (resultCode == 25) {
            String a = data.getStringExtra(Action.SELECT_CAR);
            cppId = data.getStringExtra(Action.SELECT_CAR_ID);
            tvCpp.setText(a);
        } else if (resultCode == Action.FINISH_ACTIVITY) {
            finish();
        }else if (resultCode == Action.FIND_HOUSE_1){
            llAddressPicker.setVisibility(View.GONE);
            llAddressWrite.setVisibility(View.VISIBLE);
            xq = data.getStringExtra(Action.XQ);
            etAddress2.setText(xq);

        }else if (resultCode == Action.FIND_HOUSE){
            llAddressPicker.setVisibility(View.VISIBLE);
            llAddressWrite.setVisibility(View.GONE);

            communityid = data.getStringExtra(Action.COMMUNITYID);
            communityname = data.getStringExtra(Action.COMMUNITYNAME);
            workplace = data.getStringExtra(Action.WORKPLACE);
            String a = data.getStringExtra(Action.HOUSE_DETAIL);
            tvAddressHouseDetail.setText(a);
        }
    }

    private void initCarOptionPicker() {//条件选择器初始化
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            if (getJsonDataUtil.isCarLoaded) {
                e.onNext(1);
            } else {
                getJsonDataUtil.initCarJsonData(CarApplicationFirstActivity.this, options1ItemsCar,
                        options2ItemsCar, e);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        pvOptionsCar.setPicker(options1ItemsCar, options2ItemsCar);//三级选择器
                        pvOptionsCar.show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick(R.id.a_firstApplicationLoan_llAddressPicker)
    public void pickerHouse(){
        FindHouseActivity.start(this,province,city,zone,Action.FIND_HOUSE_ADDRESS);
    }

    private void showHouse() {
        KBDialog.init()
                .setLayoutId(R.layout.two_select)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setOnClickListener(R.id.twoSelect_xz, v -> {
                            llAddressDetail.setVisibility(View.GONE);
                            FindHouseActivity.start(CarApplicationFirstActivity.this,province,city,zone,24);
                            dialog.dismiss();
                        });
                        holder.setOnClickListener(R.id.twoSelect_sd, v -> {
                                    llAddressDetail.setVisibility(View.VISIBLE);
                                    dialog.dismiss();
                                }

                        );
                    }
                })
                .setMargin(60)
                .show(getSupportFragmentManager());
    }
}
