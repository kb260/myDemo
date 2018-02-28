package com.kb260.gxdk.view.me;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.CharacterApplicationData;
import com.kb260.gxdk.entity.JsonBean;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.GetJsonDataUtil;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.base.WebViewActivity;
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
 * @author KB260
 *         Created on  2017/11/29
 */
public class CharacterApplicationNextActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_characterApplicationNext_tvCity)
    TextView tvCity;
    @BindView(R.id.a_characterApplicationNext_etSocialSecurity)
    EditText etSocialSecurity;
    @BindView(R.id.a_characterApplicationNext_etSocialSecurityPassword)
    EditText etSocialSecurityPassword;
    @BindView(R.id.a_characterApplicationNext_etProvidentFund)
    EditText etProvidentFund;
    @BindView(R.id.a_characterApplicationNext_etProvidentFundPassword)
    EditText etProvidentFundPassword;
    @BindView(R.id.a_characterApplicationNext_cb)
    CheckBox cb;

    GetJsonDataUtil getJsonDataUtil;
    OptionsPickerView pvOptions;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    String province, city, sbzh, sbmm, gjjzh, gjjmm;
    CharacterApplicationData data;

    public static void start(Activity context, CharacterApplicationData applicationData) {
        Intent intent = new Intent(context, CharacterApplicationNextActivity.class);
        intent.putExtra(Action.CHARACTER_APPLICATION,applicationData);
        context.startActivityForResult(intent,23);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_character_application_next;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        data = (CharacterApplicationData) getIntent().getSerializableExtra(Action.CHARACTER_APPLICATION);
        initAddressDialog();
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText("认证");
        initThisToolbarHaveBack(toolbar, this);
    }

    //所在城市
    @OnClick(R.id.a_characterApplicationNext_llCity)
    public void city() {
        initCityOptionPicker();
    }

    //授权协议
    @OnClick(R.id.a_characterApplicationNext_tvProtocol)
    public void protocol() {
        WebViewActivity.start(this,"file:///android_asset/authorized.html","授权协议");
    }

    //提交
    @OnClick(R.id.a_characterApplicationNext_btSubmit)
    public void submit() {
        if (getInput()) {
            return;
        }
        if (data.isChange()) {
            Api.getDefault().updataapply(KBApplication.token, KBApplication.userid, data.getId(), data.getStatus(),
                    data.getRealname(), data.getBank(), data.getBranchname(), data.getBankaddress(), data.getCompany(),
                    data.getCompanyaddress(), data.getTelphone(),province,city,gjjzh,
                    gjjmm,sbmm,sbzh)
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<String>(this) {
                        @Override
                        protected void success(String list) {
                            ToastUtil.showSuccess("信息修改成功！");
                            setResult(25);
                            finish();
                        }

                        @Override
                        protected void error(String msg) {
                            Logger.d(msg);
                            ToastUtil.showError(msg);
                        }
                    });
        } else {
            switch (data.getType()) {
                case 0:
                    Api.getDefault().makeagent(KBApplication.token, KBApplication.userid, data.getCompany(),
                            data.getCompanyaddress(),data.getRealname(), data.getTelphone(),province,city,gjjzh,
                            gjjmm,sbmm,sbzh)
                            .compose(RxHelper.handleResult())
                            .subscribe(new RxSubscriber<String>(this) {
                                @Override
                                protected void success(String list) {
                                    ToastUtil.showSuccess("信息提交成功！");
                                    setResult(25);
                                    finish();
                                }

                                @Override
                                protected void error(String msg) {
                                    Logger.d(msg);
                                    ToastUtil.showError(msg);
                                }
                            });
                    break;
                case 1:
                    Api.getDefault().makebank(KBApplication.token, KBApplication.userid, data.getBranchname(),
                            data.getBankaddress(), data.getBank(), data.getRealname(), data.getTelphone()
                            ,province,city,gjjzh,
                            gjjmm,sbmm,sbzh)
                            .compose(RxHelper.handleResult())
                            .subscribe(new RxSubscriber<String>(this) {
                                @Override
                                protected void success(String list) {
                                    ToastUtil.showSuccess("信息提交成功！");
                                    setResult(25);
                                    finish();
                                }

                                @Override
                                protected void error(String msg) {
                                    Logger.d(msg);
                                    ToastUtil.showError(msg);
                                }
                            });
                    break;
                default:
                    break;
            }
        }
    }

    private boolean getInput() {
        if (StringUtils.isEmpty(province) || StringUtils.isEmpty(city)) {
            ToastUtil.showInfo("请选择所在城市！");
            return true;
        }

        sbzh = etSocialSecurity.getText().toString();
        if (StringUtils.isEmpty(sbzh)) {
            ToastUtil.showInfo("请输入社保账户！");
            return true;
        }
        sbmm = etSocialSecurityPassword.getText().toString();
        if (StringUtils.isEmpty(sbmm)) {
            ToastUtil.showInfo("请输入社保密码！");
            return true;
        }
        gjjzh = etProvidentFund.getText().toString();
        if (StringUtils.isEmpty(gjjzh)) {
            ToastUtil.showInfo("请输入公积金账户！");
            return true;
        }
        gjjmm = etProvidentFundPassword.getText().toString();
        if (StringUtils.isEmpty(gjjmm)) {
            ToastUtil.showInfo("请输入公积金密码！");
            return true;
        }

        if (!cb.isChecked()){
            ToastUtil.showInfo("需要同意《授权协议》！");
            return true;
        }
        return false;
    }

    private void initCityOptionPicker() {
        Observable.create((ObservableOnSubscribe<Integer>) e -> {
            if (getJsonDataUtil.isLoaded) {
                e.onNext(1);
            } else {
                getJsonDataUtil.initJsonData(CharacterApplicationNextActivity.this, options1Items, options2Items, e);
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
        pvOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            //返回的分别是三个级别的选中位置
            province = options1Items.get(options1).getPickerViewText();
            city = options2Items.get(options1).get(options2);
            String tx = province + " " + city;
            tvCity.setText(tx);

        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build();
    }
}
