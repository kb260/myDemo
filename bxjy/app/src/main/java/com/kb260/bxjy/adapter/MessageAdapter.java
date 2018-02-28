package com.kb260.bxjy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.entity.MessageData;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/6
 */

public class MessageAdapter extends BaseQuickAdapter<MessageData,BaseViewHolder>{
    public MessageAdapter( @Nullable List<MessageData> data) {
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageData item) {

    }
}
