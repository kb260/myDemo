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
import com.kb260.gxdk.model.AboutApp;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/11/13
 */
public class AboutAppActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_aboutApp_tvContent)
    TextView tvContent;

    public static void start(Context context){
        Intent intent = new Intent(context,AboutAppActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_app;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initData();
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_set_about);
        initThisToolbarHaveBack(toolbar,this);
    }

    private void initData() {
        Api.getDefault().selaboutgxdk(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<AboutApp>(this) {
                    @Override
                    protected void success(AboutApp list) {
                        tvContent.setText(list.getContent());
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }
}
