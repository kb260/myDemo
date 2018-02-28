package com.kb260.gxdk.view.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import com.flyco.tablayout.SlidingTabLayout;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.MyPagerAdapter;
import com.kb260.gxdk.view.base.BaseFragment;
import com.kb260.gxdk.view.gradmonad.ConfirmedFragment;
import com.kb260.gxdk.view.gradmonad.FirstDraftFragment;
import com.kb260.gxdk.view.gradmonad.HandledFragment;
import com.kb260.gxdk.view.gradmonad.LastDraftFragment;
import com.kb260.gxdk.view.gradmonad.OrderFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/11/14
 */

public class GrabMonadFragment extends BaseFragment {
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.f_grabMonad_vp)
    ViewPager vp;
    @BindView(R.id.f_grabMonad_stl)
    SlidingTabLayout stl;

    private String[] mTitles = {"订单", "提供初审", "提供终审", "已确认", "已办理"};

    @Override
    protected int getLayoutResource() {
        return R.layout.f_grabmonad;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    protected void initView() {
        initViews();
    }

    private void initViews() {
        toolbarTitle.setText(R.string.f_grabMonad_toolbarTitle);
        List<Fragment> mFragments = new ArrayList<>();
        mFragments.add(OrderFragment.getInstance());
        mFragments.add(FirstDraftFragment.getInstance());
        mFragments.add(LastDraftFragment.getInstance());
        mFragments.add(ConfirmedFragment.getInstance());
        mFragments.add(HandledFragment.getInstance());

        MyPagerAdapter mAdapter = new MyPagerAdapter(getFragmentManager(), mFragments, mTitles);
        vp.setOffscreenPageLimit(4);
        vp.setAdapter(mAdapter);
        stl.setViewPager(vp);
    }

}

