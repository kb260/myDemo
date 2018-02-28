package com.kb260.gxdk.entity;

/**
 * @author KB260
 *         Created on  2017/12/20
 */

public class Version {

    /**
     * id : 1
     * flag : null
     * createtime : 2017-11-10 09:41:42
     * type : android
     * currentversion : 1.2
     * context : ururiur
     * reserve : www.hdkhjs.com
     */

    private int id;
    private Object flag;
    private String createtime;
    private String type;
    private double currentversion;
    private String context;
    private String reserve;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getFlag() {
        return flag;
    }

    public void setFlag(Object flag) {
        this.flag = flag;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCurrentversion() {
        return currentversion;
    }

    public void setCurrentversion(double currentversion) {
        this.currentversion = currentversion;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }
}
