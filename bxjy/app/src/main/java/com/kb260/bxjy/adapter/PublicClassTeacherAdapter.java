package com.kb260.bxjy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.HomeMultiItem;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/1/31
 */

public class PublicClassTeacherAdapter extends BaseQuickAdapter<HomeMultiItem.Teacher,BaseViewHolder>{

    public PublicClassTeacherAdapter(@Nullable List<HomeMultiItem.Teacher> data) {
        super(R.layout.item_publicclass_teachers, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMultiItem.Teacher item) {

    }
}
