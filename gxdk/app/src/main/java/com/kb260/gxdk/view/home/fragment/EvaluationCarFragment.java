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
import com.bigkoo.pickerview.TimePickerView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.JsonBean;
import com.kb260.gxdk.model.CarAccess;
import com.kb260.gxdk.model.EventData;
import com.kb260.gxdk.model.HouseAccess;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.GetJsonDataUtil;
import com.kb260.gxdk.utils.Kb260Utils;
import com.kb260.gxdk.utils.RegexUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseFragment;
import com.kb260.gxdk.view.base.SelectActivity;
import com.kb260.gxdk.view.base.SelectNewActivity;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

public class EvaluationCarFragment extends BaseFragment {
    @BindView(R.id.a_evaluationCar_tvCk)
    TextView tvCk;
    @BindView(R.id.a_evaluationCar_tvClyt)
    TextView tvClyt;
    @BindView(R.id.a_evaluationCar_tvSs)
    TextView tvSs;
    @BindView(R.id.a_evaluationCar_tvCpp1)
    TextView tvCpp1;
    @BindView(R.id.a_evaluationCar_tvGcsj)
    TextView tvGcsj;
    @BindView(R.id.a_evaluationCar_tvXsjl1)
    TextView tvXsjl1;
    @BindView(R.id.a_evaluationCar_tvGcyj)
    TextView tvGcyj;

    @BindView(R.id.a_evaluationCar_llResult)
    LinearLayout llResult;
    @BindView(R.id.a_evaluationCar_sc)
    ScrollView sc;

    @BindView(R.id.a_evaluationCar_tvCpp)
    TextView tvCpp;
    @BindView(R.id.a_evaluationCar_tvXsjl)
    TextView tvXsjl;
    @BindView(R.id.a_evaluationCar_tvZj)
    TextView tvZj;

    Context context;
    private int type,threeType,oneType;
    List<String> list, listNumber;
    GetJsonDataUtil getJsonDataUtil;
    OptionsPickerView  pvOptions;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    TimePickerView timePickerView;

    String ck,clyt,xsjl,gcyj,cppId,province,city,useddateMonth,useddate,purpose,reserve;


    public static EvaluationCarFragment getInstance(int type) {
        EvaluationCarFragment integralFragment = new EvaluationCarFragment();
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
        return R.layout.f_evaluation_car;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);

        initRv();
        list = new ArrayList<>();
        listNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listNumber.add(String.valueOf(i));
        }

        initTimeOptionsPicker();
        initAddressDialog();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initRv() {

    }

    //车况
    @OnClick(R.id.a_evaluationCar_llCk)
    public void llCk() {
        oneType =1;
        list.clear();
        list.add("较差");
        list.add("一般");
        list.add("优秀");
        pickerView(list);
    }

    //车辆用途
    @OnClick(R.id.a_evaluationCar_llClyt)
    public void llClyt() {
        oneType = 2;
        list.clear();
        list.add("自用");
        list.add("公务商用");
        list.add("营运");
        pickerView(list);
    }

    //省市
    @OnClick(R.id.a_evaluationCar_llSs)
    public void llSs() {
        initCityOptionPicker();
    }

    //车品牌
    @OnClick(R.id.a_evaluationCar_llCpp)
    public void llCpp() {
        SelectNewActivity.start(getActivity());
    }
    //购车时间
    @OnClick(R.id.a_evaluationCar_llGcsj)
    public void llGcsj() {
        if (timePickerView != null){
            timePickerView.show();
        }
    }

    //行驶距离
    @OnClick(R.id.a_evaluationCar_llXsjl)
    public void llXsjl() {
        initNoLinkTwoOptionsPicker();
    }

    //购车原价
    @OnClick(R.id.a_evaluationCar_llGcyj)
    public void llGcyj() {
        threeType = 2;
        initNoLinkOptionsPicker();
    }


    //评估
    @OnClick(R.id.a_evaluationCar_btEvaluation)
    public void evaluation() {
        if (!getInput()){
            Api.getDefault().carpinggu(KBApplication.token,KBApplication.userid,province,city,
                    Double.valueOf(gcyj),Double.valueOf(xsjl),useddateMonth,useddate,cppId,ck,purpose,reserve)
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<CarAccess>(getContext()) {
                        @Override
                        protected void success(CarAccess list) {
                            llResult.setVisibility(View.VISIBLE);
                            tvCpp.setText(list.getReserve());
                            tvXsjl.setText(list.getMileage());
                            tvZj.setText(list.getTotalprice());
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

    private boolean getInput(){
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

        clyt = tvClyt.getText().toString().trim();
        if (StringUtils.isEmpty(clyt)){
            ToastUtil.showInfo("请选择车辆用途！");
            return true;
        }
        switch (clyt){
            case "自用":
                purpose = "1";
                break;
            case "公务商用":
                purpose = "2";
                break;
            case "运营":
                purpose = "3";
                break;
            default:
                break;
        }

        if (StringUtils.isEmpty(province) || StringUtils.isEmpty(city)){
            ToastUtil.showInfo("请选择省市！");
            return true;
        }

        if (StringUtils.isEmpty(cppId) || StringUtils.isEmpty(reserve)){
            ToastUtil.showInfo("请选择车品牌！");
            return true;
        }

        if (StringUtils.isEmpty(useddate) || StringUtils.isEmpty(useddateMonth)){
            ToastUtil.showInfo("请选择购车时间！");
            return true;
        }

        xsjl = tvXsjl1.getText().toString();
        if (StringUtils.isEmpty(xsjl)){
            ToastUtil.showInfo("请选行驶距离！");
            return true;
        }

        gcyj = tvGcyj.getText().toString().trim();
        if (StringUtils.isEmpty(gcyj)){
            ToastUtil.showInfo("请选择购车原价！");
            return true;
        }
        return false;
    }

    public void pickerView(final List<String> list) {
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(context, (options1, options2, options3, v) ->{
            switch (oneType){
                case 1:
                    tvCk.setText(list.get(options1));
                    break;
                case 2:
                    tvClyt.setText(list.get(options1));
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

    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(context, (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2) + listNumber.get(options3);
            String newStr = Kb260Utils.removeFrontZero(str);
            switch (threeType){
                case 1:
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

    private void initNoLinkTwoOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(context, (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2);
            String newStr = Kb260Utils.removeFrontZero(str);
            tvXsjl1.setText(newStr);
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        pvNoLinkOptions.setNPicker(listNumber, listNumber,null);
        pvNoLinkOptions.show();
    }

    private void initCityOptionPicker() {//条件选择器初始化
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            if (getJsonDataUtil.isLoaded){
                e.onNext(1);
            }else {
                getJsonDataUtil.initJsonData(getContext(),options1Items,options2Items,e);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        pvOptions.setPicker(options1Items, options2Items);//2级选择器
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
            city =  options2Items.get(options1).get(options2);
            String tx = province +" "+ city;
            tvSs.setText(tx);
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
    }

    private void initTimeOptionsPicker() {// 不联动的多级选项
        timePickerView = new TimePickerView.Builder(getContext(), (date, v) -> {
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(EventData eventData) {
        if (eventData.getType() == Action.EVENT_TYPE_SELECT_CAR){
            String a = eventData.getData();
            reserve = a;
            tvCpp1.setText(reserve);
            cppId = eventData.getId();
        }
    }
}
