package com.panda.sharebike.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by Administrator on 2016/7/15.
 */

public class SharedPreferencesUtils {

    private Context context;

    public SharedPreferencesUtils(Context context) {
// TODO Auto-generated constructor stub
        this.context = context;
    }

    public boolean saveSharePreference(String filename, Map<String, Object> map) {
        boolean flag = false;
        SharedPreferences preferences = context.getSharedPreferences(filename,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object object = entry.getValue();
            if (object instanceof Boolean) {
                boolean b = (boolean) object;
                editor.putBoolean(key, b);
            }
            if (object instanceof String) {
                String s = (String) object;
                editor.putString(key, s);
            }
            if (object instanceof Integer) {
                Integer i = (Integer) object;
                editor.putInt(key, i);
            }
            if (object instanceof Float) {
                Float f = (Float) object;
                editor.putFloat(key, f);
            }
            if (object instanceof Long) {
                Long l = (Long) object;
                editor.putLong(key, l);
            }
        }
        editor.commit();
        return flag;
    }
}

