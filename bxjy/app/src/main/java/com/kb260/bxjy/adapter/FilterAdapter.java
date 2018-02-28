package com.kb260.bxjy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.entity.ClassData;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/6
 */

public class FilterAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    public FilterAdapter(@Nullable List<String> data) {
        super(R.layout.item_filter, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.itemFilter_tv,item);
    }
}
