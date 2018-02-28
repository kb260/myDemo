package com.kb260.bxjy.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kb260.bxjy.R;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.weight.PayPwdEditText;
import com.kb260.bxjy.weight.dialog.NiceDialog;
import com.kb260.bxjy.weight.dialog.ViewConvertListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/6
 */
public class ReflectActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_reflect_tvBank)
    TextView tvBank;
    @BindView(R.id.a_reflect_etMoney)
    EditText etMoney;
    @BindView(R.id.a_reflect_tvBalanceAll)
    TextView tvBalanceAll;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, ReflectActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_reflect;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.wallet_reflect);
    }

    @OnClick(R.id.a_reflect_tvBank)
    public void bank(){

    }

    @OnClick(R.id.a_reflect_balanceAll)
    public void balanceAll(){
        etMoney.setText(tvBalanceAll.getText());
        etMoney.setSelection(etMoney.length());
    }

    @OnClick(R.id.a_reflect_reflect)
    public void reflect(){
        inputPw();
    }

    public void inputPw(){
        NiceDialog.init()
                .setLayoutId(R.layout.input_pw)
                .setConvertListener((ViewConvertListener) (holder, dialog) -> {
                    PayPwdEditText payPwdEditText = holder.getView(R.id.inputPw_ppet);
                    payPwdEditText.initStyle(R.drawable.edit_num_bg, 6,
                            0f, R.color.colorAccent, R.color.colorAccent, 30);
                    payPwdEditText.setShowPwd(true);
                    payPwdEditText.setOnTextFinishListener(new PayPwdEditText.OnTextFinishListener() {
                        @Override
                        public void onFinish(String str) {//密码输入完后的回调
                            holder.setBackgroundResource(R.id.inputPw_reflect,R.drawable.wallet_bt);
                        }
                    });
                    payPwdEditText.setOnTextUnFinishListener(new PayPwdEditText.OnTextUnFinishListener() {
                        @Override
                        public void onUnFinish(String str) {
                            holder.setBackgroundResource(R.id.inputPw_reflect,R.drawable.inputpw_0);
                        }
                    });

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            payPwdEditText.setFocus();
                        }
                    }, 100);

                    holder.setOnClickListener(R.id.inputPw_reflect, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            holder.setVisibility(R.id.inputPw_tvPwError,View.VISIBLE);
                        }
                    });
                    holder.setOnClickListener(R.id.inputPw_cancel, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                })
                .setOutCancel(false)
                .setMargin(40)
                .show(getSupportFragmentManager());
    }

}
