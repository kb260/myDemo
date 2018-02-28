package com.kb260.kbutils.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.kbutils.R;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/1/8
 */

public class MainAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    public MainAdapter(@Nullable List<String> data) {
        super(R.layout.item_main, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_main_tv,item);
    }
}
