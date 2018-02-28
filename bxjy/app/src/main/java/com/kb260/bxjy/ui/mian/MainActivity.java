package com.kb260.bxjy.ui.mian;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.kb260.bxjy.R;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.entity.TabEntity;
import com.kb260.bxjy.ui.account.LoginPwActivity;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Contact;
import java.util.ArrayList;
import butterknife.BindView;

/**
 * @author KB260
 *         Created on  2018/1/31
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.a_main_fl)
    FrameLayout aMainFl;
    @BindView(R.id.a_main_bottomBar)
    CommonTabLayout bottomBar;

    private HomeFragment homeFragment;
    private GroupClassFragment groupClassFragment;
    private AdvisoryFragment advisoryFragment;
    private MineFragment mineFragment;
    private PublicClassFragment publicClassFragment;
    private int currentTabPosition = 0;

    private int[] mUnSelectIcons = {
            R.drawable.main_bottom_home0, R.drawable.main_bottom_public0,
            R.drawable.main_bottom_group0, R.drawable.main_bottom_advisory0, R.drawable.main_bottom_mine0};
    private int[] mSelectIcons = {
            R.drawable.main_bottom_home, R.drawable.main_bottom_public,
            R.drawable.main_bottom_group, R.drawable.main_bottom_advisory, R.drawable.main_bottom_mine};
   //private String[] bottomTxt = {"首页", "公开课", "小组课 ", "咨询", "我的"};
    private String[] bottomTxt = {null, null, null, null, null};


    /**
     *开启页面
     */
    public static void start(Context context){
        KbApplication.exitAppList();
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initFragment(savedInstanceState);
        addBottom();
    }

    private void addBottom() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (int i = 0; i < bottomTxt.length; i++) {
            mTabEntities.add(new TabEntity(bottomTxt[i], mSelectIcons[i], mUnSelectIcons[i]));
        }
        bottomBar.setTabData(mTabEntities);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                currentTabPosition = position;
                switchTo(currentTabPosition);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        bottomBar.setCurrentTab(currentTabPosition);
        switchTo(currentTabPosition);
    }


    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState != null) {
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("homeFragment");
            publicClassFragment = (PublicClassFragment) getSupportFragmentManager().findFragmentByTag("publicClassFragment");
            groupClassFragment = (GroupClassFragment) getSupportFragmentManager().findFragmentByTag("groupClassFragment");
            advisoryFragment = (AdvisoryFragment) getSupportFragmentManager().findFragmentByTag("advisoryFragment");
            mineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag("mineFragment");
            currentTabPosition = savedInstanceState.getInt(Contact.HOME_CURRENT_TAB_POSITION);
        } else {
            homeFragment = new HomeFragment();
            transaction.add(R.id.a_main_fl, homeFragment, "homeFragment");
            publicClassFragment = new PublicClassFragment();
            transaction.add(R.id.a_main_fl, publicClassFragment, "publicClassFragment");

            groupClassFragment = new GroupClassFragment();
            transaction.add(R.id.a_main_fl, groupClassFragment, "groupClassFragment");

            advisoryFragment = new AdvisoryFragment();
            transaction.add(R.id.a_main_fl, advisoryFragment, "advisoryFragment");

            mineFragment = new MineFragment();
            transaction.add(R.id.a_main_fl, mineFragment, "mineFragment");
        }
        transaction.commit();
    }

    /**
     * 切换
     */
    private void switchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.show(homeFragment);
                transaction.hide(publicClassFragment);
                transaction.hide(groupClassFragment);
                transaction.hide(advisoryFragment);
                transaction.hide(mineFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 1:
                transaction.hide(homeFragment);
                transaction.show(publicClassFragment);
                transaction.hide(groupClassFragment);
                transaction.hide(advisoryFragment);
                transaction.hide(mineFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 2:
                transaction.hide(homeFragment);
                transaction.hide(publicClassFragment);
                transaction.show(groupClassFragment);
                transaction.hide(advisoryFragment);
                transaction.hide(mineFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 3:
                transaction.hide(homeFragment);
                transaction.hide(publicClassFragment);
                transaction.hide(groupClassFragment);
                transaction.show(advisoryFragment);
                transaction.hide(mineFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 4:
                transaction.hide(homeFragment);
                transaction.hide(publicClassFragment);
                transaction.hide(groupClassFragment);
                transaction.hide(advisoryFragment);
                transaction.show(mineFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

}
