package com.panda.sharebike.ui.deblocking;

import android.app.AlertDialog;
import android.bluetooth.BluetoothGatt;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleScanAndConnectCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.clj.fastble.utils.HexUtil;
import com.orhanobut.logger.Logger;
import com.panda.sharebike.R;
import com.panda.sharebike.app.AppApplication;
import com.panda.sharebike.kb260.Aes;
import com.panda.sharebike.kb260.CheckPermissionBle;
import com.panda.sharebike.model.MessageEventInt;
import com.panda.sharebike.service.SocketService;
import com.panda.sharebike.ui.MainActivity;
import com.panda.sharebike.ui.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * 解锁页
 */
public class DeBlockingActivity extends BaseActivity {

    private SocketService myService;
    private AppApplication mApplication;
    private AlertDialog mAlertDialog;
    private boolean islockShow = false;
    private String result;

    private static String UUID_SERVICE = "0000fee7-0000-1000-8000-00805f9b34fb";
    private static String UUID_WRITE = "000036f5-0000-1000-8000-00805f9b34fb";
    private static String UUID_READ = "000036f6-0000-1000-8000-00805f9b34fb";

    private String mac,ps;
    BleDevice myBleDevice;
    int currentStep;
    byte[] token;
    String tokenStr;
    boolean isOpen;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_de_blocking;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        setLeftIconHide();//隐藏返回键

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isOpen){
                    ToastUtils.showShort("解锁超时");
                    DeBlockingActivity.this.finish();
                }
            }
        },15000);
        //
        mAlertDialog = new AlertDialog.Builder(this).create();//初始化提示dialog
        mApplication = (AppApplication) AppApplication.getAppContext();

        Intent intent = getIntent();
        mac = intent.getStringExtra("mac");
        ps = intent.getStringExtra("ps");

        initBle();

