package com.kb260.gxdk.model;

import java.util.List;

/**
 * Created by kb260 on 2017/9/29.
 * Email: work260@outlook.com
 */

public class IntegralDetail {

    private List<DetailBean> detail;

    public List<DetailBean> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailBean> detail) {
        this.detail = detail;
    }

    public static class DetailBean {
        /**
         * id : 1
         * userid : 23
         * flag : 0
         * createtime : 334
         * score : 12.0
         * reserve : 积分充值
         * type : 1
         */

        private int id;
        private int userid;
        private String flag;
        private String createtime;
        private double score;
        private String reserve;
        private String type;

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

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
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
    }
}
