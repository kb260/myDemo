package com.kb260.kbutils.view;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.kbutils.R;
import com.kb260.kbutils.activityanim.ActivityAnimActivity;
import com.kb260.kbutils.adapter.MainAdapter;
import com.kb260.kbutils.utils.DividerItemDecoration;
import com.kb260.kbutils.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/1/5
 */
public class MainActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.a_main_rv)
    RecyclerView rv;
    MainAdapter adapter;
    List<String> data;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
    }

    private void initRv() {
        data  = new ArrayList<>();
        data.add("侧滑");
        data.add("Activity动画");
        data.add("侧滑2");
        data.add("MaterialDialog");
        data.add("Dialog");

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this,R.drawable.divide_deepr)));
        adapter = new MainAdapter(data);

        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position){
            case 0:
                SlideActivity.start(this);
                break;
            case 1:
                ActivityAnimActivity.start(this);
                break;
            case 2:
                SwipeActivity.start(this);
                break;
            case 3:
                MaterialDialogActivity.start(this);
                break;
            case 4:
                DialogActivity.start(this);
                break;
            default:
                break;
        }
    }
}
