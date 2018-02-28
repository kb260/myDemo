package com.kb260.gxdk.view.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jaeger.library.StatusBarUtil;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.TabEntity;
import com.kb260.gxdk.model.LoginSuccess;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.CallHistory;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.GifSizeFilter;
import com.kb260.gxdk.utils.SPUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.account.LoginActivity;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.me.MeDetailActivity;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import butterknife.BindView;

/**
 * @author KB260
 *         Created on  2017/11/23
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.a_main_bottomBar)
    CommonTabLayout bottomBar;

    /**
     * 银行客户经理
     */
    private int[] mIconUnSelectIds = {
            R.drawable.main_0_0, R.drawable.main_1_0,
            R.drawable.main_2_0, R.drawable.main_3_0};
    private int[] mIconSelectIds = {
            R.drawable.main_0_1, R.drawable.main_1_1,
            R.drawable.main_2_1, R.drawable.main_3_1};
    private String[] bottomTxt = {"首页", "抢单", "商城 ", "我的"};

    /**
     * 普通用户
     */
    private int[] mIconUnSelectIdsUser = {
            R.drawable.main_0_0,
            R.drawable.main_2_0, R.drawable.main_3_0};
    private int[] mIconSelectIdsUser = {
            R.drawable.main_0_1,
            R.drawable.main_2_1, R.drawable.main_3_1};
    private String[] bottomTxtUser = {"首页", "商城 ", "我的"};

    private HomeFragment homeFragment;
    private GrabMonadFragment grabMonadFragment;
    private ShopFragment shopFragment;
    private MeFragment meFragment;

    int currentTabPosition = 0;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        KBApplication.exitAppList();
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initLogin(savedInstanceState);
        contact();
    }

    private void initLogin(Bundle savedInstanceState) {
        String username = SPUtils.getInstance().getString(Action.SP_USERNAME);
        String password = SPUtils.getInstance().getString(Action.SP_PASSWORD);
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            login(username, password, KBApplication.registrationId,savedInstanceState);
        } else {
            initFragment(savedInstanceState);
            initBottomBar();
        }
    }

    private void login(String username, String password, String regisonid,Bundle savedInstanceState) {
        Api.getDefault().applogin(username, password, regisonid,null,null)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<LoginSuccess>(this) {
                    @Override
                    protected void success(LoginSuccess list) {
                        KBApplication.userid = list.getId();
                        KBApplication.setLoginType(list.getType());
                        KBApplication.currentPhone = list.getUsername();
                        KBApplication.token = list.getToken();

                        initFragment(savedInstanceState);
                        initBottomBar();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);

                        initFragment(savedInstanceState);
                        initBottomBar();
                    }
                });
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }

    private void initBottomBar() {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        switch (KBApplication.getLoginType()) {
            case Action.LOGIN_TYPE_BANK:
                for (int i = 0; i < bottomTxt.length; i++) {
                    mTabEntities.add(new TabEntity(bottomTxt[i], mIconSelectIds[i], mIconUnSelectIds[i]));
                }
                break;
            case "":
                mTabEntities.add(new TabEntity(bottomTxt[0], mIconSelectIds[0], mIconUnSelectIds[0]));
                break;
            default:
                for (int i = 0; i < bottomTxtUser.length; i++) {
                    mTabEntities.add(new TabEntity(bottomTxtUser[i], mIconSelectIdsUser[i], mIconUnSelectIdsUser[i]));
                }
                break;
        }
        bottomBar.setTabData(mTabEntities);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (KBApplication.userid != 0) {
                    currentTabPosition = position;
                    switchTo(currentTabPosition);
                } else {
                    showPhone();
                }
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

            if (KBApplication.userid != 0){
                if (KBApplication.getLoginType().equals(Action.LOGIN_TYPE_BANK)) {
                    grabMonadFragment = (GrabMonadFragment) getSupportFragmentManager().findFragmentByTag("serviceFragment");
                }
                shopFragment = (ShopFragment) getSupportFragmentManager().findFragmentByTag("financingAdviserFragment");
                meFragment = (MeFragment) getSupportFragmentManager().findFragmentByTag("personalCenterFragment");
            }
            currentTabPosition = savedInstanceState.getInt(Contact.HOME_CURRENT_TAB_POSITION);
        } else {
            homeFragment = new HomeFragment();
            transaction.add(R.id.a_main_frameLayout, homeFragment, "homeFragment");

            if (KBApplication.userid != 0){
                if (KBApplication.getLoginType().equals(Action.LOGIN_TYPE_BANK)) {
                    grabMonadFragment = new GrabMonadFragment();
                    transaction.add(R.id.a_main_frameLayout, grabMonadFragment, "serviceFragment");
                }

                shopFragment = new ShopFragment();
                transaction.add(R.id.a_main_frameLayout, shopFragment, "financingAdviserFragment");

                meFragment = new MeFragment();
                transaction.add(R.id.a_main_frameLayout, meFragment, "personalCenterFragment");
            }
        }
        transaction.commit();
    }

    /**
     * 切换
     */
    private void switchTo(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (KBApplication.userid == 0) {
            transaction.show(homeFragment);
            transaction.commitAllowingStateLoss();
        } else {
            if (!KBApplication.getLoginType().equals(Action.LOGIN_TYPE_BANK)) {
                switch (position) {
                    case 0:
                        transaction.show(homeFragment);
                        transaction.hide(shopFragment);
                        transaction.hide(meFragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    case 1:
                        transaction.hide(homeFragment);
                        transaction.show(shopFragment);
                        transaction.hide(meFragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    case 2:
                        transaction.hide(homeFragment);
                        transaction.hide(shopFragment);
                        transaction.show(meFragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    default:
                        break;
                }
            } else {
                switch (position) {
                    case 0:
                        transaction.show(homeFragment);
                        transaction.hide(grabMonadFragment);
                        transaction.hide(shopFragment);
                        transaction.hide(meFragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    case 1:
                        transaction.hide(homeFragment);
                        transaction.show(grabMonadFragment);
                        transaction.hide(shopFragment);
                        transaction.hide(meFragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    case 2:
                        transaction.hide(homeFragment);
                        transaction.hide(grabMonadFragment);
                        transaction.show(shopFragment);
                        transaction.hide(meFragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    case 3:
                        transaction.hide(homeFragment);
                        transaction.hide(grabMonadFragment);
                        transaction.hide(shopFragment);
                        transaction.show(meFragment);
                        transaction.commitAllowingStateLoss();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (KBApplication.getLoginType().equals(Action.LOGIN_TYPE_BANK)) {
            if (currentTabPosition == 2) {
                shopFragment.a();
            } else {
                showExit();
            }
        } else {
            if (currentTabPosition == 1) {
                shopFragment.a();
            } else {
                showExit();
            }
        }
    }

    public void showExit() {
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setVisibility(R.id.message, View.GONE);
                        holder.setText(R.id.title, "确定退出?");
                        holder.setOnClickListener(R.id.ok, v -> {
                            dialog.dismiss();
                            KBApplication.exitAppList();
                        });
                        holder.setOnClickListener(R.id.cancel, v ->
                            dialog.dismiss()
                        );
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    private void showPhone() {
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setVisibility(R.id.message, View.GONE);
                        holder.setText(R.id.title, "登录后才能查看更多！");
                        holder.setText(R.id.ok, "去登录");
                        holder.setOnClickListener(R.id.ok, v -> {
                            LoginActivity.start(MainActivity.this);
                            finish();
                        });
                        holder.setOnClickListener(R.id.cancel, v ->
                            dialog.dismiss()
                        );
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    public void contact() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_CONTACTS)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        new CallHistory(this);
                    }
                });
    }

}
