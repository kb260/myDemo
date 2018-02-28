package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.ApplicationDetailAdapter;
import com.kb260.gxdk.entity.ApplicationDetailMultiItem;
import com.kb260.gxdk.entity.MyCreditData;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.ImageLoader;
import com.kb260.gxdk.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/12/7
 */
public class MyCreditDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_myCreditDetail_rv)
    RecyclerView rv;

    MyCreditData myCreditData;
    ApplicationDetailAdapter adapter;
    List<ApplicationDetailMultiItem> data;

    public static void start(MyCreditData myCreditData ,Context context) {
        Intent intent = new Intent(context, MyCreditDetailActivity.class);
        intent.putExtra(Action.PRODUCT_DATA,myCreditData);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_credit_detail;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
        initData();
        initRv();
    }

    private void initData() {
        data = new ArrayList<>();

        switch (myCreditData.getIscredit()){
            case Action.KIND_XD:
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"申请类型","信用咨询"));
                break;
            case Action.KIND_DZD:
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"申请类型","垫资咨询"));
                break;
            default:
                break;
        }
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"期望",myCreditData.getCount()));
        /*switch (myCreditData.getIscredit()){
            case Action.KIND_XD:
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"期望",myCreditData.getCount()));
                break;
            case Action.KIND_DZD:
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"垫资金额",myCreditData.getCount()));
                break;
            default:
                break;
        }*/
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.LINE));

        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"身份证",myCreditData.getIdcard()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"年龄",myCreditData.getAge()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"联系方式",myCreditData.getTelphone()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"常住地址",myCreditData.getAddress()));
        String sign = myCreditData.getSign();
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"婚姻状况",sign));
        switch (sign){
            case "未婚":
                break;
            case "已婚":
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"配偶姓名",myCreditData.getSpousename()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"配偶联系方式",myCreditData.getSpousetel()));
                break;
            default:
                break;
        }
        String isHouse = myCreditData.getIsroom();
        if (isHouse != null){
            data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"是否有房",isHouse));
            if (isHouse.equals("是")){
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"房子所在地",myCreditData.getRoomaddress()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"房屋面积",myCreditData.getArea()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"房屋性质",myCreditData.getNature()));
                String houseStatus = myCreditData.getCurrent();
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"房屋现状",houseStatus));
                if (houseStatus.equals("按揭")){
                    data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"按揭银行",myCreditData.getMortgagebank()));
                    data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"剩余尾款",myCreditData.getSurpius()));
                    data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"需要垫资",myCreditData.getExpenditure()));
                }
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"房龄",myCreditData.getTimes()));
            }
        }

        String isCar = myCreditData.getIscar();
        if (isCar != null){
            data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"是否有车",isCar));
            if (isCar.equals("是")){
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"车架号",myCreditData.getCarframe()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"车品牌",myCreditData.getCarbrand()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"车牌",myCreditData.getCarno()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"车龄",myCreditData.getCarage()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"行驶距离",myCreditData.getDistance()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"车况",myCreditData.getCarstatus()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"购车时间",myCreditData.getUseddate()+"-"+myCreditData.getUseddatemonth()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"购车原价",myCreditData.getPrice()));
            }
        }

        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.LINE));

        switch (myCreditData.getIscredit()){
            case Action.KIND_XD:
                break;
            case Action.KIND_DZD:
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"类型",myCreditData.getReverse()));
                break;
            default:
                break;
        }
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"工作所在地",myCreditData.getWorkplace()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"行业",myCreditData.getIndustry()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"职业",myCreditData.getOccupation()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.LINE));

        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"当前单位工作时间",myCreditData.getWorktime()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"目前工资",myCreditData.getCurrentsalary()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"工资发放形式",myCreditData.getPayform()));
        String isGjj = myCreditData.getIsfund();
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"公积金",isGjj));
        if (isGjj.equals("有")){
            data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"单边缴存",myCreditData.getUnilateralpay()));
            data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"缴存城市",myCreditData.getPaycity()));
            data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"已缴期限",myCreditData.getLimittime()));
        }
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.LINE));

        String idBd = myCreditData.getIsinsurancepolicy();
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"保单",idBd));
        if (idBd.equals("有")){
            data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"投保公司",myCreditData.getInsuredcompany()));
            data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"月缴",myCreditData.getMonthpay()));
            data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"年缴",myCreditData.getYearpay()));
        }

        String isYyq = myCreditData.getIsoverdue();
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"是否有逾期",isYyq));
        if (isYyq.equals("是")){
            data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"逾期项",myCreditData.getDueoption()));
            data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"逾期时间",myCreditData.getDuetime()));
            data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"逾期金额",myCreditData.getAmount()));
        }
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.LINE));

        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"其他资产说明",myCreditData.getInstruction()));
    }

    private void initIntent() {
        myCreditData = (MyCreditData) getIntent().getSerializableExtra(Action.PRODUCT_DATA);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText("订单详情");
        initThisToolbarHaveBack(toolbar, this);
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ApplicationDetailAdapter(data);

        rv.setAdapter(adapter);
        addTop();
    }

    private void addTop() {
        View view = getLayoutInflater().inflate(R.layout.my_application_detail_top, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);

        ImageView ivUser = view.findViewById(R.id.top_myApplicationDetail_ivUser);
        TextView tvName = view.findViewById(R.id.top_myApplicationDetail_tvNickname);
        TextView tvTime = view.findViewById(R.id.top_myApplicationDetail_tvTime);
        TextView tvStatus = view.findViewById(R.id.top_myApplicationDetail_tvStatus);

        ImageLoader.showImage(ivUser,"");
        tvName.setText(myCreditData.getRealname());
        tvTime.setText(myCreditData.getCreatetime());
        tvStatus.setText(myCreditData.getStatus());
    }
}
