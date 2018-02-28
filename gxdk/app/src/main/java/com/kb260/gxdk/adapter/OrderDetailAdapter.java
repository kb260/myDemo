package com.kb260.gxdk.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.OrderDetailMultiItem;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class OrderDetailAdapter extends BaseMultiItemQuickAdapter<OrderDetailMultiItem,BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public OrderDetailAdapter(List<OrderDetailMultiItem> data) {
        super(data);
        addItemType(OrderDetailMultiItem.DESCRIPTION, R.layout.item_order_detail_description);
        addItemType(OrderDetailMultiItem.LINE, R.layout.item_order_detail_line);
        addItemType(OrderDetailMultiItem.RESULT, R.layout.item_order_detail_result);
        addItemType(OrderDetailMultiItem.RESULT_DESCRIPTION, R.layout.item_order_detail_result_description);
        addItemType(OrderDetailMultiItem.BUTTON, R.layout.item_order_detail_button);
        addItemType(OrderDetailMultiItem.PHONE, R.layout.item_order_detail_description);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailMultiItem item) {
        switch (helper.getItemViewType()) {
            case OrderDetailMultiItem.DESCRIPTION:
                helper.setText(R.id.a_orderDetail_key,item.getKey());
                helper.setText(R.id.a_orderDetail_value,item.getValue());
                break;
            case OrderDetailMultiItem.LINE:
                break;
            case OrderDetailMultiItem.PHONE:
                helper.setText(R.id.a_orderDetail_key,item.getKey());
                helper.setBackgroundRes(R.id.a_orderDetail_value,R.drawable.phone_);
                helper.setText(R.id.a_orderDetail_value,"点击联系客户");
                helper.addOnClickListener(R.id.a_orderDetail_value);
                break;
            case OrderDetailMultiItem.RESULT_DESCRIPTION:
                helper.setText(R.id.item_order_detail_result_description_tvKey,item.getKey());
                helper.setText(R.id.item_order_detail_result_description_tvValue,item.getValue());
                break;
            case OrderDetailMultiItem.RESULT:
                helper.setText(R.id.item_order_detail_result_tv,item.getStatus());
                break;
            case OrderDetailMultiItem.BUTTON:
                helper.addOnClickListener(R.id.item_order_detail_bt);
                helper.setText(R.id.item_order_detail_bt,item.getStatus());
                break;
            default:
                break;
        }
    }
}
