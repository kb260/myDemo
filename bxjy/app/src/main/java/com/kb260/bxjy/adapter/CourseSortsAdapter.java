package com.kb260.bxjy.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.CourseSortsMultiItem;
import com.kb260.bxjy.model.HomeMultiItem;
import com.kb260.bxjy.model.PublicClassMultiItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class CourseSortsAdapter extends BaseMultiItemQuickAdapter<CourseSortsMultiItem,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CourseSortsAdapter(List<CourseSortsMultiItem> data) {
        super(data);
        addItemType(CourseSortsMultiItem.SUBJECT_TITLE, R.layout.item_home_title);
        addItemType(CourseSortsMultiItem.SUBJECT_LIST, R.layout.item_class_noavatar);
        addItemType(CourseSortsMultiItem.SORT_LIST, R.layout.item_class_squareavatar_sort);
        addItemType(CourseSortsMultiItem.FREE_LIST, R.layout.item_class_squareavatar);
        addItemType(CourseSortsMultiItem.PAY_LIST, R.layout.item_class_squareavatar_pay);
        addItemType(CourseSortsMultiItem.TEACHER_TITLE, R.layout.item_home_title);
        addItemType(CourseSortsMultiItem.TEACHER_LIST, R.layout.item_class_noavatar);
        addItemType(CourseSortsMultiItem.NO_DATA, R.layout.empty_view_0);
        addItemType(CourseSortsMultiItem.NO_DATA404, R.layout.empty_view_404);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseSortsMultiItem item) {
        ImageView iv = helper.getView(R.id.item_title_img);
        switch (helper.getItemViewType()) {
            case CourseSortsMultiItem.SUBJECT_TITLE:
                helper.setText(R.id.item_title_tv,item.getTitle());
                switch (item.getTitle()){
                    case "公务员笔试":
                        iv.setImageResource(R.drawable.notebook);
                        break;
                    case "公务员面试":
                        iv.setImageResource(R.drawable.interview);
                        break;
                    case "事业单位":
                        iv.setImageResource(R.drawable.institution);
                        break;
                    case "政法考试":
                        iv.setImageResource(R.drawable.examination);
                        break;
                    default:
                        iv.setImageResource(R.drawable.notebook);
                        break;
                }
                helper.addOnClickListener(R.id.item_title_llMare);
                break;
            case CourseSortsMultiItem.SUBJECT_LIST:

                break;
            case CourseSortsMultiItem.TEACHER_TITLE:
                iv.setImageResource(R.drawable.home_teacher);
                helper.setText(R.id.item_title_tv,item.getTitle());
                helper.addOnClickListener(R.id.item_title_llMare);
                break;
            case CourseSortsMultiItem.TEACHER_LIST:

                break;
            case CourseSortsMultiItem.SORT_LIST:
                break;
            case CourseSortsMultiItem.FREE_LIST:
                break;
            case CourseSortsMultiItem.PAY_LIST:
                break;
            default:
                break;
        }
    }
}
