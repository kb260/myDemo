package com.kb260.gxdk.entity;

/**
 * @author KB260
 *         Created on  2017/11/16
 */

public class FirstTrialResult {

    /**
     * id : 10
     * userid : 13
     * orderid : 6
     * firstid : null
     * lastid : null
     * grabid : 15
     * productid : 1
     * flag : 0
     * status : 0
     * createtime : 2017-11-13 11:53:40
     * grabtime : 2017-11-13 11:53:40
     * times : null
     * reserve : null
     * roomloan : {"id":6,"userid":13,"createtime":"2017-10-31 15:42:45","flag":"0","realname":null,"age":"33","idcard":null,"telphone":null,"address":"下沙","spousename":null,"spousetel":null,"roomaddress":null,"area":"30","nature":null,"current":null,"count":50,"rate":5,"maxrate":6,"descore":null,"balscore":null,"loantime":null,"position":null,"ratio":null,"dueoption":null,"duetime":null,"amount":null,"evaluatingprice":null,"goodreliability":null,"instruction":null,"status":"3","type":"1","isplace":null,"sign":null,"iscompany":null,"isoverdue":null,"cardphoto":null,"marriagephoto":null,"residencephoto":null,"threephoto":null,"license":null,"creditphoto":null,"association":null,"replenishment":null,"credibility":null,"carframe":null,"carbrand":null,"carno":null,"carage":null,"distance":null,"ispay":null,"mortgagebank":null,"expenditure":null,"surpius":null,"iscar":null,"isroom":null,"workplace":null,"industry":null,"occupation":null,"currentsalary":null,"worktime":null,"payform":null,"isfund":null,"unilateralpay":null,"paycity":null,"limittime":null,"isinsurancepolicy":null,"insuredcompany":null,"monthpay":null,"yearpay":null,"province":"浙江省","city":"杭州市","zone":"江干区","times":null,"reserve":null,"lasttrial":null,"firsttrial":null,"appuser":null}
     * firsttrial : null
     * lasttrial : null
     * appuser : null
     */

    private int id;
    private int userid;
    private int orderid;
    private Object firstid;
    private Object lastid;
    private int grabid;
    private int productid;
    private String flag;
    private String status;
    private String createtime;
    private String grabtime;
    private Object times;
    private Object reserve;
    private RoomloanBean roomloan;
    private Object firsttrial;
    private Object lasttrial;
    private Object appuser;

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

    public Object getFirstid() {
        return firstid;
    }

    public void setFirstid(Object firstid) {
        this.firstid = firstid;
    }

    public Object getLastid() {
        return lastid;
    }

    public void setLastid(Object lastid) {
        this.lastid = lastid;
    }

    public int getGrabid() {
        return grabid;
    }

