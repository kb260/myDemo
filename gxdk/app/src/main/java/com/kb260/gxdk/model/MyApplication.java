package com.kb260.gxdk.model;

import java.io.Serializable;

/**
 * @author  KB260
 * Created on  2017/11/10
 */

public class MyApplication implements Serializable{
    /**
     * id : 11
     * userid : 15
     * createtime : 2017-11-15 10:06:05
     * flag : 0
     * realname : 罢了
     * age : 3岁
     * idcard : 655666
     * telphone : 25566
     * address : 北京市 北京市 东城区
     * spousename : null
     * spousetel : null
     * roomaddress : null
     * area : null
     * nature : null
     * current : null
     * count : 768.0
     * rate : 55.0
     * maxrate : 66.0
     * descore : null
     * balscore : null
     * loantime : 4个月
     * position : null
     * ratio : null
     * dueoption : null
     * duetime : null
     * amount : null
     * evaluatingprice : null
     * goodreliability : null
     * instruction : 郑谊庄
     * status : 0
     * type : 2
     * isplace : null
     * sign : 未婚
     * iscompany : null
     * isoverdue : 否
     * cardphoto : com.kb260.gxdk.entity.UploadPic@eddb382,com.kb260.gxdk.entity.UploadPic@9a33a93
     * marriagephoto : com.kb260.gxdk.entity.UploadPic@4909134
     * residencephoto : com.kb260.gxdk.entity.UploadPic@ec1345e
     * threephoto : com.kb260.gxdk.entity.UploadPic@dc249e
     * license : com.kb260.gxdk.entity.UploadPic@6ba9a78
     * creditphoto : null
     * association : null
     * replenishment : null
     * credibility : null
     * carframe : 3666
     * carbrand : 奥迪
     * carno : 浙A
     * carage : 3年
     * distance : 2万公里
     * ispay : null
     * mortgagebank : null
     * expenditure : null
     * surpius : null
     * iscar : null
     * isroom : null
     * workplace : null
     * industry : null
     * occupation : null
     * currentsalary : null
     * worktime : null
     * payform : null
     * isfund : null
     * unilateralpay : null
     * paycity : null
     * limittime : null
     * isinsurancepolicy : null
     * insuredcompany : null
     * monthpay : null
     * yearpay : null
     * province : 北京市
     * city : 北京市
     * zone : 东城区
     * times : null
     * reserve : null
     * lasttrial : null
     * firsttrial : null
     * appuser : null
     */

