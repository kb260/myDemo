package com.panda.sharebike.model.entity;

/**
 * @author KB260
 *         Created on  2018/2/2
 */

public class GuideBean {

    /**
     * id : 5a73f8c5887d2b39b0f9c52d
     * content : 尊敬的用户:您好
     * createTime : 1517549764994
     * modifyTime : 1517549764994
     * guideType : header
     */

    private String id;
    private String content;
    private long createTime;
    private long modifyTime;
    private String guideType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getGuideType() {
        return guideType;
    }

    public void setGuideType(String guideType) {
        this.guideType = guideType;
    }
}
