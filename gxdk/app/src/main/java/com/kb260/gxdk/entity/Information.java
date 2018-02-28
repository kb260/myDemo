package com.kb260.gxdk.entity;

/**
 * @author KB260
 *         Created on  2017/11/16
 */

public class Information {

    /**
     * id : 2
     * userid : 15
     * flag : 12
     * createtime : 4
     * content : 4
     * picture : null
     * reserve : null
     * title : 4
     * status : 0
     */

    private int id;
    private int userid;
    private String flag;
    private String createtime;
    private String content;
    private Object picture;
    private Object reserve;
    private String title;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public Object getReserve() {
        return reserve;
    }

    public void setReserve(Object reserve) {
        this.reserve = reserve;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
