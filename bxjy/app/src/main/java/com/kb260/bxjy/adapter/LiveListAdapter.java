package com.kb260.bxjy.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.entity.LiveData;
import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/5
 */

public class LiveListAdapter extends BaseQuickAdapter<LiveData,BaseViewHolder>{

    public LiveListAdapter( @Nullable List<LiveData> data) {
        super(R.layout.item_livelist, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiveData item) {
        if (helper.getAdapterPosition() == 1){
            helper.setBackgroundRes(R.id.item_liveList_tvLive,R.drawable.livied_shape);
            helper.setText(R.id.item_liveList_tvLive,"直播结束");
        }else if (helper.getAdapterPosition() == 2){
            helper.setBackgroundRes(R.id.item_liveList_tvLive,R.drawable.nolive);
            helper.setText(R.id.item_liveList_tvLive,"未开始");
        }
    }
}
