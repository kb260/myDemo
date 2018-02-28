package com.kb260.gxdk.view.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.bigkoo.pickerview.OptionsPickerView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.JsonBean;
import com.kb260.gxdk.model.HouseAccess;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.GetJsonDataUtil;
import com.kb260.gxdk.utils.Kb260Utils;
import com.kb260.gxdk.utils.RegexUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseFragment;
import com.kb260.gxdk.view.base.FindHouseActivity;
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

/**
 * @author  KB260
 * Created on  2018/1/4
 */

public class EvaluationFragment extends BaseFragment {
    @BindView(R.id.a_evaluation_tvAddress)
    TextView tvAddress;
    @BindView(R.id.a_evaluation_tvMarriage)
    TextView tvMarriage;
    @BindView(R.id.a_evaluation_tvHouseArea)
    TextView tvHouseArea;
    @BindView(R.id.a_evaluation_llResult)
    LinearLayout llResult;
    @BindView(R.id.a_evaluation_etName)
    EditText etName;
    @BindView(R.id.a_evaluation_etPhone)
    EditText etPhone;
    @BindView(R.id.a_evaluation_etSfz)
    EditText etIdCard;
    @BindView(R.id.a_evaluation_tvUnitPrice)
    TextView tvUnitPrice;
    @BindView(R.id.a_evaluation_tvArea)
    TextView tvArea;
    @BindView(R.id.a_evaluation_tvTotalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.a_evaluation_sc)
    ScrollView sc;

    @BindView(R.id.a_evaluation_llAddressDetail)
    LinearLayout llAddressDetail;
    @BindView(R.id.a_evaluation_llAddressPicker)
    LinearLayout llAddressPicker;
    @BindView(R.id.a_evaluation_llAddressWrite)
    LinearLayout llAddressWrite;
    @BindView(R.id.a_evaluation_tvAddressHouseDetail)
    TextView tvAddressHouseDetail;

    @BindView(R.id.a_evaluation_etAddress1)
    EditText etAddress1;
    @BindView(R.id.a_evaluation_etAddress2)
    EditText etAddress2;
    @BindView(R.id.a_evaluation_etAddress3)
    EditText etAddress3;
    @BindView(R.id.a_evaluation_etAddress4)
    EditText etAddress4;

    Context context;
    String jd, xq, z, s;
    private int type;
    List<String> list, listNumber;

    GetJsonDataUtil getJsonDataUtil;
    OptionsPickerView pvOptions;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    String telphone, realname, address, idcard, roomarea, marriage, province, city, zone,
            communityid ,communityname,workplace;


    public static EvaluationFragment getInstance(int type) {
        EvaluationFragment integralFragment = new EvaluationFragment();
        integralFragment.type = type;
        return integralFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.f_evaluation;
    }

