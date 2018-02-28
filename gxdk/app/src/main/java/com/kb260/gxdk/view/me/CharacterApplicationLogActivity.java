package com.kb260.gxdk.view.me;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.CharacterApplicationLogAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.ApplicationLog;
import com.kb260.gxdk.entity.ApplicationLogNew;
import com.kb260.gxdk.entity.CharacterApplicationLog;
import com.kb260.gxdk.utils.DividerItemDecoration;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 *         Created on  2017/11/23
 */
public class CharacterApplicationLogActivity extends BaseActivity {
    @BindView(R.id.a_character_applicationLog_toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_characterApplicationLog_rv)
    RecyclerView rv;

    List<CharacterApplicationLog> data;
    CharacterApplicationLogAdapter adapter;
    ApplicationLog applicationLog;

    public static void start(Activity context) {
        Intent intent = new Intent(context, CharacterApplicationLogActivity.class);
        context.startActivityForResult(intent, 1);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_character_application_log;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
        initData();
    }

    @Override
    public void initToolbar() {
        initThisToolbarHaveBack(toolbar, this);
    }

    //申请记录
    @OnClick(R.id.a_characterApplicationLog_toolbarRight)
    public void applicationLog() {
        CharacterApplicationLogLogActivity.start(this);
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider)));

        data = new ArrayList<>();
        adapter = new CharacterApplicationLogAdapter(data);

        rv.setAdapter(adapter);
    }

    private void addFoote() {
        View view = getLayoutInflater().inflate(R.layout.footer_character, (ViewGroup) rv.getParent(), false);
        adapter.addFooterView(view);
        Button btCancel = view.findViewById(R.id.footer_character_button1);
        Button btChange = view.findViewById(R.id.footer_character_button2);
        btCancel.setOnClickListener(view12 -> {
            cancel();
        });
        btChange.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.putExtra("24", applicationLog);
            setResult(24, intent);
            finish();
        });
    }


    private void initData() {
        Api.getDefault().record(KBApplication.token, KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<ApplicationLogNew>(this) {
                    @Override
                    protected void success(ApplicationLogNew list) {
                        if (list != null && list.getRecord()!= null && list.getRecord().size() > 0) {
                            applicationLog = list.getRecord().get(0);
                            data.clear();
                            if (applicationLog.getType() != null && applicationLog.getStatus() != null) {
                                switch (applicationLog.getApplyflag()) {
                                    case "0"://审核中
                                        data.add(new CharacterApplicationLog("审核状态", "审核中"));
                                        break;
                                    case "1"://已通过
                                        data.add(new CharacterApplicationLog("审核状态", "已通过"));
                                        break;
                                    case "2"://未通过
                                        data.add(new CharacterApplicationLog("审核状态", "未通过"));
                                        break;
                                    default:
                                        break;
                                }
                                switch (applicationLog.getStatus()) {
                                    case "1":
                                        data.add(new CharacterApplicationLog("申请角色", "代理商"));
                                        data.add(new CharacterApplicationLog("姓名", applicationLog.getRealname()));
                                        data.add(new CharacterApplicationLog("手机号", applicationLog.getTelphone()));
                                        data.add(new CharacterApplicationLog("公司名称", applicationLog.getCompany()));
                                        data.add(new CharacterApplicationLog("公司地址", applicationLog.getCompanyaddress()));
                                        break;
                                    case "2":
                                        data.add(new CharacterApplicationLog("申请角色", "客户经理"));
                                        data.add(new CharacterApplicationLog("姓名", applicationLog.getRealname()));
                                        data.add(new CharacterApplicationLog("手机号", applicationLog.getTelphone()));
                                        data.add(new CharacterApplicationLog("所属银行", applicationLog.getBank()));
                                        data.add(new CharacterApplicationLog("支行", applicationLog.getBranchname()));
                                        data.add(new CharacterApplicationLog("银行地址", applicationLog.getBankaddress()));
                                        break;
                                    default:
                                        adapter.setEmptyView(R.layout.empty_view, (ViewGroup) rv.getParent());
                                        break;
                                }
                                if (applicationLog.getApplyflag().equals("0")) {
                                    //addFoote();
                                }
                            }
                            adapter.setNewData(data);
                        } else {
                            adapter.setEmptyView(R.layout.empty_view, (ViewGroup) rv.getParent());
                        }

                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void cancel() {
        Api.getDefault().canleapply(KBApplication.token,applicationLog.getId(),KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("取消申请成功！");
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
