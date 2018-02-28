package com.kb260.gxdk.model;

/**
 * @author KB260
 *         Created on  2017/11/14
 */

public class Protocol {

    /**
     * id : 2
     * userid : 2
     * flag : 2
     * createtime : 2017-10-27 17:25:47
     * content : 4
     * title : 3
     * picture : 3
     */

    private int id;
    private int userid;
    private String flag;
    private String createtime;
    private String content;
    private String title;
    private String picture;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
