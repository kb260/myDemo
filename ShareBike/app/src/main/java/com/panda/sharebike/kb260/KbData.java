package com.panda.sharebike.kb260;

import java.io.Serializable;
import java.util.List;

/**
 * @author KB260
 *         Created on  2018/1/30
 */

public class KbData implements Serializable{


    /**
     * duration : 60000
     * lockInfo : {"id":"171110000301","bikeId":"5388966","secretKey":null,"position":[120.080925,30.285212],"locateTime":1517275869444,"reason":"1111","electricQuantity":68,"macAddress":"3C:97:11:6F:71:CB","password":null,"lock":true}
     * parks : [{"bikePark":{"id":"5a6987ea12c7394a0089402b","position":[120.076513,30.288853],"name":"中节能·西溪首座(东门)"},"distance":143.8225225933042}]
     * locations : [{"id":null,"rideOrderId":"ab78b721-8c18-44ce-b04d-4cfe5c80ab4d","position":[120.075504,30.289807],"locateTime":1517275890882}]
     * state : riding
     * memberRideConfig : {"id":"1","deposit":0.01,"bikeParkScope":150,"maxBikePark":30,"subscribeDuration":900000,"rental":0,"timeUnit":1800000}
     * order : {"id":"ab78b721-8c18-44ce-b04d-4cfe5c80ab4d","state":"riding","startTime":1517275890882,"endTime":0,"distance":0,"cost":0,"bike":{"id":"5388966","state":"riding","num":"050006967","bikeType":{"id":"5a65a07b37304d5b2caeb43e","type":"OKbike","createTime":1516609658996},"version":"1.0","createTime":1516784659026,"lock":false,"bikeLock":{"id":"171110000301","bikeId":"5388966","secretKey":null,"position":[120.080925,30.285212],"locateTime":1517275869444,"reason":"1111","electricQuantity":68,"macAddress":"3C:97:11:6F:71:CB","password":null,"lock":true}},"member":{"id":"1","cellphone":"18268213183","nickname":"18268213183","avatar":"http://127.0.0.1:91/qbike/img/defaut_avatar.png","email":"","realname":"徐康康","idCard":"331082199209165037","realnameAuth":true,"registerTime":1516772843339,"lastActivateTime":1517275652755},"duration":-1517275890882}
     */

    private int duration;
    private LockInfoBean lockInfo;
    private String state;
    private MemberRideConfigBean memberRideConfig;
    private OrderBean order;
    private List<ParksBean> parks;
    private List<LocationsBean> locations;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LockInfoBean getLockInfo() {
        return lockInfo;
    }

