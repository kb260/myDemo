package com.kb260.gxdk.api;

import com.kb260.gxdk.utils.StringUtils;
import java.io.Serializable;

/**
 * @author  KB260
 * @date  2017/10/19
 */

public class BaseModel<T> implements Serializable {
    public String status;
    public String message;
    public long timestamp;
    public T params;

    /**
     * 修改成自己对应的状态码 服务器返回状态码
     */
    public static String SUCCEED_CODE = "900000"; //成功状态码
    public static String OFF_SITE = "999991"; //异地登录
    public static String PHONE_UNREGISTER = "900003"; //手机号未注册
    public static String SUCCEED_ALIPAY = "1"; //支付包成功状态码
    public static String WAIT_REGISTER_CODE = "911111"; //预注册状态码
    public static String TOKEN_VERIFICATION_FAILED = "999997"; //token失效状态码
    public static String TOKEN_PAST_DUE = "999998"; //token失效状态码
    public static String THIRD_NOT_BIND = "900333"; //第三方绑定
    public static String PHONE_NOT_REGISTER = "0"; //第三方绑定
    public static String TOKEN_INVALID = "999900"; //第三方绑定
    public static String NO_MONEY = "900601"; //余额不足
    public static String NOT_SET_PAY_PASSWORD = "900300"; //未设置支付密码
    public static String NEED_BIND_QQ = "977777"; //请先绑定QQ
    public static String NEED_UNBIND_QQ = "977776"; //请先解绑qq
    public static String NEED_BIND_WX = "988888"; //请先绑定微信
    public static String NEED_UNBIND_WX = "988886"; //请先解绑微信


    /**
     * 请求成功
     * @return
     */
    public boolean isNEED_BIND_QQ(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? NEED_BIND_QQ : status, NEED_BIND_QQ);
    }
    /**
     * 请求成功
     * @return
     */
    public boolean isNEED_UNBIND_QQ(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? NEED_UNBIND_QQ : status, NEED_UNBIND_QQ);
    }
    /**
     * 请求成功
     * @return
     */
    public boolean isNEED_BIND_WX(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? NEED_BIND_WX : status, NEED_BIND_WX);
    }
    /**
     * 请求成功
     * @return
     */
    public boolean isNEED_UNBIND_WX(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? NEED_UNBIND_WX : status, NEED_UNBIND_WX);
    }

    /**
     * 请求成功
     * @return
     */
    public boolean isSucceed(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? SUCCEED_CODE : status, SUCCEED_CODE);
    }

    /**
     * 请求成功
     * @return
     */
    public boolean isPhoneNotRegister(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? PHONE_NOT_REGISTER : status, PHONE_NOT_REGISTER);
    }

    /**
     * 请求成功
     * @return
     */
    public boolean isNoMoney(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? NO_MONEY : status, NO_MONEY);
    }

    /**
     * 请求成功
     * @return
     */
    public boolean isAlipay(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? SUCCEED_ALIPAY : status, SUCCEED_ALIPAY);
    }

    /**
     * 请求成功
     * @return
     */
    public boolean isNoSetPayPassword(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? NOT_SET_PAY_PASSWORD : status, NOT_SET_PAY_PASSWORD);
    }


    /**
     * 预注册
     */
    public boolean isWaitRegister(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? WAIT_REGISTER_CODE : status, WAIT_REGISTER_CODE);
    }

    /**
     * 预注册
     */
    public boolean userIsNoExist(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? PHONE_UNREGISTER : status, PHONE_UNREGISTER);
    }

    /**
     * 异地登录
     */
    public boolean offite(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? OFF_SITE : status, OFF_SITE);
    }

    /**
     * token 验证失效
     * @return
     */
    public boolean isTokenVerificationFailed() {
        if (StringUtils.isEmpty(status)) {
            return false;
        }
        if (StringUtils.equals(status, TOKEN_PAST_DUE) ||
                StringUtils.equals(status, TOKEN_VERIFICATION_FAILED)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", params=" + params +
                '}';
    }
}
