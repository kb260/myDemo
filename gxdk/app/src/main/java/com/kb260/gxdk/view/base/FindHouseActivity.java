package com.kb260.gxdk.view.base;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.HouseAdapter;
import com.kb260.gxdk.adapter.HouseDetailAdapter;
import com.kb260.gxdk.entity.HouseDetail;
import com.kb260.gxdk.adapter.LouCengAdapter;
import com.kb260.gxdk.adapter.LouDongAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.entity.LouDong;
import com.kb260.gxdk.entity.Loupan;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.DividerItemDecoration;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 *         Created on  2017/12/29
 */
public class FindHouseActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, TextWatcher {
    @BindView(R.id.findHouse_toolbar)
    Toolbar toolbar;
    @BindView(R.id.findHouse_toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_findHouse_et)
    EditText et;
    @BindView(R.id.a_findHouse_rv)
    RecyclerView rv;

    List<Loupan> data;
    List<LouDong> louDongs;
    List<String> louCengs;
    List<HouseDetail> houseDetails;
    HouseAdapter adapter;
    LouDongAdapter louDongAdapter;
    LouCengAdapter louCengAdapter;
    HouseDetailAdapter houseDetailAdapter;
    String province, city, area, keyword;
    String communityid,	buildingid,houseId,workplace,communityname,buildingname,housename,houseDetail;
    boolean searching = true;

    public static void start(Activity context,String province,String city,String area,int a) {
        Intent intent = new Intent(context, FindHouseActivity.class);
        intent.putExtra(Action.PROVINCE,province);
        intent.putExtra(Action.CITY,city);
        intent.putExtra(Action.AREA,area);
        context.startActivityForResult(intent, a);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_find_house;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        province = intent.getStringExtra(Action.PROVINCE);
        city = intent.getStringExtra(Action.CITY);
        area = intent.getStringExtra(Action.AREA);

        initRv();
        et.addTextChangedListener(this);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.findHouse_toolbar);
        initThisToolbarHaveBack(toolbar, this);
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divide_deepr)));

        data = new ArrayList<>();
        louDongs = new ArrayList<>();
        louCengs = new ArrayList<>();
        houseDetails = new ArrayList<>();

        adapter = new HouseAdapter(data);
        louDongAdapter = new LouDongAdapter(louDongs);
        louCengAdapter = new LouCengAdapter(louCengs);
        houseDetailAdapter = new HouseDetailAdapter(houseDetails);

        adapter.setDuration(1);
        louDongAdapter.setDuration(2);
        louCengAdapter.setDuration(3);
        houseDetailAdapter.setDuration(4);

        adapter.setOnItemClickListener(this);
        louDongAdapter.setOnItemClickListener(this);
        louCengAdapter.setOnItemClickListener(this);
        houseDetailAdapter.setOnItemClickListener(this);
    }

    private void initData1() {
        Api.getDefault().loupan(province, city, area, keyword)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<Loupan>>(this) {
                    @Override
                    protected void success(List<Loupan> list) {
                        if (list != null && list.size() > 0) {
                            rv.setAdapter(adapter);
                            data = list;
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void initData2(String id) {
        Api.getDefault().build(id, city)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<LouDong>>(this) {
                    @Override
                    protected void success(List<LouDong> list) {
                        if (list != null && list.size() > 0) {
                            rv.setAdapter(louDongAdapter);
                            louDongs = list;
                            louDongAdapter.setNewData(louDongs);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void initData3(String id) {
        Api.getDefault().ceng(city, id)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<String>>(this) {
                    @Override
                    protected void success(List<String> list) {
                        if (list != null && list.size() > 0) {
                            rv.setAdapter(louCengAdapter);
                            louCengs = list;
                            louCengAdapter.setNewData(louCengs);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void initData4(String id, String floor) {
        Api.getDefault().area(id, floor, city)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<HouseDetail>>(this) {
                    @Override
                    protected void success(List<HouseDetail> list) {
                        if (list != null && list.size() > 0) {
                            rv.setAdapter(houseDetailAdapter);
                            houseDetails = list;
                            houseDetailAdapter.setNewData(houseDetails);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter.getData() == data) {
            searching = false;
            communityid = data.get(position).getCommunityid();
            communityname = data.get(position).getCommunityname();
            et.setText(communityname);
            initData2(communityid);
        }else if (adapter.getData() == louDongs){
            buildingid = louDongs.get(position).getBuildingId();
            buildingname = louDongs.get(position).getBuildingName();

            String a = communityname+buildingname;
            et.setText(a);
            initData3(buildingid);
        }else if (adapter.getData() == louCengs){
            initData4(buildingid,louCengs.get(position));
            String b = communityname+buildingname+louCengs.get(position);
            et.setText(b);
        }else if (adapter.getData() == houseDetails){
            houseId = houseDetails.get(position).getHouseId();
            workplace = houseDetails.get(position).getArea();
            housename = houseDetails.get(position).getHouseName();

            houseDetail = communityname+buildingname+housename;
            et.setText(houseDetail);
            Intent intent = new Intent();
            intent.putExtra(Action.COMMUNITYID,communityid);
            intent.putExtra(Action.COMMUNITYNAME,communityname);
            intent.putExtra(Action.WORKPLACE,workplace);
            intent.putExtra(Action.HOUSE_DETAIL,houseDetail);
            intent.putExtra(Action.BUILDINGNAME,buildingname);
            setResult(Action.FIND_HOUSE,intent);
            finish();
        }

    }

    @OnClick(R.id.findHouse_toolbarRight)
    public void sure(){
        if (StringUtils.isEmpty(keyword)) {
            ToastUtil.showInfo("请输入小区名！");
        }else {
            Intent intent = new Intent();
            intent.putExtra(Action.XQ,keyword);
            setResult(Action.FIND_HOUSE_1,intent);
            finish();
        }
    }

    // 输入前的监听
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    // 输入的内容变化的监听
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        keyword = charSequence.toString();
        if (StringUtils.isEmpty(keyword)) {
            /*if (rv.getAdapter() == adapter) {
                data.clear();
                adapter.setNewData(data);
            }else if (rv.getAdapter() == louDongAdapter){
                louDongs.clear();
                louDongAdapter.setNewData(louDongs);
            }else if (rv.getAdapter() == louCengAdapter){
                louCengs.clear();
                louCengAdapter.setNewData(louCengs);
            }else if (rv.getAdapter() == houseDetailAdapter){
                houseDetails.clear();
                houseDetailAdapter.setNewData(houseDetails);
            }*/
            data.clear();
            adapter.setNewData(data);
        }else {
            if (searching){
                initData1();
            }
        }
    }

    // 输入后的监听
    @Override
    public void afterTextChanged(Editable editable) {
       /* keyword = editable.toString();
        if (StringUtils.isEmpty(keyword)) {
            data.clear();
            adapter.setNewData(data);
        } else {
            if (searching){
                initData1();
            }
        }*/
    }
}
