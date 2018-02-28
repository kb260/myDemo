package com.kb260.gxdk.view.home;

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
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.home.fragment.EvaluationCarFragment;
import com.kb260.gxdk.view.home.fragment.EvaluationFragment;
import java.util.ArrayList;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/11/23
 */
public class EvaluationActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_evaluation_vp)
    ViewPager vp;
    @BindView(R.id.a_evaluation_stl)
    SlidingTabLayout stl;

    private String[] mTitles = {"房屋评估", "车辆评估"};

    public static void start(Context context){
        Intent intent = new Intent(context,EvaluationActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_evaluation;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        ArrayList<Fragment> mFragments = new ArrayList<>();
        mFragments.add(EvaluationFragment.getInstance(1));
        mFragments.add(EvaluationCarFragment.getInstance(2));

        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager(),mFragments,mTitles);
        vp.setAdapter(mAdapter);
        stl.setViewPager(vp);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_evaluation_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }
}
