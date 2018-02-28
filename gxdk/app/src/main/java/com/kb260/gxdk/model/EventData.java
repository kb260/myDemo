package com.kb260.gxdk.model;

/**
 * @author KB260
 *         Created on  2017/11/14
 */

public class EventData {
    private int type;
    private String data;
    private String id;

    public EventData(int type) {
        this.type = type;
    }

    public EventData(int type, String data) {
        this.type = type;
        this.data = data;
    }

    public EventData(int type, String data,String id) {
        this.type = type;
        this.data = data;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
