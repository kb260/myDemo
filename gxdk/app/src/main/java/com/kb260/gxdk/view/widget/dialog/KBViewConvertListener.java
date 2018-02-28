package com.kb260.gxdk.view.widget.dialog;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kb260 on 2017/9/5.
 * Email: work260@outlook.com
 */

public class KBViewConvertListener implements ViewConvertListener{
    @Override
    public void convertView(ViewHolder holder, BaseKBDialog dialog) {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    //在实现上面的接口方法后，接下来还需要执行反序列化，定义一个变量，并重新定义其中的部分方法
    public static final Parcelable.Creator<KBViewConvertListener> CREATOR = new Parcelable.Creator<KBViewConvertListener>(){


        @Override
        public KBViewConvertListener createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public KBViewConvertListener[] newArray(int size) {
            return new KBViewConvertListener[0];
        }
    };

}
