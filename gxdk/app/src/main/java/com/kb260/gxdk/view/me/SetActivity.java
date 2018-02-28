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
import com.kb260.gxdk.model.Version;
import com.kb260.gxdk.utils.AppUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 */
public class SetActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_set_version)
    TextView tvVersion;

    public static void start(Context context){
        Intent intent = new Intent(context,SetActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        String version = "V" + AppUtils.getAppVersionName();
        tvVersion.setText(version);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.f_me_sz);
        initThisToolbarHaveBack(toolbar,this);
    }

   /* //分享
    @OnClick(R.id.a_set_share)
    public void share(){
    }*/

    //关于
    @OnClick(R.id.a_set_about)
    public void about(){
        AboutAppActivity.start(this);
    }

    //版本
    @OnClick(R.id.a_set_llVersion)
    public void version(){
        initData();
    }

    private void initData() {
        Api.getDefault().selversion(KBApplication.token,KBApplication.userid,"android")
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<Version>(this) {
                    @Override
                    protected void success(Version list) {
                        if (list != null){
                            //ToastUtil.showInfo("最新版本为："+ list.getCurrentversion());
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
