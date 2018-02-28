package com.kb260.gxdk.view.gradmonad;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.OrderDetailAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.OrderDetailMultiItem;
import com.kb260.gxdk.model.Order;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.ImageLoader;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 */
public class OrderDetailActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.orderDetail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.orderDetail_toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.orderDetail_toolbarRight)
    TextView toolbarRight;
    @BindView(R.id.a_orderDetail_rv)
    RecyclerView rv;


    Order order;
    OrderDetailAdapter adapter;
    List<OrderDetailMultiItem> data;

    public static void start(Context context,Order order){
        Intent intent = new Intent(context,OrderDetailActivity.class);
        intent.putExtra(Contact.ORDER,order);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
        //initData();
        initRv();
    }

    private void initIntent() {
        Intent intent = getIntent();
        order = (Order)intent.getSerializableExtra(Contact.ORDER);
        data = new ArrayList<>();
        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"身份证",order.getRoomloan().getIdcard()));
        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"年龄",order.getRoomloan().getAge()));

        String status = order.getRoomloan().getStatus();
        if (!status.equals(Action.ORDER_TYPE_WZF) &&  !status.equals(Action.ORDER_TYPE_YZF)){
            data.add(new OrderDetailMultiItem(OrderDetailMultiItem.PHONE,"联系方式",order.getRoomloan().getTelphone()));
        }
        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"常住地址",order.getRoomloan().getAddress()));
        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"还款周期",order.getRoomloan().getYearpay()));
        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"还款方式",order.getRoomloan().getMonthpay()));

        String sign = order.getRoomloan().getSign();
        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"婚姻状况",sign));

        /*switch (sign){
            case "未婚":
                break;
            case "已婚":
                data.add(new OrderDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"配偶姓名",order.getRoomloan().getSpousename()));
                data.add(new OrderDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"配偶联系方式",order.getRoomloan().getSpousetel()));
                break;
            default:
                break;
        }*/

        switch (order.getRoomloan().getType()){
            case "1":
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.LINE));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"房子所在地",order.getRoomloan().getRoomaddress()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"房屋面积",order.getRoomloan().getArea()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"房屋性质",order.getRoomloan().getNature()));

                String current = order.getRoomloan().getCurrent();
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"房屋状况",current));
                switch (current){
                    case "全款":
                        break;
                    case "按揭":
                        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"按揭银行",order.getRoomloan().getMortgagebank()));
                        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"剩余尾款",order.getRoomloan().getSurpius()));
                        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"需要垫资",order.getRoomloan().getExpenditure()));
                        break;
                    default:
                        break;
                }
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"房龄",order.getRoomloan().getTimes()));

                String isCompany = order.getRoomloan().getIscompany();
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"是否有公司",isCompany));
                switch (isCompany){
                    case "否":
                        break;
                    case "是":
                        String position = order.getRoomloan().getPosition();
                        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"身份",position));
                        switch (position){
                            case "股东":
                                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"占股比例",order.getRoomloan().getRatio()));
                                break;
                            default:
                                break;
                        }
                        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"是否有场地",order.getRoomloan().getIsplace()));

                        break;
                    default:
                        break;
                }
                break;
            case "2":
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.LINE));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"车架号",order.getRoomloan().getCarframe()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"车品牌",order.getRoomloan().getCarbrand()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"车牌",order.getRoomloan().getCarno()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"车龄",order.getRoomloan().getCarage()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"行驶距离",order.getRoomloan().getDistance()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"车况",order.getRoomloan().getIndustry()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"购车原价",order.getRoomloan().getPaycity()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"购车时间",order.getRoomloan().getLimittime()+"-"
                +order.getRoomloan().getPayform()));

                String currentCar = order.getRoomloan().getIspay();
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"车现状",currentCar));
                switch (currentCar){
                    case "全款":
                        break;
                    case "按揭":
                        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"按揭银行",order.getRoomloan().getMortgagebank()));
                        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"剩余尾款",order.getRoomloan().getSurpius()));
                        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"需要垫资",order.getRoomloan().getExpenditure()));
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        //data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"申请额度",order.getRoomloan().getCount()));
       /* data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"月利率范围",order.getRoomloan().getRate()
                +"厘~"+order.getRoomloan().getMaxrate()+"厘"));*/

        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"是否逾期",order.getRoomloan().getIsoverdue()));
        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.LINE));
        if (order.getRoomloan().getIsoverdue().equals("是")){
            data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"逾期项",order.getRoomloan().getDueoption()));
            data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"逾期时间",order.getRoomloan().getDuetime()));
            data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"金额",order.getRoomloan().getAmount()));
        }
        data.add(new OrderDetailMultiItem(OrderDetailMultiItem.DESCRIPTION,"其他资产说明",order.getRoomloan().getInstruction()));

        if (order.getStatus().equals("2") || order.getStatus().equals("4")){
            if (order.getFirsttrial()!=null){
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT,"初审结果"));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"额度",order.getFirsttrial().getCount()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"月利率",order.getFirsttrial().getRate()+"~"+order.getFirsttrial().getMaxrate()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"还款方式",order.getFirsttrial().getLoantype()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"可办理期限",order.getFirsttrial().getLoanlimit()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"还款周期",order.getFirsttrial().getLoantime()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"预计终审结果时间",order.getFirsttrial().getLastresult()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"所需材料",order.getFirsttrial().getFirstpicture()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"备注",order.getFirsttrial().getRemark()));
            }
        }
        if (order.getStatus().equals("3") || order.getStatus().equals("4")){
            if (order.getLasttrial()!=null){
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT,"终审结果"));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"额度",order.getLasttrial().getCount()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"月利率",order.getLasttrial().getRate()+"~"+order.getLasttrial().getMaxrate()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"还款方式",order.getLasttrial().getLoantype()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"可办理期限",order.getLasttrial().getLoanlimit()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"还款周期",order.getLasttrial().getLoantime()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"预计办理完成时间",order.getLasttrial().getLendingtime()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"所需材料",order.getLasttrial().getLastpicture()));
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.RESULT_DESCRIPTION,"备注",order.getLasttrial().getRemark()));
            }
        }
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new OrderDetailAdapter(data);

        rv.setAdapter(adapter);
        addTop();
        adapter.setOnItemChildClickListener(this);
    }

    private void addTop(){
        View view = getLayoutInflater().inflate(R.layout.order_detail_top, (ViewGroup) rv.getParent(),
                false);
        adapter.addHeaderView(view);
        ImageView ivUser = view.findViewById(R.id.order_detailTop_ivUser);
        TextView tvName = view.findViewById(R.id.order_detailTop_tvName);
        TextView tvStatus = view.findViewById(R.id.order_detailTop_tvStatus);
        TextView tvTime = view.findViewById(R.id.order_detailTop_tvTime);
        TextView tvEvaluationAmount = view.findViewById(R.id.order_detailTop_tvEvaluationAmount);
        TextView tvType = view.findViewById(R.id.order_detailTop_tvType);
        TextView tvRate = view.findViewById(R.id.order_detailTop_tvRate);
        TextView tvAmount = view.findViewById(R.id.order_detailTop_tvAmount);
        TextView tvCredit = view.findViewById(R.id.order_detailTop_tvCredit);
        TextView tvTerm = view.findViewById(R.id.order_detailTop_tvTerm);

        ImageLoader.showImage(ivUser,order.getAppuser().getPicture());
        tvName.setText(order.getRoomloan().getRealname());
        switch (order.getRoomloan().getStatus()){
            case Action.ORDER_TYPE_WZF://未支付押金
                tvStatus.setText("未抢单");
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.BUTTON,"抢单"));
                toolbarRight.setVisibility(View.GONE);
                break;
            case Action.ORDER_TYPE_YZF://已支付押金
                tvStatus.setText("未抢单");
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.BUTTON,"抢单"));
                toolbarRight.setVisibility(View.GONE);
                break;
            case Action.ORDER_TYPE_YQD://以抢单
                tvStatus.setText("已抢单");
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.BUTTON,"提供初审"));
                break;
            case Action.ORDER_TYPE_YCS://已初审
                tvStatus.setText("已初审");
                data.add(new OrderDetailMultiItem(OrderDetailMultiItem.BUTTON,"提供终审"));
                break;
            case Action.ORDER_TYPE_YZS://已终审
                tvStatus.setText("已终审");
                break;
            case Action.ORDER_TYPE_YZFFWF://已支付服务费
                if (order.getRoomloan().getReserve().equals("1")){
                    tvStatus.setText("已支付服务费");
                }else {
                    tvStatus.setText("已支付服务费");
                    data.add(new OrderDetailMultiItem(OrderDetailMultiItem.BUTTON,"确认完成"));
                }
                break;
            case Action.ORDER_TYPE_DKCG://贷款成功
                tvStatus.setText("订单完成");
                toolbarRight.setVisibility(View.GONE);
                //data.add(new OrderDetailMultiItem(OrderDetailMultiItem.BUTTON,"确认放贷"));
                break;
            case Action.ORDER_TYPE_YSX://已失效
                tvStatus.setText("已失效");
                break;
            case Action.ORDER_TYPE_DCL://待处理
                tvStatus.setText("待处理");
                break;
            default:
                break;
        }
        tvTime.setText(order.getCreatetime());

        switch (order.getRoomloan().getType()){
            case "1":
                tvEvaluationAmount.setText(order.getRoomloan().getEvaluatingprice());
                tvType.setText("房屋");
                break;
            case "2"://已终审
                tvEvaluationAmount.setText(order.getRoomloan().getEvaluatingpriceCar());
                tvType.setText("车辆");
                break;
            default:
                break;
        }
        tvRate.setText(order.getRoomloan().getRate()+"厘~"+order.getRoomloan().getMaxrate()+"厘");
        tvAmount.setText(order.getRoomloan().getCount());
        tvCredit.setText(order.getRoomloan().getGoodreliability());
        tvTerm.setText(order.getRoomloan().getLoantime());
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_orderDetail_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter.getItemViewType(position) == OrderDetailMultiItem.DESCRIPTION){
            String key = ((OrderDetailMultiItem)(adapter.getData().get(position))).getKey();
            if (key != null){
                if (key.equals("联系方式")){
                    telphone(data.get(position).getValue());
                    return;
                }
            }
        }
        if (data.get(position).getItemType() == OrderDetailMultiItem.BUTTON){
            switch (order.getRoomloan().getStatus()){
                case Action.ORDER_TYPE_WZF://未支付押金
                    showDialog();
                    break;
                case Action.ORDER_TYPE_YZF://已支付押金
                    showDialog();
                    break;
                case Action.ORDER_TYPE_YQD://以抢单
                    FirstDraftProgramActivity.start(OrderDetailActivity.this,order.getRoomloan().getStatus(),order.getId(),"");
                    break;
                case Action.ORDER_TYPE_YCS://已初审
                    FirstDraftProgramActivity.start(OrderDetailActivity.this,order.getRoomloan().getStatus(),order.getId(),order.getFirsttrial().getFirstpicture());
                    break;
                case Action.ORDER_TYPE_YZFFWF://已支付服务费
                    sure();
                    break;
                default:
                    break;
            }
        }
    }

    //获取电话号码
    private void telphone(String realPhone){
        Api.getDefault().grab(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        if (!StringUtils.isEmpty(realPhone) && !StringUtils.isEmpty(list)){
                            call(list,realPhone);
                        }else {
                            ToastUtil.showInfo("号码出错，无法拨打！");
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                    }
                });
    }

    /**
     * 打电话
     */
    private void call(String phone,String realPhone){
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CALL_PHONE)
                .subscribe(aBoolean -> {
                    Logger.d("修改头像");
                    if (aBoolean) {
                        Api.getDefault().axbbanding(KBApplication.token,KBApplication.userid,realPhone)
                                .compose(RxHelper.handleResult())
                                .subscribe(new RxSubscriber<String>(this) {
                                    @Override
                                    protected void success(String list) {
                                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
                                        startActivity(intent);
                                    }

                                    @Override
                                    protected void error(String msg) {
                                        Logger.d(msg);
                                        ToastUtil.showError(msg);
                                    }
                                });
                    }else {
                        ToastUtil.showInfo("无法获取拨打电话权限！");
                    }
                });
    }

    public void showDialog() {
        KBDialog.init()
                .setLayoutId(R.layout.dialog_with_hour)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setOnClickListener(R.id.dialog_withHour_ok, v -> {
                            dialog.dismiss();
                            String a = ((TextView)holder.getView(R.id.dialog_withHour_hour)).getText().toString();
                            graBorder(a);
                        });
                        holder.setOnClickListener(R.id.dialog_withHour_remove, v -> {
                            String a = ((TextView)holder.getView(R.id.dialog_withHour_hour)).getText().toString();
                            int b = Integer.valueOf(a);
                            if (b>1){
                                int c = b-1;
                                holder.setText(R.id.dialog_withHour_hour,String.valueOf(c));
                            }
                        });
                        holder.setOnClickListener(R.id.dialog_withHour_add, v -> {
                            String a = ((TextView)holder.getView(R.id.dialog_withHour_hour)).getText().toString();
                            int b = Integer.valueOf(a);
                            if (b<4){
                                int c = b+1;
                                holder.setText(R.id.dialog_withHour_hour,String.valueOf(c));
                            }
                        });
                    }
                })
                .setOutCancel(false)
                .setMargin(50)
                .show(getSupportFragmentManager());
    }

    /**
     * 抢单
     */
    public void graBorder(String time){
        Api.getDefault().graborder(KBApplication.token,KBApplication.userid,order.getId(),time)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("抢单成功！");
                        finish();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }



    /**
     * 点击确认放贷
     */
    private void sure(){
        Api.getDefault().sure(KBApplication.token,KBApplication.userid,order.getId())
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("确认办理完成！");
                        finish();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 24){
            finish();
        }
    }

    @OnClick(R.id.orderDetail_toolbarRight)
    public void error(){
        OrderErrorActivity.start(this,order.getId());
    }
}
