package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.lib.WheelView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.RewordDetailsAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.MyTeam;
import com.kb260.gxdk.entity.MyTeamOne;
import com.kb260.gxdk.model.RewordDetails;
import com.kb260.gxdk.utils.Action;
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
 *         Created on  2017/11/13
 */
public class RewordDetailsActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_rewordDetail_rv)
    RecyclerView rv;
    @BindView(R.id.my_team_jljf)
    TextView tvJljf;
    @BindView(R.id.my_team_myTeamSize)
    TextView myTeamSize;

    RewordDetailsAdapter adapter;
    List<MyTeamOne.DataBean> data;

    public static void start(Context context, String jljf) {
        Intent intent = new Intent(context, RewordDetailsActivity.class);
        intent.putExtra(Action.INTEGRAL, jljf);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_reword_details;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
        initRv();
        initData();
    }

    private void initIntent() {
        String a = getIntent().getStringExtra(Action.INTEGRAL);
        tvJljf.setText(a);
    }

    private void initRv() {
        data = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider)));
        adapter = new RewordDetailsAdapter(data);
        rv.setAdapter(adapter);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_integral_integral_log);
        initThisToolbarHaveBack(toolbar, this);
    }

    //查看
    @OnClick(R.id.a_rewordDetail_see)
    public void see() {
        MyTeamActivity.start(this);
    }

    private void initData() {
        Api.getDefault().selmyteam(KBApplication.token, KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<MyTeamOne.DataBean>>(this) {
                    @Override
                    protected void success(List<MyTeamOne.DataBean> list) {
                        if (list != null && list.size() > 0) {
                            myTeamSize.setText(String.valueOf(list.size()));
                            data = list;
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
}
