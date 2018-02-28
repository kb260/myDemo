package com.kb260.gxdk.model;

/**
 * @author KB260
 *         Created on  2017/11/13
 */

public class AboutApp {

    /**
     * id : 1
     * flag : 1
     * createtime : 1
     * title : 1
     * content : 1
     * reserve : 1
     */

    private int id;
    private String flag;
    private String createtime;
    private String title;
    private String content;
    private String reserve;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
}
