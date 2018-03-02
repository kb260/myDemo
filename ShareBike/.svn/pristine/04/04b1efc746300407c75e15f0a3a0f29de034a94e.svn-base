package com.panda.sharebike.ui.selfinfo;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.LogUtils;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.model.entity.MemberInfo;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.login.LoginByPhoneActivity;
import com.panda.sharebike.ui.login.RegisterActivity;
import com.panda.sharebike.ui.ride.ItineraryListActivity;
import com.panda.sharebike.util.ImageLoaderUtils;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PersonalActivity extends BaseActivity {
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.llayout_login)
    LinearLayout llayoutLogin;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_right_use)
    TextView tvRightUse;
    @BindView(R.id.rl_guide)
    RelativeLayout rlGuide;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.iv_right_back)
    ImageView ivRightBack;
    @BindView(R.id.ll_iphone)
    LinearLayout llIphone;
    private boolean state = true;
    private static final int RESULT_PERSON_ACTIVITY = 1001;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_personal;
    }



    private void initData(MemberInfo memberInfo) {
        int userTimeH = 0;
        int userTimeM = 0;
        llIphone.setVisibility(View.VISIBLE);
        ImageLoaderUtils.displayRound(this, ivAvatar, memberInfo.getMember().getAvatar());//头像
        // userTimeH = (int) (memberInfo.getMemberRideTimeStat() / 1000 / 60 / 60);//小时
        int userTime = (int) (memberInfo.getMemberRideTimeStat() / 1000);
        LogUtils.e(memberInfo.getMemberRideTimeStat() + "");
        String time = getFriendlyTimeForSecond(userTime);
        tvRightUse.setText(time);
//        if (0 == userTimeH) {
//            userTimeM = (int) (((memberInfo.getMemberRideTimeStat() / 1000) % 3600) / 60);//分钟
//            double m = userTimeM / 100;
//            m = Math.floor(m * 10) / 10;
//            tvRightUse.setText(m + "h");
//        } else {
//            tvRightUse.setText(userTimeH + "h");
//        }

        //  tvRightUse.setText(userTime + "h");
        if (EmptyUtils.isNotEmpty(memberInfo)) {

            llayoutLogin.setVisibility(View.GONE);
            tvNickname.setVisibility(View.VISIBLE);
            ivRightBack.setVisibility(View.VISIBLE);
            //tvNickname.setText(RegexUtil.phoneHide(memberInfo.getMember().getCellphone()));
            tvNickname.setText(memberInfo.getMember().getNickname());
        } else {
            llayoutLogin.setVisibility(View.VISIBLE);
            tvNickname.setVisibility(View.GONE);
            ivRightBack.setVisibility(View.GONE);
        }
        if (0.0 != memberInfo.getMemberBeenz()) {
            tvMoney.setVisibility(View.VISIBLE);
            DecimalFormat df = new DecimalFormat("######0.00");
            tvMoney.setText("￥" + df.format(memberInfo.getMemberBeenz()));
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getHttpMemberInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getHttpMemberInfo();
    }


    @Override
    protected void setUpView() {
        super.setUpView();
        hideTitleBar();
    }

    @OnClick({R.id.iv_back, R.id.iv_avatar, R.id.tv_login, R.id.tv_register, R.id.tv_right_use,
            R.id.rl_schedule, R.id.rl_wallet, R.id.rl_account_manager, R.id.rl_guide, R.id.rl_invite, R.id.rl_setup, R.id.iv_message, R.id.ll_iphone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_iphone:
                if (state) {
                    startActivityForResult(new Intent(this, SelfInfoActivity.class), RESULT_PERSON_ACTIVITY);
                }
                break;
            case R.id.iv_avatar:
//                if (state) {
//                    startActivityForResult(new Intent(this, SelfInfoActivity.class), RESULT_PERSON_ACTIVITY);
//                }
                break;
            case R.id.tv_login:
                startActivity(new Intent(this, LoginByPhoneActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_right_use:
                break;
            case R.id.rl_schedule://我的行程
                if (state) {
                    startActivity(ItineraryListActivity.class);
                } else {
                    startActivity(LoginByPhoneActivity.class);
                }
                break;
            case R.id.rl_wallet:
                if (state) {
                    startActivity(new Intent(this, MyWalletActivity.class));
                } else {
                    startActivity(LoginByPhoneActivity.class);
                }
                break;
            case R.id.rl_account_manager://账户管理
                if (state) {
                    startActivity(new Intent(this, AccountManagerActivity.class));
                } else {
                    startActivity(LoginByPhoneActivity.class);
                }
                break;
            case R.id.rl_guide:
                startActivity(new Intent(this, userGuideActivity.class));
                break;
            case R.id.rl_invite:
                startActivity(UserShareBikeActivity.class);
                break;
            case R.id.iv_message://我的消息
                if (state) {
                    startActivity(MessageListActivity.class);
                } else {
                    startActivity(LoginByPhoneActivity.class);
                }
                break;
            case R.id.rl_setup:
                Intent intent = new Intent(this, setUpActivity.class);
                intent.putExtra("state", state);
                startActivity(intent);
//                startActivity(new Intent(this, setUpActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (EmptyUtils.isNotEmpty(data)) {
            int result = data.getExtras().getInt("result", 0);
            if (result == 1) {
                getHttpMemberInfo();
            }
        }
    }
    /**
     * 获取会员中心信息
     */
    private void getHttpMemberInfo() {
        Api.getInstance().getDefault().getMemberCenter(Config.TOKEN, Api.getCacheControl())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<MemberInfo>>(new SubscriberListener<HttpResult<MemberInfo>>() {
                    @Override
                    public void onSuccess(HttpResult<MemberInfo> model) {
                        //app.setUserInfo(model.getData());//存储用户信息
                        if (model.isOk()) {
                            state = true;
                            initData(model.getData());
                            //  ObjectSaveUtil.saveObject(PersonalActivity.this, model.getData());
                        }
                        if (401 == model.getCode()) {
                            state = false;
                            llIphone.setVisibility(View.GONE);
                            ivAvatar.setImageResource(R.drawable.icon_default);
                            tvRightUse.setText(0 + "h");
                            llayoutLogin.setVisibility(View.VISIBLE);
                            tvNickname.setVisibility(View.GONE);
                            ivRightBack.setVisibility(View.GONE);
                            tvMoney.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, false));
    }

    /**
     * 测试大哥说要精确到秒
     */

    public String getFriendlyTimeForSecond(int second) {
        DecimalFormat df = new DecimalFormat("######0.0");
        if (second > 3600) {
            double hour = second / 3600;
            int miniate = (second % 3600) / 60;
            double m = miniate / 60.00;
            String k = df.format(hour + m);
            int idx = k.lastIndexOf(".");
            String h = k.substring(0, idx + 2);

            return k + "h";
        }
        if (second >= 60) {
            int miniate = second / 60;
            double v = miniate / 60.00;
            String s = String.valueOf(v);
            int idx = s.lastIndexOf(".");
            String m = s.substring(0, idx + 2);

            return m + "h";
        }
        return 0 + "h";
    }
}
