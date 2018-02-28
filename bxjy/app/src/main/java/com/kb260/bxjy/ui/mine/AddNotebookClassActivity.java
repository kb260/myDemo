package com.kb260.bxjy.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.kb260.bxjy.R;
import com.kb260.bxjy.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/6
 */
public class AddNotebookClassActivity extends BaseActivity {
    @BindView(R.id.a_addNotebookClass_toolbar)
    Toolbar toolbar;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, AddNotebookClassActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_notebook_class;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.addNotebookClass_toolbarTitle);
    }

    //确定
    @OnClick(R.id.a_addNotebookClass_toolbarRight)
    public void sure() {
        finish();
    }
}
