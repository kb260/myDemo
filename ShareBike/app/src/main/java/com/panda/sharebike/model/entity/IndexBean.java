package com.panda.sharebike.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/07/23
 * desc   :
 * version: 1.0
 */
public class IndexBean implements Serializable {


    /**
     * code : 200
     * msg : null
     * data : {"parks":[{"bikePark":{"id":"p1","position":[120.2708504872148,30.20555067014208],"name":"p1","bikeCount":2},"distance":0.05954301571129674}],"leftTime":899979,"state":"subscribing","nearBikes":[],"memberRideConfig":{"id":"1","deposit":0.01,"bikeParkScope":300,"maxBikePark":30,"subscribeDuration":900000,"rental":0.01,"timeUnit":1800000},"order":{"id":"1de881c6-718a-4118-8c7d-2f456aa51422","state":"underway","startTime":1501676273407,"endTime":0,"bike":{"id":"0000002","state":"subscribed","num":"0000002","bikeType":null,"version":"version","createTime":1500955057463,"bikeLock":{"id":"864501036491381","bikeId":"0000002","position":[120.2708504872108,30.205550670141083],"locateTime":0,"electricQuantity":400,"lock":true}},"member":{"id":"2","cellphone":"13478606965","nickname":"13478606965","avatar":"http://www.qiaoqidanche.com:91/qbike/upload/avatar/851501664428959.jpg","email":"","realname":"宁立森","idCard":"450721199209140974","realnameAuth":true,"registerTime":1501664175121,"lastActivateTime":1501665835826}}}
     */

    /**
     * parks : [{"bikePark":{"id":"p1","position":[120.2708504872148,30.20555067014208],"name":"p1","bikeCount":2},"distance":0.05954301571129674}]
     * leftTime : 899979
     * state : subscribing
     * nearBikes : []
     * memberRideConfig : {"id":"1","deposit":0.01,"bikeParkScope":300,"maxBikePark":30,"subscribeDuration":900000,"rental":0.01,"timeUnit":1800000}
     * order : {"id":"1de881c6-718a-4118-8c7d-2f456aa51422","state":"underway","startTime":1501676273407,"endTime":0,"bike":{"id":"0000002","state":"subscribed","num":"0000002","bikeType":null,"version":"version","createTime":1500955057463,"bikeLock":{"id":"864501036491381","bikeId":"0000002","position":[120.2708504872108,30.205550670141083],"locateTime":0,"electricQuantity":400,"lock":true}},"member":{"id":"2","cellphone":"13478606965","nickname":"13478606965","avatar":"http://www.qiaoqidanche.com:91/qbike/upload/avatar/851501664428959.jpg","email":"","realname":"宁立森","idCard":"450721199209140974","realnameAuth":true,"registerTime":1501664175121,"lastActivateTime":1501665835826}}
     */

