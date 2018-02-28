package com.kb260.bxjy.ui.mian;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.BannerViewHolder;
import com.kb260.bxjy.adapter.HomeAdapter;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.HomeMultiItem;
import com.kb260.bxjy.model.entity.BannerData;
import com.kb260.bxjy.model.entity.ClassData;
import com.kb260.bxjy.model.entity.LiveRecommendedData;
import com.kb260.bxjy.model.entity.TeacherLimitData;
import com.kb260.bxjy.ui.base.BaseFragment;
import com.kb260.bxjy.ui.home.ClassDetailActivity;
import com.kb260.bxjy.ui.home.CourseSortsActivity;
import com.kb260.bxjy.ui.home.MoreActivity;
import com.kb260.bxjy.ui.home.TeacherDetailActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.ToastUtil;
import com.kb260.bxjy.weight.mzbanner.MZBannerView;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 *         Created on  2018/1/31
 */

public class HomeFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.a_main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_main_rv)
    RecyclerView rv;

    MZBannerView mMZBanner;
    HomeAdapter adapter;
    List<HomeMultiItem> data;


    @Override
    protected int getLayoutResource() {
        return R.layout.f_home;
    }

    @Override
    protected void initView() {
        toolbar.setTitle(R.string.home_toolbarLeft);
        initData();
        initRv();
    }

    private void initData() {
        data = new ArrayList<>();
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new HomeAdapter(data);

        rv.setAdapter(adapter);
        addTop();
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);

        getLiveData();
        getHotData();
        getTeacherData();
    }

    //课程分类
    @OnClick(R.id.a_main_toolbarRight)
    public void courseSorts() {
        CourseSortsActivity.start(getContext());
    }

    private void addTop() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.top_home, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);

        mMZBanner = view.findViewById(R.id.banner);
        mMZBanner.setBannerPageClickListener((view1, i) -> {
        });
        getBannerData();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.item_title_llMare:
                //查看更多
                switch (data.get(position).getItemType()) {
                    case HomeMultiItem.LIVE_TITLE:
                        //直播推荐查看更多
                        MoreActivity.start(getContext(), "直播推荐", "", Action.MORE_TYPE_HOME_LIVE);
                        break;
                    case HomeMultiItem.HIT_TITLE:
                        //热播榜查看更多
                        MoreActivity.start(getContext(), "热播榜", "", Action.MORE_TYPE_HOME_HIT);
                        break;
                    case HomeMultiItem.TEACHER_TITLE:
                        //教师榜查看更多
                        MoreActivity.start(getContext(), "教师榜","", Action.MORE_TYPE_HOME_TEACHER);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (data.get(position).getItemType()) {
            case HomeMultiItem.LIVE_LIST:
                //直播推荐
                ClassDetailActivity.start(getContext(), "", 0);
                break;
            case HomeMultiItem.HIT_LIST:
                //热播榜
                ClassDetailActivity.start(getContext(), "", 0);
                break;
            case HomeMultiItem.TEACHER_LIST:
                //教师榜
                TeacherDetailActivity.start(getContext(), data.get(position).getId());
                break;
            default:
                break;
        }
    }

    private void getBannerData() {
        Api.getDefault().banner(KbApplication.token, "5")
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<BannerData>>(getContext()) {
                    @Override
                    protected void success(List<BannerData> list) {
                        if (list != null && list.size()>0){
                            mMZBanner.setPages(list, () -> new BannerViewHolder());
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

    /**
     * 直播推荐
     */
    private void getLiveData() {
        Api.getDefault().liveStreamLimit(KbApplication.token)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<LiveRecommendedData>>(getContext()) {
                    @Override
                    protected void success(List<LiveRecommendedData> list) {
                        if (list!=null&& list.size()>0){
                            data.add(new HomeMultiItem(HomeMultiItem.LIVE_TITLE));
                            for (LiveRecommendedData lData:list){
                                data.add(new HomeMultiItem(HomeMultiItem.LIVE_LIST));
                            }
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

    /**
     * 首页-热播榜(推荐)
     */
    private void getHotData() {
        Api.getDefault().topBroadcastLimit(KbApplication.token)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<ClassData>>(getContext()) {
                    @Override
                    protected void success(List<ClassData> list) {
                        if (list!=null&& list.size()>0){
                            data.add(new HomeMultiItem(HomeMultiItem.HIT_TITLE));
                            for (ClassData lData:list){
                                data.add(new HomeMultiItem(HomeMultiItem.HIT_LIST));
                            }
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

    /**
     * 首页-教师榜(推荐)
     */
    private void getTeacherData() {
        Api.getDefault().topTeacherLimit(KbApplication.token)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<TeacherLimitData>>(getContext()) {
                    @Override
                    protected void success(List<TeacherLimitData> list) {
                        if (list!=null&& list.size()>0){
                            data.add(new HomeMultiItem(HomeMultiItem.TEACHER_TITLE));
                            for (TeacherLimitData lData:list){
                                data.add(new HomeMultiItem(HomeMultiItem.TEACHER_LIST,lData.getTeacherId()));
                            }
                            adapter.setNewData(data);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

}
