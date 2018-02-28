package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.WithdrawLog;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/27
 */

public class WithdrawLogAdapter extends BaseQuickAdapter<WithdrawLog,BaseViewHolder>{
    public WithdrawLogAdapter(@Nullable List<WithdrawLog> data) {
        super(R.layout.item_withdraw_log, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WithdrawLog item) {
        helper.setText(R.id.item_withdrawLog_no,item.getReserve());
        helper.setText(R.id.item_withdrawLog_money,String.valueOf(item.getScore()));
        helper.setText(R.id.item_withdrawLog_time,item.getCreatetime());
        helper.setText(R.id.item_withdrawLog_status,item.getStatus());
        helper.setText(R.id.item_withdrawLog_type,item.getScoretype());
    }
}
