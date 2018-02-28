package com.kb260.bxjy.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import com.kb260.bxjy.R;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Contact;
import com.kb260.bxjy.utils.CountDownTimerUtils;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/6
 */
public class AddBankCardActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_addBankCard_etName)
    EditText etName;
    @BindView(R.id.a_addBankCard_etIdCard)
    EditText etIdCard;
    @BindView(R.id.a_addBankCard_etCardNum)
    EditText etCardNum;
    @BindView(R.id.a_addBankCard_etBank)
    EditText etBank;
    @BindView(R.id.a_addBankCard_etBranch)
    EditText etBranch;
    @BindView(R.id.a_addBankCard_etPhone)
    EditText etPhone;
    @BindView(R.id.a_addBankCard_etCode)
    EditText etCode;
    @BindView(R.id.a_addBankCard_tvGetCode)
    TextView tvGetCode;

    CountDownTimerUtils countDownTimerUtils;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, AddBankCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        countDownTimerUtils = new CountDownTimerUtils(tvGetCode, Contact.CODE_TIME, 1000,R.color.white);
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.add_bankCard);
    }

    @OnClick(R.id.a_addBankCard_tvGetCode)
    public void getCode(){
        countDownTimerUtils.start();
    }

    @OnClick(R.id.a_addBankCard_tvAdd)
    public void add(){
        finish();
    }
}
