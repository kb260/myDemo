package com.kb260.gxdk.model;

/**
 * @author KB260
 *         Created on  2017/12/5
 */

public class CarAccess {

    /**
     * id : 9
     * userid : 40
     * flag : 0
     * createtime : 2017-12-06 09:17:11
     * idcard : null
     * telphone : null
     * realname : null
     * carstatus : 3
     * city : 杭州市
     * purpose : 1
     * totalprice : 17.44
     * province : 浙江省
     * car : 1020637
     * reserve : null
     * useddate : 2017
     * useddatemonth : 01
     * mileage : 30.0
     * price : 60.0
     */

    private int id;
    private int userid;
    private String flag;
    private String createtime;
    private Object idcard;
    private Object telphone;
    private Object realname;
    private String carstatus;
    private String city;
    private String purpose;
    private String totalprice;
    private String province;
    private String car;
    private String reserve;
    private String useddate;
    private String useddatemonth;
    private double mileage;
    private double price;

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

    public Object getIdcard() {
        return idcard;
    }

    public void setIdcard(Object idcard) {
        this.idcard = idcard;
    }

    public Object getTelphone() {
        return telphone;
    }

    public void setTelphone(Object telphone) {
        this.telphone = telphone;
    }

    public Object getRealname() {
        return realname;
    }

    public void setRealname(Object realname) {
        this.realname = realname;
    }

    public String getCarstatus() {
        return carstatus;
    }

    public void setCarstatus(String carstatus) {
        this.carstatus = carstatus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTotalprice() {
        return totalprice+"万元";
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getUseddate() {
        return useddate;
    }

    public void setUseddate(String useddate) {
        this.useddate = useddate;
    }

    public String getUseddatemonth() {
        return useddatemonth;
    }

    public void setUseddatemonth(String useddatemonth) {
        this.useddatemonth = useddatemonth;
    }

    public String getMileage() {
        return mileage+"万公里";
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