    @Override
    protected void initView() {
        initRv();
        list = new ArrayList<>();
        listNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listNumber.add(String.valueOf(i));
        }
        initAddressDialog();
    }

    private void initRv() {

    }

    //常住地址
    @OnClick(R.id.a_evaluation_llAddress)
    public void selectAddress() {
        initCityOptionPicker();
    }

    //婚姻状况
    @OnClick(R.id.a_evaluation_llMarriage)
    public void selectMarriage() {
        list.clear();
        list.add("已婚");
        list.add("未婚");
        pickerView(list);
    }

    //房屋面积
    @OnClick(R.id.a_firstApplicationLoan_llHouseArea)
    public void selectHouseArea() {
        initNoLinkOptionsPicker();
    }

    //评估
    @OnClick(R.id.a_evaluation_btEvaluation)
    public void evaluation() {
        //SetPayPasswordActivity.start(getContext());
        if (!getInput()) {
            Api.getDefault().saveevaluate(KBApplication.token, KBApplication.userid, telphone, realname,
                    address, idcard, roomarea, marriage,communityid,communityname,workplace,city)
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<HouseAccess>(getContext()) {
                        @Override
                        protected void success(HouseAccess list) {
                            llResult.setVisibility(View.VISIBLE);
                            tvUnitPrice.setText(list.getUnitprice());
                            String area = list.getRealarea();
                            String roomArea = list.getRoomarea();
                            if (StringUtils.isEmpty(area)){
                                tvArea.setText(roomArea);
                            }else {
                                tvArea.setText(area+"平方米");
                            }

                            tvTotalPrice.setText(list.getTotalprice());

                            new Handler().post(() -> sc.fullScroll(ScrollView.FOCUS_DOWN));
                        }

                        @Override
                        protected void error(String msg) {
                            Logger.d(msg);
                            ToastUtil.showError(msg);
                        }
                    });
        }
    }

    private boolean getInput() {
        realname = etName.getText().toString().trim();
        if (StringUtils.isEmpty(realname)) {
            ToastUtil.showInfo("请输入姓名！");
            return true;
        }

        idcard = etIdCard.getText().toString().trim();
        if (StringUtils.isEmpty(idcard)) {
            ToastUtil.showInfo("请输入身份证号码！");
            return true;
        }
        if (!RegexUtils.isIDCard18(idcard)) {
            ToastUtil.showInfo("请输入正确的身份证号码！");
            return true;
        }

        telphone = etPhone.getText().toString().trim();
        if (StringUtils.isEmpty(telphone)) {
            ToastUtil.showInfo("请输入手机号码！");
            return true;
        }
        if (RegexUtils.isMobileSimple(telphone)) {
            ToastUtil.showInfo("请输入正确的手机号码！");
            return true;
        }

        address = tvAddress.getText().toString().trim();
        if (StringUtils.isEmpty(address)) {
            ToastUtil.showInfo("请选择常住地址！");
            return true;
        }
        jd = etAddress1.getText().toString().trim();
        if (StringUtils.isEmpty(jd)) {
            ToastUtil.showInfo("请选择常住地址街道/镇！");
            return true;
        }
        if (StringUtils.isEmpty(tvAddressHouseDetail.getText().toString())){
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
            address = address + jd + "街道/镇" + xq + "小区" + z + "幢" + s + "室";
        }else {
            address = address + jd + "街道/镇" +tvAddressHouseDetail.getText().toString();
        }

        marriage = tvMarriage.getText().toString().trim();
        if (StringUtils.isEmpty(marriage)) {
            ToastUtil.showInfo("请选择婚姻状况！");
            return true;
        }
        switch (marriage) {
            case "已婚":
                marriage = "1";
                break;
            case "未婚":
                marriage = "0";
                break;
            default:
                break;
        }

        roomarea = tvHouseArea.getText().toString().trim();
        if (StringUtils.isEmpty(roomarea)) {
            ToastUtil.showInfo("请选择房屋面积！");
            return true;
        }
        return false;
    }

    public void pickerView(final List<String> list) {
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(context, (options1, options2, options3, v) -> tvMarriage.setText(list.get(options1))).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        optionsPickerView.setPicker(list);
        optionsPickerView.show();
    }

    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(context, (options1, options2, options3, v) -> {
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

    private void initCityOptionPicker() {//条件选择器初始化
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            if (getJsonDataUtil.isLoaded) {
                e.onNext(1);
            } else {
                getJsonDataUtil.initJsonData(getContext(), options1Items, options2Items, options3Items, e);
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
        pvOptions = new OptionsPickerView.Builder(getContext(), (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            province = options1Items.get(options1).getPickerViewText();
            city = options2Items.get(options1).get(options2);
            zone = options3Items.get(options1).get(options2).get(options3);
            String address = province + city +zone;
            tvAddress.setText(address);
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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Action.FIND_HOUSE_1){
            llAddressPicker.setVisibility(View.GONE);
            llAddressWrite.setVisibility(View.VISIBLE);
            String xq = data.getStringExtra(Action.XQ);
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

    @OnClick(R.id.a_evaluation_llAddressPicker)
    public void pickerHouse() {
        Intent intent = new Intent(context, FindHouseActivity.class);
        intent.putExtra(Action.PROVINCE,province);
        intent.putExtra(Action.CITY,city);
        intent.putExtra(Action.AREA,zone);
        startActivityForResult(intent, Action.FIND_HOUSE_ADDRESS);
    }

}
