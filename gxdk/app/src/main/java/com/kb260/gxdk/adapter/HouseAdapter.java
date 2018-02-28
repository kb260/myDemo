package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.Loupan;
import java.util.List;

/**
 * @author KB260
 *         Created on  2017/12/29
 */

public class HouseAdapter extends BaseQuickAdapter<Loupan,BaseViewHolder>{
    public HouseAdapter( @Nullable List<Loupan> data) {
        super(R.layout.item_house, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Loupan item) {
        helper.setText(R.id.item_house_tv,item.getCommunityname());
    }
}
