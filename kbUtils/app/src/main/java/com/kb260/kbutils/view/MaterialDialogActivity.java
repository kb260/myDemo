package com.kb260.kbutils.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kb260.kbutils.R;
import com.kb260.kbutils.view.base.BaseActivity;
import com.kb260.kbutils.widget.dialog_material.MaterialDialog;

import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/1/11
 */
public class MaterialDialogActivity extends BaseActivity {
    /**
     *开启页面
     */
    public static void start(Context context){
        Intent intent = new Intent(context,MaterialDialogActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_material_dialog;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
    }

    @OnClick(R.id.a_materialDialog_bt1)
    public void bt1(){
        MaterialDialog mMaterialDialog = new MaterialDialog(this);
        mMaterialDialog.setTitle("MaterialDialog")
                .setMessage(
                        "圣诞放假啊连接方式垃圾法拉盛")
                //mMaterialDialog.setBackgroundResource(R.drawable.background);
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        mMaterialDialog.dismiss();
                    }
                })
                .setNegativeButton("取消",
                        new View.OnClickListener() {
                            @Override public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        })
                .setCanceledOnTouchOutside(true)
                // You can change the message anytime.
                // mMaterialDialog.setTitle("提示");
                .setOnDismissListener(
                        new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                Toast.makeText(MaterialDialogActivity.this,
                                        "onDismiss",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
        mMaterialDialog.show();
    }

    @OnClick(R.id.a_materialDialog_bt2)
    public void bt2(){
        MaterialDialog mMaterialDialog = new MaterialDialog(this);
        View view = LayoutInflater.from(this)
                .inflate(R.layout.dialog_loading, null);
        mMaterialDialog.setCanceledOnTouchOutside(true);
        mMaterialDialog.setView(view).show();
    }
    static int i = 0;
    @OnClick(R.id.a_materialDialog_bt3)
    public void bt3(){
        MaterialDialog mMaterialDialog = new MaterialDialog(this);
        if (mMaterialDialog != null) {
            if (i % 2 != 0) {
                mMaterialDialog.setBackgroundResource(
                        R.drawable.background);
            } else {
                Resources res = getResources();
                Bitmap bmp = BitmapFactory.decodeResource(res,
                        R.drawable.background2);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(
                        getResources(), bmp);
                mMaterialDialog.setBackground(bitmapDrawable);
            }
            mMaterialDialog.setCanceledOnTouchOutside(true).show();
            i++;
            Toast.makeText(getApplicationContext(),
                    "Try to click again~", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    "You should init firstly!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @OnClick(R.id.a_materialDialog_bt4)
    public void bt4(){
        final ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        for (int j = 0; j < 38; j++) {
            arrayAdapter.add("This is item " + j);
        }

        ListView listView = new ListView(this);
        listView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        float scale = getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (8 * scale + 0.5f);
        listView.setPadding(0, dpAsPixels, 0, dpAsPixels);
        listView.setDividerHeight(0);
        listView.setAdapter(arrayAdapter);

        final MaterialDialog alert = new MaterialDialog(this).setTitle(
                "MaterialDialog").setContentView(listView);

        alert.setPositiveButton("OK", new View.OnClickListener() {
            @Override public void onClick(View v) {
                alert.dismiss();
            }
        });

        alert.show();
    }

    @OnClick(R.id.a_materialDialog_bt5)
    public void bt5(){
        final MaterialDialog alert = new MaterialDialog(this).setTitle(
                "MaterialDialog")
                .setContentView(
                        R.layout.custom_message_content);

        alert.setPositiveButton("OK", new View.OnClickListener() {
            @Override public void onClick(View v) {
                alert.dismiss();
            }
        });
        alert.show();
    }

    @OnClick(R.id.a_materialDialog_bt6)
    public void bt6(){
        final MaterialDialog materialDialog = new MaterialDialog(this);
        //materialDialog.setMessage("This is a dialog without title. This is a dialog without title. This is a dialog without title. This is a dialog without title. This is a dialog without title. ")
        materialDialog.setMessage(
                "This is a dialog without title. This is a dialog without title. This is a dialog without title. " +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        "This is a dialog without title. This is a dialog without title." +
                        " ")
                .setPositiveButton(android.R.string.yes,
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                materialDialog.dismiss();
                            }
                        });
        materialDialog.show();

    }
}
