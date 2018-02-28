package com.kb260.bxjy.api;

import android.content.Context;

import com.kb260.bxjy.R;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.NetworkUtils;
import com.kb260.bxjy.weight.MyProgressDialog;
import com.kb260.bxjy.weight.dialog.NiceDialog;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @author  KB260
 * Created on  2017/12/28
 */
public abstract class RxSubscriber<T> implements Observer<T> {
    private Context mContext;
    private MyProgressDialog dialog;
    private boolean isDialog = true;

    RxSubscriber(Context context, String msg) {
        this.mContext = context;
    }
    protected RxSubscriber(Context context) {
        this(context, context.getString(R.string.please_wait));
    }

    public RxSubscriber(Context context, boolean isDialog) {
        this(context, context.getString(R.string.please_wait));
        this.isDialog = isDialog;
    }


    @Override
    public void onSubscribe(Disposable d) {
        if (dialog == null && isDialog) {
            initDialog();
        }
    }


    @Override
    public void onComplete() {
        hintDialog();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        hintDialog();
        String err;
        if (!NetworkUtils.isConnected()) {
            err = mContext.getString(R.string.network_error);
            error(err);
        }else if (e instanceof ServerException) {
            err = e.getMessage();
            error(err);
        } else {
            err = mContext.getString(R.string.request_failure);
            error(err);
        }
    }

    private void initDialog() {
        if (dialog == null){
            dialog = new MyProgressDialog(mContext);
        }
        dialog.show();
    }

    private void hintDialog() {
        if (null != dialog && dialog.isShowing()) {
            dialog.cancel();
        }
    }


    @Override
    public void onNext(T t) {
        success(t);
    }

    /**
     *
     * @param t
     */
    protected abstract void success(T t);

    /**
     *
     * @param msg
     */
    protected abstract void error(String msg);
}
