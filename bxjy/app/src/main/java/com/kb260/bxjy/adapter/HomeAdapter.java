package com.kb260.bxjy.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.HomeMultiItem;
import com.kb260.bxjy.ui.home.TeacherDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeMultiItem,BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeAdapter(List<HomeMultiItem> data) {
        super(data);
        addItemType(HomeMultiItem.LIVE_TITLE, R.layout.item_home_title);
        addItemType(HomeMultiItem.LIVE_LIST, R.layout.item_home_live);
        addItemType(HomeMultiItem.HIT_TITLE, R.layout.item_home_title);
        addItemType(HomeMultiItem.HIT_LIST, R.layout.item_home_live);
        addItemType(HomeMultiItem.TEACHER_TITLE, R.layout.item_home_title);
        addItemType(HomeMultiItem.TEACHER_LIST, R.layout.item_home_teacher);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMultiItem item) {
        ImageView iv = helper.getView(R.id.item_title_img);
        switch (helper.getItemViewType()) {
            case HomeMultiItem.LIVE_TITLE:
                iv.setImageResource(R.drawable.home_ing);
                helper.setText(R.id.item_title_tv,R.string.home_liveTitle);
                helper.addOnClickListener(R.id.item_title_llMare);
                break;
            case HomeMultiItem.LIVE_LIST:
                RecyclerView rv = helper.getView(R.id.item_home_rv);
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
                break;
            case HomeMultiItem.HIT_TITLE:
                iv.setImageResource(R.drawable.home_hit);
                helper.setText(R.id.item_title_tv,R.string.home_hitTitle);
                helper.addOnClickListener(R.id.item_title_llMare);
                break;
            case HomeMultiItem.HIT_LIST:
                RecyclerView rv1 = helper.getView(R.id.item_home_rv);
                List<HomeMultiItem.Teacher> data1 = new ArrayList<>();
                data1.add(new HomeMultiItem.Teacher("",""));
                data1.add(new HomeMultiItem.Teacher("",""));
                data1.add(new HomeMultiItem.Teacher("",""));

                HomeTeacherAdapter adapter1 = new HomeTeacherAdapter(data1);

                LinearLayoutManager ms1= new LinearLayoutManager(rv1.getContext());
                ms1.setOrientation(LinearLayoutManager.HORIZONTAL);
                rv1.setLayoutManager(ms1);

                rv1.setAdapter(adapter1);
                adapter1.setOnItemClickListener(this);
                break;
            case HomeMultiItem.TEACHER_TITLE:
                iv.setImageResource(R.drawable.home_teacher);
                helper.setText(R.id.item_title_tv,R.string.home_teacherTitle);
                helper.addOnClickListener(R.id.item_title_llMare);
                break;
            case HomeMultiItem.TEACHER_LIST:
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TeacherDetailActivity.start(mContext,0);
    }
}
