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
import com.kb260.bxjy.adapter.TeacherClassAdapter;
import com.kb260.bxjy.adapter.TeacherDetailAdapter;
import com.kb260.bxjy.model.TeacherClassMultiItem;
import com.kb260.bxjy.model.TeacherDetailMultiItem;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.ui.live.VideoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author KB260
 *         Created on  2018/2/7
 */
public class TeacherClassActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_teacherClass_rv)
    RecyclerView rv;

    TeacherClassAdapter adapter;
    List<TeacherClassMultiItem> data;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, TeacherClassActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_teacher_class;
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
        toolbar.setTitle(R.string.teacherClass_toolbar);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new TeacherClassMultiItem(TeacherClassMultiItem.TEACHER));
        data.add(new TeacherClassMultiItem(TeacherClassMultiItem.TITLE));
        data.add(new TeacherClassMultiItem(TeacherClassMultiItem.CLASS));
        data.add(new TeacherClassMultiItem(TeacherClassMultiItem.CLASS));
        data.add(new TeacherClassMultiItem(TeacherClassMultiItem.CLASS));
        data.add(new TeacherClassMultiItem(TeacherClassMultiItem.CLASS));
        data.add(new TeacherClassMultiItem(TeacherClassMultiItem.CLASS));

    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TeacherClassAdapter(data);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        VideoActivity.start(this);
    }
}
