package com.panda.sharebike.model;

/**
 * <pre>
 *     author : zhangyuan
 *     e-mail : zhangyuan_min@163.com
 *     time   : 2017/06/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MessageEvent { /* Additional fields if needed */
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
