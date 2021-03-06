package com.panda.sharebike.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/08/08
 * desc   :
 * version: 1.0
 */
public class RideBikeStayStillBean implements Serializable {

    /**
     * code : 200
     * msg : null
     * data : {"duration":5773303,"parks":[{"bikePark":{"id":"p1","position":[120.2708504872148,30.20555067014208],"name":"p1"},"distance":257.9263903194046}],"locations":[{"id":"59893b3224f9b114f5d7b918","rideOrderId":"cf6163a2-1f7e-41df-bebd-2e94df75dddb","position":[120.26851415634154,30.202456759651145],"locateTime":1502165810647}],"state":"await","memberRideConfig":{"id":"1","deposit":0.01,"bikeParkScope":1000,"maxBikePark":30,"subscribeDuration":900000,"rental":0.01,"timeUnit":1800000},"order":{"id":"cf6163a2-1f7e-41df-bebd-2e94df75dddb","state":"riding","startTime":1502165810647,"endTime":0,"distance":0,"cost":0.04,"bike":{"id":"6964476","state":"riding","num":"6964476","bikeType":null,"version":"version","createTime":1502104569640,"lock":true,"bikeLock":{"id":"864501036491381","bikeId":"6964476","position":[120.268935004341,30.202546929254],"locateTime":1502159645691,"electricQuantity":390,"macAddress":"mac1","lock":true}},"member":{"id":"2","cellphone":"15669922623","nickname":"等待着","avatar":"http://www.qiaoqidanche.com:91/qbike/upload/avatar/841502164118354.jpg","email":"","realname":"宁立森","idCard":"450721199209140974","realnameAuth":true,"registerTime":1502160365479,"lastActivateTime":1502160365479},"duration":-1502165810647}}
     */

    /**
     * duration : 5773303
     * parks : [{"bikePark":{"id":"p1","position":[120.2708504872148,30.20555067014208],"name":"p1"},"distance":257.9263903194046}]
     * locations : [{"id":"59893b3224f9b114f5d7b918","rideOrderId":"cf6163a2-1f7e-41df-bebd-2e94df75dddb","position":[120.26851415634154,30.202456759651145],"locateTime":1502165810647}]
     * state : await
     * memberRideConfig : {"id":"1","deposit":0.01,"bikeParkScope":1000,"maxBikePark":30,"subscribeDuration":900000,"rental":0.01,"timeUnit":1800000}
     * order : {"id":"cf6163a2-1f7e-41df-bebd-2e94df75dddb","state":"riding","startTime":1502165810647,"endTime":0,"distance":0,"cost":0.04,"bike":{"id":"6964476","state":"riding","num":"6964476","bikeType":null,"version":"version","createTime":1502104569640,"lock":true,"bikeLock":{"id":"864501036491381","bikeId":"6964476","position":[120.268935004341,30.202546929254],"locateTime":1502159645691,"electricQuantity":390,"macAddress":"mac1","lock":true}},"member":{"id":"2","cellphone":"15669922623","nickname":"等待着","avatar":"http://www.qiaoqidanche.com:91/qbike/upload/avatar/841502164118354.jpg","email":"","realname":"宁立森","idCard":"450721199209140974","realnameAuth":true,"registerTime":1502160365479,"lastActivateTime":1502160365479},"duration":-1502165810647}
     */

    private long duration;
    private String state;
    private MemberRideConfigBean memberRideConfig;
    private OrderBean order;
    private List<ParksBean> parks;
    private List<LocationsBean> locations;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
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

