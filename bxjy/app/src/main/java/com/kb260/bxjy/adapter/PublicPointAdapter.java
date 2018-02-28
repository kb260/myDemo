package com.kb260.bxjy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/9
 */

public class PublicPointAdapter extends BaseQuickAdapter<Boolean,BaseViewHolder>{
    public PublicPointAdapter(@Nullable List<Boolean> data) {
        super(R.layout.item_point, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Boolean item) {
        if (item){
            helper.setBackgroundRes(R.id.itemPoint_iv,R.drawable.point_group1);
        }else {
            helper.setBackgroundRes(R.id.itemPoint_iv,R.drawable.point_group0);
        }
    }
}
