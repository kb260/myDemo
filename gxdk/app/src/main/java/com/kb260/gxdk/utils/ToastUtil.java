package com.kb260.gxdk.utils;

import android.widget.Toast;

import com.kb260.gxdk.app.KBApplication;
import es.dmoral.toasty.Toasty;

/**
 * @author  KB260
 * Created on  2017/11/1
 */
public class ToastUtil {
    public static void showSuccess(CharSequence message) {
        Toasty.success(KBApplication.getAppContext(), message, Toast.LENGTH_SHORT, true).show();
    }
    public static void showError(CharSequence message) {
        Toasty.error(KBApplication.getAppContext(), message, Toast.LENGTH_SHORT, true).show();
    }
    public static void showInfo(CharSequence message) {
        Toasty.info(KBApplication.getAppContext(), message, Toast.LENGTH_SHORT, true).show();
    }
    public static void showInfoLong(CharSequence message) {
        Toasty.info(KBApplication.getAppContext(), message, Toast.LENGTH_LONG, true).show();
    }
    public static void showWarn(CharSequence message) {
        Toasty.warning(KBApplication.getAppContext(), message, Toast.LENGTH_SHORT, true).show();
    }
    public static void showNormal(CharSequence message) {
        Toasty.normal(KBApplication.getAppContext(), message).show();
    }

    public static void showLongInfo(CharSequence message) {
        Toasty.info(KBApplication.getAppContext(), message, Toast.LENGTH_LONG, true).show();
    }
}

