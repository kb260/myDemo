package com.kb260.bxjy.ui.mine;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.kb260.bxjy.R;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.EventData;
import com.kb260.bxjy.model.entity.PersonInfoData;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.GlideUtils;
import com.kb260.bxjy.utils.ToastUtil;
import com.kb260.bxjy.weight.dialog.NiceDialog;
import com.kb260.bxjy.weight.dialog.ViewConvertListener;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author KB260
 *         Created on  2018/2/5
 */
public class PersonalIfmActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_personalIfm_tvNickname)
    TextView tvNickname;
    @BindView(R.id.a_personalIfm_tvGender)
    TextView tvGender;
    @BindView(R.id.a_personalIfm_civHeader)
    CircleImageView civHeader;

    RxPermissions rxPermissions;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, PersonalIfmActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_ifm;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        rxPermissions = new RxPermissions(this);
        getUserData();
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.mine_personalIfm_toolbar);
    }

    private void getUserData() {
        Api.getDefault().personInfo(KbApplication.token)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<PersonInfoData>(this) {
                    @Override
                    protected void success(PersonInfoData list) {
                        if (list!=null){
                            tvNickname.setText(list.getName());
                            GlideUtils.showImgHead(PersonalIfmActivity.this,list.getHeadImg(),
                                    civHeader,R.mipmap.ic_launcher_round);
                            tvGender.setText(list.getSex()==0?"男":"女");
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }



    //头像
    @OnClick(R.id.a_personalIfm_llHead)
    public void head() {
        bottom();
    }

    //昵称
    @OnClick(R.id.a_personalIfm_llNickname)
    public void nickname() {
        ChangeNicknameActivity.start(this);
    }

    //性别
    @OnClick(R.id.a_personalIfm_llGender)
    public void gender() {
        GenderActivity.start(this);
    }

    //笔试课程
    @OnClick(R.id.a_personalIfm_llNotebookClass)
    public void notebookClass() {
        NotebookClassActivity.start(this);
    }

    public void bottom() {
        NiceDialog.init()
                .setLayoutId(R.layout.bottom_change_header)
                .setConvertListener((ViewConvertListener) (holder, dialog) -> {
                    holder.setOnClickListener(R.id.bottom_changeHead_camera, view -> {
                        dialog.dismiss();
                        takeCamera();
                    });
                    holder.setOnClickListener(R.id.bottom_changeHead_album, view -> {
                        dialog.dismiss();
                        album();
                    });
                    holder.setOnClickListener(R.id.bottom_changeHead_cancel, view ->
                            dialog.dismiss()
                    );
                })
                .setOutCancel(false)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private void album() {
        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setType("image/*");
        startActivityForResult(openAlbumIntent, Action.CAMERA_2);//打开相册
    }

    private void takeCamera() {
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now
                        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//用来打开相机的Intent
                        if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {
                            //这句作用是如果没有相机则该应用不会闪退，要是不加这句则当系统没有相机应用的时候该应用会闪退
                            startActivityForResult(takePhotoIntent, Action.CAMERA_1);//启动相机
                        }

                    } else {
                        // Oups permission denied
                        ToastUtil.showShout("获取相机权限失败！");
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Action.START_ACTIVITY_CHANGE_NICKNAME:
                tvNickname.setText(data.getStringExtra(Action.START_ACTIVITY_CHANGE_NICKNAME_KEY));
                break;
            case Action.START_ACTIVITY_CHANGE_GENDER:
                tvGender.setText(data.getBooleanExtra(Action.START_ACTIVITY_CHANGE_GENDER_KEY,
                        true) ? "男" : "女");
                break;
            case RESULT_OK:
                switch (requestCode) {
                    case Action.CAMERA_1:
                        Bundle extras = data.getExtras();
                        Bitmap bitmap = (Bitmap) extras.get("data");
                        civHeader.setImageBitmap(bitmap);
                        break;
                    case Action.CAMERA_2:
                        Logger.d("onActivityResult: ImageUriFromAlbum: " + data.getData());
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            handleImageOnKitKat(data);//4.4之后图片解析
                        } else {
                            handleImageBeforeKitKat(data);//4.4之前图片解析
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }


    /**
     * 4.4版本以下对返回的图片Uri的处理：
     * 就是从返回的Intent中取出图片Uri，直接显示就好
     * @param data 调用系统相册之后返回的Uri
     */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    /**
     * 4.4版本以上对返回的图片Uri的处理：
     * 返回的Uri是经过封装的，要进行处理才能得到真实路径
     * @param data 调用系统相册之后返回的Uri
     */
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document类型的Uri，则提供document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的uri，则进行普通处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的uri，则直接获取路径
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    /**
     * 将imagePath指定的图片显示到ImageView上
     */
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            civHeader.setImageBitmap(bitmap);
        } else {
            ToastUtil.showShout("加载图片失败");
        }
    }

    /**
     * 将Uri转化为路径
     * @param uri 要转化的Uri
     * @param selection 4.4之后需要解析Uri，因此需要该参数
     * @return 转化之后的路径
     */
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;

    }
}
