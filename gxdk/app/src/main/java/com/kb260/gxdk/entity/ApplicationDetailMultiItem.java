package com.kb260.gxdk.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author  KB260
 * Created on  2017/11/16
 */

public class ApplicationDetailMultiItem implements MultiItemEntity {
    public static final int TEXT_KEY_VALUE = 0;
    public static final int BUTTON_ONE = 1;
    public static final int BUTTON_TWO = 2;
    public static final int LINE = 3;
    public static final int RESULT = 4;
    private int itemType;
    private String key;
    private String value;
    private String button;

    public ApplicationDetailMultiItem(int itemType, String key, String value) {
        this.itemType = itemType;
        this.key = key;
        this.value = value;
    }



    public ApplicationDetailMultiItem(int itemType, String button) {
        this.itemType = itemType;
        this.button = button;
    }

    public ApplicationDetailMultiItem(int itemType) {
        this.itemType = itemType;
    }


    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

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

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }


}
