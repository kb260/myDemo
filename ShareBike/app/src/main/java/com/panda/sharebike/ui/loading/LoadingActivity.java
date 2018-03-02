package com.panda.sharebike.ui.loading;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.N;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.model.entity.GuideBean;
import com.panda.sharebike.model.entity.TouristsBean;
import com.panda.sharebike.model.entity.VersionBean;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.MainActivity;
import com.panda.sharebike.ui.guidepage.GuideActivity;
import com.panda.sharebike.ui.login.LoginByPhoneActivity;
import com.panda.sharebike.ui.widget.dialog.BaseNiceDialog;
import com.panda.sharebike.ui.widget.dialog.NiceDialog;
import com.panda.sharebike.ui.widget.dialog.ViewConvertListener;
import com.panda.sharebike.ui.widget.dialog.ViewHolder;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoadingActivity extends AppCompatActivity {

    // private SSocketService myService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_loading);
        SPUtils.getInstance("dialog").put("first_dialog", true);
        if (NetworkUtils.isConnected()){
            getVersion();
        }else {
            //第二次进入跳转
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }, 2000);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * @param mContext 在应用初始，启动Activity里获取本地保存的登录配置和用户信息
     */
    public static void init(Context mContext) {
        Config.USER_ID = SPUtils.getInstance("USER_INFO").getString("USER_ID");
        if (EmptyUtils.isNotEmpty(Config.USER_ID)) {
            // getUserInfo();
        }
    }

    @SuppressLint("ApplySharedPref")
    private void initDate() {
        SharedPreferences sp = getSharedPreferences("is", MODE_PRIVATE);
        boolean first = sp.getBoolean("first", true);
        SharedPreferences.Editor editor = sp.edit();
        if (first) {
            //第一次进入跳转
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LoadingActivity.this, GuideActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }, 2000);
            editor.putBoolean("first", false);//debug模式下传true进入引导页，上线前记得修改
            editor.commit();
        } else {
            //第二次进入跳转
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }, 2000);
        }
    }

    /**
     * 游客接口实现
     */
    private void getToken(String token) {
        LogUtils.e(token + "<------------");
        String url;
        if (TextUtils.isEmpty(token)) {
            url = N.KEY_GUESTLOGIN;
        } else {
            url = N.KEY_GUESTLOGIN + "?token=" + token;
        }

        Api.getInstance().getDefault().getToken(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<TouristsBean>>(new SubscriberListener<HttpResult<TouristsBean>>() {

                    @Override
                    public void onSuccess(HttpResult<TouristsBean> model) {
                        Logger.e(model.getData().getSession().getToken() + "-----------------");

                        SharedPreferences sp = getSharedPreferences(N.SHARE_TOKEN, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(N.SHARE_TOKEN_KEY, model.getData().getSession().getToken());
                        editor.commit();//提交token，全局需要，这里是

//                        if (model.getData().getSession().getToken().equals(SPUtils.getInstance(N.SHARE_TOKEN).getString(N.SHARE_TOKEN_KEY))) {
//                            //token一样不用登录
//
//                        } else {
//                            Intent intent = new Intent(LoadingActivity.this, LoginByPhoneActivity.class);
//                            startActivity(intent);
//                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }, this, false));

    }

    /**
     * 判断状态,登录态,用于维修按钮
     */
    private void getVersion() {
        Api.getInstance().getDefault().getVersion(Config.TOKEN, "android")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<VersionBean>>(new SubscriberListener<HttpResult<VersionBean>>() {

                    @Override
                    public void onSuccess(HttpResult<VersionBean> model) {
                        if (model.isOk()) {
                            Double minVersion = Double.valueOf(model.getData().getMinVersion());
                            Double maxVersion = Double.valueOf(model.getData().getMaxVersion());
                            String url = model.getData().getDownLoadUrl();
                            Double currentVersion = Double.valueOf(getCurrentVersion());
                            /*minVersion =0.8;
                            maxVersion = 1.0;
                            Double currentVersion = 0.7;*/
                            if (currentVersion<minVersion){
                                bt4(url,model.getData().getMaxVersion());
                            }else if (currentVersion<maxVersion){
                                bt3(url,model.getData().getMaxVersion());
                            }else {
                                getToken(Config.TOKEN);
                                initDate();
                            }


                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                    }
                }, this, false));

    }

    public void gotoDownload(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        startActivity(intent);
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getCurrentVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            return  info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    public void bt4(final String url, final String version){
        NiceDialog.init()
                .setLayoutId(R.layout.version1)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        holder.setText(R.id.version_update,"更新版本v"+version);
                        holder.setOnClickListener(R.id.version_update, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                gotoDownload(url);
                                finish();
                            }
                        });
                    }
                })
                .setHeight(362)
                .setWidth(300)
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }

    public void bt3(final String url, final String version){
        NiceDialog.init()
                .setLayoutId(R.layout.version2)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        holder.setText(R.id.version2_update,"更新版本v"+version);
                        holder.setOnClickListener(R.id.version2_update, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                gotoDownload(url);
                                finish();
                            }
                        });
                        holder.setOnClickListener(R.id.version2_noupdate, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                getToken(Config.TOKEN);
                                initDate();
                            }
                        });
                    }
                })
                .setHeight(362)
                .setWidth(300)
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }

}