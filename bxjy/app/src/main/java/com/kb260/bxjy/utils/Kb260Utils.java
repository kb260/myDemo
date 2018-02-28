package com.kb260.bxjy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author KB260
 * @date 2017/10/19
 * @email work260@outlook.com
 */

public class Kb260Utils {

    /**
     * 去掉数字前面的0
     * @param str
     * @return
     */
    public static String removeFrontZero(String str) {
        String s = str.replaceFirst("^0*", "");
        if (StringUtils.isEmpty(s)){
            return "0";
        }else {
            return s;
        }
    }

    public static String getTime(long date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(new Date(date));
    }
    public static String getTimeHM(long date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(date));
    }

    public static String getTimeYM(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    public static String getTimeY(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    public static String getTimeM(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return format.format(date);
    }
}
