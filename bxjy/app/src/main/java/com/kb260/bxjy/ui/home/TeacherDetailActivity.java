package com.kb260.bxjy.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.TeacherDetailAdapter;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.TeacherDetailMultiItem;
import com.kb260.bxjy.model.entity.TeacherCourseData;
import com.kb260.bxjy.model.entity.TeacherDetailData;
import com.kb260.bxjy.model.entity.TeacherEvaluatesData;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.Kb260Utils;
import com.kb260.bxjy.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/2/2
 * 教师详情
 */
public class TeacherDetailActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener,
        RadioGroup.OnCheckedChangeListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_teacherDetail_rv)
    RecyclerView rv;

    TeacherDetailAdapter adapter;
    List<TeacherDetailMultiItem> data;
    int id;
    int type,page=1,rows=10;

    RadioButton rbEvaluation,rbOnlineClass;

    /**
     *开启页面
     */
    public static void start(Context context, int id){
        Intent intent = new Intent(context,TeacherDetailActivity.class);
        intent.putExtra(Action.ID,id);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_teacher_detail;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
        data = new ArrayList<>();
        initRv();
    }

    private void initData(int type) {
        switch (type){
            case 0:
                getTeacherEvaluatesData();
                break;
            case 1:
                getTeacherCourseData();
                break;
            default:
                break;
        }


    }

    private void initIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra(Action.ID,-1);
        type = intent.getIntExtra(Action.TYPE,-1);
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar,this);
        toolbar.setTitle(R.string.home_teacherDetail_toolbar);
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TeacherDetailAdapter(data);
        addTop();
        addTopClassDetail();
        rv.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);
    }

    private void addTop(){
        View view = getLayoutInflater().inflate(R.layout.item_teacher_one, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);
        getTeacherDetailData();
    }

    private void addTopClassDetail(){
        View view = getLayoutInflater().inflate(R.layout.top_teacher_detail, (ViewGroup) rv.getParent(), false);
        RadioGroup rg = view.findViewById(R.id.top_teacherDetail_rg);
        rbEvaluation = view.findViewById(R.id.top_teacherDetail_rbEvaluation);
        rbOnlineClass = view.findViewById(R.id.top_teacherDetail_rbOnlineClass);
        rg.setOnCheckedChangeListener(this);
        rg.check(R.id.top_teacherDetail_rbEvaluation);
        adapter.addHeaderView(view);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (data.get(position).getItemType()){
            case TeacherDetailMultiItem.ONLINE_CLASS:
                ClassListActivity.start(this);
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
        switch (i){
            case R.id.top_teacherDetail_rbEvaluation:
                rbEvaluation.setTextColor(Color.WHITE);
                rbOnlineClass.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                initData(0);
                break;
            case R.id.top_teacherDetail_rbOnlineClass:
                rbOnlineClass .setTextColor(Color.WHITE);
                rbEvaluation.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                initData(1);
                break;
            default:
                break;
        }
    }

    /**
     * 教师介绍
     */
    private void getTeacherDetailData() {
        Api.getDefault().teacherDetail(KbApplication.token,id)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<TeacherDetailData>(this) {
                    @Override
                    protected void success(TeacherDetailData list) {
                        if (list!=null){
                            // TODO: 2018/2/27
                        }else {
                            setNoData0();
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                        setNoData404();
                    }
                });
    }

    /**
     * 教师介绍-学生评价
     */
    private void getTeacherEvaluatesData() {
        Api.getDefault().teacherEvaluates(KbApplication.token,id,page,rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<TeacherEvaluatesData>>(this) {
                    @Override
                    protected void success(List<TeacherEvaluatesData> list) {
                        if (list!=null && list.size()>0){
                            data.clear();
                            for (TeacherEvaluatesData evaluatesData:list){
                                data.add(new TeacherDetailMultiItem(TeacherDetailMultiItem.EVALUATION
                                ,evaluatesData.getUserEvaluate(),evaluatesData.getUserName(),evaluatesData.getHeadImg()));
                            }
                            adapter.setNewData(data);
                        }else {
                            data.clear();
                            data.add(new TeacherDetailMultiItem(TeacherDetailMultiItem.NO_DATA));
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                        data.clear();
                        data.add(new TeacherDetailMultiItem(TeacherDetailMultiItem.NO_DATA404));
                        adapter.setNewData(data);
                    }
                });
    }


    /**
     * 教师介绍-在售课程
     */
    private void getTeacherCourseData() {
        Api.getDefault().teacherCourse(KbApplication.token,id,page,rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<TeacherCourseData>>(this) {
                    @Override
                    protected void success(List<TeacherCourseData> list) {
                        if (list!=null){
                            data.clear();
                            for (TeacherCourseData courseData:list){
                                data.add(new TeacherDetailMultiItem(TeacherDetailMultiItem.EVALUATION
                                        ,courseData.getGroupName(), Kb260Utils.getTime(courseData.getOpeningTime())
                                        +"-"+Kb260Utils.getTime(courseData.getCloseingTime())
                                ,"￥"+courseData.getPrice(),courseData.getPurchaseNum()+"课时"));
                            }
                            adapter.setNewData(data);
                        }else {
                            data.clear();
                            data.add(new TeacherDetailMultiItem(TeacherDetailMultiItem.NO_DATA));
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                        data.clear();
                        data.add(new TeacherDetailMultiItem(TeacherDetailMultiItem.NO_DATA404));
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
