package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.widget.PayPwdEditText;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2017/11/29
 */
public class SetNewPayPasswordActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.a_setNewPayPw_ppet)
    PayPwdEditText payPwdEditText;



    public static void start(Context context){
        Intent intent = new Intent(context,SetNewPayPasswordActivity.class);
        context.startActivity(intent);
    }
  

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_new_pay_password;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        payPwdEditText.initStyle(R.drawable.edit_num_bg_red, 6, 0.33f, R.color.colorAccent, R.color.colorAccent, 20);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText("设置支付密码");
        initThisToolbarHaveBack(toolbar,this);
    }

    //确认
    @OnClick(R.id.a_setNewPayPw_btSubmit)
    public void submit(){
        String newPassword = payPwdEditText.getPwdText();
        if (StringUtils.isEmpty(newPassword) || newPassword.length()!=6){
            ToastUtil.showInfo("请输入支付密码");
        }else {
            forgetPayPw(newPassword);
        }
    }

    /**
     * 忘记支付密码
     */
    private void forgetPayPw(String newPassword) {
        Api.getDefault().forgetpaypw(KBApplication.token,KBApplication.userid, newPassword,KBApplication.currentPhone)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("设置成功！");
                        finish();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }
}
