package com.kb260.bxjy.model.entity;

/**
 * @author KB260
 *         Created on  2018/2/26
 */

public class PersonInfoData {

    /**
     * id : 2
     * name : 14727002600
     * phone : 14727002600
     * password : 123456
     * sex : 1
     * headImg : null
     * createTime : 1519543627000
     * updateTime : 1519543627000
     * status : 1
     */

    private int id;
    private String name;
    private String phone;
    private String password;
    private int sex;
    private String headImg;
    private long createTime;
    private long updateTime;
    private int status;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
