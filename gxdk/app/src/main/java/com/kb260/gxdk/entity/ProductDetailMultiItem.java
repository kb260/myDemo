package com.kb260.gxdk.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author  KB260
 * Created on  2017/11/16
 */

public class ProductDetailMultiItem implements MultiItemEntity {
    public static final int TEXT_KEY_VALUE = 0;
    public static final int TEXT_KEY_VALUE_LONG = 2;
    public static final int TYPE = 1;
    public static final int BUTTON_ONE = 3;
    private int itemType;
    private String key;
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public ProductDetailMultiItem(int itemType, String key, String value) {
        this.itemType = itemType;
        this.key = key;
        this.value = value;
    }



    public ProductDetailMultiItem(int itemType, String type) {
        this.itemType = itemType;
        this.type = type;
    }
    public ProductDetailMultiItem(int itemType) {
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
        return type;
    }

    public void setButton(String button) {
        this.type = button;
    }


}
