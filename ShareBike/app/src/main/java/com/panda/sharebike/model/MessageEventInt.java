package com.panda.sharebike.model;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/08/02
 * desc   :
 * version: 1.0
 */
public class MessageEventInt {
    private int type;
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public MessageEventInt(int type) {
        this.type = type;
    }

    public MessageEventInt(int type,String orderId) {
        this.type = type;this.orderId=orderId;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
