package com.panda.sharebike.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/07/26
 * desc   :
 * version: 1.0
 */
public class MessageListBean implements Serializable {

    /**
     * pageNum : 1
     * totalItemsCount : 1
     * pageItemsCount : 10
     * items : [{"id":"596db4f467ecaa0b00bd767a","memberId":"f0ae060e-d84a-42a9-99cd-292be3ba68b3","title":"title","textContent":"msg","msgTime":1500361972362}]
     */

    private int pageNum;
    private int totalItemsCount;
    private int pageItemsCount;
    private boolean hasNextPage;
    private boolean hasPreviousPage;

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

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

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * id : 596db4f467ecaa0b00bd767a
         * memberId : f0ae060e-d84a-42a9-99cd-292be3ba68b3
         * title : title
         * textContent : msg
         * msgTime : 1500361972362
         */

        private String id;
        private String memberId;
        private String title;
        private String textContent;
        private long msgTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTextContent() {
            return textContent;
        }

        public void setTextContent(String textContent) {
            this.textContent = textContent;
        }

        public long getMsgTime() {
            return msgTime;
        }

        public void setMsgTime(long msgTime) {
            this.msgTime = msgTime;
        }
    }
}
