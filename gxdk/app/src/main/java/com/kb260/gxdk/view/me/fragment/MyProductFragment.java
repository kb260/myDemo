package com.kb260.gxdk.view.me.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.MyProductAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.EventData;
import com.kb260.gxdk.model.MyProduct;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseFragment;
import com.kb260.gxdk.view.me.ProductDetailActivity;
import com.kb260.gxdk.view.me.ProductDetailNewActivity;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/11/10
 */

public class MyProductFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.a_myProduct_rv)
    RecyclerView rv;
    MyProductAdapter adapter;

    Context context;
    int page =1;
    List<MyProduct> data;

    public static MyProductFragment getInstance() {
        return new MyProductFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.f_my_product;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        initRv();
        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initRv() {
        data = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MyProductAdapter(data);

        adapter.setOnLoadMoreListener(this, rv);
        adapter.disableLoadMoreIfNotFullPage();

        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ProductDetailNewActivity.start(context,data.get(position));
    }

    private void initData() {
        Api.getDefault().selroomproduct(KBApplication.token,KBApplication.userid,page)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<MyProduct>>(getContext()) {
                    @Override
                    protected void success(List<MyProduct> list) {
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void helloEventBus(EventData eventData) {
        if (eventData.getType() == Action.EVENT_TYPE_MY_PRODUCT){
            initData();
        }
    }
}
