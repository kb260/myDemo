package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.RewordDetailsAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.MyTeamOne;
import com.kb260.gxdk.entity.PlateDetailThree;
import com.kb260.gxdk.entity.PlateDetailThreeSection;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.DividerItemDecoration;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.base.WebViewActivity;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InviteFriendActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_inviteFriend_rv)
    RecyclerView rv;

    RewordDetailsAdapter adapter;
    List<MyTeamOne.DataBean> data;

    public static void start(Context context){
        Intent intent = new Intent(context,InviteFriendActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invide_friend;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
        initData();
    }

    private void initData() {
        Api.getDefault().selmyteam(KBApplication.token, KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<MyTeamOne.DataBean>>(this) {
                    @Override
                    protected void success(List<MyTeamOne.DataBean> list) {
                        if (list != null && list.size() > 0) {
                            data = list;
                            adapter.setNewData(data);
                        } else {
                            adapter.setEmptyView(R.layout.empty_view, (ViewGroup) rv.getParent());
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }



    private void initRv() {
        data = new ArrayList<>();

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider)));
        adapter = new RewordDetailsAdapter(data);
        rv.setAdapter(adapter);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.f_me_yqhy);
        initThisToolbarHaveBack(toolbar,this);
    }

    //申请
    @OnClick(R.id.a_inviteFriend_btApplication)
    public void application(){
        //InviteRegisterActivity.start(this,-1);
    }

    //二维码
    @OnClick(R.id.a_inviteFriend_llQRCode)
    public void qrCode(){
        share();
    }

    //帮好友注册
    @OnClick(R.id.a_inviteFriend_friendRegister)
    public void friendRegister(){
        InviteRegisterActivity.start(this, Contact.REGISTER_TYPE_USER);
    }

    //代理商
    @OnClick(R.id.a_inviteFriend_Agents)
    public void agents(){
        /*AgentsActivity.start(this);*/
        InviteRegisterActivity.start(this, Contact.REGISTER_TYPE_AGENTS);
    }

    //银行客户经理
    @OnClick(R.id.a_inviteFriend_bankManager)
    public void bankManager(){
        /*BankManagerActivity.start(this);*/
        InviteRegisterActivity.start(this, Contact.REGISTER_TYPE_BANK);
    }


    private void share(){
        Api.getDefault().share(KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        if (list != null ){
                            WebViewActivity.start(InviteFriendActivity.this,list,"二维码邀请");
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
