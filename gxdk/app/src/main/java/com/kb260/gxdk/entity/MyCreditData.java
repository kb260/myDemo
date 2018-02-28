package com.kb260.gxdk.entity;

import java.io.Serializable;

/**
 * @author KB260
 *         Created on  2017/12/7
 */

public class MyCreditData implements Serializable{


    /**
     * id : 20
     * userid : 69
     * createtime : 2017-12-07 20:43:12
     * flag : 0
     * realname : 不行
     * age : 5
     * idcard : 330821199507066037
     * telphone : 14727002600
     * address : 北京市 北京市 东城区不行街道/镇换小区6幢6室
     * spousename : 型号
     * spousetel : 14727002600
     * count : 23.0
     * dueoption : 信用卡
     * duetime : 2017-12-07
     * amount : 10020.0
     * status : 0
     * sign : 已婚
     * isoverdue : 是
     * instruction : 不行不行
     * iscar : 是
     * isroom : null
     * workplace : 天津市 天津市 和平区
     * industry : 只能
     * occupation : 信你
     * currentsalary : 67646
     * worktime : 30个月
     * payform : 混合
     * isfund : 有
     * unilateralpay : 6464
     * paycity : 北京市 北京市 东城区
     * limittime : 2个月
     * isinsurancepolicy : null
     * insuredcompany : 继续继续
     * monthpay : 6464
     * yearpay : 646767
     * province : null
     * city : null
     * zone : null
     * reserve : null
     * carframe : 67979797979797979
     * carbrand : 本田 哥瑞 哥瑞 2016款 1.5 手动 经典版
     * carno : 浙A
     * carage : 20年
     * distance : 204
     * type : null
     * area : null
     * current : null
     * nature : null
     * roomaddress : null街道/镇null小区null幢null室
     * addressdetail : null
     * car : 1088130
     * carstatus : 1
     * useddate : 2018
     * useddatemonth : 12
     * price : 203
     * mortgagebank : null
     * expenditure : null
     * surpius : null
     * times : 0
     */

    private int id;
    private int userid;
    private String createtime;
    private String flag;
    private String realname;
    private String age;
    private String idcard;
    private String telphone;
    private String address;
    private String spousename;
    private String spousetel;
    private double count;
    private String dueoption;
    private String duetime;
    private double amount;
    private String status;
    private String sign;
    private String isoverdue;
    private String instruction;
    private String iscar;
    private String isroom;
    private String workplace;
    private String industry;
    private String occupation;
    private String currentsalary;
    private String worktime;
    private String payform;
    private String isfund;
    private String unilateralpay;
    private String paycity;
    private String limittime;
    private String isinsurancepolicy;
    private String insuredcompany;
    private String monthpay;
    private String yearpay;
    private String province;
    private String city;
    private String zone;
    private String reserve;
    private String carframe;
    private String carbrand;
    private String carno;
    private String carage;
    private String distance;
    private String type;
    private String area;
    private String current;
    private String nature;
    private String roomaddress;
    private String addressdetail;
    private String car;
    private String carstatus;
    private String useddate;
    private String useddatemonth;
    private String price;
    private String mortgagebank;
    private String expenditure;
    private String surpius;
    private int times;
    private String reverse;
    private int iscredit;

    public String getReverse() {
        if (reverse != null){
            return reverse;
        }
        return "";
    }

    public void setReverse(String reverse) {
        this.reverse = reverse;
    }

    public int getIscredit() {
        return iscredit;
    }

    public void setIscredit(int iscredit) {
        this.iscredit = iscredit;
    }

    public String getIscar() {
        return iscar;
    }

    public void setIscar(String iscar) {
        this.iscar = iscar;
    }

    public String getIsroom() {
        return isroom;
    }



    public String getDistance() {
        return distance+"万公里";
    }



    public String getArea() {
        return area+"平方米";
    }




