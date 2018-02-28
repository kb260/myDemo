package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.model.MyProduct;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/15
 */

public class MyProductAdapter extends BaseQuickAdapter<MyProduct,BaseViewHolder>{
    public MyProductAdapter( @Nullable List<MyProduct> data) {
        super(R.layout.item_my_product, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyProduct item) {
        if (item.getType()!=null){
            switch (item.getType()){
                case "1":
                    helper.setText(R.id.item_myProduct_type,"房屋服务");
                    break;
                case "2":
                    helper.setText(R.id.item_myProduct_type,"车辆服务");
                    break;
                default:
                    break;
            }
        }
        helper.setText(R.id.item_myProduct_money,item.getCount());
        helper.setText(R.id.item_myProduct_proportion,item.getRate()+"厘~"+item.getMaxrate()+"厘");
        helper.setText(R.id.item_myProduct_time,item.getPaytime());
        helper.setText(R.id.item_myProduct_type2,item.getPaytype());

    }
}
