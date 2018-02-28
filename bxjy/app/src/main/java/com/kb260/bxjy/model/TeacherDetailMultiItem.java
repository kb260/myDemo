package com.kb260.bxjy.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author  KB260
 * Created on  2017/11/16
 */

public class TeacherDetailMultiItem implements MultiItemEntity {
    public static final int EVALUATION = 0;
    public static final int ONLINE_CLASS = 1;
    public static final int NO_DATA = 2;
    public static final int NO_DATA404 = 3;
    private int itemType;
    private String title;
    private String time;
    private String price;
    private String number;
    private String studentContent;
    private String studentName;
    private String studentIcon;



    public TeacherDetailMultiItem(int itemType) {
        this.itemType = itemType;
    }

    public TeacherDetailMultiItem(int itemType, String studentContent, String studentName, String studentIcon) {
        this.itemType = itemType;
        this.studentContent = studentContent;
        this.studentName = studentName;
        this.studentIcon = studentIcon;
    }

    public TeacherDetailMultiItem(int itemType, String title, String time, String price, String number) {
        this.itemType = itemType;
        this.title = title;
        this.time = time;
        this.price = price;
        this.number = number;
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

    public String getStudentContent() {
        return studentContent;
    }

    public void setStudentContent(String studentContent) {
        this.studentContent = studentContent;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentIcon() {
        return studentIcon;
    }

    public void setStudentIcon(String studentIcon) {
        this.studentIcon = studentIcon;
    }
}
