package com.kb260.bxjy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.kb260.bxjy.R;
import com.kb260.bxjy.model.entity.BannerData;
import com.kb260.bxjy.utils.GlideUtils;
import com.kb260.bxjy.weight.mzbanner.holder.MZViewHolder;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/1/31
 */

public class BannerViewHolder implements MZViewHolder<BannerData> {
    private ImageView mImageView;
    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_banner,null);
        mImageView =  view.findViewById(R.id.item_home_banner);
        return view;
    }

    @Override
    public void onBind(Context context, int position, List<BannerData> list) {
        // 数据绑定
        GlideUtils.showImg(context,list.get(position).getImg1(),mImageView);
    }
}
