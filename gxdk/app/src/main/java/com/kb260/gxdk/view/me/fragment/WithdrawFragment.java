package com.kb260.gxdk.view.me.fragment;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.WithdrawLogAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.WithdrawLog;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.DividerItemDecoration;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseFragment;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/11/13
 */

public class WithdrawFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.f_integral_rv)
    RecyclerView rv;

    List<WithdrawLog> data;
    WithdrawLogAdapter adapter;
    Context context;
    private int type;
    int page = 1;

    public static WithdrawFragment getInstance(int type) {
        WithdrawFragment integralFragment = new WithdrawFragment();
        integralFragment.type = type;
        return integralFragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.f_integral;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected void initView() {
        initRv();
        initData();
    }

    private void initRv() {
        data = new ArrayList<>();

        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(context,R.drawable.divider)));
        adapter = new WithdrawLogAdapter(data);

        adapter.setOnLoadMoreListener(this, rv);
        adapter.disableLoadMoreIfNotFullPage();

        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    private void initData(){
        Api.getDefault().seldeposithistory(KBApplication.token,KBApplication.userid,page,type)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<WithdrawLog>>(getContext()) {
                    @Override
                    protected void success(List<WithdrawLog> list) {
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
