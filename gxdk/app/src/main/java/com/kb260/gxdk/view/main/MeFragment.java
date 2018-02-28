package com.kb260.gxdk.view.main;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.ApplicationLog;
import com.kb260.gxdk.entity.ApplicationLogNew;
import com.kb260.gxdk.model.MeHome;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.ImageLoader;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseFragment;
import com.kb260.gxdk.view.base.WebViewActivity;
import com.kb260.gxdk.view.me.AboutMeActivity;
import com.kb260.gxdk.view.me.CharacterApplicationActivity;
import com.kb260.gxdk.view.me.FeedbackActivity;
import com.kb260.gxdk.view.me.InformationActivity;
import com.kb260.gxdk.view.me.IntegralActivity;
import com.kb260.gxdk.view.me.InviteFriendActivity;
import com.kb260.gxdk.view.me.MeDetailActivity;
import com.kb260.gxdk.view.me.MyApplicationActivity;
import com.kb260.gxdk.view.me.MyProductActivity;
import com.kb260.gxdk.view.me.SetActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author KB260
 *         Created on  2017/11/14
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.f_me_civ)
    CircleImageView icon;
    @BindView(R.id.f_me_tvId)
    TextView tvId;
    @BindView(R.id.f_me_myIntegral)
    TextView tvScore;
    @BindView(R.id.f_me_tvPhone)
    TextView tvPhone;
    @BindView(R.id.f_me_wdcp)
    LinearLayout wdcp;
    @BindView(R.id.f_me_yqhy)
    LinearLayout yqhy;

    @Override
    protected int getLayoutResource() {
        return R.layout.f_me;
    }

    @Override
    protected void initView() {
        toolbarTitle.setText(R.string.f_me_toolbarTitle);

        switch (KBApplication.getLoginType()) {
            case Action.LOGIN_TYPE_BANK:
                wdcp.setVisibility(View.VISIBLE);
                yqhy.setVisibility(View.VISIBLE);
                break;
            case Action.LOGIN_TYPE_MANAGE:
                yqhy.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        initData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    //我的
    @OnClick(R.id.f_me_me)
    public void me() {
        MeDetailActivity.start(getContext());
    }

    //我的订单
    @OnClick(R.id.f_me_wddd)
    public void meWddd() {
        myOrder();
    }

    //我的订单  待支付
    @OnClick(R.id.f_me_wddd_dzf)
    public void meWdddDzf() {

    }

    //我的订单  已取消
    @OnClick(R.id.f_me_wddd_yqx)
    public void meWdddYqx() {

    }

    //我的订单  待评价
    @OnClick(R.id.f_me_wddd_dpj)
    public void meWdddDpj() {

    }

    //我的订单  待收货
    @OnClick(R.id.f_me_wddd_dsh)
    public void meWdddDsh() {

    }

    //我的申请
    @OnClick(R.id.f_me_wdsq)
    public void meApplication() {
        MyApplicationActivity.start(getContext());
    }

    //我的产品
    @OnClick(R.id.f_me_wdcp)
    public void myProject() {
        MyProductActivity.start(getContext());
    }

    //申请角色
    @OnClick(R.id.f_me_sqjs)
    public void applicationCharacter() {
        CharacterApplicationActivity.start(getContext());
    }

    //消息中心
    @OnClick(R.id.f_me_xxzx)
    public void messageCenter() {
        InformationActivity.start(getContext());
    }

    //关于我们
    @OnClick(R.id.f_me_gywm)
    public void aboutMe() {
        AboutMeActivity.start(getContext());
    }

    //意见反馈
    @OnClick(R.id.f_me_yjfk)
    public void feedback() {
        FeedbackActivity.start(getContext());
    }

    //设置
    @OnClick(R.id.f_me_sz)
    public void set() {
        MeDetailActivity.start(getContext());
        //SetActivity.start(getContext());
    }

    //邀请好友
    @OnClick(R.id.f_me_yqhy)
    public void inviteFriends() {
        InviteFriendActivity.start(getContext());
    }

    //我的积分
    @OnClick(R.id.f_me_llMyIntegral)
    public void myIntegral() {
        IntegralActivity.start(getContext());
    }

    private void initData() {
        Api.getDefault().selmyaccount(KBApplication.token, KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<MeHome>(getContext(), false) {
                    @Override
                    protected void success(MeHome list) {
                        ImageLoader.showImage(icon, list.getPicture());
                        tvId.setText(list.getAccount());
                        //tvScore.setText(String.valueOf(list.getScore()));
                        if (StringUtils.isEmpty(list.getTelphone())) {
                            tvPhone.setText("无");
                        } else {
                            tvPhone.setText(list.getTelphone());
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void myOrder() {
        Api.getDefault().selmyorder(KBApplication.token, KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(getContext()) {
                    @Override
                    protected void success(String list) {
                        WebViewActivity.start(getContext(), list, "我的订单");
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

}
