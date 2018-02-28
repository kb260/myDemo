package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.model.IntegralDetail;

import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/20
 */

public class IntegralDetailAdapter extends BaseQuickAdapter<IntegralDetail.DetailBean,BaseViewHolder>{
    public IntegralDetailAdapter( @Nullable List<IntegralDetail.DetailBean> data) {
        super(R.layout.item_integral_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralDetail.DetailBean item) {
        switch (item.getType()){
            case "1":
                helper.setText(R.id.item_integral_detail_tvType,"充值");
                break;
            case "2":
                helper.setText(R.id.item_integral_detail_tvType,"消费");
                break;
            case "3":
                helper.setText(R.id.item_integral_detail_tvType,"商城积分奖励");
                break;
            case "4":
                helper.setText(R.id.item_integral_detail_tvType,"充值提现");
                break;
            case "5":
                helper.setText(R.id.item_integral_detail_tvType,"提现积分奖励");
                break;
            case "6":
                helper.setText(R.id.item_integral_detail_tvType,"提现返还");
                break;
            case "7":
                helper.setText(R.id.item_integral_detail_tvType,"奖励提现");
                break;
            default:
                break;
        }

        helper.setText(R.id.item_integral_detail_tvTime,item.getCreatetime());
        helper.setText(R.id.item_integral_detail_tvMoney,String.valueOf(item.getScore()));
    }
}
