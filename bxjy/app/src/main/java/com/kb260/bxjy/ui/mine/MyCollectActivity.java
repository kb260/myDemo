package com.kb260.bxjy.ui.mine;

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
import com.kb260.bxjy.adapter.MessageAdapter;
import com.kb260.bxjy.adapter.MyCollectAdapter;
import com.kb260.bxjy.model.entity.MessageData;
import com.kb260.bxjy.model.entity.MyCollectData;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/2/6
 */
public class MyCollectActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_myCollect_rv)
    RecyclerView rv;

    MyCollectAdapter adapter;
    List<MyCollectData> data;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, MyCollectActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_collect;
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
        toolbar.setTitle(R.string.myCollect_toolbar);
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyCollectAdapter(data);

        rv.setAdapter(adapter);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new MyCollectData());
        data.add(new MyCollectData());
        data.add(new MyCollectData());
        data.add(new MyCollectData());
    }
}
