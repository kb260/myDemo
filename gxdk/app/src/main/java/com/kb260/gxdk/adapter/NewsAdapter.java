package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.model.Information;
import com.kb260.gxdk.utils.ImageLoader;

import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/6
 */

public class NewsAdapter extends BaseQuickAdapter<Information,BaseViewHolder>{
    public NewsAdapter(@Nullable List<Information> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Information item) {
        helper.setText(R.id.item_news_tv,item.getTitle());
        helper.setText(R.id.item_news_tvTime,item.getCreatetime());
        ImageView imageView = helper.getView(R.id.item_borrowingTips_iv);
        ImageLoader.showImage(imageView,item.getPicture());

    }
}
