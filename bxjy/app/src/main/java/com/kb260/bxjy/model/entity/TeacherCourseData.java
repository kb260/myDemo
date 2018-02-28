package com.kb260.bxjy.model.entity;

/**
 * @author KB260
 *         Created on  2018/2/27
 */

public class TeacherCourseData {

    /**
     * id : 1
     * groupName : 蜘蛛侠合集
     * groupImg : null
     * subjectId : 1
     * openingTime : 1517560710000
     * closeingTime : 1517560712000
     * clickRate : 100
     * price : 100
     * purchaseNum : 23
     * describe : 蜘蛛侠好啊
     * ispay : true
     * status : 1
     * createId : 1
     * updateId : null
     * createTime : 1517560740000
     * updateTime : 1519695515000
     * beginCategory : 1
     * courseCount : 3
     */

    private int id;
    private String groupName;
    private Object groupImg;
    private int subjectId;
    private long openingTime;
    private long closeingTime;
    private int clickRate;
    private int price;
    private int purchaseNum;
    private String describe;
    private boolean ispay;
    private int status;
    private int createId;
    private Object updateId;
    private long createTime;
    private long updateTime;
    private int beginCategory;
    private int courseCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Object getGroupImg() {
        return groupImg;
    }

    public void setGroupImg(Object groupImg) {
        this.groupImg = groupImg;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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

    public int getClickRate() {
        return clickRate;
    }

    public void setClickRate(int clickRate) {
        this.clickRate = clickRate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(int purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isIspay() {
        return ispay;
    }

    public void setIspay(boolean ispay) {
        this.ispay = ispay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreateId() {
        return createId;
    }

    public void setCreateId(int createId) {
        this.createId = createId;
    }

    public Object getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Object updateId) {
        this.updateId = updateId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getBeginCategory() {
        return beginCategory;
    }

    public void setBeginCategory(int beginCategory) {
        this.beginCategory = beginCategory;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }
}
