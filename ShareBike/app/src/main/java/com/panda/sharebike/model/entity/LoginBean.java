package com.panda.sharebike.model.entity;

import java.io.Serializable;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/07/17
 * desc   :
 * version: 1.0
 */
public class LoginBean implements Serializable {


    private boolean realnameAuth;
    private SessionBean session;
    private String nickname;
    private String avatar;
    private boolean hasPayedDeposit;

    public boolean isRealnameAuth() {
        return realnameAuth;
    }

    public void setRealnameAuth(boolean realnameAuth) {
        this.realnameAuth = realnameAuth;
    }

    public SessionBean getSession() {
        return session;
    }

    public void setSession(SessionBean session) {
        this.session = session;
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

    public boolean isHasPayedDeposit() {
        return hasPayedDeposit;
    }

    public void setHasPayedDeposit(boolean hasPayedDeposit) {
        this.hasPayedDeposit = hasPayedDeposit;
    }

    public static class SessionBean {
        /**
         * token : 653c24ed-3612-4cf3-9326-ad0fb5723d5c
         * status : loggedmember
         * memberId : 09c08e5f-d750-47de-b971-91cf9a0032d7
         * lastActiveTime : 1500367262405
         */

        private String token;
        private String status;
        private String memberId;
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

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
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
