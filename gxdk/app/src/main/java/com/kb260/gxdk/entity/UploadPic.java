package com.kb260.gxdk.entity;

/**
 * @author KB260
 *         Created on  2017/11/14
 */

public class UploadPic {
    private int type;
    private String url;

    public UploadPic(int type, String url) {
        this.type = type;
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
