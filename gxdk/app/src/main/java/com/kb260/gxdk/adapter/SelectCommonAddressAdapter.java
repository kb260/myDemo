package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/12/28
 */

public class SelectCommonAddressAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    public SelectCommonAddressAdapter(@Nullable List<String> data) {
        super(R.layout.item_select, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
