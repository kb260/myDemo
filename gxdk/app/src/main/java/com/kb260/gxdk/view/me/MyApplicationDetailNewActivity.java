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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.ApplicationDetailAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.ApplicationDetailMultiItem;
import com.kb260.gxdk.model.EventData;
import com.kb260.gxdk.model.MyApplication;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.ImageLoader;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/11/16
 */
public class MyApplicationDetailNewActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_myApplicationDetailNew_rv)
    RecyclerView rv;

    MyApplication myApplication;
    int id;
    ApplicationDetailAdapter adapter;
    List<ApplicationDetailMultiItem> data;

    public static void start(Context context, int id){
        Intent intent = new Intent(context,MyApplicationDetailNewActivity.class);
        intent.putExtra(Action.ID,id);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_application_detail_new;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_myApplicationDetail_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    private void initIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra(Action.ID,-1);
    }

    private void getData() {
        Api.getDefault().selorderdetail(KBApplication.token,KBApplication.userid,id)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<MyApplication>(this) {
                    @Override
                    protected void success(MyApplication list) {
                        myApplication = list;
                        initData();
                        initRv();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void initData() {
        data = new ArrayList<>();

        String type = "";
        switch (myApplication.getType()){
            case "1":
                type = "房屋";
                break;
            case "2":
                type = "车辆";
                break;
            default:
                break;
        }
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"申请类型",type));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"期望",myApplication.getCount()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"期限",myApplication.getLoantime()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"还款周期",myApplication.getYearpay()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"还款方式",myApplication.getMonthpay()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.LINE));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"身份证",myApplication.getIdcard()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"年龄",myApplication.getAge()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"联系方式",myApplication.getTelphone()));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"常住地址",myApplication.getAddress()));

        String sign = myApplication.getSign();
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"婚姻状况",sign));
        switch (sign){
            case "未婚":
                break;
            case "已婚":
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"配偶姓名",myApplication.getSpousename()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"配偶联系方式",myApplication.getSpousetel()));
                break;
            default:
                break;
        }


        switch (myApplication.getType()){
            case "1":
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"房子所在地",myApplication.getRoomaddress()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"房屋面积",myApplication.getArea()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"房屋性质",myApplication.getNature()));
                String current = myApplication.getCurrent();
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"房屋现状",current));
                switch (current){
                    case "全款":
                        break;
                    case "按揭":
                        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"按揭银行",myApplication.getMortgagebank()));
                        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"剩余尾款",myApplication.getSurpius()));
                        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"需要垫资",myApplication.getExpenditure()));
                        break;
                    default:
                        break;
                }
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"房龄",myApplication.getTimes()));
                break;
            case "2":
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"车架号",myApplication.getCarframe()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"车品牌",myApplication.getCarbrand()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"车牌",myApplication.getCarno()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"车龄",myApplication.getCarage()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"行驶距离",myApplication.getDistance()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"车况",myApplication.getIndustry()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"购车原价",myApplication.getPaycity()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"购车时间",myApplication.getLimittime()
                +"-"+myApplication.getPayform()));
                break;
            default:
                break;
        }

        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"接受月利率",myApplication.getRate()
                +"厘~"+myApplication.getMaxrate()+"厘"));
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.LINE));


        switch (myApplication.getType()){
            case "1":
                String isCompany = myApplication.getIscompany();
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"是否有公司",isCompany));
                switch (isCompany){
                    case "否":
                        break;
                    case "是":
                        String position = myApplication.getPosition();
                        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"身份",position));
                        switch (position){
                            case "股东":
                                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"占股比例",myApplication.getRatio()));
                                break;
                            default:
                                break;
                        }
                        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"是否有场地",myApplication.getIsplace()));

                        break;
                    default:
                        break;
                }
                break;
            case "2":
                String isPay = myApplication.getIspay();
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"车现状",isPay));
                switch (isPay){
                    case "全款":
                        break;
                    case "按揭":
                        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"按揭银行",myApplication.getMortgagebank()));
                        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"剩余尾款",myApplication.getSurpius()));
                        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"需要垫资",myApplication.getExpenditure()));
                        break;
                    default:
                        break;
                }

                break;
            default:
                break;
        }
        String isOverdue = myApplication.getIsoverdue();
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"是否有逾期",isOverdue));
        switch (isOverdue){
            case "否":
                break;
            case "是":
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"逾期项",myApplication.getDueoption()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"逾期时间",myApplication.getDuetime()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"金额",myApplication.getAmount()));
                break;
            default:
                break;
        }
        data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"其他资产说明",myApplication.getInstruction()));
        if (myApplication.getStatus().equals("3") || myApplication.getStatus().equals("4")|| myApplication.getStatus().equals("5")|| myApplication.getStatus().equals("6")){
            if (myApplication.getFirsttrial()!=null){
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.RESULT,"初审结果"));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"额度",myApplication.getFirsttrial().getCount()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"月利率",myApplication.getFirsttrial().getRate()
                        +"~"+myApplication.getFirsttrial().getMaxrate()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"办理方式",myApplication.getFirsttrial().getLoantype()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"还款期限",myApplication.getFirsttrial().getLoanlimit()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"还款周期",myApplication.getFirsttrial().getLoantime()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"预计终审结果时间",
                        myApplication.getFirsttrial().getLastresult()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"所需材料",myApplication.getFirsttrial().getFirstpicture()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"备注",myApplication.getFirsttrial().getRemark()));
            }
        }
        if (myApplication.getStatus().equals("4") || myApplication.getStatus().equals("5")|| myApplication.getStatus().equals("6")){
            if (myApplication.getLasttrial()!=null){
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.RESULT,"终审结果"));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"额度",myApplication.getLasttrial().getCount()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"月利率",myApplication.getLasttrial().getRate()+"~"
                +myApplication.getLasttrial().getMaxrate()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"办理方式",myApplication.getLasttrial().getLoantype()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"还款期限",myApplication.getLasttrial().getLoanlimit()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"还款周期",myApplication.getLasttrial().getLoantime()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"预计完成时间",myApplication.getLasttrial().getLendingtime()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"所需材料",myApplication.getLasttrial().getLastpicture()));
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.TEXT_KEY_VALUE,"备注",myApplication.getLasttrial().getRemark()));
            }
        }

        switch (myApplication.getStatus()){
            case Action.ORDER_TYPE_WZF://未支付押金
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.BUTTON_TWO,"取消","支付"));
                break;
            case Action.ORDER_TYPE_YZF://已支付押金
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.BUTTON_ONE,"申请解锁押金"));
                break;
            case Action.ORDER_TYPE_YQD://以抢单
                break;
            case Action.ORDER_TYPE_YCS://已初审
                break;
            case Action.ORDER_TYPE_YZS://已终审
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.BUTTON_ONE,"支付"));
                break;
            case Action.ORDER_TYPE_YZFFWF://已支付服务费
                if (myApplication.getReserve().equals("1")){
                    data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.BUTTON_ONE,"确认完成"));
                }
                break;
            case Action.ORDER_TYPE_DKCG://贷款成功
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.BUTTON_ONE,"删除"));
                break;
            case Action.ORDER_TYPE_YSX://已失效
                data.add(new ApplicationDetailMultiItem(ApplicationDetailMultiItem.BUTTON_ONE,"删除"));
                break;
            case Action.ORDER_TYPE_DCL://待处理
                break;
            default:
                break;
        }
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ApplicationDetailAdapter(data);

        rv.setAdapter(adapter);
        addTop();
        adapter.setOnItemChildClickListener(this);
    }

    private void addTop() {
        View view = getLayoutInflater().inflate(R.layout.my_application_detail_top, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);

        ImageView ivUser = view.findViewById(R.id.top_myApplicationDetail_ivUser);
        TextView tvName = view.findViewById(R.id.top_myApplicationDetail_tvNickname);
        TextView tvTime = view.findViewById(R.id.top_myApplicationDetail_tvTime);
        TextView tvStatus = view.findViewById(R.id.top_myApplicationDetail_tvStatus);

        ImageLoader.showImage(ivUser,myApplication.getAppuser().getPicture());
        tvName.setText(myApplication.getRealname());
        tvTime.setText(myApplication.getCreatetime());
        switch (myApplication.getStatus()){
            case Action.ORDER_TYPE_WZF://未支付押金
                tvStatus.setText("未支付押金");
                break;
            case Action.ORDER_TYPE_YZF://已支付押金
                tvStatus.setText("已支付押金");
                break;
            case Action.ORDER_TYPE_YQD://已抢单
                tvStatus.setText("已抢单");
                break;
            case Action.ORDER_TYPE_YCS://已初审
                tvStatus.setText("已初审");
                break;
            case Action.ORDER_TYPE_YZS://已终审
                tvStatus.setText("已终审");
                break;
            case Action.ORDER_TYPE_YZFFWF://已支付服务费
                tvStatus.setText("已支付服务费");
                break;
            case Action.ORDER_TYPE_DKCG://贷款成功
                tvStatus.setText("办理成功");
                break;
            case Action.ORDER_TYPE_YSX://已失效
                tvStatus.setText("已失效");
                break;
            case Action.ORDER_TYPE_DCL://待处理
                tvStatus.setText("待处理");
                break;
            case Action.ORDER_TYPE_9://押金申请解锁中
                tvStatus.setText("押金申请解锁中");
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.item_myApplication_button:
                switch (myApplication.getStatus()){
                    case Action.ORDER_TYPE_YZS://支付
                        selscorepaylast();
                        break;
                    case Action.ORDER_TYPE_DKCG://确认放款
                        //delete(myApplication.getId());
                        break;
                    case Action.ORDER_TYPE_YZFFWF:
                        if (myApplication.getReserve().equals("1")){
                            usersure();
                        }
                        break;
                    case Action.ORDER_TYPE_YZF://解锁押金
                        KBDialog.init()
                                .setLayoutId(R.layout.confirm_layout)
                                .setConvertListener(new KBViewConvertListener() {
                                    @Override
                                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                                        holder.setVisibility(R.id.message, View.GONE);
                                        holder.setText(R.id.title, "确认解锁押金？");
                                        holder.setOnClickListener(R.id.ok, v -> {
                                            dialog.dismiss();
                                            applyopen();
                                        });
                                        holder.setOnClickListener(R.id.cancel, v -> {
                                            dialog.dismiss();
                                        });
                                    }
                                })
                                .setMargin(60)
                                .show(getSupportFragmentManager());
                        break;
                    case Action.ORDER_TYPE_YSX://已失效
                        //delete(myApplication.getId());
                        break;
                    default:
                        break;
                }
                break;
            case R.id.item_myApplication_button1:
                switch (myApplication.getStatus()){
                    case Action.ORDER_TYPE_WZF://未支付押金  取消
                        cancel();
                        break;
                    default:
                        break;
                }
                break;
            case R.id.item_myApplication_button2:
                switch (myApplication.getStatus()){
                    case Action.ORDER_TYPE_WZF://未支付押金  支付
                        queryIntegral();
                        break;

                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    /**
     * 取消订单
     */
    private void cancel() {
        Api.getDefault().cancel(KBApplication.token,KBApplication.userid,myApplication.getId())
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("取消成功！");
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
     * 查询支付押金
     */
    private void queryIntegral() {
        Api.getDefault().selpayscore(KBApplication.token,KBApplication.userid,myApplication.getCountDouble())
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        showDialog1(list);
                        initData();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    /**
     * 查询支付剩余的积分
     */
    private void selscorepaylast() {
        Api.getDefault().selscorepaylast(KBApplication.token,KBApplication.userid,myApplication.getId(),myApplication.getLasttrial().getCountDouble())
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        showDialog(list);
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    /**
     * 申请解锁
     */
    private void applyopen() {
        Api.getDefault().applyopen(KBApplication.token,KBApplication.userid, myApplication.getId())
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        show24();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    public void show24(){
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setVisibility(R.id.message, View.GONE);
                        holder.setVisibility(R.id.cancel, View.GONE);
                        holder.setText(R.id.title, "您的申请已提交，请耐心等待，工作人员会在1~3工作日进行审核操作，谢谢！");
                        holder.setOnClickListener(R.id.ok, v -> {
                            dialog.dismiss();
                            getData();
                        });
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    public void showDialog1(String integral) {
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setText(R.id.title, "您需支付" + integral + "积分");
                        holder.setText(R.id.message, "温馨提示：用户申请时需要支付押金，" +
                                "办理成功之后则需支付剩余的服务费用");
                        holder.setOnClickListener(R.id.ok, v -> {
                            dialog.dismiss();
                            RechargePayActivity.start(MyApplicationDetailNewActivity.this,
                                    Double.valueOf(integral)/100,"1",String.valueOf(myApplication.getId()));

                        });
                        holder.setOnClickListener(R.id.cancel, v -> {
                            dialog.dismiss();
                        });
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    public void showDialog(String integral) {
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setText(R.id.title, "您需解冻"+ integral + "积分！");
                        holder.setText(R.id.message, "温馨提示：您的办理已成功，请支付剩余的服务费用");
                        holder.setOnClickListener(R.id.cancel, v -> dialog.dismiss());
                        holder.setOnClickListener(R.id.ok, v -> {
                            dialog.dismiss();
                            PayIntegralActivity.start(MyApplicationDetailNewActivity.this,integral,myApplication.getId());
                        });
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    /**
     * 点击确认放贷
     */
    private void sure(){
        Api.getDefault().sure(KBApplication.token,KBApplication.userid,myApplication.getId())
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("确认办理成功！");
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
    private void usersure(){
        Api.getDefault().usersurn(KBApplication.token,KBApplication.userid,myApplication.getId())
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("确认办理成功！");
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
     * 删除
     * @param id
     */
    private void delete(int id){
        Api.getDefault().delroomloan(KBApplication.token,KBApplication.userid,id)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("删除成功");
                        finish();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }
}
