package com.kb260.gxdk.model;

/**
 * Created by kb260 on 2017/9/26.
 * Email: work260@outlook.com
 */

public class Card {


    /**
     * id : 14
     * userid : 17
     * flag : 0
     * createtime : 2017-11-17 17:01:30
     * realname : 加急件
     * bankcardno : 123556
     * bankname : 55566
     * banknamebranch : 老两口
     * telphone : 可口可乐
     * reserve : null
     */

    private int id;
    private int userid;
    private String flag;
    private String createtime;
    private String realname;
    private String bankcardno;
    private String bankname;
    private String banknamebranch;
    private String telphone;
    private Object reserve;

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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
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

    public String getBanknamebranch() {
        return banknamebranch;
    }

    public void setBanknamebranch(String banknamebranch) {
        this.banknamebranch = banknamebranch;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public Object getReserve() {
        return reserve;
    }

    public void setReserve(Object reserve) {
        this.reserve = reserve;
    }
}
