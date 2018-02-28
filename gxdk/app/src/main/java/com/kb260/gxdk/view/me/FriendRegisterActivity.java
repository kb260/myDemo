package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.view.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class FriendRegisterActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    public static void start(Context context){
        Intent intent = new Intent(context,FriendRegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_friend_register;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_inviteFriend_friendRegister);
        initThisToolbarHaveBack(toolbar,this);
    }

    //注册
    @OnClick(R.id.a_friendRegister_btRegister)
    public void register(){
        finish();
    }

    //获取验证码
    @OnClick(R.id.a_friendRegister_btGetCode)
    public void getCode(){

    }
}
