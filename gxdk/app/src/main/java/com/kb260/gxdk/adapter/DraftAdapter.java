package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.model.Feedback;
import com.kb260.gxdk.utils.ImageLoader;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/13
 */

public class DraftAdapter extends BaseQuickAdapter<Feedback,BaseViewHolder>{
    public DraftAdapter(@Nullable List<Feedback> data) {
        super(R.layout.item_draft, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, Feedback item) {
        ImageView imageView = helper.getView(R.id.item_draft_iv);
        if (item.getType() == 1){
            helper.setVisible(R.id.item_draft_add,false);
            helper.setVisible(R.id.item_draft_bg,true);
            helper.setVisible(R.id.item_draft_tv,false);
            ImageLoader.showImage(imageView,item.getUrl());
        }if (item.getType() == 0){
            helper.setVisible(R.id.item_draft_add,true);
            helper.setVisible(R.id.item_draft_bg,false);
        }
    }
}
