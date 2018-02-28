package com.kb260.gxdk.view.widget.dialog;


import android.os.Parcelable;

public interface ViewConvertListener extends Parcelable {
    void convertView(ViewHolder holder, BaseKBDialog dialog);

}
