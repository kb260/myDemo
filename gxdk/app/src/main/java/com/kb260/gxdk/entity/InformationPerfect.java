package com.kb260.gxdk.entity;

/**
 * @author KB260
 *         Created on  2017/11/7
 */

public class InformationPerfect {
    private String idCardUrl;
    private String marryUrl;
    private String accountBookUrl;
    private String deedUrl;
    private String businessLicenseUrl;
    private String credit;
    private String charter;
    private String informationSupplement;

    public InformationPerfect(String idCardUrl, String marryUrl, String accountBookUrl, String deedUrl, String businessLicenseUrl, String credit, String charter, String informationSupplement) {
        this.idCardUrl = idCardUrl;
        this.marryUrl = marryUrl;
        this.accountBookUrl = accountBookUrl;
        this.deedUrl = deedUrl;
        this.businessLicenseUrl = businessLicenseUrl;
        this.credit = credit;
        this.charter = charter;
        this.informationSupplement = informationSupplement;
    }

    public String getIdCardUrl() {
        return idCardUrl;
    }

    public void setIdCardUrl(String idCardUrl) {
        this.idCardUrl = idCardUrl;
    }

    public String getMarryUrl() {
        return marryUrl;
    }

    public void setMarryUrl(String marryUrl) {
        this.marryUrl = marryUrl;
    }

    public String getAccountBookUrl() {
        return accountBookUrl;
    }

    public void setAccountBookUrl(String accountBookUrl) {
        this.accountBookUrl = accountBookUrl;
    }

    public String getDeedUrl() {
        return deedUrl;
    }

    public void setDeedUrl(String deedUrl) {
        this.deedUrl = deedUrl;
    }

    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    public void setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCharter() {
        return charter;
    }

    public void setCharter(String charter) {
        this.charter = charter;
    }

    public String getInformationSupplement() {
        return informationSupplement;
    }

    public void setInformationSupplement(String informationSupplement) {
        this.informationSupplement = informationSupplement;
    }
}
