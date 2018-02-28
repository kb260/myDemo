package com.kb260.bxjy.model.entity;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/26
 */

public class LiveRecommendedData {

    /**
     * groupName : 蜘蛛侠合集
     * groupImg : null
     * describe : null
     * openingTime : 1517560710000
     * closeingTime : 1517560712000
     * ispay : true
     * price : 100.0
     * purchaseNum : 23
     * teacherList : [{"id":1,"name":"钢铁侠","describe":"********牛逼","level":"A_SENIOR_TEACHER","primaryCoverage":"***武器制造","score":9.7,"headImg":null,"status":1,"createTime":1517560054000,"updateTime":1517560054000},{"id":2,"name":"蜘蛛侠","describe":"漫威英雄人物，***","level":"A_SENIOR_TEACHER","primaryCoverage":"吐司，城市爬行动物","score":9.8,"headImg":null,"status":1,"createTime":1517560142000,"updateTime":1517560142000}]
     * courseGroupId : 1
     * clickRate : null
     */

    private String groupName;
    private Object groupImg;
    private Object describe;
    private long openingTime;
    private long closeingTime;
    private boolean ispay;
    private double price;
    private int purchaseNum;
    private int courseGroupId;
    private Object clickRate;
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

    public Object getDescribe() {
        return describe;
    }

    public void setDescribe(Object describe) {
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

    public int getCourseGroupId() {
        return courseGroupId;
    }

    public void setCourseGroupId(int courseGroupId) {
        this.courseGroupId = courseGroupId;
    }

    public Object getClickRate() {
        return clickRate;
    }

    public void setClickRate(Object clickRate) {
        this.clickRate = clickRate;
    }

    public List<TeacherListBean> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherListBean> teacherList) {
        this.teacherList = teacherList;
    }

    public static class TeacherListBean {
        /**
         * id : 1
         * name : 钢铁侠
         * describe : ********牛逼
         * level : A_SENIOR_TEACHER
         * primaryCoverage : ***武器制造
         * score : 9.7
         * headImg : null
         * status : 1
         * createTime : 1517560054000
         * updateTime : 1517560054000
         */

        private int id;
        private String name;
        private String describe;
        private String level;
        private String primaryCoverage;
        private double score;
        private Object headImg;
        private int status;
        private long createTime;
        private long updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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
    }
}
