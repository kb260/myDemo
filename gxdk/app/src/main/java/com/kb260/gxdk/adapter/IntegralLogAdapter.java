package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.model.IntegralLog;
import java.util.List;

/**
 * Created by kb260 on 2017/9/29.
 * Email: work260@outlook.com
 */

public class IntegralLogAdapter extends BaseQuickAdapter<IntegralLog,BaseViewHolder>{
    public IntegralLogAdapter(@Nullable List<IntegralLog> data) {
        super(R.layout.item_integral_log, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralLog item) {
        helper.setText(R.id.item_integralLog_type,item.getReserve());
        helper.setText(R.id.item_integralLog_status,item.getType());
        helper.setText(R.id.item_integralLog_integer,item.getScore());
        helper.setText(R.id.item_integralLog_time,item.getCreatetime());
    }
}
