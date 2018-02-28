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
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.CourseSortsAdapter;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.CourseSortsMultiItem;
import com.kb260.bxjy.model.entity.RateLimitData;
import com.kb260.bxjy.model.entity.SubjectLimitData;
import com.kb260.bxjy.model.entity.TeacherLimitData;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/2/1
 * 课程分类
 */
public class CourseSortsActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener, RadioGroup.OnCheckedChangeListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_courseSorts_rv)
    RecyclerView rv;

    List<CourseSortsMultiItem> data;
    CourseSortsAdapter adapter;
    LinearLayout llSort;

    RadioButton rbSubject,rbTeacher,rbSort,rbFree,rbPay;

    int page=1,rows=10,type=0,payType = 0;

    /**
     *开启页面
     */
    public static void start(Context context){
        Intent intent = new Intent(context,CourseSortsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_course_sorts;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        data = new ArrayList<>();
        initRv();
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        toolbar.setTitle(R.string.home_courseSorts_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    private void initData(int type) {
        data.clear();
        switch (type){
            case 0:
                getSubjectLimitData();
                break;
            case 1:
               getTeacherLimitData();
                break;
            case 2:
                getClickRateLimitData();
                break;
            case 3:
                payType = 0;
                getCourseIsPayData();
                break;
            case 4:
                payType = 1;
                getCourseIsPayData();
                break;
            default:
                break;
        }


    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CourseSortsAdapter(data);

        rv.setAdapter(adapter);
        addTop();
        addTopSort();
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);
    }


    private void addTop(){
        View view = getLayoutInflater().inflate(R.layout.top_coursesorts, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);

        RadioGroup rg = view.findViewById(R.id.top_courseSorts_rg);
        rbSubject = view.findViewById(R.id.top_courseSorts_rbSubject);
        rbTeacher = view.findViewById(R.id.top_courseSorts_rbTeacher);
        rbSort = view.findViewById(R.id.top_courseSorts_rbSort);
        rbFree = view.findViewById(R.id.top_courseSorts_rbFree);
        rbPay = view.findViewById(R.id.top_courseSorts_rbPay);
        rg.setOnCheckedChangeListener(this);
        rg.check(R.id.top_courseSorts_rbSubject);
    }

    private void addTopSort(){
        View view = getLayoutInflater().inflate(R.layout.top_coursesorts_sort, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);
        llSort = view.findViewById(R.id.top_courseSortsSort_ll);
        Spinner spinner = view.findViewById(R.id.top_courseSorts_sp);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        type = 0;
                        break;
                    case 1:
                        type = 1;
                        break;
                    default:
                        break;
                }
                getClickRateLimitData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }





    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.top_courseSorts_rbSubject:
                rbSubject.setTextColor(Color.WHITE);
                rbTeacher.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbSort.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbFree.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbPay.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                if (llSort!=null){
                    llSort.setVisibility(View.GONE);
                }
                initData(0);
                break;
            case R.id.top_courseSorts_rbTeacher:
                rbTeacher .setTextColor(Color.WHITE);
                rbSubject.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbSort.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbFree.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbPay.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                if (llSort!=null){
                    llSort.setVisibility(View.GONE);
                }
                initData(1);
                break;
            case R.id.top_courseSorts_rbSort:
                rbSort.setTextColor(Color.WHITE);
                rbTeacher.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbSubject.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbFree.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbPay.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                if (llSort!=null){
                    llSort.setVisibility(View.VISIBLE);
                }
                initData(2);
                break;
            case R.id.top_courseSorts_rbFree:
                rbFree.setTextColor(Color.WHITE);
                rbTeacher.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbSort.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbSubject.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbPay.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                if (llSort!=null){
                    llSort.setVisibility(View.GONE);
                }
                initData(3);
                break;
            case R.id.top_courseSorts_rbPay:
                rbSubject.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbTeacher.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbSort.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbFree.setTextColor(ContextCompat.getColor(this,R.color.text_content_999));
                rbPay.setTextColor(Color.WHITE);
                if (llSort!=null){
                    llSort.setVisibility(View.GONE);
                }
                initData(4);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (data.get(position).getItemType()){
            case CourseSortsMultiItem.SUBJECT_LIST:
                ClassDetailActivity.start(this,"",0);
                break;
            case CourseSortsMultiItem.TEACHER_LIST:
                ClassDetailActivity.start(this,"",0);
                break;
            case CourseSortsMultiItem.SORT_LIST:
                ClassDetailActivity.start(this,"",0);
                break;
            case CourseSortsMultiItem.FREE_LIST:
                ClassDetailActivity.start(this,"",0);
                break;
            case CourseSortsMultiItem.PAY_LIST:
                ClassDetailActivity.start(this,"",0);
                break;
            default:
                break;
        }
    }

    /**
     * 公开课-正在开课（推荐）
     */
    private void getSubjectLimitData() {
        Api.getDefault().subjectLimit(KbApplication.token,page,rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<SubjectLimitData>>(this) {
                    @Override
                    protected void success(List<SubjectLimitData> list) {
                        if (list!=null&& list.size()>0){
                            data.clear();
                            for (SubjectLimitData subjectLimitData : list){
                                data.add(new CourseSortsMultiItem(CourseSortsMultiItem.SUBJECT_TITLE,subjectLimitData.getSubjectName()));
                                for (SubjectLimitData.CousrseGroupBean cousrseGroupBean : subjectLimitData.getCousrseGroup()){
                                    data.add(new CourseSortsMultiItem(CourseSortsMultiItem.SUBJECT_LIST));
                                }
                            }
                            adapter.setNewData(data);
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
     * 课程分类-教师-获取课程组（推荐）
     */
    private void getTeacherLimitData() {
        Api.getDefault().teacherLimit(KbApplication.token,page,rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<TeacherLimitData>>(this) {
                    @Override
                    protected void success(List<TeacherLimitData> list) {
                        if (list!=null&& list.size()>0){
                            data.clear();
                            for (TeacherLimitData teacherLimitData : list){
                                data.add(new CourseSortsMultiItem(CourseSortsMultiItem.TEACHER_TITLE,teacherLimitData.getTeacherName()));
                                /*for (SubjectLimitData.CousrseGroupBean cousrseGroupBean : teacherLimitData.getCourseGroup(){
                                    data.add(new CourseSortsMultiItem(CourseSortsMultiItem.SUBJECT_LIST));
                                }*/
                            }
                            adapter.setNewData(data);
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
     * 公开课-正在开课（推荐）
     */
    private void getClickRateLimitData() {
        Api.getDefault().clickRateLimit(KbApplication.token,page,type,rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<RateLimitData>>(this) {
                    @Override
                    protected void success(List<RateLimitData> list) {
                        if (list!=null&& list.size()>0){
                            data.clear();
                            for (RateLimitData rateLimitData : list){
                                data.add(new CourseSortsMultiItem(CourseSortsMultiItem.SORT_LIST));
                            }
                            adapter.setNewData(data);
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
     * 公开课-正在开课（推荐）
     */
    private void getCourseIsPayData() {
        Api.getDefault().courseIsPay(KbApplication.token,page,payType,rows)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<RateLimitData>>(this) {
                    @Override
                    protected void success(List<RateLimitData> list) {
                        if (list!=null&& list.size()>0){
                            data.clear();
                            for (RateLimitData rateLimitData : list){
                                if (payType == 0){
                                    data.add(new CourseSortsMultiItem(CourseSortsMultiItem.FREE_LIST,rateLimitData.getGroupName()));
                                }else{
                                    data.add(new CourseSortsMultiItem(CourseSortsMultiItem.PAY_LIST,rateLimitData.getGroupName()));
                                }
                            }
                            adapter.setNewData(data);
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

    private void setNoData404(){
        data.clear();
        data.add(new CourseSortsMultiItem(CourseSortsMultiItem.NO_DATA404));
        adapter.setNewData(data);
    }

    private void setNoData0(){
        data.clear();
        data.add(new CourseSortsMultiItem(CourseSortsMultiItem.NO_DATA));
        adapter.setNewData(data);
    }

}
