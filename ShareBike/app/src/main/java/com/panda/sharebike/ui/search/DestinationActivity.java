package com.panda.sharebike.ui.search;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.panda.sharebike.R;
import com.panda.sharebike.lib.InputTipTask;
import com.panda.sharebike.lib.PoiSearchTask;
import com.panda.sharebike.lib.PositionEntity;
import com.panda.sharebike.lib.RecomandAdapter;
import com.panda.sharebike.lib.RouteTask;
import com.panda.sharebike.ui.MainActivity;


/**
 * 搜索框
 */

public class DestinationActivity extends Activity implements View.OnClickListener, TextWatcher, AdapterView.OnItemClickListener {

    private ListView mRecommendList;//列表显示

    private TextView tv_cancel;//取消按钮

    private TextView tv_mAddress;

    private EditText mDestinaionText;//输入按钮

    private RecomandAdapter mRecomandAdapter;

    private RouteTask mRouteTask;

    private LinearLayout mLinearLayout;
    private String mAddress;
    private AMapLocationClient locationClientSingle;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // mAddress = getIntent().getStringExtra("ADDRESS");
        mRecommendList = (ListView) findViewById(R.id.recommend_list);
        mLinearLayout = (LinearLayout) findViewById(R.id.ll_location);
        mDestinaionText = (EditText) findViewById(R.id.destination_edittext);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_mAddress = (TextView) findViewById(R.id.tv_address);

        tv_cancel.setOnClickListener(this);
        mDestinaionText.addTextChangedListener(this);
        mRecomandAdapter = new RecomandAdapter(getApplicationContext());
        mRecommendList.setAdapter(mRecomandAdapter);
        mRecommendList.setOnItemClickListener(this);

        mRouteTask = RouteTask.getInstance(getApplicationContext());
        initLocation();//定位
    }

    private void initLocation() {
        locationClientSingle = new AMapLocationClient(this.getApplicationContext());
        AMapLocationClientOption locationClientSingleOption = new AMapLocationClientOption();
        //获取一次定位结果：
        //该方法默认为false。
        locationClientSingleOption.setOnceLocation(true);
        //关闭缓存机制
        locationClientSingleOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        locationClientSingle.setLocationOption(locationClientSingleOption);
        //启动定位
        locationClientSingle.startLocation();
        locationClientSingle.setLocationListener(locationSingleListener);
    }

    /**
     * 单次客户端的定位监听
     */
    AMapLocationListener locationSingleListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            mAddress = location.getAddress();
            tv_mAddress.setText(location.getAddress());
        }
    };

    @Override
    public void afterTextChanged(Editable arg0) {

        // TODO Auto-generated method stub

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {

        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (RouteTask.getInstance(getApplicationContext()).getStartPoint() == null) {
            Toast.makeText(getApplicationContext(), "检查网络，Key等问题", Toast.LENGTH_SHORT).show();
            return;
        }
        mLinearLayout.setVisibility(View.GONE);//隐藏位置信息
        InputTipTask.getInstance(mRecomandAdapter).searchTips(getApplicationContext(), s.toString(),
                RouteTask.getInstance(getApplicationContext()).getStartPoint().city);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_cancel:
                Intent intent = new Intent(DestinationActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                            long id) {

        PositionEntity entity = (PositionEntity) mRecomandAdapter.getItem(position);
        if (entity.latitue == 0 && entity.longitude == 0) {
            PoiSearchTask poiSearchTask = new PoiSearchTask(getApplicationContext(), mRecomandAdapter);
            poiSearchTask.search(entity.address, RouteTask.getInstance(getApplicationContext()).getStartPoint().city);

        } else {
            mRouteTask.setEndPoint(entity);
            mRouteTask.search();
            Intent intent = new Intent(DestinationActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
        }
    }

}

