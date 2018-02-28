package com.kb260.bxjy.ui.live;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.MyClassSectionAdapter;
import com.kb260.bxjy.model.MyClassSection;
import com.kb260.bxjy.model.entity.MyClass;
import com.kb260.bxjy.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZUserAction;
import cn.jzvd.JZUserActionStandard;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * @author  KB260
 * Created on  2018/2/8
 */
public class VideoActivity extends BaseActivity {
    @BindView(R.id.a_video_rv)
    RecyclerView rv;
    @BindView(R.id.video_back)
    ImageView ivBack;

    @BindView(R.id.a_video_mv)
    MyJZVideoPlayerStandard myJZVideoPlayerStandard;

    MyClassSectionAdapter adapter;
    List<MyClassSection> data;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, VideoActivity.class);
        context.startActivity(intent);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initData();
        initRv();
        initVideo();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
    }

    @OnClick(R.id.video_back)
    public void back(){
        finish();
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyClassSectionAdapter(data);
        addTop();
        rv.setAdapter(adapter);

    }



    private void addTop(){
        View view = getLayoutInflater().inflate(R.layout.divider20, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new MyClassSection(new MyClass()));
        data.add(new MyClassSection(new MyClass()));
        data.add(new MyClassSection(new MyClass()));
        data.add(new MyClassSection(new MyClass()));
        data.add(new MyClassSection(new MyClass()));
        data.add(new MyClassSection(new MyClass()));
    }

    private void initVideo(){
        myJZVideoPlayerStandard.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");


        Glide.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png")
                .into(myJZVideoPlayerStandard.thumbImageView);
    }




}
