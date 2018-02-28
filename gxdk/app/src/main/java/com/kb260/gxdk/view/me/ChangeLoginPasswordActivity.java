package com.kb260.gxdk.view.me;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2017/11/1
 */
public class ChangeLoginPasswordActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_me_meDetail_passwordSet_changeOrForget_old)
    LinearLayout llOld;
    @BindView(R.id.a_me_meDetail_passwordSet_change_old)
    EditText etPasswordOld;
    @BindView(R.id.a_me_meDetail_passwordSet_change_new)
    EditText etPasswordNew1;
    @BindView(R.id.a_me_meDetail_passwordSet_change_newAgain)
    EditText etPasswordNew2;

    int type;
    String username,passwordOld,passwordNew1,passwordNew2;

    public static void start(Activity context, int type,String username){
        Intent intent = new Intent(context,ChangeLoginPasswordActivity.class);
        intent.putExtra(Action.PASSWORD_TYPE,type);
        intent.putExtra(Action.PASSWORD_USERNAME,username);
        context.startActivityForResult(intent,8);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_login_password;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
    }

    private void initIntent() {

    }

    @Override
    public void initToolbar() {
        Intent intent = getIntent();
        type = intent.getIntExtra(Action.PASSWORD_TYPE,0);
        username = intent.getStringExtra(Action.PASSWORD_USERNAME);
        switch (type){
            case Action.PASSWORD_CHANGE:
                toolbarTitle.setText(R.string.a_me_meDetail_passwordSet_change);
                break;
            case Action.PASSWORD_FORGET:
                toolbarTitle.setText(R.string.a_me_meDetail_passwordSet_forget);
                llOld.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        initThisToolbarHaveBack(toolbar,this);
    }

    //保存
    @OnClick(R.id.a_me_meDetail_passwordSet_change_btSave)
    public void save(){
        if (getInput()){
            return;
        }
        switch (type){
            case Action.PASSWORD_CHANGE:
                Api.getDefault().modifypassword(KBApplication.token,KBApplication.currentPhone,passwordOld,passwordNew2)
                        .compose(RxHelper.handleResult())
                        .subscribe(new RxSubscriber<String>(this) {
                            @Override
                            protected void success(String list) {
                                showDialog();
                            }

                            @Override
                            protected void error(String msg) {
                                Logger.d(msg);
                                ToastUtil.showError(msg);
                            }
                        });
                break;
            case Action.PASSWORD_FORGET:
                Api.getDefault().forgetpassword(KBApplication.token,username,passwordNew2)
                        .compose(RxHelper.handleResult())
                        .subscribe(new RxSubscriber<String>(this) {
                            @Override
                            protected void success(String list) {
                                showDialog();
                            }

                            @Override
                            protected void error(String msg) {
                                Logger.d(msg);
                                ToastUtil.showError(msg);
                            }
                        });
                break;
            default:
                break;
        }

    }

    private boolean getInput(){
        if (type== Action.PASSWORD_CHANGE){
            passwordOld = etPasswordOld.getText().toString().trim();
            if (StringUtils.isEmpty(passwordOld)){
                ToastUtil.showInfo("请输入原始密码！");
                return true;
            }
        }

        passwordNew1 = etPasswordNew1.getText().toString().trim();
        if (StringUtils.isEmpty(passwordNew1)){
            ToastUtil.showInfo("请输入新密码！");
            return true;
        }

        if (passwordNew1.length()<6 || passwordNew1.length()>16){
            ToastUtil.showInfo("密码应设置为6-16位数字加字符");
            return true;
        }

        passwordNew2 = etPasswordNew2.getText().toString().trim();
        if (StringUtils.isEmpty(passwordNew1)){
            ToastUtil.showInfo("请再次输入新密码！");
            return true;
        }

        if (passwordNew2.length()<6 || passwordNew2.length()>16){
            ToastUtil.showInfo("密码应设置为6-16位数字加字符");
            return true;
        }

        if (!passwordNew1.equals(passwordNew2)){
            ToastUtil.showInfo("密码不一致！");
            return true;
        }
        return false;
    }

    public void showDialog() {
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setVisibility(R.id.cancel,View.GONE);
                        holder.setVisibility(R.id.message,View.GONE);
                        holder.setViewVisibility(R.id.cancel_divider,View.GONE);
                        holder.setText(R.id.title, "修改密码成功！");
                        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                setResult(24);
                                finish();
                            }
                        });
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }
}
