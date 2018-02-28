package com.kb260.kbutils.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.kbutils.R;
import com.kb260.kbutils.adapter.MainAdapter;
import com.kb260.kbutils.adapter.SlideAdapter;
import com.kb260.kbutils.utils.DividerItemDecoration;
import com.kb260.kbutils.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/1/9
 */
public class SlideActivity extends BaseActivity implements  BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.a_slide_rv)
    RecyclerView rv;
    SlideAdapter adapter;
    List<String> data;


    /**
     *开启页面
     */
    public static void start(Context context){
        Intent intent = new Intent(context,SlideActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_slide;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
    }

    private void initRv() {
        data  = new ArrayList<>();
        data.add("111");
        data.add("222");
        data.add("333");
        data.add("444");
        data.add("555");
        data.add("666");
        data.add("777");
        data.add("888");
        data.add("999");
        data.add("000");

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this,R.drawable.divide_deepr)));
        adapter = new SlideAdapter(data);

        rv.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
    }



    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        data.remove(position);//删除数据源
        adapter.notifyItemRemoved(position);//刷新被删除的地方
        adapter.notifyItemRangeChanged(position, 1); //刷新被删除数据，以及其后面的数据

    }
}
