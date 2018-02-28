package com.kb260.gxdk.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.ProductDetailMultiItem;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class ProductDetailAdapter extends BaseMultiItemQuickAdapter<ProductDetailMultiItem,BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ProductDetailAdapter(List<ProductDetailMultiItem> data) {
        super(data);
        addItemType(ProductDetailMultiItem.TEXT_KEY_VALUE, R.layout.item_my_application_key_value);
        addItemType(ProductDetailMultiItem.TEXT_KEY_VALUE_LONG, R.layout.item_product_detail_key_value_long);
        addItemType(ProductDetailMultiItem.TYPE, R.layout.item_product_detail_type);
        addItemType(ProductDetailMultiItem.BUTTON_ONE, R.layout.item_my_application_button_one);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductDetailMultiItem item) {
        switch (helper.getItemViewType()) {
            case ProductDetailMultiItem.TEXT_KEY_VALUE:
                helper.setText(R.id.item_myApplicationKey,item.getKey());
                helper.setText(R.id.item_myApplicationValue,item.getValue());
                break;
            case ProductDetailMultiItem.TEXT_KEY_VALUE_LONG:
                helper.setText(R.id.item_productDetailLong_key,item.getKey());
                helper.setText(R.id.item_productDetailLong_value,item.getValue());
                break;
            case ProductDetailMultiItem.TYPE:
                helper.setText(R.id.item_productDetail_tvType,item.getType());
                break;
            case ProductDetailMultiItem.BUTTON_ONE:
                helper.setText(R.id.item_myApplication_button,"删除");
                helper.addOnClickListener(R.id.item_myApplication_button);
                break;
            default:
                break;
        }
    }
}
