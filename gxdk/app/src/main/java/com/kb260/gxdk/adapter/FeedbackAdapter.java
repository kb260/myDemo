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

public class FeedbackAdapter extends BaseQuickAdapter<Feedback,BaseViewHolder>{
    public FeedbackAdapter( @Nullable List<Feedback> data) {
        super(R.layout.item_feedback, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Feedback item) {
        if (item.getType() == 1){
            ImageView imageView = helper.getView(R.id.item_feedback_iv);
            helper.setVisible(R.id.item_feedback_ivDefault,false);
            ImageLoader.showImage(imageView,item.getUrl());
        }
    }
}
