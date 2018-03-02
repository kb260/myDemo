package com.panda.sharebike.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/07/26
 * desc   :充值明细
 * version: 1.0
 */
public class WaterMessageBean implements Serializable {

    /**
     * pageNum : 1
     * totalItemsCount : 1
     * pageItemsCount : 10
     * items : [{"source":"buyBeenz","memberId":"4f42297e-0aff-45e0-99da-ebe9b4d686a4","reason":"充值费用","deltaFee":100,"logTime":1501049228296}]
     * hasPreviousPage : false
     * hasNextPage : false
     * pageCount : 1
     */

    private int pageNum;
    private int totalItemsCount;
    private int pageItemsCount;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int pageCount;
    private List<ItemsBean> items;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalItemsCount() {
        return totalItemsCount;
    }

    public void setTotalItemsCount(int totalItemsCount) {
        this.totalItemsCount = totalItemsCount;
    }

    public int getPageItemsCount() {
        return pageItemsCount;
    }

    public void setPageItemsCount(int pageItemsCount) {
        this.pageItemsCount = pageItemsCount;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * source : buyBeenz
         * memberId : 4f42297e-0aff-45e0-99da-ebe9b4d686a4
         * reason : 充值费用
         * deltaFee : 100
         * logTime : 1501049228296
         */

        private String source;
        private String memberId;
        private String reason;
        private double deltaFee;
        private long logTime;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public double getDeltaFee() {
            return deltaFee;
        }

        public void setDeltaFee(int deltaFee) {
            this.deltaFee = deltaFee;
        }

        public long getLogTime() {
            return logTime;
        }

        public void setLogTime(long logTime) {
            this.logTime = logTime;
        }
    }
}