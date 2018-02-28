package com.kb260.gxdk.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

/**
 * @author KB260
 *         Created on  2017/12/5
 */

public class PlateDetailNewSection extends SectionEntity<PlateDetailNew.PinpaiListBean.XilieBean> {
    public PlateDetailNewSection(boolean isHeader, String header) {
        super(isHeader, header);
        this.header = header;
    }

    public PlateDetailNewSection(PlateDetailNew.PinpaiListBean.XilieBean t) {
        super(t);
    }

}
