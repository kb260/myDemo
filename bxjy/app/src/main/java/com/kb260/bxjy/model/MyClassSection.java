package com.kb260.bxjy.model;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.kb260.bxjy.model.entity.MyClass;

/**
 * @author KB260
 *         Created on  2018/2/6
 */

public class MyClassSection extends SectionEntity<MyClass> {
    public MyClassSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MyClassSection(MyClass myClass) {
        super(myClass);
    }
}