    public List<LocationsBean> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationsBean> locations) {
        this.locations = locations;
    }

    public static class MemberRideConfigBean {
        /**
         * id : 1
         * deposit : 0.01
         * bikeParkScope : 1000.0
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
         * id : cf6163a2-1f7e-41df-bebd-2e94df75dddb
         * state : riding
         * startTime : 1502165810647
         * endTime : 0
         * distance : 0.0
         * cost : 0.04
         * bike : {"id":"6964476","state":"riding","num":"6964476","bikeType":null,"version":"version","createTime":1502104569640,"lock":true,"bikeLock":{"id":"864501036491381","bikeId":"6964476","position":[120.268935004341,30.202546929254],"locateTime":1502159645691,"electricQuantity":390,"macAddress":"mac1","lock":true}}
         * member : {"id":"2","cellphone":"15669922623","nickname":"等待着","avatar":"http://www.qiaoqidanche.com:91/qbike/upload/avatar/841502164118354.jpg","email":"","realname":"宁立森","idCard":"450721199209140974","realnameAuth":true,"registerTime":1502160365479,"lastActivateTime":1502160365479}
         * duration : -1502165810647
         */

        private String id;
        private String state;
        private long startTime;
        private int endTime;
        private double distance;
        private double cost;
        private BikeBean bike;
        private MemberBean member;
        private long duration;

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

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
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

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public static class BikeBean {
            /**
             * id : 6964476
             * state : riding
             * num : 6964476
             * bikeType : null
             * version : version
             * createTime : 1502104569640
             * lock : true
             * bikeLock : {"id":"864501036491381","bikeId":"6964476","position":[120.268935004341,30.202546929254],"locateTime":1502159645691,"electricQuantity":390,"macAddress":"mac1","lock":true}
             */

            private String id;
            private String state;
            private String num;
            private Object bikeType;
            private String version;
            private long createTime;
            private boolean lock;
            private BikeLockBean bikeLock;

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

            public Object getBikeType() {
                return bikeType;
            }

            public void setBikeType(Object bikeType) {
                this.bikeType = bikeType;
            }

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

            public boolean isLock() {
                return lock;
            }

            public void setLock(boolean lock) {
                this.lock = lock;
            }

            public BikeLockBean getBikeLock() {
                return bikeLock;
            }

            public void setBikeLock(BikeLockBean bikeLock) {
                this.bikeLock = bikeLock;
            }

            public static class BikeLockBean {
                /**
                 * id : 864501036491381
                 * bikeId : 6964476
                 * position : [120.268935004341,30.202546929254]
                 * locateTime : 1502159645691
                 * electricQuantity : 390.0
                 * macAddress : mac1
                 * lock : true
                 */

                private String id;
                private String bikeId;
                private long locateTime;
                private double electricQuantity;
                private String macAddress;
                private boolean lock;
                private List<Double> position;

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

                public void setLocateTime(long locateTime) {
                    this.locateTime = locateTime;
                }

                public double getElectricQuantity() {
                    return electricQuantity;
                }

                public void setElectricQuantity(double electricQuantity) {
                    this.electricQuantity = electricQuantity;
                }

                public String getMacAddress() {
                    return macAddress;
                }

                public void setMacAddress(String macAddress) {
                    this.macAddress = macAddress;
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
             * cellphone : 15669922623
             * nickname : 等待着
             * avatar : http://www.qiaoqidanche.com:91/qbike/upload/avatar/841502164118354.jpg
             * email :
             * realname : 宁立森
             * idCard : 450721199209140974
             * realnameAuth : true
             * registerTime : 1502160365479
             * lastActivateTime : 1502160365479
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
         * bikePark : {"id":"p1","position":[120.2708504872148,30.20555067014208],"name":"p1"}
         * distance : 257.9263903194046
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
             */

            private String id;
            private String name;
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

            public List<Double> getPosition() {
                return position;
            }

            public void setPosition(List<Double> position) {
                this.position = position;
            }
        }
    }

    public static class LocationsBean {
        /**
         * id : 59893b3224f9b114f5d7b918
         * rideOrderId : cf6163a2-1f7e-41df-bebd-2e94df75dddb
         * position : [120.26851415634154,30.202456759651145]
         * locateTime : 1502165810647
         */

        private String id;
        private String rideOrderId;
        private long locateTime;
        private List<Double> position;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRideOrderId() {
            return rideOrderId;
        }

        public void setRideOrderId(String rideOrderId) {
            this.rideOrderId = rideOrderId;
        }

        public long getLocateTime() {
            return locateTime;
        }

        public void setLocateTime(long locateTime) {
            this.locateTime = locateTime;
        }

        public List<Double> getPosition() {
            return position;
        }

        public void setPosition(List<Double> position) {
            this.position = position;
        }
    }
}
