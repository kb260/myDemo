package com.kb260.gxdk.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.CharacterApplicationLog;
import java.util.List;

/**
 * @author KB260
 *         Created on  2017/11/23
 */

public class CharacterApplicationLogAdapter extends BaseQuickAdapter<CharacterApplicationLog,BaseViewHolder>{
    public CharacterApplicationLogAdapter(@Nullable List<CharacterApplicationLog> data) {
        super(R.layout.item_character_log, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CharacterApplicationLog item) {
        helper.setText(R.id.item_character_key,item.getKey());
        helper.setText(R.id.item_character_value,item.getValue());
        if (item.getKey().equals("审核状态")){
            helper.setTextColor(R.id.item_character_value, Color.RED);
        }
    }


}
