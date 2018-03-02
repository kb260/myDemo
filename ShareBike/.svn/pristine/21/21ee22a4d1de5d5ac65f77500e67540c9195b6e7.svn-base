package com.panda.sharebike.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/08/03
 * desc   :场地还车
 * version: 1.0
 */
public class ReturnBikeBean implements Serializable {

    /**
     * code : 200
     * msg : null
     * data : {"locations":[{"id":"59847c2224f9b15b8c58e284","rideOrderId":"2a2f64c0-b5bd-468a-a9e0-925cd6691ea7","position":[120.25399804115298,30.20263641284256],"locateTime":1501854754436},{"id":"5984889424f9b15b8c58e285","rideOrderId":"2a2f64c0-b5bd-468a-a9e0-925cd6691ea7","position":[120.2708504872148,30.20555067014208],"locateTime":1501857940894}],"order":{"id":"2a2f64c0-b5bd-468a-a9e0-925cd6691ea7","state":"done","startTime":1501854754436,"endTime":1501857940894,"distance":1653.4549141870973,"cost":-0.02,"bike":{"id":"0000001","state":"normal","num":"0000001","bikeType":null,"version":"version","createTime":1501832960121,"lock":true,"bikeLock":{"id":"864501036491381","bikeId":"0000001","position":[120.26413266666665,30.204662666666664],"locateTime":1501833403489,"electricQuantity":400,"macAddress":"mac1","lock":true}},"member":{"id":"3","cellphone":"13478606965","nickname":"13478606965","avatar":"http://www.qiaoqidanche.com:91/qbike/upload/avatar/961501848410275.jpg","email":"","realname":"宁立森","idCard":"450721199209140974","realnameAuth":true,"registerTime":1501834479572,"lastActivateTime":1501846763200},"duration":3186458}}
     */

    /**
     * locations : [{"id":"59847c2224f9b15b8c58e284","rideOrderId":"2a2f64c0-b5bd-468a-a9e0-925cd6691ea7","position":[120.25399804115298,30.20263641284256],"locateTime":1501854754436},{"id":"5984889424f9b15b8c58e285","rideOrderId":"2a2f64c0-b5bd-468a-a9e0-925cd6691ea7","position":[120.2708504872148,30.20555067014208],"locateTime":1501857940894}]
     * order : {"id":"2a2f64c0-b5bd-468a-a9e0-925cd6691ea7","state":"done","startTime":1501854754436,"endTime":1501857940894,"distance":1653.4549141870973,"cost":-0.02,"bike":{"id":"0000001","state":"normal","num":"0000001","bikeType":null,"version":"version","createTime":1501832960121,"lock":true,"bikeLock":{"id":"864501036491381","bikeId":"0000001","position":[120.26413266666665,30.204662666666664],"locateTime":1501833403489,"electricQuantity":400,"macAddress":"mac1","lock":true}},"member":{"id":"3","cellphone":"13478606965","nickname":"13478606965","avatar":"http://www.qiaoqidanche.com:91/qbike/upload/avatar/961501848410275.jpg","email":"","realname":"宁立森","idCard":"450721199209140974","realnameAuth":true,"registerTime":1501834479572,"lastActivateTime":1501846763200},"duration":3186458}
     */

    private OrderBean order;
    private List<LocationsBean> locations;

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public List<LocationsBean> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationsBean> locations) {
        this.locations = locations;
    }

    public static class OrderBean {
        /**
         * id : 2a2f64c0-b5bd-468a-a9e0-925cd6691ea7
         * state : done
         * startTime : 1501854754436
         * endTime : 1501857940894
         * distance : 1653.4549141870973
         * cost : -0.02
         * bike : {"id":"0000001","state":"normal","num":"0000001","bikeType":null,"version":"version","createTime":1501832960121,"lock":true,"bikeLock":{"id":"864501036491381","bikeId":"0000001","position":[120.26413266666665,30.204662666666664],"locateTime":1501833403489,"electricQuantity":400,"macAddress":"mac1","lock":true}}
         * member : {"id":"3","cellphone":"13478606965","nickname":"13478606965","avatar":"http://www.qiaoqidanche.com:91/qbike/upload/avatar/961501848410275.jpg","email":"","realname":"宁立森","idCard":"450721199209140974","realnameAuth":true,"registerTime":1501834479572,"lastActivateTime":1501846763200}
         * duration : 3186458
         */

        private String id;
        private String state;
        private long startTime;
        private long endTime;
        private double distance;
        private double cost;
        private BikeBean bike;
        private MemberBean member;
        private int duration;

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

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
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

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public static class BikeBean {
            /**
             * id : 0000001
             * state : normal
             * num : 0000001
             * bikeType : null
             * version : version
             * createTime : 1501832960121
             * lock : true
             * bikeLock : {"id":"864501036491381","bikeId":"0000001","position":[120.26413266666665,30.204662666666664],"locateTime":1501833403489,"electricQuantity":400,"macAddress":"mac1","lock":true}
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
                 * bikeId : 0000001
                 * position : [120.26413266666665,30.204662666666664]
                 * locateTime : 1501833403489
                 * electricQuantity : 400
                 * macAddress : mac1
                 * lock : true
                 */

                private String id;
                private String bikeId;
                private long locateTime;
                private int electricQuantity;
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
             * id : 3
             * cellphone : 13478606965
             * nickname : 13478606965
             * avatar : http://www.qiaoqidanche.com:91/qbike/upload/avatar/961501848410275.jpg
             * email :
             * realname : 宁立森
             * idCard : 450721199209140974
             * realnameAuth : true
             * registerTime : 1501834479572
             * lastActivateTime : 1501846763200
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

    public static class LocationsBean {
        /**
         * id : 59847c2224f9b15b8c58e284
         * rideOrderId : 2a2f64c0-b5bd-468a-a9e0-925cd6691ea7
         * position : [120.25399804115298,30.20263641284256]
         * locateTime : 1501854754436
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
