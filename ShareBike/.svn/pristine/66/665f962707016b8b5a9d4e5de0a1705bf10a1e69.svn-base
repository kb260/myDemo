package com.panda.sharebike.model.entity;

import java.io.Serializable;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/07/23
 * desc   :
 * version: 1.0
 */
public class RentBean implements Serializable {

    /**
     * code : 200
     * data : {"state":"riding","order":{"id":"b4a69308-0b56-4fd0-bdf1-96462f283989","state":"riding","startTime":1500263182729,"endTime":0,"distance":0,"cost":2,"bike":{"id":"0000001","state":"riding","version":"version","lock":true,"electricquantity":0,"createTime":1499854233170},"member":{"id":"b07a3700-f737-4095-9cf2-729caafef1d2","cellphone":"8977","nickname":"68969","avatar":"/qbike/img/defaut_avatar.png","realname":"realname","idCard":"idCard","realnameAuth":true,"registerTime":1499852194020,"lastActivateTime":1499852194020}}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * state : riding
         * order : {"id":"b4a69308-0b56-4fd0-bdf1-96462f283989","state":"riding","startTime":1500263182729,"endTime":0,"distance":0,"cost":2,"bike":{"id":"0000001","state":"riding","version":"version","lock":true,"electricquantity":0,"createTime":1499854233170},"member":{"id":"b07a3700-f737-4095-9cf2-729caafef1d2","cellphone":"8977","nickname":"68969","avatar":"/qbike/img/defaut_avatar.png","realname":"realname","idCard":"idCard","realnameAuth":true,"registerTime":1499852194020,"lastActivateTime":1499852194020}}
         */

        private String state;
        private OrderBean order;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public static class OrderBean {
            /**
             * id : b4a69308-0b56-4fd0-bdf1-96462f283989
             * state : riding
             * startTime : 1500263182729
             * endTime : 0
             * distance : 0
             * cost : 2
             * bike : {"id":"0000001","state":"riding","version":"version","lock":true,"electricquantity":0,"createTime":1499854233170}
             * member : {"id":"b07a3700-f737-4095-9cf2-729caafef1d2","cellphone":"8977","nickname":"68969","avatar":"/qbike/img/defaut_avatar.png","realname":"realname","idCard":"idCard","realnameAuth":true,"registerTime":1499852194020,"lastActivateTime":1499852194020}
             */

            private String id;
            private String state;
            private long startTime;
            private int endTime;
            private int distance;
            private int cost;
            private BikeBean bike;
            private MemberBean member;

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

            public static class BikeBean {
                /**
                 * id : 0000001
                 * state : riding
                 * version : version
                 * lock : true
                 * electricquantity : 0
                 * createTime : 1499854233170
                 */

                private String id;
                private String state;
                private String version;
                private boolean lock;
                private int electricquantity;
                private long createTime;

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

                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }

                public boolean isLock() {
                    return lock;
                }

                public void setLock(boolean lock) {
                    this.lock = lock;
                }

                public int getElectricquantity() {
                    return electricquantity;
                }

                public void setElectricquantity(int electricquantity) {
                    this.electricquantity = electricquantity;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }
            }

            public static class MemberBean {
                /**
                 * id : b07a3700-f737-4095-9cf2-729caafef1d2
                 * cellphone : 8977
                 * nickname : 68969
                 * avatar : /qbike/img/defaut_avatar.png
                 * realname : realname
                 * idCard : idCard
                 * realnameAuth : true
                 * registerTime : 1499852194020
                 * lastActivateTime : 1499852194020
                 */

                private String id;
                private String cellphone;
                private String nickname;
                private String avatar;
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
    }
}
