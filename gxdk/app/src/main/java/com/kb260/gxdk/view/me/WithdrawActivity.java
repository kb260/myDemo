package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.Card;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * @author  KB260
 * Created on  2017/11/13
 */
public class WithdrawActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.a_withdraw_toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_withdraw_sp)
    Spinner spinner;
    @BindView(R.id.a_rechargeWithdraw_tvIntegral)
    TextView tvAllIntegral;
    @BindView(R.id.a_rechargeWithdraw_etIntegral)
    EditText etIntegral;
    @BindView(R.id.a_rechargeWithdraw_tvBank)
    TextView tvBank;
    @BindView(R.id.a_rechargeWithdraw_tvBranch)
    TextView tvBranch;
    @BindView(R.id.a_rechargeWithdraw_ivAdd)
    LinearLayout ivAdd;
    @BindView(R.id.a_rechargeWithdraw_llBank)
    LinearLayout llBank;

    List<String> cardData;
    List<Card> cards;
    OptionsPickerView optionsPickerView;
    String bankcardno,bankname,scoretype,score;

    public static void start(Context context){
        Intent intent = new Intent(context,WithdrawActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    public void initToolbar() {
        initThisToolbarHaveBack(toolbar,this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        spinner.setOnItemSelectedListener(this);
        initPickerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initPickerView() {
        cardData = new ArrayList<>();
        optionsPickerView = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            String a = cardData.get(options1);
            String[]b = a.split("\n");
            tvBank.setText(b[0]);
            tvBranch.setText(b[1]);
            bankcardno = cards.get(options1).getBankcardno();
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
    }

    //提现记录
    @OnClick(R.id.a_withdraw_toolbarRight)
    public void log(){
        WithdrawLogActivity.start(this);
    }

    //下一步
    @OnClick(R.id.a_rechargeWithdraw_btNext)
    public void next(){
        if (!getInput()){
            get();
        }
    }

    private void queryIntegral(int type){
        Api.getDefault().getscoreable(KBApplication.token,KBApplication.userid,type)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        tvAllIntegral.setText(list);
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void get(){
        Api.getDefault().savedeposit(KBApplication.token,KBApplication.userid,bankcardno,bankname,scoretype,score)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("提交成功！");
                        finish();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void initData() {
        Api.getDefault().selbank(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<Card>>(this) {
                    @Override
                    protected void success(List<Card> list) {
                        if (list != null && list.size() > 0) {
                            cards = list;
                            llBank.setVisibility(View.VISIBLE);
                            ivAdd.setVisibility(View.GONE);
                            tvBank.setText(list.get(0).getBankname());
                            String a = list.get(0).getBankcardno();
                            bankcardno = a;
                            tvBranch.setText(showNumber(a));


                            cardData.clear();
                            for (Card card :list){
                                String b = card.getBankcardno();
                                cardData.add(card.getBankname()+"\n"+showNumber(b));
                            }
                        } else {
                            llBank.setVisibility(View.GONE);
                            ivAdd.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private boolean getInput(){
        bankname = tvBank.getText().toString().trim();
        if (StringUtils.isEmpty(bankcardno) || StringUtils.isEmpty(bankname)){
            ToastUtil.showInfo("请选择提现银行卡！");
            return true;
        }

        score = etIntegral.getText().toString().trim();
        if (StringUtils.isEmpty(score)){
            ToastUtil.showInfo("请输入提现数量！");
            return true;
        }

        if (Integer.valueOf(score)<10000){
            ToastUtil.showInfo("单笔积分必须满10000积分才能提现！");
            return true;
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int type = i+1;
        queryIntegral(type);
        scoretype = String.valueOf(type);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void pickerView(final List<String> list){
        if (optionsPickerView != null && list!=null){
            optionsPickerView.setPicker(list);
            optionsPickerView.show();
        }
    }

    @OnClick(R.id.a_rechargeWithdraw_llBank)
    public void pickBank(){
        if (cardData != null && cardData.size()>0){
            pickerView(cardData);
        }
    }

    @OnClick(R.id.a_rechargeWithdraw_ivAdd)
    public void add(){
        AddCardActivity.start(this);
    }


    private String showNumber(String bankCard) {
        int hideLength = 8;//替换位数，这里替代中间8位
        int startIndex = bankCard.length()/2-hideLength/2;
        String replaceSymbol = "*";//替换符号，这里用“#”为例
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i<bankCard.length();i++){
            char number = bankCard.charAt(i);
            if (i>=startIndex-1&&i<startIndex+hideLength){
                stringBuilder.append(replaceSymbol);
            }else {
                stringBuilder.append(number);
            }
        }
        return stringBuilder.toString();
    }
}
