package com.kb260.gxdk.view.me.fragment;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.BCheckedAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.CheckedData;
import com.kb260.gxdk.entity.JsonBean;
import com.kb260.gxdk.model.EventData;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.GetJsonDataUtil;
import com.kb260.gxdk.utils.Kb260Utils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseFragment;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;
import org.greenrobot.eventbus.EventBus;
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
 * Created on  2017/12/1
 */

public class NewProductFragment extends BaseFragment {
    @BindView(R.id.f_newProduct_tvType)
    TextView tvType;
    @BindView(R.id.f_newProduct_tvBank)
    TextView tvBank;
    @BindView(R.id.f_newProduct_tvCarDoPlate)
    TextView tvCarDoPlate;
    @BindView(R.id.f_newProduct_tvHouseCarDoArea)
    TextView tvHouseCarDoArea;
    @BindView(R.id.f_newProduct_tvRepaymentType)
    TextView tvRepaymentType;
    @BindView(R.id.f_newProduct_tvRepaymentTime)
    TextView tvRepaymentTime;
    @BindView(R.id.f_newProduct_tvMaterial)
    TextView tvMaterial;
    @BindView(R.id.f_newProduct_tvMinAge)
    TextView tvMinAge;
    @BindView(R.id.f_newProduct_tvMaxAge)
    TextView tvMaxAge;
    @BindView(R.id.f_newProduct_tvDistance)
    TextView tvDistance;
    @BindView(R.id.f_newProduct_tvMaxMoney)
    TextView tvMaxMoney;
    @BindView(R.id.f_newProduct_llTypeHouse)
    LinearLayout llTypeHouse;
    @BindView(R.id.f_newProduct_llTypeCar)
    LinearLayout llTypeCar;

    @BindView(R.id.f_newProduct_tvHouseAge)
    TextView tvHouseAge;
    @BindView(R.id.f_newProduct_tvHouseTLoanType)
    TextView tvHouseTLoanType;
    @BindView(R.id.f_newProduct_tvUserType)
    TextView tvUserType;
    @BindView(R.id.f_newProduct_tvZhaGu)
    TextView tvZhaGu;
    @BindView(R.id.f_newProduct_tvBusinessPremises)
    TextView tvBusinessPremises;
    @BindView(R.id.f_newProduct_tvHouseArea)
    TextView tvHouseArea;
    @BindView(R.id.f_newProduct_tvNature)
    TextView tvNature;

    @BindView(R.id.f_newProduct_etBranch)
    EditText etBranch;
    @BindView(R.id.f_newProduct_tvTime)
    TextView tvTime;
    @BindView(R.id.f_newProduct_tvMinInterestRate)
    TextView tvMinRate;
    @BindView(R.id.f_newProduct_tvMaxInterestRate)
    TextView tvRate;

    @BindView(R.id.f_newProduct_llZhaGu)
    LinearLayout llZhaGu;
    @BindView(R.id.f_newProduct_llUserType)
    LinearLayout llUserType;
    @BindView(R.id.f_newProduct_llBusinessPremises)
    LinearLayout llBusinessPremises;


    /*@BindView(R.id.f_newProduct_tvMinCarAge)
    TextView tvMinCarAge;*/
    @BindView(R.id.f_newProduct_tvMaxCarAge)
    TextView tvMaxCarAge;

    Context context;

    int type, type1,ageType,addressType,threeType;
    List<String> list, listNumber,listPoint;
    List<CheckedData> needMaterials;
    List<CheckedData> needType;
    List<CheckedData> repaymentCycles;
    List<CheckedData> canDoNatures;
    List<CheckedData> zones;
    List<CheckedData> userTypes;
    List<CheckedData> cars;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    GetJsonDataUtil getJsonDataUtil;
    OptionsPickerView  pvOptions,pvOptionsCar;
    WheelAdapter wheelAdapter;
    WheelAdapter rateAdapter;
    BCheckedAdapter bCheckedAdapter;
    BCheckedAdapter needTypeAdapter;
    BCheckedAdapter repaymentCycleAdapter;
    BCheckedAdapter canDoNatureAdapter;
    BCheckedAdapter zoneAdapter;
    BCheckedAdapter userTypeAdapter;
    BCheckedAdapter carTypeAdapter;
    int rateType,manyType;

    private ArrayList<JsonBean> options1ItemsCar = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2ItemsCar = new ArrayList<>();


