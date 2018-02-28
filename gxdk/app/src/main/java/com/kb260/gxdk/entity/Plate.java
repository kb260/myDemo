package com.kb260.gxdk.entity;

/**
 * @author KB260
 *         Created on  2017/11/9
 */

public class Plate {

    /**
     * id : 1
     * name : 奥迪
     * initial : A
     * parentid : 0
     * logo : http://pic1.jisuapi.cn/car/static/images/logo/300/1.png
     * depth : 1
     */

    private String id;
    private String name;
    private String initial;
    private String parentid;
    private String logo;
    private String depth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }
}
