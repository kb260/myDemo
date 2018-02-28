package com.kb260.gxdk.entity;

import java.io.Serializable;

/**
 * @author KB260
 *         Created on  2017/11/29
 */

public class CharacterApplicationData implements Serializable{
    private String realname;
    private String telphone;
    private String company;
    private String companyaddress;
    private String branchname;
    private String bankaddress;
    private String bank;
    private String status;
    private int id;
    private int type;
    private boolean isChange;

    public CharacterApplicationData(String realname, String telphone, String company, String companyaddress,
                                    String branchname, String bankaddress, String bank, String status, int id,
                                    int type,boolean isChange) {
        this.realname = realname;
        this.telphone = telphone;
        this.company = company;
        this.companyaddress = companyaddress;
        this.branchname = branchname;
        this.bankaddress = bankaddress;
        this.bank = bank;
        this.status = status;
        this.id = id;
        this.type = type;
        this.isChange = isChange;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }
}
