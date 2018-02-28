package com.kb260.gxdk.view.me;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.FeedbackAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.Feedback;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.GifSizeFilter;
import com.kb260.gxdk.utils.PathUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.utils.UploadImgUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 *         Created on  2017/11/13
 */
public class FeedbackActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_feedback_et)
    EditText et;
    @BindView(R.id.a_feedback_rv)
    RecyclerView rv;

    FeedbackAdapter adapter;
    List<Feedback> datas;
    String content,picture;

    public static void start(Context context) {
        Intent intent = new Intent(context, FeedbackActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
    }

    private void initRv() {
        rv.setLayoutManager(new GridLayoutManager(this, 3));

        datas = new ArrayList<>();
        datas.add(new Feedback(0, R.drawable.yjfk_iv));
        adapter = new FeedbackAdapter(datas);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_feedback_toolbar);
        initThisToolbarHaveBack(toolbar, this);
    }

    //提交
    @OnClick(R.id.a_feedback_btSubmit)
    public void submit() {
        if (!getInput()){
            initData();
        }
    }

    private void initData() {
        Api.getDefault().savefeedback(KBApplication.token,KBApplication.userid,content,picture)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        showDialog();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private boolean getInput(){
        content = et.getText().toString().trim();
        if (StringUtils.isEmpty(content)){
            ToastUtil.showInfo("请输入反馈信息！");
            return true;
        }

        StringBuilder sb = new StringBuilder();
        for (int i =0;i<datas.size();i++){
            if (datas.get(i).getUrl()!= null){
                if (i != 0){
                    sb.append(",");
                }
                sb.append(datas.get(i).getUrl());
            }
        }
        picture = sb.toString();
        return false;
    }

    public void showDialog() {
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setVisibility(R.id.message, View.GONE);
                        holder.setVisibility(R.id.cancel, View.GONE);
                        holder.setViewVisibility(R.id.cancel_divider, View.GONE);
                        holder.setText(R.id.title, "恭喜您提交成功！");
                        holder.setOnClickListener(R.id.ok, v -> {
                            dialog.dismiss();
                            finish();
                        });
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter.getItemCount() - 1 == position) {
            if (datas.size() == 1){
                pickPicture();
            }else {
                ToastUtil.showInfo("只能上传一张图片！");
            }
        }
    }

    private void pickPicture() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    Logger.d("修改头像");
                    if (aBoolean) {
                        Matisse.from(FeedbackActivity.this)
                                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                                .countable(true)
                                .capture(true)
                                .captureStrategy(
                                        new CaptureStrategy(true, "com.kb260.gxdk.fileprovider"))
                                .maxSelectable(1)
                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(
                                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                .imageEngine(new GlideEngine())
                                .forResult(Contact.REQUEST_CODE_CHOOSE);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Contact.REQUEST_CODE_CHOOSE:
                if (resultCode == RESULT_OK) {
                    String a = Matisse.obtainResult(data).get(0).toString();
                    if (a.contains("com.kb260.gxdk.fileprovider")) {
                        String b = a.replace("content://com.kb260.gxdk.fileprovider/my_images/", "");
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/" + b;
                        uploadImg(path);
                    } else {
                        String path = PathUtils.getPath(this, Matisse.obtainResult(data).get(0));
                        uploadImg(path);
                    }
                }
                break;
            default:
                break;
        }

    }

    public void uploadImg(String imgPath) {
        String imgName = "android_hxd_" + System.currentTimeMillis() + ".jpg";
        new UploadImgUtil(this, imgName, imgPath) {
            @Override
            public void _onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
                String imgId = "http://ykd2017.oss-cn-hangzhou.aliyuncs.com/" + putObjectRequest.getObjectKey();
                datas.set(0,new Feedback(1, imgId));
                adapter.setNewData(datas);
                Logger.d("_onSuccess" + imgId);
            }

            @Override
            public void _onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                Logger.d("_onFailure");
            }
        };
    }
}
