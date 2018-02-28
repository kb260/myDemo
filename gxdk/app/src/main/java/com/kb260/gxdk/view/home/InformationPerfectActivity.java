package com.kb260.gxdk.view.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.CarLoanFirst;
import com.kb260.gxdk.entity.CarLoanSecond;
import com.kb260.gxdk.entity.CreditFirst;
import com.kb260.gxdk.entity.CreditSecond;
import com.kb260.gxdk.entity.MortgageFirst;
import com.kb260.gxdk.entity.MortgageSecond;
import com.kb260.gxdk.entity.UploadPic;
import com.kb260.gxdk.model.EventData;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.SPUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.me.*;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 */
public class InformationPerfectActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_home_informationPerfect_tvIdentity)
    TextView tvIdentity;
    @BindView(R.id.a_home_informationPerfect_tvMarriage)
    TextView tvMarriage;
    @BindView(R.id.a_home_informationPerfect_tvAccount)
    TextView tvAccount;
    @BindView(R.id.a_home_informationPerfect_tvDeed)
    TextView tvDeed;
    @BindView(R.id.a_home_informationPerfect_tvBusinessLicense)
    TextView tvBusinessLicense;
    @BindView(R.id.a_home_informationPerfect_tvCredit)
    TextView tvCredit;
    @BindView(R.id.a_home_informationPerfect_tvCharter)
    TextView tvCharter;
    @BindView(R.id.a_home_informationPerfect_tvInformationSupplement)
    TextView tvInformationSupplement;
    @BindView(R.id.a_informationPerfect_llHouse)
    LinearLayout llHouse;
    @BindView(R.id.a_informationPerfect_llCar)
    LinearLayout llCar;
    @BindView(R.id.a_home_informationPerfect_tvCldjz)
    TextView tvCldjz;
    @BindView(R.id.a_home_informationPerfect_tvGcfp)
    TextView tvGcfp;
    @BindView(R.id.a_home_informationPerfect_tvXsz)
    TextView tvXsz;
    @BindView(R.id.a_home_informationPerfect_tvJsz)
    TextView tvJsz;
    @BindView(R.id.a_informationPerfect_llCharter)
    LinearLayout llCharter;
    @BindView(R.id.a_informationPerfect_vCharter)
    View vCharter;


    String idCardUrl, marryUrl, accountBookUrl, deedUrl, businessLicenseUrl, credit, charter, informationSupplement;
    MortgageFirst mortgageFirst;
    MortgageSecond mortgageSecond;
    CarLoanFirst carLoanFirst;
    CarLoanSecond carLoanSecond;
    String count;

    int type;

    public static void start(Activity context, MortgageFirst mortgageFirst, MortgageSecond mortgageSecond) {
        Intent intent = new Intent(context, InformationPerfectActivity.class);
        intent.putExtra(Contact.TYPE, 1);
        intent.putExtra(Contact.MORTGAGE_FIRST, mortgageFirst);
        intent.putExtra(Contact.MORTGAGE_SECOND, mortgageSecond);
        context.startActivityForResult(intent,24);
    }

    public static void start(Activity context, CarLoanFirst carLoanFirst, CarLoanSecond carLoanSecond) {
        Intent intent = new Intent(context, InformationPerfectActivity.class);
        intent.putExtra(Contact.TYPE, 2);
        intent.putExtra(Contact.CAR_LOAN_FIRST, carLoanFirst);
        intent.putExtra(Contact.CAR_LOAN_SECOND, carLoanSecond);
        context.startActivityForResult(intent,24);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_information_perfect;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        initIntent();
        //initSP();
    }

    private void initSP() {
        String sfz = SPUtils.getInstance().getString(Action.SP_SFZ);
        if (!StringUtils.isEmpty(sfz)){
            idCardUrl = sfz;
            tvIdentity.setText("已完成");
        }
        String zx = SPUtils.getInstance().getString(Action.SP_ZX);
        if (!StringUtils.isEmpty(zx)){
            credit = zx;
            tvCredit.setText("已完成");
        }
        String zc = SPUtils.getInstance().getString(Action.SP_ZC);
        if (!StringUtils.isEmpty(zc)){
            charter = zc;
            tvCharter.setText("已完成");
        }
        String xxbc = SPUtils.getInstance().getString(Action.SP_XXBC);
        if (!StringUtils.isEmpty(xxbc)){
            informationSupplement = xxbc;
            tvInformationSupplement.setText("已完成");
        }

        switch (type) {
            case 1:
                String jhz = SPUtils.getInstance().getString(Action.SP_JHZ);
                if (!StringUtils.isEmpty(jhz)){
                    marryUrl = jhz;
                    tvMarriage.setText("已完成");
                }
                String hkb = SPUtils.getInstance().getString(Action.SP_HKB);
                if (!StringUtils.isEmpty(hkb)){
                    accountBookUrl = hkb;
                    tvAccount.setText("已完成");
                }
                String fcz = SPUtils.getInstance().getString(Action.SP_FCZ);
                if (!StringUtils.isEmpty(fcz)){
                    deedUrl = fcz;
                    tvDeed.setText("已完成");
                }
                String gsyyzz = SPUtils.getInstance().getString(Action.SP_GSYYZZ);
                if (!StringUtils.isEmpty(gsyyzz)){
                    businessLicenseUrl = gsyyzz;
                    tvBusinessLicense.setText("已完成");
                }
                break;
            case 2:
                String cldjz = SPUtils.getInstance().getString(Action.SP_CLDJZ);
                if (!StringUtils.isEmpty(cldjz)){
                    marryUrl = cldjz;
                    tvCldjz.setText("已完成");
                }
                String gcfp = SPUtils.getInstance().getString(Action.SP_GCFP);
                if (!StringUtils.isEmpty(gcfp)){
                    accountBookUrl = gcfp;
                    tvGcfp.setText("已完成");
                }
                String xsz = SPUtils.getInstance().getString(Action.SP_XSZ);
                if (!StringUtils.isEmpty(xsz)){
                    deedUrl = xsz;
                    tvXsz.setText("已完成");
                }
                String jsz = SPUtils.getInstance().getString(Action.SP_JSZ);
                if (!StringUtils.isEmpty(jsz)){
                    businessLicenseUrl = jsz;
                    tvJsz.setText("已完成");
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initIntent() {
        Intent intent = getIntent();
        type = intent.getIntExtra(Contact.TYPE, 0);
        switch (type) {
            case 1:
                mortgageFirst = (MortgageFirst) intent.getSerializableExtra(Contact.MORTGAGE_FIRST);
                mortgageSecond = (MortgageSecond) intent.getSerializableExtra(Contact.MORTGAGE_SECOND);
                count = mortgageSecond.getApplicationMoney();
                llHouse.setVisibility(View.VISIBLE);
                break;
            case 2:
                carLoanFirst = (CarLoanFirst) intent.getSerializableExtra(Contact.CAR_LOAN_FIRST);
                carLoanSecond = (CarLoanSecond) intent.getSerializableExtra(Contact.CAR_LOAN_SECOND);
                count = carLoanSecond.getSqje();
                llCar.setVisibility(View.VISIBLE);

                vCharter.setVisibility(View.GONE);
                llCharter.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.f_home_informationPerfect_toolbarTitle);
        initThisToolbarHaveBack(toolbar, this);
    }

    //身份证 !
    @OnClick(R.id.a_informationPerfect_llIdentity)
    public void sfz() {
        UploadDocumentsActivity.start(this, Action.UPLOAD_TYPE_SFZ,idCardUrl);
    }

    //结婚证/离婚证
    @OnClick(R.id.a_informationPerfect_llMarriage)
    public void jhz() {
        UploadDocumentsActivity.start(this, Action.UPLOAD_TYPE_JHZ,marryUrl);
    }

    //户口本 ！
    @OnClick(R.id.a_informationPerfect_llAccount)
    public void hkb() {
        UploadDocumentsActivity.start(this, Action.UPLOAD_TYPE_HKB,accountBookUrl);
    }

    //房产证 ！
    @OnClick(R.id.a_informationPerfect_llDeed)
    public void fcz() {
        UploadDocumentsActivity.start(this, Action.UPLOAD_TYPE_FCZ,deedUrl);
    }

    //公司营业执照
    @OnClick(R.id.a_informationPerfect_llBusinessLicense)
    public void yyzz() {
        UploadDocumentsActivity.start(this, Action.UPLOAD_TYPE_YYZZ,businessLicenseUrl);
    }

    //车辆登记证 ！
    @OnClick(R.id.a_informationPerfect_llCldjz)
    public void cldjz() {
        UploadDocumentsActivity.start(this, Action.UPLOAD_TYPE_CLDJZ,marryUrl);
    }

    //购车发票
    @OnClick(R.id.a_informationPerfect_llGcfp)
    public void gcfp() {
        UploadDocumentsActivity.start(this, Action.UPLOAD_TYPE_GCFP,accountBookUrl);
    }

    //行驶证 ！
    @OnClick(R.id.a_informationPerfect_llXsz)
    public void xsz() {
        UploadDocumentsActivity.start(this, Action.UPLOAD_TYPE_XSZ,deedUrl);
    }

    //驾驶证 ！
    @OnClick(R.id.a_informationPerfect_llJsz)
    public void jsz() {
        UploadDocumentsActivity.start(this, Action.UPLOAD_TYPE_JSZ,businessLicenseUrl);
    }

    //征信
    @OnClick(R.id.a_informationPerfect_llCredit)
    public void zx() {
        UploadDocumentsActivity.start(this, Action.UPLOAD_TYPE_ZX,credit);
    }

    //章程
    @OnClick(R.id.a_informationPerfect_llCharter)
    public void zc() {
        UploadDocumentsActivity.start(this, Action.UPLOAD_TYPE_ZC,charter);
    }

    //信息补充
    @OnClick(R.id.a_informationPerfect_llInformationSupplement)
    public void xxbc() {
        UploadDocumentsActivity.start(this, Action.UPLOAD_TYPE_XXBC,informationSupplement);
    }

    //确认提交
    @OnClick(R.id.a_home_informationPerfect_btSubmit)
    public void submit() {
        if (!getInput()) {
            switch (type) {
                case 1:
                    houseSubmit();
                    break;
                case 2:
                    carSubmit();
                    break;
                default:
                    break;
            }
        }
    }

    private void houseSubmit() {
        Api.getDefault().saveroomloan(KBApplication.token, KBApplication.userid, mortgageFirst.getName(), mortgageFirst.getAge(),
                mortgageFirst.getSfz(), mortgageFirst.getPhone(), mortgageFirst.getMarry(), mortgageFirst.getAddress()
                , mortgageFirst.getHouseAddress(), mortgageFirst.getProvince(), mortgageFirst.getCity(),
                mortgageFirst.getZone(), mortgageFirst.getHouseArea(), mortgageFirst.getHouseNature(),
                mortgageFirst.getHouseStatus(), mortgageSecond.getApplicationMoney(), mortgageSecond.getMinRate(),
                mortgageSecond.getRate(), mortgageSecond.getLoanTime(), mortgageSecond.getHaveCompany(),
                mortgageSecond.getIdentity(), mortgageSecond.getProportion(), mortgageSecond.getHaveSite(),
                mortgageSecond.getHaveOverdue(), mortgageSecond.getOverdue(), mortgageSecond.getOverdueTime(),
                mortgageSecond.getOverdueMoney(), mortgageSecond.getOther(), type, mortgageFirst.getSpouseName(),
                mortgageFirst.getSpousePhone(), idCardUrl, marryUrl, accountBookUrl, deedUrl, businessLicenseUrl,
                credit, charter, informationSupplement, null, null, null,
                null, null, null, mortgageFirst.getBank(), mortgageFirst.getSfdz(),
                mortgageSecond.getPaytime(),mortgageFirst.getSywk(),mortgageSecond.getMonthpay(),mortgageFirst.getHouseAge(),
                null,null,null,null,null,
                mortgageFirst.getCommunityid(),mortgageFirst.getCommunityname(),mortgageFirst.getWorkplace()
        )
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("提交成功！");
                        queryIntegral(list);
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void carSubmit() {
        Api.getDefault().saveroomloan(KBApplication.token, KBApplication.userid, carLoanFirst.getName(), carLoanFirst.getAge(),
                        carLoanFirst.getSfz(), carLoanFirst.getPhone(), carLoanFirst.getMarry(), carLoanFirst.getAddress(),
                        null, carLoanFirst.getProvince(), carLoanFirst.getCity(),
                        carLoanFirst.getZone(), null, null,
                        null, carLoanSecond.getSqje(), carLoanSecond.getMinJsll(),
                        carLoanSecond.getJsll(), carLoanSecond.getDkqx(), null,
                        null, null, null,
                        carLoanSecond.getSfyyq(), carLoanSecond.getYqx(), carLoanSecond.getYqsj(),
                        carLoanSecond.getJe(), carLoanSecond.getQtzcsm(), type, carLoanFirst.getSpouseName(),
                        carLoanFirst.getSpousePhone(), idCardUrl, marryUrl, accountBookUrl, deedUrl, businessLicenseUrl,
                        credit, charter, informationSupplement, carLoanFirst.getCjh(), carLoanFirst.getCpp(), carLoanFirst.getCp(),
                        carLoanFirst.getCl(), carLoanFirst.getXsjl(), carLoanSecond.getCxz(), carLoanSecond.getAjyh(), carLoanSecond.getXydz()
                        ,carLoanSecond.getPaytime(), carLoanSecond.getSywk(),carLoanSecond.getMonthpay(),null,carLoanFirst.getCppId(),
                        carLoanFirst.getCk(),carLoanFirst.getGcyj(),carLoanFirst.getUseddate(),carLoanFirst.getUseddateMonth(),
                carLoanFirst.getCommunityid(),carLoanFirst.getCommunityname(),carLoanFirst.getWorkplace())
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("提交成功！");
                        queryIntegral(list);
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }


    public boolean getInput() {
        switch (type) {
            case 1:
                if (StringUtils.isEmpty(idCardUrl)) {
                    ToastUtil.showInfo("请上传身份证！");
                    return true;
                }
                if (StringUtils.isEmpty(accountBookUrl)) {
                    ToastUtil.showInfo("请上传户口本！");
                    return true;
                }
                if (StringUtils.isEmpty(deedUrl)) {
                    ToastUtil.showInfo("请上传房产证！");
                    return true;
                }
                break;
            case 2:
                if (StringUtils.isEmpty(idCardUrl)) {
                    ToastUtil.showInfo("请上传身份证！");
                    return true;
                }
                if (StringUtils.isEmpty(marryUrl)) {
                    ToastUtil.showInfo("请上传车辆登记证！");
                    return true;
                }
                if (StringUtils.isEmpty(deedUrl)) {
                    ToastUtil.showInfo("请上传行驶证！");
                    return true;
                }
                if (StringUtils.isEmpty(businessLicenseUrl)) {
                    ToastUtil.showInfo("请上传驾驶证！");
                    return true;
                }
                break;
            default:
                break;
        }

        return false;
    }

    /**
     * 查询支付押金
     */
    private void queryIntegral(String id) {
        Api.getDefault().selpayscore(KBApplication.token, KBApplication.userid, Double.valueOf(count))
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        showDialog(list, id);
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }


    public void showDialog(String integral, String id) {
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
                            RechargePayActivity.start(InformationPerfectActivity.this,
                                    Double.valueOf(integral)/100, "1", id);
                        });
                        holder.setOnClickListener(R.id.cancel, v -> {
                            dialog.dismiss();
                            setResult(Action.FINISH_ACTIVITY);
                            finish();
                        });
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(EventData eventData) {
        switch (eventData.getType()) {
            //身份证
            case Action.UPLOAD_TYPE_SFZ:
                idCardUrl = eventData.getData();
                tvIdentity.setText("已完成");
                break;
            //房产证
            case Action.UPLOAD_TYPE_FCZ:
                deedUrl = eventData.getData();
                tvDeed.setText("已完成");
                break;
            //户口本
            case Action.UPLOAD_TYPE_HKB:
                accountBookUrl = eventData.getData();
                tvAccount.setText("已完成");
                break;
            //结婚证
            case Action.UPLOAD_TYPE_JHZ:
                marryUrl = eventData.getData();
                tvMarriage.setText("已完成");
                break;
            //信息补充
            case Action.UPLOAD_TYPE_XXBC:
                informationSupplement = eventData.getData();
                tvInformationSupplement.setText("已完成");
                break;
            //营业执照
            case Action.UPLOAD_TYPE_YYZZ:
                businessLicenseUrl = eventData.getData();
                tvBusinessLicense.setText("已完成");
                break;
            //章程
            case Action.UPLOAD_TYPE_ZC:
                charter = eventData.getData();
                tvCharter.setText("已完成");
                break;
            //征信
            case Action.UPLOAD_TYPE_ZX:
                credit = eventData.getData();
                tvCredit.setText("已完成");
                break;
            //车辆登记证
            case Action.UPLOAD_TYPE_CLDJZ:
                marryUrl = eventData.getData();
                tvCldjz.setText("已完成");
                break;
            //购车发票
            case Action.UPLOAD_TYPE_GCFP:
                accountBookUrl = eventData.getData();
                tvGcfp.setText("已完成");
                break;
            //行驶证
            case Action.UPLOAD_TYPE_XSZ:
                deedUrl = eventData.getData();
                tvXsz.setText("已完成");
                break;
            //驾驶证
            case Action.UPLOAD_TYPE_JSZ:
                businessLicenseUrl = eventData.getData();
                tvJsz.setText("已完成");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==24){
            setResult(Action.FINISH_ACTIVITY);
            finish();
        }
    }

}
