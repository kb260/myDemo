package com.kb260.bxjy.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.ClassDetailMultiItem;
import com.kb260.bxjy.model.TeacherDetailMultiItem;

import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class TeacherDetailAdapter extends BaseMultiItemQuickAdapter<TeacherDetailMultiItem,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public TeacherDetailAdapter(List<TeacherDetailMultiItem> data) {
        super(data);
        addItemType(TeacherDetailMultiItem.EVALUATION, R.layout.item_teacherdetail_evaluation);
        addItemType(TeacherDetailMultiItem.ONLINE_CLASS, R.layout.item_teacherdetail_onlineclass);
        addItemType(TeacherDetailMultiItem.NO_DATA, R.layout.empty_view_0);
        addItemType(TeacherDetailMultiItem.NO_DATA404, R.layout.empty_view_404);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeacherDetailMultiItem item) {
        switch (helper.getItemViewType()) {
            case TeacherDetailMultiItem.EVALUATION:

                break;
            case TeacherDetailMultiItem.ONLINE_CLASS:

                break;
            default:
                break;
        }
    }
}
