package com.panda.sharebike.model.entity;

import java.io.Serializable;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/07/17
 * desc   :游客接口，主要用于获取token
 * version: 1.0
 */
public class TouristsBean implements Serializable {
    /**
     * code : 200
     * msg : null
     * data : {"session":{"token":"5e23ab80-f4ee-4914-b3e6-7d239061de37","status":"guest","memberId":null,"lastActiveTime":1500283957291}}
     * <p>
     * session : {"token":"5e23ab80-f4ee-4914-b3e6-7d239061de37","status":"guest","memberId":null,"lastActiveTime":1500283957291}
     */

    private SessionBean session;

    public SessionBean getSession() {
        return session;
    }

    public void setSession(SessionBean session) {
        this.session = session;
    }

    public static class SessionBean {
        /**
         * token : 5e23ab80-f4ee-4914-b3e6-7d239061de37
         * status : guest
         * memberId : null
         * lastActiveTime : 1500283957291
         */

        private String token;
        private String status;
        private Object memberId;
        private long lastActiveTime;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getMemberId() {
            return memberId;
        }

        public void setMemberId(Object memberId) {
            this.memberId = memberId;
        }

        public long getLastActiveTime() {
            return lastActiveTime;
        }

        public void setLastActiveTime(long lastActiveTime) {
            this.lastActiveTime = lastActiveTime;
        }
    }
}
