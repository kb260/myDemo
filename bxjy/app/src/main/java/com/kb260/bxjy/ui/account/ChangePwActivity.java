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
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.StringUtils;
import com.kb260.bxjy.utils.ToastUtil;
import com.kb260.bxjy.weight.dialog.ConfirmDialog;
import com.orhanobut.logger.Logger;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/5
 */
public class ChangePwActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_changePw_etPw)
    EditText etPw;
    @BindView(R.id.a_changePw_etPw2)
    EditText etPw2;

    String pw1,pw2,phone,code;

    /**
     *开启页面
     */
    public static void start(Context context){
        Intent intent = new Intent(context,ChangePwActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_pw;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.account_forgetPwToolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    @OnClick(R.id.a_changePw_sure)
    public void sure(){
        if (checkInput()){
            Api.getDefault().setNewPassword(phone, pw1,pw2)
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<String>(this) {
                        @Override
                        protected void success(String list) {
                            showDialog();
                        }

                        @Override
                        protected void error(String msg) {
                            Logger.d(msg);
                            ToastUtil.showShout(msg);
                        }
                    });
        }
    }

    public void showDialog(){
        ConfirmDialog.newInstance(Action.DIALOG_TYPE_CHANGE_PW)
                .setMargin(50)
                .setOutCancel(false)
                .show(getSupportFragmentManager());
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
