package com.kb260.bxjy.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.ClassListAdapter;
import com.kb260.bxjy.model.entity.ClassData;
import com.kb260.bxjy.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/2/7
 */
public class ClassListActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_classList_rv)
    RecyclerView rv;

    ClassListAdapter adapter;
    List<ClassData> data;

    /**
     *开启页面
     */
    public static void start(Context context){
        Intent intent = new Intent(context,ClassListActivity.class);
        context.startActivity(intent);
    }
    

    @Override
    public int getLayoutId() {
        return R.layout.activity_class_list;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initData();
        initRv();
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar,this);
        toolbar.setTitle(R.string.classList_toolbar);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new ClassData());
        data.add(new ClassData());
        data.add(new ClassData());
        data.add(new ClassData());
        data.add(new ClassData());
        data.add(new ClassData());
        data.add(new ClassData());
        data.add(new ClassData());
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ClassListAdapter(data);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
