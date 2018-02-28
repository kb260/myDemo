package com.kb260.gxdk.model;

import java.io.Serializable;

/**
 * @author KB260
 *         Created on  2017/11/13
 */

public class Order implements Serializable {


    /**
     * id : 10
     * userid : 13
     * orderid : 6
     * firstid : 6
     * lastid : 7
     * grabid : 15
     * productid : 1
     * flag : 0
     * status : 3
     * createtime : 2017-11-13 11:53:40
     * grabtime : 2017-11-16 14:14:24
     * times : 2
     * reserve : 2017-11-16 15:44:24
     * roomloan : {"id":6,"userid":13,"createtime":"2017-10-31 15:42:45","flag":"0","realname":null,"age":"33","idcard":null,"telphone":null,"address":"下沙","spousename":null,"spousetel":null,"roomaddress":null,"area":"30","nature":null,"current":null,"count":50,"rate":5,"maxrate":6,"descore":null,"balscore":null,"loantime":null,"position":null,"ratio":null,"dueoption":null,"duetime":null,"amount":null,"evaluatingprice":null,"goodreliability":null,"instruction":null,"status":"4","type":"1","isplace":null,"sign":null,"iscompany":null,"isoverdue":null,"cardphoto":null,"marriagephoto":null,"residencephoto":null,"threephoto":null,"license":null,"creditphoto":null,"association":null,"replenishment":null,"credibility":null,"carframe":null,"carbrand":null,"carno":null,"carage":null,"distance":null,"ispay":null,"mortgagebank":null,"expenditure":null,"surpius":null,"iscar":null,"isroom":null,"workplace":null,"industry":null,"occupation":null,"currentsalary":null,"worktime":null,"payform":null,"isfund":null,"unilateralpay":null,"paycity":null,"limittime":null,"isinsurancepolicy":null,"insuredcompany":null,"monthpay":null,"yearpay":null,"province":"浙江省","city":"杭州市","zone":"江干区","times":null,"reserve":null,"lasttrial":null,"firsttrial":null,"appuser":null}
     * firsttrial : {"id":6,"userid":15,"orderid":10,"flag":"0","createtime":"2017-11-16 14:38:04","count":2,"rate":"15","maxrate":"15","loanlimit":"15","loantype":"15","loantime":"15","lastresult":15,"reserve":"2017-12-01 10:30:00","type":"1","status":"0","lendingtime":null}
     * lasttrial : {"id":7,"userid":15,"orderid":null,"flag":"0","createtime":"2017-11-16 15:01:00","count":34,"rate":"56","maxrate":"5","loanlimit":"2","loantype":"一年一转","loantime":"2","lastresult":null,"reserve":null,"type":"2","status":"0","lendingtime":"4"}
     * appuser : {"id":15,"username":"14727002600","telphone":"14727002580","createtime":"2017-11-02 11:00:35","password":"e10adc3949ba59abbe56e057f20f883e","parentid":"11","nick":"突击","realname":"啦啦啦","picture":null,"idcard":null,"bank":"ggg","branchname":"ggh","bankaddress":"yhhh","company":"老婆","companyaddress":"一起住","token":null,"regisonid":null,"flag":"0","type":"0","sign":"0","curentaddrress":null,"province":null,"city":null,"zone":null,"link":null,"ewm":null,"address":null,"reverse":null,"applyflag":"2","status":null}
     */

    private int id;
    private int userid;
    private int orderid;
    private int firstid;
    private int lastid;
    private int grabid;
    private int productid;
    private String flag;
    private String status;
    private String createtime;
    private String grabtime;
    private int times;
    private String reserve;
    private String othertime;
    private RoomloanBean roomloan;
    private FirsttrialBean firsttrial;
    private LasttrialBean lasttrial;
    private AppuserBean appuser;

    public String getOthertime() {
        return othertime;
    }

