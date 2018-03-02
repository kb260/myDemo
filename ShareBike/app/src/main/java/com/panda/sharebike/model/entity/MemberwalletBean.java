package com.panda.sharebike.model.entity;

import java.io.Serializable;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/07/26
 * desc   :
 * version: 1.0
 */
public class MemberwalletBean implements Serializable {


    /**
     * memberDeposit : 0
     * memberBeenz : 0
     * depositConfig : 99
     */

    private double memberDeposit;
    private double memberBeenz;
    private double depositConfig;

    public double getMemberDeposit() {
        return memberDeposit;
    }

    public void setMemberDeposit(int memberDeposit) {
        this.memberDeposit = memberDeposit;
    }

    public double getMemberBeenz() {
        return memberBeenz;
    }

    public void setMemberBeenz(int memberBeenz) {
        this.memberBeenz = memberBeenz;
    }

    public double getDepositConfig() {
        return depositConfig;
    }

    public void setDepositConfig(int depositConfig) {
        this.depositConfig = depositConfig;
    }
}
