package com.kb260.gxdk.entity;

import java.io.Serializable;

/**
 * @author KB260
 *         Created on  2017/11/8
 */

public class CarLoanFirst implements Serializable{
    private String name;
    private String age;
    private String sfz;
    private String phone;
    private String marry;
    private String spouseName;
    private String spousePhone;
    private String address;
    private String cjh;
    private String cpp;
    private String cp;
    private String cl;
    private String xsjl;
    private String province;
    private String city;
    private String zone;
    private String cppId;
    private String ck;
    private String gcyj;
    private String useddate;
    private String useddateMonth;
    private String communityid;
    private String communityname;
    private String workplace;
    private String houseDetail;

    public CarLoanFirst(String name, String age, String sfz, String phone, String marry, String spouseName, String spousePhone, String address, String cjh, String cpp, String cp, String cl, String xsjl, String province, String city, String zone, String cppId, String ck, String gcyj, String useddate, String useddateMonth, String communityid, String communityname, String workplace, String houseDetail) {
        this.name = name;
        this.age = age;
        this.sfz = sfz;
        this.phone = phone;
        this.marry = marry;
        this.spouseName = spouseName;
        this.spousePhone = spousePhone;
        this.address = address;
        this.cjh = cjh;
        this.cpp = cpp;
        this.cp = cp;
        this.cl = cl;
        this.xsjl = xsjl;
        this.province = province;
        this.city = city;
        this.zone = zone;
        this.cppId = cppId;
        this.ck = ck;
        this.gcyj = gcyj;
        this.useddate = useddate;
        this.useddateMonth = useddateMonth;
        this.communityid = communityid;
        this.communityname = communityname;
        this.workplace = workplace;
        this.houseDetail = houseDetail;
    }

    public CarLoanFirst(String name, String age, String sfz, String phone, String marry, String spouseName, String spousePhone, String address, String cjh, String cpp, String cp, String cl, String xsjl, String province, String city, String zone, String cppId, String ck, String gcyj, String useddate, String useddateMonth) {
        this.name = name;
        this.age = age;
        this.sfz = sfz;
        this.phone = phone;
        this.marry = marry;
        this.spouseName = spouseName;
        this.spousePhone = spousePhone;
        this.address = address;
        this.cjh = cjh;
        this.cpp = cpp;
        this.cp = cp;
        this.cl = cl;
        this.xsjl = xsjl;
        this.province = province;
        this.city = city;
        this.zone = zone;
        this.cppId = cppId;
        this.ck = ck;
        this.gcyj = gcyj;
        this.useddate = useddate;
        this.useddateMonth = useddateMonth;
    }

    public String getCommunityid() {
        return communityid;
    }

    public void setCommunityid(String communityid) {
        this.communityid = communityid;
    }

    public String getCommunityname() {
        return communityname;
    }

    public void setCommunityname(String communityname) {
        this.communityname = communityname;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getHouseDetail() {
        return houseDetail;
    }

    public void setHouseDetail(String houseDetail) {
        this.houseDetail = houseDetail;
    }

    public String getCppId() {
        return cppId;
    }

    public void setCppId(String cppId) {
        this.cppId = cppId;
    }

    public String getCk() {
        return ck;
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public String getGcyj() {
        return gcyj;
    }

    public void setGcyj(String gcyj) {
        this.gcyj = gcyj;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
