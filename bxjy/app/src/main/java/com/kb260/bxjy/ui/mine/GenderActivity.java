package com.kb260.bxjy.ui.mine;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.kb260.bxjy.R;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 *         Created on  2018/2/5
 */
public class GenderActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.a_changeGender_toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_changeGender_rbBoy)
    RadioButton rbBoy;
    @BindView(R.id.a_changeGender_rbGirl)
    RadioButton rbGirl;

    boolean gender = true;
    /**
     * 开启页面
     */
    public static void start(Activity context) {
        Intent intent = new Intent(context, GenderActivity.class);
        context.startActivityForResult(intent, Action.START_ACTIVITY_CHANGE_GENDER);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_gender;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        rbBoy.setOnCheckedChangeListener(this);
        rbGirl.setOnCheckedChangeListener(this);
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.changeGender_toolbar);
    }

    //确定
    @OnClick(R.id.a_changeGender_toolbarRight)
    public void sure() {
        String sex = "0";
        if (!gender){
            sex = "1";
        }
        Api.getDefault().resetSex(KbApplication.token,sex)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showShout("修改成功");
                        Intent intent = new Intent();
                        intent.putExtra(Action.START_ACTIVITY_CHANGE_GENDER_KEY, gender);
                        setResult(Action.START_ACTIVITY_CHANGE_GENDER, intent);
                        finish();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });

    }

    @OnClick(R.id.a_changeGender_llBoy)
    public void llBoy() {
        rbBoy.setChecked(!rbBoy.isSelected());
    }

    @OnClick(R.id.a_changeGender_llGirl)
    public void llGirl() {
        rbGirl.setChecked(!rbGirl.isSelected());
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()){
            case R.id.a_changeGender_rbBoy:
                rbGirl.setChecked(!b);
                gender = b;
                break;
            case R.id.a_changeGender_rbGirl:
                rbBoy.setChecked(!b);
                gender = !b;
                break;
            default:
                break;
        }
    }
}
