package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.model.Information;
import com.kb260.gxdk.utils.ImageLoader;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author KB260
 *         Created on  2017/11/14
 */

public class InformationAdapter extends BaseQuickAdapter<Information,BaseViewHolder>{
    public InformationAdapter(@Nullable List<Information> data) {
        super(R.layout.item_information, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Information item) {
        helper.setText(R.id.item_information_tvTitle,item.getTitle());
        helper.setText(R.id.item_information_tvTime,item.getCreatetime());
        helper.setText(R.id.item_information_tvContent,item.getContent());
        CircleImageView iv = helper.getView(R.id.item_information_iv);
        ImageLoader.showImage(iv,item.getPicture());
    }
}