    private int leftTime;
    private String state;
    private MemberRideConfigBean memberRideConfig;
    private OrderBean order;
    private List<ParksBean> parks;
    private List<NearBikesBean> nearBikes;
    private long duration;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(int leftTime) {
        this.leftTime = leftTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public MemberRideConfigBean getMemberRideConfig() {
        return memberRideConfig;
    }

    public void setMemberRideConfig(MemberRideConfigBean memberRideConfig) {
        this.memberRideConfig = memberRideConfig;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public List<ParksBean> getParks() {
        return parks;
    }

    public void setParks(List<ParksBean> parks) {
        this.parks = parks;
    }

    public List<NearBikesBean> getNearBikes() {
        return nearBikes;
    }

    public void setNearBikes(List<NearBikesBean> nearBikes) {
        this.nearBikes = nearBikes;
    }

    public static class NearBikesBean {


        private double distance;
        private String id;
        private String macAddress;
        private String lockId;
        private String bikeId;
        private long locateTime;
        private int electricQuantity;
        private boolean lock;
        private List<Double> position;

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public String getMacAddress() {
            return macAddress;
        }

        public void setMacAddress(String macAddress) {
            this.macAddress = macAddress;
        }

        public String getLockId() {
            return lockId;
        }

        public void setLockId(String lockId) {
            this.lockId = lockId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBikeId() {
            return bikeId;
        }

        public void setBikeId(String bikeId) {
            this.bikeId = bikeId;
        }

        public long getLocateTime() {
            return locateTime;
        }

        public void setLocateTime(int locateTime) {
            this.locateTime = locateTime;
        }

        public int getElectricQuantity() {
            return electricQuantity;
        }

        public void setElectricQuantity(int electricQuantity) {
            this.electricQuantity = electricQuantity;
        }

        public boolean isLock() {
            return lock;
        }

        public void setLock(boolean lock) {
            this.lock = lock;
        }

        public List<Double> getPosition() {
            return position;
        }

        public void setPosition(List<Double> position) {
            this.position = position;
        }
    }



    public static class MemberRideConfigBean {
        /**
         * id : 1
         * deposit : 0.01
         * bikeParkScope : 300.0
         * maxBikePark : 30
         * subscribeDuration : 900000
         * rental : 0.01
         * timeUnit : 1800000
         */

        private String id;
        private double deposit;
        private double bikeParkScope;
        private int maxBikePark;
        private int subscribeDuration;
        private double rental;
        private int timeUnit;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getDeposit() {
            return deposit;
        }

        public void setDeposit(double deposit) {
            this.deposit = deposit;
        }

        public double getBikeParkScope() {
            return bikeParkScope;
        }

        public void setBikeParkScope(double bikeParkScope) {
            this.bikeParkScope = bikeParkScope;
        }

        public int getMaxBikePark() {
            return maxBikePark;
        }

        public void setMaxBikePark(int maxBikePark) {
            this.maxBikePark = maxBikePark;
        }

        public int getSubscribeDuration() {
            return subscribeDuration;
        }

        public void setSubscribeDuration(int subscribeDuration) {
            this.subscribeDuration = subscribeDuration;
        }

        public double getRental() {
            return rental;
        }

        public void setRental(double rental) {
            this.rental = rental;
        }

        public int getTimeUnit() {
            return timeUnit;
        }

        public void setTimeUnit(int timeUnit) {
            this.timeUnit = timeUnit;
        }
    }

    public static class OrderBean {
        /**
         * id : 1de881c6-718a-4118-8c7d-2f456aa51422
         * state : underway
         * startTime : 1501676273407
         * endTime : 0
         * bike : {"id":"0000002","state":"subscribed","num":"0000002","bikeType":null,"version":"version","createTime":1500955057463,"bikeLock":{"id":"864501036491381","bikeId":"0000002","position":[120.2708504872108,30.205550670141083],"locateTime":0,"electricQuantity":400,"lock":true}}
         * member : {"id":"2","cellphone":"13478606965","nickname":"13478606965","avatar":"http://www.qiaoqidanche.com:91/qbike/upload/avatar/851501664428959.jpg","email":"","realname":"宁立森","idCard":"450721199209140974","realnameAuth":true,"registerTime":1501664175121,"lastActivateTime":1501665835826}
         */

        private String id;
        private String state;
        private long startTime;
        private int endTime;
        private double distance;
        private double cost;
        private long duration;
        private Boolean superzone;
        private BikeBean bike;
        private MemberBean member;

        public Boolean getSuperzone() {
            if (superzone==null){
                return false;
            }else {
                return superzone;
            }
        }

        public void setSuperzone(Boolean superzone) {
            this.superzone = superzone;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public BikeBean getBike() {
            return bike;
        }

        public void setBike(BikeBean bike) {
            this.bike = bike;
        }

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public static class BikeBean {
            /**
             * id : 0000002
             * state : subscribed
             * num : 0000002
             * bikeType : null
             * version : version
             * createTime : 1500955057463
             * bikeLock : {"id":"864501036491381","bikeId":"0000002","position":[120.2708504872108,30.205550670141083],"locateTime":0,"electricQuantity":400,"lock":true}
             */

            private String id;
            private String state;
            private String num;
            //   private String bikeType;
            private String version;
            private long createTime;
            private BikeLockBean bikeLock;
            private BikeTypeBean bikeType;
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            //    public String getBikeType() {
            //        return bikeType;
            //      }

//            public void setBikeType(String bikeType) {
//                this.bikeType = bikeType;
//            }

            public String getVersion() {
                return version;
        }

            public void setVersion(String version) {
                this.version = version;
        }

            public long getCreateTime() {
                return createTime;
        }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
        }

            public BikeLockBean getBikeLock() {
                return bikeLock;
        }

            public void setBikeLock(BikeLockBean bikeLock) {
                this.bikeLock = bikeLock;
        }

            public static class BikeTypeBean {
                private String id;
                private String type;
                private long createTime;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }
            }
        public static class BikeLockBean {
            /**
             * id : 864501036491381
             * bikeId : 0000002
             * position : [120.2708504872108,30.205550670141083]
             * locateTime : 0
             * electricQuantity : 400.0
             * lock : true
             */

            private String id;
            private String bikeId;
            private String macAddress;
            private long locateTime;
            private double electricQuantity;
            private boolean lock;
            private List<Double> position;

            public String getMacAddress() {
                return macAddress;
            }

            public void setMacAddress(String macAddress) {
                this.macAddress = macAddress;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBikeId() {
                return bikeId;
            }

            public void setBikeId(String bikeId) {
                this.bikeId = bikeId;
            }

            public long getLocateTime() {
                return locateTime;
            }

            public void setLocateTime(int locateTime) {
                this.locateTime = locateTime;
            }

            public double getElectricQuantity() {
                return electricQuantity;
            }

            public void setElectricQuantity(double electricQuantity) {
                this.electricQuantity = electricQuantity;
            }

            public boolean isLock() {
                return lock;
            }

            public void setLock(boolean lock) {
                this.lock = lock;
            }

            public List<Double> getPosition() {
                return position;
            }

            public void setPosition(List<Double> position) {
                this.position = position;
            }
        }
        }

        public static class MemberBean {
            /**
             * id : 2
             * cellphone : 13478606965
             * nickname : 13478606965
             * avatar : http://www.qiaoqidanche.com:91/qbike/upload/avatar/851501664428959.jpg
             * email :
             * realname : 宁立森
             * idCard : 450721199209140974
             * realnameAuth : true
             * registerTime : 1501664175121
             * lastActivateTime : 1501665835826
             */

            private String id;
            private String cellphone;
            private String nickname;
            private String avatar;
            private String email;
            private String realname;
            private String idCard;
            private boolean realnameAuth;
            private long registerTime;
            private long lastActivateTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCellphone() {
                return cellphone;
            }

            public void setCellphone(String cellphone) {
                this.cellphone = cellphone;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getIdCard() {
                return idCard;
            }

            public void setIdCard(String idCard) {
                this.idCard = idCard;
            }

            public boolean isRealnameAuth() {
                return realnameAuth;
            }

            public void setRealnameAuth(boolean realnameAuth) {
                this.realnameAuth = realnameAuth;
            }

            public long getRegisterTime() {
                return registerTime;
            }

            public void setRegisterTime(long registerTime) {
                this.registerTime = registerTime;
            }

            public long getLastActivateTime() {
                return lastActivateTime;
            }

            public void setLastActivateTime(long lastActivateTime) {
                this.lastActivateTime = lastActivateTime;
            }
        }
    }

    public static class ParksBean {
        /**
         * bikePark : {"id":"p1","position":[120.2708504872148,30.20555067014208],"name":"p1","bikeCount":2}
         * distance : 0.05954301571129674
         */

        private BikeParkBean bikePark;
        private double distance;

        public BikeParkBean getBikePark() {
            return bikePark;
        }

        public void setBikePark(BikeParkBean bikePark) {
            this.bikePark = bikePark;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public static class BikeParkBean {
            /**
             * id : p1
             * position : [120.2708504872148,30.20555067014208]
             * name : p1
             * bikeCount : 2
             */

            private String id;
            private String name;
            private int bikeCount;
            private List<Double> position;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getBikeCount() {
                return bikeCount;
            }

            public void setBikeCount(int bikeCount) {
                this.bikeCount = bikeCount;
            }

            public List<Double> getPosition() {
                return position;
            }

            public void setPosition(List<Double> position) {
                this.position = position;
            }
        }
        }
}