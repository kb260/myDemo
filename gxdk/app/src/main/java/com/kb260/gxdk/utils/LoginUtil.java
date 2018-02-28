package com.kb260.gxdk.utils;

import android.content.Context;
import com.kb260.gxdk.app.KBApplication;

/**
 * @author  KB260
 * @date  2017/10/20
 */

public class LoginUtil {
    public static void exitLogin(Context context){
        SPUtils.getInstance().clear();
        SPUtils.getInstance().put(Action.SP_IS_FIRST,false);
        KBApplication.userid = 0;
        KBApplication.setLoginType("");
        KBApplication.token = null;
        KBApplication.currentPhone = null;
        KBApplication.exitAppList();
    }
}
