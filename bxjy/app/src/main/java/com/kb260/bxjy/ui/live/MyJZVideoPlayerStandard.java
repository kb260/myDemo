package com.kb260.bxjy.ui.live;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kb260.bxjy.R;

import java.util.LinkedHashMap;

import cn.jzvd.JZUserAction;
import cn.jzvd.JZUserActionStandard;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * 这里可以监听到视频播放的生命周期和播放状态
 * 所有关于视频的逻辑都应该写在这里
 * Created by Nathen on 2017/7/2.
 */
public class MyJZVideoPlayerStandard extends JZVideoPlayerStandard implements CompoundButton.OnCheckedChangeListener {
    RelativeLayout ivBackRl;
    CheckBox cbCollect;
    ImageView ivShow;
    LinearLayout play4g;
    TextView tvPlay4g;

    public MyJZVideoPlayerStandard(Context context) {
        super(context);
    }

    public MyJZVideoPlayerStandard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);

        cbCollect = findViewById(R.id.video_cbCollect);
        ivShow = findViewById(R.id.video_ivShow);
        play4g = findViewById(R.id.play_4g);
        tvPlay4g = findViewById(R.id.play4G_tvPlay);
        cbCollect.setOnCheckedChangeListener(this);
        ivShow.setOnClickListener(this);
        tvPlay4g.setOnClickListener(this);
        JZVideoPlayer.setJzUserAction(new MyUserActionStandard());


    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == cn.jzvd.R.id.fullscreen) {
            if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
                //click quit fullscreen
            } else {
                //click goto fullscreen
            }
        }
        switch (v.getId()){
            case R.id.video_ivShow:
                break;
            case R.id.play4G_tvPlay:
                startVideo();
                break;
            case R.id.back:
                break;
            case R.id.fullscreen:
                break;
            default:
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.jz_layout_standard;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return super.onTouch(v, event);
    }

    @Override
    public void startVideo() {
        super.startVideo();
    }

    @Override
    public void onStateNormal() {
        super.onStateNormal();
    }

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
    }

    @Override
    public void onStatePause() {
        super.onStatePause();
    }

    @Override
    public void onStateError() {
        super.onStateError();
    }

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
    }

    @Override
    public void onInfo(int what, int extra) {
        super.onInfo(what, extra);
    }

    @Override
    public void onError(int what, int extra) {
        super.onError(what, extra);
    }

    @Override
    public void startWindowFullscreen() {
        super.startWindowFullscreen();
    }


    @Override
    public void autoQuitFullscreen() {
        super.autoQuitFullscreen();
    }

    @Override
    public void autoFullscreen(float x) {
        super.autoFullscreen(x);
    }



    @Override
    public void startWindowTiny() {
        super.startWindowTiny();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    /**
     * 这只是给埋点统计用户数据用的，不能写和播放相关的逻辑，监听事件请参考MyJZVideoPlayerStandard，复写函数取得相应事件
     */
    class MyUserActionStandard implements JZUserActionStandard {

        @Override
        public void onEvent(int type, Object url, int screen, Object... objects) {
            switch (type) {
                case JZUserAction.ON_ENTER_FULLSCREEN:
                    Log.i("USER_EVENT", "ON_ENTER_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    //ivBack.setVisibility(GONE);
                    break;
                case JZUserAction.ON_QUIT_FULLSCREEN:
                    Log.i("USER_EVENT", "ON_QUIT_FULLSCREEN" + " title is : " + (objects.length == 0 ? "" : objects[0]) + " url is : " + url + " screen is : " + screen);
                    //ivBack.setVisibility(VISIBLE);
                    break;
                default:
                    Log.i("USER_EVENT", "unknow");
                    break;
            }
        }
    }
}
