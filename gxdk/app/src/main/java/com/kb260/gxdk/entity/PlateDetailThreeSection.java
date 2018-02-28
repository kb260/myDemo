package com.kb260.gxdk.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author KB260
 *         Created on  2017/12/5
 */

public class PlateDetailThreeSection extends SectionEntity<PlateDetailThree.DataBean.ChexingListBean> {
    public PlateDetailThreeSection(boolean isHeader, String header) {
        super(isHeader, header);
        this.header = header;
    }

    public PlateDetailThreeSection(PlateDetailThree.DataBean.ChexingListBean t) {
        super(t);
    }

}
