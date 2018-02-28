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
import com.kb260.bxjy.model.PublicClassMultiItem;
import com.kb260.bxjy.model.TeacherClassMultiItem;
import com.kb260.bxjy.ui.home.TeacherDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class TeacherClassAdapter extends BaseMultiItemQuickAdapter<TeacherClassMultiItem,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public TeacherClassAdapter(List<TeacherClassMultiItem> data) {
        super(data);
        addItemType(TeacherClassMultiItem.TEACHER, R.layout.item_teacher_one);
        addItemType(TeacherClassMultiItem.TITLE, R.layout.item_teacher_class_title);
        addItemType(TeacherClassMultiItem.CLASS, R.layout.item_teacherclass_class);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeacherClassMultiItem item) {
        switch (helper.getItemViewType()) {
            case TeacherClassMultiItem.TEACHER:

                break;
            case TeacherClassMultiItem.TITLE:

                break;
            case TeacherClassMultiItem.CLASS:

                break;

            default:
                break;
        }
    }

}
