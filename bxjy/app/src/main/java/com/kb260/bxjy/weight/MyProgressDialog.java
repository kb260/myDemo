package com.kb260.bxjy.weight;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.kb260.bxjy.R;

/**
 * @author KB260
 *         Created on  2018/2/25
 */

public class MyProgressDialog extends ProgressDialog {
    public MyProgressDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(false);
        setProgressStyle(STYLE_SPINNER);
        setMessage(context.getText(R.string.please_wait));
    }
}

