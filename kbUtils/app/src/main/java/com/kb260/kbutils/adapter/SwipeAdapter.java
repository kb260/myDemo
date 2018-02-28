package com.kb260.kbutils.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kb260.kbutils.R;
import com.kb260.kbutils.widget.swipe.SwipeLayout;
import com.kb260.kbutils.widget.swipe.adapters.RecyclerSwipeAdapter;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/1/10
 */

public class SwipeAdapter extends RecyclerSwipeAdapter<SwipeAdapter.ViewHolder> {
    private List<String> data;

    public SwipeAdapter(List<String> data){
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swipe, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        viewHolder.textView.setText(data.get(position));
        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.item_swipe_swl;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView textView;
        TextView textViewDel;

        ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.item_swipe_swl);
            textView = itemView.findViewById(R.id.item_swipe_tv);
            textViewDel = itemView.findViewById(R.id.item_swipe_tvDel);
        }
    }
}
