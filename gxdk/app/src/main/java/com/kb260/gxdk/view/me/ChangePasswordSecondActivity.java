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
 * Created on  2017/11/9
 */
public class ChangePasswordSecondActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_me_meDetail_payPassword_changeOrForget_llChange)
    LinearLayout llChange;
    @BindView(R.id.a_me_meDetail_payPassword_change_old)
    EditText etOld;
    @BindView(R.id.a_me_meDetail_payPassword_change_new)
    EditText etNew;
    @BindView(R.id.a_me_meDetail_payPassword_change_newAgain)
    EditText etNewAgain;
    @BindView(R.id.a_me_meDetail_payPassword_change_phone)
    TextView tvPhone;

    int type;

    String oldPassword,newPassword,newPasswordAgain,phone;

    public static void start(Activity context,int type){
        Intent intent = new Intent(context,ChangePasswordSecondActivity.class);
        intent.putExtra(Action.PASSWORD_TYPE,type);
        context.startActivityForResult(intent,8);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_change_password_second;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        tvPhone.setText(KBApplication.currentPhone);
    }

    @Override
    public void initToolbar() {
        type = getIntent().getIntExtra(Action.PASSWORD_TYPE,0);
        switch (type){
            case Action.PASSWORD_CHANGE:
                toolbarTitle.setText(R.string.a_me_meDetail_payPassword_change);
                llChange.setVisibility(View.VISIBLE);
                break;
            case Action.PASSWORD_FORGET:
                toolbarTitle.setText(R.string.set_password);
                /*toolbarTitle.setText(R.string.a_me_meDetail_payPassword_forget_toolbar);*/
                llChange.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        initThisToolbarHaveBack(toolbar,this);
    }

    //保存
    @OnClick(R.id.a_me_meDetail_payPassword_change_btSave)
    public void save(){
        if (!getInput()){
            switch (type){
                case Action.PASSWORD_CHANGE:
                    upPayPw();
                    break;
                case Action.PASSWORD_FORGET:
                    forgetPayPw();
                    break;
                default:
                    break;
            }
        }
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
                        holder.setOnClickListener(R.id.ok, v -> {
                            dialog.dismiss();
                            setResult(24);
                            finish();
                        });
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    /**
     * 忘记支付密码
     */
    private void forgetPayPw() {
        Api.getDefault().forgetpaypw(KBApplication.token,KBApplication.userid, newPassword,phone)
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
    }

    /**
     * 修改支付密码
     */
    private void upPayPw() {
        Api.getDefault().uppaypw(KBApplication.token,KBApplication.userid,oldPassword, newPassword,phone)
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
    }

    private boolean getInput(){
        if (type == Action.PASSWORD_CHANGE){
            oldPassword = etOld.getText().toString().trim();
            if (StringUtils.isEmpty(oldPassword)){
                ToastUtil.showInfo("请输入原支付密码！");
                return true;
            }
            if (oldPassword.length()!=6){
                ToastUtil.showInfo("请输入6位支付密码");
                return true;
            }
        }

        newPassword = etNew.getText().toString().trim();
        if (StringUtils.isEmpty(newPassword)){
            ToastUtil.showInfo("请输入新支付密码！");
            return true;
        }

        if (newPassword.length()!=6){
            ToastUtil.showInfo("请输入6位支付密码");
            return true;
        }

        newPasswordAgain = etNewAgain.getText().toString().trim();
        if (StringUtils.isEmpty(newPasswordAgain)){
            ToastUtil.showInfo("请再次输入新支付密码！");
            return true;
        }

        if (newPasswordAgain.length()!=6){
            ToastUtil.showInfo("请输入6位支付密码！");
            return true;
        }

        if (!newPasswordAgain.equals(newPassword)){
            ToastUtil.showInfo("俩次输入不一致！");
            return true;
        }

        phone = tvPhone.getText().toString();

        return false;
    }
}
