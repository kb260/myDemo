package com.kb260.gxdk.entity;

/**
 * Created by kb260 on 2017/10/16.
 * Email: work260@outlook.com
 */

public class CheckedData {
    private String name;
    private boolean checked;

    public CheckedData(String name, boolean checked) {
        this.name = name;
        this.checked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
