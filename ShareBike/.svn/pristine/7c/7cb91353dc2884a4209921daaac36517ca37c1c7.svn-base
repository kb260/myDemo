package com.panda.sharebike.util;

import com.panda.sharebike.Config;

import org.apache.http.NameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/08/24
 * desc   :
 * version: 1.0
 */
public class WXPayParamsUtil {
    StringBuffer sb;

    private void initData() {
        sb = new StringBuffer();
    }

    /**
     * 生成签名
     */
    private String genPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Config.WX_APPID);
        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        try {
            return new String(packageSign.getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//		Log.e("orion",packageSign);
        return null;
    }

    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Config.WX_APPID);
//		Log.e("TAG_SB",sb.toString());
        this.sb.append("sign str\n" + sb.toString() + "\n\n");
        String appSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
//		Log.e("orion",appSign);
        return appSign;
    }

    //获取随机字符串
    private String genNonceStr() {
        Random random = new Random();
        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }
    
    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<" + params.get(i).getName() + ">");


            sb.append(params.get(i).getValue());
            sb.append("</" + params.get(i).getName() + ">");
        }
        sb.append("</xml>");

//		Log.e("orion",sb.toString());
        return sb.toString();
    }
}
