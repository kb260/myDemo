package com.kb260.bxjy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.entity.BankCard;
import com.kb260.bxjy.model.entity.ClassData;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/6
 */

public class ClassListAdapter extends BaseQuickAdapter<ClassData,BaseViewHolder>{
    public ClassListAdapter(@Nullable List<ClassData> data) {
        super(R.layout.item_class_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassData item) {

    }
}
