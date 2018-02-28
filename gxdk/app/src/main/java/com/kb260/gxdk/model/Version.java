package com.kb260.gxdk.model;

/**
 * @author KB260
 *         Created on  2017/11/14
 */

public class Version {

    /**
     * id : 2
     * flag : 0
     * createtime : 4
     * type : android
     * currentversion : 3.0
     * context : 3
     * reserve : 3
     */

    private int id;
    private String flag;
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
