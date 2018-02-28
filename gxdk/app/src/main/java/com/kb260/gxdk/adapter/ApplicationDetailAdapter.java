package com.kb260.gxdk.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.ApplicationDetailMultiItem;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class ApplicationDetailAdapter extends BaseMultiItemQuickAdapter<ApplicationDetailMultiItem,BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ApplicationDetailAdapter(List<ApplicationDetailMultiItem> data) {
        super(data);
        addItemType(ApplicationDetailMultiItem.TEXT_KEY_VALUE, R.layout.item_my_application_key_value);
        addItemType(ApplicationDetailMultiItem.LINE, R.layout.item_my_application_line);
        addItemType(ApplicationDetailMultiItem.BUTTON_ONE, R.layout.item_my_application_button_one);
        addItemType(ApplicationDetailMultiItem.BUTTON_TWO, R.layout.item_my_application_button_two);
        addItemType(ApplicationDetailMultiItem.RESULT, R.layout.item_my_application_result);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApplicationDetailMultiItem item) {
        switch (helper.getItemViewType()) {
            case ApplicationDetailMultiItem.TEXT_KEY_VALUE:
                helper.setText(R.id.item_myApplicationKey,item.getKey());
                helper.setText(R.id.item_myApplicationValue,item.getValue());
                break;
            case ApplicationDetailMultiItem.LINE:
                break;
            case ApplicationDetailMultiItem.BUTTON_ONE:
                helper.setText(R.id.item_myApplication_button,item.getButton());
                helper.addOnClickListener(R.id.item_myApplication_button);
                break;
            case ApplicationDetailMultiItem.BUTTON_TWO:
                helper.setText(R.id.item_myApplication_button1,item.getKey());
                helper.setText(R.id.item_myApplication_button2,item.getValue());
                helper.addOnClickListener(R.id.item_myApplication_button1);
                helper.addOnClickListener(R.id.item_myApplication_button2);
                break;
            case ApplicationDetailMultiItem.RESULT:
                helper.setText(R.id.item_myApplication_result,item.getButton());
                break;
            default:
                break;
        }
    }
}
