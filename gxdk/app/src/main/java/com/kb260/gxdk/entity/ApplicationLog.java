package com.kb260.gxdk.entity;

import java.io.Serializable;

/**
 * @author KB260
 *         Created on  2017/11/24
 */

public class ApplicationLog implements Serializable{


    /**
     * id : 39
     * userid : 37
     * createtime : 2017-11-29 10:27:38
     * realname : 宝宝
     * idcard : null
     * bank : null
     * branchname : null
     * bankaddress : null
     * company : 刚刚好
     * companyaddress : 该喝喝
     * flag : 0
     * type : 0
     * link : 14727002600
     * telphone : 14727002600
     * reverse : null
     * applyflag : 0
     * status : 1
     * appuser : null
     */

    private int id;
    private int userid;
    private String createtime;
    private String realname;
    private Object idcard;
    private String bank;
    private String branchname;
    private String bankaddress;
    private String company;
    private String companyaddress;
    private String flag;
    private String type;
    private String link;
    private String telphone;
    private String reverse;
    private String applyflag;
    private String status;
    private Object appuser;

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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Object getIdcard() {
        return idcard;
    }

    public void setIdcard(Object idcard) {
        this.idcard = idcard;
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

    public String getBankaddress() {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress) {
        this.bankaddress = bankaddress;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getReverse() {
        return reverse;
    }

    public void setReverse(String reverse) {
        this.reverse = reverse;
    }

    public String getApplyflag() {
        return applyflag;
    }

    public void setApplyflag(String applyflag) {
        this.applyflag = applyflag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getAppuser() {
        return appuser;
    }

    public void setAppuser(Object appuser) {
        this.appuser = appuser;
    }
}
