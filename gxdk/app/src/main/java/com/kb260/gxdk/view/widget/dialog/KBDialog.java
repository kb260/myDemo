package com.kb260.gxdk.view.widget.dialog;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

/**
 * Created by kb260 on 2017/9/5.
 * Email: work260@outlook.com
 */

public class KBDialog extends BaseKBDialog {

    private ViewConvertListener convertListener;

    public static KBDialog init() {
        return new KBDialog();
    }

    @Override
    public int intLayoutId() {
        return layoutId;
    }

    @Override
    public void convertView(ViewHolder holder, BaseKBDialog dialog) {
        if (convertListener != null) {
            convertListener.convertView(holder, dialog);
        }
    }

    public KBDialog setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public KBDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            convertListener =  savedInstanceState.getParcelable("listener");
        }
    }

    /**
     * 保存接口
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("listener", convertListener);
    }
}
