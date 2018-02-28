package com.kb260.bxjy.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.HomeMultiItem;
import com.kb260.bxjy.model.MoreMultiItem;
import com.kb260.bxjy.ui.home.TeacherDetailActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class MoreAdapter extends BaseMultiItemQuickAdapter<MoreMultiItem,BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MoreAdapter(List<MoreMultiItem> data) {
        super(data);
        addItemType(MoreMultiItem.HOME_LIVING, R.layout.item_home_live);
        addItemType(MoreMultiItem.HOME_HIT, R.layout.item_home_live);
        addItemType(MoreMultiItem.HOME_TEACHER, R.layout.item_home_teacher);
        addItemType(MoreMultiItem.PUBLIC_CLASSED, R.layout.item_publicclass_classing);
        addItemType(MoreMultiItem.PUBLIC_CLASSING, R.layout.item_home_live);
        addItemType(MoreMultiItem.PUBLIC_CLASS_SOON, R.layout.item_home_live);
    }

    @Override
    protected void convert(BaseViewHolder helper, MoreMultiItem item) {
        switch (helper.getItemViewType()) {
            case MoreMultiItem.HOME_LIVING:
                initRv(helper);
                break;
            case MoreMultiItem.HOME_HIT:
                initRv(helper);
                break;

            case MoreMultiItem.HOME_TEACHER:

                break;
            case MoreMultiItem.PUBLIC_CLASSED:
                RecyclerView rv2 = helper.getView(R.id.item_publicClass_classing_rv);
                List<HomeMultiItem.Teacher> data2 = new ArrayList<>();
                data2.add(new HomeMultiItem.Teacher("",""));
                data2.add(new HomeMultiItem.Teacher("",""));
                data2.add(new HomeMultiItem.Teacher("",""));

                PublicClassTeacherAdapter adapter2 = new PublicClassTeacherAdapter(data2);

                LinearLayoutManager ms2= new LinearLayoutManager(mContext);
                ms2.setOrientation(LinearLayoutManager.HORIZONTAL);
                rv2.setLayoutManager(ms2);

                rv2.setAdapter(adapter2);
                adapter2.setOnItemClickListener(this);
                break;
            case MoreMultiItem.PUBLIC_CLASSING:
                initRv(helper);
                break;
            case MoreMultiItem.PUBLIC_CLASS_SOON:
                initRv(helper);
                break;
            default:
                break;
        }
    }

    private void initRv(BaseViewHolder helper){
        RecyclerView rv = helper.getView(R.id.item_home_rv);
        List<HomeMultiItem.Teacher> data = new ArrayList<>();
        data.add(new HomeMultiItem.Teacher("",""));
        data.add(new HomeMultiItem.Teacher("",""));
        data.add(new HomeMultiItem.Teacher("",""));

        HomeTeacherAdapter adapter = new HomeTeacherAdapter(data);

        LinearLayoutManager ms= new LinearLayoutManager(mContext);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(ms);

        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        TeacherDetailActivity.start(mContext,0);
    }
}
