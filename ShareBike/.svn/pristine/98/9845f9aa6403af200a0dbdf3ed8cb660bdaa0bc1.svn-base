package com.panda.sharebike.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.panda.sharebike.util.NetUtil;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/08/02
 * desc   :
 * version: 1.0
 */
public class NetBroadcastReceiver extends BroadcastReceiver {
    private NetEvevtListener mNetEvevtListener = null;

    public void setmNetEvevtListener(NetEvevtListener mNetEvevtListener) {
        this.mNetEvevtListener = mNetEvevtListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //    if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtil.getNetWorkState(context);
            // 接口回调传过去状态的类型
        if (mNetEvevtListener != null) {
            mNetEvevtListener.onNetChange(netWorkState);
        }
        //     }
    }


    // 自定义接口
    public interface NetEvevtListener {
        void onNetChange(int netMobile);
    }
}
