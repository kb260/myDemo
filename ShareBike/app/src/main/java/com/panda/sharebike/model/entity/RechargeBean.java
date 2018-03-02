package com.panda.sharebike.model.entity;

import java.io.Serializable;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/07/26
 * desc   :
 * version: 1.0
 */
public class RechargeBean implements Serializable {

    /**
     * id : 5965c81167ecaa0f28a93118
     * orderName : 巧骑单车充值订单
     * totalPrice : 0.01
     * alipayNotifyUrl : http://192.168.0.177:1111/qbike/thirdpay/alipay/notify
     * tenpayNotifyUrl : http://192.168.0.177:1111/qbike/thirdpay/tenpay/notify
     */

    private String id;
    private String orderName;
    private double totalPrice;
    private String alipayNotifyUrl;
    private String tenpayNotifyUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAlipayNotifyUrl() {
        return alipayNotifyUrl;
    }

    public void setAlipayNotifyUrl(String alipayNotifyUrl) {
        this.alipayNotifyUrl = alipayNotifyUrl;
    }

    public String getTenpayNotifyUrl() {
        return tenpayNotifyUrl;
    }

    public void setTenpayNotifyUrl(String tenpayNotifyUrl) {
        this.tenpayNotifyUrl = tenpayNotifyUrl;
    }
}
