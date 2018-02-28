package com.kb260.gxdk.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.kb260.gxdk.app.MyActivityManager;
import com.kb260.gxdk.utils.LoginUtil;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.account.LoginActivity;
import com.orhanobut.logger.Logger;
import cn.jpush.android.api.JPushInterface;

/**
 * @author KB260
 *         Created on  2017/12/11
 */

public class MyNotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.e("推送");
        Bundle bundle = intent.getExtras();
        type(intent.getAction(),bundle,context);
    }

    private void type(String action,Bundle bundle,Context context){
        switch (action) {
            case KBJPushInterface.ACTION_REGISTRATION_ID:
                Logger.e("推送1");
                break;
            case KBJPushInterface.ACTION_MESSAGE_RECEIVED:
                Logger.e("推送2");
                String extras = bundle.getString(JPushInterface.EXTRA_MESSAGE);
                Logger.e("EXTRA_EXTRA=" + extras);
                if (extras.equals("用户在其它手机上登录，请确认是否是本人操作。")){
                    ToastUtil.showInfoLong("您的帐号在异地登录，请确认是否是本人操作，如不是请及时更改密码，以免数据缺失！");
                    LoginUtil.exitLogin(MyActivityManager.getInstance().getCurrentActivity());
                    LoginActivity.start(MyActivityManager.getInstance().getCurrentActivity());
                }
                break;
            case KBJPushInterface.ACTION_NOTIFICATION_RECEIVED:
                Logger.e("推送3");
                String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                String content = bundle.getString(JPushInterface.EXTRA_ALERT);
                int notificationId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
                String fileHtml = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_PATH);
                String fileStr = bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_RES);
                //String[] fileNames = fileStr.split(",");
                String file = bundle.getString(JPushInterface.EXTRA_MSG_ID);
                String bigText = bundle.getString(JPushInterface.EXTRA_BIG_TEXT);
                String bigPicPath = bundle.getString(JPushInterface.EXTRA_BIG_PIC_PATH);
                String inboxJson = bundle.getString(JPushInterface.EXTRA_INBOX);
                String prio = bundle.getString(JPushInterface.EXTRA_NOTI_PRIORITY);
                String prio1 = bundle.getString(JPushInterface.EXTRA_NOTI_CATEGORY);
                String prio2 = bundle.getString("extra");
                if (title.equals("下线通知")) {

                }
                break;
            case KBJPushInterface.ACTION_NOTIFICATION_OPENED:
                Logger.e("推送4");
                openNotification(context, bundle);
                break;
            case KBJPushInterface.ACTION_NOTIFICATION_CLICK_ACTION:
                Logger.e("推送5");
                openNotification(context, bundle);
                break;
            case KBJPushInterface.ACTION_CONNECTION_CHANGE:
                Logger.e("推送6");
                break;
            default:
                break;
        }
    }

    private void openNotification(Context context, Bundle bundle) {
        /*try {
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            JSONObject object = new JSONObject(extras);
            String id = object.optString("articleId");
            if (!StringUtils.isEmpty(id)) {
                Intent mIntent = new Intent(context, ServiceDetailActivity.class);
                mIntent.putExtras(bundle);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mIntent.putExtra(Contact.ID, Integer.valueOf(id));
                context.startActivity(mIntent);
            } else {
                Intent mIntent = new Intent(context, MyMessageActivity.class);
                mIntent.putExtras(bundle);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
