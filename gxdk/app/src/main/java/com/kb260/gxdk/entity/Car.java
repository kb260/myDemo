package com.kb260.gxdk.entity;

/**
 * Created by kb260 on 2017/10/10.
 * Email: work260@outlook.com
 */

public class Car {
    public Car(String carName, String firstLetter) {
        this.carName = carName;
        this.firstLetter = firstLetter;
    }

    private String carName;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    private String firstLetter;
}
