package com.kb260.gxdk.model;

/**
 * @author KB260
 *         Created on  2017/11/6
 */

public class Information {

    /**
     * id : 2
     * flag : 12
     * createtime : 4
     * content : 4
     * picture : null
     * reserve : null
     * title : 4
     */

    private int id;
    private String flag;
    private String createtime;
    private String content;
    private String picture;
    private Object reserve;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
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
}
