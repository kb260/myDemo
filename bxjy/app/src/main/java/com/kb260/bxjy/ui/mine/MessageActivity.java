package com.kb260.bxjy.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.MessageAdapter;
import com.kb260.bxjy.adapter.NotebookClassAdapter;
import com.kb260.bxjy.model.entity.MessageData;
import com.kb260.bxjy.model.entity.NotebookClass;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/2/6
 */
public class MessageActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_message_rv)
    RecyclerView rv;

    MessageAdapter adapter;
    List<MessageData> data;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, MessageActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
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
        toolbar.setTitle(R.string.message_toolbar);
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        //rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider)));

        adapter = new MessageAdapter(data);
        addTop();
        rv.setAdapter(adapter);
    }

    private void addTop() {
        View view = getLayoutInflater().inflate(R.layout.divider10, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);
        adapter.setOnItemClickListener(this);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new MessageData());
        data.add(new MessageData());
        data.add(new MessageData());
    }

    private void setNoData404(){
        data.clear();
        adapter.setNewData(data);
        adapter.setEmptyView(R.layout.empty_view_404, (ViewGroup) rv.getParent());
    }

    private void setNoData0(){
        data.clear();
        adapter.setNewData(data);
        adapter.setEmptyView(R.layout.empty_view_0, (ViewGroup) rv.getParent());
    }

    private void setNoData1(){
        data.clear();
        adapter.setNewData(data);
        adapter.setEmptyView(R.layout.empty_view_1, (ViewGroup) rv.getParent());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (position == 0){
            setNoData404();
        }
        if (position == 1){
            setNoData0();
        }
        if (position == 2){
            setNoData1();
        }
    }
}
