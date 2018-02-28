package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.PlateDetailThreeSection;

import java.util.List;

/**
 * @author  KB260
 * Created on  2017/12/28
 */
public class SelectThreeAdapter extends BaseSectionQuickAdapter<PlateDetailThreeSection,BaseViewHolder> {
    public SelectThreeAdapter(@Nullable List<PlateDetailThreeSection> data) {
        super(R.layout.item_select_two,R.layout.item_select_two_header, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, PlateDetailThreeSection item) {
        helper.setText(R.id.select_two_tvHeader,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlateDetailThreeSection item) {
        helper.setText(R.id.select_two_tv,item.t.getCxname());
        helper.addOnClickListener(R.id.select_two_tv);
    }
}
