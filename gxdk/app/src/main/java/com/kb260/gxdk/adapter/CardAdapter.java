package com.kb260.gxdk.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.Card;
import com.kb260.gxdk.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import java.util.List;

/**
 * Created by kb260 on 2017/9/26.
 * Email: work260@outlook.com
 */

public class CardAdapter extends RecyclerSwipeAdapter<CardAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView textViewPos;
        TextView textViewData;
        RelativeLayout ivDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.item_card_swipe);
            textViewPos = itemView.findViewById(R.id.item_card_tvBank);
            textViewData = itemView.findViewById(R.id.item_card_tvBranch);
            ivDelete = itemView.findViewById(R.id.item_cardDel);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(getClass().getSimpleName(), "onItemSelected: " + textViewData.getText().toString());
                    Toast.makeText(view.getContext(), "onItemSelected: " + textViewData.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private List<Card> cards;

    public CardAdapter(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        viewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delCard(cards.get(position).getId(), view.getContext(),viewHolder,position);
                /*mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                mDataset.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mDataset.size());
                mItemManger.closeAllItems();
                Toast.makeText(view.getContext(), "Deleted " + viewHolder.textViewData.getText().toString() + "!", Toast.LENGTH_SHORT).show();*/
            }
        });
        viewHolder.textViewPos.setText(cards.get(position).getBankname());
        viewHolder.textViewData.setText(showNumber(cards.get(position).getBankcardno()));
        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.item_card_swipe;
    }

    private void delCard(int id, Context context,ViewHolder viewHolder,int position) {
        Api.getDefault().delbank(KBApplication.token,KBApplication.userid, id)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(context) {
                    @Override
                    protected void success(String list) {
                        mItemManger.closeItem(position);
                        mItemManger.removeShownLayouts(viewHolder.swipeLayout);
                        cards.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, cards.size());
                        mItemManger.closeAllItems();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }
    private String showNumber(String bankCard) {
        int hideLength = 8;//替换位数，这里替代中间8位
        int startIndex = bankCard.length()/2-hideLength/2;
        String replaceSymbol = "*";//替换符号，这里用“#”为例
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i<bankCard.length();i++){
            char number = bankCard.charAt(i);
            if (i>=startIndex-1&&i<startIndex+hideLength){
                stringBuilder.append(replaceSymbol);
            }else {
                stringBuilder.append(number);
            }
        }
        return stringBuilder.toString();
    }
}