    String creditday,bank,branchname,loantype,productType,usertype,ismanager,isproperty,
            rate,maxrate,count,paytype,paytime,address,province,city,zone,cardphoto,marriagephoto,residencephoto,
            threephoto,license,creditphoto,association,reserve,ratio,plateFirst,plateSecond;
    int maxcarage,carage,age,maxage,roomage,cardistance,area;

    public static NewProductFragment getInstance() {
        return new NewProductFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.f_new_product;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected void initView() {
        initAddressDialog();
        initList();
    }

    private void initList() {
        list = new ArrayList<>();
        listNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listNumber.add(String.valueOf(i));
        }
        wheelAdapter = new ArrayWheelAdapter(listNumber);

        listPoint = new ArrayList<>();
        listPoint.add(".");
        rateAdapter = new ArrayWheelAdapter(listPoint);

        needMaterials = new ArrayList<>();
        needMaterials.add(new CheckedData("身份证正反面",false));
        needMaterials.add(new CheckedData("结婚证/离婚证",false));
        needMaterials.add(new CheckedData("户口本",false));
        needMaterials.add(new CheckedData("车辆登记证",false));
        needMaterials.add(new CheckedData("行驶证",false));
        needMaterials.add(new CheckedData("征信",false));
        needMaterials.add(new CheckedData("房产证",false));
        needMaterials.add(new CheckedData("驾驶证",false));
        needMaterials.add(new CheckedData("购车发票",false));
        bCheckedAdapter = new BCheckedAdapter(needMaterials);

        needType = new ArrayList<>();
        needType.add(new CheckedData("等额本息",false));
        needType.add(new CheckedData("先息后本",false));
        needType.add(new CheckedData("随借随还",false));
        needTypeAdapter = new BCheckedAdapter(needType);

        repaymentCycles = new ArrayList<>();
        repaymentCycles.add(new CheckedData("3个月",false));
        repaymentCycles.add(new CheckedData("6个月",false));
        repaymentCycles.add(new CheckedData("一年一转",false));
        repaymentCycles.add(new CheckedData("三年一转",false));
        repaymentCycles.add(new CheckedData("五年一转",false));
        repaymentCycles.add(new CheckedData("随借随还",false));
        repaymentCycles.add(new CheckedData("10年",false));
        repaymentCycles.add(new CheckedData("20年",false));
        repaymentCycles.add(new CheckedData("30年",false));
        repaymentCycleAdapter = new BCheckedAdapter(repaymentCycles);


        canDoNatures = new ArrayList<>();
        canDoNatures.add(new CheckedData("住宅",false));
        canDoNatures.add(new CheckedData("酒店式公寓",false));
        canDoNatures.add(new CheckedData("别墅",false));
        canDoNatures.add(new CheckedData("排屋",false));
        canDoNatures.add(new CheckedData("商铺",false));
        canDoNatures.add(new CheckedData("写字楼",false));
        canDoNatures.add(new CheckedData("厂房",false));
        canDoNatures.add(new CheckedData("土地",false));
        canDoNatureAdapter = new BCheckedAdapter(canDoNatures);

        userTypes = new ArrayList<>();
        userTypes.add(new CheckedData("法人",false));
        userTypes.add(new CheckedData("股东",false));
        userTypes.add(new CheckedData("合伙人",false));
        userTypeAdapter = new BCheckedAdapter(userTypes);

        zones = new ArrayList<>();
        zoneAdapter = new BCheckedAdapter(zones);

        cars = new ArrayList<>();
        carTypeAdapter = new BCheckedAdapter(cars);
    }


    //产品类型
    @OnClick(R.id.f_newProduct_llType)
    public void llType() {
        type = 1;
        list.clear();
        list.add("房屋");
        list.add("车辆");
        pickerView(list);
    }

    //所属银行
    @OnClick(R.id.f_newProduct_llBank)
    public void llBank() {
        type = 2;
        list.clear();
        list = new GetJsonDataUtil().initJsonBank(getContext());
        pickerView(list);
    }

    //预计放款天数
    @OnClick(R.id.f_newProduct_llTime)
    public void llTime() {
        ageType = 4;
        initAgeOptionsPicker();
    }

    //借款人年龄
    @OnClick(R.id.f_newProduct_tvMinAge)
    public void tvMinAge() {
        ageType =1;
        initAgeOptionsPicker();
    }

