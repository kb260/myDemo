package com.kb260.gxdk.view.me.fragment;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.IntegralDetailAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.Score;
import com.kb260.gxdk.model.IntegralDetail;
import com.kb260.gxdk.utils.Action;
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

public class IntegralFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.f_integral_rv)
    RecyclerView rv;

    Context context;
    IntegralDetailAdapter adapter;

    private int type;
    int addPage=1,subPage =1,allPage = 1;

    List<IntegralDetail.DetailBean> data;

    public static IntegralFragment getInstance(int type) {
        IntegralFragment integralFragment = new IntegralFragment();
        integralFragment.type = type;
        return integralFragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
        }
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
        adapter = new IntegralDetailAdapter(data);

        adapter.setOnLoadMoreListener(this, rv);
        adapter.disableLoadMoreIfNotFullPage();

        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    private void initData(){
        switch (type){
            case Action.INTEGRAL_TYPE_ALL:
                Api.getDefault().clickmyscore(KBApplication.token,KBApplication.userid,allPage)
                        .compose(RxHelper.handleResult())
                        .subscribe(new RxSubscriber<Score>(getContext()) {
                            @Override
                            protected void success(Score list) {
                                if (list.getDetail() != null && list.getDetail().size()>0){
                                    if (allPage >1){
                                        adapter.addData(list.getDetail());
                                    }else {
                                        data = list.getDetail();
                                        adapter.setNewData(data);
                                    }
                                }else {
                                    if (allPage ==1){
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
                break;
            case Action.INTEGRAL_TYPE_ADD:
                Api.getDefault().clickmyscoreadd(KBApplication.token,KBApplication.userid,addPage)
                        .compose(RxHelper.handleResult())
                        .subscribe(new RxSubscriber<IntegralDetail>(getContext()) {
                            @Override
                            protected void success(IntegralDetail list) {
                                if (list.getDetail() != null && list.getDetail().size()>0){
                                    if (addPage >1){
                                        adapter.addData(list.getDetail());
                                    }else {
                                        data = list.getDetail();
                                        adapter.setNewData(data);
                                    }
                                }else {
                                    if (addPage ==1){
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
                break;
            case Action.INTEGRAL_TYPE_EXPENDITURE:
                Api.getDefault().clickmyscoresub(KBApplication.token,KBApplication.userid,subPage)
                        .compose(RxHelper.handleResult())
                        .subscribe(new RxSubscriber<IntegralDetail>(getContext()) {
                            @Override
                            protected void success(IntegralDetail list) {
                                if (list.getDetail() != null && list.getDetail().size()>0){
                                    if (subPage >1){
                                        adapter.addData(list.getDetail());
                                    }else {
                                        data = list.getDetail();
                                        adapter.setNewData(data);
                                    }
                                }else {
                                    if (subPage ==1){
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
                break;
            default:
                break;
        }

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
        switch (type){
            case Action.INTEGRAL_TYPE_ALL:
                if (adapter.getData().size() < Contact.PAGE * allPage) {
                    adapter.loadMoreEnd(false);
                } else {
                    allPage++;
                    initData();
                }
                break;
            case Action.INTEGRAL_TYPE_ADD:
                if (adapter.getData().size() < Contact.PAGE * addPage) {
                    adapter.loadMoreEnd(false);
                } else {
                    addPage++;
                    initData();
                }
                break;
            case Action.INTEGRAL_TYPE_EXPENDITURE:
                if (adapter.getData().size() < Contact.PAGE * subPage) {
                    adapter.loadMoreEnd(false);
                } else {
                    subPage++;
                    initData();
                }
                break;
            default:
                break;
        }
    }
}
