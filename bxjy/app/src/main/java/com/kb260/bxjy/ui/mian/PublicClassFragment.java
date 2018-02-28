package com.kb260.bxjy.ui.mian;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.BannerNoClassViewHolder;
import com.kb260.bxjy.adapter.BannerViewHolder;
import com.kb260.bxjy.adapter.PublicClassAdapter;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.MoreMultiItem;
import com.kb260.bxjy.model.PublicClassMultiItem;
import com.kb260.bxjy.model.entity.BannerData;
import com.kb260.bxjy.model.entity.ClassData;
import com.kb260.bxjy.ui.base.BaseFragment;
import com.kb260.bxjy.ui.home.ClassDetailActivity;
import com.kb260.bxjy.ui.home.MoreActivity;
import com.kb260.bxjy.utils.ToastUtil;
import com.kb260.bxjy.weight.mzbanner.MZBannerView;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/1/31
 */

public class PublicClassFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener,
        View.OnClickListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_publicClass_rv)
    RecyclerView rv;

    MZBannerView mMZBanner;
    MZBannerView mMZBannerNoClass;
    LinearLayout noClassLl;
    private ArrayList<ImageView> mIndicators = new ArrayList<>();
    PublicClassAdapter adapter;
    List<PublicClassMultiItem> data;
    List<ClassData> list1;

    @Override
    protected int getLayoutResource() {
        return R.layout.f_publicclass;
    }

    @Override
    protected void initView() {
        toolbar.setTitle(R.string.publicClass_toolbarLeft);
        initData();
        initRv();
    }

    private void initData() {
        data = new ArrayList<>();
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new PublicClassAdapter(data);

        addTop();
        rv.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);

        getUnderwayCourseGroupLimitData();
        getAlreadyCourseGroupLimitData();
    }


    private void addTop(){
        View view = getActivity().getLayoutInflater().inflate(R.layout.top_home,
                (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);

        mMZBanner =  view.findViewById(R.id.banner);
        getBannerData();


        View view1 = getActivity().getLayoutInflater().inflate(R.layout.item_home_title,
                (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view1);
        ((TextView)view1.findViewById(R.id.item_title_tv)).setText(R.string.publicClass_noClassTitle);
        ((ImageView)view1.findViewById(R.id.item_title_img)).setImageResource(R.drawable.noclass);
        view1.findViewById(R.id.item_title_llMare).setOnClickListener(this);

        View view2 = getActivity().getLayoutInflater().inflate(R.layout.item_publicclass_noclass,
                (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view2);


        mMZBannerNoClass =  view2.findViewById(R.id.item_publicClass_noClassMbv);
        noClassLl =  view2.findViewById(R.id.item_publicClass_noClassLl);

        mMZBannerNoClass.setBannerPageClickListener((view3, position) ->
                ClassDetailActivity.start(getContext(),"",MoreMultiItem.PUBLIC_CLASS_SOON));
        mMZBannerNoClass.setIndicatorVisible(false);
        mMZBannerNoClass.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0;i<list1.size();i++){
                    if (i == position){
                        mIndicators.get(i).setBackgroundResource(R.drawable.point_group1);
                    }else {
                        mIndicators.get(i).setBackgroundResource(R.drawable.point_group0);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getUpcomingCourseLimitData();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMZBanner.pause();
        mMZBannerNoClass.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();
        mMZBannerNoClass.start();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.item_title_llMare:
                switch (data.get(position).getItemType()){
                    case PublicClassMultiItem.CLASSING_TITLE:
                        MoreActivity.start(getContext(),"正在开课","",MoreMultiItem.PUBLIC_CLASSING);
                        break;
                    case PublicClassMultiItem.CLASSED_TITLE:
                        MoreActivity.start(getContext(),"已开课程","", MoreMultiItem.PUBLIC_CLASSED);
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_title_llMare:
                MoreActivity.start(getContext(),"即将开课","",0);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (data.get(position).getItemType()){
            case PublicClassMultiItem.CLASSING_LIST:
                ClassDetailActivity.start(getContext(),"",0);
                break;
            case PublicClassMultiItem.CLASSED_LIST:
                ClassDetailActivity.start(getContext(),"",0);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化指示器Indicator
     */
    private void initIndicator(){
        noClassLl.removeAllViews();
        mIndicators.clear();
        for(int i=0;i<list1.size();i++){
            ImageView imageView = new ImageView(getContext());
            if (i == 0){
                imageView.setBackgroundResource(R.drawable.point_group1);
            }else {
                imageView.setBackgroundResource(R.drawable.point_group0);
            }
            mIndicators.add(imageView);
            noClassLl.addView(imageView);
            setMargins(imageView,4,0,4,0);
        }
    }

    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(l, t, r, b);
            v.setLayoutParams(lp);
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
     * 公开课-即将开课（推荐）
     */
    private void getUpcomingCourseLimitData() {
        Api.getDefault().upcomingCourseLimit(KbApplication.token)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<ClassData>>(getContext()) {
                    @Override
                    protected void success(List<ClassData> list) {
                        if (list!=null&& list.size()>0){
                            list1 = list;
                            initIndicator();
                            mMZBannerNoClass.setPages(list1, () -> new BannerNoClassViewHolder());
                        }else {
                            list1 = new ArrayList<>();
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        list1 = new ArrayList<>();
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

    /**
     * 公开课-正在开课（推荐）
     */
    private void getUnderwayCourseGroupLimitData() {
        Api.getDefault().underwayCourseGroupLimit(KbApplication.token)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<ClassData>>(getContext()) {
                    @Override
                    protected void success(List<ClassData> list) {
                        if (list!=null&& list.size()>0){
                            data.add(new PublicClassMultiItem(PublicClassMultiItem.CLASSING_TITLE));
                            for (ClassData data1 : list){
                                data.add(new PublicClassMultiItem(PublicClassMultiItem.CLASSING_LIST));
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
     * 公开课-正在开课（推荐）
     */
    private void getAlreadyCourseGroupLimitData() {
        Api.getDefault().alreadyCourseGroupLimit(KbApplication.token)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<ClassData>>(getContext()) {
                    @Override
                    protected void success(List<ClassData> list) {
                        if (list!=null&& list.size()>0){
                            data.add(new PublicClassMultiItem(PublicClassMultiItem.CLASSED_TITLE));
                            for (ClassData data1 : list){
                                data.add(new PublicClassMultiItem(PublicClassMultiItem.CLASSED_LIST));
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
