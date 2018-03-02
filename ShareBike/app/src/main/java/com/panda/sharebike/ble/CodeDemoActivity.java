package com.panda.sharebike.ble;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.clj.fastble.BleManager;
/*import com.clj.fastble.conn.BleCharacterCallback;
import com.clj.fastble.conn.BleGattCallback;
import com.clj.fastble.conn.BleRssiCallback;
import com.clj.fastble.data.ScanResult;
import com.clj.fastble.scan.ListScanCallback;*/
import com.clj.fastble.utils.HexUtil;
import com.clj.fastble.exception.BleException;
import com.panda.sharebike.R;

import java.util.Random;

/**
 * 代码示范
 */
public class CodeDemoActivity extends AppCompatActivity {
    //这个是线上的字节码：4CtxssHi
    //蓝牙连接8字节KEY：4CtxssHi

    // 下面的所有UUID及指令请根据实际设备替换
    /*private static final String UUID_SERVICE = "0783b03e-8535-b5a0-7140-a304d2495cb7";
    private static final String UUID_INDICATE = "0000000-0000-0000-8000-00805f9b0000";
    private static final String UUID_NOTIFY = "0783b03e-8535-b5a0-7140-a304d2495cb8";
    private static final String UUID_WRITE = "0783b03e-8535-b5a0-7140-a304d2495cba";
    private static final String UUID_READ = "00000000-0000-0000-8000-00805f9b0000";
    private static final String SAMPLE_WRITE_DATA = "000000000000000";                  // 要写入设备某一个character的指令

    private static final long TIME_OUT = 5000;                                          // 扫描超时时间
    private static final String DEVICE_NAME = "这里写你的设备名";                         // 符合连接规则的蓝牙设备名
    private static final String[] DEVICE_NAMES = new String[]{};                        // 符合连接规则的蓝牙设备名
    private static final String DEVICE_MAC = "80:EA:CA:01:00:4D";                        // 符合连接规则的蓝牙设备地址
    private static final String DEVICE_MAC2 = "80:EA:CA:01:00:30";                        // 符合连接规则的蓝牙设备地址

    //这个是线下的字节码，需要替换。成线上的可打开正式锁
    private static final char NUM_Y = 'y';
    private static final char NUM_O = 'O';
    private static final char NUM_T = 'T';
    private static final char NUM_M = 'm';
    private static final char NUM_K = 'K';
    private static final int NUM_5 = 0x05;
    private static final int NUM_0 = 0x00;
    private static final char NUM_Z = 'z';


    private BleManager bleManager;
    private ScanResult scanResult;
    private byte crc10[] = {(byte) 0xFE, 0x66, 0x34, 0x25, 0x3C, 0x4D, 0x7B, 0x60, 0x59, 0x7F, 0x01, 0x04, 0x4E};
    private int NUM;
    private int NNM_1;
    private byte key;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble);

        bleManager = new BleManager(this);
        bleManager.enableBluetooth();
        initView();
        initdata();
    }

    private void initdata() {
        findViewById(R.id.listen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotify();//开始监听
            }
        });
        findViewById(R.id.write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write();//监听成功后开始写入
            }
        });
        findViewById(R.id.unlock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeLock();//写入成功获取秘钥开锁
            }
        });
    }

    private void initView() {
        if (isSupportBle()) {
            if (bleManager.isBlueEnable()) {
                scanAndConnect5();
            } else {
                bleManager.enableBluetooth();
            }
        } else {
            Toast.makeText(this, "不支持蓝牙", Toast.LENGTH_SHORT).show();
        }
    }

    *//**************************************user's operate****************************************//*

    *//**
     * 判断是否支持ble
     *//*
    private boolean isSupportBle() {
        return bleManager.isSupportBle();
    }

    *//**
     * 手动开启蓝牙
     *//*
    private void enableBlue() {
        bleManager.enableBluetooth();
    }

    *//**
     * 手动关闭蓝牙
     *//*
    private void disableBlue() {
        bleManager.disableBluetooth();
    }

    *//**
     * 刷新缓存操作
     *//*
    private void refersh() {
        bleManager.refreshDeviceCache();
    }

    *//**
     * 关闭操作
     *//*
    private void close() {
        bleManager.closeBluetoothGatt();
    }

    *//**
     * 扫描出周围所有设备
     *//*
    private void scanDevice() {
        bleManager.scanDevice(new ListScanCallback(TIME_OUT) {
            @Override
            public void onScanning(ScanResult result) {
                scanResult = result;
            }

            @Override
            public void onScanComplete(ScanResult[] results) {

            }
        });
    }

    *//**
     * 当搜索到周围有设备之后，可以选择直接连某一个设备
     *//*
    private void connectDevice() {
        bleManager.connectDevice(scanResult, true, new BleGattCallback() {

            @Override
            public void onConnecting(BluetoothGatt gatt, int status) {

            }

            @Override
            public void onConnectError(BleException exception) {

            }

            @Override
            public void onConnectSuccess(BluetoothGatt gatt, int status) {

            }

            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {

            }

            @Override
            public void onDisConnected(BluetoothGatt gatt, int status, BleException exception) {

            }

        });
    }

    *//**
     * 扫描指定广播名的设备，并连接（唯一广播名）
     *//*
    private void scanAndConnect1() {
        bleManager.scanNameAndConnect(
                DEVICE_NAME,
                TIME_OUT,
                false,
                new BleGattCallback() {

                    @Override
                    public void onFoundDevice(ScanResult scanResult) {

                    }

                    @Override
                    public void onConnecting(BluetoothGatt gatt, int status) {

                    }

                    @Override
                    public void onConnectError(BleException exception) {

                    }

                    @Override
                    public void onConnectSuccess(BluetoothGatt gatt, int status) {

                    }

                    @Override
                    public void onServicesDiscovered(BluetoothGatt gatt, int status) {

                    }

                    @Override
                    public void onDisConnected(BluetoothGatt gatt, int status, BleException exception) {

                    }

                });
    }

    *//**
     * 扫描指定广播名的设备，并连接（模糊广播名）
     *//*
    private void scanAndConnect2() {
        bleManager.scanfuzzyNameAndConnect(
                DEVICE_NAME,
                TIME_OUT,
                false,
                new BleGattCallback() {

                    @Override
                    public void onFoundDevice(ScanResult scanResult) {

                    }

                    @Override
                    public void onConnecting(BluetoothGatt gatt, int status) {

                    }

                    @Override
                    public void onConnectError(BleException exception) {

                    }

                    @Override
                    public void onConnectSuccess(BluetoothGatt gatt, int status) {

                    }

                    @Override
                    public void onServicesDiscovered(BluetoothGatt gatt, int status) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
                    @Override
                    public void onDisConnected(BluetoothGatt gatt, int status, BleException exception) {
                        gatt.connect();
                    }

                });
    }

    *//**
     * 扫描指定广播名的设备，并连接（多个广播名）
     *//*
    private void scanAndConnect3() {
        bleManager.scanNamesAndConnect(
                DEVICE_NAMES,
                TIME_OUT,
                false,
                new BleGattCallback() {

                    @Override
                    public void onFoundDevice(ScanResult scanResult) {

                    }

                    @Override
                    public void onConnecting(BluetoothGatt gatt, int status) {

                    }

                    @Override
                    public void onConnectError(BleException exception) {

                    }

                    @Override
                    public void onConnectSuccess(BluetoothGatt gatt, int status) {

                    }

                    @Override
                    public void onServicesDiscovered(BluetoothGatt gatt, int status) {

                    }

                    @Override
                    public void onDisConnected(BluetoothGatt gatt, int status, BleException exception) {

                    }

                });
    }

    *//**
     * 扫描指定广播名的设备，并连接（模糊、多个广播名）
     *//*
    private void scanAndConnect4() {
        bleManager.scanfuzzyNamesAndConnect(
                DEVICE_NAMES,
                TIME_OUT,
                false,
                new BleGattCallback() {

                    @Override
                    public void onFoundDevice(ScanResult scanResult) {

                    }

                    @Override
                    public void onConnecting(BluetoothGatt gatt, int status) {

                    }

                    @Override
                    public void onConnectError(BleException exception) {

                    }

                    @Override
                    public void onConnectSuccess(BluetoothGatt gatt, int status) {

                    }

                    @Override
                    public void onServicesDiscovered(BluetoothGatt gatt, int status) {

                    }

                    @Override
                    public void onDisConnected(BluetoothGatt gatt, int status, BleException exception) {

                    }

                });
    }

    *//**
     * 扫描指定物理地址的设备，并连接
     *//*
    private void scanAndConnect5() {

        bleManager.scanMacAndConnect(
                DEVICE_MAC2,
                TIME_OUT,
                false,
                new BleGattCallback() {

                    @Override
                    public void onFoundDevice(ScanResult scanResult) {

                    }

                    @Override
                    public void onConnecting(BluetoothGatt gatt, int status) {

                    }

                    @Override
                    public void onConnectError(BleException exception) {
                        Log.e("code", "连接错误");
                    }

                    @Override
                    public void onConnectSuccess(BluetoothGatt gatt, int status) {
                        Log.e("code", "连接成功");
                        Log.e("code", status + "");
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    Thread.sleep(100);

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        //    write();
                    }

                    @Override
                    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
//                        write();
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    Thread.sleep(100);

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        //addNotify();

                    }

                    @Override
                    public void onDisConnected(BluetoothGatt gatt, int status, BleException exception) {

                    }

                });
    }

    *//**
     * 取消搜索
     *//*
    private void cancelScan() {
        bleManager.cancelScan();
    }

    *//**
     * notify
     *//*
    private boolean addNotify() {

        return bleManager.notify(
                UUID_SERVICE,
                UUID_NOTIFY,
                new BleCharacterCallback() {
                    @Override
                    public void onSuccess(final BluetoothGattCharacteristic characteristic) {
                        runOnUiThread(new Runnable() {
                            //@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
                            @Override
                            public void run() {
                                Log.e("QTQ", "notify");
                                byte NUM_1 = characteristic.getValue()[1];
                                byte NUM = (byte) (NUM_1 - 0x32);
                                byte KEY = characteristic.getValue()[5];

                                key = (byte) (KEY ^ NUM);//获得开锁秘钥

                                Log.e("取出NUM-->", String.format("%1$#9x", NUM));
                                Log.e("取出NUM_1-->", String.format("%1$#9x", NUM_1));

                                Log.e("QKQ", "notify--->" + String.format("%1$#9x", key));

                                Log.e("characteristic--->", HexUtil.encodeHexStr(characteristic.getValue()));
                                for (int i = 0; i < characteristic.getValue().length; i++) {

                                    Log.e("characteristic-->", String.format("%1$#9x", characteristic.getValue()[i]));
                                }
                            }
                        });
                    }

                    @Override
                    public void onFailure(BleException exception) {

                    }

                    @Override
                    public void onInitiatedResult(boolean result) {
                        Log.e("code", "开始监听-->" + result);
                    }
                });
    }

    *//**
     * stop notify
     *//*
    private boolean stopNotify() {
        return bleManager.stopNotify(UUID_SERVICE, UUID_NOTIFY);
    }

    *//**
     * indicate
     *//*
    private boolean addIndicate() {
        return bleManager.indicate(
                UUID_SERVICE,
                UUID_INDICATE,
                new BleCharacterCallback() {
                    @Override
                    public void onSuccess(BluetoothGattCharacteristic characteristic) {

                    }

                    @Override
                    public void onFailure(BleException exception) {

                    }

                    @Override
                    public void onInitiatedResult(boolean result) {

                    }
                });
    }

    *//**
     * stop indicate
     *//*
    private boolean stopIndicate() {
        return bleManager.stopIndicate(UUID_SERVICE, UUID_INDICATE);
    }

    *//**
     * write
     *//*
    private boolean write() {
        Log.e("code", "开始写入");
        return bleManager.writeDevice(
                UUID_SERVICE,
                UUID_WRITE,
                getKeyByte(),
                new BleCharacterCallback() {
                    @Override
                    public void onSuccess(BluetoothGattCharacteristic characteristic) {
                        Log.e("code->write", "写入成功");
                    }

                    @Override
                    public void onFailure(BleException exception) {
                        Log.e("code->write", "写入失败" + exception);
                    }

                    @Override
                    public void onInitiatedResult(boolean result) {
                        Log.e("code->write", "写入" + result);
                    }
                });
    }

    *//**
     * write
     *//*
    private boolean writeLock() {
        Log.e("code", "开始开锁");
        return bleManager.writeDevice(
                UUID_SERVICE,
                UUID_WRITE,
                getLock(),
                new BleCharacterCallback() {
                    @Override
                    public void onSuccess(BluetoothGattCharacteristic characteristic) {
                        Log.e("code->write", "开锁成功");
                    }

                    @Override
                    public void onFailure(BleException exception) {
                        Log.e("code->write", "开锁失败" + exception);
                    }

                    @Override
                    public void onInitiatedResult(boolean result) {
                        Log.e("code->write", "开锁" + result);
                    }
                });
    }

    *//**
     * read
     *//*
    private boolean read() {
        return bleManager.readDevice(
                UUID_SERVICE,
                UUID_READ,
                new BleCharacterCallback() {
                    @Override
                    public void onSuccess(BluetoothGattCharacteristic characteristic) {

                    }

                    @Override
                    public void onFailure(BleException exception) {

                    }

                    @Override
                    public void onInitiatedResult(boolean result) {

                    }
                });
    }

    private boolean readRssi() {
        return bleManager.readRssi(new BleRssiCallback() {
            @Override
            public void onSuccess(int rssi) {

            }

            @Override
            public void onFailure(BleException exception) {

            }

            @Override
            public void onInitiatedResult(boolean result) {

            }
        });
    }

    *//**
     * crc校验，获取解锁秘钥
     *//*
    public byte[] getKeyByte() {

        // 生成随机数NUM
        Random random = new Random();
        NUM = random.nextInt() & 0xff;
        // 产生随机数变种NUM_1
        NNM_1 = NUM + 0x32;
        Log.e("初始NUM-->", String.format("%1$#9x", NUM));
        Log.e("初始NUM_1-->", String.format("%1$#9x", NNM_1));
        *//**
         * 获取key,
         * 加密组成：随机数、KEY。
         * 加密过程:
         * 1、产生随机数 NUM
         * 2、产生随机数变种 NUM_1 = NUM + 0x32
         * 3、把 NUM_1 填充到数据的第 1 字节
         * 4、用 NUM 分别异或 NUM 之后，CRC 之前的明文数据并把结果对应回填
         * 5、把 CRC 之前数据做 CRC16 校验，校验值填到 CRC 位置。
         *//*
        byte crc[] = {
                (byte) 0xFE,
                (byte) NNM_1,
                (byte) (0x00 ^ NUM),
                (byte) (0x11 ^ NUM),
                (byte) (0x08 ^ NUM),
                (byte) (NUM_Y ^ NUM),
                (byte) (NUM_O ^ NUM),
                (byte) (NUM_T ^ NUM),
                (byte) (NUM_M ^ NUM),
                (byte) (NUM_K ^ NUM),
                (byte) (NUM_5 ^ NUM),
                (byte) (NUM_0 ^ NUM),
                (byte) (NUM_Z ^ NUM)};

        int cac = CRCUtil.calcCRC(crc);
        byte low = (byte) (cac & 0xff);//低位
        byte high = (byte) ((cac >> 8) & 0xff);//高位
        Log.e("low-->", String.format("%1$#9x", low) + "high-->" + String.format("%1$#9x", high));
        //
        byte crc1[] = {
                (byte) 0xFE,
                (byte) NNM_1,
                (byte) (0x00 ^ NUM),
                (byte) (0x11 ^ NUM),
                (byte) (0x08 ^ NUM),
                (byte) (NUM_Y ^ NUM),
                (byte) (NUM_O ^ NUM),
                (byte) (NUM_T ^ NUM),
                (byte) (NUM_M ^ NUM),
                (byte) (NUM_K ^ NUM),
                (byte) (NUM_5 ^ NUM),
                (byte) (NUM_0 ^ NUM),
                (byte) (NUM_Z ^ NUM),
                high,
                low};
        Log.e("输入的随机数NUM", String.format("%1$#9x", NUM));

//        for (int i = 0; i < crc1.length; i++) {
//            Log.e("crc1", String.format("%1$#9x", crc1[i]));
//        }
        return crc1;
    }

    *//**
     * crc校验，开锁指令
     *//*
    public byte[] getLock() {

        // 生成随机数NUM
        Random random = new Random();
        NUM = random.nextInt() & 0xff;
        // 产生随机数变种NUM_1
        NNM_1 = NUM + 0x32;
        *//**
         * 获取key,
         * 加密组成：随机数、KEY。
         * 加密过程:
         * 1、产生随机数 NUM
         * 2、产生随机数变种 NUM_1 = NUM + 0x32
         * 3、把 NUM_1 填充到数据的第 1 字节
         * 4、用 NUM 分别异或 NUM 之后，CRC 之前的明文数据并把结果对应回填
         * 5、把 CRC 之前数据做 CRC16 校验，校验值填到 CRC 位置。
         *//*
        Log.e("key", String.format("%1$#9x", key));
        //数组拼接
        byte data1[] = {(byte) 0xFE, (byte) NNM_1, (byte) (key ^ NUM), (byte) (0x21 ^ NUM), (byte) (0x08 ^ NUM)};
        byte data2[] = IntToByteArray2(1111111, NUM);//将id转成4字节byte[]数组
        byte data3[] = new byte[data1.length + data2.length];

        byte data4[] = TimeToByteArray2(NUM);//将时间戳转化成byte[]数组
        byte data5[] = new byte[data3.length + data4.length];//最终生成的开锁数组

        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);

        System.arraycopy(data3, 0, data5, 0, data3.length);
        System.arraycopy(data4, 0, data5, data3.length, data4.length);

        int cac = CRCUtil.calcCRC(data5);
        byte low = (byte) (cac & 0xff);//低位
        byte high = (byte) ((cac >> 8) & 0xff);//高位
        Log.e("low-->", String.format("%1$#9x", low) + "high-->" + String.format("%1$#9x", high));

        byte crc1[] = {high, low};

        byte key[] = new byte[data5.length + crc1.length];//最终数组
        System.arraycopy(data5, 0, key, 0, data5.length);
        System.arraycopy(crc1, 0, key, data5.length, crc1.length);


        Log.e("getRandom(X)", String.valueOf(NUM));
        for (int i = 0; i < key.length; i++) {
            Log.e("KEY", String.format("%1$#9x", key[i]));
        }
        return key;
    }

    public byte[] TimeToByteArray2(int num) {

        int time = (int) (System.currentTimeMillis() / 1000);
        //  IntToByteArray2(time, num);
        return IntToByteArray2(time, num);

    }

    *//**
     * 将int转为低字节在后，高字节在前的byte数组
     * b[0] = 11111111(0xff) & 01100001
     * b[1] = 11111111(0xff) & 00000000
     * b[2] = 11111111(0xff) & 00000000
     * b[3] = 11111111(0xff) & 00000000
     *//*
    public byte[] IntToByteArray2(int value, int num) {
        byte[] src = new byte[4];
        src[0] = (byte) (((value >>> 24) & 0xFF) ^ num);
        src[1] = (byte) (((value >>> 16) & 0xFF) ^ num);
        src[2] = (byte) (((value >>> 8) & 0xFF) ^ num);
        src[3] = (byte) ((value & 0xFF) ^ num);
        return src;
    }*/

}
