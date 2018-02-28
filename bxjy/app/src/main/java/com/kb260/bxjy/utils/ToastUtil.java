package com.kb260.bxjy.utils;

import android.widget.Toast;

import com.kb260.bxjy.app.KbApplication;

/**
 * @author KB260
 *         Created on  2018/2/5
 */

public class ToastUtil {

    public static void showShout(String text){
        Toast.makeText(KbApplication.getAppContext(),text,Toast.LENGTH_SHORT).show();
    }
    public static void showLong(String text){
        Toast.makeText(KbApplication.getAppContext(),text,Toast.LENGTH_LONG).show();
    }

}
