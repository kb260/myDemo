package com.kb260.kbutils.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.kb260.kbutils.app.KbApplication;

/**
 * @author  KB260
 * Created on  2018/1/8
 */

public class AppUtils {


    /**
     * 获取App版本号
     *
     * @return App版本号
     */
    public static String getAppVersionName() {
        return getAppVersionName(KbApplication.getAppContext().getPackageName());
    }


    /**
     * 获取App版本号
     *
     * @param packageName 包名
     * @return App版本号
     */
    private static String getAppVersionName(final String packageName) {
        if (isSpace(packageName)){
            return null;
        }
        try {
            PackageManager pm = KbApplication.getAppContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static boolean isSpace(final String s) {
        if (s == null) {
            return true;
        }
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
