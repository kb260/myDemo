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
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.me.fragment.MyProductFragment;
import com.kb260.gxdk.view.me.fragment.NewProductFragment;
import java.util.ArrayList;
import butterknife.BindView;

public class MyProductActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_myProduct_vp)
    ViewPager vp;
    @BindView(R.id.a_myProduct_stl)
    SlidingTabLayout stl;


    private ArrayList<Fragment> mFragments;
    private String[] mTitles = {"新增产品", "我的产品"};

    public static void start(Context context){
        Intent intent = new Intent(context,MyProductActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_product;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        mFragments = new ArrayList<>();
        mFragments.add(NewProductFragment.getInstance());
        mFragments.add(MyProductFragment.getInstance());

        MyPagerAdapter mAdapter = new MyPagerAdapter(getSupportFragmentManager(),mFragments,mTitles);
        //vp.setOffscreenPageLimit(4);
        vp.setAdapter(mAdapter);
        stl.setViewPager(vp);
    }


    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_myProduct_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }
}
