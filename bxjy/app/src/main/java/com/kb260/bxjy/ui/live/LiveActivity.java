package com.kb260.bxjy.ui.live;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.kb260.bxjy.R;
import com.kb260.bxjy.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/2/8
 */
public class LiveActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, LiveActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_live;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle("直播");
    }
}
