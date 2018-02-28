package com.kb260.bxjy.weight.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.kb260.bxjy.R;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.ui.home.OrderSureActivity;
import com.kb260.bxjy.utils.Action;

/**
 * @author KB260
 *         Created on  2018/2/2
 */

public class ConfirmDialog extends BaseNiceDialog {
    private int type;

    public static ConfirmDialog newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        type = bundle.getInt("type",-1);
    }

    @Override
    public int intLayoutId() {
        return R.layout.confirm_layout;
    }

    @Override
    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
        switch (type){
            case Action.DIALOG_TYPE_BUY_CLASS:
                holder.setText(R.id.title, "提示");
                holder.setText(R.id.message, "应版权方要求：本视频需购买看完整版原价:7元订单列表中支付订单!");
                break;
            case Action.DIALOG_TYPE_CHANGE_PW:
                holder.setVisibility(R.id.message,View.GONE);
                holder.setVisibility(R.id.cancel,View.GONE);
                holder.setVisibility(R.id.line,View.GONE);
                holder.setText(R.id.title, "修改密码成功");
                break;
            case Action.DIALOG_TYPE_EXIT:
                holder.setText(R.id.title, "确定退出登录");
                holder.setVisibility(R.id.message,View.GONE);
                break;
            case Action.DIALOG_TYPE_CLEAR:
                holder.setText(R.id.title, "确定清理缓存？");
                holder.setVisibility(R.id.message,View.GONE);
                break;
            default:
                break;
        }
        holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                switch (type){
                    case Action.DIALOG_TYPE_BUY_CLASS:
                        OrderSureActivity.start(dialog.getContext(),"","",1.2);
                        break;
                    case Action.DIALOG_TYPE_CHANGE_PW:
                        dialog.getActivity().finish();
                        break;
                    case Action.DIALOG_TYPE_EXIT:
                        KbApplication.exitAppList();
                        break;
                    case Action.DIALOG_TYPE_CLEAR:

                        break;
                    default:
                        break;
                }
            }
        });
    }

}
