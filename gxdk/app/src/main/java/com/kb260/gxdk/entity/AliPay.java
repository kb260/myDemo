package com.kb260.gxdk.entity;

/**
 * @author KB260
 *         Created on  2017/11/16
 */

public class AliPay {

    /**
     * code : null
     * msg : null
     * subCode : null
     * subMsg : null
     * body : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017110209674870&biz_content=%7B%22body%22%3A%22hello%22%2C%22out_trade_no%22%3A%221510819211344%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22test%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Feefdt9.natappfree.cc%2Fapi%2Fpay%2Fali%2Fnotify&sign=br2D1NP%2FagX0wMHcyMtkwNDPfWrouuCJhiZovxom5K1YR23%2FoJRKaY%2BHCJXDarRJJizKDv3ZJtXDNYmXltEixfGr937JWkONUQ5HMZuyD6rxD1WiDTOdKm9ZBmkerC6PBedEUtnIhMixnU5aWCP3xx%2FKvbGFN9f1KzJn8Dcx6N3QdHcfeFfwVIM9R97vTfXIFwmSAPeyMouUprosaWa1a1oL6jZokaS0oUwhtk9QpnCnIqBiR9KZp%2F%2FpzVO9WL3EUC0ycdU3JS8NHKfj1O7fJNzh1sL19f4iW1GIP%2F71b5GFxo6a1lGj0Ua8h%2BdO4YIQ%2BXCNHQBNu2emfN0kjnLfuQ%3D%3D&sign_type=RSA2&timestamp=2017-11-16+16%3A00%3A11&version=1.0
     * params : null
     * outTradeNo : null
     * sellerId : null
     * totalAmount : null
     * tradeNo : null
     * errorCode : null
     * success : true
     */

    private Object code;
    private Object msg;
    private Object subCode;
    private Object subMsg;
    private String body;
    private Object params;
    private Object outTradeNo;
    private Object sellerId;
    private Object totalAmount;
    private Object tradeNo;
    private Object errorCode;
    private boolean success;

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Object getSubCode() {
        return subCode;
    }

    public void setSubCode(Object subCode) {
        this.subCode = subCode;
    }

    public Object getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(Object subMsg) {
        this.subMsg = subMsg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public Object getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(Object outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Object getSellerId() {
        return sellerId;
    }

    public void setSellerId(Object sellerId) {
        this.sellerId = sellerId;
    }

    public Object getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Object totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Object getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(Object tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Object getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Object errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