    private int id;
    private int userid;
    private String createtime;
    private String flag;
    private String realname;
    private String age;
    private String idcard;
    private String telphone;
    private String address;
    private String spousename;
    private String spousetel;
    private String roomaddress;
    private String area;
    private String nature;
    private String current;
    private double count;
    private double rate;
    private double maxrate;
    private String descore;
    private String balscore;
    private String loantime;
    private String position;
    private String ratio;
    private String dueoption;
    private String duetime;
    private String amount;
    private String evaluatingprice;
    private String goodreliability;
    private String instruction;
    private String status;
    private String type;
    private String isplace;
    private String sign;
    private String iscompany;
    private String isoverdue;
    private String cardphoto;
    private String marriagephoto;
    private String residencephoto;
    private String threephoto;
    private String license;
    private String creditphoto;
    private String association;
    private String replenishment;
    private String credibility;
    private String carframe;
    private String carbrand;
    private String carno;
    private String carage;
    private String distance;
    private String ispay;
    private String mortgagebank;
    private String expenditure;
    private String surpius;
    private String iscar;
    private String isroom;
    private String workplace;
    private String industry;
    private String occupation;
    private String currentsalary;
    private String worktime;
    private String payform;
    private String isfund;
    private String unilateralpay;
    private String paycity;
    private String limittime;
    private String isinsurancepolicy;
    private String insuredcompany;
    private String monthpay;
    private String yearpay;
    private String province;
    private String city;
    private String zone;
    private String times;
    private String reserve;
    private LasttrialBean lasttrial;
    private FirsttrialBean firsttrial;
    private AppuserBean appuser;

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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAge() {
        return age+"岁";
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpousename() {
        return spousename;
    }

    public void setSpousename(String spousename) {
        this.spousename = spousename;
    }

    public String getSpousetel() {
        return spousetel;
    }

    public void setSpousetel(String spousetel) {
        this.spousetel = spousetel;
    }

    public String getRoomaddress() {
        return roomaddress;
    }

    public void setRoomaddress(String roomaddress) {
        this.roomaddress = roomaddress;
    }

    public String getArea() {
        return area+"平方米";
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getCount() {
        return String.valueOf(count)+"万";
    }

    public Double getCountDouble() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMaxrate() {
        return maxrate;
    }

    public void setMaxrate(double maxrate) {
        this.maxrate = maxrate;
    }

    public String getDescore() {
        return descore;
    }

    public void setDescore(String descore) {
        this.descore = descore;
    }

    public String getBalscore() {
        return balscore;
    }

    public void setBalscore(String balscore) {
        this.balscore = balscore;
    }

    public String getLoantime() {
        return loantime+"月";
    }

    public void setLoantime(String loantime) {
        this.loantime = loantime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRatio() {
        if (ratio != null){
            if(ratio.contains("%")){
                return ratio;
            }else {
                return ratio+"%";
            }
        }else {
            return "无数据";
        }
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String getDueoption() {
        return dueoption;
    }

    public void setDueoption(String dueoption) {
        this.dueoption = dueoption;
    }

    public String getDuetime() {
        return duetime;
    }

    public void setDuetime(String duetime) {
        this.duetime = duetime;
    }

    public String getAmount() {
        return amount+"元";
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEvaluatingprice() {
        return evaluatingprice;
    }

    public void setEvaluatingprice(String evaluatingprice) {
        this.evaluatingprice = evaluatingprice;
    }

    public String getGoodreliability() {
        return goodreliability;
    }

    public void setGoodreliability(String goodreliability) {
        this.goodreliability = goodreliability;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getStatus() {
        if (status !=null){
            return status;
        }
        return "";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsplace() {
        return isplace;
    }

    public void setIsplace(String isplace) {
        this.isplace = isplace;
    }

    public String getSign() {
        switch (sign){
            case "0":
                return "未婚";
            case "1":
                return "已婚";
            default:
                return sign;
        }
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getIscompany() {
        if (iscompany != null){
            switch (iscompany){
                case "0":
                    return "否";
                case "1":
                    return "是";
                default:
                    return iscompany;
            }
        }
        return "";
    }

    public void setIscompany(String iscompany) {
        this.iscompany = iscompany;
    }

    public String getIsoverdue() {
        switch (isoverdue){
            case "0":
                return "否";
            case "1":
                return "是";
            default:
                return isoverdue;
        }
    }

    public void setIsoverdue(String isoverdue) {
        this.isoverdue = isoverdue;
    }

    public String getCardphoto() {
        return cardphoto;
    }

    public void setCardphoto(String cardphoto) {
        this.cardphoto = cardphoto;
    }

    public String getMarriagephoto() {
        return marriagephoto;
    }

    public void setMarriagephoto(String marriagephoto) {
        this.marriagephoto = marriagephoto;
    }

    public String getResidencephoto() {
        return residencephoto;
    }

    public void setResidencephoto(String residencephoto) {
        this.residencephoto = residencephoto;
    }

    public String getThreephoto() {
        return threephoto;
    }

    public void setThreephoto(String threephoto) {
        this.threephoto = threephoto;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCreditphoto() {
        return creditphoto;
    }

    public void setCreditphoto(String creditphoto) {
        this.creditphoto = creditphoto;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getReplenishment() {
        return replenishment;
    }

    public void setReplenishment(String replenishment) {
        this.replenishment = replenishment;
    }

    public String getCredibility() {
        return credibility;
    }

    public void setCredibility(String credibility) {
        this.credibility = credibility;
    }

    public String getCarframe() {
        return carframe;
    }

    public void setCarframe(String carframe) {
        this.carframe = carframe;
    }

    public String getCarbrand() {
        return carbrand;
    }

    public void setCarbrand(String carbrand) {
        this.carbrand = carbrand;
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public String getCarage() {
        return carage+"年";
    }

    public void setCarage(String carage) {
        this.carage = carage;
    }

    public String getDistance() {
        return distance+"万公里";
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getIspay() {
        if (ispay != null){
            return ispay;
        }
        return "";
    }

    public void setIspay(String ispay) {
        this.ispay = ispay;
    }

    public String getMortgagebank() {
        return mortgagebank;
    }

    public void setMortgagebank(String mortgagebank) {
        this.mortgagebank = mortgagebank;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
    }

    public String getSurpius() {
        return surpius+"万";
    }

    public void setSurpius(String surpius) {
        this.surpius = surpius;
    }

    public String getIscar() {
        return iscar;
    }

    public void setIscar(String iscar) {
        this.iscar = iscar;
    }

    public String getIsroom() {
        return isroom;
    }

    public void setIsroom(String isroom) {
        this.isroom = isroom;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getIndustry() {
        if (industry != null){
            switch (industry){
                case "1":
                    return "优秀";
                case "2":
                    return "一般";
                case "3":
                    return "较差";
                default:
                    return "";
            }
        }else {
            return "";
        }
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCurrentsalary() {
        return currentsalary;
    }

    public void setCurrentsalary(String currentsalary) {
        this.currentsalary = currentsalary;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getPayform() {
        return payform;
    }

    public void setPayform(String payform) {
        this.payform = payform;
    }

    public String getIsfund() {
        return isfund;
    }

    public void setIsfund(String isfund) {
        this.isfund = isfund;
    }

    public String getUnilateralpay() {
        return unilateralpay;
    }

    public void setUnilateralpay(String unilateralpay) {
        this.unilateralpay = unilateralpay;
    }

    public String getPaycity() {
        return paycity+"万元";
    }

    public void setPaycity(String paycity) {
        this.paycity = paycity;
    }

    public String getLimittime() {
        return limittime;
    }

    public void setLimittime(String limittime) {
        this.limittime = limittime;
    }

    public String getIsinsurancepolicy() {
        return isinsurancepolicy;
    }

    public void setIsinsurancepolicy(String isinsurancepolicy) {
        this.isinsurancepolicy = isinsurancepolicy;
    }

    public String getInsuredcompany() {
        return insuredcompany;
    }

    public void setInsuredcompany(String insuredcompany) {
        this.insuredcompany = insuredcompany;
    }

    public String getMonthpay() {
        return monthpay;
    }

    public void setMonthpay(String monthpay) {
        this.monthpay = monthpay;
    }

    public String getYearpay() {
        return yearpay;
    }

    public void setYearpay(String yearpay) {
        this.yearpay = yearpay;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getTimes() {
        return times+"年";
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getReserve() {
        if (reserve != null){
            return reserve;
        }
        return "";
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public LasttrialBean getLasttrial() {
        return lasttrial;
    }

    public void setLasttrial(LasttrialBean lasttrial) {
        this.lasttrial = lasttrial;
    }

    public FirsttrialBean getFirsttrial() {
        return firsttrial;
    }

    public void setFirsttrial(FirsttrialBean firsttrial) {
        this.firsttrial = firsttrial;
    }

    public AppuserBean getAppuser() {
        return appuser;
    }

    public void setAppuser(AppuserBean appuser) {
        this.appuser = appuser;
    }

    public static class FirsttrialBean implements Serializable{
        /**
         * id : 6
         * userid : 15
         * orderid : 10
         * flag : 0
         * createtime : 2017-11-16 14:38:04
         * count : 2.0
         * rate : 15
         * maxrate : 15
         * loanlimit : 15
         * loantype : 15
         * loantime : 15
         * lastresult : 15
         * reserve : 2017-12-01 10:30:00
         * type : 1
         * status : 0
         * lendingtime : null
         */

        private int id;
        private int userid;
        private int orderid;
        private String flag;
        private String createtime;
        private double count;
        private String rate;
        private String maxrate;
        private String loanlimit;
        private String loantype;
        private String loantime;
        private int lastresult;
        private String reserve;
        private String type;
        private String status;
        private String lendingtime;
        private String firstpicture;
        private String remark;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getFirstpicture() {
            return firstpicture;
        }

        public void setFirstpicture(String firstpicture) {
            this.firstpicture = firstpicture;
        }

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

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
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

        public String getCount() {
            return String.valueOf(count)+"万";
        }
        public Double getCountDouble() {
            return count;
        }

        public void setCount(double count) {
            this.count = count;
        }

        public String getRate() {
            return rate+"厘";
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getMaxrate() {
            return maxrate+"厘";
        }

        public void setMaxrate(String maxrate) {
            this.maxrate = maxrate;
        }

        public String getLoanlimit() {
            return loanlimit+"月";
        }

        public void setLoanlimit(String loanlimit) {
            this.loanlimit = loanlimit;
        }

        public String getLoantype() {
            return loantype;
        }

        public void setLoantype(String loantype) {
            this.loantype = loantype;
        }

        public String getLoantime() {
            return loantime;
        }

        public void setLoantime(String loantime) {
            this.loantime = loantime;
        }

        public String getLastresult() {
            return String.valueOf(lastresult)+"天";
        }

        public void setLastresult(int lastresult) {
            this.lastresult = lastresult;
        }

        public String getReserve() {
            return reserve;
        }

        public void setReserve(String reserve) {
            this.reserve = reserve;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLendingtime() {
            return lendingtime;
        }

        public void setLendingtime(String lendingtime) {
            this.lendingtime = lendingtime;
        }
    }

    public static class LasttrialBean implements Serializable{
        /**
         * id : 7
         * userid : 15
         * orderid : null
         * flag : 0
         * createtime : 2017-11-16 15:01:00
         * count : 34.0
         * rate : 56
         * maxrate : 5
         * loanlimit : 2
         * loantype : 一年一转
         * loantime : 2
         * lastresult : null
         * reserve : null
         * type : 2
         * status : 0
         * lendingtime : 4
         */

        private int id;
        private int userid;
        private String orderid;
        private String flag;
        private String createtime;
        private double count;
        private String rate;
        private String maxrate;
        private String loanlimit;
        private String loantype;
        private String loantime;
        private String lastresult;
        private String reserve;
        private String type;
        private String status;
        private String lendingtime;
        private String lastpicture;
        private String remark;

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getLastpicture() {
            return lastpicture;
        }

        public void setLastpicture(String lastpicture) {
            this.lastpicture = lastpicture;
        }

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

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
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

        public String getCount() {
            return String.valueOf(count)+"万";
        }

        public double getCountDouble() {
            return count;
        }

        public void setCount(double count) {
            this.count = count;
        }

        public String getRate() {
            return rate+"厘";
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getMaxrate() {
            return maxrate+"厘";
        }

        public void setMaxrate(String maxrate) {
            this.maxrate = maxrate;
        }

        public String getLoanlimit() {
            return loanlimit+"月";
        }

        public void setLoanlimit(String loanlimit) {
            this.loanlimit = loanlimit;
        }

        public String getLoantype() {
            return loantype;
        }

        public void setLoantype(String loantype) {
            this.loantype = loantype;
        }

        public String getLoantime() {
            return loantime;
        }

        public void setLoantime(String loantime) {
            this.loantime = loantime;
        }

        public String getLastresult() {
            return lastresult+"天";
        }

        public void setLastresult(String lastresult) {
            this.lastresult = lastresult;
        }

        public String getReserve() {
            return reserve;
        }

        public void setReserve(String reserve) {
            this.reserve = reserve;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLendingtime() {
            return lendingtime+"天";
        }

        public void setLendingtime(String lendingtime) {
            this.lendingtime = lendingtime;
        }
    }

    public static class AppuserBean {
        /**
         * id : 21
         * username : 13125162988
         * telphone : null
         * createtime : 2017-11-17 15:27:11
         * password : e10adc3949ba59abbe56e057f20f883e
         * parentid : null
         * nick : null
         * realname : 赵
         * picture : http://ykd2017.oss-cn-hangzhou.aliyuncs.com/android_gxdk_1511244068412.jpg
         * idcard : null
         * bank : 干活
         * branchname : 大哥哥
         * bankaddress : 发广告
         * company : null
         * companyaddress : null
         * token : null
         * regisonid : null
         * flag : 0
         * type : 2
         * sign : 0
         * curentaddrress : null
         * province : 浙江省
         * city : 杭州市
         * zone : 萧山区
         * link : null
         * ewm : null
         * address : null
         * reverse : null
         * applyflag : 0
         * status : 2
         */

        private int id;
        private String username;
        private Object telphone;
        private String createtime;
        private String password;
        private Object parentid;
        private Object nick;
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
        private String province;
        private String city;
        private String zone;
        private Object link;
        private Object ewm;
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

        public Object getTelphone() {
            return telphone;
        }

        public void setTelphone(Object telphone) {
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

        public Object getParentid() {
            return parentid;
        }

        public void setParentid(Object parentid) {
            this.parentid = parentid;
        }

        public Object getNick() {
            return nick;
        }

        public void setNick(Object nick) {
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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
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
