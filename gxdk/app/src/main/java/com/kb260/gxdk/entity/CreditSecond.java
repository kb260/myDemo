package com.kb260.gxdk.entity;

import java.io.Serializable;

/**
 * @author KB260
 *         Created on  2017/11/15
 */

public class CreditSecond implements Serializable{
    private String workTime;
    private String wage;
    private String wageType;
    private String providentFund;
    private String unilateralDeposit;
    private String depositCity;
    private String depositTime;
    private String policy;
    private String policyCompany;
    private String policyMouth;
    private String policyYear;
    private String other;

    public CreditSecond(String workTime, String wage, String wageType, String providentFund, String unilateralDeposit, String depositCity, String depositTime, String policy, String policyCompany, String policyMouth, String policyYear, String other) {
        this.workTime = workTime;
        this.wage = wage;
        this.wageType = wageType;
        this.providentFund = providentFund;
        this.unilateralDeposit = unilateralDeposit;
        this.depositCity = depositCity;
        this.depositTime = depositTime;
        this.policy = policy;
        this.policyCompany = policyCompany;
        this.policyMouth = policyMouth;
        this.policyYear = policyYear;
        this.other = other;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    public String getWageType() {
        return wageType;
    }

    public void setWageType(String wageType) {
        this.wageType = wageType;
    }

    public String getProvidentFund() {
        return providentFund;
    }

    public void setProvidentFund(String providentFund) {
        this.providentFund = providentFund;
    }

    public String getUnilateralDeposit() {
        return unilateralDeposit;
    }

    public void setUnilateralDeposit(String unilateralDeposit) {
        this.unilateralDeposit = unilateralDeposit;
    }

    public String getDepositCity() {
        return depositCity;
    }

    public void setDepositCity(String depositCity) {
        this.depositCity = depositCity;
    }

    public String getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(String depositTime) {
        this.depositTime = depositTime;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getPolicyCompany() {
        return policyCompany;
    }

    public void setPolicyCompany(String policyCompany) {
        this.policyCompany = policyCompany;
    }

    public String getPolicyMouth() {
        return policyMouth;
    }

    public void setPolicyMouth(String policyMouth) {
        this.policyMouth = policyMouth;
    }

    public String getPolicyYear() {
        return policyYear;
    }

    public void setPolicyYear(String policyYear) {
        this.policyYear = policyYear;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
