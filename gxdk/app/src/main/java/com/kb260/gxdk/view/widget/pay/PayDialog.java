package com.kb260.gxdk.view.widget.pay;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.kb260.gxdk.R;
import com.kb260.gxdk.view.widget.PayPwdEditText;

/**
 * @author  KB260
 * Created on  2017/11/29
 */

public class PayDialog extends BaseDialog{

    private EditFinish editFinish;
    private PayPwdEditText payPwdEditText;

    public PayDialog(Context context,EditFinish editFinish) {
        super(context);
        this.editFinish = editFinish;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_dialog_lyaout);
        payPwdEditText =  findViewById(R.id.ppet);

        payPwdEditText.initStyle(R.drawable.edit_num_bg_red, 6, 0.33f, R.color.colorAccent, R.color.colorAccent, 20);
        payPwdEditText.setOnTextFinishListener(new PayPwdEditText.OnTextFinishListener() {
            @Override
            public void onFinish(String str) {//密码输入完后的回调
                editFinish.onFinish(str);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                payPwdEditText.setFocus();
            }
        }, 100);

    }

    public interface EditFinish{
        void onFinish(String str);
    };

    public void clear(){
        payPwdEditText.clearText();
    }

}
