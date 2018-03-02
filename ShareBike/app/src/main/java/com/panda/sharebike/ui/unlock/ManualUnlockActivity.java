package com.panda.sharebike.ui.unlock;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.orhanobut.logger.Logger;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.kb260.KbData;
import com.panda.sharebike.model.MessageEventInt;
import com.panda.sharebike.model.entity.RentBean;
import com.panda.sharebike.service.SocketService;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.deblocking.DeBlockingActivity;
import com.panda.sharebike.ui.login.LoginByPhoneActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.panda.sharebike.util.KeyboardUtil.showSoftInputFromWindow;

public class ManualUnlockActivity extends BaseActivity implements QRCodeView.Delegate, View.OnKeyListener {
    @BindView(R.id.scan_view)
    ZXingView scanView;
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.et1)
    EditText et1;
    @BindView(R.id.et2)
    EditText et2;
    @BindView(R.id.et3)
    EditText et3;
    @BindView(R.id.et4)
    EditText et4;
    @BindView(R.id.et5)
    EditText et5;
    @BindView(R.id.et6)
    EditText et6;
    @BindView(R.id.et7)
    EditText et7;
    @BindView(R.id.et8)
    EditText et8;
    @BindView(R.id.et9)
    EditText et9;
    @BindView(R.id.et10)
    EditText et10;
    @BindView(R.id.et11)
    EditText et11;
    @BindView(R.id.llayout_keyboard)
    LinearLayout llayoutKeyboard;
    @BindView(R.id.tv_input_alert)
    TextView tvInputAlert;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.iv_light)
    ImageView ivLight;
    @BindView(R.id.tv_light)
    TextView tvLight;
    @BindView(R.id.llayout_bottom)
    LinearLayout llayoutBottom;
    @BindView(R.id.tv_input_error)
    TextView tvInputError;
    private SocketService myService;
    private boolean islockShow = false;
    private AMapLocationClient locationClientContinue;
    private double longtitude, latitude;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_manual_unlock;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        scanView.setDelegate(this);

        et1.addTextChangedListener(new ClassOfTextWatcher(et1));
        et2.addTextChangedListener(new ClassOfTextWatcher(et2));
        et3.addTextChangedListener(new ClassOfTextWatcher(et3));
        et4.addTextChangedListener(new ClassOfTextWatcher(et4));
        et5.addTextChangedListener(new ClassOfTextWatcher(et5));
        et6.addTextChangedListener(new ClassOfTextWatcher(et6));
        et7.addTextChangedListener(new ClassOfTextWatcher(et7));
        et8.addTextChangedListener(new ClassOfTextWatcher(et8));
        et9.addTextChangedListener(new ClassOfTextWatcher(et9));
        et10.addTextChangedListener(new ClassOfTextWatcher(et10));
        et11.addTextChangedListener(new ClassOfTextWatcher(et10));

        et2.setOnKeyListener(this);
        et3.setOnKeyListener(this);
        et4.setOnKeyListener(this);
        et5.setOnKeyListener(this);
        et6.setOnKeyListener(this);
        et7.setOnKeyListener(this);
        et8.setOnKeyListener(this);
        et9.setOnKeyListener(this);
        et10.setOnKeyListener(this);
        et11.setOnKeyListener(this);
        showSoftInputFromWindow(ManualUnlockActivity.this, et1);