//        Intent intent = new Intent();
//        intent.setAction("com.panda.sharebike.service.SocketService");
//        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    private void initBle() {
        BleManager.getInstance().init(getApplication());
        BleManager.getInstance()
                .enableLog(true)
                .setMaxConnectCount(7)
                .setOperateTimeout(5000);

        new CheckPermissionBle(this).checkPermissions();
        setScanRule();
        scanAndConnect();
    }

    /**
     * 从faultrepairactivity传过来的数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBusFaultRepair(MessageEventInt msg) {
        Logger.e(msg.getType() + "<----------->");
        if (0 == msg.getType()) {
            if (!isOpen){
                isOpen = true;
                Intent intent = new Intent(DeBlockingActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                DeBlockingActivity.this.finish();
            }

        } else if (1 == msg.getType()) {
            ToastUtils.showShort("开锁失败");
            DeBlockingActivity.this.finish();
        }
    }

//    private ServiceConnection conn = new ServiceConnection() {
//
//        @Override
//        public void onServiceConnected(ComponentName componentName, IBinder service) {
//            myService = ((SocketService.LocalBinder) service).getService();
//            System.out.println("Service连接成功");
//            // 执行Service内部自己的方法
//            myService.connect();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName componentName) {
//            myService = null;
//        }
//    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //   unbindService(conn);
    }

    //扫描设置
    void setScanRule() {
        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
                //.setServiceUuids(serviceUuids)      // 只扫描指定的服务的设备，可选
                //.setDeviceName(true, names)   // 只扫描指定广播名的设备，可选
                .setDeviceMac(mac)                  // 只扫描指定mac的设备，可选
                .setAutoConnect(false)      // 连接时的autoConnect参数，可选，默认false
                .setScanTimeOut(10000)              // 扫描超时时间，可选，默认10秒
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);
    }

    void scanAndConnect(){
        BleManager.getInstance().scanAndConnect(new BleScanAndConnectCallback() {
            @Override
            public void onScanStarted(boolean success) {
                // 开始扫描（主线程）
                Log.d("kb260","开始扫描（主线程）");
            }

            @Override
            public void onScanFinished(BleDevice scanResult) {
                // 扫描结束，结果即为扫描到的第一个符合扫描规则的BLE设备，如果为空表示未搜索到（主线程）
                Log.d("kb260","扫描结束");


            }

            @Override
            public void onStartConnect() {
                // 开始连接（主线程）
                Log.d("kb260","开始连接");
            }

            @Override
            public void onConnectFail(BleException exception) {
                // 连接失败（主线程）
                Log.d("kb260","开始连接");
            }

            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                // 连接成功，BleDevice即为所连接的BLE设备（主线程）
                Log.d("kb260","连接成功");
                //Toast.makeText(DeBlockingActivity.this,"连接成功",Toast.LENGTH_SHORT).show();
                myBleDevice = bleDevice;
                openNotify(bleDevice);
                getToken();
            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice device, BluetoothGatt gatt, int status) {
                // 连接断开，isActiveDisConnected是主动断开还是被动断开（主线程）
                Log.d("kb260","连接断开");
            }
        });
    }


    void openNotify(BleDevice bleDevice){
        BleManager.getInstance().notify(
                bleDevice,
                UUID_SERVICE,
                UUID_READ,
                new BleNotifyCallback() {

                    @Override
                    public void onNotifySuccess() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }

                    @Override
                    public void onNotifyFailure(final BleException exception) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }

                    @Override
                    public void onCharacteristicChanged(final byte[] data) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String a = HexUtil.formatHexString(data, true);
                                Log.d("kb260","通知："+a);
                                if (currentStep == 1){
                                    byte[]b = Aes.Decrypt(data,Aes.sKey);
                                    String c = HexUtil.formatHexString(b);
                                    if (b != null && b.length == 16){
                                        token = new byte[]{b[3],b[4],b[5],b[6]};
                                        currentStep =3;
                                    }
                                    if (c != null &&  c.length() == 32){
                                        tokenStr = c.substring(6,14);
                                        currentStep =3;
                                        //Toast.makeText(DeBlockingActivity.this,"获取token成功",Toast.LENGTH_SHORT).show();
                                        kaiSuo();
                                    }
                                }else if (currentStep == 4){
                                    byte[]c = Aes.Decrypt(data,Aes.sKey);
                                    String d = Aes.byteArrayToHexStr(c);
                                    if (d!=null){
                                        String b = d.substring(0,8);
                                        if (b.contains("050201")){
                                            if (b.substring(6,8).equals("00")){
                                                //开锁成功
                                                if (!isOpen){
                                                    isOpen = true;
                                                    Intent intent = new Intent(DeBlockingActivity.this, MainActivity.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    startActivity(intent);
                                                    DeBlockingActivity.this.finish();
                                                }
                                            }else if (b.substring(6,8).equals("01")){
                                                //开锁失败
                                                ToastUtils.showShort("蓝牙开锁失败");
                                                DeBlockingActivity.this.finish();

                                            }
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
    }

    private void kaiSuo() {
        if (currentStep == 3){
            String a = "050106"+ps+tokenStr+"000000";
            byte[] b = HexUtil.hexStringToBytes(a);
            byte[]c = Aes.Encrypt(b,Aes.sKey);
            currentStep = 4;
            write(myBleDevice,c);
        }else {
            Log.d("kb260","无法获取token");
        }
    }

    private void getToken() {
        byte[] a = Aes.Encrypt(Aes.sSrc,Aes.sKey);
        String b = HexUtil.formatHexString(a);
        currentStep = 1;
        write(myBleDevice,a);
    }

    void write(BleDevice bleDevice,byte[] a){
        BleManager.getInstance().write(
                bleDevice,
                UUID_SERVICE,
                UUID_WRITE,
                a, new BleWriteCallback() {
                    @Override
                    public void onWriteSuccess(int current, int total, byte[] justWrite) {
                        if (currentStep == 1){
                            Log.d("kb260","获取token,发送指令成功");
                        }else if (currentStep == 4){
                            Log.d("kb260","开锁,发送指令成功");
                        }
                    }

                    @Override
                    public void onWriteFailure(BleException exception) {
                        String a = exception.getDescription();
                    }
                });
    }
}
