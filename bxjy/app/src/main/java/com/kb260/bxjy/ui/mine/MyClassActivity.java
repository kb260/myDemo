package com.kb260.bxjy.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.MyClassSectionAdapter;
import com.kb260.bxjy.adapter.MyCollectAdapter;
import com.kb260.bxjy.model.MyClassSection;
import com.kb260.bxjy.model.entity.MyClass;
import com.kb260.bxjy.model.entity.MyCollectData;
import com.kb260.bxjy.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/2/6
 */
public class MyClassActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_myClass_rv)
    RecyclerView rv;

    MyClassSectionAdapter adapter;
    List<MyClassSection> data;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, MyClassActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_class;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initData();
        initRv();
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.mine_wdkc);
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyClassSectionAdapter(data);
        addTop();
        rv.setAdapter(adapter);
    }

    private void addTop(){
        View view = getLayoutInflater().inflate(R.layout.divider6, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new MyClassSection(true,""));
        data.add(new MyClassSection(new MyClass()));
        data.add(new MyClassSection(new MyClass()));
        data.add(new MyClassSection(new MyClass()));
        data.add(new MyClassSection(true,""));
        data.add(new MyClassSection(new MyClass()));
        data.add(new MyClassSection(new MyClass()));
        data.add(new MyClassSection(new MyClass()));
    }
}
