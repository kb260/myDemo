package com.kb260.bxjy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.bxjy.R;
import com.kb260.bxjy.model.entity.MyCollectData;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/6
 */

public class MyCollectAdapter extends BaseQuickAdapter<MyCollectData,BaseViewHolder>{
    public MyCollectAdapter( @Nullable List<MyCollectData> data) {
        super(R.layout.item_mycollect, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCollectData item) {

    }
}
