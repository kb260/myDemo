package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.RewordDetailsAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.MyTeam;
import com.kb260.gxdk.entity.MyTeamOne;
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
public class MyTeamActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_myTeam_tvNickname)
    TextView tvNickname;
    @BindView(R.id.a_myTeam_rv)
    RecyclerView rv;
    @BindView(R.id.a_myTeam_tvSelect1)
    TextView tvSelect1;
    @BindView(R.id.a_myTeam_vSelect1)
    View vSelect1;
    @BindView(R.id.a_myTeam_tvSelect2)
    TextView tvSelect2;
    @BindView(R.id.a_myTeam_vSelect2)
    View vSelect2;
    @BindView(R.id.a_myTeam_tvSelect3)
    TextView tvSelect3;
    @BindView(R.id.a_myTeam_vSelect3)
    View vSelect3;

    RewordDetailsAdapter adapter;
    List<MyTeamOne.DataBean> data;
    boolean check1, check2, check3;

    public static void start(Context context) {
        Intent intent = new Intent(context, MyTeamActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_team;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
        initData(1);
        initData(2);
        initData(3);
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
        toolbarTitle.setText(R.string.a_myTeam_toolbar);
        initThisToolbarHaveBack(toolbar, this);
    }

    private void initData(int type) {
        switch (type) {
            case 1:
                Api.getDefault().selmyteamone(KBApplication.token, KBApplication.userid)
                        .compose(RxHelper.handleResult())
                        .subscribe(new RxSubscriber<MyTeamOne>(this) {
                            @Override
                            protected void success(MyTeamOne list) {
                                tvSelect1.setText("您的已\n申请推荐\n" + list.getSize() + "人");
                                tvNickname.setText(list.getNick());
                                if (list.getData() != null && list.getData().size() > 0) {
                                    data = list.getData();
                                    adapter.setNewData(data);
                                } else {
                                    data.clear();
                                    adapter.setNewData(data);
                                    adapter.setEmptyView(R.layout.empty_view, (ViewGroup) rv.getParent());
                                }
                            }

                            @Override
                            protected void error(String msg) {
                                Logger.d(msg);
                                ToastUtil.showError(msg);
                            }
                        });
                break;
            case 2:
                Api.getDefault().selmyteamtwo(KBApplication.token, KBApplication.userid)
                        .compose(RxHelper.handleResult())
                        .subscribe(new RxSubscriber<MyTeamOne>(this) {
                            @Override
                            protected void success(MyTeamOne list) {
                                tvSelect2.setText("您的已\n申请二级推荐\n" + list.getSize() + "人");
                                tvNickname.setText(list.getNick());
                                if (check2) {
                                    if (list.getData() != null && list.getData().size() > 0) {
                                        data = list.getData();
                                        adapter.setNewData(data);
                                    } else {
                                        data.clear();
                                        adapter.setNewData(data);
                                        adapter.setEmptyView(R.layout.empty_view, (ViewGroup) rv.getParent());
                                    }
                                }

                            }

                            @Override
                            protected void error(String msg) {
                                Logger.d(msg);
                                ToastUtil.showError(msg);
                            }
                        });
                break;
            case 3:
                Api.getDefault().selmyteamthree(KBApplication.token, KBApplication.userid)
                        .compose(RxHelper.handleResult())
                        .subscribe(new RxSubscriber<MyTeamOne>(this) {
                            @Override
                            protected void success(MyTeamOne list) {
                                tvSelect3.setText("您的未\n申请推荐\n" + list.getSize() + "人");
                                tvNickname.setText(list.getNick());
                                if (check3) {
                                    if (list.getData() != null && list.getData().size() > 0) {
                                        data = list.getData();
                                        adapter.setNewData(data);
                                    } else {
                                        data.clear();
                                        adapter.setNewData(data);
                                        adapter.setEmptyView(R.layout.empty_view, (ViewGroup) rv.getParent());
                                    }
                                }

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

    @OnClick(R.id.a_myTeam_tvSelect1)
    public void select1() {
        check1 = true;
        tvSelect1.setBackgroundResource(R.drawable.my_team_select1);
        tvSelect1.setTextColor(getResources().getColor(R.color.toolbar_white));
        vSelect1.setBackgroundResource(R.color.a_select_1);

        tvSelect2.setBackgroundResource(R.drawable.my_team_select0);
        tvSelect2.setTextColor(getResources().getColor(R.color.txt999));
        vSelect2.setBackgroundResource(R.color.a_select_0);

        tvSelect3.setBackgroundResource(R.drawable.my_team_select0);
        tvSelect3.setTextColor(getResources().getColor(R.color.txt999));
        vSelect3.setBackgroundResource(R.color.a_select_0);
        initData(1);
    }

    @OnClick(R.id.a_myTeam_tvSelect2)
    public void select2() {
        check2 = true;
        tvSelect1.setBackgroundResource(R.drawable.my_team_select0);
        tvSelect1.setTextColor(getResources().getColor(R.color.txt999));
        vSelect1.setBackgroundResource(R.color.a_select_0);

        tvSelect2.setBackgroundResource(R.drawable.my_team_select1);
        tvSelect2.setTextColor(getResources().getColor(R.color.toolbar_white));
        vSelect2.setBackgroundResource(R.color.a_select_1);

        tvSelect3.setBackgroundResource(R.drawable.my_team_select0);
        tvSelect3.setTextColor(getResources().getColor(R.color.txt999));
        vSelect3.setBackgroundResource(R.color.a_select_0);
        initData(2);
    }

    @OnClick(R.id.a_myTeam_tvSelect3)
    public void select3() {
        check3 = true;
        tvSelect1.setBackgroundResource(R.drawable.my_team_select0);
        tvSelect1.setTextColor(getResources().getColor(R.color.txt999));
        vSelect1.setBackgroundResource(R.color.a_select_0);

        tvSelect2.setBackgroundResource(R.drawable.my_team_select0);
        tvSelect2.setTextColor(getResources().getColor(R.color.txt999));
        vSelect2.setBackgroundResource(R.color.a_select_0);

        tvSelect3.setBackgroundResource(R.drawable.my_team_select2);
        tvSelect3.setTextColor(getResources().getColor(R.color.toolbar_white));
        vSelect3.setBackgroundResource(R.color.a_select_2);
        initData(3);
    }
}
