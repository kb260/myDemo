package com.kb260.gxdk.model;

/**
 * @author  KB260
 * Created on  2017/11/13
 */

public class Feedback {
    private int type;
    private String url;
    private int drawable;

    public Feedback(int type, String url) {
        this.type = type;
        this.url = url;
    }

    public Feedback(int type, int drawable) {
        this.type = type;
        this.drawable = drawable;
    }
    public Feedback(int type) {
        this.type = type;
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

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
