package com.kb260.gxdk.view.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.BorrowingTipsAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.Information;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/11/23
 */
public class BorrowingTipsActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_borrowingTips_rv)
    RecyclerView rv;

    List<Information> data;
    int page=1;
    BorrowingTipsAdapter adapter;

    public static void start(Context context,String title){
        Intent intent = new Intent(context,BorrowingTipsActivity.class);
        intent.putExtra(Action.TOOLBAR_TITLE,title);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_borrowing_tips;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
        initData();
    }

    private void initData() {
        Api.getDefault().selinforation(KBApplication.token,KBApplication.userid,page)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<Information>>(this) {
                    @Override
                    protected void success(List<Information> list) {
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
        rv.setLayoutManager(new LinearLayoutManager(this));


        data = new ArrayList<>();
        adapter = new BorrowingTipsAdapter(data);

        adapter.setOnLoadMoreListener(this, rv);
        adapter.disableLoadMoreIfNotFullPage();
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void initToolbar() {
        Intent intent = getIntent();
        String title = intent.getStringExtra(Action.TOOLBAR_TITLE);
        toolbarTitle.setText(title);
        initThisToolbarHaveBack(toolbar,this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        InformationDetailActivity.start(this,data.get(position).getTitle(),data.get(position).getContent(),
                data.get(position).getCreatetime(),data.get(position).getPicture());
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
