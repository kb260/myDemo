package com.kb260.bxjy.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */

public class CourseSortsMultiItem implements MultiItemEntity {
    public static final int SUBJECT_TITLE = 0;
    public static final int SUBJECT_LIST = 1;
    public static final int SORT_LIST = 2;
    public static final int FREE_LIST = 3;
    public static final int PAY_LIST = 4;
    public static final int TEACHER_TITLE = 5;
    public static final int TEACHER_LIST = 6;
    public static final int NO_DATA = 7;
    public static final int NO_DATA404 = 8;
    private int itemType;
    private String title;
    private String content;
    private String time;
    private String price;
    private String number;
    private String img;


    public CourseSortsMultiItem(int itemType) {
        this.itemType = itemType;
    }

    public CourseSortsMultiItem(int itemType, String title) {
        this.itemType = itemType;
        this.title = title;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
