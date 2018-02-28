package com.kb260.bxjy.ui.mine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.NotebookClassAdapter;
import com.kb260.bxjy.model.entity.NotebookClass;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/6
 */
public class NotebookClassActivity extends BaseActivity {
    @BindView(R.id.a_notebookClass_toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_notebookClass_rv)
    RecyclerView rv;

    NotebookClassAdapter adapter;
    List<NotebookClass> data;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, NotebookClassActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_notebook_class;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initData();
        initRv();
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        //rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider)));

        adapter = new NotebookClassAdapter(data);

        rv.setAdapter(adapter);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new NotebookClass());
        data.add(new NotebookClass());
        data.add(new NotebookClass());
        data.add(new NotebookClass());
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.mine_notebookClass);
    }

    //确定
    @OnClick(R.id.a_notebookClass_toolbarRight)
    public void add() {
        AddNotebookClassActivity.start(this);
    }
}
