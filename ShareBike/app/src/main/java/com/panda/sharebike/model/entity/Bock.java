package com.panda.sharebike.model.entity;

import java.util.List;

/**
 * @author KB260
 *         Created on  2018/2/7
 */

public class Bock {

    /**
     * id : 5388966
     * state : malfunction
     * num : 05000696711
     * bikeType : {"id":"5a65a07b37304d5b2caeb43e","type":"OKbike","createTime":1516609658996}
     * version : 1.0
     * createTime : 1516784659026
     * lock : false
     * bikeLock : {"id":"171110000301","bikeId":"5388966","secretKey":"417A686F6E6731353132333830303034","position":[120.075992,30.2895],"locateTime":1517994296639,"reason":"关锁上传","electricQuantity":56,"macAddress":"3C:97:11:6F:71:CB","password":"303030303330","lock":true}
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
         * secretKey : 417A686F6E6731353132333830303034
         * position : [120.075992,30.2895]
         * locateTime : 1517994296639
         * reason : 关锁上传
         * electricQuantity : 56
         * macAddress : 3C:97:11:6F:71:CB
         * password : 303030303330
         * lock : true
         */

        private String id;
        private String bikeId;
        private String secretKey;
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

        public String getSecretKey() {
            return secretKey;
        }

        public void setSecretKey(String secretKey) {
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
}
