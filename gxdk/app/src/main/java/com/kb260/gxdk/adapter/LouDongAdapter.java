package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.LouDong;
import com.kb260.gxdk.entity.Loupan;

import java.util.List;

/**
 * @author KB260
 *         Created on  2017/12/29
 */

public class LouDongAdapter extends BaseQuickAdapter<LouDong,BaseViewHolder>{
    public LouDongAdapter(@Nullable List<LouDong> data) {
        super(R.layout.item_house, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LouDong item) {
        helper.setText(R.id.item_house_tv,item.getBuildingName());
    }
}
