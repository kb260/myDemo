package com.kb260.bxjy.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.kb260.bxjy.R;
import com.kb260.bxjy.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/6
 */
public class AccountSefeActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, AccountSefeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_sefe;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.set_accountSafe);
    }

    @OnClick(R.id.a_accountSafe_llChangePw)
    public void changePw(){
        SetNewPwActivity.start(this);
    }


}
