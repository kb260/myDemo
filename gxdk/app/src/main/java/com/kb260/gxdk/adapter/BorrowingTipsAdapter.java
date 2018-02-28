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

public class BorrowingTipsAdapter extends BaseQuickAdapter<Information,BaseViewHolder>{
    public BorrowingTipsAdapter( @Nullable List<Information> data) {
        super(R.layout.item_borrowing_tips, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Information item) {
        helper.setText(R.id.item_borrowingTips_tv,item.getTitle());
        ImageView imageView = helper.getView(R.id.item_borrowingTips_iv);
        ImageLoader.showImage(imageView,item.getPicture());
    }
}
