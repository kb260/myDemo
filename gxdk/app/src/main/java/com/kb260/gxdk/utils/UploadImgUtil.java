package com.kb260.gxdk.utils;

import android.app.Activity;
import android.content.Context;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.utils.BinaryUtil;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.view.shop.LoadingDialog;
import com.orhanobut.logger.Logger;
import java.io.File;
import java.io.IOException;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


/**
 * Created by 郑谊庄 on 2017/6/29
 */

public abstract class UploadImgUtil {

    //private static String BUCKET_NAME = "wantloan";

    private LoadingDialog dialog;
    private Context mContext;

    protected UploadImgUtil(Context mContext, String key, String path) {
        this.mContext = mContext;
        uploadImg(key, path);
    }

    private void uploadImg(String key, String path) {
        initDialog();
        Luban.with(mContext)
                .load(new File(path))
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Logger.d("Luban,onStart");
                    }

                    @Override
                    public void onSuccess(File file) {
                        doUploadPic(key, file.getPath());
                    }

                    @Override
                    public void onError(Throwable e) {
                        hintDialog();
                        ToastUtil.showError("上传图片失败");
                    }
                }).launch();
    }

    /**
     * 上传文件至oss
     */
    private void doUploadPic(String key, String path) {
        Logger.d(path);
        PutObjectRequest put = new PutObjectRequest("ykd2017", key, path);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("application/octet-stream");
        try {
            // 设置Md5以便校验
            metadata.setContentMD5(BinaryUtil.calculateBase64Md5(path)); // 如果是从文件上传
            // metadata.setContentMD5(BinaryUtil.calculateBase64Md5(byte[])); // 如果是上传二进制数据
        } catch (IOException e) {
            e.printStackTrace();
        }
        put.setMetadata(metadata);
        KBApplication.getAppContext().oss
                .asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                    @Override
                    public void onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
                        ((Activity) mContext).runOnUiThread(() -> {
                            hintDialog();
                            //ToastUtils.showShort(mContext.getString(R.string.approve_upload_succeed));
                            _onSuccess(putObjectRequest, putObjectResult);
                        });
                    }

                    @Override
                    public void onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                        ((Activity) mContext).runOnUiThread(() -> hintDialog());
                        //ToastUtils.showShort(mContext.getString(R.string.approve_upload_failed));
                        _onFailure(putObjectRequest, e, e1);
                    }
                });
    }

    private void initDialog(){
        if (dialog == null){
            dialog = new LoadingDialog(mContext);
        }
        if (!dialog.isShowing()){
            dialog.show();
        }
    }

    private void hintDialog(){
        if (null != dialog && dialog.isShowing()) {
            dialog.cancel();
        }
    }

    public abstract void _onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult);

    public abstract void _onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1);

}
