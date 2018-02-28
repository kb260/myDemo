package com.kb260.kbutils.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.kbutils.R;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/1/10
 */

public class SlideAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    public SlideAdapter( @Nullable List<String> data) {
        super(R.layout.item_silde, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_slide_tv,item);
        helper.addOnClickListener(R.id.item_slide_tvDelete);
    }
}
