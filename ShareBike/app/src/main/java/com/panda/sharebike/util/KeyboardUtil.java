package com.panda.sharebike.util;

import android.app.Activity;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * author : zhangyuan
 * e-mail : zhangyuan_min@163.com
 * time   : 2017/06/21
 * desc   :
 * version: 1.0
 */
public final class KeyboardUtil {
    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
}
