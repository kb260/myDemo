package com.panda.sharebike.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;

import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.model.entity.LoginDataBean;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.MainActivity;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.widget.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 实名认证
 */
public class CertificationActivity extends BaseActivity {
    @BindView(R.id.et_name)
    ClearEditText etName;
    @BindView(R.id.et_identity)
    ClearEditText etIdentity;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_certification;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        textChange textChange = new textChange();
        etName.addTextChangedListener(textChange);
        etIdentity.addTextChangedListener(textChange);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
    }

    @OnClick(R.id.btn_register)
    public void onViewClicked() {
        //startActivity(new Intent(this, RechargeActivity.class));
        initGetButton();
    }

    private void initGetButton() {
        if (TextUtils.isEmpty(etName.getText().toString()) || !RegexUtils.isZh(etName.getText().toString())) {
            ToastUtils.showShort("请输入正确的姓名");
            return;
        }
        if (TextUtils.isEmpty(etIdentity.getText().toString()) || !RegexUtils.isIDCard18(etIdentity.getText().toString())) {
            ToastUtils.showShort("请输入正确的身份证号码");
            return;
        }
        getHttpRealName(etName.getText().toString(), etIdentity.getText().toString());


    }

    /**
     * 实名认证
     */
    private void getHttpRealName(String realNameAuth, String idCard) {
        Api.getInstance().getDefault().getRealNameAuth(Config.TOKEN, realNameAuth, idCard)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult>(new SubscriberListener<HttpResult>() {
                    @Override
                    public void onSuccess(HttpResult model) {
                        if (model.isOk()) {
                            getStatus();
                            /*SharedPreferences sp_real = getSharedPreferences("is_real_recharge", MODE_PRIVATE);//判断是否充值过押金.是否实名
                            SharedPreferences.Editor editor_real = sp_real.edit();
                            editor_real.putBoolean("is_real", true);//
                            editor_real.commit();
                            startActivity(RechargeActivity.class);
                            finish();*/
                        } else {
                            if (!EmptyUtils.isEmpty(model.getMsg())) {
                                //     LogUtils.e(model.getMsg());
                                if ("2-不一致".equals(model.getMsg())) {
                                    ToastUtils.showShort("身份证号码与姓名不一致");
                                    return;
                                }
                                if ("身份证号码不正确".equals(model.getMsg())) {
                                    ToastUtils.showShort("身份证号码不正确");
                                    return;
                                }
                                ToastUtils.showShort(model.getMsg());
                            }

                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }, this, true));
    }

    //EditText监听器
    class textChange implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            boolean Sign1 = etName.getText().length() > 1;
            boolean Sign2 = etIdentity.getText().length() > 14;

            if (Sign1 & Sign2) {
                btnRegister.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_btn_enable));
                btnRegister.setEnabled(true);
            } else {
                btnRegister.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_btn_unenable));
                btnRegister.setEnabled(false);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    /**
     * 判断状态,登录态
     */
    private void getStatus() {
        Api.getInstance().getDefault().getState(Config.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<LoginDataBean>>(new SubscriberListener<HttpResult<LoginDataBean>>() {

                    @Override
                    public void onSuccess(HttpResult<LoginDataBean> model) {
                        if (401 == model.getCode()) {
                            startActivity(LoginByPhoneActivity.class);
                            return;
                        }
                        if (model.isOk()) {
                            //实名认证
                            if (!model.getData().isRealnameAuth()) {
                                startActivity(CertificationActivity.class);
                                return;
                            }
                            //充值押金
                            if (!model.getData().isHasPayedDeposit()) {
                                SharedPreferences sp_real = getSharedPreferences("is_real_recharge", MODE_PRIVATE);//判断是否充值过押金.是否实名
                                SharedPreferences.Editor editor_real = sp_real.edit();
                                editor_real.putBoolean("is_real", true);//
                                editor_real.commit();
                                startActivity(RechargeActivity.class);
                                finish();
                            }else {
                                Intent intent = new Intent(CertificationActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                CertificationActivity.this.finish();
                            }
                        }

                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }, this, false));

    }
}