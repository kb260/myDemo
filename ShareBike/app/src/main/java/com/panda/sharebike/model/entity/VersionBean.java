package com.panda.sharebike.model.entity;

/**
 * @author KB260
 *         Created on  2018/2/2
 */

public class VersionBean {

    /**
     * id : 5a74200c887d2b492078ca3e
     * appType : ios
     * minVersion : 1.0
     * maxVersion : 1.0
     * currentVersion : 1.0
     * downLoadUrl : http://www.okbikes.cn/app/okbike.apk
     * createTime : 1517559820765
     * modifyTime : 1517559820765
     */

    private String id;
    private String appType;
    private String minVersion;
    private String maxVersion;
    private String currentVersion;
    private String downLoadUrl;
    private long createTime;
    private long modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(String minVersion) {
        this.minVersion = minVersion;
    }

    public String getMaxVersion() {
        return maxVersion;
    }

    public void setMaxVersion(String maxVersion) {
        this.maxVersion = maxVersion;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getDownLoadUrl() {
        return downLoadUrl;
    }

    public void setDownLoadUrl(String downLoadUrl) {
        this.downLoadUrl = downLoadUrl;
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
}
