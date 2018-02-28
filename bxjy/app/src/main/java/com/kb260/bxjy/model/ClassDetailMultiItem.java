package com.kb260.bxjy.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */

public class ClassDetailMultiItem implements MultiItemEntity {
    public static final int INTRODUCTION = 0;
    public static final int CLASS_SCHEDULE = 1;
    public static final int TEACHER = 2;
    public static final int NO_DATA = 3;
    public static final int NO_DATA404 = 4;
    private int itemType;
    private String title;
    private String time;
    private String price;
    private String introduction;
    private String content;
    private String teacherScore;
    private String teacherName;
    private String teacherIcon;



    public ClassDetailMultiItem(int itemType) {
        this.itemType = itemType;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTeacherScore() {
        return teacherScore;
    }

    public void setTeacherScore(String teacherScore) {
        this.teacherScore = teacherScore;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherIcon() {
        return teacherIcon;
    }

    public void setTeacherIcon(String teacherIcon) {
        this.teacherIcon = teacherIcon;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
