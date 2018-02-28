package com.kb260.bxjy.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author KB260
 *         Created on  2018/2/7
 */

public class KeyBordUtil {

    /**
     * 显示键盘
     * @param view
     */
    public static void showKB(View view,Context context) {
        view.requestFocus();
        view.setFocusable(true);
        KeyBordUtil.showKeyBord(view,context);


    }


    /**
     * 显示键盘
     * @param view
     */
    public static void showKeyBord(View view,Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);

    }

    /**
     * 显示键盘
     */
    public static void hitKeyBord(View view,Activity context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            //隐藏软键盘 //
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }
}
