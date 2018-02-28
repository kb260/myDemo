package com.kb260.bxjy.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */

public class MoreMultiItem implements MultiItemEntity {
    public static final int HOME_LIVING = 0;
    public static final int HOME_HIT = 1;
    public static final int HOME_TEACHER = 2;
    public static final int PUBLIC_CLASSED = 3;
    public static final int PUBLIC_CLASSING = 4;
    public static final int PUBLIC_CLASS_SOON = 5;
    private int itemType;
    private String title;
    private String time;
    private List<Teacher> teachers;
    private String price;
    private String number;
    private String content;
    private String teacherScore;
    private String teacherName;
    private String teacherIcon;



    public MoreMultiItem(int itemType) {
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

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
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

    public static class Teacher{
        public Teacher(String img, String name) {
            this.img = img;
            this.name = name;
        }

        private String img;
        private String name;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