//        Intent intent = new Intent();
//        intent.setAction("com.panda.sharebike.service.SocketService");
//        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        initLocation();
    }

    private void initLocation() {
        locationClientContinue = new AMapLocationClient(this.getApplicationContext());
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(false);//false多次定位
        option.setInterval(1000 * 2);
        locationClientContinue.setLocationOption(option);
        //启动定位
        locationClientContinue.startLocation();
        //启动定位
        locationClientContinue.setLocationListener(locationContinueListener);
    }

    /**
     * 单次客户端的定位监听
     */
    AMapLocationListener locationContinueListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (location.getErrorCode() == 0) {
                longtitude = location.getLongitude();
                latitude = location.getLatitude();
            }
        }
    };
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

    /**
     * 从faultrepairactivity传过来的数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBusFaultRepair(MessageEventInt msg) {
        Logger.e(msg.getType() + "<----------->");
        if (0 == msg.getType()) {

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        scanView.startCamera();
        scanView.showScanRect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scanView.startSpot();
    }

    @Override
    public void onStop() {
        scanView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (scanView != null)
            scanView.onDestroy();
        super.onDestroy();
        //    unbindService(conn);
    }


    @OnClick({R.id.iv_scan, R.id.iv_light})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                startActivity(new Intent(this, ScanUnlockActivity.class));
                scanView.onDestroy();
                finish();
                break;
            case R.id.iv_light:
                if (ivLight.getTag() == null || ivLight.getTag().equals("close")) {
                    tvLight.setText("关闭手电筒");
                    scanView.openFlashlight();
                    ivLight.setTag("open");
                } else {
                    tvLight.setText("打开手电筒");
                    scanView.closeFlashlight();
                    ivLight.setTag("close");
                }
                break;
        }
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Logger.i("打开相机出错");
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_DEL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if (view instanceof EditText && ((EditText) view).getText().toString().length() == 0) {
                switch (view.getId()) {
                    case R.id.et2:
                        et1.setText("");
                        showSoftInputFromWindow(ManualUnlockActivity.this, et1);
                        break;
                    case R.id.et3:
                        et2.setText("");
                        showSoftInputFromWindow(ManualUnlockActivity.this, et2);
                        break;
                    case R.id.et4:
                        et3.setText("");
                        showSoftInputFromWindow(ManualUnlockActivity.this, et3);
                        break;
                    case R.id.et5:
                        et4.setText("");
                        showSoftInputFromWindow(ManualUnlockActivity.this, et4);
                        break;
                    case R.id.et6:
                        et5.setText("");
                        showSoftInputFromWindow(ManualUnlockActivity.this, et5);
                        break;
                    case R.id.et7:
                        et6.setText("");
                        showSoftInputFromWindow(ManualUnlockActivity.this, et6);
                        break;
                    case R.id.et8:
                        et7.setText("");
                        showSoftInputFromWindow(ManualUnlockActivity.this, et7);
                        break;
                    case R.id.et9:
                        et8.setText("");
                        showSoftInputFromWindow(ManualUnlockActivity.this, et8);
                        break;
                    case R.id.et10:
                        et9.setText("");
                        showSoftInputFromWindow(ManualUnlockActivity.this, et9);
                        break;
                    case R.id.et11:
                        et10.setText("");
                        showSoftInputFromWindow(ManualUnlockActivity.this, et9);
                        break;
                    default:
                        break;
                }
                return true;
            }
        }
        return false;
    }

    private class ClassOfTextWatcher implements TextWatcher {
        private TextView view;

        public ClassOfTextWatcher(View view) {
            if (view instanceof TextView)
                this.view = (TextView) view;
            else
                throw new ClassCastException(
                        "view must be an instance Of TextView");
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(et1.getText()) && !TextUtils.isEmpty(et2.getText()) && !TextUtils.isEmpty(et3.getText()) && !TextUtils.isEmpty(et4.getText()) && !TextUtils.isEmpty(et5.getText()) && !TextUtils.isEmpty(et6.getText()) && !TextUtils.isEmpty(et7.getText()) && !TextUtils.isEmpty(et8.getText())&& !TextUtils.isEmpty(et9.getText())&& !TextUtils.isEmpty(et10.getText())&& !TextUtils.isEmpty(et11.getText())) {
                //   ToastUtils.showShort("输入完了");//进行网络操作拉取数据
                String bikeId = et1.getText().toString() + et2.getText().toString() + et3.getText().toString() + et4.getText().toString() + et5.getText().toString() + et6.getText().toString() + et7.getText().toString()+ et8.getText().toString()+ et9.getText().toString()+ et10.getText().toString()+ et11.getText().toString();
                if (EmptyUtils.isNotEmpty(latitude) && EmptyUtils.isNotEmpty(longtitude)) {
                    getHttpRent(bikeId, longtitude, latitude);//扫码租车
                }
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (after == 1) {
                switch (view.getId()) {
                    case R.id.et1:
                        showSoftInputFromWindow(ManualUnlockActivity.this, et2);
                        break;
                    case R.id.et2:
                        showSoftInputFromWindow(ManualUnlockActivity.this, et3);
                        break;
                    case R.id.et3:
                        showSoftInputFromWindow(ManualUnlockActivity.this, et4);
                        break;
                    case R.id.et4:
                        showSoftInputFromWindow(ManualUnlockActivity.this, et5);
                        break;
                    case R.id.et5:
                        showSoftInputFromWindow(ManualUnlockActivity.this, et6);
                        break;
                    case R.id.et6:
                        showSoftInputFromWindow(ManualUnlockActivity.this, et7);
                        break;
                    case R.id.et7:
                        showSoftInputFromWindow(ManualUnlockActivity.this, et8);
                        //et10.setFocusable(true);
                        //et10.setFocusableInTouchMode(true);
                        //InputMethodManager imm = (InputMethodManager) ManualUnlockActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        //imm.hideSoftInputFromWindow(ManualUnlockActivity.this.getWindow().getDecorView().getWindowToken(), 0);
                        break;
                    case R.id.et8:
                        showSoftInputFromWindow(ManualUnlockActivity.this, et9);
                        break;
                    case R.id.et9:
                        showSoftInputFromWindow(ManualUnlockActivity.this, et10);
                        //et9.setFocusable(true);
                        //et9.setFocusableInTouchMode(true);
                        //InputMethodManager imm = (InputMethodManager) ManualUnlockActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        //imm.hideSoftInputFromWindow(ManualUnlockActivity.this.getWindow().getDecorView().getWindowToken(), 0);
                        break;
                        //showSoftInputFromWindow(ManualUnlockActivity.this, et10);
                    case R.id.et10:
                        showSoftInputFromWindow(ManualUnlockActivity.this, et11);
                        //et10.setFocusable(true);
                        //et10.setFocusableInTouchMode(true);
                        //InputMethodManager imm1 = (InputMethodManager) ManualUnlockActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        //imm1.hideSoftInputFromWindow(ManualUnlockActivity.this.getWindow().getDecorView().getWindowToken(), 0);
                        break;
                    case R.id.et11:
                        et11.setFocusable(true);
                        et11.setFocusableInTouchMode(true);
                        InputMethodManager imm2 = (InputMethodManager) ManualUnlockActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm2.hideSoftInputFromWindow(ManualUnlockActivity.this.getWindow().getDecorView().getWindowToken(), 0);
                        break;
                    default:
                        break;
                }
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

    }

    /**
     * 手动开锁
     *
     * @param bikeId
     */
    private void getHttpRent(String bikeId, double longtitude, double latitude) {

        Api.getInstance().getDefault().getRent(Config.TOKEN, bikeId, longtitude + "", latitude + "", 5000)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<KbData>>(new SubscriberListener<HttpResult<KbData>>() {
                    @Override
                    public void onSuccess(HttpResult<KbData> model) {
                        if (401 == model.getCode()) {
                            startActivity(LoginByPhoneActivity.class);
                            return;
                        }
                        if (model.isOk()) {
                            String  ps = model.getData().getLockInfo().getPassword();
                            String mac =  model.getData().getLockInfo().getMacAddress();
                            Intent intent = new Intent(ManualUnlockActivity.this, DeBlockingActivity.class);
                            intent.putExtra("ps",ps);
                            intent.putExtra("mac",mac);
                            startActivity(intent);
                        } else {
                            if (503 == model.getCode()) {
                                tvInputError.setVisibility(View.VISIBLE);
                            } else if (501 == model.getCode()) {
                                if (EmptyUtils.isNotEmpty(model.getMsg())) {
                                    ToastUtils.showShort(model.getMsg());
                                }
                            }

                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, true));
    }
}
