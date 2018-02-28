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
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2017/11/29
 */
public class PayPasswordFirstActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    public static void start(Context context){
        Intent intent = new Intent(context,PayPasswordFirstActivity.class);
        context.startActivity(intent);
    }
    

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_password_first;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_meDetail_payPassword_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    //设置新密码
    @OnClick(R.id.a_me_meDetail_payPasswordFirst_change)
    public void change(){
        selPayWord();
    }

    //修改支付密码
    @OnClick(R.id.a_me_meDetail_payPasswordFirst_forget)
    public void forget(){
        PayPasswordActivity.start(this);
    }

    /**
     * 查询设置支付密码
     */
    private void selPayWord() {
        Api.getDefault().selpayword(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        switch (list) {
                            case "已设置支付密码":
                                ToastUtil.showInfo("您已设置过支付密码！");
                                break;
                            case "未设置支付密码":
                                SetNewPayPasswordActivity.start(PayPasswordFirstActivity.this);
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
