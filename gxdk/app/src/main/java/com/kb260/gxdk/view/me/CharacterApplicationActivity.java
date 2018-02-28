package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.ApplicationLog;
import com.kb260.gxdk.entity.ApplicationLogNew;
import com.kb260.gxdk.entity.CharacterApplicationData;
import com.kb260.gxdk.entity.CharacterApplicationLog;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.GetJsonDataUtil;
import com.kb260.gxdk.utils.RegexUtils;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.account.LoginActivity;
import com.kb260.gxdk.view.base.BaseActivity;
import com.kb260.gxdk.view.main.MainActivity;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 *         Created on  2017/11/6
 */
public class CharacterApplicationActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    @BindView(R.id.a_character_application_toolbar)
    Toolbar toolbar;

    @BindView(R.id.a_characterApplication_sp)
    Spinner spinner;
    @BindView(R.id.a_characterApplication_llCompanyName)
    LinearLayout llCompanyName;
    @BindView(R.id.a_characterApplication_llCompanyAddress)
    LinearLayout llCompanyAddress;
    @BindView(R.id.a_characterApplication_llBink)
    LinearLayout llBink;
    @BindView(R.id.a_characterApplication_llBranch)
    LinearLayout llBranch;
    @BindView(R.id.a_characterApplication_llBankAddress)
    LinearLayout llBankAddress;

    @BindView(R.id.a_characterApplication_etName)
    EditText etName;
    @BindView(R.id.a_characterApplication_tvPhone)
    TextView tvPhone;
    @BindView(R.id.a_characterApplication_etCompanyName)
    EditText etCompanyName;
    @BindView(R.id.a_characterApplication_etCompanyAddress)
    EditText etCompanyAddress;
    @BindView(R.id.a_characterApplication_tvBank)
    TextView tvBank;
    @BindView(R.id.a_characterApplication_etBranch)
    EditText etBranch;
    @BindView(R.id.a_characterApplication_etBankAddress)
    EditText etBankAddress;

    @BindView(R.id.a_characterApplication_btApplication)
    Button btApplication;

    List<String> list;
    String realname, telphone, company, companyaddress, branchname, bankaddress, bank, status = "1";
    int type, id;
    boolean isChange;

    public static void start(Context context) {
        Intent intent = new Intent(context, CharacterApplicationActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_character_application;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        spinner.setOnItemSelectedListener(this);
        tvPhone.setText(KBApplication.currentPhone);
        list = new ArrayList<>();
        initData();
    }

    @Override
    public void initToolbar() {
        initThisToolbarHaveBack(toolbar, this);
    }

    //按揭银行
    @OnClick(R.id.a_characterApplication_llBink)
    public void selectBank() {
        list.clear();
        list = new GetJsonDataUtil().initJsonBank(this);
        pickerView(list);
    }

    //我的角色
    @OnClick(R.id.a_characterApplication_toolbarRight)
    public void applicationLog() {
        CharacterApplicationLogActivity.start(this);
    }

    //申请
    @OnClick(R.id.a_characterApplication_btApplication)
    public void application() {
        if (getInput()) {
            return;
        }
        if (btApplication.getText().equals("认证")) {
            CharacterApplicationData data = new CharacterApplicationData(realname, telphone, company, companyaddress,
                    branchname, bankaddress, bank, status, id, type, isChange);
            CharacterApplicationNextActivity.start(this, data);
        } else {
            showPhone();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        type = i;
        switch (type) {
            case 0:
                status = "1";
                llCompanyName.setVisibility(View.VISIBLE);
                llCompanyAddress.setVisibility(View.VISIBLE);
                llBink.setVisibility(View.GONE);
                llBranch.setVisibility(View.GONE);
                llBankAddress.setVisibility(View.GONE);
                break;
            case 1:
                status = "2";
                llCompanyName.setVisibility(View.GONE);
                llCompanyAddress.setVisibility(View.GONE);
                llBink.setVisibility(View.VISIBLE);
                llBranch.setVisibility(View.VISIBLE);
                llBankAddress.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private boolean getInput() {
        switch (KBApplication.getLoginType()) {
            case Action.LOGIN_TYPE_BANK:
                ToastUtil.showInfo("您已是最高权限，无法再次申请！");
                return true;
            case Action.LOGIN_TYPE_MANAGE:
                if (type == 0) {
                    ToastUtil.showInfo("您已是代理商，无法再次申请！");
                    return true;
                }
            default:
                break;
        }


        realname = etName.getText().toString().trim();
        if (StringUtils.isEmpty(realname)) {
            ToastUtil.showInfo("请输入姓名！");
            return true;
        }

        telphone = tvPhone.getText().toString();
        if (StringUtils.isEmpty(telphone)) {
            ToastUtil.showInfo("请输入手机号码！");
            return true;
        }
        if (RegexUtils.isMobileSimple(telphone)) {
            ToastUtil.showInfo("请输入正确的手机号码！");
            return true;
        }

        switch (type) {
            case 0:
                company = etCompanyName.getText().toString().trim();
                if (StringUtils.isEmpty(company)) {
                    ToastUtil.showInfo("请输入公司名称！");
                    return true;
                }

                companyaddress = etCompanyAddress.getText().toString().trim();
                if (StringUtils.isEmpty(companyaddress)) {
                    ToastUtil.showInfo("请输入公司地址！");
                    return true;
                }
                break;
            case 1:
                bank = tvBank.getText().toString().trim();
                if (StringUtils.isEmpty(bank)) {
                    ToastUtil.showInfo("请选择银行名称！");
                    return true;
                }

                branchname = etBranch.getText().toString().trim();
                if (StringUtils.isEmpty(branchname)) {
                    ToastUtil.showInfo("请输入支行名称！");
                    return true;
                }

                bankaddress = etBankAddress.getText().toString().trim();
                if (StringUtils.isEmpty(bankaddress)) {
                    ToastUtil.showInfo("请输入银行地址！");
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 24) {
            ApplicationLog applicationLog = (ApplicationLog) data.getSerializableExtra("24");
            isChange = true;
            id = applicationLog.getId();
            status = applicationLog.getStatus();
            switch (applicationLog.getStatus()) {
                case "1":
                    spinner.setSelection(0);
                    etName.setText(applicationLog.getRealname());
                    etCompanyName.setText(applicationLog.getCompany());
                    etCompanyAddress.setText(applicationLog.getCompanyaddress());
                    break;
                case "2":
                    spinner.setSelection(1);
                    etName.setText(applicationLog.getRealname());
                    tvBank.setText(applicationLog.getBank());
                    etBranch.setText(applicationLog.getBranchname());
                    etBankAddress.setText(applicationLog.getBankaddress());
                    break;
                default:
                    break;
            }
        } else if (resultCode == 25) {
            finish();
        }
    }

    private void initData() {
        Api.getDefault().record(KBApplication.token, KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<ApplicationLogNew>(this) {
                    @Override
                    protected void success(ApplicationLogNew list) {
                        if (list != null && list.getRecord()!= null && list.getRecord().size() > 0) {
                            ApplicationLog applicationLog = list.getRecord().get(0);
                            if (list.getSign().equals("1") && !list.getRecord().get(0).getApplyflag().equals("1")) {
                                btApplication.setText("认证");
                                switch (applicationLog.getStatus()) {
                                    case "1":
                                        spinner.setSelection(0);
                                        etName.setText(applicationLog.getRealname());
                                        etCompanyName.setText(applicationLog.getCompany());
                                        etCompanyAddress.setText(applicationLog.getCompanyaddress());
                                        break;
                                    case "2":
                                        spinner.setSelection(1);
                                        etName.setText(applicationLog.getRealname());
                                        tvBank.setText(applicationLog.getBank());
                                        etBranch.setText(applicationLog.getBranchname());
                                        etBankAddress.setText(applicationLog.getBankaddress());
                                        break;
                                    default:
                                        break;
                                }
                            } else if (!list.getSign().equals("1") && list.getRecord().get(0).getApplyflag().equals("1")) {
                                switch (applicationLog.getStatus()) {
                                    case "1":
                                        spinner.setSelection(1);
                                        spinner.setEnabled(false);
                                        break;
                                    case "2":
                                        btApplication.setVisibility(View.GONE);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void application1() {
        switch (type) {
            case 0:
                Api.getDefault().makeagent(KBApplication.token, KBApplication.userid, company,
                        companyaddress, realname, telphone, null, null, null,
                        null, null, null)
                        .compose(RxHelper.handleResult())
                        .subscribe(new RxSubscriber<String>(this) {
                            @Override
                            protected void success(String list) {
                                ToastUtil.showSuccess("信息提交成功！");
                                finish();
                            }

                            @Override
                            protected void error(String msg) {
                                Logger.d(msg);
                                ToastUtil.showError(msg);
                            }
                        });
                break;
            case 1:
                Api.getDefault().makebank(KBApplication.token, KBApplication.userid, branchname,
                        bankaddress, bank, realname, telphone
                        , null, null, null,
                        null, null, null)
                        .compose(RxHelper.handleResult())
                        .subscribe(new RxSubscriber<String>(this) {
                            @Override
                            protected void success(String list) {
                                ToastUtil.showSuccess("信息提交成功！");
                                finish();
                            }

                            @Override
                            protected void error(String msg) {
                                Logger.d(msg);
                                ToastUtil.showError(msg);
                            }
                        });
                break;
            default:
                break;
        }
    }

    private void showPhone() {
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setVisibility(R.id.message, View.GONE);
                        holder.setText(R.id.title, "是否认证！");
                        holder.setText(R.id.ok, "确认");
                        holder.setOnClickListener(R.id.ok, v -> {
                            CharacterApplicationData data = new CharacterApplicationData(realname, telphone, company, companyaddress,
                                    branchname, bankaddress, bank, status, id, type, isChange);
                            CharacterApplicationNextActivity.start(CharacterApplicationActivity.this, data);
                        });
                        holder.setOnClickListener(R.id.cancel, v -> {
                            dialog.dismiss();
                            application1();
                        });
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getSupportFragmentManager());
    }

    public void pickerView(final List<String> list) {
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            tvBank.setText(list.get(options1));
        }).setCancelColor(Color.BLACK)
                .setSubmitColor(Color.RED)
                .setContentTextSize(20)
                .setLineSpacingMultiplier(1.5f)
                .setTitleText("请选择").build();
        optionsPickerView.setPicker(list);
        optionsPickerView.show();
    }
}
