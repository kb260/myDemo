package com.panda.sharebike.ui.ride;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.github.jdsjlzx.ItemDecoration.LuDividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.model.entity.ItineraryBean;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.base.ListBaseAdapter;
import com.panda.sharebike.ui.base.SuperViewHolder;
import com.panda.sharebike.util.AMapUtil;
import com.panda.sharebike.util.AppUtil;

import java.util.List;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 我的行程
 */
public class ItineraryListActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    /**
     * 服务器端一共多少条数据
     */
    private int TOTAL_COUNTER = 0;
    /**
     * 已经获取到多少条数据了
     */
    private int mCurrentCounter = 1;
    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;
    //判断是否有下页
    private boolean hasNextPage = true;

    private LuRecyclerViewAdapter mLuRecyclerViewAdapter = null;
    private DataAdapter mDataAdapter = null;
    private List<ItineraryBean.ItemsBean> item;


    @BindView(R.id.my_recycleView)
    LuRecyclerView mRecyclerView;
    @BindView(R.id.my_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.empty_view)
    TextView mEmptyView;


    @Override
    protected int getLayoutView() {
        return R.layout.activity_itinerary_list;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
   
        initView();
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
        //  mRecyclerView.setEmptyView(mEmptyView);
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
        //recycleview点击事件
        mLuRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {//点击跳转到行程详情界面
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ItineraryListActivity.this, RideDetailActivity.class);
                intent.putExtra(Config.SCANUNLOCK_KEY, mDataAdapter.getDataList().get(position).getId());
                //   intent.putExtra(Config.SCANUNLOCK_KEY, item.get(position).getId());//将对应位置订单ID传给另一个界面
                startActivity(intent);
            }
        });
        //recycleview的加载更多事件
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (hasNextPage) {
                    mCurrentCounter += 1;
                    getMyItineraryList(mCurrentCounter, 10);
                } else {
                    mRecyclerView.setNoMore(true);
                }
            }
        });
        //设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.colorAccent, R.color.dark, android.R.color.white);
        //设置底部加载文字提示
        mRecyclerView.setFooterViewHint("正在加载", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");
        getMyItineraryList(mCurrentCounter, REQUEST_COUNT);
    }


    @Override
    public void onRefresh() {
        mCurrentCounter = 1;
        mSwipeRefreshLayout.setRefreshing(true);
        mRecyclerView.setRefreshing(true);//同时调用LuRecyclerView的setRefreshing方法
        //  mDataAdapter.clear();
        getMyItineraryListT(mCurrentCounter, REQUEST_COUNT);
    }

    private void notifyDataSetChanged() {
        mLuRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(List<ItineraryBean.ItemsBean> list) {
        item = list;
        mDataAdapter.addAll(list);
        //   mCurrentCounter += list.size();
    }

    private class DataAdapter extends ListBaseAdapter<ItineraryBean.ItemsBean> {

        public DataAdapter(Context context) {
            super(context);
        }

        @Override
        public int getLayoutId() {
            return R.layout.view_item_itinerary;
        }

        @Override
        public void onBindItemHolder(SuperViewHolder holder, int position) {
            ItineraryBean.ItemsBean item = mDataList.get(position);
            String startTime = null;
            String time = null;
            try {
                //骑行开始时间
                startTime = TimeUtils.millis2String(item.getStartTime());
                long timeSpan = TimeUtils.getTimeSpan(item.getEndTime(), item.getStartTime(), TimeConstants.MIN);
                //time = (int) (timeSpan);//获得分钟
                time = AMapUtil.getFriendlyTime((int) ((item.getEndTime() - item.getStartTime()) / 1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String money = String.valueOf(item.getCost());
            if (money.startsWith("-")) {
                money = money.substring(1, money.length());
            }
            TextView item_time = holder.getView(R.id.tv_time);//时间
            TextView item_number = holder.getView(R.id.tv_number);//单车编号
            TextView item_money = holder.getView(R.id.tv_money);//花费的钱
            TextView item_paly_time = holder.getView(R.id.tv_play_time);//花费的时间
            item_time.setText(startTime);
            item_paly_time.setText(time);
            item_number.setText(item.getBike().getNum());
            item_money.setText(money);
        }

    }

    /**
     * 我的行程
     */
    private void getMyItineraryList(int pageNum, int pageSize) {
        Api.getInstance().getDefault().getRecords(Config.TOKEN, pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<ItineraryBean>>(new SubscriberListener<HttpResult<ItineraryBean>>() {
                    @Override
                    public void onSuccess(HttpResult<ItineraryBean> model) {
                        if (model.isOk()) {
                            TOTAL_COUNTER = model.getData().getPageCount();//服务器端总共数据
                            hasNextPage = model.getData().isHasNextPage();//判断是否有下页
                            if (EmptyUtils.isNotEmpty(model.getData().getItems())) {
                                addItems(model.getData().getItems());
                                mEmptyView.setVisibility(View.GONE);
                            } else {
                                mEmptyView.setVisibility(View.VISIBLE);
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
                                        getMyItineraryList(mCurrentCounter, 10);
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
     * 我的行程
     */
    private void getMyItineraryListT(int pageNum, int pageSize) {
        Api.getInstance().getDefault().getRecords(Config.TOKEN, pageNum, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult<ItineraryBean>>(new SubscriberListener<HttpResult<ItineraryBean>>() {
                    @Override
                    public void onSuccess(HttpResult<ItineraryBean> model) {
                        if (model.isOk()) {
                            TOTAL_COUNTER = model.getData().getPageCount();//服务器端总共数据
                            hasNextPage = model.getData().isHasNextPage();//判断是否有下页

                            if (EmptyUtils.isNotEmpty(model.getData().getItems())) {
                                mDataAdapter.setDataList(model.getData().getItems());
                                mEmptyView.setVisibility(View.GONE);
                            } else {
                                mEmptyView.setVisibility(View.VISIBLE);
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
                                        getMyItineraryList(mCurrentCounter, 10);
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
}
