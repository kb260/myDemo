package com.kb260.gxdk.view.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.UploadDocumentsAdapter;
import com.kb260.gxdk.entity.UploadPic;
import com.kb260.gxdk.model.EventData;
import com.kb260.gxdk.model.Feedback;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.GifSizeFilter;
import com.kb260.gxdk.utils.PathUtils;
import com.kb260.gxdk.utils.SPUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.utils.UploadImgUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.me.FeedbackActivity;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 */
public class UploadDocumentsActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_home_uploadDocuments_rv)
    RecyclerView rv;
    @BindView(R.id.a_home_uploadDocuments_tvTop)
    TextView tvTop;

    UploadDocumentsAdapter adapter;
    int type,currentPosition;
    String url;
    List<UploadPic> data;

    public static void start(Context context,int type,String url){
        Intent intent = new Intent(context,UploadDocumentsActivity.class);
        intent.putExtra(Action.UPLOAD_TYPE,type);
        intent.putExtra(Action.URL,url);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_upload_documents;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
        initRv();
    }

    private void initIntent() {
        data = new ArrayList<>();
        Intent intent = getIntent();
        type = intent.getIntExtra(Action.UPLOAD_TYPE,-1);
        url = intent.getStringExtra(Action.URL);
        int topStr =R.string.a_home_uploadDocuments_toolbarTitle;
        switch (type){
            //身份证
            case Action.UPLOAD_TYPE_SFZ:
                topStr = R.string.a_home_uploadDocuments_sfz;
                if (!StringUtils.isEmpty(url)){
                    String[] a =url.split(",");
                    data.add(new UploadPic(type, a[0]));
                    data.add(new UploadPic(type, a[1]));
                }else {
                    data.add(new UploadPic(type,null));
                    data.add(new UploadPic(type,null));
                }

                break;
            //房产证
            case Action.UPLOAD_TYPE_FCZ:
                topStr = R.string.a_home_uploadDocuments_fcz;
                if (!StringUtils.isEmpty(url)){
                    data.add(new UploadPic(type,url));
                }else {
                    data.add(new UploadPic(type,null));
                }
                break;
            //户口本
            case Action.UPLOAD_TYPE_HKB:
                topStr = R.string.a_home_uploadDocuments_hkb;
                if (!StringUtils.isEmpty(url)){
                    data.add(new UploadPic(type,url));
                }else {
                    data.add(new UploadPic(type,null));
                }
                break;
            //结婚证
            case Action.UPLOAD_TYPE_JHZ:
                topStr = R.string.a_home_uploadDocuments_jhz;
                if (!StringUtils.isEmpty(url)){
                    data.add(new UploadPic(type,url));
                }else {
                    data.add(new UploadPic(type,null));
                }
                break;
            //信息补充
            case Action.UPLOAD_TYPE_XXBC:
                topStr = R.string.a_home_uploadDocuments_xxbc;
                if (!StringUtils.isEmpty(url)){
                    String[] a =url.split(",");
                    for (int i =0;i<a.length;i++){
                        data.add(new UploadPic(type, a[i]));
                    }
                }else {
                    data.add(new UploadPic(type,null));
                }
                break;
            //营业执照
            case Action.UPLOAD_TYPE_YYZZ:
                topStr = R.string.a_home_uploadDocuments_yyzz;
                if (!StringUtils.isEmpty(url)){
                    data.add(new UploadPic(type,url));
                }else {
                    data.add(new UploadPic(type,null));
                }
                break;
            //章程
            case Action.UPLOAD_TYPE_ZC:
                topStr = R.string.a_home_uploadDocuments_zc;
                if (!StringUtils.isEmpty(url)){
                    String[] a =url.split(",");
                    for (int i =0;i<a.length;i++){
                        data.add(new UploadPic(type, a[i]));
                    }
                }else {
                    data.add(new UploadPic(type,null));
                }
                break;
            //征信
            case Action.UPLOAD_TYPE_ZX:
                topStr = R.string.a_home_uploadDocuments_zx;
                if (!StringUtils.isEmpty(url)){
                    String[] a =url.split(",");
                    for (int i =0;i<a.length;i++){
                        data.add(new UploadPic(type, a[i]));
                    }
                }else {
                    data.add(new UploadPic(type,null));
                }
                break;
            //车辆登记证
            case Action.UPLOAD_TYPE_CLDJZ:
                topStr = R.string.a_home_uploadDocuments_cldjz;
                if (!StringUtils.isEmpty(url)){
                    data.add(new UploadPic(type,url));
                }else {
                    data.add(new UploadPic(type,null));
                }
                break;
            //购车发票
            case Action.UPLOAD_TYPE_GCFP:
                topStr = R.string.a_home_uploadDocuments_gcfp;
                if (!StringUtils.isEmpty(url)){
                    data.add(new UploadPic(type,url));
                }else {
                    data.add(new UploadPic(type,null));
                }
                break;
            //行驶证
            case Action.UPLOAD_TYPE_XSZ:
                topStr = R.string.a_home_uploadDocuments_xsz;
                if (!StringUtils.isEmpty(url)){
                    data.add(new UploadPic(type,url));
                }else {
                    data.add(new UploadPic(type,null));
                }
                break;
            //驾驶证
            case Action.UPLOAD_TYPE_JSZ:
                topStr = R.string.a_home_uploadDocuments_jsz;
                if (!StringUtils.isEmpty(url)){
                    data.add(new UploadPic(type,url));
                }else {
                    data.add(new UploadPic(type,null));
                }
                break;
            default:
                break;
        }
        tvTop.setText(topStr);
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UploadDocumentsAdapter(data);
        rv.setAdapter(adapter);

        if (type == Action.UPLOAD_TYPE_ZC || type == Action.UPLOAD_TYPE_ZX || type == Action.UPLOAD_TYPE_XXBC){
            addFooterView();
        }
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_home_uploadDocuments_toolbarTitle);
        initThisToolbarHaveBack(toolbar,this);

    }

    //确认提交
    @OnClick(R.id.a_home_uploadDocuments_btSubmit)
    public void submit(){
        StringBuffer stringBuilder = new StringBuffer();
        for (int i =0;i<data.size();i++){
            if (data.get(i).getUrl()==null){
                ToastUtil.showWarn("请上传证件！");
                return;
            }else {
                if (i != 0){
                    stringBuilder.append(",");
                }
                stringBuilder.append(data.get(i).getUrl());
            }
        }
        String a = stringBuilder.toString();

        //saveHouse(a);

        EventData eventData = new EventData(type,a);
        EventBus.getDefault().post(eventData);
        finish();
    }

    private void addFooterView(){
        View view = getLayoutInflater().inflate(R.layout.add_view, (ViewGroup) rv.getParent(), false);
        adapter.addFooterView(view);
        ImageView ivAdd = view.findViewById(R.id.footerView_add);
        ivAdd.setOnClickListener(view1 -> {
            data.add(new UploadPic(type,null));
            adapter.setNewData(data);
        });

    }

    private void pickPicture() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    Logger.d("修改头像");
                    if (aBoolean) {
                        Matisse.from(UploadDocumentsActivity.this)
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
        String imgName = "android_gxdk_" + System.currentTimeMillis() + ".jpg";
        new UploadImgUtil(this, imgName, imgPath) {
            @Override
            public void _onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
                String imgId = "http://ykd2017.oss-cn-hangzhou.aliyuncs.com/" + putObjectRequest.getObjectKey();
                data.set(currentPosition,new UploadPic(type, imgId));
                adapter.setNewData(data);
                Logger.d("_onSuccess" + imgId);
            }

            @Override
            public void _onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                Logger.d("_onFailure");
            }
        };
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        currentPosition = position;
        pickPicture();
    }

    private void saveHouse(String a){
        String ac="";
        switch (type) {
            //身份证
            case Action.UPLOAD_TYPE_SFZ:
                ac = Action.SP_SFZ;
                break;
            //房产证
            case Action.UPLOAD_TYPE_FCZ:
                ac = Action.SP_FCZ;
                break;
            //户口本
            case Action.UPLOAD_TYPE_HKB:
                ac = Action.SP_HKB;
                break;
            //结婚证
            case Action.UPLOAD_TYPE_JHZ:
                ac = Action.SP_JHZ;
                break;
            //信息补充
            case Action.UPLOAD_TYPE_XXBC:
                ac = Action.SP_XXBC;
                break;
            //营业执照
            case Action.UPLOAD_TYPE_YYZZ:
                ac = Action.SP_GSYYZZ;
                break;
            //章程
            case Action.UPLOAD_TYPE_ZC:
                ac = Action.SP_ZC;
                break;
            //征信
            case Action.UPLOAD_TYPE_ZX:
                ac = Action.SP_ZX;
                break;
            //车辆登记证
            case Action.UPLOAD_TYPE_CLDJZ:
                ac = Action.SP_CLDJZ;
                break;
            //购车发票
            case Action.UPLOAD_TYPE_GCFP:
                ac = Action.SP_GCFP;
                break;
            //行驶证
            case Action.UPLOAD_TYPE_XSZ:
                ac = Action.SP_XSZ;
                break;
            //驾驶证
            case Action.UPLOAD_TYPE_JSZ:
                ac = Action.SP_JSZ;
                break;
            default:
                break;
        }
        SPUtils.getInstance().put(ac,a);
    }
}