    //借款人年龄
    @OnClick(R.id.f_newProduct_tvMaxAge)
    public void tvMaxAge() {
        ageType =3;
        initAgeOptionsPicker();
    }

    //可做房龄
    @OnClick(R.id.f_newProduct_llHouseAge)
    public void llHouseAge() {
        ageType =2;
        initAgeOptionsPicker();
    }

    //放贷类型
    @OnClick(R.id.f_newProduct_llHouseTLoanType)
    public void llHouseTLoanType() {
        type = 4;
        list.clear();
        list.add("经营型");
        list.add("消费型");
        pickerView(list);
    }
    //用户类型
    @OnClick(R.id.f_newProduct_llUserType)
    public void llUserType() {
        /*type = 5;
        list.clear();
        list.add("法人");
        list.add("股东");
        list.add("合伙人");
        pickerView(list);*/
        manyType = 6;
        showBottomCheckedDialog(userTypeAdapter);
    }

    //用户类型
    @OnClick(R.id.f_newProduct_llZhaGu)
    public void llZhaGu() {
        ageType = 7;
        initAgeOptionsPicker();
    }

    //经营场地
    @OnClick(R.id.f_newProduct_llBusinessPremises)
    public void llBusinessPremises() {
        type = 8;
        list.clear();
        list.add("是");
        list.add("否");
        pickerView(list);

    }
    //房屋面积要求
    @OnClick(R.id.f_newProduct_llHouseArea)
    public void llHouseArea() {
        type1 = 1;
        showDialog();
    }
    //可做性质
    @OnClick(R.id.f_newProduct_llNature)
    public void llNature() {
        manyType = 4;
        showBottomCheckedDialog(canDoNatureAdapter);
    }



    //车公里数
    @OnClick(R.id.f_newProduct_llDistance)
    public void llDistance() {
        ageType = 8;
        initAgeOptionsPicker();
    }
    /*//车年限 小
    @OnClick(R.id.f_newProduct_tvMinCarAge)
    public void tvMinCarAge() {
        ageType = 5;
        initAgeOptionsPicker();
    }*/

    //车年限 大
    @OnClick(R.id.f_newProduct_llYear)
    public void tvMaxCarAge() {
        ageType = 6;
        initAgeOptionsPicker();
    }

    //最高额度
    @OnClick(R.id.f_newProduct_llMaxMoney)
    public void llMaxMoney() {
        type1 = 2;
        showDialog();
    }

    //最小利率
    @OnClick(R.id.f_newProduct_tvMinInterestRate)
    public void minInterestRate(){
        rateType =2;
        showRate();
    }

    //最大利率
    @OnClick(R.id.f_newProduct_tvMaxInterestRate)
    public void maxInterestRate(){
        rateType =1;
        showRate();
    }


    //还款方式
    @OnClick(R.id.f_newProduct_llRepaymentType)
    public void llRepaymentType() {
        manyType =2;
        showBottomCheckedDialog(needTypeAdapter);
    }

    //还款周期
    @OnClick(R.id.f_newProduct_llRepaymentTime)
    public void llRepaymentTime() {
        manyType =3;
        showBottomCheckedDialog(repaymentCycleAdapter);
    }

    //可做区域
    @OnClick(R.id.f_newProduct_llHouseCarDoArea)
    public void llHouseCarDoArea() {
        addressType =1;
        initCityOptionPicker();
    }

    //可做车牌
    @OnClick(R.id.f_newProduct_llCarDoPlate)
    public void llCarDoPlate() {
        initCarOptionPicker();
    }

    //所需材料
    @OnClick(R.id.f_newProduct_llMaterial)
    public void llMaterial() {
        manyType =1;
        showBottomCheckedDialog(bCheckedAdapter);
    }

    //提交
    @OnClick(R.id.f_newProduct_btApplication)
    public void submit() {
        if (!getInput()){
            submitProduct();
        }
    }

