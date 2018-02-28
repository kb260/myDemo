package com.kb260.bxjy.ui.account;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import com.kb260.bxjy.R;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.model.entity.UserData;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.StringUtils;
import com.kb260.bxjy.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/5
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_register_etPw)
    EditText etPw;
    @BindView(R.id.a_register_etPw2)
    EditText etPw2;

    String pw1,pw2;
    String phone,code;

    /**
     *开启页面
     */
    public static void start(Context context,String phone,String code){
        Intent intent = new Intent(context,RegisterActivity.class);
        intent.putExtra(Action.PHONE,phone);
        intent.putExtra(Action.CODE,code);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_regiser;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        phone = intent.getStringExtra(Action.PHONE);
        code = intent.getStringExtra(Action.CODE);
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.account_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    @OnClick(R.id.a_register_sure)
    public void sure(){
        if (checkInput()){
            Api.getDefault().register(phone, pw1,pw2,code)
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<UserData>(this) {
                        @Override
                        protected void success(UserData list) {
                            ToastUtil.showShout("注册成功！");
                            LoginCodeActivity.start(RegisterActivity.this);

                        }

                        @Override
                        protected void error(String msg) {
                            Logger.d(msg);
                            ToastUtil.showShout(msg);
                        }
                    });
        }
    }

    private boolean checkInput(){
        pw1 = etPw.getText().toString();
        if (StringUtils.isEmpty(pw1)){
            ToastUtil.showShout("请输入密码");
            return false;
        }

        pw2 = etPw2.getText().toString();
        if (StringUtils.isEmpty(pw2)){
            ToastUtil.showShout("请输入确认密码");
            return false;
        }

        if (!pw1.equals(pw2)){
            ToastUtil.showShout("俩次输入密码不一致");
            return false;
        }
        return true;
    }

}
