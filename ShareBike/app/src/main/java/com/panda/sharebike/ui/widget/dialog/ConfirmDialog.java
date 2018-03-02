package com.panda.sharebike.ui.widget.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.blankj.utilcode.util.ToastUtils;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.model.entity.Bock;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.MainActivity;
import com.panda.sharebike.ui.deblocking.DeBlockingActivity;
import com.panda.sharebike.ui.login.LoginByPhoneActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author KB260
 *         Created on  2018/2/2
 */

public class ConfirmDialog extends BaseNiceDialog {
    public static OkListener okListener;

    public static ConfirmDialog newInstance(OkListener okListener1) {
        Bundle bundle = new Bundle();
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setArguments(bundle);
        okListener = okListener1;
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
    }

    @Override
    public int intLayoutId() {
        return R.layout.confirm_layout;
    }

    @Override
    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                okListener.isOk();
            }
        });
    }

    public interface OkListener{
        void isOk();
    };
}
