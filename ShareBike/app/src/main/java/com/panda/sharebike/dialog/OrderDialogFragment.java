package com.panda.sharebike.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.model.entity.CreateBean;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.dialogactivity.CancelDialogActivity;
import com.panda.sharebike.ui.login.CertificationActivity;
import com.panda.sharebike.ui.login.LoginByPhoneActivity;
import com.panda.sharebike.ui.login.RechargeActivity;
import com.panda.sharebike.util.SpannableStringUtil;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/08/08
 * desc   :
 * version: 1.0
 */
public class OrderDialogFragment extends DialogFragment {

    // mUniqueFlag作用是唯一码,可以使返回时做判断
    private int mUniqueFlag = -1;
    private onOrderDialogListener mOnOrderDialogListener;
    private View view;

    public static OrderDialogFragment newInstance(String bikeId, double longtitude, double lattude, String address, String cost, String time, String distance) {
        OrderDialogFragment fragment = new OrderDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("bikeId", bikeId);
        bundle.putDouble("longtitude", longtitude);
        bundle.putDouble("lattude", lattude);
        bundle.putString("address", address);
        bundle.putString("cost", cost);
        bundle.putString("time", time);
        bundle.putString("distance", distance);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        dismiss();
    }

    public interface onOrderDialogListener {
        public abstract void OnOrderDialogListener(int mUniqueFlag);
    }


    // 旋转时候保存
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//            outState.putString("InputName", meditTextName.getText().toString());
//            outState.putString("InputHigh", meditTextHigh.getText().toString());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //设置dialog展示的位置
        final DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        final WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
        layoutParams.width = dm.widthPixels;
        layoutParams.height = getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin);
        layoutParams.gravity = Gravity.CENTER;
        getDialog().getWindow().setAttributes(layoutParams);
        //设置无标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View view = getActivity().getLayoutInflater().inflate(R.layout.view_order_sticker, null);
        setUI(view);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        //设置背景透明
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = 0.0f;
        window.setAttributes(params);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.view_order_sticker, null);
        builder.setView(view);
        setUI(view);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    private void setUI(View view) {
        if (view == null) {
            return;
        }
        final String bikeId = getArguments().getString("bikeId");
        final double longtitude = getArguments().getDouble("longtitude");
        final double lattude = getArguments().getDouble("lattude");
        final String address = getArguments().getString("address");
        String cost = getArguments().getString("cost");
        String time = getArguments().getString("time");
        String distance = getArguments().getString("distance");


        TextView order_title = (TextView) view.findViewById(R.id.order_title);
        TextView order_riding = (TextView) view.findViewById(R.id.tv_riding_order);
        TextView order_time = (TextView) view.findViewById(R.id.tv_time_order);
        TextView order_distance = (TextView) view.findViewById(R.id.tv_distance_order);
        Button order_btn = (Button) view.findViewById(R.id.btn_order_sticker);
        order_title.setText(address);
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOrderBike(bikeId, longtitude, lattude, 5000, address);
            }
        });
        SpannableStringUtil.setMainOrderTipString(cost, time, distance, order_riding, order_time, order_distance);//配置文字类型
    }

    /**
     * 预约用车
     */
    private void setOrderBike(String bikeId, double longtitude, double lattude, int maxDistance, final String address) {
        Api.getInstance().getDefault().getCreate(Config.TOKEN, bikeId, longtitude, lattude, maxDistance)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<CreateBean>>(new SubscriberListener<HttpResult<CreateBean>>() {
                    @Override
                    public void onSuccess(HttpResult<CreateBean> model) {
                        if (501 == model.getCode() && model.getMsg().equals("无押金")) {
                            Intent intent = new Intent(getActivity(), RechargeActivity.class);//无押金前往充值
                            startActivity(intent);
                            return;
                        }
                        if (501 == model.getCode() && model.getMsg().equals("会员未实名认证")) {
                            Intent intent = new Intent(getActivity(), CertificationActivity.class);//未实名前往实名
                            startActivity(intent);
                        }
                        if (401 == model.getCode()) {
                            // ToastUtils.showShort("请先登录");
                            Intent intent = new Intent(getActivity(), LoginByPhoneActivity.class);//未实名前往实名
                            startActivity(intent);
                            return;
                        }
                        if (model.isOk()) {//倒计时
                            Intent intent = new Intent(getContext(), CancelDialogActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("leftTime", model.getData().getLeftTime());
                            bundle.putString("address", address);
                            bundle.putString("bikeId", model.getData().getOrder().getBike().getId());
                            intent.putExtras(bundle);
                            startActivity(intent);
                            dismiss();
                        } else if (EmptyUtils.isNotEmpty(model.getMsg())) {
                            ToastUtils.showShort(model.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, getActivity(), false));
    }
}
