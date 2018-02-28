package com.kb260.gxdk.view.gradmonad;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.GrabMonadAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.EventData;
import com.kb260.gxdk.model.Order;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/11/13
 */

public class ConfirmedFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener,
        BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.f_grabMonad_order_rv)
    RecyclerView rv;
    @BindView(R.id.f_grabMonad_order_srl)
    SwipeRefreshLayout srl;
    Context context;
    List<Order> data;
    int page = 1;
    String status = "3";
    GrabMonadAdapter adapter;

    public static ConfirmedFragment getInstance() {
        return new ConfirmedFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.f_grabmonad_order;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected void initView() {
        initRv();
    }

    @Override
    public void onResume() {
        super.onResume();
        page =1;
        initData();
    }

    private void initRv() {
        srl.setOnRefreshListener(this);
        data = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(context));
        adapter = new GrabMonadAdapter(data);

        adapter.setOnLoadMoreListener(this, rv);
        adapter.disableLoadMoreIfNotFullPage();

        rv.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (position <=data.size()-1){
            OrderDetailActivity.start(context,data.get(position));
        }else {
            initData();
        }
    }

    private void initData(){
        Api.getDefault().grabselroomloan(KBApplication.token,KBApplication.userid,page,status)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<Order>>(getContext()) {
                    @Override
                    protected void success(List<Order> list) {
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
        if (srl != null && srl.isRefreshing()){
            srl.setRefreshing(false);
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

    @Override
    public void onRefresh() {
        page =1;
        initData();
    }

}
