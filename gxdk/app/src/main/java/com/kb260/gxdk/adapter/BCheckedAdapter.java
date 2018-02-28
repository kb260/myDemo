package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.CheckedData;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/23
 */

public class BCheckedAdapter extends BaseQuickAdapter<CheckedData,BaseViewHolder>{
    public List<CheckedData> datas;
    public BCheckedAdapter( @Nullable List<CheckedData> data) {
        super(R.layout.item_b_checked, data);
        datas = getData();

    }

    @Override
    protected void convert(final BaseViewHolder helper, final CheckedData item) {
        helper.setChecked(R.id.item_b_checkedCb,getData().get(helper.getAdapterPosition()).isChecked());

        helper.setText(R.id.item_b_checkedName,item.getName());
        helper.setOnCheckedChangeListener(R.id.item_b_checkedCb, (compoundButton, b) -> {
            CheckedData cd = getItem(helper.getAdapterPosition());
            cd.setChecked(b);
            getData().set(helper.getAdapterPosition(),cd);
        });

    }
}
