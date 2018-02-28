package com.kb260.gxdk.view.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.Protocol;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.me.AboutMeActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/11/14
 */
public class ProtocolActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_protocol_tvContent)
    TextView tvContent;

    public static void start(Context context){
        Intent intent = new Intent(context,ProtocolActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_protocol;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        getProtocol();
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_set_about);
        initThisToolbarHaveBack(toolbar,this);
    }

    public void getProtocol(){
        Api.getDefault().selprotocol(KBApplication.token)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<Protocol>(this) {
                    @Override
                    protected void success(Protocol list) {
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