    public String getPrice() {
        return price+"万元";
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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAge() {
        return age+"岁";
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpousename() {
        return spousename;
    }

    public void setSpousename(String spousename) {
        this.spousename = spousename;
    }

    public String getSpousetel() {
        return spousetel;
    }

    public void setSpousetel(String spousetel) {
        this.spousetel = spousetel;
    }

    public String getCount() {
        return count+"万元";
    }

    public void setCount(double count) {
        this.count = count;
    }

    public String getDueoption() {
        return dueoption;
    }

    public void setDueoption(String dueoption) {
        this.dueoption = dueoption;
    }

    public String getDuetime() {
        return duetime;
    }

    public void setDuetime(String duetime) {
        this.duetime = duetime;
    }

    public String getAmount() {
        return amount+"元";
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        if (status != null){
            switch (status){
                case "1":
                    return "申请中";
                case "2":
                    return "已成功";
                case "3":
                    return "已失效";
                default:
                    return "";
            }

        }else {
            return "";
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getIsoverdue() {
        return isoverdue;
    }

    public void setIsoverdue(String isoverdue) {
        this.isoverdue = isoverdue;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }





    public void setIsroom(String isroom) {
        this.isroom = isroom;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCurrentsalary() {
        return currentsalary;
    }

    public void setCurrentsalary(String currentsalary) {
        this.currentsalary = currentsalary;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getPayform() {
        return payform;
    }

    public void setPayform(String payform) {
        this.payform = payform;
    }

    public String getIsfund() {
        return isfund;
    }

    public void setIsfund(String isfund) {
        this.isfund = isfund;
    }

    public String getUnilateralpay() {
        return unilateralpay;
    }

    public void setUnilateralpay(String unilateralpay) {
        this.unilateralpay = unilateralpay;
    }

    public String getPaycity() {
        return paycity;
    }

    public void setPaycity(String paycity) {
        this.paycity = paycity;
    }

    public String getLimittime() {
        return limittime;
    }

    public void setLimittime(String limittime) {
        this.limittime = limittime;
    }

    public String getIsinsurancepolicy() {
        if (isinsurancepolicy != null){
            return isinsurancepolicy;
        }
        return "";
    }

    public void setIsinsurancepolicy(String isinsurancepolicy) {
        this.isinsurancepolicy = isinsurancepolicy;
    }

    public String getInsuredcompany() {
        return insuredcompany;
    }

    public void setInsuredcompany(String insuredcompany) {
        this.insuredcompany = insuredcompany;
    }

    public String getMonthpay() {
        return monthpay;
    }

    public void setMonthpay(String monthpay) {
        this.monthpay = monthpay;
    }

    public String getYearpay() {
        return yearpay;
    }

    public void setYearpay(String yearpay) {
        this.yearpay = yearpay;
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

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getCarframe() {
        return carframe;
    }

    public void setCarframe(String carframe) {
        this.carframe = carframe;
    }

    public String getCarbrand() {
        return carbrand;
    }

    public void setCarbrand(String carbrand) {
        this.carbrand = carbrand;
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public String getCarage() {
        return carage;
    }

    public void setCarage(String carage) {
        this.carage = carage;
    }


    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public void setArea(String area) {
        this.area = area;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getRoomaddress() {
        return roomaddress;
    }

    public void setRoomaddress(String roomaddress) {
        this.roomaddress = roomaddress;
    }

    public String getAddressdetail() {
        return addressdetail;
    }

    public void setAddressdetail(String addressdetail) {
        this.addressdetail = addressdetail;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCarstatus() {
        if (carstatus != null){
            switch (carstatus){
                case "1":
                    return "优秀";
                case "2":
                    return "一般";
                case "3":
                    return "较差";
                default:
                    return "";
            }
        }else {
            return "";
        }
    }

    public void setCarstatus(String carstatus) {
        this.carstatus = carstatus;
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

  

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMortgagebank() {
        return mortgagebank;
    }

    public void setMortgagebank(String mortgagebank) {
        this.mortgagebank = mortgagebank;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
    }

    public String getSurpius() {
        return surpius+"万元";
    }

    public void setSurpius(String surpius) {
        this.surpius = surpius;
    }

    public String getTimes() {
        return times+"岁";
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
