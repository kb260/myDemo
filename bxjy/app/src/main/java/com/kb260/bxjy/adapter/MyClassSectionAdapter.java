package com.kb260.bxjy.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.MyClassSection;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/6
 */

public class MyClassSectionAdapter extends BaseSectionQuickAdapter<MyClassSection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public MyClassSectionAdapter(List<MyClassSection> data) {
        super(R.layout.item_myclass, R.layout.item_myclass_head, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MyClassSection item) {

    }

    @Override
    protected void convert(BaseViewHolder helper, MyClassSection item) {

    }
}
