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
import com.kb260.bxjy.ui.home.TeacherDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class PublicClassAdapter extends BaseMultiItemQuickAdapter<PublicClassMultiItem,BaseViewHolder> implements
        BaseQuickAdapter.OnItemClickListener {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public PublicClassAdapter(List<PublicClassMultiItem> data) {
        super(data);
        addItemType(PublicClassMultiItem.NO_CLASS_TITLE, R.layout.item_home_title);
        addItemType(PublicClassMultiItem.NO_CLASS_LIST, R.layout.item_publicclass_noclass);
        addItemType(PublicClassMultiItem.CLASSING_TITLE, R.layout.item_home_title);
        addItemType(PublicClassMultiItem.CLASSING_LIST, R.layout.item_home_live);
        addItemType(PublicClassMultiItem.CLASSED_TITLE, R.layout.item_home_title);
        addItemType(PublicClassMultiItem.CLASSED_LIST, R.layout.item_publicclass_classing);
    }

    @Override
    protected void convert(BaseViewHolder helper, PublicClassMultiItem item) {
        ImageView iv = helper.getView(R.id.item_title_img);
        switch (helper.getItemViewType()) {
            case PublicClassMultiItem.NO_CLASS_TITLE:
                helper.setText(R.id.item_title_tv,R.string.publicClass_noClassTitle);
                iv.setImageResource(R.drawable.noclass);
                helper.addOnClickListener(R.id.item_title_llMare);
                break;
            case PublicClassMultiItem.NO_CLASS_LIST:
                break;

            case PublicClassMultiItem.CLASSING_LIST:
                RecyclerView rv1 = helper.getView(R.id.item_home_rv);
                List<HomeMultiItem.Teacher> data1 = new ArrayList<>();
                data1.add(new HomeMultiItem.Teacher("",""));
                data1.add(new HomeMultiItem.Teacher("",""));
                data1.add(new HomeMultiItem.Teacher("",""));

                HomeTeacherAdapter adapter1 = new HomeTeacherAdapter(data1);

                LinearLayoutManager ms1= new LinearLayoutManager(mContext);
                ms1.setOrientation(LinearLayoutManager.HORIZONTAL);
                rv1.setLayoutManager(ms1);

                rv1.setAdapter(adapter1);
                adapter1.setOnItemClickListener(this);
                break;
            case PublicClassMultiItem.CLASSED_TITLE:
                helper.setText(R.id.item_title_tv,R.string.publicClass_ClassedTitle);
                iv.setImageResource(R.drawable.classed);
                helper.addOnClickListener(R.id.item_title_llMare);
                break;
            case PublicClassMultiItem.CLASSED_LIST:
                RecyclerView rv = helper.getView(R.id.item_publicClass_classing_rv);
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
            case PublicClassMultiItem.CLASSING_TITLE:
                helper.setText(R.id.item_title_tv,R.string.publicClass_ClassingTitle);
                iv.setImageResource(R.drawable.classing);
                helper.addOnClickListener(R.id.item_title_llMare);
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
