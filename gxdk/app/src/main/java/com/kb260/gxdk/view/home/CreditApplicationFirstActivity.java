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
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.CreditFirst;
import com.kb260.gxdk.entity.JsonBean;
import com.kb260.gxdk.entity.ProvinceBean;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.GetJsonDataUtil;
import com.kb260.gxdk.utils.Kb260Utils;
import com.kb260.gxdk.utils.RegexUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.base.FindHouseActivity;
import com.kb260.gxdk.view.base.SelectActivity;
import com.kb260.gxdk.view.base.SelectNewActivity;
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
 * @date  2017/10/18
 */
public class CreditApplicationFirstActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_firstApplicationLoan_llSpouseName)
    LinearLayout llSpouseName;
    @BindView(R.id.a_firstApplicationLoan_llSpousePhone)
    LinearLayout llSpousePhone;
    @BindView(R.id.a_firstApplicationLoan_vSpouseName)
    View vSpouseName;
    @BindView(R.id.a_firstApplicationLoan_vSpousePhone)
    View vSpousePhone;

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
    @BindView(R.id.a_firstApplicationLoan_tvIsHaveCar)
    TextView tvIsHaveCar;
    @BindView(R.id.a_firstApplicationLoan_tvIsHaveHouse)
    TextView tvIsHaveHouse;

    @BindView(R.id.a_creditFirst_tvSqje)
    TextView tvSqje;
    @BindView(R.id.a_creditFirst_tvWorkAddress)
    TextView tvWorkAddress;
    @BindView(R.id.a_creditFirst_etIndustry)
    EditText etIndustry;

    @BindView(R.id.carLoan_ll)
    LinearLayout carLoanLl;
    @BindView(R.id.houseLoan_ll)
    LinearLayout houseLoanLl;

    @BindView(R.id.a_firstApplicationLoan_tvHouseAddress)
    TextView tvHouseAddress;
    @BindView(R.id.a_firstApplicationLoan_tvHouseArea)
    TextView tvHouseArea;
    @BindView(R.id.a_firstApplicationLoan_tvHouseNature)
    TextView tvHouseNature;
    @BindView(R.id.a_firstApplicationLoan_tvHouseStatus)
    TextView tvHouseStatus;
    @BindView(R.id.a_firstApplicationLoan_tvHouseAge)
    TextView tvHouseAge;

    @BindView(R.id.a_carFirstApplication_etCjh)
    EditText etCjh;
    @BindView(R.id.a_carFirstApplication_tvCpp)
    TextView tvCpp;
    @BindView(R.id.a_carFirstApplication_etCp)
    EditText etCp;
    @BindView(R.id.a_carFirstApplication_tvCp)
    TextView tvCp;
    @BindView(R.id.a_carFirstApplication_tvCl)
    TextView tvCl;
    @BindView(R.id.a_carFirstApplication_tvXsjl)
    TextView tvXsjl;
    @BindView(R.id.a_firstApplicationLoan_llIsMarriage)
    LinearLayout llIsMarriage;

    @BindView(R.id.a_firstApplicationLoan_llHouseStatusType)
    LinearLayout llHouseStatusType;
    @BindView(R.id.a_firstApplicationLoan_tvBank)
    TextView tvBank;
    @BindView(R.id.a_firstApplicationLoan_tvSywk)
    TextView tvSywk;
    @BindView(R.id.a_secondApplicationLoan_tvSfdz)
    TextView tvSfdz;

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
    @BindView(R.id.a_creditFirst_etCareer)
    EditText etCareer;

    @BindView(R.id.a_carFirstApplication_tvCk)
    TextView tvCk;
    @BindView(R.id.a_carFirstApplication_tvGcsj)
    TextView tvGcsj;
    @BindView(R.id.a_carFirstApplication_tvGcyj)
    TextView tvGcyj;

    @BindView(R.id.a_firstApplicationLoan_llIsHaveCar)
    LinearLayout llIsHaveCar;
    @BindView(R.id.a_firstApplicationLoan_llIsHaveHouse)
    LinearLayout llIsHaveHouse;
    @BindView(R.id.a_firstApplicationLoan_vIsHaveHouse)
    View vIsHaveHouse;
    @BindView(R.id.a_firstApplicationLoan_vIsHaveCar)
    View vIsHaveCar;

    @BindView(R.id.a_creditFirst_vType)
    View vType;
    @BindView(R.id.a_creditFirst_llType)
    LinearLayout llType;
    @BindView(R.id.a_creditFirst_tvType)
    TextView tvType;
    @BindView(R.id.a_creditFirst_tvSqjeKey)
    TextView tvSqjeKey;

    @BindView(R.id.a_firstApplicationLoan_llHouseAddressWrite)
    LinearLayout llHouseAddressWrite;
    @BindView(R.id.a_firstApplicationLoan_llHouseAddressPicker)
    LinearLayout llHouseAddressPicker;
    @BindView(R.id.a_firstApplicationLoan_tvHouseAddressHouseDetail)
    TextView tvHouseAddressHouseDetail;

    @BindView(R.id.a_firstApplicationLoan_llAddressWrite)
    LinearLayout llAddressWrite;
    @BindView(R.id.a_firstApplicationLoan_llAddressPicker)
    LinearLayout llAddressPicker;
    @BindView(R.id.a_firstApplicationLoan_tvAddressHouseDetail)
    TextView tvAddressHouseDetail;


    String jd,xq,z,s;
    String jdHouse,xqHouse,zHouse,sHouse;

    List<String> list,listNumber;
    int type,cityType,timeType,moneyType,threeType,kind;
    WheelAdapter wheelAdapter;
    String name,age,sfz,phone,marry,spouseName,spousePhone,haveCar,haveHouse,address,applicationMoney,workAddress
            ,industry,career,houseAddress,houseArea,houseNature,houseStatus,bank,sywk,cjh,cpp,cp,cl,xsjl,province,
            city,zone,houseAge, ck,useddate,useddateMonth,gcyj,cppId,sfdz,type1,provinceAddress,cityAddress,
            zoneAddress;
    GetJsonDataUtil getJsonDataUtil;
    OptionsPickerView  pvOptions,pvOptionsCar;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private ArrayList<JsonBean> options1ItemsCar = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2ItemsCar = new ArrayList<>();

    String myType;
    TimePickerView timePickerView;

    public static void start(Context context,String type,int kind){
        Intent intent = new Intent(context,CreditApplicationFirstActivity.class);
        intent.putExtra(Contact.TYPE,type);
        intent.putExtra(Contact.KIND,kind);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_credit_application_first;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
        initTimeOptionsPicker();

        list = new ArrayList<>();
        listNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listNumber.add(String.valueOf(i));
        }
        wheelAdapter = new ArrayWheelAdapter(listNumber);
        initAddressDialog();
    }

    private void initIntent() {
        Intent intent = getIntent();
        myType = intent.getStringExtra(Contact.TYPE);
        kind = intent.getIntExtra(Contact.KIND,0);
        switch (myType){
            case "1":
                llIsHaveHouse.setVisibility(View.GONE);
                vIsHaveHouse.setVisibility(View.GONE);
                break;
            case "2":
                llIsHaveCar.setVisibility(View.GONE);
                vIsHaveCar.setVisibility(View.GONE);
                break;
            case "3":
                llIsHaveHouse.setVisibility(View.GONE);
                vIsHaveHouse.setVisibility(View.GONE);
                llIsHaveCar.setVisibility(View.GONE);
                vIsHaveCar.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        switch (kind){
            case 1:
                toolbarTitle.setText(R.string.a_home_xdsqSecond_toolbarTitle);
                vType.setVisibility(View.GONE);
                llType.setVisibility(View.GONE);
                tvSqjeKey.setText("申请额度");
                break;
            case 2:
                toolbarTitle.setText(R.string.home_jqqd);
                vType.setVisibility(View.VISIBLE);
                llType.setVisibility(View.VISIBLE);
                tvSqjeKey.setText("需要垫资金额");
                break;
            default:
                break;
        }
    }

    @Override
    public void initToolbar() {
        initThisToolbarHaveBack(toolbar,this);
    }

    //下一步
    @OnClick(R.id.a_creditFirst_btNext)
    public void next(){
        if (!getInput()){
            CreditFirst creditFirst = new CreditFirst(name,age,sfz,phone,marry,spouseName,spousePhone,
                    haveCar,haveHouse,address+jd+"街道/镇"+xq+"小区"+z+"幢"+s+"室",applicationMoney,workAddress
                    ,industry,career,jdHouse+"街道/镇"+xqHouse+"小区"+zHouse+"幢"+sHouse+"室",houseArea,
                    houseNature,houseStatus,
                    cjh,cpp,cp,cl,xsjl,province,city,zone,bank,sywk,houseAge,ck,useddate,useddateMonth,gcyj,cppId
                    ,sfdz,kind,type1);
            CreditApplicationSecondActivity.start(this,creditFirst);
        }
    }

    //选择年龄
    @OnClick(R.id.a_firstApplicationLoan_llAge)
    public void selectAge(){
        timeType = 1;
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
        cityType =1;
        initCityOptionPicker();
    }

    //申请金额
    @OnClick(R.id.a_creditFirst_llSqje)
    public void selectSqje(){
        moneyType = 1;
        showDialog();
    }

    //类型
    @OnClick(R.id.a_creditFirst_llType)
    public void llType(){
        type = 13;
        list.clear();
        list.add("抵押垫资");
        list.add("转贷垫资");
        list.add("买卖垫资");
        pickerView(list);
    }

    //工作所在地
    @OnClick(R.id.a_creditFirst_llWorkAddress)
    public void selectWorkAddress(){
        cityType =2;
        initCityOptionPicker();
    }

    //选择房屋所在地
    @OnClick(R.id.a_firstApplicationLoan_llHouseAddress)
    public void selectHouseAddress(){
        cityType = 3;
        initCityOptionPicker();
    }

    //选择房屋面积
    @OnClick(R.id.a_firstApplicationLoan_llHouseArea)
    public void selectHouseArea(){
        initAreaOptionsPicker();
    }

    //选择房屋性质选择房屋现状
    @OnClick(R.id.a_firstApplicationLoan_llHouseStatus)
    public void selectHouseStatus(){
        type = 7;
        list.clear();
        list.add("全款");
        list.add("按揭");
        pickerView(list);
    }

    //按揭银行
    @OnClick(R.id.a_firstApplicationLoan_llBank)
    public void selectBank(){
        type = 10;
        list.clear();
        list.add("中国人民银行");
        list.add("中国国家开发银行");
        list.add("中国农业发展银行");
        list.add("中国进出口银行");
        list.add("中国工商银行");
        list.add("中国农业银行");
        list.add("中国银行");
        list.add("中国建设银行");
        list.add("中国光大银行");
        list.add("中国民生银行");
        list.add("华夏银行");
        list.add("中信银行");
        list.add("恒丰银行");
        list.add("上海浦东发展银行");
        list.add("交通银行");
        list.add("浙商银行");
        list.add("兴业银行");
        list.add("深圳发展银行");
        list.add("招商银行");
        list.add("广东发展银行");
        pickerView(list);
    }

    //剩余尾款
    @OnClick(R.id.a_firstApplicationLoan_llSywk)
    public void sywk(){
        moneyType = 2;
        showDialog();
    }

    //需要垫资
    @OnClick(R.id.a_secondApplicationLoan_llSfdz)
    public void llSfdz(){
        type =12;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);
    }

    //选择房屋性质选择房屋现状
    @OnClick(R.id.a_firstApplicationLoan_llHouseNature)
    public void selectHouseNature(){
        type = 6;
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

    //房龄
    @OnClick(R.id.a_firstApplicationLoan_llHouseAge)
    public void llHouseAge(){
        timeType = 3;
        initAgeOptionsPicker();
    }

    //车品牌
    @OnClick(R.id.a_carFirstApplication_llCpp)
    public void selectCpp(){
        SelectNewActivity.start(this);
    }

    //车牌
    @OnClick(R.id.a_carFirstApplication_llCp)
    public void selectCp(){
        initCarOptionPicker();
    }

    //车龄
    @OnClick(R.id.a_carFirstApplication_llCl)
    public void selectCl(){
        timeType = 2;
        initAgeOptionsPicker();
    }

    //行驶距离
    @OnClick(R.id.a_carFirstApplication_llXsjl)
    public void selectXsjl(){
        threeType = 1;
        initDistanceOptionsPicker();

    }

    //车况
    @OnClick(R.id.a_carFirstApplication_llCk)
    public void llCk() {
        type =11;
        list.clear();
        list.add("较差");
        list.add("一般");
        list.add("优秀");
        pickerView(list);
    }

    //购车时间
    @OnClick(R.id.a_carFirstApplication_llGcsj)
    public void llGcsj() {
        if (timePickerView != null){
            timePickerView.show();
        }
    }

    //购车原价
    @OnClick(R.id.a_carFirstApplication_llGcyj)
    public void llGcyj() {
        threeType = 2;
        initDistanceOptionsPicker();
    }

    public boolean getInput(){
        name = etName.getText().toString();
        if (StringUtils.isEmpty(name)){
            ToastUtil.showInfo("请输入姓名！");
            return true;
        }

        age = tvAge.getText().toString();
        if (StringUtils.isEmpty(age)){
            ToastUtil.showInfo("请选择年龄！");
            return true;
        }
        sfz = etSfz.getText().toString();
        if (StringUtils.isEmpty(sfz)){
            ToastUtil.showInfo("请输入身份证号码！");
            return true;
        }
        if (!RegexUtils.isIDCard18(sfz)){
            ToastUtil.showInfo("请输入正确的身份证号码！");
            return true;
        }
        phone = etPhone.getText().toString();
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

        if (marry.equals("已婚")){
            spouseName = etSpouseName.getText().toString();
            if (StringUtils.isEmpty(spouseName)){
                ToastUtil.showInfo("请输入配偶姓名！");
                return true;
            }
            spousePhone = etSpousePhone.getText().toString();
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

        if (myType.equals("1")){
            haveCar = tvIsHaveCar.getText().toString();
            if (StringUtils.isEmpty(haveCar)){
                ToastUtil.showInfo("请选择是否有车！");
                return true;
            }

            if (haveCar.equals("是")){
                cjh = etCjh.getText().toString();
                if (StringUtils.isEmpty(cjh)){
                    ToastUtil.showInfo("请输入车架号！");
                    return true;
                }
                if (cjh.length() != 17){
                    ToastUtil.showInfo("请输入17位车架号！");
                    return true;
                }

                cpp = tvCpp.getText().toString();
                if (StringUtils.isEmpty(cpp) || StringUtils.isEmpty(cppId) ){
                    ToastUtil.showInfo("请选择车品牌！");
                    return true;
                }
                cp = tvCp.getText().toString();
                if (StringUtils.isEmpty(cp)){
                    ToastUtil.showInfo("请选择车牌！");
                    return true;
                }
                /*if (!RegexUtils.isIDCar(cp)){
                    ToastUtil.showInfo("请输入正确车牌前缀（类似浙A）！");
                    return true;
                }*/


                cl = tvCl.getText().toString();
                if (StringUtils.isEmpty(cl)){
                    ToastUtil.showInfo("请选择车龄！");
                    return true;
                }
                xsjl = tvXsjl.getText().toString();
                if (StringUtils.isEmpty(xsjl)){
                    ToastUtil.showInfo("请选择行驶距离！");
                    return true;
                }
                ck = tvCk.getText().toString().trim();
                if (StringUtils.isEmpty(ck)){
                    ToastUtil.showInfo("请选择车况！");
                    return true;
                }
                switch (ck){
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

                if (StringUtils.isEmpty(useddate) || StringUtils.isEmpty(useddateMonth)){
                    ToastUtil.showInfo("请选择购车时间！");
                    return true;
                }

                gcyj = tvGcyj.getText().toString().trim();
                if (StringUtils.isEmpty(gcyj)){
                    ToastUtil.showInfo("请选择购车原价！");
                    return true;
                }
            }
        }
        if (myType.equals("2")){
            haveHouse = tvIsHaveHouse.getText().toString();
            if (StringUtils.isEmpty(haveHouse)){
                ToastUtil.showInfo("请选择是否有房！");
                return true;
            }
            if (haveHouse.equals("是")){
                houseAddress = tvHouseAddress.getText().toString();
                if (StringUtils.isEmpty(houseAddress)){
                    ToastUtil.showInfo("请选择房子所在地！");
                    return true;
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
                    sfdz = tvSfdz.getText().toString().trim();
                    if (StringUtils.isEmpty(sfdz)){
                        ToastUtil.showInfo("请选择是否垫资！");
                        return true;
                    }
                }
                houseAge = tvHouseAge.getText().toString();
                if (StringUtils.isEmpty(houseAge)){
                    ToastUtil.showInfo("请选择房龄！");
                    return true;
                }
            }
        }

        applicationMoney = tvSqje.getText().toString();
        if (StringUtils.isEmpty(applicationMoney)){
            if (kind == 1) {
                ToastUtil.showInfo("请选择申请金额！");
            }else if (kind == 2){
                ToastUtil.showInfo("请选择需要垫资金额！");
            }
            return true;
        }

        if (kind == 2){
            type1 = tvType.getText().toString();
            if (StringUtils.isEmpty(type1)){
                ToastUtil.showInfo("请选择类型！");
                return true;
            }
        }

        workAddress = tvWorkAddress.getText().toString();
        if (StringUtils.isEmpty(workAddress)){
            ToastUtil.showInfo("请选择工作所在地！");
            return true;
        }
        industry = etIndustry.getText().toString();
        if (StringUtils.isEmpty(industry)){
            ToastUtil.showInfo("请输入行业！");
            return true;
        }
        career = etCareer.getText().toString();
        if (StringUtils.isEmpty(career)){
            ToastUtil.showInfo("请选择职业！");
            return true;
        }
        return false;
    }


    public void pickerView(final List<String> list){
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
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
                        switch (a){
                            case "是":
                                carLoanLl.setVisibility(View.VISIBLE);
                                break;
                            case "否":
                                carLoanLl.setVisibility(View.GONE);
                                break;
                            default:
                                break;
                        }
                        break;
                    case 3:
                        tvIsHaveHouse.setText(a);
                        switch (a){
                            case "是":
                                houseLoanLl.setVisibility(View.VISIBLE);
                                break;
                            case "否":
                                houseLoanLl.setVisibility(View.GONE);
                                break;
                            default:
                                break;
                        }
                        break;
                    case 4:
                        tvSqje.setText(a);
                        break;
                    case 6:
                        tvHouseNature.setText(a);
                        break;
                    case 7:
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
                        tvHouseStatus.setText(a);
                        break;
                    case 8:
                        tvCpp.setText(a);
                        break;
                    case 10:
                        tvBank.setText(a);
                        break;
                    case 11:
                        tvCk.setText(a);
                        break;
                    case 12:
                        tvSfdz.setText(a);
                        break;
                    case 13:
                        tvType.setText(a);
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

    private void initAgeOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String str = listNumber.get(options1) + listNumber.get(options2);
                String newStr = Kb260Utils.removeFrontZero(str);
                switch (timeType){
                    case 1:
                        tvAge.setText(newStr);
                        break;
                    case 2:
                        tvCl.setText(newStr);
                        break;
                    case 3:
                        tvHouseAge.setText(newStr);
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
        pvNoLinkOptions.setNPicker(listNumber, listNumber,null);
        pvNoLinkOptions.show();
    }

    private void initCityOptionPicker() {
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            if (getJsonDataUtil.isLoaded){
                e.onNext(1);
            }else {
                getJsonDataUtil.initJsonData(CreditApplicationFirstActivity.this,options1Items,options2Items,options3Items,e);
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
            String city1 = options1Items.get(options1).getPickerViewText()+" "+
                    options2Items.get(options1).get(options2)+" "+
                    options3Items.get(options1).get(options2).get(options3);
            switch (cityType){
                case 1:
                    provinceAddress = options1Items.get(options1).getPickerViewText();
                    cityAddress = options2Items.get(options1).get(options2);
                    zoneAddress = options3Items.get(options1).get(options2).get(options3);
                    tvAddress.setText(city1);
                    llAddressDetail.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    tvWorkAddress.setText(city1);
                    break;
                case 3:
                    province = options1Items.get(options1).getPickerViewText();
                    city = options2Items.get(options1).get(options2);
                    zone = options3Items.get(options1).get(options2).get(options3);

                    tvHouseAddress.setText(city1);
                    llHouseAddressDetail.setVisibility(View.VISIBLE);
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

        pvOptionsCar = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            String a = options1ItemsCar.get(options1).getPickerViewText();
            String b = options2ItemsCar.get(options1).get(options2);
            String tx = a+b;
            tvCp.setText(tx);
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
    }

    private void initAreaOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2) + listNumber.get(options3);
            String newStr = Kb260Utils.removeFrontZero(str);
            tvHouseArea.setText(newStr);
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        pvNoLinkOptions.setNPicker(listNumber, listNumber, listNumber);
        pvNoLinkOptions.show();
    }

    //行驶距离
    private void initDistanceOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2)+ listNumber.get(options3);
            String newStr = Kb260Utils.removeFrontZero(str);

            switch (threeType){
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
        pvNoLinkOptions.setNPicker(listNumber, listNumber,listNumber);
        pvNoLinkOptions.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Action.FINISH_ACTIVITY){
            finish();
        }else if (resultCode == 25){
            String a = data.getStringExtra(Action.SELECT_CAR);
            cppId = data.getStringExtra(Action.SELECT_CAR_ID);
            tvCpp.setText(a);
        }else if (resultCode ==24){
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

                    String communityid = data.getStringExtra(Action.COMMUNITYID);
                    String communityname = data.getStringExtra(Action.COMMUNITYNAME);
                    String workplace = data.getStringExtra(Action.WORKPLACE);
                    String b = data.getStringExtra(Action.HOUSE_DETAIL);
                    tvHouseAddressHouseDetail.setText(b);
                    break;
                default:
                    break;

            }

        }
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

                            switch (moneyType){
                                case 1:
                                    tvSqje.setText(newStr);
                                    break;
                                case 2:
                                    tvSywk.setText(newStr);
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

    private void initCarOptionPicker() {//条件选择器初始化
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            if (getJsonDataUtil.isCarLoaded){
                e.onNext(1);
            }else {
                getJsonDataUtil.initCarJsonData(CreditApplicationFirstActivity.this,options1ItemsCar,
                        options2ItemsCar,e);
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
        if (!StringUtils.isEmpty(provinceAddress) && !StringUtils.isEmpty(cityAddress) &&!StringUtils.isEmpty(zoneAddress)) {
            FindHouseActivity.start(this,provinceAddress,cityAddress,zoneAddress,Action.FIND_HOUSE_ADDRESS);
        }else {
            ToastUtil.showInfo("请先选择省市区");
        }
    }
    @OnClick(R.id.a_firstApplicationLoan_llHouseAddressPicker)
    public void llHouseAddressPicker(){
        if (!StringUtils.isEmpty(province) && !StringUtils.isEmpty(city) &&!StringUtils.isEmpty(zone)) {
            FindHouseActivity.start(this,province,city,zone,Action.FIND_HOUSE_HOUSE);
        }else {
            ToastUtil.showInfo("请先选择省市区");
        }
    }

}
