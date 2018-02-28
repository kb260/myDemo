package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.flyco.tablayout.SlidingTabLayout;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.MyPagerAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.Score;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.me.fragment.IntegralFragment;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
/**
 * @author  KB260
 * Created on  2017/11/15
 */
public class IntegralActivity extends BaseActivity {
    @BindView(R.id.a_integral_toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_integral_vp)
    ViewPager vp;
    @BindView(R.id.a_integral_stl)
    SlidingTabLayout stl;
    @BindView(R.id.a_integral_integral_1)
    TextView tvChargeScore;
    @BindView(R.id.a_integral_integral_2)
    TextView tvRewardScore;
    @BindView(R.id.a_integral_integral_3)
    TextView tvShopScore;

    private String[] mTitles = {"全部", "增加", "支出"};
    String rechargePoints,iconUrl,jlmx;

    public static void start(Context context){
        Intent intent = new Intent(context,IntegralActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_integral;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initVp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initVp() {
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(IntegralFragment.getInstance(Action.INTEGRAL_TYPE_ALL));
        mFragments.add(IntegralFragment.getInstance(Action.INTEGRAL_TYPE_ADD));
        mFragments.add(IntegralFragment.getInstance(Action.INTEGRAL_TYPE_EXPENDITURE));

        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager(),mFragments,mTitles);
        vp.setOffscreenPageLimit(2);
        vp.setAdapter(mAdapter);
        stl.setViewPager(vp);
    }

    @Override
    public void initToolbar() {
        initThisToolbarHaveBack(toolbar,this);
    }

    //积分规则
    @OnClick(R.id.a_integral_toolbarRight)
    public void integralRule(){
        IntegralRuleActivity.start(this);
    }

    //奖励明细
    @OnClick(R.id.a_integral_integralDetail)
    public void integralDetail(){
        MyTeamActivity.start(this);
        //RewordDetailsActivity.start(this,jlmx);
    }

    //充值
    @OnClick(R.id.a_integral_btPut)
    public void put(){
        RechargeActivity.start(this);
    }

    //提现
    @OnClick(R.id.a_integral_btGet)
    public void get(){
        WithdrawActivity.start(this);
    }

    private void initData(){
        Api.getDefault().clickmyscore(KBApplication.token,KBApplication.userid,1)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<Score>(this) {
                    @Override
                    protected void success(Score list) {
                        rechargePoints = String.valueOf(list.getChargescore());
                        iconUrl = list.getPicture();
                        tvChargeScore.setText(rechargePoints);
                        jlmx = String.valueOf(list.getRewardscore());
                        tvRewardScore.setText(jlmx);
                        tvShopScore.setText(String.valueOf(list.getShopscore()));
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

}
