package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.model.Order;
import com.kb260.gxdk.utils.ImageLoader;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */

public class GrabMonadAdapter extends BaseQuickAdapter<Order,BaseViewHolder>{
    public GrabMonadAdapter(@Nullable List<Order> data) {
        super(R.layout.item_grabmonad, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Order item) {
        helper.setText(R.id.item_grabMonad_tvName,item.getRoomloan().getRealname());
        helper.setText(R.id.item_grabMonad_tvTime,item.getCreatetime());
        helper.setText(R.id.item_grabMonad_tvAge,item.getRoomloan().getAge());

        String a = "";
        switch (item.getStatus()){
            case "0"://
                a ="抢单";
                break;
            case "1"://
                a ="初审";
                break;
            case "2"://
                a ="终审";
                break;
            case "3"://
                switch (item.getRoomloan().getStatus()){
                    case "5":
                        if (item.getOthertime().equals("0")){
                            a ="确认放款";
                        }else {
                            a ="等待用户确认";
                        }
                        break;
                    case "4":
                        if (item.getOthertime().equals("0")){
                            a ="等待用户支付";
                        }
                        break;
                    default:
                        break;
                }
                break;
            case "4"://
                a ="已成功";
                break;
            default:
                break;
        }
        helper.setText(R.id.item_grabMonad_tv,a);

        helper.setText(R.id.item_grabMonad_tvIsYuQi,item.getRoomloan().getIsoverdue());

        switch (item.getRoomloan().getType()){
            case "1":
                helper.setText(R.id.item_grabMonad_tvType,"房贷");
                helper.setText(R.id.item_grabMonad_tvAddress,item.getRoomloan().getRoomaddress());
                helper.setText(R.id.item_grabMonad_tvArea,"房屋面积"+item.getRoomloan().getArea());
                helper.setText(R.id.item_grabMonad_tvNature,item.getRoomloan().getNature());
                break;
            case "2":
                helper.setText(R.id.item_grabMonad_tvType,"车贷");
                helper.setVisible(R.id.item_grabMonad_tvAddress,false);
                helper.setText(R.id.item_grabMonad_tvArea,item.getRoomloan().getCarbrand());
                helper.setText(R.id.item_grabMonad_tvNature,item.getRoomloan().getCarno());
                break;
            default:
                break;
        }
        helper.setText(R.id.item_grabMonad_tvRate,item.getRoomloan().getRate()+"厘~"+item.getRoomloan().getMaxrate()+"厘");
        helper.setText(R.id.item_grabMonad_tvExpect,String.valueOf(item.getRoomloan().getCount()));
        helper.setText(R.id.item_grabMonad_tvCredit,item.getRoomloan().getGoodreliability());
        helper.setText(R.id.item_grabMonad_tvDeadline,item.getRoomloan().getLoantime());

        ImageView iv = helper.getView(R.id.item_grabMonad_ivUser);
        ImageLoader.showImage(iv,item.getAppuser().getPicture());

        helper.addOnClickListener(R.id.item_grabMonad_tv);
        if (helper.getAdapterPosition() == 0){
            helper.setVisible(R.id.item_grabMonad_recommend,true);
        }
    }


}
