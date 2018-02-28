package com.kb260.bxjy.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.ClassDetailMultiItem;
import com.kb260.bxjy.model.HomeMultiItem;
import com.kb260.bxjy.model.MoreMultiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class ClassDetailAdapter extends BaseMultiItemQuickAdapter<ClassDetailMultiItem,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ClassDetailAdapter(List<ClassDetailMultiItem> data) {
        super(data);
        addItemType(ClassDetailMultiItem.INTRODUCTION, R.layout.item_classdetail_introduction);
        addItemType(ClassDetailMultiItem.CLASS_SCHEDULE, R.layout.item_classdetail_classschedule);
        addItemType(ClassDetailMultiItem.TEACHER, R.layout.item_teacher_one);
        addItemType(ClassDetailMultiItem.NO_DATA, R.layout.empty_view_0);
        addItemType(ClassDetailMultiItem.NO_DATA404, R.layout.empty_view_404);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassDetailMultiItem item) {
        switch (helper.getItemViewType()) {
            case ClassDetailMultiItem.INTRODUCTION:

                break;
            case ClassDetailMultiItem.CLASS_SCHEDULE:

                break;
            case ClassDetailMultiItem.TEACHER:

                break;
            default:
                break;
        }
    }
}
