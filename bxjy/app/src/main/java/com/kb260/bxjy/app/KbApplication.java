package com.kb260.bxjy.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * @author KB260
 *         Created on  2018/1/31
 */

public class KbApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private static KbApplication application;
    public static String token = "";
    private static List<Activity> activityLinkedList;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        //初始化Activity队列
        activityManager();
        //初始化打印信息
        initLog();
    }

    private void initLog() {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    private void activityManager() {
        activityLinkedList = new LinkedList<>();
        registerActivityLifecycleCallbacks(this);
    }

    public static KbApplication getAppContext() {
        return application;
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
