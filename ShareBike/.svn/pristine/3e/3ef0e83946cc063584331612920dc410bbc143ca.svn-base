package com.panda.sharebike.model.entity;

import java.io.Serializable;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/07/17
 * desc   :
 * version: 1.0
 */
public class RegisteredBean implements Serializable {

    /**
     * session : {"token":"66a15768-e732-456c-816d-af91f8d11136","status":"loggedmember","memberId":"c828f2a8-99f8-4829-bd1d-456106d58944","lastActiveTime":1500364216773}
     * nickname : 13478616961
     * avatar : /qbike/img/defaut_avatar.png
     */

    private SessionBean session;
    private String nickname;
    private String avatar;

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

    public static class SessionBean {
        /**
         * token : 66a15768-e732-456c-816d-af91f8d11136
         * status : loggedmember
         * memberId : c828f2a8-99f8-4829-bd1d-456106d58944
         * lastActiveTime : 1500364216773
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