    public void setOthertime(String othertime) {
        this.othertime = othertime;
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

    public int getFirstid() {
        return firstid;
    }

    public void setFirstid(int firstid) {
        this.firstid = firstid;
    }

    public int getLastid() {
        return lastid;
    }

    public void setLastid(int lastid) {
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
        if (status != null) {
            return status;
        }
        return "";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatetime() {
        if (createtime != null) {
            return createtime;
        }
        return "";
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

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public RoomloanBean getRoomloan() {
        if (roomloan != null) {
            return roomloan;
        }
        return new RoomloanBean();
    }

    public void setRoomloan(RoomloanBean roomloan) {
        this.roomloan = roomloan;
    }

    public FirsttrialBean getFirsttrial() {
        return firsttrial;
    }

    public void setFirsttrial(FirsttrialBean firsttrial) {
        this.firsttrial = firsttrial;
    }

    public LasttrialBean getLasttrial() {
        return lasttrial;
    }

    public void setLasttrial(LasttrialBean lasttrial) {
        this.lasttrial = lasttrial;
    }

    public AppuserBean getAppuser() {
        if (appuser != null) {
            return appuser;
        }
        return new AppuserBean();
    }

    public void setAppuser(AppuserBean appuser) {
        this.appuser = appuser;
    }

    public static class RoomloanBean implements Serializable {
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
         * status : 4
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

        public String getRealname() {
            if (realname != null) {
                return realname;
            }
            return "";
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getAge() {
            return age + "岁";
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
            return area + "平方米";
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
            if (current != null) {
                return current;
            }
            return "";
        }

        public void setCurrent(String current) {
            this.current = current;
        }

        public String getCount() {
            return String.valueOf(count) + "万元";
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
            return loantime + "月";
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
            if (ratio != null) {
                if (ratio.contains("%")) {
                    return ratio;
                } else {
                    return ratio + "%";
                }
            } else {
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
            return amount + "元";
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getEvaluatingprice() {

            if (evaluatingprice != null) {
                return evaluatingprice + "元";
            }
            return "";
        }

        public String getEvaluatingpriceCar() {

            if (evaluatingprice != null) {
                return evaluatingprice + "万元";
            }
            return "";
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
            if (status != null) {
                return status;
            }
            return "";
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            if (type != null) {
                return type;
            }
            return "";
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
            if (sign != null) {
                switch (sign) {
                    case "0":
                        return "未婚";
                    case "1":
                        return "已婚";
                    default:
                        return sign;
                }
            }
            return "";
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getIscompany() {
            if (iscompany != null) {
                switch (iscompany) {
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
            if (isoverdue != null) {
                switch (isoverdue) {
                    case "否":
                        return "否";
                    case "是":
                        return "是";
                    case "0":
                        return "否";
                    case "1":
                        return "是";
                    default:
                        return isoverdue;
                }
            }
            return "";
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
            return carage + "年";
        }

        public void setCarage(String carage) {
            this.carage = carage;
        }

        public String getDistance() {
            return distance + "万公里";
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getIspay() {
            return ispay;
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
            if (expenditure != null) {
                switch (expenditure) {
                    case "0":
                        return "否";
                    case "1":
                        return "是";
                    default:
                        return expenditure;
                }
            }else {
                return "";
            }
        }

        public void setExpenditure(String expenditure) {
            this.expenditure = expenditure;
        }

        public String getSurpius() {
            return surpius + "万";
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
            if (industry != null) {
                switch (industry) {
                    case "1":
                        return "优秀";
                    case "2":
                        return "一般";
                    case "3":
                        return "较差";
                    default:
                        return "";
                }
            } else {
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
            return paycity + "万元";
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
            return times + "年";
        }

        public void setTimes(String times) {
            this.times = times;
        }

        public String getReserve() {
            if (reserve != null) {
                return reserve;
            }
            return "";
        }

        public void setReserve(String reserve) {
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

    public static class FirsttrialBean implements Serializable {
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
            return String.valueOf(count) + "万";
        }

        public void setCount(double count) {
            this.count = count;
        }

        public String getRate() {
            return rate + "厘";
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getMaxrate() {
            return maxrate + "厘";
        }

        public void setMaxrate(String maxrate) {
            this.maxrate = maxrate;
        }

        public String getLoanlimit() {
            return loanlimit + "月";
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
            return String.valueOf(lastresult) + "天";
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

    public static class LasttrialBean implements Serializable {
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
            return String.valueOf(count) + "万";
        }

        public void setCount(double count) {
            this.count = count;
        }

        public String getRate() {
            return rate + "厘";
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getMaxrate() {
            return maxrate + "厘";
        }

        public void setMaxrate(String maxrate) {
            this.maxrate = maxrate;
        }

        public String getLoanlimit() {
            return loanlimit + "月";
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
            return lastresult;
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
            return lendingtime + "天";
        }

        public void setLendingtime(String lendingtime) {
            this.lendingtime = lendingtime;
        }
    }

    public static class AppuserBean implements Serializable {
        /**
         * id : 15
         * username : 14727002600
         * telphone : 14727002580
         * createtime : 2017-11-02 11:00:35
         * password : e10adc3949ba59abbe56e057f20f883e
         * parentid : 11
         * nick : 突击
         * realname : 啦啦啦
         * picture : null
         * idcard : null
         * bank : ggg
         * branchname : ggh
         * bankaddress : yhhh
         * company : 老婆
         * companyaddress : 一起住
         * token : null
         * regisonid : null
         * flag : 0
         * type : 0
         * sign : 0
         * curentaddrress : null
         * province : null
         * city : null
         * zone : null
         * link : null
         * ewm : null
         * address : null
         * reverse : null
         * applyflag : 2
         * status : null
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
        private String idcard;
        private String bank;
        private String branchname;
        private String bankaddress;
        private String company;
        private String companyaddress;
        private String token;
        private String regisonid;
        private String flag;
        private String type;
        private String sign;
        private String curentaddrress;
        private String province;
        private String city;
        private String zone;
        private String link;
        private String ewm;
        private String address;
        private String reverse;
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
            if (picture != null) {
                return picture;
            }
            return "";
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
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

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getCompanyaddress() {
            return companyaddress;
        }

        public void setCompanyaddress(String companyaddress) {
            this.companyaddress = companyaddress;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRegisonid() {
            return regisonid;
        }

        public void setRegisonid(String regisonid) {
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

        public String getCurentaddrress() {
            return curentaddrress;
        }

        public void setCurentaddrress(String curentaddrress) {
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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getEwm() {
            return ewm;
        }

        public void setEwm(String ewm) {
            this.ewm = ewm;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getReverse() {
            return reverse;
        }

        public void setReverse(String reverse) {
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
