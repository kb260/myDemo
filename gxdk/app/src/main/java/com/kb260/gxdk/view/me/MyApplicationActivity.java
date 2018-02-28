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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.MyApplicationAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.MyApplication;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.DividerItemDecoration;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2017/11/10
 */
public class MyApplicationActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.a_myApplication_toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_myApplication_toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_myApplication_rv)
    RecyclerView rv;

    MyApplicationAdapter adapter;
    List<MyApplication> data;
    int page=1;

    public static void start(Context context){
        Intent intent = new Intent(context,MyApplicationActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_application;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    //二次申请
    @OnClick(R.id.a_myApplication_toolbarRight)
    public void credit(){
        MyCreditActivity.start(this);
    }

    private void initData() {
        Api.getDefault().selroomloan(KBApplication.token,KBApplication.userid,page)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<MyApplication>>(this) {
                    @Override
                    protected void success(List<MyApplication> list) {
                        if (list != null && list.size()>0){
                            if (page >1){
                                adapter.addData(list);
                            }else {
                                data = list;
                                adapter.setNewData(data);
                            }
                        }else {
                            if (page ==1){
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
                        loadOk(-1);
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        loadOk(1);
                    }
                });
    }

    private void loadOk(int type){
        if (adapter != null && adapter.isLoading()){
            switch (type){
                case 1:
                    adapter.loadMoreComplete();
                    break;
                case -1:
                    adapter.loadMoreFail();
                    break;
                default:
                    break;
            }
        }
    }

    private void initRv() {
        data = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyApplicationAdapter(data);
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this,R.drawable.divider20)));

        adapter.setOnLoadMoreListener(this, rv);
        adapter.disableLoadMoreIfNotFullPage();

        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_meDetail_myApplication_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MyApplicationDetailNewActivity.start(this,data.get(position).getId());
    }

    @Override
    public void onLoadMoreRequested() {
        if (adapter.getData().size() < Contact.PAGE * page) {
            adapter.loadMoreEnd(false);
        } else {
            page++;
            initData();
        }
    }
}