    private void submitProduct(){
        Api.getDefault().saveroomproduct(KBApplication.token,KBApplication.userid,age,
                maxage,creditday,roomage,bank,branchname,
                loantype,productType, usertype,ismanager,isproperty,area, rate,maxrate,
                count,paytype,paytime, address,province, city,zone,cardphoto,marriagephoto,residencephoto,
                threephoto,license, creditphoto,association,maxcarage,carage,
                reserve,cardistance,ratio)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(getContext()) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("新增成功！");
                        EventData eventData = new EventData(Action.EVENT_TYPE_MY_PRODUCT);
                        EventBus.getDefault().post(eventData);
                        getActivity().finish();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private boolean getInput(){
        productType = tvType.getText().toString();
        switch (productType){
            case "车辆":
                productType ="2";
                break;
            case "房屋":
                productType="1";
                break;
            default:
                break;
        }
        if (StringUtils.isEmpty(productType)){
            ToastUtil.showInfo("请选择产品类型！");
            return true;
        }

        bank = tvBank.getText().toString();
        if (StringUtils.isEmpty(bank)){
            ToastUtil.showInfo("请选择所属银行！");
            return true;
        }

        branchname = etBranch.getText().toString();
        if (StringUtils.isEmpty(branchname)){
            ToastUtil.showInfo("请输入支行名称！");
            return true;
        }

        creditday = tvTime.getText().toString();
        if (StringUtils.isEmpty(creditday)){
            ToastUtil.showInfo("请选择预计放款天数！");
            return true;
        }

        String age1 = tvMinAge.getText().toString();
        if (StringUtils.isEmpty(age1)){
            ToastUtil.showInfo("请选择最小借款人年龄！");
            return true;
        }
        age = Integer.valueOf(age1);

        String age2 = tvMaxAge.getText().toString();
        if (StringUtils.isEmpty(age2)){
            ToastUtil.showInfo("请选择最大借款人年龄！");
            return true;
        }
        maxage = Integer.valueOf(age2);

        switch (productType){
            case "1":
                String roomage1 = tvHouseAge.getText().toString();
                if (StringUtils.isEmpty(roomage1)){
                    ToastUtil.showInfo("请选择可做房龄！");
                    return true;
                }
                roomage = Integer.valueOf(roomage1);

                loantype = tvHouseTLoanType.getText().toString();
                if (StringUtils.isEmpty(loantype)){
                    ToastUtil.showInfo("请选择房贷类型！");
                    return true;
                }

                if (loantype.equals("经营型")){
                    ismanager = tvBusinessPremises.getText().toString();
                    if (StringUtils.isEmpty(ismanager)){
                        ToastUtil.showInfo("请选择经营场地！");
                        return true;
                    }

                    usertype = tvUserType.getText().toString();
                    if (StringUtils.isEmpty(usertype)){
                        ToastUtil.showInfo("请选择用户类型！");
                        return true;
                    }

                    if (usertype.contains("股东")){
                        ratio = tvZhaGu.getText().toString();
                        if (StringUtils.isEmpty(ratio)){
                            ToastUtil.showInfo("请选择占股比例！");
                            return true;
                        }
                    }
                }

                String area1 = tvHouseArea.getText().toString();
                if (StringUtils.isEmpty(area1)){
                    ToastUtil.showInfo("请选择房屋面积要求！");
                    return true;
                }
                area = Integer.valueOf(area1);

                isproperty = tvNature.getText().toString();
                if (StringUtils.isEmpty(isproperty)){
                    ToastUtil.showInfo("请选择可做性质！");
                    return true;
                }
                address = tvHouseCarDoArea.getText().toString();
                if (StringUtils.isEmpty(address)){
                    ToastUtil.showInfo("请选择可做区域！");
                    return true;
                }

                break;
            case "2":
                /*carage =tvMinCarAge.getText().toString().trim();
                if (StringUtils.isEmpty(carage)){
                    ToastUtil.showInfo("请选择最小车年限！");
                    return true;
                }*/
                String maxcarage1 =tvMaxCarAge.getText().toString().trim();
                if (StringUtils.isEmpty(maxcarage1)){
                    ToastUtil.showInfo("请选择车年限！");
                    return true;
                }
                maxcarage = Integer.valueOf(maxcarage1);

                String cardistance1 =tvDistance.getText().toString().trim();
                if (StringUtils.isEmpty(cardistance1)){
                    ToastUtil.showInfo("请输入车公里数！");
                    return true;
                }
                cardistance = Integer.valueOf(cardistance1);

                address = tvCarDoPlate.getText().toString();
                if (StringUtils.isEmpty(address)){
                    ToastUtil.showInfo("请选择可做车牌！");
                    return true;
                }
                break;
            default:
                break;
        }

        count = tvMaxMoney.getText().toString();
        if (StringUtils.isEmpty(count)){
            ToastUtil.showInfo("请选择最高额度！");
            return true;
        }

        rate = tvMinRate.getText().toString();
        if (StringUtils.isEmpty(rate)){
            ToastUtil.showInfo("请选择最低月利率！");
            return true;
        }

        maxrate = tvRate.getText().toString();
        if (StringUtils.isEmpty(maxrate)){
            ToastUtil.showInfo("请选择最高月利率！");
            return true;
        }
        if (Double.valueOf(maxrate)<4.7 || Double.valueOf(maxrate)>15){
            ToastUtil.showInfo("最大利率范围为：4.7~15！");
            return true;
        }

        paytype = tvRepaymentType.getText().toString();
        if (StringUtils.isEmpty(paytype)){
            ToastUtil.showInfo("请选择还款方式！");
            return true;
        }

        paytime = tvRepaymentTime.getText().toString();
        if (StringUtils.isEmpty(paytime)){
            ToastUtil.showInfo("请选择还款周期！");
            return true;
        }


        reserve = tvMaterial.getText().toString();
        return false;
    }

    public void pickerView(final List<String> list) {
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(context, (options1, options2, options3, v) -> {
            String a = list.get(options1);
            switch (type) {
                case 1:
                    switch (a){
                        case "房屋":
                            llTypeHouse.setVisibility(View.VISIBLE);
                            llTypeCar.setVisibility(View.GONE);
                            break;
                        case "车辆":
                            llTypeHouse.setVisibility(View.GONE);
                            llTypeCar.setVisibility(View.VISIBLE);
                            break;
                        default:
                            break;
                    }
                    tvType.setText(a);
                    break;
                case 2:
                    tvBank.setText(a);
                    break;
                case 4:
                    switch (a){
                        case "经营型":
                            llUserType.setVisibility(View.VISIBLE);
                            llBusinessPremises.setVisibility(View.VISIBLE);
                            break;
                        case "消费型":
                            llUserType.setVisibility(View.GONE);
                            llBusinessPremises.setVisibility(View.GONE);
                            llZhaGu.setVisibility(View.GONE);
                            tvUserType.setText("");
                            tvBusinessPremises.setText("");
                            tvZhaGu.setText("");
                            break;
                        default:
                            break;
                    }
                    tvHouseTLoanType.setText(a);
                    break;
                case 5:
                    tvUserType.setText(a);
                    if (a.equals("股东")){
                        llZhaGu.setVisibility(View.VISIBLE);
                    }else {
                        llZhaGu.setVisibility(View.GONE);
                    }
                    break;
                case 8:
                    tvBusinessPremises.setText(a);
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

    private void initCycleOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(context, (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2) + listNumber.get(options3);
            String newStr = Kb260Utils.removeFrontZero(str);
            switch (threeType){
                case 2:
                    //tvHouseArea.setText(newStr);
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
                getJsonDataUtil.initJsonData(getContext(),options1Items,options2Items,options3Items,e);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        pvOptions.setPicker(options1Items, options2Items);//三级选择器
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
        pvOptions = new OptionsPickerView.Builder(getContext(), (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            province = options1Items.get(options1).getPickerViewText();
            city = options2Items.get(options1).get(options2);
            List<String> zone = options3Items.get(options1).get(options2);
            zones.clear();
            for (int i = 0;i<zone.size();i++){
                zones.add(new CheckedData(zone.get(i),false));
            }
            zoneAdapter.setNewData(zones);
            manyType =5;
            showBottomCheckedDialog(zoneAdapter);

        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();

        pvOptionsCar = new OptionsPickerView.Builder(getContext(), (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            plateFirst = options1ItemsCar.get(options1).getPickerViewText();
            List<String> car = options2ItemsCar.get(options1);
            cars.clear();
            for (int i = 0;i<car.size();i++){
                cars.add(new CheckedData(plateFirst+car.get(i),false));
            }
            carTypeAdapter.setNewData(cars);
            manyType =7;
            showBottomCheckedDialog(carTypeAdapter);
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
    }
    // 不联动的多级选项
    private void initAgeOptionsPicker() {
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(context, (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2);
            String newStr = Kb260Utils.removeFrontZero(str) ;
            switch (ageType){
                case 1:
                    if (Integer.valueOf(newStr)<18 || Integer.valueOf(newStr)>80){
                        ToastUtil.showInfo("选择范围为18岁~80岁");
                        break;
                    }
                    tvMinAge.setText(newStr);
                    break;
                case 2:
                    tvHouseAge.setText(newStr);
                    break;
                case 3:
                    if (Integer.valueOf(newStr)<18 || Integer.valueOf(newStr)>80){
                        ToastUtil.showInfo("选择范围为18岁~80岁");
                        break;
                    }
                    tvMaxAge.setText(newStr);
                    break;
                case 4:
                    tvTime.setText(newStr);
                    break;
               /* case 5:
                    tvMinCarAge.setText(newStr);
                    break;*/
                case 6:
                    tvMaxCarAge.setText(newStr);
                    break;
                case 7:
                    tvZhaGu.setText(newStr);
                    break;
                case 8:
                    tvDistance.setText(newStr);
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

                        holder.setOnClickListener(R.id.b_btnSubmit, view -> {
                            dialog.dismiss();

                            String a = String.valueOf(wv1.getCurrentItem()) + String.valueOf(wv2.getCurrentItem())
                                    + String.valueOf(wv3.getCurrentItem()) + String.valueOf(wv4.getCurrentItem());
                            String newStr = a.replaceFirst("^0*", "");
                            switch (type1) {
                                case 1:
                                    tvHouseArea.setText(newStr);
                                    break;
                                case 2:
                                    tvMaxMoney.setText(newStr);
                                    break;
                                default:
                                    break;
                            }
                        });
                        holder.setOnClickListener(R.id.b_btnCancel, view -> dialog.dismiss());
                    }
                })
                .setShowBottom(true)
                .show(getFragmentManager());
    }

    public void showBottomCheckedDialog(BCheckedAdapter adapter) {
        KBDialog.init()
                .setLayoutId(R.layout.bottom_checked)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final BaseKBDialog dialog) {
                        RecyclerView rv = holder.getView(R.id.b_checked_rv);
                        rv.setLayoutManager(new LinearLayoutManager(context));
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
                                    tvMaterial.setText(a);
                                    break;
                                case 2:
                                    tvRepaymentType.setText(a);
                                    break;
                                case 3:
                                    tvRepaymentTime.setText(a);
                                    break;
                                case 4:
                                    tvNature.setText(a);
                                    break;
                                case 5:
                                    zone = a;
                                    tvHouseCarDoArea.setText(province+" "+city+" "+a);
                                    break;
                                case 6:
                                    if (a.contains("股东")){
                                        llZhaGu.setVisibility(View.VISIBLE);
                                    }else {
                                        llZhaGu.setVisibility(View.GONE);
                                    }
                                    tvUserType.setText(a);
                                    break;
                                case 7:
                                    tvCarDoPlate.setText(a);
                                    break;
                                default:
                                    break;
                            }

                        });
                        holder.setOnClickListener(R.id.b_checked_btnCancel, view -> dialog.dismiss());
                    }
                })
                .setShowBottom(true)
                .show(getFragmentManager());
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
                                case 2:
                                    double min = Double.valueOf(c);
                                    if (min<4.7 || min>15){
                                        ToastUtil.showInfo("最小利率范围为：4.7~15！");
                                        break;
                                    }else {
                                        if (!StringUtils.isEmpty(tvRate.getText().toString())){
                                            double max = Double.valueOf(tvRate.getText().toString());
                                            if (max <min){
                                                ToastUtil.showInfo("最小利率不能大于最大利率！");
                                                break;
                                            }
                                        }
                                        tvMinRate.setText(c);
                                    }
                                    break;
                                case 1:
                                    double maxMax = Double.valueOf(c);
                                    if (maxMax<4.7 || maxMax>15){
                                        ToastUtil.showInfo("最大利率范围为：4.7~15！");
                                        break;
                                    }else {
                                        if (!StringUtils.isEmpty(tvMinRate.getText().toString())){
                                            double maxMin = Double.valueOf(tvMinRate.getText().toString());
                                            if (maxMax <maxMin){
                                                ToastUtil.showInfo("最大利率不能小于最小利率！");
                                                break;
                                            }
                                        }
                                        tvRate.setText(c);
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
                .show(getFragmentManager());
    }

    private void initCarOptionPicker() {//条件选择器初始化
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            if (getJsonDataUtil.isCarLoaded){
                e.onNext(1);
            }else {
                getJsonDataUtil.initCarJsonData(getContext(),options1ItemsCar,
                        options2ItemsCar,e);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        pvOptionsCar.setPicker(options1ItemsCar);//三级选择器
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

}
