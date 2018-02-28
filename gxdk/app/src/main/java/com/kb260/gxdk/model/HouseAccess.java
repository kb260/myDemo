package com.kb260.gxdk.model;

/**
 * @author KB260
 *         Created on  2017/11/10
 */

public class HouseAccess {

    /**
     * id : 3
     * userid : 15
     * flag : 0
     * createtime : 2017-11-10 15:07:39
     * idcard : 61979797
     * telphone : 16949797
     * realname : 掐指一算
     * area : null
     * address : 北京市北京市崇文区
     * unitprice : null
     * totalprice : null
     * roomarea : 67平方米
     * type : null
     * reserve : null
     * sign : 1
     */

    private int id;
    private int userid;
    private String flag;
    private String createtime;
    private String idcard;
    private String telphone;
    private String realname;
    private Object area;
    private String address;
    private String unitprice;
    private String totalprice;
    private String roomarea;
    private Object type;
    private Object reserve;
    private String sign;
    private String 	realarea;

    public String getRealarea() {
        return realarea;
    }

    public void setRealarea(String realarea) {
        this.realarea = realarea;
    }

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

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Object getArea() {
        return area;
    }

    public void setArea(Object area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUnitprice() {
        return unitprice+"元";
    }

    public void setUnitprice(String unitprice) {
        this.unitprice = unitprice;
    }

    public String getTotalprice() {
        return totalprice+"元";
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getRoomarea() {
        return roomarea+"平方米";
    }

    public void setRoomarea(String roomarea) {
        this.roomarea = roomarea;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getReserve() {
        return reserve;
    }

    public void setReserve(Object reserve) {
        this.reserve = reserve;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
