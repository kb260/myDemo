package com.kb260.bxjy.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @author  KB260
 * Created on  2017/11/16
 */

public class GroupClassMultiItem implements MultiItemEntity {
    public static final int INTERNAL = 0;
    public static final int PUBLIC = 1;
    private int itemType;
    private String title;
    private String time;
    private List<Teacher> teachers;
    private String price;
    private String number;
    private String limit;
    private String remaining;

    public GroupClassMultiItem(int itemType, String title, String time, List<Teacher> teachers, String price, String number, String limit, String remaining) {
        this.itemType = itemType;
        this.title = title;
        this.time = time;
        this.teachers = teachers;
        this.price = price;
        this.number = number;
        this.limit = limit;
        this.remaining = remaining;
    }


    public GroupClassMultiItem(int itemType) {
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
    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
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
