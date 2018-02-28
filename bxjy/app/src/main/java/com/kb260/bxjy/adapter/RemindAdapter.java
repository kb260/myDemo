package com.kb260.bxjy.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.HomeMultiItem;
import com.kb260.bxjy.model.entity.RemindData;
import com.kb260.bxjy.ui.home.TeacherDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/6
 */

public class RemindAdapter extends BaseQuickAdapter<RemindData,BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {
    public RemindAdapter(@Nullable List<RemindData> data) {
        super(R.layout.item_remind, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RemindData item) {
        RecyclerView rv = helper.getView(R.id.item_remind_rv);
        List<HomeMultiItem.Teacher> data = new ArrayList<>();
        data.add(new HomeMultiItem.Teacher("",""));
        data.add(new HomeMultiItem.Teacher("",""));
        data.add(new HomeMultiItem.Teacher("",""));

        HomeTeacherAdapter adapter = new HomeTeacherAdapter(data);

        LinearLayoutManager ms= new LinearLayoutManager(rv.getContext());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(ms);

        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TeacherDetailActivity.start(mContext,0);
    }
}
