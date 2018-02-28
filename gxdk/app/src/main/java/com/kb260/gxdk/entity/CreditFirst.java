package com.kb260.gxdk.entity;

import com.kb260.gxdk.utils.StringUtils;

import java.io.Serializable;

/**
 * @author KB260
 *         Created on  2017/11/15
 */

public class CreditFirst implements Serializable{
    private String name;
    private String age;
    private String sfz;
    private String phone;
    private String marry;
    private String spouseName;
    private String spousePhone;
    private String haveCar;
    private String haveHouse;
    private String address;
    private String applicationMoney;
    private String workAddress;
    private String industry;
    private String career;
    private String houseAddress;
    private String houseArea;
    private String houseNature;
    private String houseStatus;
    private String cjh;
    private String cpp;
    private String cp;
    private String cl;
    private String xsjl;
    private String province;
    private String city;
    private String zone;
    private String bank;
    private String sywk;
    private String houseAge;
    private String ck;
    private String useddate;
    private String useddateMonth;
    private String gcyj;
    private String cppId;
    private String sfdz;
    private int kind;
    private String type;

    public CreditFirst(String name, String age, String sfz, String phone, String marry, String spouseName, String spousePhone, String haveCar, String haveHouse, String address, String applicationMoney, String workAddress, String industry, String career, String houseAddress, String houseArea, String houseNature, String houseStatus, String cjh, String cpp, String cp, String cl, String xsjl, String province, String city, String zone, String bank, String sywk, String houseAge, String ck, String useddate, String useddateMonth, String gcyj, String cppId, String sfdz, int kind, String type) {
        this.name = name;
        this.age = age;
        this.sfz = sfz;
        this.phone = phone;
        this.marry = marry;
        this.spouseName = spouseName;
        this.spousePhone = spousePhone;
        this.haveCar = haveCar;
        this.haveHouse = haveHouse;
        this.address = address;
        this.applicationMoney = applicationMoney;
        this.workAddress = workAddress;
        this.industry = industry;
        this.career = career;
        this.houseAddress = houseAddress;
        this.houseArea = houseArea;
        this.houseNature = houseNature;
        this.houseStatus = houseStatus;
        this.cjh = cjh;
        this.cpp = cpp;
        this.cp = cp;
        this.cl = cl;
        this.xsjl = xsjl;
        this.province = province;
        this.city = city;
        this.zone = zone;
        this.bank = bank;
        this.sywk = sywk;
        this.houseAge = houseAge;
        this.ck = ck;
        this.useddate = useddate;
        this.useddateMonth = useddateMonth;
        this.gcyj = gcyj;
        this.cppId = cppId;
        this.sfdz = sfdz;
        this.kind = kind;
        this.type = type;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSfdz() {
        return sfdz;
    }

    public void setSfdz(String sfdz) {
        this.sfdz = sfdz;
    }

    public String getCk() {
        return ck;
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public String getUseddate() {
        return useddate;
    }

    public void setUseddate(String useddate) {
        this.useddate = useddate;
    }

    public String getUseddateMonth() {
        return useddateMonth;
    }

    public void setUseddateMonth(String useddateMonth) {
        this.useddateMonth = useddateMonth;
    }

    public String getGcyj() {
        return gcyj;
    }

    public void setGcyj(String gcyj) {
        this.gcyj = gcyj;
    }

    public String getCppId() {
        return cppId;
    }

    public void setCppId(String cppId) {
        this.cppId = cppId;
    }

    public int getHouseAge() {
        if (!StringUtils.isEmpty(houseAge)){
            return Integer.valueOf(houseAge);
        }
        return 0;
    }

    public void setHouseAge(String houseAge) {
        this.houseAge = houseAge;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getSywk() {
        return sywk;
    }

    public void setSywk(String sywk) {
        this.sywk = sywk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getSpousePhone() {
        return spousePhone;
    }

    public void setSpousePhone(String spousePhone) {
        this.spousePhone = spousePhone;
    }

    public String getHaveCar() {
        return haveCar;
    }

    public void setHaveCar(String haveCar) {
        this.haveCar = haveCar;
    }

    public String getHaveHouse() {
        return haveHouse;
    }

    public void setHaveHouse(String haveHouse) {
        this.haveHouse = haveHouse;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApplicationMoney() {
        return applicationMoney;
    }

    public void setApplicationMoney(String applicationMoney) {
        this.applicationMoney = applicationMoney;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea;
    }

    public String getHouseNature() {
        return houseNature;
    }

    public void setHouseNature(String houseNature) {
        this.houseNature = houseNature;
    }

    public String getHouseStatus() {
        return houseStatus;
    }

    public void setHouseStatus(String houseStatus) {
        this.houseStatus = houseStatus;
    }

    public String getCjh() {
        return cjh;
    }

    public void setCjh(String cjh) {
        this.cjh = cjh;
    }

    public String getCpp() {
        return cpp;
    }

    public void setCpp(String cpp) {
        this.cpp = cpp;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public String getXsjl() {
        return xsjl;
    }

    public void setXsjl(String xsjl) {
        this.xsjl = xsjl;
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
