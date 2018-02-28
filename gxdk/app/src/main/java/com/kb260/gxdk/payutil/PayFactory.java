package com.kb260.gxdk.payutil;

import android.content.Context;
import com.google.gson.Gson;

/**
 * @author 沈小建 on 2016/12/5 0005.
 */

public class PayFactory {

    public static final int BALANCE = 1;
    public static final int ALPAY = 2;
    public static final int WXPAY = 3;

    public static IPayBean createPay(int type, Context context, String content) {
        switch (type) {
            case BALANCE:
                return new BalancePay();
            case ALPAY:
                return new ALPay(context, content);
            case WXPAY:
                WxPayBean wxPayBean =new Gson().fromJson(content, WxPayBean.class);
                //WxPayBean wxPayBean = Gs.parseObject(content, WxPayBean.class);
                return new WXPay(context, wxPayBean);
            default:
                break;
        }
        return null;
    }
}
