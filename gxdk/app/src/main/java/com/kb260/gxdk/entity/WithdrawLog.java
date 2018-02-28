package com.kb260.gxdk.entity;

/**
 * @author KB260
 *         Created on  2017/11/27
 */

public class WithdrawLog {

    /**
     * id : 1
     * userid : 21
     * flag : 0
     * createtime : 2017-11-21 13:57:32
     * realname : null
     * bankcardno : 260
     * bankname : 260
     * scoretype : 1
     * scoreable : null
     * score : 1.0
     * reserve : 1511243852259
     * status : 0
     */

    private int id;
    private int userid;
    private String flag;
    private String createtime;
    private Object realname;
    private String bankcardno;
    private String bankname;
    private String scoretype;
    private Object scoreable;
    private double score;
    private String reserve;
    private String status;

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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Object getRealname() {
        return realname;
    }

    public void setRealname(Object realname) {
        this.realname = realname;
    }

    public String getBankcardno() {
        return bankcardno;
    }

    public void setBankcardno(String bankcardno) {
        this.bankcardno = bankcardno;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getScoretype() {
        if (scoretype!= null){
            switch (scoretype){
                case "1":
                    return "充值积分";
                case "2":
                    return "提现积分";
                default:
                    break;
            }
        }
        return "";
    }

    public void setScoretype(String scoretype) {
        this.scoretype = scoretype;
    }

    public Object getScoreable() {
        return scoreable;
    }

    public void setScoreable(Object scoreable) {
        this.scoreable = scoreable;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public String getStatus() {
        if (status !=null){
            switch (status){
                case "0":
                    return "审核中";
                case "1":
                    return "已拒绝";
                case "2":
                    return "已通过";
                default:
                    return "";
            }
        }
        return "";
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
