package com.kb260.gxdk.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author  KB260
 * Created on  2017/11/16
 */

public class OrderDetailMultiItem implements MultiItemEntity {
    public static final int DESCRIPTION = 0;

    public OrderDetailMultiItem(int itemType, String key, String value) {
        this.itemType = itemType;
        this.key = key;
        this.value = value;
    }

    public OrderDetailMultiItem(int itemType, String key, String value, String status) {
        this.itemType = itemType;
        this.key = key;
        this.value = value;
        this.status = status;
    }

    public static final int LINE = 1;
    public static final int RESULT = 2;
    public static final int RESULT_DESCRIPTION = 3;
    public static final int BUTTON = 4;
    public static final int PHONE = 5;
    private int itemType;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public OrderDetailMultiItem(int itemType, String status) {
        this.itemType = itemType;
        this.status = status;
    }

    public OrderDetailMultiItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
