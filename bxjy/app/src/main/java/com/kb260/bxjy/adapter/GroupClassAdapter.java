package com.kb260.bxjy.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.GroupClassMultiItem;
import com.kb260.bxjy.model.HomeMultiItem;
import com.kb260.bxjy.model.PublicClassMultiItem;
import com.kb260.bxjy.ui.home.TeacherDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class GroupClassAdapter extends BaseMultiItemQuickAdapter<GroupClassMultiItem,BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public GroupClassAdapter(List<GroupClassMultiItem> data) {
        super(data);
        addItemType(GroupClassMultiItem.INTERNAL, R.layout.item_groupclass_internal);
        addItemType(GroupClassMultiItem.PUBLIC, R.layout.item_groupclass_internal);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupClassMultiItem item) {
        switch (helper.getItemViewType()) {
            case GroupClassMultiItem.INTERNAL:
                RecyclerView rv = helper.getView(R.id.item_groupClass_internalRv);
                List<HomeMultiItem.Teacher> data = new ArrayList<>();
                data.add(new HomeMultiItem.Teacher("",""));
                data.add(new HomeMultiItem.Teacher("",""));
                data.add(new HomeMultiItem.Teacher("",""));

                PublicClassTeacherAdapter adapter = new PublicClassTeacherAdapter(data);

                LinearLayoutManager ms= new LinearLayoutManager(mContext);
                ms.setOrientation(LinearLayoutManager.HORIZONTAL);
                rv.setLayoutManager(ms);

                rv.setAdapter(adapter);
                adapter.setOnItemClickListener(this);
                break;
            case GroupClassMultiItem.PUBLIC:

                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TeacherDetailActivity.start(mContext,0);
    }
}
