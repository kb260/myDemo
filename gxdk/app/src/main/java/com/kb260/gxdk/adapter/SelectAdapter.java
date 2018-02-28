package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.NewPlate;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/10
 */

public class SelectAdapter extends BaseQuickAdapter<NewPlate.Bean,BaseViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
    public SelectAdapter( @Nullable List<NewPlate.Bean> data) {
        super(R.layout.item_select, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewPlate.Bean item) {
        helper.setText(R.id.item_select_value,item.getBig_ppname());
    }

    @Override
    public long getHeaderId(int position) {
        return getItem(position).getPin().charAt(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return new RecyclerView.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_header, parent, false)) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(getItem(position).getPin());
    }
}
