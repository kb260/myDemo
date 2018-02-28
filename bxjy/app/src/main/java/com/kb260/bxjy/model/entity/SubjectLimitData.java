package com.kb260.bxjy.model.entity;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/27
 */

public class SubjectLimitData {

    /**
     * subjectId : 1
     * subjectName : 漫画英雄类
     * cousrseGroup : [{"id":1,"groupName":"蜘蛛侠合集","groupImg":null,"subjectId":1,"openingTime":1517560710000,"closeingTime":1517560712000,"clickRate":104,"price":100,"purchaseNum":23,"describe":"蜘蛛侠好啊","ispay":true,"status":1,"createId":1,"updateId":null,"createTime":1517560740000,"updateTime":1519695515000,"beginCategory":1,"courseCount":3},{"id":2,"groupName":"猪猪侠合集","groupImg":null,"subjectId":1,"openingTime":1517560761000,"closeingTime":1517560763000,"clickRate":70,"price":90,"purchaseNum":45,"describe":"猪猪侠好啊","ispay":true,"status":1,"createId":1,"updateId":null,"createTime":1517560785000,"updateTime":1519695516000,"beginCategory":2,"courseCount":3},{"id":3,"groupName":"钢铁侠合集","groupImg":null,"subjectId":1,"openingTime":1517560800000,"closeingTime":1517560802000,"clickRate":9,"price":9,"purchaseNum":1,"describe":"钢铁侠不行了啊","ispay":true,"status":1,"createId":1,"updateId":null,"createTime":1517560820000,"updateTime":1519695517000,"beginCategory":3,"courseCount":3}]
     */

    private int subjectId;
    private String subjectName;
    private List<CousrseGroupBean> cousrseGroup;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<CousrseGroupBean> getCousrseGroup() {
        return cousrseGroup;
    }

    public void setCousrseGroup(List<CousrseGroupBean> cousrseGroup) {
        this.cousrseGroup = cousrseGroup;
    }

    public static class CousrseGroupBean {
        /**
         * id : 1
         * groupName : 蜘蛛侠合集
         * groupImg : null
         * subjectId : 1
         * openingTime : 1517560710000
         * closeingTime : 1517560712000
         * clickRate : 104
         * price : 100.0
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
        private double price;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
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
}
