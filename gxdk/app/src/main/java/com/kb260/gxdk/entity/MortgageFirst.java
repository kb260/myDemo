package com.kb260.gxdk.entity;

import java.io.Serializable;

/**
 * @author KB260
 *         Created on  2017/11/7
 */

public class MortgageFirst implements Serializable{
    private String name;
    private String age;
    private String sfz;
    private String phone;
    private String marry;
    private String spouseName;
    private String spousePhone;
    private String address;
    private String houseAddress;
    private String houseArea;
    private String houseNature;
    private String houseStatus;
    private String province;
    private String city;
    private String zone;
    private String bank;
    private String sywk;
    private String sfdz;
    private String houseAge;
    private String communityid;
    private String communityname;
    private String workplace;
    private String houseDetail;

    public MortgageFirst(String name, String age, String sfz, String phone, String marry, String spouseName, String spousePhone, String address, String houseAddress, String houseArea, String houseNature, String houseStatus, String province, String city, String zone, String bank, String sywk, String sfdz, String houseAge, String communityid, String communityname, String workplace, String houseDetail) {
        this.name = name;
        this.age = age;
        this.sfz = sfz;
        this.phone = phone;
        this.marry = marry;
        this.spouseName = spouseName;
        this.spousePhone = spousePhone;
        this.address = address;
        this.houseAddress = houseAddress;
        this.houseArea = houseArea;
        this.houseNature = houseNature;
        this.houseStatus = houseStatus;
        this.province = province;
        this.city = city;
        this.zone = zone;
        this.bank = bank;
        this.sywk = sywk;
        this.sfdz = sfdz;
        this.houseAge = houseAge;
        this.communityid = communityid;
        this.communityname = communityname;
        this.workplace = workplace;
        this.houseDetail = houseDetail;
    }

    public MortgageFirst(String name, String age, String sfz, String phone, String marry, String spouseName,
                         String spousePhone, String address, String houseAddress, String houseArea,
                         String houseNature, String houseStatus, String province, String city, String zone,
                         String bank, String sywk, String sfdz, String houseAge) {
        this.name = name;
        this.age = age;
        this.sfz = sfz;
        this.phone = phone;
        this.marry = marry;
        this.spouseName = spouseName;
        this.spousePhone = spousePhone;
        this.address = address;
        this.houseAddress = houseAddress;
        this.houseArea = houseArea;
        this.houseNature = houseNature;
        this.houseStatus = houseStatus;
        this.province = province;
        this.city = city;
        this.zone = zone;
        this.bank = bank;
        this.sywk = sywk;
        this.sfdz = sfdz;
        this.houseAge = houseAge;
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

    public String getHouseAge() {
        return houseAge;
    }

    public void setHouseAge(String houseAge) {
        this.houseAge = houseAge;
    }

    public String getSfdz() {
        return sfdz;
    }

    public void setSfdz(String sfdz) {
        this.sfdz = sfdz;
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
        if (marry.equals("已婚")){
            return "1";
        }else if (marry.equals("未婚")){
            return "0";
        }else {
            return marry;
        }
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
