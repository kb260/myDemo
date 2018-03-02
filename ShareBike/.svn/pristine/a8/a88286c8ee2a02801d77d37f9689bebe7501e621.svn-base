package com.panda.sharebike.api;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/07/17
 * desc   :请求结果封装类
 * version: 1.0
 */
public class HttpResult<T> {

    private int code;
    private String msg;
    private T data;

    //该方法假设服务器返回200表示成功返回结果，其他数字表示异常
    public boolean isOk() {
        return code == 200;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
