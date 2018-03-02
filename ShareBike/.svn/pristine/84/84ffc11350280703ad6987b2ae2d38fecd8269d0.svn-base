package com.panda.sharebike.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.panda.sharebike.R;


/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class CountDownTV extends TextView {

    private MyCount timer = null;

    private int second = 60;
    private String endString = "";
    private CountListener listener;

    public void setListener(CountListener listener) {
        this.listener = listener;
    }

    private class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            CountDownTV.this.setText(millisUntilFinished / 1000 + "S");
        }

        @Override
        public void onFinish() {
            timer.cancel();
            timer = null;
            CountDownTV.this.setText(endString);
            CountDownTV.this.setEnabled(true);
            if (listener != null)
                listener.onFinishListener();
        }
    }

    public CountDownTV(Context context) {
        super(context);
    }

    public CountDownTV(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public CountDownTV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    void initView(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CountDownTV);
        second = array.getInt(R.styleable.CountDownTV_second, 60);
        endString = array.getString(R.styleable.CountDownTV_end_string);
        array.recycle();
        setBackground(ContextCompat.getDrawable(context, R.drawable.bg_count_tv_selector));
        setTextColor(ContextCompat.getColor(context, R.color.enable_color));
        setGravity(Gravity.CENTER);
    }

    /**
     * 开始计时器
     */
    public void start(int time) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new MyCount(time * 1000, 1000);
        timer.start();
        setEnabled(false);
    }

    /**
     * 停止计时器
     */
    public void stop() {
        if (timer != null) {
            timer.onFinish();
        }
    }

    public interface CountListener {
        void onFinishListener();
    }
}
