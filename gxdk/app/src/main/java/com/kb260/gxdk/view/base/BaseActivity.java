package com.kb260.gxdk.view.base;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.jaeger.library.StatusBarUtil;
import com.kb260.gxdk.R;
import butterknife.ButterKnife;

/**
 * @author  KB260
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView(savedInstanceState);
        setStatusBar();
        initToolbar();
    }

    //获取布局文件
    public abstract int getLayoutId();

    public abstract void initView(@Nullable Bundle savedInstanceState);

    public void initToolbar(){};

    protected void setStatusBar() {
        StatusBarUtil.setColor(this,  ContextCompat.getColor(this, R.color.toolbar_white));
    }

    public Toolbar initThisToolbarHaveBack(Toolbar toolbar, final Activity activity) {
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(v -> activity.finish());
        return toolbar;
    }
}
