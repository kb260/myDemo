package com.kb260.gxdk.entity;

import java.io.Serializable;

/**
 * @author KB260
 *         Created on  2017/11/8
 */

public class CarLoanSecond implements Serializable{
    private String cxz;
    private String ajyh;
    private String sywk;
    private String xydz;
    private String sqje;
    private String minJsll;
    private String jsll;
    private String dkqx;
    private String sfyyq;
    private String yqx;
    private String yqsj;
    private String je;
    private String qtzcsm;
    private String paytime;
    private String monthpay;

    public CarLoanSecond(String cxz, String ajyh, String sywk, String xydz, String sqje, String minJsll, String jsll, String dkqx, String sfyyq, String yqx, String yqsj, String je, String qtzcsm,String paytime,String monthpay) {
        this.cxz = cxz;
        this.ajyh = ajyh;
        this.sywk = sywk;
        this.xydz = xydz;
        this.sqje = sqje;
        this.minJsll = minJsll;
        this.jsll = jsll;
        this.dkqx = dkqx;
        this.sfyyq = sfyyq;
        this.yqx = yqx;
        this.yqsj = yqsj;
        this.je = je;
        this.qtzcsm = qtzcsm;
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

    public String getCxz() {
        return cxz;
    }

    public void setCxz(String cxz) {
        this.cxz = cxz;
    }

    public String getAjyh() {
        return ajyh;
    }

    public void setAjyh(String ajyh) {
        this.ajyh = ajyh;
    }

    public String getSywk() {
        return sywk;
    }

    public void setSywk(String sywk) {
        this.sywk = sywk;
    }

    public String getXydz() {
        return xydz;
    }

    public void setXydz(String xydz) {
        this.xydz = xydz;
    }

    public String getSqje() {
        return sqje;
    }

    public void setSqje(String sqje) {
        this.sqje = sqje;
    }

    public String getMinJsll() {
        return minJsll;
    }

    public void setMinJsll(String minJsll) {
        this.minJsll = minJsll;
    }

    public String getJsll() {
        return jsll;
    }

    public void setJsll(String jsll) {
        this.jsll = jsll;
    }

    public String getDkqx() {
        return dkqx;
    }

    public void setDkqx(String dkqx) {
        this.dkqx = dkqx;
    }

    public String getSfyyq() {
        return sfyyq;
    }

    public void setSfyyq(String sfyyq) {
        this.sfyyq = sfyyq;
    }

    public String getYqx() {
        return yqx;
    }

    public void setYqx(String yqx) {
        this.yqx = yqx;
    }

    public String getYqsj() {
        return yqsj;
    }

    public void setYqsj(String yqsj) {
        this.yqsj = yqsj;
    }

    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }

    public String getQtzcsm() {
        return qtzcsm;
    }

    public void setQtzcsm(String qtzcsm) {
        this.qtzcsm = qtzcsm;
    }
}
