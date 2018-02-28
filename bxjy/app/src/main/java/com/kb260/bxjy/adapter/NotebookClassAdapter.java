package com.kb260.bxjy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.entity.NotebookClass;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/6
 */

public class NotebookClassAdapter extends BaseQuickAdapter<NotebookClass,BaseViewHolder>{
    public NotebookClassAdapter(@Nullable List<NotebookClass> data) {
        super(R.layout.item_notebookclass, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NotebookClass item) {

    }
}