    public void setLockInfo(LockInfoBean lockInfo) {
        this.lockInfo = lockInfo;
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

    public static class LockInfoBean {
        /**
         * id : 171110000301
         * bikeId : 5388966
         * secretKey : null
         * position : [120.080925,30.285212]
         * locateTime : 1517275869444
         * reason : 1111
         * electricQuantity : 68
         * macAddress : 3C:97:11:6F:71:CB
         * password : null
         * lock : true
         */

        private String id;
        private String bikeId;
        private Object secretKey;
        private long locateTime;
        private String reason;
        private int electricQuantity;
        private String macAddress;
        private String password;
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

        public Object getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(Object secretKey) {
            this.secretKey = secretKey;
        }

        public long getLocateTime() {
            return locateTime;
        }

        public void setLocateTime(long locateTime) {
            this.locateTime = locateTime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getElectricQuantity() {
            return electricQuantity;
        }

        public void setElectricQuantity(int electricQuantity) {
            this.electricQuantity = electricQuantity;
        }

        public String getMacAddress() {
            return macAddress;
        }

        public void setMacAddress(String macAddress) {
            this.macAddress = macAddress;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
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
         * bikeParkScope : 150
         * maxBikePark : 30
         * subscribeDuration : 900000
         * rental : 0
         * timeUnit : 1800000
         */

        private String id;
        private double deposit;
        private int bikeParkScope;
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

        public int getBikeParkScope() {
            return bikeParkScope;
        }

        public void setBikeParkScope(int bikeParkScope) {
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
         * id : ab78b721-8c18-44ce-b04d-4cfe5c80ab4d
         * state : riding
         * startTime : 1517275890882
         * endTime : 0
         * distance : 0
         * cost : 0
         * bike : {"id":"5388966","state":"riding","num":"050006967","bikeType":{"id":"5a65a07b37304d5b2caeb43e","type":"OKbike","createTime":1516609658996},"version":"1.0","createTime":1516784659026,"lock":false,"bikeLock":{"id":"171110000301","bikeId":"5388966","secretKey":null,"position":[120.080925,30.285212],"locateTime":1517275869444,"reason":"1111","electricQuantity":68,"macAddress":"3C:97:11:6F:71:CB","password":null,"lock":true}}
         * member : {"id":"1","cellphone":"18268213183","nickname":"18268213183","avatar":"http://127.0.0.1:91/qbike/img/defaut_avatar.png","email":"","realname":"徐康康","idCard":"331082199209165037","realnameAuth":true,"registerTime":1516772843339,"lastActivateTime":1517275652755}
         * duration : -1517275890882
         */

        private String id;
        private String state;
        private long startTime;
        private int endTime;
        private int distance;
        private int cost;
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

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
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
             * id : 5388966
             * state : riding
             * num : 050006967
             * bikeType : {"id":"5a65a07b37304d5b2caeb43e","type":"OKbike","createTime":1516609658996}
             * version : 1.0
             * createTime : 1516784659026
             * lock : false
             * bikeLock : {"id":"171110000301","bikeId":"5388966","secretKey":null,"position":[120.080925,30.285212],"locateTime":1517275869444,"reason":"1111","electricQuantity":68,"macAddress":"3C:97:11:6F:71:CB","password":null,"lock":true}
             */

            private String id;
            private String state;
            private String num;
            private BikeTypeBean bikeType;
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

            public BikeTypeBean getBikeType() {
                return bikeType;
            }

            public void setBikeType(BikeTypeBean bikeType) {
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

            public static class BikeTypeBean {
                /**
                 * id : 5a65a07b37304d5b2caeb43e
                 * type : OKbike
                 * createTime : 1516609658996
                 */

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
                 * id : 171110000301
                 * bikeId : 5388966
                 * secretKey : null
                 * position : [120.080925,30.285212]
                 * locateTime : 1517275869444
                 * reason : 1111
                 * electricQuantity : 68
                 * macAddress : 3C:97:11:6F:71:CB
                 * password : null
                 * lock : true
                 */

                private String id;
                private String bikeId;
                private Object secretKey;
                private long locateTime;
                private String reason;
                private int electricQuantity;
                private String macAddress;
                private Object password;
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

                public Object getSecretKey() {
                    return secretKey;
                }

                public void setSecretKey(Object secretKey) {
                    this.secretKey = secretKey;
                }

                public long getLocateTime() {
                    return locateTime;
                }

                public void setLocateTime(long locateTime) {
                    this.locateTime = locateTime;
                }

                public String getReason() {
                    return reason;
                }

                public void setReason(String reason) {
                    this.reason = reason;
                }

                public int getElectricQuantity() {
                    return electricQuantity;
                }

                public void setElectricQuantity(int electricQuantity) {
                    this.electricQuantity = electricQuantity;
                }

                public String getMacAddress() {
                    return macAddress;
                }

                public void setMacAddress(String macAddress) {
                    this.macAddress = macAddress;
                }

                public Object getPassword() {
                    return password;
                }

                public void setPassword(Object password) {
                    this.password = password;
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
             * id : 1
             * cellphone : 18268213183
             * nickname : 18268213183
             * avatar : http://127.0.0.1:91/qbike/img/defaut_avatar.png
             * email :
             * realname : 徐康康
             * idCard : 331082199209165037
             * realnameAuth : true
             * registerTime : 1516772843339
             * lastActivateTime : 1517275652755
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
         * bikePark : {"id":"5a6987ea12c7394a0089402b","position":[120.076513,30.288853],"name":"中节能·西溪首座(东门)"}
         * distance : 143.8225225933042
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
             * id : 5a6987ea12c7394a0089402b
             * position : [120.076513,30.288853]
             * name : 中节能·西溪首座(东门)
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
         * id : null
         * rideOrderId : ab78b721-8c18-44ce-b04d-4cfe5c80ab4d
         * position : [120.075504,30.289807]
         * locateTime : 1517275890882
         */

        private Object id;
        private String rideOrderId;
        private long locateTime;
        private List<Double> position;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
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
