package com.kb260.bxjy.api;

import com.kb260.bxjy.utils.StringUtils;
import java.io.Serializable;

/**
 * @author  KB260
 * Created on  2018/2/25
 */

public class BaseModel<T> implements Serializable {
    String status;
    public String message;
    public T data;
    public long timestamp;

    /**
     * 修改成自己对应的状态码 服务器返回状态码
     */
    static final String SUCCEED_CODE = "900000";
    static final String TOKE_TIMEOUT_CODE = "999998";
    static final String TOKE_ERROR_CODE = "999999";

    private static String TOKEN_VERIFICATION_FAILED = "999997"; //token失效状态码
    private static String TOKEN_PAST_DUE = "999998"; //token失效状态码




    /**
     * 请求成功
     * @return d
     */
    boolean isSucceed(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "900000");
    }

    /**
     * token过期
     * @return d
     */
    boolean tokenTimeout(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "999998");
    }

    /**
     * token无效
     * @return d
     */
    boolean tokenError(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "999999");
    }

  /*  *//**
     * token无效
     * @return d
     *//*
    boolean tokenError(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400001");
    }*/

    /**
     * 验证码发送失败！
     * @return d
     */
    boolean codeFail(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400002");
    }

    /**
     * 短信发送频繁,请稍后...
     * @return d
     */
    boolean codeTooMach(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400003");
    }

    /**
     * 	验证码已失效
     * @return d
     */
    boolean codeInvalid(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400004");
    }

    /**
     * 	验证码不正确
     * @return d
     */
    boolean codeIncorrect(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400005");
    }

    /**
     * 密码不一致
     * @return d
     */
    boolean pwInconsistent(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400006");
    }

    /**
     * 	注册失败
     * @return d
     */
    boolean registerFail(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400007");
    }

    /**
     * 	设置新密码失败
     * @return d
     */
    boolean setNewPwFail(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400008");
    }

    /**
     * 	该手机号未注册
     * @return d
     */
    boolean phoneNotRegister(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400009");
    }

    /**
     * 	该手机号已注册
     * @return d
     */
    boolean phoneRegister(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400010");
    }

    /**
     * 	请输入正确的手机号码
     * @return d
     */
    boolean phoneIncorrect(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400011");
    }

    /**
     * 用户不存在
     * @return d
     */
    boolean userNotExist(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400100");
    }

    /**
     * 密码错误
     * @return d
     */
    boolean pwError(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400200");
    }

    /**
     * 	登录异常
     * @return d
     */
    boolean loginError(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? "" : status, "400300");
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
