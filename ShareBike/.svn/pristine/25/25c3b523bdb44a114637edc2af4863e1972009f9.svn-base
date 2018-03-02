package com.panda.sharebike.util;

import static com.blankj.utilcode.util.RegexUtils.isMatch;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/07/18
 * desc   :
 * version: 1.0
 */
public class RegexUtil {
    /**
     * 最少7位数字+字母
     */
    public static String regex1 = "^[a-zA-Z0-9]{7,}$";
    public static String regex2 = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{7,}$";
    private static String phone;
    public static boolean isStringNumTen(CharSequence input) {
        return isMatch(regex2, input);
    }

    public static String phoneHide(String phone) {
        phone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1* * * *$2");
        return phone;
    }
}
