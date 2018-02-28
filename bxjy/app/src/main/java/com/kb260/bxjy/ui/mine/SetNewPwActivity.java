package com.kb260.bxjy.ui.mine;

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
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.StringUtils;
import com.kb260.bxjy.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/6
 */
public class SetNewPwActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_setNewPw_etOld)
    EditText etOld;
    @BindView(R.id.a_setNewPw_etNew)
    EditText etNew;
    @BindView(R.id.a_setNewPw_etNewComfit)
    EditText etNewComfit;

    String pw1,pw2,pw3;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, SetNewPwActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_new_pw;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.set_setNewPw_toolbar);
    }

    @OnClick(R.id.a_setNewPw_tvOk)
    public void ok(){
        setNewPw();
    }

    private void setNewPw() {
        if (checkInput()){
            Api.getDefault().resetPassword(KbApplication.token,pw1,pw2,pw3)
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<String>(this) {
                        @Override
                        protected void success(String list) {
                            ToastUtil.showShout("修改成功");
                            finish();
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
        pw1 = etOld.getText().toString();
        if (StringUtils.isEmpty(pw1)){
            ToastUtil.showShout("请输入原密码");
            return false;
        }

        pw2 = etNew.getText().toString();
        if (StringUtils.isEmpty(pw2)){
            ToastUtil.showShout("请输入新密码");
            return false;
        }

        pw3 = etNewComfit.getText().toString();
        if (StringUtils.isEmpty(pw3)){
            ToastUtil.showShout("请输入确认新密码");
            return false;
        }

        return true;
    }
}
