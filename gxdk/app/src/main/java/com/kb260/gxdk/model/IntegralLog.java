package com.kb260.gxdk.model;

/**
 * Created by kb260 on 2017/9/29.
 * Email: work260@outlook.com
 */

public class IntegralLog {

    /**
     * id : 342
     * userid : 131
     * flag : 0
     * createtime : 2017-12-19 18:53:08
     * score : 10000.0
     * reserve : 积分充值
     * type : 1
     */

    private int id;
    private int userid;
    private String flag;
    private String createtime;
    private double score;
    private String reserve;
    private String type;

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

    public String getScore() {
        return String.valueOf(score);
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

    public String getType() {
        if (type != null){
            switch (type){
                case "1":
                    return "充值";
                default:
                    break;
            }
        }
        return "";
    }

    public void setType(String type) {
        this.type = type;
    }
}
