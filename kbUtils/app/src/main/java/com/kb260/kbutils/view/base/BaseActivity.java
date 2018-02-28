package com.kb260.kbutils.view.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.jaeger.library.StatusBarUtil;
import com.kb260.kbutils.R;
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
        StatusBarUtil.setColor(this,  ContextCompat.getColor(this, R.color.black));
    }

    public Toolbar initThisToolbarHaveBack(Toolbar toolbar, final Activity activity) {
        toolbar.setNavigationIcon(R.drawable.ic_launcher_background);
        toolbar.setNavigationOnClickListener(v -> activity.finish());
        return toolbar;
    }
}
