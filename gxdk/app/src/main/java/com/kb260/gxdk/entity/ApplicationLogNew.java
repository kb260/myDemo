package com.kb260.gxdk.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author KB260
 *         Created on  2017/11/24
 */

public class ApplicationLogNew implements Serializable{


    /**
     * record : []
     * sign : 0
     */

    private String sign;
    private List<ApplicationLog> record;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<ApplicationLog> getRecord() {
        return record;
    }

    public void setRecord(List<ApplicationLog> record) {
        this.record = record;
    }
}
