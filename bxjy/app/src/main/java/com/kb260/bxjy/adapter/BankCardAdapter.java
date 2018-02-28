package com.kb260.bxjy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.entity.BankCard;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/6
 */

public class BankCardAdapter extends BaseQuickAdapter<BankCard,BaseViewHolder>{
    public BankCardAdapter( @Nullable List<BankCard> data) {
        super(R.layout.item_bank_card, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BankCard item) {

    }
}
