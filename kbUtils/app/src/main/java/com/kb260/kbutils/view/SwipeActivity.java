package com.kb260.kbutils.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.kb260.kbutils.R;
import com.kb260.kbutils.adapter.SwipeAdapter;
import com.kb260.kbutils.utils.DividerItemDecoration;
import com.kb260.kbutils.view.base.BaseActivity;
import com.kb260.kbutils.widget.swipe.util.Attributes;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/1/10
 */
public class SwipeActivity extends BaseActivity {
    @BindView(R.id.a_swipe_rv)
    RecyclerView rv;

    List<String> datas;
    SwipeAdapter adapter;

    /**
     *开启页面
     */
    public static void start(Context context){
        Intent intent = new Intent(context,SwipeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_swipe;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
    }

    private void initRv() {
        datas = new ArrayList<>();
        datas.add("000");
        datas.add("111");
        datas.add("222");
        datas.add("333");
        datas.add("444");
        datas.add("555");
        datas.add("666");
        datas.add("777");
        datas.add("888");
        datas.add("999");
        datas.add("000");


        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SwipeAdapter(datas);
        // Item Decorator:
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divide_deepr)));
        adapter.setMode(Attributes.Mode.Single);
        rv.setAdapter(adapter);
    }

}
