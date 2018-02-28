package com.kb260.gxdk.api;

import android.content.Context;
import com.kb260.gxdk.R;
import com.kb260.gxdk.app.MyActivityManager;
import com.kb260.gxdk.utils.LoginUtil;
import com.kb260.gxdk.utils.NetworkUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.account.LoginActivity;
import com.kb260.gxdk.view.shop.LoadingDialog;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * @author  KB260
 * Created on  2017/12/28
 */
public abstract class RxSubscriber<T> implements Observer<T> {
    private Context mContext;
    private LoadingDialog dialog;
    private boolean isDialog = true;

    public RxSubscriber(Context context, String msg) {
        this.mContext = context;
    }

    public RxSubscriber(Context context) {
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
        String err = "";


        if (!NetworkUtils.isConnected()) {
            err = mContext.getString(R.string.network_error);
            error(err);
        } else if (e.getMessage()!=null &&(e.getMessage().equals(BaseModel.OFF_SITE) || e.getMessage().equals("token不一致"))){
            //异地登录
            showDialog();
        }else if (e instanceof ServerException) {
            err = e.getMessage();
            error(err);
        } else {
            err = mContext.getString(R.string.request_failure);
            error(err);
        }
    }

    private void initDialog() {
        dialog = new LoadingDialog(mContext);
        dialog.show();
    }

    private void hintDialog() {
        if (null != dialog && dialog.isShowing()) {
            dialog.cancel();
        }
    }


    @Override
    public void onNext(T t) {
        // token验证失败，重新登陆
        if (t instanceof String &&
                String.valueOf(t).equals(BaseModel.TOKEN_VERIFICATION_FAILED) ||
                String.valueOf(t).equals(BaseModel.TOKEN_PAST_DUE)) {
            return;
        }
        success(t);
    }

    protected abstract void success(T t);

    protected abstract void error(String msg);

    private void showDialog() {
        ToastUtil.showInfoLong("您的帐号在异地登录，请确认是否是本人操作，如不是请及时更改密码，以免数据缺失！");
        LoginUtil.exitLogin(MyActivityManager.getInstance().getCurrentActivity());
        LoginActivity.start(MyActivityManager.getInstance().getCurrentActivity());
    }
}
