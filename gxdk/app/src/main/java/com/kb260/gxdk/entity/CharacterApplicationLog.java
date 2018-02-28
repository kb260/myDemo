package com.kb260.gxdk.entity;

/**
 * @author KB260
 *         Created on  2017/11/23
 */

public class CharacterApplicationLog {
    private String key;
    private String value;

    public CharacterApplicationLog(String key, String value) {
        this.key = key;
        this.value = value;
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
}
