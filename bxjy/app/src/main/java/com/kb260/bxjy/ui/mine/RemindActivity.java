package com.kb260.bxjy.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.RemindAdapter;
import com.kb260.bxjy.model.entity.RemindData;
import com.kb260.bxjy.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/2/6
 */
public class RemindActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_remind_rv)
    RecyclerView rv;

    RemindAdapter adapter;
    List<RemindData> data;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, RemindActivity.class);
        context.startActivity(intent);
    }
    

    @Override
    public int getLayoutId() {
        return R.layout.activity_remind;
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
        toolbar.setTitle(R.string.mine_kbtx);
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RemindAdapter(data);

        rv.setAdapter(adapter);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new RemindData());
        data.add(new RemindData());
        data.add(new RemindData());
        data.add(new RemindData());
    }
}
