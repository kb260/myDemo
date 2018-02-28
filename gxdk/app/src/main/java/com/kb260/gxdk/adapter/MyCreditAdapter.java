package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.MyCreditData;
import com.kb260.gxdk.utils.Action;

import java.util.List;

/**
 * @author KB260
 *         Created on  2017/12/7
 */

public class MyCreditAdapter extends BaseQuickAdapter<MyCreditData, BaseViewHolder> {
    public MyCreditAdapter(@Nullable List<MyCreditData> data) {
        super(R.layout.item_my_credit, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCreditData item) {

        helper.setText(R.id.item_myCredit_tvStatus, item.getStatus());
        helper.setText(R.id.item_myCredit_tvIsHouse, item.getIsroom());
        helper.setText(R.id.item_myCredit_tvIsCar, item.getIscar());
        switch (item.getIscredit()) {
            case Action.KIND_XD:
                helper.setText(R.id.item_myCredit_tvType, "信用咨询");
                helper.setText(R.id.item_myCredit_tvMoneyKey, "贷款金额：");
                break;
            case Action.KIND_DZD:
                helper.setText(R.id.item_myCredit_tvType, "垫资咨询");
                helper.setText(R.id.item_myCredit_tvMoneyKey, "垫资金额：");
                break;
            default:
                break;
        }
        helper.setText(R.id.item_myCredit_tvMoney, item.getCount());
    }
}
