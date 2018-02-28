package com.kb260.gxdk.entity;

import com.kb260.gxdk.model.IntegralDetail;

import java.util.List;

/**
 * @author KB260
 *         Created on  2017/11/13
 */

public class Score {


    /**
     * shopscore : 0.0
     * rewardscore : 0.0
     * chargescore : 0.0
     * detail : [{"id":1,"userid":23,"flag":"0","createtime":"334","score":12,"reserve":"积分充值","type":"1"}]
     */

    private double shopscore;
    private double rewardscore;
    private double chargescore;
    private String picture;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    private List<IntegralDetail.DetailBean> detail;

    public double getShopscore() {
        return shopscore;
    }

    public void setShopscore(double shopscore) {
        this.shopscore = shopscore;
    }

    public double getRewardscore() {
        return rewardscore;
    }

    public void setRewardscore(double rewardscore) {
        this.rewardscore = rewardscore;
    }

    public double getChargescore() {
        return chargescore;
    }

    public void setChargescore(double chargescore) {
        this.chargescore = chargescore;
    }

    public List<IntegralDetail.DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<IntegralDetail.DetailBean> detail) {
        this.detail = detail;
    }


}
