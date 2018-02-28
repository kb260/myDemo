package com.kb260.gxdk.entity;

import java.io.Serializable;

/**
 * @author KB260
 *         Created on  2017/11/7
 */

public class MortgageSecond implements Serializable{
    private String applicationMoney;
    private String rate;
    private String minRate;
    private String loanTime;
    private String haveCompany;
    private String identity;
    private String proportion;
    private String haveSite;
    private String haveOverdue;
    private String overdue;
    private String overdueTime;
    private String overdueMoney;
    private String other;
    private String paytime;
    private String monthpay;

    public MortgageSecond(String applicationMoney, String rate, String minRate, String loanTime, String haveCompany, String identity, String proportion, String haveSite, String haveOverdue, String overdue, String overdueTime, String overdueMoney, String other,String paytime,String monthpay) {
        this.applicationMoney = applicationMoney;
        this.minRate = minRate;
        this.rate = rate;
        this.loanTime = loanTime;
        this.haveCompany = haveCompany;
        this.identity = identity;
        this.proportion = proportion;
        this.haveSite = haveSite;
        this.haveOverdue = haveOverdue;
        this.overdue = overdue;
        this.overdueTime = overdueTime;
        this.overdueMoney = overdueMoney;
        this.other = other;
        this.paytime = paytime;
        this.monthpay = monthpay;
    }

    public String getMonthpay() {
        return monthpay;
    }

    public void setMonthpay(String monthpay) {
        this.monthpay = monthpay;
    }

    public String getPaytime() {
        return paytime;
    }

    public void setPaytime(String paytime) {
        this.paytime = paytime;
    }

    public String getMinRate() {
        return minRate;
    }

    public void setMinRate(String minRate) {
        this.minRate = minRate;
    }

    public String getApplicationMoney() {
        return applicationMoney;
    }

    public void setApplicationMoney(String applicationMoney) {
        this.applicationMoney = applicationMoney;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public String getHaveCompany() {
        switch (haveCompany){
            case "是":
                return "1";
            case "否":
                return "0";
            default:
                return haveCompany;
        }
    }

    public void setHaveCompany(String haveCompany) {
        this.haveCompany = haveCompany;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getHaveSite() {
        return haveSite;
    }

    public void setHaveSite(String haveSite) {
        this.haveSite = haveSite;
    }

    public String getHaveOverdue() {
        switch (haveOverdue){
            case "是":
                return "1";
            case "否":
                return "0";
            default:
                return haveOverdue;
        }
    }

    public void setHaveOverdue(String haveOverdue) {
        this.haveOverdue = haveOverdue;
    }

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }

    public String getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(String overdueTime) {
        this.overdueTime = overdueTime;
    }

    public String getOverdueMoney() {
        return overdueMoney;
    }

    public void setOverdueMoney(String overdueMoney) {
        this.overdueMoney = overdueMoney;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
