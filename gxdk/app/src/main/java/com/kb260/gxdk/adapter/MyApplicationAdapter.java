package com.kb260.gxdk.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kb260.gxdk.R;
import com.kb260.gxdk.model.MyApplication;
import com.kb260.gxdk.utils.Action;
import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/10
 */

public class MyApplicationAdapter extends BaseQuickAdapter<MyApplication,BaseViewHolder>{
    public MyApplicationAdapter( @Nullable List<MyApplication> data) {
        super(R.layout.item_my_application, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyApplication item) {
        String type = item.getType();
        if (type != null){
            switch (type){
                case "1":
                    type = "房屋服务";
                    break;
                case "2":
                    type = "车辆服务";
                    break;
                default:
                    break;
            }
            helper.setText(R.id.item_myApplication_tvType,type);
        }
        helper.setText(R.id.item_myApplication_tvMoney,"贷款金额："+item.getCount());
        helper.setText(R.id.item_myApplication_tvRate,"接受利率："+item.getRate()+"厘~"+item.getMaxrate()+"厘");
        helper.setText(R.id.item_myApplication_tvMouth,"贷款期限："+item.getLoantime());

        String myStatus = "";
        switch (item.getStatus()){
            case Action.ORDER_TYPE_WZF://未支付押金
                myStatus = "未支付押金";
                helper.setVisible(R.id.item_myApplication_tvPay,true);
                break;
            case Action.ORDER_TYPE_YZF://已支付押金
                myStatus = "已支付押金";
                helper.setVisible(R.id.item_myApplication_tvPay,false);
                break;
            case Action.ORDER_TYPE_YQD://以抢单
                myStatus = "已抢单";
                helper.setVisible(R.id.item_myApplication_tvPay,false);
                break;
            case Action.ORDER_TYPE_YCS://已初审
                myStatus = "已初审";
                helper.setVisible(R.id.item_myApplication_tvPay,false);
                break;
            case Action.ORDER_TYPE_YZS://已终审
                myStatus = "已终审";
                helper.setVisible(R.id.item_myApplication_tvPay,true);
                break;
            case Action.ORDER_TYPE_YZFFWF://已支付服务费
                myStatus = "已支付服务费";
                if (item.getReserve().equals("1")){
                    helper.setVisible(R.id.item_myApplication_tvPay,true);
                    helper.setText(R.id.item_myApplication_tvPay,"确认完成");
                }else {
                    helper.setVisible(R.id.item_myApplication_tvPay,false);
                }
                break;
            case Action.ORDER_TYPE_DKCG://贷款成功
                myStatus = "贷款成功";
                helper.setVisible(R.id.item_myApplication_tvPay,false);
                break;
            case Action.ORDER_TYPE_YSX://已失效
                myStatus = "已失效";
                helper.setVisible(R.id.item_myApplication_tvPay,false);
                break;
            case Action.ORDER_TYPE_DCL://待处理
                myStatus = "待处理";
                helper.setVisible(R.id.item_myApplication_tvPay,false);
                break;
            case Action.ORDER_TYPE_9://押金申请解锁中
                myStatus = "押金申请解锁中";
                helper.setVisible(R.id.item_myApplication_tvPay,false);
                break;
            default:
                helper.setVisible(R.id.item_myApplication_tvPay,false);
                break;
        }
        helper.setText(R.id.item_myApplication_tvStatus,myStatus);
    }
}
