package com.kb260.bxjy.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;

import com.kb260.bxjy.R;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.KeyBordUtil;
import com.kb260.bxjy.weight.dialog.NiceDialog;
import com.kb260.bxjy.weight.dialog.ViewConvertListener;

import butterknife.BindView;
import butterknife.OnClick;
import me.shihao.library.XRadioGroup;

/**
 * @author KB260
 *         Created on  2018/2/6
 */
public class RechargeActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_recharge_xrg)
    XRadioGroup xRg;
    @BindView(R.id.a_recharge_rb10)
    RadioButton rb10;
    @BindView(R.id.a_recharge_rb50)
    RadioButton rb50;
    @BindView(R.id.a_recharge_rb100)
    RadioButton rb100;
    @BindView(R.id.a_recharge_rb500)
    RadioButton rb500;
    @BindView(R.id.a_recharge_rb1000)
    RadioButton rb1000;
    @BindView(R.id.a_recharge_rb2000)
    RadioButton rb2000;
    @BindView(R.id.a_recharge_rb3000)
    RadioButton rb3000;
    @BindView(R.id.a_recharge_rb5000)
    RadioButton rb5000;
    @BindView(R.id.a_recharge_rbOther)
    RadioButton rbOther;

    EditText etMoney;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, RechargeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        xRg.setOnCheckedChangeListener((xRadioGroup, i) -> {
            if (rb10.isChecked()) {
                rb10.setTextColor(Color.WHITE);
            } else {
                rb10.setTextColor(ContextCompat.getColor(this, R.color.price_red));
            }
            if (rb50.isChecked()) {
                rb50.setTextColor(Color.WHITE);
            } else {
                rb50.setTextColor(ContextCompat.getColor(this, R.color.price_red));
            }
            if (rb100.isChecked()) {
                rb100.setTextColor(Color.WHITE);
            } else {
                rb100.setTextColor(ContextCompat.getColor(this, R.color.price_red));
            }
            if (rb500.isChecked()) {
                rb500.setTextColor(Color.WHITE);
            } else {
                rb500.setTextColor(ContextCompat.getColor(this, R.color.price_red));
            }
            if (rb1000.isChecked()) {
                rb1000.setTextColor(Color.WHITE);
            } else {
                rb1000.setTextColor(ContextCompat.getColor(this, R.color.price_red));
            }
            if (rb2000.isChecked()) {
                rb2000.setTextColor(Color.WHITE);
            } else {
                rb2000.setTextColor(ContextCompat.getColor(this, R.color.price_red));
            }
            if (rb3000.isChecked()) {
                rb3000.setTextColor(Color.WHITE);
            } else {
                rb3000.setTextColor(ContextCompat.getColor(this, R.color.price_red));
            }
            if (rb5000.isChecked()) {
                rb5000.setTextColor(Color.WHITE);
            } else {
                rb5000.setTextColor(ContextCompat.getColor(this, R.color.price_red));
            }
            if (rbOther.isChecked()) {
                rbOther.setTextColor(Color.WHITE);
                showInput();
            } else {
                rbOther.setTextColor(ContextCompat.getColor(this, R.color.price_red));
            }
            /*switch (i){
                case R.id.a_recharge_rb10:
                    if (rb10.isChecked()){
                        rb10.setTextColor(Color.WHITE);
                    }else {
                        rb10.setTextColor(ContextCompat.getColor(this,R.color.price_red));
                    }
                    break;
                case R.id.a_recharge_rb50:
                    if (rb50.isChecked()){
                        rb50.setTextColor(Color.WHITE);
                    }else {
                        rb50.setTextColor(ContextCompat.getColor(this,R.color.price_red));
                    }
                    break;
                case R.id.a_recharge_rb100:
                    if (rb100.isChecked()){
                        rb100.setTextColor(Color.WHITE);
                    }else {
                        rb100.setTextColor(ContextCompat.getColor(this,R.color.price_red));
                    }
                    break;
                case R.id.a_recharge_rb500:
                    if (rb500.isChecked()){
                        rb500.setTextColor(Color.WHITE);
                    }else {
                        rb500.setTextColor(ContextCompat.getColor(this,R.color.price_red));
                    }
                    break;
                case R.id.a_recharge_rb1000:
                    if (rb1000.isChecked()){
                        rb1000.setTextColor(Color.WHITE);
                    }else {
                        rb1000.setTextColor(ContextCompat.getColor(this,R.color.price_red));
                    }
                    break;
                case R.id.a_recharge_rb2000:
                    if (rb2000.isChecked()){
                        rb2000.setTextColor(Color.WHITE);
                    }else {
                        rb2000.setTextColor(ContextCompat.getColor(this,R.color.price_red));
                    }
                    break;
                case R.id.a_recharge_rb3000:
                    if (rb3000.isChecked()){
                        rb3000.setTextColor(Color.WHITE);
                    }else {
                        rb3000.setTextColor(ContextCompat.getColor(this,R.color.price_red));
                    }
                    break;
                case R.id.a_recharge_rb5000:
                    if (rb5000.isChecked()){
                        rb5000.setTextColor(Color.WHITE);
                    }else {
                        rb5000.setTextColor(ContextCompat.getColor(this,R.color.price_red));
                    }
                    break;
                case R.id.a_recharge_rbOther:
                    if (rbOther.isChecked()){
                        rbOther.setTextColor(Color.WHITE);
                    }else {
                        rbOther.setTextColor(ContextCompat.getColor(this,R.color.price_red));
                    }
                    break;
                default:
                    break;
            }*/

        });
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.wallet_recharge);
    }

    @OnClick(R.id.payType_ali)
    public void aliPay() {

    }

    @OnClick(R.id.payType_wx)
    public void wxPay() {

    }

    @OnClick(R.id.payType_bank)
    public void bankPay() {

    }

    public void showInput() {
        NiceDialog.init()
                .setLayoutId(R.layout.input_money1)
                .setConvertListener((ViewConvertListener) (holder, dialog) -> {
                    etMoney = holder.getView(R.id.inputMoney_et);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setFocus();
                        }
                    }, 100);
                    holder.setOnClickListener(R.id.inputMoney_cancel, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            KeyBordUtil.hitKeyBord(etMoney,RechargeActivity.this);
                            xRg.clearCheck();
                            rbOther.setText("其他");
                            dialog.dismiss();
                        }
                    });
                    holder.setOnClickListener(R.id.inputMoney_ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            KeyBordUtil.hitKeyBord(etMoney,RechargeActivity.this);
                            dialog.dismiss();
                            String money = etMoney.getText().toString()+"元";
                            rbOther.setText(money);
                        }
                    });
                })
                .setOutCancel(false)
                .setMargin(40)
                .show(getSupportFragmentManager());

    }

    public void setFocus() {
        etMoney.requestFocus();
        etMoney.setFocusable(true);
        KeyBordUtil.showKeyBord(etMoney,this);
    }

}
