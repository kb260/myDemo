package com.kb260.bxjy.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */

public class PublicClassMultiItem implements MultiItemEntity {
    public static final int NO_CLASS_TITLE = 0;
    public static final int NO_CLASS_LIST = 1;
    public static final int CLASSING_TITLE = 2;
    public static final int CLASSING_LIST = 3;
    public static final int CLASSED_TITLE = 4;
    public static final int CLASSED_LIST = 5;
    private int itemType;
    private String title;
    private String time;
    private List<Teacher> teachers;
    private String price;
    private String number;

    public PublicClassMultiItem(int itemType, String title, String time, List<Teacher> teachers, String price, String number) {
        this.itemType = itemType;
        this.title = title;
        this.time = time;
        this.teachers = teachers;
        this.price = price;
        this.number = number;
    }


    public PublicClassMultiItem(int itemType) {
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
