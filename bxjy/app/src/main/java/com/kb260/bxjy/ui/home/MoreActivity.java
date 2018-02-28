package com.kb260.bxjy.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.FilterAdapter;
import com.kb260.bxjy.adapter.MoreAdapter;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.MoreMultiItem;
import com.kb260.bxjy.model.entity.ClassData;
import com.kb260.bxjy.model.entity.TeacherLimitData;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.KeyBordUtil;
import com.kb260.bxjy.utils.ToastUtil;
import com.kb260.bxjy.weight.CustomPopWindow;
import com.kb260.bxjy.weight.dialog.NiceDialog;
import com.kb260.bxjy.weight.dialog.ViewConvertListener;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 *         Created on  2018/2/1
 *         查看更多
 */
public class MoreActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.toolbarWithSearch)
    Toolbar toolbar;
    @BindView(R.id.toolbarEt)
    EditText toolbarEt;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_more_rv)
    RecyclerView rv;

    MoreAdapter adapter;
    List<MoreMultiItem> data;

    String title, id;
    int type, page = 1, rows = 5;

    /**
     * 开启页面
     */
    public static void start(Context context, String title, String id, int type) {
        Intent intent = new Intent(context, MoreActivity.class);
        intent.putExtra(Action.TITLE, title);
        intent.putExtra(Action.ID, id);
        intent.putExtra(Action.TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_more;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
        initRv();
        initData();
    }

    private void initData() {
        switch (type) {
            case Action.MORE_TYPE_HOME_LIVE:
                getLiveData();
                break;
            case Action.MORE_TYPE_HOME_HIT:
                getBroadcastData();
                break;
            case Action.MORE_TYPE_HOME_TEACHER:
                getTeacherData();
                break;
            case MoreMultiItem.PUBLIC_CLASSED:
                getAlreadyCourseGroupMoreData();
                break;
            case MoreMultiItem.PUBLIC_CLASSING:
                getUnderwayCourseGroupMoreData();
                break;
            case MoreMultiItem.PUBLIC_CLASS_SOON:
                getUpcomingCourseMoreData();
                break;
            default:
                break;
        }


    }

    private void initIntent() {
        Intent intent = getIntent();
        title = intent.getStringExtra(Action.TITLE);
        id = intent.getStringExtra(Action.ID);
        type = intent.getIntExtra(Action.TYPE, -1);
        toolbarTitle.setText(title);
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyBordUtil.hitKeyBord(toolbarEt, MoreActivity.this);
                finish();
            }
        });
    }

    private void initRv() {
        data = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MoreAdapter(data);

        rv.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (data.get(position).getItemType()) {
            case MoreMultiItem.HOME_LIVING:
                ClassDetailActivity.start(this, "", 0);
                break;
            case MoreMultiItem.HOME_HIT:
                ClassDetailActivity.start(this, "", 0);
                break;
            case MoreMultiItem.HOME_TEACHER:
                TeacherDetailActivity.start(this, 0);
                break;
            case MoreMultiItem.PUBLIC_CLASSED:
                ClassDetailActivity.start(this, "", 0);
                break;
            case MoreMultiItem.PUBLIC_CLASSING:
                ClassDetailActivity.start(this, "", 0);
                break;
            case MoreMultiItem.PUBLIC_CLASS_SOON:
                ClassDetailActivity.start(this, "", 0);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @OnClick(R.id.toolbarSearch)
    public void search() {
        toolbarTitle.setVisibility(View.GONE);
        toolbarEt.setVisibility(View.VISIBLE);
        toolbarEt.requestFocus();
        toolbarEt.setFocusable(true);
        KeyBordUtil.showKeyBord(toolbarEt, this);
    }

    @OnClick(R.id.toolbarFilter)
    public void filter() {
        showPopListView();
    }


    public void bottom() {
        NiceDialog.init()
                .setLayoutId(R.layout.filter_top)
                .setConvertListener((ViewConvertListener) (holder, dialog) -> {

                })
                .setOutCancel(true)
                .setShowTop(true)
                .show(getSupportFragmentManager());
    }

    private void showPopListView() {
        //创建并显示popWindow
        initFilter();
        new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                //.enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()
                .showAsDropDown(toolbar, 0, 0);
    }

    View contentView;

    private void initFilter() {
        if (contentView == null) {
            contentView = LayoutInflater.from(this).inflate(R.layout.filter_top, null);
            RecyclerView filterRv = contentView.findViewById(R.id.pop_filter_rv);
            filterRv.setLayoutManager(new GridLayoutManager(this, 4));
            List<String> data = new ArrayList<>();
            data.add("全部");
            data.add("科目");
            data.add("公开");
            data.add("内部");
            data.add("全部");
            data.add("科目");
            data.add("公开");
            data.add("内部");
            data.add("全部");
            data.add("科目");
            data.add("公开");
            data.add("内部");
            data.add("全部");
            data.add("科目");
            data.add("公开");
            data.add("内部");
            FilterAdapter adapter = new FilterAdapter(data);
            filterRv.setAdapter(adapter);
        }
    }

    /**
     * 直播推荐
     */
    private void getLiveData() {
        Api.getDefault().liveStream(KbApplication.token, page, rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<ClassData>>(this) {
                    @Override
                    protected void success(List<ClassData> list) {
                        if (list != null && list.size() > 0) {
                            for (ClassData data1 : list) {
                                data.add(new MoreMultiItem(MoreMultiItem.HOME_LIVING));
                            }
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

    /**
     * 首页-热播榜(更多)
     */
    private void getBroadcastData() {
        Api.getDefault().topBroadcast(KbApplication.token, page, rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<ClassData>>(this) {
                    @Override
                    protected void success(List<ClassData> list) {
                        if (list != null && list.size() > 0) {
                            for (ClassData data1 : list) {
                                data.add(new MoreMultiItem(MoreMultiItem.HOME_HIT));
                            }
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

    /**
     * 首页-教师榜(更多)
     */
    private void getTeacherData() {
        Api.getDefault().topTeacher(KbApplication.token, page, rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<TeacherLimitData>>(this) {
                    @Override
                    protected void success(List<TeacherLimitData> list) {
                        if (list != null && list.size() > 0) {
                            for (TeacherLimitData data1 : list) {
                                data.add(new MoreMultiItem(MoreMultiItem.HOME_TEACHER));
                            }
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

    /**
     * 公开课-即将开课（更多）
     */
    private void getUpcomingCourseMoreData() {
        Api.getDefault().upcomingCourseMore(KbApplication.token, page, rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<ClassData>>(this) {
                    @Override
                    protected void success(List<ClassData> list) {
                        if (list != null && list.size() > 0) {
                            for (ClassData classData : list) {
                                data.add(new MoreMultiItem(MoreMultiItem.PUBLIC_CLASS_SOON));
                            }
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

    /**
     * 公开课-正在开课（更多）
     */
    private void getUnderwayCourseGroupMoreData() {
        Api.getDefault().underwayCourseGroupMore(KbApplication.token, page, rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<ClassData>>(this) {
                    @Override
                    protected void success(List<ClassData> list) {
                        if (list != null && list.size() > 0) {
                            for (ClassData classData : list) {
                                data.add(new MoreMultiItem(MoreMultiItem.PUBLIC_CLASSING));
                            }
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

    /**
     * 公开课-即将开课（更多）
     */
    private void getAlreadyCourseGroupMoreData() {
        Api.getDefault().alreadyCourseGroupMore(KbApplication.token, page, rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<ClassData>>(this) {
                    @Override
                    protected void success(List<ClassData> list) {
                        if (list != null && list.size() > 0) {
                            for (ClassData classData : list) {
                                data.add(new MoreMultiItem(MoreMultiItem.PUBLIC_CLASSED));
                            }
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

    /**
     * 公开课-即将开课（更多）
     */
    private void getVideoSearchData() {
        Api.getDefault().videoSearch(KbApplication.token)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<ClassData>>(this) {
                    @Override
                    protected void success(List<ClassData> list) {
                        if (list != null && list.size() > 0) {
                            for (ClassData classData : list) {
                                data.add(new MoreMultiItem(MoreMultiItem.PUBLIC_CLASSED));
                            }
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

}
