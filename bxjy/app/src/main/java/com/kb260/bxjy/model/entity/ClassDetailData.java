package com.kb260.bxjy.model.entity;

/**
 * @author KB260
 *         Created on  2018/2/28
 */

public class ClassDetailData {

    /**
     * crouseGroupId : 1
     * groupName : 蜘蛛侠合集
     * describe : 蜘蛛侠好啊
     * openingTime : 1517560710000
     * closeingTime : 1517560712000
     * price : 100
     * pay : true
     * buy : false
     */

    private int crouseGroupId;
    private String groupName;
    private String describe;
    private long openingTime;
    private long closeingTime;
    private int price;
    private boolean pay;
    private boolean buy;

    public int getCrouseGroupId() {
        return crouseGroupId;
    }

    public void setCrouseGroupId(int crouseGroupId) {
        this.crouseGroupId = crouseGroupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public long getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(long openingTime) {
        this.openingTime = openingTime;
    }

    public long getCloseingTime() {
        return closeingTime;
    }

    public void setCloseingTime(long closeingTime) {
        this.closeingTime = closeingTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isPay() {
        return pay;
    }

    public void setPay(boolean pay) {
        this.pay = pay;
    }

    public boolean isBuy() {
        return buy;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
    }
}
