package com.panda.sharebike.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.panda.sharebike.R;

/**
 * Created by Administrator on 2017/4/24.
 * 公告
 */

public class AdviseDialogFragment extends DialogFragment {

    public static AdviseDialogFragment getInstance() {
        return FirstQuote.instance;
    }

    //在第一次被引用时被加载
    static class FirstQuote {
        private static AdviseDialogFragment instance = new AdviseDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.dialog_title_mseeage, container);

        LinearInterpolator lin = new LinearInterpolator();
        TextView tvContent = view.findViewById(R.id.kb_toast);
        tvContent.setText(getTag());
        final TextView finish = (TextView) view.findViewById(R.id.tv_finsh);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtils.getInstance("dialog").put("first_dialog", false);
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SPUtils.getInstance("dialog").put("first_dialog", false);
        dismiss();
    }
}
