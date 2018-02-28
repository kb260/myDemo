package com.kb260.gxdk.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author  KB260
 * Created on  2017/11/16
 */

public class CharacterLogMultiItem implements MultiItemEntity {
    public static final int TYPE_DLS = 1;
    public static final int TYPE_YHJL = 0;

    private ApplicationLog applicationLog;
    private int itemType;

    public CharacterLogMultiItem(int itemType, ApplicationLog applicationLog) {
        this.itemType = itemType;
        this.applicationLog = applicationLog;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public ApplicationLog getApplicationLog() {
        return applicationLog;
    }

    public void setApplicationLog(ApplicationLog applicationLog) {
        this.applicationLog = applicationLog;
    }
}
