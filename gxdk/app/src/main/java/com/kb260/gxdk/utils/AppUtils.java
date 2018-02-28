package com.kb260.gxdk.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.kb260.gxdk.app.KBApplication;

/**
 * @author KB260
 * @date 2017/10/26
 * @email work260@outlook.com
 */

public class AppUtils {


    /**
     * 获取App版本号
     *
     * @return App版本号
     */
    public static String getAppVersionName() {
        return getAppVersionName(KBApplication.getAppContext().getPackageName());
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
            PackageManager pm = KBApplication.getAppContext().getPackageManager();
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
