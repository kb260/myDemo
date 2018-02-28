package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.kb260.gxdk.R;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.widget.PayPwdEditText;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/11/9
 */
public class SetPayPasswordActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_setPayPassword_ppet)
    PayPwdEditText payPwdEditText;

    public static void start(Context context){
        Intent intent = new Intent(context,SetPayPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_pay_password;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initPPEt();
    }

    private void initPPEt() {
        payPwdEditText.initStyle(R.drawable.edit_num_bg, 6, 0.33f, R.color.txt999, R.color.txt999, 20);
        payPwdEditText.setOnTextFinishListener(new PayPwdEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {//密码输入完后的回调
                Toast.makeText(SetPayPasswordActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_setPayPassword_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }
}