    public void setGrabid(int grabid) {
        this.grabid = grabid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getGrabtime() {
        return grabtime;
    }

    public void setGrabtime(String grabtime) {
        this.grabtime = grabtime;
    }

    public Object getTimes() {
        return times;
    }

    public void setTimes(Object times) {
        this.times = times;
    }

    public Object getReserve() {
        return reserve;
    }

    public void setReserve(Object reserve) {
        this.reserve = reserve;
    }

    public RoomloanBean getRoomloan() {
        return roomloan;
    }

    public void setRoomloan(RoomloanBean roomloan) {
        this.roomloan = roomloan;
    }

    public Object getFirsttrial() {
        return firsttrial;
    }

    public void setFirsttrial(Object firsttrial) {
        this.firsttrial = firsttrial;
    }

    public Object getLasttrial() {
        return lasttrial;
    }

    public void setLasttrial(Object lasttrial) {
        this.lasttrial = lasttrial;
    }

    public Object getAppuser() {
        return appuser;
    }

    public void setAppuser(Object appuser) {
        this.appuser = appuser;
    }

    public static class RoomloanBean {
        /**
         * id : 6
         * userid : 13
         * createtime : 2017-10-31 15:42:45
         * flag : 0
         * realname : null
         * age : 33
         * idcard : null
         * telphone : null
         * address : 下沙
         * spousename : null
         * spousetel : null
         * roomaddress : null
         * area : 30
         * nature : null
         * current : null
         * count : 50.0
         * rate : 5.0
         * maxrate : 6.0
         * descore : null
         * balscore : null
         * loantime : null
         * position : null
         * ratio : null
         * dueoption : null
         * duetime : null
         * amount : null
         * evaluatingprice : null
         * goodreliability : null
         * instruction : null
         * status : 3
         * type : 1
         * isplace : null
         * sign : null
         * iscompany : null
         * isoverdue : null
         * cardphoto : null
         * marriagephoto : null
         * residencephoto : null
         * threephoto : null
         * license : null
         * creditphoto : null
         * association : null
         * replenishment : null
         * credibility : null
         * carframe : null
         * carbrand : null
         * carno : null
         * carage : null
         * distance : null
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
         * province : 浙江省
         * city : 杭州市
         * zone : 江干区
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
        private Object realname;
        private String age;
        private Object idcard;
        private Object telphone;
        private String address;
        private Object spousename;
        private Object spousetel;
        private Object roomaddress;
        private String area;
        private Object nature;
        private Object current;
        private double count;
        private double rate;
        private double maxrate;
        private Object descore;
        private Object balscore;
        private Object loantime;
        private Object position;
        private Object ratio;
        private Object dueoption;
        private Object duetime;
        private Object amount;
        private Object evaluatingprice;
        private Object goodreliability;
        private Object instruction;
        private String status;
        private String type;
        private Object isplace;
        private Object sign;
        private Object iscompany;
        private Object isoverdue;
        private Object cardphoto;
        private Object marriagephoto;
        private Object residencephoto;
        private Object threephoto;
        private Object license;
        private Object creditphoto;
        private Object association;
        private Object replenishment;
        private Object credibility;
        private Object carframe;
        private Object carbrand;
        private Object carno;
        private Object carage;
        private Object distance;
        private Object ispay;
        private Object mortgagebank;
        private Object expenditure;
        private Object surpius;
        private Object iscar;
        private Object isroom;
        private Object workplace;
        private Object industry;
        private Object occupation;
        private Object currentsalary;
        private Object worktime;
        private Object payform;
        private Object isfund;
        private Object unilateralpay;
        private Object paycity;
        private Object limittime;
        private Object isinsurancepolicy;
        private Object insuredcompany;
        private Object monthpay;
        private Object yearpay;
        private String province;
        private String city;
        private String zone;
        private Object times;
        private Object reserve;
        private Object lasttrial;
        private Object firsttrial;
        private Object appuser;

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

        public Object getRealname() {
            return realname;
        }

        public void setRealname(Object realname) {
            this.realname = realname;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Object getIdcard() {
            return idcard;
        }

        public void setIdcard(Object idcard) {
            this.idcard = idcard;
        }

        public Object getTelphone() {
            return telphone;
        }

        public void setTelphone(Object telphone) {
            this.telphone = telphone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getSpousename() {
            return spousename;
        }

        public void setSpousename(Object spousename) {
            this.spousename = spousename;
        }

        public Object getSpousetel() {
            return spousetel;
        }

        public void setSpousetel(Object spousetel) {
            this.spousetel = spousetel;
        }

        public Object getRoomaddress() {
            return roomaddress;
        }

        public void setRoomaddress(Object roomaddress) {
            this.roomaddress = roomaddress;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public Object getNature() {
            return nature;
        }

        public void setNature(Object nature) {
            this.nature = nature;
        }

        public Object getCurrent() {
            return current;
        }

        public void setCurrent(Object current) {
            this.current = current;
        }

        public double getCount() {
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

        public Object getDescore() {
            return descore;
        }

        public void setDescore(Object descore) {
            this.descore = descore;
        }

        public Object getBalscore() {
            return balscore;
        }

        public void setBalscore(Object balscore) {
            this.balscore = balscore;
        }

        public Object getLoantime() {
            return loantime;
        }

        public void setLoantime(Object loantime) {
            this.loantime = loantime;
        }

        public Object getPosition() {
            return position;
        }

        public void setPosition(Object position) {
            this.position = position;
        }

        public Object getRatio() {
            return ratio;
        }

        public void setRatio(Object ratio) {
            this.ratio = ratio;
        }

        public Object getDueoption() {
            return dueoption;
        }

        public void setDueoption(Object dueoption) {
            this.dueoption = dueoption;
        }

        public Object getDuetime() {
            return duetime;
        }

        public void setDuetime(Object duetime) {
            this.duetime = duetime;
        }

        public Object getAmount() {
            return amount;
        }

        public void setAmount(Object amount) {
            this.amount = amount;
        }

        public Object getEvaluatingprice() {
            return evaluatingprice;
        }

        public void setEvaluatingprice(Object evaluatingprice) {
            this.evaluatingprice = evaluatingprice;
        }

        public Object getGoodreliability() {
            return goodreliability;
        }

        public void setGoodreliability(Object goodreliability) {
            this.goodreliability = goodreliability;
        }

        public Object getInstruction() {
            return instruction;
        }

        public void setInstruction(Object instruction) {
            this.instruction = instruction;
        }

        public String getStatus() {
            return status;
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

        public Object getIsplace() {
            return isplace;
        }

        public void setIsplace(Object isplace) {
            this.isplace = isplace;
        }

        public Object getSign() {
            return sign;
        }

        public void setSign(Object sign) {
            this.sign = sign;
        }

        public Object getIscompany() {
            return iscompany;
        }

        public void setIscompany(Object iscompany) {
            this.iscompany = iscompany;
        }

        public Object getIsoverdue() {
            return isoverdue;
        }

        public void setIsoverdue(Object isoverdue) {
            this.isoverdue = isoverdue;
        }

        public Object getCardphoto() {
            return cardphoto;
        }

        public void setCardphoto(Object cardphoto) {
            this.cardphoto = cardphoto;
        }

        public Object getMarriagephoto() {
            return marriagephoto;
        }

        public void setMarriagephoto(Object marriagephoto) {
            this.marriagephoto = marriagephoto;
        }

        public Object getResidencephoto() {
            return residencephoto;
        }

        public void setResidencephoto(Object residencephoto) {
            this.residencephoto = residencephoto;
        }

        public Object getThreephoto() {
            return threephoto;
        }

        public void setThreephoto(Object threephoto) {
            this.threephoto = threephoto;
        }

        public Object getLicense() {
            return license;
        }

        public void setLicense(Object license) {
            this.license = license;
        }

        public Object getCreditphoto() {
            return creditphoto;
        }

        public void setCreditphoto(Object creditphoto) {
            this.creditphoto = creditphoto;
        }

        public Object getAssociation() {
            return association;
        }

        public void setAssociation(Object association) {
            this.association = association;
        }

        public Object getReplenishment() {
            return replenishment;
        }

        public void setReplenishment(Object replenishment) {
            this.replenishment = replenishment;
        }

        public Object getCredibility() {
            return credibility;
        }

        public void setCredibility(Object credibility) {
            this.credibility = credibility;
        }

        public Object getCarframe() {
            return carframe;
        }

        public void setCarframe(Object carframe) {
            this.carframe = carframe;
        }

        public Object getCarbrand() {
            return carbrand;
        }

        public void setCarbrand(Object carbrand) {
            this.carbrand = carbrand;
        }

        public Object getCarno() {
            return carno;
        }

        public void setCarno(Object carno) {
            this.carno = carno;
        }

        public Object getCarage() {
            return carage;
        }

        public void setCarage(Object carage) {
            this.carage = carage;
        }

        public Object getDistance() {
            return distance;
        }

        public void setDistance(Object distance) {
            this.distance = distance;
        }

        public Object getIspay() {
            return ispay;
        }

        public void setIspay(Object ispay) {
            this.ispay = ispay;
        }

        public Object getMortgagebank() {
            return mortgagebank;
        }

        public void setMortgagebank(Object mortgagebank) {
            this.mortgagebank = mortgagebank;
        }

        public Object getExpenditure() {
            return expenditure;
        }

        public void setExpenditure(Object expenditure) {
            this.expenditure = expenditure;
        }

        public Object getSurpius() {
            return surpius;
        }

        public void setSurpius(Object surpius) {
            this.surpius = surpius;
        }

        public Object getIscar() {
            return iscar;
        }

        public void setIscar(Object iscar) {
            this.iscar = iscar;
        }

        public Object getIsroom() {
            return isroom;
        }

        public void setIsroom(Object isroom) {
            this.isroom = isroom;
        }

        public Object getWorkplace() {
            return workplace;
        }

        public void setWorkplace(Object workplace) {
            this.workplace = workplace;
        }

        public Object getIndustry() {
            return industry;
        }

        public void setIndustry(Object industry) {
            this.industry = industry;
        }

        public Object getOccupation() {
            return occupation;
        }

        public void setOccupation(Object occupation) {
            this.occupation = occupation;
        }

        public Object getCurrentsalary() {
            return currentsalary;
        }

        public void setCurrentsalary(Object currentsalary) {
            this.currentsalary = currentsalary;
        }

        public Object getWorktime() {
            return worktime;
        }

        public void setWorktime(Object worktime) {
            this.worktime = worktime;
        }

        public Object getPayform() {
            return payform;
        }

        public void setPayform(Object payform) {
            this.payform = payform;
        }

        public Object getIsfund() {
            return isfund;
        }

        public void setIsfund(Object isfund) {
            this.isfund = isfund;
        }

        public Object getUnilateralpay() {
            return unilateralpay;
        }

        public void setUnilateralpay(Object unilateralpay) {
            this.unilateralpay = unilateralpay;
        }

        public Object getPaycity() {
            return paycity;
        }

        public void setPaycity(Object paycity) {
            this.paycity = paycity;
        }

        public Object getLimittime() {
            return limittime;
        }

        public void setLimittime(Object limittime) {
            this.limittime = limittime;
        }

        public Object getIsinsurancepolicy() {
            return isinsurancepolicy;
        }

        public void setIsinsurancepolicy(Object isinsurancepolicy) {
            this.isinsurancepolicy = isinsurancepolicy;
        }

        public Object getInsuredcompany() {
            return insuredcompany;
        }

        public void setInsuredcompany(Object insuredcompany) {
            this.insuredcompany = insuredcompany;
        }

        public Object getMonthpay() {
            return monthpay;
        }

        public void setMonthpay(Object monthpay) {
            this.monthpay = monthpay;
        }

        public Object getYearpay() {
            return yearpay;
        }

        public void setYearpay(Object yearpay) {
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

        public Object getTimes() {
            return times;
        }

        public void setTimes(Object times) {
            this.times = times;
        }

        public Object getReserve() {
            return reserve;
        }

        public void setReserve(Object reserve) {
            this.reserve = reserve;
        }

        public Object getLasttrial() {
            return lasttrial;
        }

        public void setLasttrial(Object lasttrial) {
            this.lasttrial = lasttrial;
        }

        public Object getFirsttrial() {
            return firsttrial;
        }

        public void setFirsttrial(Object firsttrial) {
            this.firsttrial = firsttrial;
        }

        public Object getAppuser() {
            return appuser;
        }

        public void setAppuser(Object appuser) {
            this.appuser = appuser;
        }
    }
}
