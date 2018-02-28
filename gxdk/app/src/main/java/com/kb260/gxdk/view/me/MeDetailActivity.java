package com.kb260.gxdk.view.me;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.UploadPic;
import com.kb260.gxdk.model.EventData;
import com.kb260.gxdk.model.Me;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.GifSizeFilter;
import com.kb260.gxdk.utils.ImageLoader;
import com.kb260.gxdk.utils.LoginUtil;
import com.kb260.gxdk.utils.PathUtils;
import com.kb260.gxdk.utils.SPUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.utils.UploadImgUtil;
import com.kb260.gxdk.view.account.LoginActivity;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.base.WebViewActivity;
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

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author KB260
 *         Created on  2017/11/2
 */
public class MeDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_me_meDetail_ivIcon)
    CircleImageView icon;
    @BindView(R.id.a_me_meDetail_tvNickname)
    TextView tvNickname;
    @BindView(R.id.a_me_meDetail_tvPhone)
    TextView tvPhone;

    public static void start(Context context) {
        Intent intent = new Intent(context, MeDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_me_detail;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initData();
        Logger.d("initView");
    }


    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_meDetail_toolbar);
        initThisToolbarHaveBack(toolbar, this);
    }

    private void initData() {
        Api.getDefault().selperon(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<Me>(this) {
                    @Override
                    protected void success(Me list) {
                        if (list.getPicture()!=null){
                            ImageLoader.showImage(icon,(String) list.getPicture());
                        }

                        String phone = list.getTelphone();
                        if (phone != null && phone.length() == 11){
                            String a= phone.substring(0,3)+"****"+phone.substring(7);
                            tvPhone.setText(a);
                        }else {
                            tvPhone.setText(phone);
                        }

                        if (StringUtils.isEmpty(list.getNick())){
                            if (phone != null && phone.length() == 11){
                                String a= phone.substring(0,3)+"****"+phone.substring(7);
                                tvNickname.setText(a);
                            }
                        }else {
                            tvNickname.setText(list.getNick());
                        }

                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    //修改头像
    @OnClick(R.id.a_me_meDetail_icon)
    public void icon() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    Logger.d("修改头像");
                    if (aBoolean) {
                        Matisse.from(MeDetailActivity.this)
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
            case Contact.CHANGE_NICKNAME:
                if (resultCode == Contact.CHANGE_NICKNAME) {
                    String nickname = data.getStringExtra(Contact.NICKNAME);
                    tvNickname.setText(nickname);

                    EventData eventData = new EventData(Action.EVENT_TYPE_CHANGE_ICON);
                    EventBus.getDefault().post(eventData);
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
                save(imgId);
                Logger.d("_onSuccess" + imgId);
            }

            @Override
            public void _onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                Logger.d("_onFailure");
            }
        };
    }

    public void save(String imgId) {
        Api.getDefault().upperon(KBApplication.token,KBApplication.userid,null,imgId,null)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ImageLoader.showImage(icon,imgId);

                        EventData eventData = new EventData(Action.EVENT_TYPE_CHANGE_ICON);
                        EventBus.getDefault().post(eventData);
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    //修改姓名
    @OnClick(R.id.a_me_meDetail_nickname)
    public void nickname() {
        ChangeNicknameActivity.start(this,tvNickname.getText().toString());
    }

    //修改号码
    @OnClick(R.id.a_me_meDetail_phone)
    public void phone() {
        ChangePhoneFirstActivity.start(this);
    }

    //常用地址
    @OnClick(R.id.a_me_meDetail_commonAddress)
    public void commonAddress() {
        //SelectCommonAddressActivity.start(this);
        myAddress();
    }

    //修改银行卡
    @OnClick(R.id.a_me_meDetail_card)
    public void card() {
        CardActivity.start(this);
    }

    //支付密码
    @OnClick(R.id.a_me_meDetail_payPassword)
    public void payPassword() {
        PayPasswordFirstActivity.start(this);
    }

    //密码管理
    @OnClick(R.id.a_me_meDetail_passwordSet)
    public void passwordSet() {
        PasswordSetActivity.start(this);
    }

    //退出登录
    @OnClick(R.id.a_me_meDetail_btExit)
    public void exit() {
        showDialog();
    }

    public void showDialog() {
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setText(R.id.title, "确定退出此账号?");
                        holder.setVisibility(R.id.message, View.GONE);
                        holder.setOnClickListener(R.id.ok, v -> {
                            dialog.dismiss();
                            LoginUtil.exitLogin(MeDetailActivity.this);
                            LoginActivity.start(MeDetailActivity.this);
                        });
                        holder.setOnClickListener(R.id.cancel, v ->
                            dialog.dismiss()
                        );
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    private void myAddress(){
        Api.getDefault().selmyaddress(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        WebViewActivity.start(MeDetailActivity.this,list,"常用地址");
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }
}
