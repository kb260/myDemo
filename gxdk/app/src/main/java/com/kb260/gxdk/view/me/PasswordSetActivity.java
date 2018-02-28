package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.view.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;
/**
 * @author  KB260
 * Created on  2017/11/20
 */
public class PasswordSetActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    public static void start(Context context){
        Intent intent = new Intent(context,PasswordSetActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_password_set;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_meDetail_passwordSet_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    //修改密码
    @OnClick(R.id.a_me_meDetail_passwordSet_change)
    public void change(){
        ChangeLoginPasswordActivity.start(this, Action.PASSWORD_CHANGE,null);
    }

    //忘记密码
    @OnClick(R.id.a_me_meDetail_passwordSet_forget)
    public void forget(){
        ChangePasswordFirstActivity.start(this, Action.LOGIN_PASSWORD_FORGET);
    }
}
