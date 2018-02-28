package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.IntegralLogAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.Score;
import com.kb260.gxdk.model.Information;
import com.kb260.gxdk.model.IntegralLog;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.DividerItemDecoration;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
/**
 * @author  KB260
 * Created on  2017/11/13
 */
public class IntegralLogActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_rechargeLog_rv)
    RecyclerView rv;

    IntegralLogAdapter adapter;
    List<IntegralLog> data;
    int page=1;

    public static void start(Context context){
        Intent intent = new Intent(context,IntegralLogActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_integral_log;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
        initData();
    }

    private void initRv() {
        data = new ArrayList<>();

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this,R.drawable.divider)));
        adapter = new IntegralLogAdapter(data);

        adapter.setOnLoadMoreListener(this, rv);
        adapter.disableLoadMoreIfNotFullPage();

        rv.setAdapter(adapter);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_rechargeLog_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    private void initData(){
        Api.getDefault().selhistory(KBApplication.token,KBApplication.userid,page)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<IntegralLog>>(this) {
                    @Override
                    protected void success(List<IntegralLog> list) {
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
