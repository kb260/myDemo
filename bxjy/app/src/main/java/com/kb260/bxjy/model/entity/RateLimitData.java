package com.kb260.bxjy.model.entity;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/27
 */

public class RateLimitData {

    /**
     * groupName : 钢铁侠合集
     * groupImg : null
     * describe : 钢铁侠不行了啊
     * openingTime : 1517560800000
     * closeingTime : 1517560802000
     * ispay : true
     * price : 9
     * purchaseNum : 1
     * teacherList : [{"teacherId":3,"name":"猪猪侠","describe":"中国漫画人物，***","primaryCoverage":"一只猪，大招翔龙十八掌","score":9.4,"headImg":null,"teacherLevel":"博学资深讲师"}]
     * clickRate : 9
     * courseGroupId : 3
     */

    private String groupName;
    private Object groupImg;
    private String describe;
    private long openingTime;
    private long closeingTime;
    private boolean ispay;
    private int price;
    private int purchaseNum;
    private int clickRate;
    private int courseGroupId;
    private List<TeacherListBean> teacherList;

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

    public boolean isIspay() {
        return ispay;
    }

    public void setIspay(boolean ispay) {
        this.ispay = ispay;
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

    public int getClickRate() {
        return clickRate;
    }

    public void setClickRate(int clickRate) {
        this.clickRate = clickRate;
    }

    public int getCourseGroupId() {
        return courseGroupId;
    }

    public void setCourseGroupId(int courseGroupId) {
        this.courseGroupId = courseGroupId;
    }

    public List<TeacherListBean> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherListBean> teacherList) {
        this.teacherList = teacherList;
    }

    public static class TeacherListBean {
        /**
         * teacherId : 3
         * name : 猪猪侠
         * describe : 中国漫画人物，***
         * primaryCoverage : 一只猪，大招翔龙十八掌
         * score : 9.4
         * headImg : null
         * teacherLevel : 博学资深讲师
         */

        private int teacherId;
        private String name;
        private String describe;
        private String primaryCoverage;
        private double score;
        private Object headImg;
        private String teacherLevel;

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getPrimaryCoverage() {
            return primaryCoverage;
        }

        public void setPrimaryCoverage(String primaryCoverage) {
            this.primaryCoverage = primaryCoverage;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public Object getHeadImg() {
            return headImg;
        }

        public void setHeadImg(Object headImg) {
            this.headImg = headImg;
        }

        public String getTeacherLevel() {
            return teacherLevel;
        }

        public void setTeacherLevel(String teacherLevel) {
            this.teacherLevel = teacherLevel;
        }
    }
}
