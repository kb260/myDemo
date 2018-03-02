package com.panda.sharebike.ui.selfinfo;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.github.jdsjlzx.ItemDecoration.LuDividerDecoration;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.model.entity.WaterMessageBean;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.base.ListBaseAdapter;
import com.panda.sharebike.ui.base.SuperViewHolder;
import com.panda.sharebike.ui.login.LoginByPhoneActivity;
import com.panda.sharebike.util.AppUtil;

import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 余额明细
 */
public class BalanceDetailActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.my_recycleView)
    LuRecyclerView mRecyclerView;
    @BindView(R.id.my_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.empty_view)
    TextView emptyView;

    /**
     * 服务器端一共多少条数据
     */
    private int TOTAL_COUNTER = 0;
    /**
     * 每一页展示多少条数据
     */
    private int REQUEST_COUNT = 10;

    /**
     * 已经获取到多少条数据了
     */
    private int mCurrentCounter = 1;

    //判断是否有下页
    private boolean hasNextPage = true;

    private LuRecyclerViewAdapter mLuRecyclerViewAdapter = null;
    private DataAdapter mDataAdapter = null;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_balance_detail;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void initView() {
        // mEmptyView.setVisibility(View.VISIBLE);
        //设置刷新时动画的颜色，可以设置4个
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setProgressViewOffset(false, 0, AppUtil.dip2px(this, 48));
            mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
            mSwipeRefreshLayout.setOnRefreshListener(this);
        }
        //mRecyclerView.setEmptyView(mEmptyView);

        mDataAdapter = new DataAdapter(this);
        // mRecyclerView.setEmptyView(emptyView);
        //setLayoutManager放在setAdapter之前配置
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLuRecyclerViewAdapter = new LuRecyclerViewAdapter(mDataAdapter);
        mRecyclerView.setAdapter(mLuRecyclerViewAdapter);
        LuDividerDecoration divider = new LuDividerDecoration.Builder(this, mLuRecyclerViewAdapter)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.split)
                .build();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(divider);

        //recycleview的加载更多事件
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (hasNextPage) {
                    mCurrentCounter++;
                    getBalanceDetail(mCurrentCounter, 10);
                } else {
                    mRecyclerView.setNoMore(true);
                }
            }
        });
        //设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.colorAccent, R.color.dark, android.R.color.white);
        //设置底部加载文字提示
        mRecyclerView.setFooterViewHint("正在加载", "加载完毕", "网络不给力啊，点击再试一次吧");
        getBalanceDetail(mCurrentCounter, 10);
    }

    private void notifyDataSetChanged() {
        mLuRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(List<WaterMessageBean.ItemsBean> list) {

        mDataAdapter.addAll(list);
    }

    private class DataAdapter extends ListBaseAdapter<WaterMessageBean.ItemsBean> {

        public DataAdapter(Context context) {
            super(context);
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_water_view;
        }

        @Override
        public void onBindItemHolder(SuperViewHolder holder, int position) {
            WaterMessageBean.ItemsBean item = mDataList.get(position);

            String logTime = TimeUtils.millis2String(item.getLogTime());
            TextView item_reason = holder.getView(R.id.tv_reason);//骑行费用
            TextView item_deltaFee = holder.getView(R.id.tv_deltaFee);//费用
            TextView item_logTime = holder.getView(R.id.tv_logTime);//花费的时间
            item_reason.setText(item.getReason());
            String str = String.valueOf(item.getDeltaFee());
            String price = null;
            if (!str.startsWith("-")) {
                price = "+" + str;
            } else {
                price = str;
            }
            item_deltaFee.setText(price + "元");
            item_logTime.setText(logTime);

        }

    }

    /**
     * 明细
     *
     * @param pageNum
     * @param pageSize
     */
    private void getBalanceDetail(int pageNum, int pageSize) {
        Api.getInstance().getDefault().getMemBerAccountLogForall(Config.TOKEN, pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<WaterMessageBean>>(new SubscriberListener<HttpResult<WaterMessageBean>>() {

                    @Override
                    public void onSuccess(HttpResult<WaterMessageBean> model) {
                        if (401 == model.getCode()) {
                            startActivity(LoginByPhoneActivity.class);
                            return;
                        }
                        if (model.isOk()) {
                            TOTAL_COUNTER = model.getData().getPageCount();//服务器端总共数据
                            hasNextPage = model.getData().isHasNextPage();//判断是否有下页
                            if (EmptyUtils.isNotEmpty(model.getData().getItems())) {
                                addItems(model.getData().getItems());
                                emptyView.setVisibility(View.GONE);
                            } else {
                                emptyView.setVisibility(View.VISIBLE);
                            }
                            if (mSwipeRefreshLayout.isRefreshing()) {
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                            mRecyclerView.refreshComplete(REQUEST_COUNT);
                            notifyDataSetChanged();
                        } else {
                            if (mSwipeRefreshLayout.isRefreshing()) {
                                mSwipeRefreshLayout.setRefreshing(false);
                                mRecyclerView.refreshComplete(REQUEST_COUNT);
                                notifyDataSetChanged();
                            } else {
                                mRecyclerView.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
                                    @Override
                                    public void reload() {
                                        mRecyclerView.refreshComplete(REQUEST_COUNT);
                                        notifyDataSetChanged();
                                        getBalanceDetail(mCurrentCounter, 10);
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, false));
    }

    /**
     * 明细
     *
     * @param pageNum
     * @param pageSize
     */
    private void getBalanceDetailT(int pageNum, int pageSize) {
        Api.getInstance().getDefault().getMemBerAccountLogForall(Config.TOKEN, pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<WaterMessageBean>>(new SubscriberListener<HttpResult<WaterMessageBean>>() {

                    @Override
                    public void onSuccess(HttpResult<WaterMessageBean> model) {
                        if (401 == model.getCode()) {
                            startActivity(LoginByPhoneActivity.class);
                            return;
                        }
                        if (model.isOk()) {
                            TOTAL_COUNTER = model.getData().getPageCount();//服务器端总共数据
                            hasNextPage = model.getData().isHasNextPage();//判断是否有下页

                            if (EmptyUtils.isNotEmpty(model.getData().getItems())) {
                                mDataAdapter.setDataList(model.getData().getItems());
                                emptyView.setVisibility(View.GONE);
                            } else {
                                emptyView.setVisibility(View.VISIBLE);
                            }

                            if (mSwipeRefreshLayout.isRefreshing()) {
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                            mRecyclerView.refreshComplete(REQUEST_COUNT);
                            notifyDataSetChanged();
                        } else {
                            if (mSwipeRefreshLayout.isRefreshing()) {
                                mSwipeRefreshLayout.setRefreshing(false);
                                mRecyclerView.refreshComplete(REQUEST_COUNT);
                                notifyDataSetChanged();
                            } else {
                                mRecyclerView.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
                                    @Override
                                    public void reload() {
                                        mRecyclerView.refreshComplete(REQUEST_COUNT);
                                        notifyDataSetChanged();
                                        getBalanceDetail(mCurrentCounter, 10);
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, false));
    }
    @Override
    public void onRefresh() {
        mCurrentCounter = 1;
        mSwipeRefreshLayout.setRefreshing(true);
        mRecyclerView.setRefreshing(true);
        getBalanceDetailT(mCurrentCounter, 10);


    }
}
