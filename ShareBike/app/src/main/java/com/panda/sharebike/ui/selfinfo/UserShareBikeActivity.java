package com.panda.sharebike.ui.selfinfo;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.cn.sharesdk.onekeyshare.OnekeyShare;
import com.panda.sharebike.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * 分享页
 */
public class UserShareBikeActivity extends BaseActivity {

    /*@BindView(R.id.iv_share_bike_back)
    ImageView ivShareBikeBack;
    @BindView(R.id.rl_share_back)
    RelativeLayout rlShareBack;*/

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_share_bike);
        setTranslucent();
        initView();
    }*/

    @Override
    protected int getLayoutView() {
        return R.layout.activity_user_share_bike;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        initView();
    }

    //初始化
    private void initView() {
        ButterKnife.bind(this);
        //
        //ivShareBikeBack.bringToFront();
        //android4.4之前的版本需要让view的父控件调用这两个方法使其重绘。
        //rlShareBack.requestLayout();
        //rlShareBack.invalidate();
        //showShare();
    }

    /**
     * 使状态栏透明
     * <p>
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     */
    public void setTranslucent() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @OnClick({/*R.id.iv_share_bike_back,*/ R.id.iv_share_wechat, R.id.iv_share_circle, R.id.iv_share_qq, R.id.iv_share_space, R.id.rl_share_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*case R.id.iv_share_bike_back:
                UserShareBikeActivity.this.finish();
                break;*/
            case R.id.iv_share_wechat:
                showShare(Wechat.NAME);
                break;
            case R.id.iv_share_circle:
                showShare(WechatMoments.NAME);
                break;
            case R.id.iv_share_qq:
                showShare(QQ.NAME);
                break;
            case R.id.iv_share_space:
                showShare(QZone.NAME);
                break;
            case R.id.rl_share_back:
                break;
        }
    }

    private void showShare(String platform) {
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        //  oks.disableSSOWhenAuthorize();
// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("巧骑单车");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://app.qiaoqidanche.com:81/qbike/kqweb/share.html");
//        oks.setTitleUrl("http://eefdt9.natappfree.cc/qbike/kqweb/share.html");
// text是分享文本，所有平台都需要这个字段
        oks.setText("扫码即可开锁,路边固定地点还车.骑行半小时只需一元!文明,低碳出行就选巧骑单车!\n");
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(Api.BASE_URL + "qbike/kqweb/share.html");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://app.qiaoqidanche.com:81/qbike/kqweb/share.html");
        oks.setImageUrl("http://i1.bvimg.com/604185/e86f35deb761f215.png");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setPlatform(platform);
// 启动分享GUI
        oks.show(this);
    }
}
