package com.kb260.bxjy.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.ClassDetailAdapter;
import com.kb260.bxjy.adapter.MoreAdapter;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.ClassDetailMultiItem;
import com.kb260.bxjy.model.CourseSortsMultiItem;
import com.kb260.bxjy.model.TeacherDetailMultiItem;
import com.kb260.bxjy.model.entity.ClassData;
import com.kb260.bxjy.model.entity.ClassDetailData;
import com.kb260.bxjy.model.entity.CourseScheduleData;
import com.kb260.bxjy.model.entity.TeacherCourseData;
import com.kb260.bxjy.model.entity.TeacherDetailData;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.Kb260Utils;
import com.kb260.bxjy.utils.ToastUtil;
import com.kb260.bxjy.weight.dialog.ConfirmDialog;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 *         Created on  2018/2/2
 *         课程详情
 */
public class ClassDetailActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.OnItemClickListener, RadioGroup.OnCheckedChangeListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.toolbarCollect)
    Toolbar toolbar;
    @BindView(R.id.toolbarCollect_cb)
    CheckBox toolbarCb;
    @BindView(R.id.a_classDetail_rv)
    RecyclerView rv;
    @BindView(R.id.bottom_classDetail_tvBuy)
    TextView tvBuy;
    @BindView(R.id.a_classDetail_bottom)
    LinearLayout llBottom;

    ClassDetailAdapter adapter;
    List<ClassDetailMultiItem> data;
    String id;
    int type,page,rows;

    RadioButton rbIntroduction, rbClassSchedule, rbTeacher;

    /**
     * 开启页面
     */
    public static void start(Context context, String id, int type) {
        Intent intent = new Intent(context, ClassDetailActivity.class);
        intent.putExtra(Action.ID, id);
        intent.putExtra(Action.TYPE, type);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_class_detail;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
        data = new ArrayList<>();
        initRv();
    }

    private void initData(int type) {
        switch (type) {
            case 0:
                data.clear();
                data.add(new ClassDetailMultiItem(ClassDetailMultiItem.INTRODUCTION));
                adapter.setNewData(data);
                break;
            case 1:
                getCourseScheduleData();
                break;
            case 2:
                getTeacherIntroduceData();
                break;
            default:
                break;
        }


    }

    private void initIntent() {
        Intent intent = getIntent();
        id = intent.getStringExtra(Action.ID);
        type = intent.getIntExtra(Action.TYPE, -1);
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.home_classDetail_toolbar);
        toolbarCb.setOnCheckedChangeListener(this);
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ClassDetailAdapter(data);

        getGroupDetailData();
        //addBottom();
        rv.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);
    }

    private void addTop() {
        View view = getLayoutInflater().inflate(R.layout.item_class_noavatar, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);
    }

    private void addTopClassDetail() {
        View view = getLayoutInflater().inflate(R.layout.top_class_detail, (ViewGroup) rv.getParent(), false);
        RadioGroup rg = view.findViewById(R.id.top_classDetail_rg);
        rbIntroduction = view.findViewById(R.id.top_classDetail_rbIntroduction);
        rbClassSchedule = view.findViewById(R.id.top_classDetail_rbClassSchedule);
        rbTeacher = view.findViewById(R.id.top_classDetail_rbTeacher);
        rg.setOnCheckedChangeListener(this);
        rg.check(R.id.top_classDetail_rbIntroduction);
        adapter.addHeaderView(view);
    }

    private void addBottom() {
        View view = getLayoutInflater().inflate(R.layout.item_class_bottom, (ViewGroup) rv.getParent(), false);
        adapter.addFooterView(view);
        TextView tvBuy = view.findViewById(R.id.bottom_classDetail_tvBuy);
        tvBuy.setOnClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (data.get(position).getItemType()) {
            case ClassDetailMultiItem.TEACHER:
                TeacherDetailActivity.start(this, 0);
                break;
            case ClassDetailMultiItem.CLASS_SCHEDULE:
                TeacherClassActivity.start(this);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.top_classDetail_rbIntroduction:
                rbIntroduction.setTextColor(Color.WHITE);
                rbTeacher.setTextColor(ContextCompat.getColor(this, R.color.text_content_999));
                rbClassSchedule.setTextColor(ContextCompat.getColor(this, R.color.text_content_999));
                initData(0);
                break;
            case R.id.top_classDetail_rbClassSchedule:
                rbClassSchedule.setTextColor(Color.WHITE);
                rbTeacher.setTextColor(ContextCompat.getColor(this, R.color.text_content_999));
                rbIntroduction.setTextColor(ContextCompat.getColor(this, R.color.text_content_999));
                initData(1);
                break;
            case R.id.top_classDetail_rbTeacher:
                rbTeacher.setTextColor(Color.WHITE);
                rbIntroduction.setTextColor(ContextCompat.getColor(this, R.color.text_content_999));
                rbClassSchedule.setTextColor(ContextCompat.getColor(this, R.color.text_content_999));
                initData(2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_classDetail_tvBuy:
                //购买
                showDialog();
                break;
            default:
                break;
        }
    }

    @OnClick(R.id.bottom_classDetail_tvBuy)
    public void buy() {
        //购买
        showDialog();
    }

    public void showDialog() {
        ConfirmDialog.newInstance(Action.DIALOG_TYPE_BUY_CLASS)
                .setMargin(50)
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            ToastUtil.showShout("收藏成功");
        } else {
            ToastUtil.showShout("取消收藏成功");
        }
    }

    /**
     * 课程组详情
     */
    private void getGroupDetailData() {
        Api.getDefault().groupDetail(KbApplication.token,id)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<ClassDetailData>(this) {
                    @Override
                    protected void success(ClassDetailData list) {
                        if (list!=null){
                            // TODO: 2018/2/27
                            addTop();
                            addTopClassDetail();
                            llBottom.setVisibility(View.VISIBLE);
                            toolbarCb.setVisibility(View.VISIBLE);
                        }else {
                            setNoData0();
                            llBottom.setVisibility(View.GONE);
                            toolbarCb.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                        setNoData404();
                        llBottom.setVisibility(View.GONE);
                        toolbarCb.setVisibility(View.GONE);
                    }
                });
    }

    /**
     * 课程表
     */
    private void getCourseScheduleData() {
        Api.getDefault().courseSchedule(KbApplication.token,id,page,rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<CourseScheduleData>>(this) {
                    @Override
                    protected void success(List<CourseScheduleData> list) {
                        if (list!=null){
                            data.clear();
                            for (CourseScheduleData courseScheduleData:list){
                                data.add(new ClassDetailMultiItem(ClassDetailMultiItem.CLASS_SCHEDULE));
                            }
                            adapter.setNewData(data);
                        }else {
                            data.clear();
                            data.add(new ClassDetailMultiItem(ClassDetailMultiItem.NO_DATA));
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                        data.clear();
                        data.add(new ClassDetailMultiItem(ClassDetailMultiItem.NO_DATA404));
                        adapter.setNewData(data);
                    }
                });
    }

    /**
     * 老师介绍
     */
    private void getTeacherIntroduceData() {
        Api.getDefault().teacherIntroduce(KbApplication.token)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<TeacherDetailData>>(this) {
                    @Override
                    protected void success(List<TeacherDetailData> list) {
                        if (list!=null){
                            data.clear();
                            for (TeacherDetailData teacherDetailData:list){
                                data.add(new ClassDetailMultiItem(ClassDetailMultiItem.TEACHER));
                            }
                            adapter.setNewData(data);
                        }else {
                            data.clear();
                            data.add(new ClassDetailMultiItem(ClassDetailMultiItem.NO_DATA));
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                        data.clear();
                        data.add(new ClassDetailMultiItem(ClassDetailMultiItem.NO_DATA404));
                        adapter.setNewData(data);
                    }
                });
    }


    private void setNoData404(){
        data.clear();
        adapter.setNewData(data);
        adapter.setEmptyView(R.layout.empty_view_404, (ViewGroup) rv.getParent());
    }

    private void setNoData0(){
        data.clear();
        adapter.setNewData(data);
        adapter.setEmptyView(R.layout.empty_view_0, (ViewGroup) rv.getParent());
    }
}
