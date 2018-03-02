package com.panda.sharebike.ui.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.model.MessageEvent;
import com.panda.sharebike.ui.widget.CustomTitleBar;
import com.panda.sharebike.util.NetUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * <pre>
 *     author : zhangyuan
 *     e-mail : zhangyuan_min@163.com
 *     time   : 2017/06/16
 *     desc   : 基类activity
 *     version: 1.0
 * </pre>
 */
public abstract class BaseActivity extends AppCompatActivity {
    private CustomTitleBar titleBar;
    // public static NetBroadcastReceiver.NetEvevt evevt;
    /**
     * 网络类型
     */
    private int netMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        隐藏系统title注意的两点：

        继承AppCompatActivity时使用：
        supportRequestWindowFeature(Window.FEATURENOTITLE)

        继承activity时使用：
        requestWindowFeature(Window.FEATURENOTITLE)
         */
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        titleBar = new CustomTitleBar(this);
        linearLayout.addView(titleBar);
        linearLayout.addView(LayoutInflater.from(this).inflate(getLayoutView(), new RelativeLayout(this)));
        setContentView(linearLayout);
        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
            parentView.setFitsSystemWindows(true);
        }
        ButterKnife.bind(this);
        setUpView();
        //  evevt = this;
        //  inspectNet();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 返回当前Activity布局文件的id
     */
    abstract protected int getLayoutView();

    protected void setUpView() {
        titleBar.setTitle(getTitle().toString());
        titleBar.setLeftIcon(R.drawable.icon_title_back, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {/* Do something */}

    /**
     * 初始化时判断有没有网络
     */

    public boolean inspectNet() {
        this.netMobile = NetUtil.getNetWorkState(BaseActivity.this);

        return isNetConnect();
//
//        if (netMobile == NetUtil.NETWORK_WIFI) {
//            System.out.println("inspectNet：连接wifi");
//        } else if (netMobile == NetUtil.NETWORK_MOBILE) {
//            System.out.println("inspectNet:连接移动数据");
//        } else if (netMobile == NetUtil.NETWORK_NONE) {
//            System.out.println("inspectNet:当前没有网络");
//
//        }
    }

    /**
     * 网络变化之后的类型
     */
//    @Override
//    public void onNetChange(int netMobile) {
//        // TODO Auto-generated method stub
//        this.netMobile = netMobile;
//        isNetConnect();
//
//    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == NetUtil.NETWORK_WIFI) {
            return true;
        } else if (netMobile == NetUtil.NETWORK_MOBILE) {
            return true;
        } else if (netMobile == NetUtil.NETWORK_NONE) {
            return false;

        }
        return false;
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    protected void setTitle(String title) {
        titleBar.setTitle(title);
    }

    protected void setLeftIcon(int resId, View.OnClickListener listener) {
        titleBar.setLeftIcon(resId, listener);
    }

    protected void setTvLeft(String text, View.OnClickListener listener) {
        titleBar.setTvLeft(text, listener);
    }

    protected void setRightIcon(int resId, View.OnClickListener listener) {
        titleBar.setRightIcon(resId, listener);
    }

    protected void setTvRight(String text, View.OnClickListener listener) {
        titleBar.setTvRight(text, listener);
    }

    protected void setTvRightColor(int color) {
        titleBar.setTvRightTextColor(color);
    }

    //设置是否显示右边的icon
    protected void setRightIconHide() {
        titleBar.setShow_right_button(false);
    }

    //设置是否显示左边箭头
    protected void setLeftIconHide() {
        titleBar.setShow_left_button(false);
    }

    protected void hideTitleBar() {
        titleBar.setVisibility(View.GONE);
    }


    /**
     * 跳转页面
     */
    public void startActivity(Class<?> clz) {
        Intent intent = new Intent(this, clz);
        startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转页面
     *
     * @param clz         所跳转的Activity类
     * @param bundle      跳转所携带的信息
     * @param requestCode 请求码
     */
    public void startActivityForResult(Class<?> clz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转页面固定请求码，用于进入同一个页面，判断是那个activity进来的
     *
     * @param clz         所跳转的Activity类
     * @param requestCode 请求码
     */
    public void startActivityForResultSingle(Class<?> clz, int tag, int requestCode) {
        Intent intent = new Intent(this, clz);
        intent.putExtra(Config.SCANUNLOCK_KEY, tag);

        startActivityForResult(intent, requestCode);
    }

    /**
     * 统一页面跳转之后获取数据的方法
     */
    public int getIntentData() {

        return getIntent().getIntExtra(Config.SCANUNLOCK_KEY, 100);
    }
}
