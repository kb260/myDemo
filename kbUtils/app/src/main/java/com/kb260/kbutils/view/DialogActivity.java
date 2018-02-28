package com.kb260.kbutils.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.kb260.kbutils.R;
import com.kb260.kbutils.view.base.BaseActivity;
import com.kb260.kbutils.widget.dialog.BaseNiceDialog;
import com.kb260.kbutils.widget.dialog.NiceDialog;
import com.kb260.kbutils.widget.dialog.ViewConvertListener;
import com.kb260.kbutils.widget.dialog.ViewHolder;

import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/1/12
 */
public class DialogActivity extends BaseActivity {

    /**
     *开启页面
     */
    public static void start(Context context){
        Intent intent = new Intent(context,DialogActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @OnClick(R.id.a_dialog_bt1)
    public void bt1(){
        NiceDialog.init()
                .setLayoutId(R.layout.share_layout)
                .setConvertListener((ViewConvertListener) (holder, dialog) ->
                        holder.setOnClickListener(R.id.wechat, v -> {
                            Toast.makeText(DialogActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
                        }))
                .setDimAmount(0.3f)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    @OnClick(R.id.a_dialog_bt2)
    public void bt2(){
        NiceDialog.init()
                .setLayoutId(R.layout.friend_set_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {

                    }
                })
                .setShowBottom(true)
                .setHeight(310)
                .show(getSupportFragmentManager());
    }

    @OnClick(R.id.a_dialog_bt3)
    public void bt3(){
        NiceDialog.init()
                .setLayoutId(R.layout.commit_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        final EditText editText = holder.getView(R.id.edit_input);
                        editText.post(new Runnable() {
                            @Override
                            public void run() {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(editText, 0);
                            }
                        });
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    @OnClick(R.id.a_dialog_bt4)
    public void bt4(){
        NiceDialog.init()
                .setLayoutId(R.layout.ad_layout)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        holder.setOnClickListener(R.id.close, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setWidth(210)
                .setOutCancel(false)
                .setAnimStyle(R.style.EnterExitAnimation)
                .show(getSupportFragmentManager());
    }

    @OnClick(R.id.a_dialog_bt5)
    public void bt5(){
        NiceDialog.init()
                .setLayoutId(R.layout.loading_layout)
                .setWidth(100)
                .setHeight(100)
                .setDimAmount(0)
                .show(getSupportFragmentManager());
    }

    @OnClick(R.id.a_dialog_bt6)
    public void bt6(){
        ConfirmDialog.newInstance("1")
                .setMargin(60)
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }

    public static class ConfirmDialog extends BaseNiceDialog {
        private String type;

        public static ConfirmDialog newInstance(String type) {
            Bundle bundle = new Bundle();
            bundle.putString("type", type);
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
            type = bundle.getString("type");
        }

        @Override
        public int intLayoutId() {
            return R.layout.confirm_layout;
        }

        @Override
        public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
            if ("1".equals(type)) {
                holder.setText(R.id.title, "提示");
                holder.setText(R.id.message, "应版权方要求：本视频需购买\n看完整版原价:7元\n订单列表中支付订单!");
            } else if ("2".equals(type)) {
                holder.setText(R.id.title, "警告");
                holder.setText(R.id.message, "您的账号已被冻结！");
            }
            holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    cancel();
                }
            });

            holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    sure();
                }
            });
        }

        public void sure(){

        }

        public void cancel(){

        }
    }
}
