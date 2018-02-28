package com.kb260.kbutils.network;

import com.kb260.kbutils.utils.StringUtils;

import java.io.Serializable;

/**
 * @author KB260
 *         Created on  2018/1/10
 */

public class BaseModel<T> implements Serializable {
    public String status;
    public String message;
    public long timestamp;
    public T params;
    //成功状态码
    public static String SUCCEED_CODE = "900000";

    /**
     * 请求成功
     * @return 是否成功
     */
    public boolean isSucceed(){
        return StringUtils.equals(StringUtils.isEmpty(status) ? SUCCEED_CODE : status, SUCCEED_CODE);
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
