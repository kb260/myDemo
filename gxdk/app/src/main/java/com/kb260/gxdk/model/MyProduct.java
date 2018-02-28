package com.kb260.gxdk.model;

import java.io.Serializable;

/**
 * @author  KB260
 * Created on  2017/11/10
 */

public class MyProduct implements Serializable{

    /**
     * id : 5
     * userid : 11
     * flag : 0
     * createtime : 2017-11-10 09:31:59
     * age : 11
     * maxage : 33
     * creditday : null
     * roomage : null
     * bank : null
     * branchname : null
     * loantype : null
     * usertype : null
     * ismanager : null
     * isproperty : null
     * area : null
     * rate : 5.0
     * maxrate : null
     * count : null
     * paytype : null
     * paytime : null
     * address : null
     * status : 0
     * cardphoto : null
     * marriagephoto : null
     * residencephoto : null
     * threephoto : null
     * license : null
     * creditphoto : null
     * association : null
     * maxcarage : null
     * carage : null
     * replenishment : null
     * reserve : null
     * type : 1
     * province : null
     * city : null
     * zone : null
     */

    private int id;
    private int userid;
    private String flag;
    private String createtime;
    private String age;
    private String maxage;
    private String creditday;
    private String roomage;
    private String bank;
    private String branchname;
    private String loantype;
    private String usertype;
    private String ismanager;
    private String isproperty;
    private String area;
    private double rate;
    private double maxrate;
    private String count;
    private String paytype;
    private String paytime;
    private String address;
    private String status;
    private String cardphoto;
    private String marriagephoto;
    private String residencephoto;
    private String threephoto;
    private String license;
    private String creditphoto;
    private String association;
    private String maxcarage;
    private String carage;
    private String replenishment;
    private String reserve;
    private String type;
    private String province;
    private String city;
    private String zone;
    private String cardistance;
    private String ratio;

    public String getCardistance() {
        return cardistance+"万公里内";
    }

    public void setCardistance(String cardistance) {
        this.cardistance = cardistance;
    }

    public String getRatio() {
        return ratio+"%以上";
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
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

    public String getAge() {
        return age+"岁";
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMaxage() {
        return maxage+"岁";
    }

    public void setMaxage(String maxage) {
        this.maxage = maxage;
    }

    public String getCreditday() {
        return creditday+"天";
    }

    public void setCreditday(String creditday) {
        this.creditday = creditday;
    }

    public String getRoomage() {
        return roomage+"年以内";
    }

    public void setRoomage(String roomage) {
        this.roomage = roomage;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getLoantype() {
        return loantype;
    }

    public void setLoantype(String loantype) {
        this.loantype = loantype;
    }

    public String getUsertype() {
        if (usertype != null){
            return usertype;
        }
        return "";
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getIsmanager() {
        return ismanager;
    }

    public void setIsmanager(String ismanager) {
        this.ismanager = ismanager;
    }

    public String getIsproperty() {
        return isproperty;
    }

    public void setIsproperty(String isproperty) {
        this.isproperty = isproperty;
    }

    public String getArea() {
        return area+"平方米以上";
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMaxrate() {
        return maxrate;
    }

    public void setMaxrate(double maxrate) {
        this.maxrate = maxrate;
    }

    public String getCount() {
        return count+"万";
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCardphoto() {
        return cardphoto;
    }

    public void setCardphoto(String cardphoto) {
        this.cardphoto = cardphoto;
    }

    public String getMarriagephoto() {
        return marriagephoto;
    }

    public void setMarriagephoto(String marriagephoto) {
        this.marriagephoto = marriagephoto;
    }

    public String getResidencephoto() {
        return residencephoto;
    }

    public void setResidencephoto(String residencephoto) {
        this.residencephoto = residencephoto;
    }

    public String getThreephoto() {
        return threephoto;
    }

    public void setThreephoto(String threephoto) {
        this.threephoto = threephoto;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCreditphoto() {
        return creditphoto;
    }

    public void setCreditphoto(String creditphoto) {
        this.creditphoto = creditphoto;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getMaxcarage() {
        return maxcarage+"年以内";
    }

    public void setMaxcarage(String maxcarage) {
        this.maxcarage = maxcarage;
    }

    public String getCarage() {
        return carage+"年";
    }

    public void setCarage(String carage) {
        this.carage = carage;
    }

    public String getReplenishment() {
        return replenishment;
    }

    public void setReplenishment(String replenishment) {
        this.replenishment = replenishment;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
