package com.kb260.gxdk.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.CharacterLogMultiItem;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class MyCharacterApplicationLogLogAdapter extends BaseMultiItemQuickAdapter<CharacterLogMultiItem,BaseViewHolder>{
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyCharacterApplicationLogLogAdapter(List<CharacterLogMultiItem> data) {
        super(data);
        addItemType(CharacterLogMultiItem.TYPE_DLS, R.layout.item_my_character_log_dls);
        addItemType(CharacterLogMultiItem.TYPE_YHJL, R.layout.item_my_character_log_yhjl);
    }

    @Override
    protected void convert(BaseViewHolder helper, CharacterLogMultiItem item) {
        switch (helper.getItemViewType()) {
            case CharacterLogMultiItem.TYPE_DLS:
                helper.setText(R.id.item_myCharacterLogDls_tvTime,item.getApplicationLog().getCreatetime());
                helper.setText(R.id.item_myCharacterLogDls_tvType,"代理商");
                helper.setText(R.id.item_myCharacterLogDls_tvName,item.getApplicationLog().getRealname());
                helper.setText(R.id.item_myCharacterLogDls_tvPhone,item.getApplicationLog().getLink());
                helper.setText(R.id.item_myCharacterLogDls_tvCompanyName,item.getApplicationLog().getCompany());
                helper.setText(R.id.item_myCharacterLogDls_tvCompanyAddress,item.getApplicationLog().getCompanyaddress());
                switch (item.getApplicationLog().getApplyflag()) {
                    case "0"://审核中
                        helper.setText(R.id.item_myCharacterLogDls_tvStatus,"审核中");
                        break;
                    case "1"://已通过
                        helper.setText(R.id.item_myCharacterLogDls_tvStatus,"已通过");
                        break;
                    case "2"://未通过
                        helper.setText(R.id.item_myCharacterLogDls_tvStatus,"未通过");
                        break;
                    default:
                        break;
                }
                helper.setText(R.id.item_myCharacterLogDls_tvReason,item.getApplicationLog().getReverse());
                break;
            case CharacterLogMultiItem.TYPE_YHJL:
                helper.setText(R.id.item_myCharacterLogYhjl_tvTime,item.getApplicationLog().getCreatetime());
                helper.setText(R.id.item_myCharacterLogYhjl_tvType,"客户经理");
                helper.setText(R.id.item_myCharacterLogYhjl_tvName,item.getApplicationLog().getRealname());
                helper.setText(R.id.item_myCharacterLogYhjl_tvPhone,item.getApplicationLog().getLink());
                helper.setText(R.id.item_myCharacterLogYhjl_tvBank,item.getApplicationLog().getBank());
                helper.setText(R.id.item_myCharacterLogYhjl_tvBranch,item.getApplicationLog().getBranchname());
                helper.setText(R.id.item_myCharacterLogYhjl_tvBankAddress,item.getApplicationLog().getBankaddress());
                switch (item.getApplicationLog().getApplyflag()) {
                    case "0"://审核中
                        helper.setText(R.id.item_myCharacterLogYhjl_tvStatus,"审核中");
                        break;
                    case "1"://已通过
                        helper.setText(R.id.item_myCharacterLogYhjl_tvStatus,"已通过");
                        break;
                    case "2"://未通过
                        helper.setText(R.id.item_myCharacterLogYhjl_tvStatus,"未通过");
                        break;
                    default:
                        break;
                }
                helper.setText(R.id.item_myCharacterLogYhjl_tvReason,item.getApplicationLog().getReverse());
                break;
            default:
                break;
        }
    }
}
