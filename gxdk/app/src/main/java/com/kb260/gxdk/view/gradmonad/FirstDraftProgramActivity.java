package com.kb260.gxdk.view.gradmonad;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.BCheckedAdapter;
import com.kb260.gxdk.adapter.DraftAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.CheckedData;
import com.kb260.gxdk.model.Feedback;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.GifSizeFilter;
import com.kb260.gxdk.utils.Kb260Utils;
import com.kb260.gxdk.utils.PathUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.utils.UploadImgUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 */
public class FirstDraftProgramActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_firstDraftProgram_RepaymentType)
    TextView tvRepaymentType;
    @BindView(R.id.a_firstDraftProgram_RepaymentCycle)
    TextView tvRepaymentCycle;
    @BindView(R.id.a_firstDraftProgram_tvTerm)
    TextView tvTerm;
    @BindView(R.id.a_firstDraftProgram_tvQuota)
    TextView tvQuota;
    @BindView(R.id.a_firstDraftProgram_tvRate)
    TextView tvRate;
    @BindView(R.id.a_firstDraftProgram_tvMinInterestRate)
    TextView tvMinInterestRate;
    @BindView(R.id.a_firstDraftProgram_auditResult)
    TextView tvAuditResult;

    @BindView(R.id.a_firstDraftProgram_tvResultKey)
    TextView tvResultKey;

    @BindView(R.id.a_firstDraftProgram_tvMaterial)
    TextView tvMaterial;
    @BindView(R.id.a_firstDraftProgram_etBz)
    TextView etBz;
    @BindView(R.id.a_firstDraftProgram_rv)
    RecyclerView rv;

    List<String> list,listNumber,numbers;
    int type,id,rateType,currentPosition;
    WheelAdapter wheelAdapter;
    String quota,minRate,rate,repaymentPeriod,repaymentType,repaymentCycle,result,needMaterial,bz,imgId;
    String status;
    WheelAdapter rateAdapter;

    List<CheckedData> needMaterials;
    BCheckedAdapter bCheckedAdapter;

    DraftAdapter adapter;
    List<Feedback> datas;

    public static void start(Activity context, String type, int id,String need){
        Intent intent = new Intent(context,FirstDraftProgramActivity.class);
        intent.putExtra(Contact.TYPE,type);
        intent.putExtra(Contact.ID,id);
        intent.putExtra(Contact.NEED_MATERIALS,need);
        context.startActivityForResult(intent,24);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_first_draft_program;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
        initList();
        initRv();
    }

    private void initRv() {
        rv.setLayoutManager(new GridLayoutManager(this, 3));

        datas = new ArrayList<>();
        datas.add(new Feedback(2));
        datas.add(new Feedback(0));
        adapter = new DraftAdapter(datas);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void initList() {
        needMaterials = new ArrayList<>();
        needMaterials.add(new CheckedData("身份证正反面",false));
        needMaterials.add(new CheckedData("结婚证/离婚证",false));
        needMaterials.add(new CheckedData("户口本",false));
        needMaterials.add(new CheckedData("车辆登记证",false));
        needMaterials.add(new CheckedData("行驶证",false));
        needMaterials.add(new CheckedData("征信",false));
        needMaterials.add(new CheckedData("房产证",false));
        needMaterials.add(new CheckedData("驾驶证",false));
        needMaterials.add(new CheckedData("购车发票",false));
        bCheckedAdapter = new BCheckedAdapter(needMaterials);
    }

    private void initIntent() {
        Intent intent = getIntent();
        status = intent.getStringExtra(Contact.TYPE);
        id = intent.getIntExtra(Contact.ID,-1);
        needMaterial = intent.getStringExtra(Contact.NEED_MATERIALS);

        if (!StringUtils.isEmpty(needMaterial)){
            tvMaterial.setText(needMaterial);
        }

        list= new ArrayList<>();
        listNumber = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listNumber.add(String.valueOf(i));
        }
        wheelAdapter = new ArrayWheelAdapter(listNumber);

        List<String> listPoint = new ArrayList<>();
        listPoint.add(".");
        rateAdapter = new ArrayWheelAdapter(listPoint);

        numbers = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            numbers.add(String.valueOf(i));
        }
    }

    @Override
    public void initToolbar() {
        switch (status){
            case Action.ORDER_TYPE_YQD://以抢单
                toolbarTitle.setText(R.string.a_firstDraftProgram_toolbar1);
                tvResultKey.setText("预计终审结果");
                rv.setVisibility(View.GONE);
                break;
            case Action.ORDER_TYPE_YCS://已初审
                toolbarTitle.setText(R.string.a_firstDraftProgram_toolbar2);
                tvResultKey.setText("预计办理完成时间");
                rv.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        initThisToolbarHaveBack(toolbar,this);
    }

    //还款方式
    @OnClick(R.id.a_firstDraftProgram_llQuota)
    public void selectQuota(){
        showBottomDialog();
    }

    //最大利率
    @OnClick(R.id.a_firstDraftProgram_tvRate)
    public void maxInterestRate(){
        rateType =1;
        showRate();
    }

   /* //最小利率
    @OnClick(R.id.a_firstDraftProgram_tvMinInterestRate)
    public void minInterestRate(){
        rateType =2;
        showRate();
    }*/

    //还款方式
    @OnClick(R.id.a_firstDraftProgram_llTerm)
    public void selectTerm(){
        initCycleOptionsPicker();
    }

    //还款方式
    @OnClick(R.id.a_firstDraftProgram_llRepaymentType)
    public void selectRepaymentType(){
        type = 1;
        list.clear();
        list.add("等额本息");
        list.add("先息后本");
        list.add("随借随还");
        pickerView(list);
    }

    //还款周期
    @OnClick(R.id.a_firstDraftProgram_llRepaymentCycle)
    public void selectRepaymentCycle(){
        type = 2;
        list.clear();
        list.add("3个月");
        list.add("6个月");
        list.add("一年一转");
        list.add("三年一转");
        list.add("五年一转");
        list.add("随借随还");
        list.add("10年");
        list.add("20年");
        list.add("30年");
        pickerView(list);
    }

    //还款周期
    @OnClick(R.id.a_firstDraftProgram_llResult)
    public void llResult(){
        initCycleOneOptionsPicker();
    }

    //所需材料
    @OnClick(R.id.a_firstDraftProgram_llMaterial)
    public void llMaterial() {
        if (status.equals(Action.ORDER_TYPE_YQD)){
            showBottomCheckedDialog(bCheckedAdapter);
        }
    }

    //确认提交
    @OnClick(R.id.a_firstDraftProgram_btSubmit)
    public void submit(){
        if (!getInput()){
            switch (status){
                //提供初审
                case Action.ORDER_TYPE_YQD:
                    initData();
                    break;
                //提供终审
                case Action.ORDER_TYPE_YCS:
                    giveLastResult();
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * 初审点击确认提交
     */
    private void initData() {
        Api.getDefault().givefirstresult(KBApplication.token,KBApplication.userid,id,quota,minRate,rate,repaymentPeriod
                ,repaymentType,result,repaymentCycle,needMaterial,bz,imgId)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        showDialog();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    /**
     * 终审点击确认提交
     */
    private void giveLastResult() {
        Api.getDefault().givelastresult(KBApplication.token,KBApplication.userid,id,quota,rate,minRate,repaymentPeriod
                ,repaymentType,repaymentCycle,result,needMaterial,bz,imgId)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        showDialog();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    public boolean getInput(){
        quota = tvQuota.getText().toString().trim();
        if (StringUtils.isEmpty(quota)){
            ToastUtil.showInfo("请输入额度！");
            return true;
        }
        minRate = tvMinInterestRate.getText().toString().trim();
        if (StringUtils.isEmpty(minRate)){
            ToastUtil.showInfo("请输入最小月利率！");
            return true;
        }
        rate = tvRate.getText().toString().trim();
        if (StringUtils.isEmpty(rate)){
            ToastUtil.showInfo("请输入最大月利率！");
            return true;
        }
        if (Double.valueOf(rate)<4.7 || Double.valueOf(rate)>15){
            ToastUtil.showInfo("最大利率范围为：4.7~15！");
            return true;
        }

        repaymentPeriod = tvTerm.getText().toString().trim();
        if (StringUtils.isEmpty(repaymentPeriod)){
            ToastUtil.showInfo("请选择还款期限！");
            return true;
        }
        repaymentType = tvRepaymentType.getText().toString().trim();
        if (StringUtils.isEmpty(repaymentType)){
            ToastUtil.showInfo("请选择还款方式！");
            return true;
        }
        repaymentCycle = tvRepaymentCycle.getText().toString().trim();
        if (StringUtils.isEmpty(repaymentCycle)){
            ToastUtil.showInfo("请选择还款周期！");
            return true;
        }
        result = tvAuditResult.getText().toString().trim();
        if (StringUtils.isEmpty(result)){
            switch (status){
                case Action.ORDER_TYPE_YQD:
                    ToastUtil.showInfo("请选择预计终审结果！");
                    break;
                case Action.ORDER_TYPE_YCS:
                    ToastUtil.showInfo("请选择预计审计结果！");
                    break;
                default:
                    break;
            }
            return true;
        }
        needMaterial = tvMaterial.getText().toString().trim();
        if (StringUtils.isEmpty(needMaterial)){
            ToastUtil.showInfo("请选择所需材料！");
            return true;
        }

        bz = etBz.getText().toString().trim();

        if (status.equals(Action.ORDER_TYPE_YCS) ){
            imgId = a();
            if (StringUtils.isEmpty(imgId)){
                ToastUtil.showInfo("请提交审批文件！");
                return true;
            }
        }
        return false;
    }

    public void showDialog() {
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setVisibility(R.id.cancel, View.GONE);
                        holder.setViewVisibility(R.id.cancel_divider,View.GONE);
                        holder.setText(R.id.title, "提交成功！");
                        switch (status){
                            case Action.ORDER_TYPE_YQD:
                                holder.setText(R.id.message, "温馨提示：亲爱的客户经理，提交成功之后，请在规定时间内给客户出具终审结果。");
                                break;
                            case Action.ORDER_TYPE_YCS:
                                holder.setText(R.id.message, "温馨提示：亲爱的客户经理，提交成功之后，请在规定时间内联系用户操作贷款。线下贷款成功之后，可在我的订单确认放贷成功。");
                                break;
                            default:
                                break;
                        }
                        holder.setOnClickListener(R.id.ok, v -> {
                            dialog.dismiss();
                            setResult(24);
                            finish();
                        });
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    public void pickerView(final List<String> list){
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this, (options1, options2, options3, v) ->{
            switch (type){
                case 1:
                    tvRepaymentType.setText(list.get(options1));
                    break;
                case 2:
                    tvRepaymentCycle.setText(list.get(options1));
                    break;
                default:
                    break;
            }

        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        optionsPickerView.setPicker(list);
        optionsPickerView.show();
    }

    private void initCycleOptionsPicker() {// 不联动的多级选项
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            String str = listNumber.get(options1) + listNumber.get(options2) + listNumber.get(options3);
            String newStr = str.replaceFirst("^0*", "");
            String a =  str.replaceFirst("^0*", "");
            tvTerm.setText(newStr);
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        pvNoLinkOptions.setNPicker(listNumber, listNumber, listNumber);
        pvNoLinkOptions.show();
    }

    private void initCycleOneOptionsPicker() {
        OptionsPickerView pvNoLinkOptions = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            String str = numbers.get(options1);
            tvAuditResult.setText(str);

        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        pvNoLinkOptions.setNPicker(numbers, null, null);
        pvNoLinkOptions.show();
    }

    public void showBottomDialog() {
        KBDialog.init()
                .setLayoutId(R.layout.bottom_money)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        final WheelView wv1 =holder.getView(R.id.b_options1);
                        final WheelView wv2 =holder.getView(R.id.b_options2);
                        final WheelView wv3 =holder.getView(R.id.b_options3);
                        final WheelView wv4 =holder.getView(R.id.b_options4);

                        wv1.setAdapter(wheelAdapter);
                        wv2.setAdapter(wheelAdapter);
                        wv3.setAdapter(wheelAdapter);
                        wv4.setAdapter(wheelAdapter);
                        wv1.setCurrentItem(0);
                        wv2.setCurrentItem(0);
                        wv3.setCurrentItem(0);
                        wv4.setCurrentItem(0);

                        holder.setOnClickListener(R.id.b_btnSubmit, view -> {
                            dialog.dismiss();

                            String a = String.valueOf(wv1.getCurrentItem()) + String.valueOf(wv2.getCurrentItem())
                                    + String.valueOf(wv3.getCurrentItem())+ String.valueOf(wv4.getCurrentItem());
                            String newStr = a.replaceFirst("^0*", "");
                            tvQuota.setText(newStr);
                        });
                        holder.setOnClickListener(R.id.b_btnCancel, view -> dialog.dismiss());
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    public void showRate() {
        KBDialog.init()
                .setLayoutId(R.layout.bottom_money)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        final WheelView wv1 =holder.getView(R.id.b_options1);
                        final WheelView wv2 =holder.getView(R.id.b_options2);
                        final WheelView wv3 =holder.getView(R.id.b_options3);
                        final WheelView wv4 =holder.getView(R.id.b_options4);

                        wv1.setAdapter(wheelAdapter);
                        wv2.setAdapter(wheelAdapter);
                        wv3.setAdapter(rateAdapter);
                        wv4.setAdapter(wheelAdapter);
                        wv1.setCurrentItem(0);
                        wv2.setCurrentItem(0);
                        wv3.setCurrentItem(0);
                        wv3.setCyclic(false);
                        wv4.setCurrentItem(0);

                        holder.setOnClickListener(R.id.b_btnSubmit, view -> {
                            dialog.dismiss();

                            String a = "."+ String.valueOf(wv4.getCurrentItem());
                            String b = String.valueOf(wv1.getCurrentItem()) + String.valueOf(wv2.getCurrentItem());
                            String newStr = Kb260Utils.removeFrontZero(b);
                            String c = newStr+a;
                            switch (rateType){
                                case 2:
                                    tvMinInterestRate.setText(c);
                                    break;
                                case 1:
                                    tvRate.setText(c);
                                    break;
                                default:
                                    break;
                            }

                        });
                        holder.setOnClickListener(R.id.b_btnCancel, view -> dialog.dismiss());
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    public void showBottomCheckedDialog(BCheckedAdapter adapter) {
        KBDialog.init()
                .setLayoutId(R.layout.bottom_checked)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(final ViewHolder holder, final BaseKBDialog dialog) {
                        RecyclerView rv = holder.getView(R.id.b_checked_rv);
                        rv.setLayoutManager(new LinearLayoutManager(FirstDraftProgramActivity.this));
                        rv.setAdapter(adapter);

                        List<CheckedData> checkedDatas = adapter.datas;
                        int length = checkedDatas.size();
                        for (int i =0;i<length;i++){
                            checkedDatas.get(i).setChecked(false);
                        }

                        holder.setOnClickListener(R.id.b_checked_btnSubmit, view -> {
                            dialog.dismiss();

                            StringBuilder stringBuilder = new StringBuilder();
                            List<CheckedData> checkedDatas1 = adapter.datas;
                            int length1 = checkedDatas1.size();
                            for (int i = 0; i< length1; i++){
                                if (checkedDatas1.get(i).isChecked()){
                                    if (stringBuilder.length() != 0){
                                        stringBuilder.append(",");
                                    }
                                    stringBuilder.append(checkedDatas1.get(i).getName());
                                }
                            }
                            String a = stringBuilder.toString();
                            tvMaterial.setText(a);
                        });
                        holder.setOnClickListener(R.id.b_checked_btnCancel, view -> dialog.dismiss());
                    }
                })
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    //修改头像
    public void icon() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    Logger.d("修改头像");
                    if (aBoolean) {
                        Matisse.from(FirstDraftProgramActivity.this)
                                .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                                .countable(true)
                                .capture(true)
                                .captureStrategy(
                                        new CaptureStrategy(true, "com.kb260.gxdk.fileprovider"))
                                .maxSelectable(1)
                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(
                                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                .thumbnailScale(0.85f)
                                .imageEngine(new GlideEngine())
                                .forResult(Contact.REQUEST_CODE_CHOOSE);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Contact.REQUEST_CODE_CHOOSE:
                if (resultCode == RESULT_OK) {
                    String a = Matisse.obtainResult(data).get(0).toString();
                    if (a.contains("com.kb260.gxdk.fileprovider")) {
                        String b = a.replace("content://com.kb260.gxdk.fileprovider/my_images/", "");
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/" + b;
                        uploadImg(path);
                    } else {
                        String path = PathUtils.getPath(this, Matisse.obtainResult(data).get(0));
                        uploadImg(path);
                    }
                }
                break;
            default:
                break;
        }

    }

    public void uploadImg(String imgPath) {
        String imgName = "android_gxdk_" + System.currentTimeMillis() + ".jpg";
        new UploadImgUtil(this, imgName, imgPath) {
            @Override
            public void _onSuccess(PutObjectRequest putObjectRequest, PutObjectResult putObjectResult) {
                String imgId = "http://ykd2017.oss-cn-hangzhou.aliyuncs.com/" + putObjectRequest.getObjectKey();
                adapter.setData(currentPosition,new Feedback(1, imgId));
                Logger.d("_onSuccess" + imgId);
            }

            @Override
            public void _onFailure(PutObjectRequest putObjectRequest, ClientException e, ServiceException e1) {
                Logger.d("_onFailure");
            }
        };
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        currentPosition = position;
        switch (datas.get(position).getType()){
            case 0:
                if (datas.size()<=5){
                    adapter.addData(datas.size()-1,new Feedback(2));
                }else {
                    ToastUtil.showInfo("最多添加5张照片");
                }
                break;
            case 1:
                icon();
                break;
            case 2:
                icon();
                break;
            default:
                break;
        }
    }

    private String a(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0;i<datas.size()-1;i++){
            if (datas.get(i).getUrl()!=null){
                if (i != 0){
                    stringBuilder.append(",");
                }
                stringBuilder.append(datas.get(i).getUrl());
            }
        }
        return stringBuilder.toString();
    }
}
