package com.kb260.bxjy.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.HomeMultiItem;
import com.kb260.bxjy.model.entity.ClassData;
import com.kb260.bxjy.weight.mzbanner.holder.MZViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KB260
 *         Created on  2018/1/31
 */

public class BannerNoClassViewHolder implements MZViewHolder<ClassData>, BaseQuickAdapter.OnItemClickListener {
    private ImageView mImageView;
    public RecyclerView rv;
    Context context;
    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner_noclass,null);
        //mImageView =  view.findViewById(R.id.item_home_banner);
        rv = view.findViewById(R.id.item_publicClass_bannerNoClass_rv);
        this.context = context;
        return view;
    }

    private void initRv(List<ClassData> mDatas, int position) {
        List<HomeMultiItem.Teacher> data = new ArrayList<>();
        for (ClassData.TeacherListBean teacherListBean:mDatas.get(position).getTeacherList()){
            data.add(new HomeMultiItem.Teacher("",""));
        }

        HomeTeacherAdapter adapter = new HomeTeacherAdapter(data);

        LinearLayoutManager ms= new LinearLayoutManager(context);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(ms);

        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onBind(Context context, int position, List<ClassData> data) {
        // 数据绑定
        initRv(data,position);
       // mImageView.setImageResource(data);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //TeacherDetailActivity.start(rv.getContext(),"");
    }
}
