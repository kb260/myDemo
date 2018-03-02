package com.panda.sharebike.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.panda.sharebike.app.AppApplication;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * author : 宁立森
 * e-mail : byning2012@163.com
 * time   : 2017/08/01
 * desc   :
 * version: 1.0
 */
public class SSocketService extends Service {
    private Socket mSocket;

    private Boolean isConnected = true;

    @Override
    public void onCreate() {
        super.onCreate();
        AppApplication app = (AppApplication) AppApplication.getAppContext();
        //  mSocket = app.getSocket();
        mSocket.on(Socket.EVENT_CONNECT, onConnect);
        mSocket.on(Socket.EVENT_DISCONNECT, onDisconnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        //  mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);

//        mSocket.on("message", onNewMessage);
        // mSocket.connect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
//
        mSocket.off(Socket.EVENT_CONNECT, onConnect);
        mSocket.off(Socket.EVENT_DISCONNECT, onDisconnect);
        mSocket.off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        //mSocket.off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
//        mSocket.off("message", onNewMessage);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    public void excute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mSocket.connect();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("通过Binder得到Service的引用来调用Service内部的方法");
    }

    public class LocalBinder extends Binder {
        public SSocketService getService() {
            // Return this instance of LocalService so clients can call public methods
            return SSocketService.this;
        }
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e("socket", "socket连接成功");
//            if (isConnected) {
//                //当服务器收到消息给我回调
//                JSONObject object = new JSONObject();
//                try {
//                    object.put("type", "login");
//                    object.put("token", Config.TOKEN);
//                    mSocket.emit("message", object);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                isConnected = true;
//            }
        }

    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e("socket", "diconnected");
            isConnected = false;

        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.e("socket", "Error connecting");

        }
    };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            JSONObject data = (JSONObject) args[0];
            String code;
            int type;
            try {
                code = data.getString("code");
                JSONObject obj = data.getJSONObject("data");
                type = obj.getInt("type");
                Log.e("socket", code + type + "<------------");
            } catch (JSONException e) {
                Log.e("socket", e.getMessage());
                return;
            }
        }
    };

//    private Emitter.Listener onUserJoined = new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    JSONObject data = (JSONObject) args[0];
//                    String username;
//                    int numUsers;
//                    try {
//                        username = data.getString("username");
//                        numUsers = data.getInt("numUsers");
//                    } catch (JSONException e) {
//                        Log.e(TAG, e.getMessage());
//                        return;
//                    }
//
//                    addLog(getResources().getString(R.string.message_user_joined, username));
//                    addParticipantsLog(numUsers);
//                }
//            });
//        }
//    };
//
//    private Emitter.Listener onUserLeft = new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    JSONObject data = (JSONObject) args[0];
//                    String username;
//                    int numUsers;
//                    try {
//                        username = data.getString("username");
//                        numUsers = data.getInt("numUsers");
//                    } catch (JSONException e) {
//                        Log.e(TAG, e.getMessage());
//                        return;
//                    }
//
////                    addLog(getResources().getString(R.string.message_user_left, username));
////                    addParticipantsLog(numUsers);
////                    removeTyping(username);
//                }
//            });
//        }
//    };
//
//    private Emitter.Listener onTyping = new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    JSONObject data = (JSONObject) args[0];
//                    String username;
//                    try {
//                        username = data.getString("username");
//                    } catch (JSONException e) {
//                        Log.e(TAG, e.getMessage());
//                        return;
//                    }
//                    addTyping(username);
//                }
//            });
//        }
//    };
//
//    private Emitter.Listener onStopTyping = new Emitter.Listener() {
//        @Override
//        public void call(final Object... args) {
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    JSONObject data = (JSONObject) args[0];
//                    String username;
//                    try {
//                        username = data.getString("username");
//                    } catch (JSONException e) {
//                        Log.e(TAG, e.getMessage());
//                        return;
//                    }
//                    removeTyping(username);
//                }
//            });
//        }
//    };

//    private Runnable onTypingTimeout = new Runnable() {
//        @Override
//        public void run() {
//            if (!mTyping) return;
//
//            mTyping = false;
//            mSocket.emit("stop typing");
//        }
//    };
}
