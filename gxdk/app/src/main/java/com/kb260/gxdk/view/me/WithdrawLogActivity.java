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
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.me.fragment.WithdrawFragment;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import butterknife.BindView;
/**
 * @author  KB260
 * Created on  2017/11/13
 */
public class WithdrawLogActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.a_withdrawLog_vp)
    ViewPager vp;
    @BindView(R.id.a_withdrawLog_stl)
    SlidingTabLayout stl;

    private ArrayList<Fragment> mFragments;
    private String[] mTitles = {"提现完成", "申请提现中","已拒绝"};

    public static void start(Context context){
        Intent intent = new Intent(context,WithdrawLogActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw_log;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mFragments = new ArrayList<>();
        mFragments.add(WithdrawFragment.getInstance(Action.WITHDRAW_TYPE_0));
        mFragments.add(WithdrawFragment.getInstance(Action.WITHDRAW_TYPE_1));
        mFragments.add(WithdrawFragment.getInstance(Action.WITHDRAW_TYPE_2));

        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager(),mFragments,mTitles);
        vp.setOffscreenPageLimit(2);
        vp.setAdapter(mAdapter);
        stl.setViewPager(vp);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_rechargeWithdraw_toolbarRight);
        initThisToolbarHaveBack(toolbar,this);
    }
}
