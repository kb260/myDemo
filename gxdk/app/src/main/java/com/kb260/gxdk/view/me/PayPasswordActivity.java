package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 */
public class PayPasswordActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    public static void start(Context context){
        Intent intent = new Intent(context,PayPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_password;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_meDetail_payPassword_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    //修改密码
    @OnClick(R.id.a_me_meDetail_payPassword_change)
    public void change(){
        selPayWord(1);
    }

    //忘记密码
    @OnClick(R.id.a_me_meDetail_payPassword_forget)
    public void forget(){
        selPayWord(2);

    }

    /**
     * 查询设置支付密码
     */
    private void selPayWord(int type) {
        Api.getDefault().selpayword(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        switch (list) {
                            case "已设置支付密码":
                                switch (type){
                                    case 1:
                                        ChangePasswordSecondActivity.start(PayPasswordActivity.this,Action.PASSWORD_CHANGE);
                                        break;
                                    case 2:
                                        ChangePasswordFirstActivity.start(PayPasswordActivity.this, Action.PASSWORD_FORGET);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case "未设置支付密码":
                                ToastUtil.showInfo("请先设置支付密码！");
                                SetNewPayPasswordActivity.start(PayPasswordActivity.this);
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }
}
