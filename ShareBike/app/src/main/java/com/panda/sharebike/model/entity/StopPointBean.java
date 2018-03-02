package com.panda.sharebike.model.entity;

import java.io.Serializable;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/08/02
 * desc   :
 * version: 1.0
 */
public class StopPointBean implements Serializable {

    private String id;
    private double longtitude;
    private double latitude;
    private String name;
    private double distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
