package com.kb260.bxjy.model.entity;

/**
 * @author KB260
 *         Created on  2018/2/25
 */

public class BannerData {

    /**
     * id : 1
     * img1 : http://learned.oss-cn-hongkong.aliyuncs.com/1505984857274.jpg
     * img2 : http://learned.oss-cn-hongkong.aliyuncs.com/1505984857274.jpg
     * courceId : 1
     * status : 1
     * createId : 1
     * createTime : 1517796267000
     * updateId : null
     * updateTime : 1517797734000
     */

    private int id;
    private String img1;
    private String img2;
    private int courceId;
    private int status;
    private int createId;
    private long createTime;
    private Object updateId;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public int getCourceId() {
        return courceId;
    }

    public void setCourceId(int courceId) {
        this.courceId = courceId;
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

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Object updateId) {
        this.updateId = updateId;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
