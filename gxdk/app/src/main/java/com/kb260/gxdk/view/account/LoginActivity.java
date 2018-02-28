package com.kb260.gxdk.view.account;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.BaseModel;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.LoginSuccess;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.RegexUtils;
import com.kb260.gxdk.utils.SPUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.main.MainActivity;
import com.kb260.gxdk.view.me.ChangePasswordFirstActivity;
import com.kb260.gxdk.view.shop.LoadingDialog;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;
import java.util.Map;
import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * @author  KB260
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_login_etPhone)
    EditText etPhone;
    @BindView(R.id.a_login_etPassword)
    EditText etPassword;
    LoadingDialog dialog;

    String username,password;

    public static void start(Context context){
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        dialog = new LoadingDialog(this);

        /*username = SPUtils.getInstance().getString(Action.SP_USERNAME);
        password = SPUtils.getInstance().getString(Action.SP_PASSWORD);
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
            etPhone.setText(username);
            etPhone.setSelection(Action.SP_USERNAME.length());//将光标移至文字末尾
            etPassword.setText(password);
            login();
        }*/
        //initOtherLogin(savedInstanceState);
    }

    private void initOtherLogin(Bundle savedInstanceState) {
        UMShareAPI.get(this).fetchAuthResultWithBundle(this, savedInstanceState, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                Logger.d("onStart");
            }

            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                Logger.d("onComplete");
            }

            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                Logger.d("onError");
            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Logger.d("onCancel");
            }
        });
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.login_toolbar);
    }

    //登录
    @OnClick(R.id.a_login_btLogin)
    public void login(){
        if (getInput()){
            return;
        }
        if (KBApplication.registrationId == null){
            KBApplication.registrationId = JPushInterface.getRegistrationID(this);
        }
        Api.getDefault().applogin(username,password,KBApplication.registrationId,null,null)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<LoginSuccess>(this) {
                    @Override
                    protected void success(LoginSuccess list) {
                        KBApplication.userid = list.getId();
                        KBApplication.setLoginType(list.getType());
                        KBApplication.currentPhone = list.getUsername();
                        KBApplication.token = list.getToken();

                        SPUtils.getInstance().put(Action.SP_USERNAME,username);
                        SPUtils.getInstance().put(Action.SP_PASSWORD,password);
                        MainActivity.start(LoginActivity.this);
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    //QQ登陆
    @OnClick(R.id.a_login_ivQQ)
    public void qq(){
        UMShareAPI.get(this).doOauthVerify(LoginActivity.this, SHARE_MEDIA.QQ, authListener);
        //UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, authListener);
       /* Api.getDefault().applogin(username,password,regisonid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<LoginSuccess>(this) {
                    @Override
                    protected void success(LoginSuccess list) {
                        KBApplication.userid = list.getId();
                        MainActivity.start(LoginActivity.this,list.getType());
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError("登录失败!");
                    }
                });*/
    }

    //微信登录
    @OnClick(R.id.a_login_ivWx)
    public void wx(){
        UMShareAPI.get(this).doOauthVerify(LoginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
    }

    //其他
    @OnClick(R.id.a_login_tvOther)
    public void other(){
        RegisterActivity.start(this);
    }

    //忘记密码
    @OnClick(R.id.a_login_tvForget)
    public void forget(){
        ChangePasswordFirstActivity.start(this, Action.LOGIN_PASSWORD_FORGET);
    }


    private boolean getInput(){
        username = etPhone.getText().toString().trim();
        if (StringUtils.isEmpty(username)){
            ToastUtil.showInfo("请输入手机号码！");
            return true;
        }

        if (RegexUtils.isMobileSimple(username)){
            ToastUtil.showInfo("请输入正确的手机号码！");
            return true;
        }

        password = etPassword.getText().toString().trim();
        if (StringUtils.isEmpty(password)){
            ToastUtil.showInfo("请输入密码！");
            return true;
        }
        return false;
    }

    private void wxLogin(String uid,String qqId){
        if (KBApplication.registrationId == null){
            KBApplication.registrationId = JPushInterface.getRegistrationID(this);
        }
        Api.getDefault().applogin(null,null,KBApplication.registrationId,uid,qqId)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<LoginSuccess>(this) {
                    @Override
                    protected void success(LoginSuccess list) {
                        KBApplication.userid = list.getId();
                        KBApplication.setLoginType(list.getType());
                        KBApplication.currentPhone = list.getUsername();
                        KBApplication.token = list.getToken();

                        SPUtils.getInstance().put(Action.SP_USERNAME,username);
                        SPUtils.getInstance().put(Action.SP_PASSWORD,password);
                        MainActivity.start(LoginActivity.this);
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        switch (msg){
                            case "977777"://请先绑定QQ
                                ThreeLoginActivity.start(LoginActivity.this,uid,qqId);
                                break;
                            case "977776"://请先解绑qq
                                ToastUtil.showError("请先解绑qq");
                                break;
                            case "988888"://请先绑定微信
                                ThreeLoginActivity.start(LoginActivity.this,uid,qqId);
                                break;
                            case "988886"://请先解绑微信
                                ToastUtil.showError("请先解绑微信");
                                break;
                            default:
                                ToastUtil.showError(msg);
                                break;
                        }
                    }
                });
    }


    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            SocializeUtils.safeCloseDialog(dialog);
            Logger.d("授权成功的回调");
            String uid = data.get("unionid");
            String qqId = data.get("openid");
            switch (platform){
                case QQ:
                    wxLogin(null,qqId);
                    break;
                case WEIXIN:
                    wxLogin(uid,null);
                    break;
                default:
                    break;
            }

            //MainActivity.start(LoginActivity.this,list.getType());

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            SocializeUtils.safeCloseDialog(dialog);
            Logger.d("授权失败的回调：");
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            SocializeUtils.safeCloseDialog(dialog);
            ToastUtil.showInfo("已取消授权");
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }
}
