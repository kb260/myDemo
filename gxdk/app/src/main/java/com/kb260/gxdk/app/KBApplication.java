package com.kb260.gxdk.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import java.util.LinkedList;
import java.util.List;
import cn.jpush.android.api.JPushInterface;

/**
 * @author  KB260
 * Created on  2017/10/31
 */
public class KBApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private static KBApplication kbApplication;
    public static int userid;
    private static String loginType;
    public static String currentPhone;
    public static String registrationId;
    public static String token;
    private static List<Activity> activityLinkedList;
    public OSS oss;

    @Override
    public void onCreate() {
        super.onCreate();
        kbApplication = this;
        registrationId = JPushInterface.getRegistrationID(this);
        initJG();
        initUM();
        initOSSClient();
        initLog();
        activityManager();
    }

    public static String getLoginType(){
        if (loginType != null){
            return loginType;
        }
        return "";
    }

    public static void setLoginType(String loginType){
        KBApplication.loginType = loginType;
    }

    /**
     * 初始化友盟
     */
    private void initUM() {
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx582cc8a7eea05e4f","2344f9c0bfc23697470b97d2073418c7");
        //PlatformConfig.setWeixin("wxe8bc9a4ab9a900e1","f06275aa48f0734e4ca2fc922affb7c4");
        PlatformConfig.setQQZone("1105534527", "fOaaf7vcWAw9s91P");
    }

    private void initLog() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    //初始化推送
    private void initJG() {
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
    }

    /**
     * 初始化OSS阿里云图片上传
     */
    private void initOSSClient() {
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        OSSCredentialProvider credentialProvider = new
                OSSPlainTextAKSKCredentialProvider("LTAIsjHbvciLYEr5", "KRc3Zyqx0eSNdtmqo2wYJEjTf8Mklk");
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(30 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(30 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider,conf);
    }

    private void activityManager() {
        activityLinkedList = new LinkedList<>();
        registerActivityLifecycleCallbacks(this);
    }

    public static KBApplication getAppContext() {
        return kbApplication;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        activityLinkedList.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        MyActivityManager.getInstance().setCurrentActivity(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityLinkedList.remove(activity);
    }

    public static void showList() {
        Logger.d( "showList: ---------------------------");
        for (Activity activity : activityLinkedList) {
            Logger.d( "showList: " + activity.getLocalClassName());
        }
        Logger.d( "showList: ---------------------------");
    }

    public static void exitAppList() {
        for (Activity activity : activityLinkedList) {
            activity.finish();
        }
    }
}
