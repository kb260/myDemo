package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.MyApplication;
import com.kb260.gxdk.model.MyApplicationDetail;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;
import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author  KB260
 * Created on  2017/11/10
 */
public class MyApplicationDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_myApplicationDetail_tvStatus)
    TextView tvStatus;
    @BindView(R.id.a_myApplicationDetail_llResult)
    LinearLayout llResult;
    @BindView(R.id.a_myApplicationDetail_llExpectedTime)
    LinearLayout llExpectedTime;
    @BindView(R.id.a_myApplicationDetail_llCancelAndPay)
    LinearLayout llCancelAndPay;
    @BindView(R.id.a_myApplicationDetail_llCancelAndGet)
    LinearLayout llCancelAndGet;
    @BindView(R.id.a_myApplicationDetail_btConfirmLoan)
    Button btConfirmLoan;
    @BindView(R.id.a_myApplicationDetail_btPay)
    Button btPay;

    @BindView(R.id.a_myApplicationDetail_tvResult)
    TextView tvResult;
    @BindView(R.id.a_myApplicationDetail_ivUser)
    CircleImageView ivUser;
    @BindView(R.id.a_myApplicationDetail_tvNickname)
    TextView tvNickname;
    @BindView(R.id.a_myApplicationDetail_tvTime)
    TextView tvTime;
    @BindView(R.id.a_myApplicationDetail_tvType)
    TextView tvType;
    @BindView(R.id.a_myApplicationDetail_tvExpect)
    TextView tvExpect;
    @BindView(R.id.a_myApplicationDetail_tvFaith)
    TextView tvFaith;
    @BindView(R.id.a_myApplicationDetail_tvTerm)
    TextView tvTerm;
    @BindView(R.id.a_myApplicationDetail_tvIdCard)
    TextView tvIdCard;
    @BindView(R.id.a_myApplicationDetail_tvAge)
    TextView tvAge;
    @BindView(R.id.a_myApplicationDetail_tvPhone)
    TextView tvPhone;
    @BindView(R.id.a_myApplicationDetail_tvAddress)
    TextView tvAddress;
    @BindView(R.id.a_myApplicationDetail_tvMarriage)
    TextView tvMarriage;
    @BindView(R.id.a_myApplicationDetail_tvHouseAddress)
    TextView tvHouseAddress;
    @BindView(R.id.a_myApplicationDetail_tvHouseArea)
    TextView tvHouseArea;
    @BindView(R.id.a_myApplicationDetail_tvHouseNature)
    TextView tvHouseNature;
    @BindView(R.id.a_myApplicationDetail_tvHouseStatus)
    TextView tvHouseStatus;
    @BindView(R.id.a_myApplicationDetail_tvRate)
    TextView tvRate;
    @BindView(R.id.a_myApplicationDetail_tvCompany)
    TextView tvCompany;
    @BindView(R.id.a_myApplicationDetail_tvIdentity)
    TextView tvIdentity;
    @BindView(R.id.a_myApplicationDetail_tvSite)
    TextView tvSite;
    @BindView(R.id.a_myApplicationDetail_tvOverdue)
    TextView tvOverdue;
    @BindView(R.id.a_myApplicationDetail_tvOverdueType)
    TextView tvOverdueType;
    @BindView(R.id.a_myApplicationDetail_tvOverdueTime)
    TextView tvOverdueTime;
    @BindView(R.id.a_myApplicationDetail_tvMoney)
    TextView tvMoney;
    @BindView(R.id.a_myApplicationDetail_tvOther)
    TextView tvOther;
    @BindView(R.id.a_myApplicationDetail_tvQuota)
    TextView tvQuota;
    @BindView(R.id.a_myApplicationDetail_tvResultRate)
    TextView tvResultRate;
    @BindView(R.id.a_myApplicationDetail_tvLoanType)
    TextView tvLoanType;
    @BindView(R.id.a_myApplicationDetail_tvRepaymentType)
    TextView tvRepaymentType;
    @BindView(R.id.a_myApplicationDetail_tvRepaymentTime)
    TextView tvRepaymentTime;
    @BindView(R.id.a_myApplicationDetail_tvExpectedTime)
    TextView tvExpectedTime;


    int type,id;
    MyApplication myApplication;

    public static void start(Context context,MyApplication myApplication){
        Intent intent = new Intent(context,MyApplicationDetailActivity.class);
        intent.putExtra(Action.ORDER_TYPE,myApplication);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_application_detail;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        myApplication = (MyApplication) intent.getSerializableExtra(Action.ORDER_TYPE);
        type(myApplication.getStatus());
        updateUI();
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_myApplicationDetail_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    //申请中  取消
    @OnClick(R.id.a_myApplicationDetail_btCancel2)
    public void btCancel2(){
        queryIntegral();
    }
    //申请中  申请解锁押金
    @OnClick(R.id.a_myApplicationDetail_btGet2)
    public void btGet2(){
        queryIntegral();
    }

    //已终审  支付
    @OnClick(R.id.a_myApplicationDetail_btPay)
    public void pay(){
        showDialog();
    }

    //已放款  确认放款
    @OnClick(R.id.a_myApplicationDetail_btConfirmLoan)
    public void btConfirmLoan(){
        showDialog();
    }


    //未支付押金  支付
    @OnClick(R.id.a_myApplicationDetail_btPay1)
    public void btPay1(){
        showDialog();
    }

    //未支付押金  取消
    @OnClick(R.id.a_myApplicationDetail_btCancel1)
    public void btCancel1(){
        showDialog();
    }



    private void updateUI() {
        if (myApplication.getType()!=null){
            switch (myApplication.getType()){
                case "1":
                    tvType.setText("房贷");
                    break;
                case "2":
                    tvType.setText("车贷");
                    break;
                default:
                    break;
            }
        }
        tvExpect.setText(String.valueOf(myApplication.getMaxrate()));
        tvFaith.setText(myApplication.getAge());
        tvTerm.setText(myApplication.getLoantime());
        tvIdCard.setText(myApplication.getIdcard());
        tvAge.setText(myApplication.getAge());
        tvPhone.setText(myApplication.getTelphone());
        tvAddress.setText(myApplication.getAddress());
        tvMarriage.setText(myApplication.getSign());
        tvHouseAddress.setText(myApplication.getRoomaddress());
        tvHouseArea.setText(myApplication.getArea());
        tvHouseNature.setText(myApplication.getNature());
        tvHouseStatus.setText(myApplication.getCurrent());
        tvRate.setText(myApplication.getRate()+"%~"+myApplication.getMaxrate()+"%");
        tvCompany.setText(myApplication.getIscompany());
        tvIdentity.setText(myApplication.getPosition());
        tvSite.setText(myApplication.getRatio());
        tvOverdue.setText(myApplication.getIsoverdue());
        tvOverdueType.setText(myApplication.getDueoption());
        tvOverdueTime.setText(myApplication.getDuetime());
        tvMoney.setText(String.valueOf(myApplication.getCount()));
        tvOther.setText(myApplication.getInstruction());
        tvQuota.setText(String.valueOf(myApplication.getMaxrate()));
        tvResultRate.setText(myApplication.getInstruction());
        tvLoanType.setText(myApplication.getPayform());
        tvRepaymentType.setText(myApplication.getPayform());
        tvRepaymentTime.setText(myApplication.getPayform());
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
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    public void showDialog1(String integral) {
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setText(R.id.title, "您需支付" + integral + "积分");
                        holder.setText(R.id.message, "温馨提示：用户申请借贷时需要支付押金，" +
                                "借款成功之后则需支付剩余的服务费用");
                        holder.setOnClickListener(R.id.ok, v -> {
                            dialog.dismiss();
                            RechargePayActivity.start(MyApplicationDetailActivity.this,0.1,"1",String.valueOf(myApplication.getId()));
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

    public void showDialog() {
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setText(R.id.title, "您需解冻8888积分！");
                        holder.setText(R.id.message, "温馨提示：您的借款已成功，请支付剩余的服务费用");
                        holder.setOnClickListener(R.id.cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        holder.setOnClickListener(R.id.ok, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                RechargePayActivity.start(MyApplicationDetailActivity.this,0.1,"1",String.valueOf(myApplication.getId()));
                            }
                        });
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    public void type(String type){
        switch (type){
            case Action.ORDER_TYPE_WZF://未支付押金
                tvStatus.setText("未支付押金");
                tvStatus.setTextColor(getResources().getColor(R.color.bt_red));
                llResult.setVisibility(View.GONE);
                llExpectedTime.setVisibility(View.GONE);
                llCancelAndPay.setVisibility(View.VISIBLE);
                llCancelAndGet.setVisibility(View.GONE);
                btConfirmLoan.setVisibility(View.GONE);
                btPay.setVisibility(View.GONE);
                break;
            case Action.ORDER_TYPE_YZF://已支付押金
                tvStatus.setText("已支付押金");
                tvStatus.setTextColor(getResources().getColor(R.color.bt_red));
                llResult.setVisibility(View.GONE);
                llExpectedTime.setVisibility(View.GONE);
                llCancelAndPay.setVisibility(View.GONE);
                llCancelAndGet.setVisibility(View.VISIBLE);
                btConfirmLoan.setVisibility(View.GONE);
                btPay.setVisibility(View.GONE);

                break;
            /*case Action.ORDER_TYPE_YQD://以抢单
                tvStatus.setText("以抢单");
                tvStatus.setTextColor(getResources().getColor(R.color.bt_red));
                llResult.setVisibility(View.VISIBLE);
                tvExpectedTime.setVisibility(View.VISIBLE);
                tvResult.setText("初审结果");

                llExpectedTime.setVisibility(View.VISIBLE);
                llCancelAndPay.setVisibility(View.GONE);
                llCancelAndGet.setVisibility(View.GONE);
                btConfirmLoan.setVisibility(View.GONE);
                btPay.setVisibility(View.GONE);
                tvExpectedTime.setText(R.string.a_myApplicationDetail_expectedTime);
                break;*/
            case Action.ORDER_TYPE_YCS://已初审
                tvStatus.setText("已初审");
                tvStatus.setTextColor(getResources().getColor(R.color.txt333));
                llResult.setVisibility(View.VISIBLE);
                tvExpectedTime.setVisibility(View.VISIBLE);
                tvResult.setText("终审结果");

                llExpectedTime.setVisibility(View.VISIBLE);
                llCancelAndPay.setVisibility(View.GONE);
                llCancelAndGet.setVisibility(View.GONE);
                btConfirmLoan.setVisibility(View.GONE);
                btPay.setVisibility(View.VISIBLE);
                tvExpectedTime.setText(R.string.a_myApplicationDetail_expectedTime1);
                break;
            case Action.ORDER_TYPE_YZS://已终审
                tvStatus.setText("已终审");
                tvStatus.setTextColor(getResources().getColor(R.color.txt333));
                llResult.setVisibility(View.VISIBLE);
                tvExpectedTime.setVisibility(View.VISIBLE);

                llExpectedTime.setVisibility(View.VISIBLE);
                llCancelAndPay.setVisibility(View.GONE);
                llCancelAndGet.setVisibility(View.GONE);
                btConfirmLoan.setVisibility(View.GONE);
                btPay.setVisibility(View.VISIBLE);
                tvExpectedTime.setText(R.string.a_myApplicationDetail_expectedTime1);
                break;
            case Action.ORDER_TYPE_YZFFWF://已支付服务费
                tvStatus.setText("已支付服务费");
                tvStatus.setTextColor(getResources().getColor(R.color.txt333));
                llResult.setVisibility(View.VISIBLE);
                tvExpectedTime.setVisibility(View.VISIBLE);

                llExpectedTime.setVisibility(View.VISIBLE);
                llCancelAndPay.setVisibility(View.GONE);
                llCancelAndGet.setVisibility(View.GONE);
                btConfirmLoan.setVisibility(View.GONE);
                btPay.setVisibility(View.VISIBLE);
                tvExpectedTime.setText(R.string.a_myApplicationDetail_expectedTime1);
                break;
            case Action.ORDER_TYPE_DKCG://贷款成功
                tvStatus.setText("贷款成功");
                tvStatus.setTextColor(getResources().getColor(R.color.txt999));
                llResult.setVisibility(View.GONE);
                llExpectedTime.setVisibility(View.GONE);
                llCancelAndPay.setVisibility(View.GONE);
                llCancelAndGet.setVisibility(View.GONE);
                btConfirmLoan.setVisibility(View.GONE);
                btPay.setVisibility(View.GONE);
                break;
            case Action.ORDER_TYPE_YSX://已失效
                tvStatus.setText("已失效");
                tvStatus.setTextColor(getResources().getColor(R.color.txt_green));
                llResult.setVisibility(View.VISIBLE);
                tvExpectedTime.setVisibility(View.GONE);

                llExpectedTime.setVisibility(View.GONE);
                llCancelAndPay.setVisibility(View.GONE);
                llCancelAndGet.setVisibility(View.GONE);
                btConfirmLoan.setVisibility(View.VISIBLE);
                btPay.setVisibility(View.GONE);
                break;
            case Action.ORDER_TYPE_DCL://待处理
                tvStatus.setText("待处理");
                tvStatus.setTextColor(getResources().getColor(R.color.txt_green));
                llResult.setVisibility(View.VISIBLE);
                tvExpectedTime.setVisibility(View.GONE);

                llExpectedTime.setVisibility(View.GONE);
                llCancelAndPay.setVisibility(View.GONE);
                llCancelAndGet.setVisibility(View.GONE);
                btConfirmLoan.setVisibility(View.VISIBLE);
                btPay.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    /**
     * 点击成功查询
     */
    private void clickSuccess(){
        Api.getDefault().clicksuccess(KBApplication.token)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {

                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }
}
