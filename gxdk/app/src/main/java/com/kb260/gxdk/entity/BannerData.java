package com.kb260.gxdk.entity;

/**
 * @author KB260
 *         Created on  2017/11/28
 */

public class BannerData {

    /**
     * id : 3
     * userid : null
     * flag : 0
     * createtime : 2017-11-28 12:25:14
     * picture : http://ykd2017.oss-cn-hangzhou.aliyuncs.com/656971eb3b64439f94d3b09fe92d3238che.png
     */

    private int id;
    private Object userid;
    private String flag;
    private String createtime;
    private String picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getUserid() {
        return userid;
    }

    public void setUserid(Object userid) {
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

    public String getPicture() {
        if (picture != null){
            return picture;
        }
        return "";
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
