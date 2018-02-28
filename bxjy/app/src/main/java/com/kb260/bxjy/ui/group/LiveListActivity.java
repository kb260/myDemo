package com.kb260.bxjy.ui.group;

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
import com.kb260.bxjy.adapter.LiveListAdapter;
import com.kb260.bxjy.model.entity.LiveData;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.ui.live.LiveActivity;
import com.kb260.bxjy.utils.Action;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/2/5
 */
public class LiveListActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_liveList_rv)
    RecyclerView rv;

    LiveListAdapter adapter;
    List<LiveData> data;

    String title,id;
    int type;
    /**
     *开启页面
     */
    public static void start(Context context, String title, String id, int type){
        Intent intent = new Intent(context,LiveListActivity.class);
        intent.putExtra(Action.TITLE,title);
        intent.putExtra(Action.ID,id);
        intent.putExtra(Action.TYPE,type);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_live_list;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
        initData();
        initRv();
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.groupClass_livingList_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    private void initIntent() {
        Intent intent = getIntent();
        title = intent.getStringExtra(Action.TITLE);
        id = intent.getStringExtra(Action.ID);
        type = intent.getIntExtra(Action.TYPE,-1);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new LiveData());
        data.add(new LiveData());
        data.add(new LiveData());
        data.add(new LiveData());
        data.add(new LiveData());
        data.add(new LiveData());
        data.add(new LiveData());
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LiveListAdapter(data);

        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        LiveActivity.start(this);
    }
}
