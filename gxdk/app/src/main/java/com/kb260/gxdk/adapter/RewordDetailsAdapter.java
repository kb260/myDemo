package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.entity.MyTeamOne;
import com.kb260.gxdk.utils.ImageLoader;
import com.kb260.gxdk.utils.StringUtils;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/28
 */

public class RewordDetailsAdapter extends BaseQuickAdapter<MyTeamOne.DataBean,BaseViewHolder>{
    public RewordDetailsAdapter(@Nullable List<MyTeamOne.DataBean> data) {
        super(R.layout.item_reword_details, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyTeamOne.DataBean item) {
        ImageView iv = helper.getView(R.id.item_rewordDetail_iv);
        ImageLoader.showImage(iv,item.getPicture());
        String nick = item.getNick();
        if (StringUtils.isEmpty(nick)){
            nick = item.getTelphone();
        }
        helper.setText(R.id.item_rewordDetail_tvNickname,nick);
        helper.setText(R.id.item_rewordDetail_tvId,"ID:"+item.getId());
        helper.setText(R.id.item_rewordDetail_tvPhone,item.getTelphone());
        helper.setText(R.id.item_rewordDetail_tvTime,item.getCreatetime());
        String type = "";
        switch (item.getType()){
            case "0":
                type = "用户";
                break;
            case "1":
                type = "代理商";
                break;
            case "2":
                type = "客户经理";
                break;
            default:
                break;
        }
        helper.setText(R.id.item_rewordDetail_tvType,type);
    }
}
