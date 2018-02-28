package com.kb260.gxdk.entity;

import java.util.List;

/**
 * @author KB260
 *         Created on  2017/11/28
 */

public class MyTeamOne {

    /**
     * size : 3
     * data : [{"id":25,"username":"13957834731","telphone":"13957834731","createtime":"2017-11-21 14:44:36","password":"200820e3227815ed1756a6b531e7e0d2","parentid":"22","nick":"啊啊啊","realname":"何入万","picture":"https://ykd2017.oss-cn-hangzhou.aliyuncs.com/IMG_0005.JPG","idcard":null,"bank":"中国人民银行","branchname":"城东支行","bankaddress":"浙江省杭州市萧山区","company":null,"companyaddress":null,"token":null,"regisonid":null,"flag":"0","type":"2","sign":"0","curentaddrress":null,"province":null,"city":null,"zone":null,"link":null,"ewm":null,"yqm":null,"address":null,"reverse":null,"applyflag":"1","status":"2"},{"id":24,"username":"13125162927","telphone":"13125162927","createtime":"2017-11-20 10:37:18","password":"e10adc3949ba59abbe56e057f20f883e","parentid":"22","nick":"，","realname":"1155","picture":"http://ykd2017.oss-cn-hangzhou.aliyuncs.com/android_gxdk_1511145553348.jpg","idcard":null,"bank":"就扣","branchname":"就来来来","bankaddress":"阿卡丽","company":null,"companyaddress":null,"token":null,"regisonid":null,"flag":"0","type":"0","sign":"1","curentaddrress":null,"province":"北京市","city":"北京市","zone":"东城区","link":null,"ewm":null,"yqm":null,"address":null,"reverse":null,"applyflag":"0","status":"2"},{"id":23,"username":"14727002600","telphone":"14727002600","createtime":"2017-11-20 09:48:35","password":"e10adc3949ba59abbe56e057f20f883e","parentid":"22","nick":"史上","realname":"也很好","picture":"http://ykd2017.oss-cn-hangzhou.aliyuncs.com/android_gxdk_1511148642245.jpg","idcard":null,"bank":null,"branchname":null,"bankaddress":null,"company":"发广告","companyaddress":"哟哟哟","token":null,"regisonid":null,"flag":"0","type":"0","sign":"0","curentaddrress":null,"province":"浙江省","city":"杭州市","zone":"萧山区","link":null,"ewm":null,"yqm":null,"address":null,"reverse":null,"applyflag":"0","status":"1"}]
     */

    private int size;
    private String nick;
    private List<DataBean> data;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 25
         * username : 13957834731
         * telphone : 13957834731
         * createtime : 2017-11-21 14:44:36
         * password : 200820e3227815ed1756a6b531e7e0d2
         * parentid : 22
         * nick : 啊啊啊
         * realname : 何入万
         * picture : https://ykd2017.oss-cn-hangzhou.aliyuncs.com/IMG_0005.JPG
         * idcard : null
         * bank : 中国人民银行
         * branchname : 城东支行
         * bankaddress : 浙江省杭州市萧山区
         * company : null
         * companyaddress : null
         * token : null
         * regisonid : null
         * flag : 0
         * type : 2
         * sign : 0
         * curentaddrress : null
         * province : null
         * city : null
         * zone : null
         * link : null
         * ewm : null
         * yqm : null
         * address : null
         * reverse : null
         * applyflag : 1
         * status : 2
         */

        private int id;
        private String username;
        private String telphone;
        private String createtime;
        private String password;
        private String parentid;
        private String nick;
        private String realname;
        private String picture;
        private Object idcard;
        private String bank;
        private String branchname;
        private String bankaddress;
        private Object company;
        private Object companyaddress;
        private Object token;
        private Object regisonid;
        private String flag;
        private String type;
        private String sign;
        private Object curentaddrress;
        private Object province;
        private Object city;
        private Object zone;
        private Object link;
        private Object ewm;
        private Object yqm;
        private Object address;
        private Object reverse;
        private String applyflag;
        private String status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTelphone() {
            if (telphone != null){
                if (telphone.length() == 11){
                    telphone = telphone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
                }
                return telphone;
            }
            return telphone;
        }

        public void setTelphone(String telphone) {
            this.telphone = telphone;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public Object getIdcard() {
            return idcard;
        }

        public void setIdcard(Object idcard) {
            this.idcard = idcard;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
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

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public Object getCompanyaddress() {
            return companyaddress;
        }

        public void setCompanyaddress(Object companyaddress) {
            this.companyaddress = companyaddress;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public Object getRegisonid() {
            return regisonid;
        }

        public void setRegisonid(Object regisonid) {
            this.regisonid = regisonid;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public Object getCurentaddrress() {
            return curentaddrress;
        }

        public void setCurentaddrress(Object curentaddrress) {
            this.curentaddrress = curentaddrress;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getZone() {
            return zone;
        }

        public void setZone(Object zone) {
            this.zone = zone;
        }

        public Object getLink() {
            return link;
        }

        public void setLink(Object link) {
            this.link = link;
        }

        public Object getEwm() {
            return ewm;
        }

        public void setEwm(Object ewm) {
            this.ewm = ewm;
        }

        public Object getYqm() {
            return yqm;
        }

        public void setYqm(Object yqm) {
            this.yqm = yqm;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getReverse() {
            return reverse;
        }

        public void setReverse(Object reverse) {
            this.reverse = reverse;
        }

        public String getApplyflag() {
            return applyflag;
        }

        public void setApplyflag(String applyflag) {
            this.applyflag = applyflag;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
