package com.kb260.bxjy.ui.mian;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.BannerViewHolder;
import com.kb260.bxjy.adapter.FilterAdapter;
import com.kb260.bxjy.adapter.GroupClassAdapter;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.GroupClassMultiItem;
import com.kb260.bxjy.model.entity.BannerData;
import com.kb260.bxjy.ui.base.BaseFragment;
import com.kb260.bxjy.ui.group.LiveListActivity;
import com.kb260.bxjy.utils.KeyBordUtil;
import com.kb260.bxjy.utils.ToastUtil;
import com.kb260.bxjy.weight.CustomPopWindow;
import com.kb260.bxjy.weight.mzbanner.MZBannerView;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/1/31
 */

public class GroupClassFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener, RadioGroup.OnCheckedChangeListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.toolbarWithSearch)
    Toolbar toolbar;
    @BindView(R.id.toolbarEt)
    EditText toolbarEt;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_groupClass_rv)
    RecyclerView rv;
    MZBannerView mMZBanner;
    ImageView point0,point1;

    GroupClassAdapter adapter;
    List<GroupClassMultiItem> data;

    RadioButton rbInternal,rbPublic;

    @Override
    protected int getLayoutResource() {
        return R.layout.f_groupcalss;
    }

    @Override
    protected void initView() {
        toolbarTitle.setText(R.string.groupClass_toolbarLeft);
        initData();
        initRv();
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new GroupClassMultiItem(GroupClassMultiItem.INTERNAL));
        data.add(new GroupClassMultiItem(GroupClassMultiItem.INTERNAL));
        data.add(new GroupClassMultiItem(GroupClassMultiItem.INTERNAL));
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new GroupClassAdapter(data);

        addTop();
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
    }


    private void addTop(){
        View view = getActivity().getLayoutInflater().inflate(R.layout.top_home, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);

        mMZBanner =  view.findViewById(R.id.banner);
        getBannerData();


        View view1 = getActivity().getLayoutInflater().inflate(R.layout.item_groupclass_top2, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view1);

        RadioGroup rg = view1.findViewById(R.id.item_groupClass_rg);
        rbInternal = view1.findViewById(R.id.item_groupClass_rbInternal);
        rbPublic = view1.findViewById(R.id.item_groupClass_rbPublic);
        point0 = view1.findViewById(R.id.item_groupClass_point0);
        point1 = view1.findViewById(R.id.item_groupClass_point1);
        rg.setOnCheckedChangeListener(this);
        rg.check(R.id.item_groupClass_rbInternal);
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

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.item_groupClass_rbInternal:
                point0.setBackgroundResource(R.drawable.point_group1);
                point1.setBackgroundResource(R.drawable.point_group0);
                rbInternal.setTextColor(Color.WHITE);
                rbPublic.setTextColor(ContextCompat.getColor(radioGroup.getContext(),R.color.text_content_999));
                break;
            case R.id.item_groupClass_rbPublic:
                point0.setBackgroundResource(R.drawable.point_group0);
                point1.setBackgroundResource(R.drawable.point_group1);
                rbPublic.setTextColor(Color.WHITE);
                rbInternal.setTextColor(ContextCompat.getColor(radioGroup.getContext(),R.color.text_content_999));
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        LiveListActivity.start(getContext(),"","",0);
    }

    @OnClick(R.id.toolbarSearch)
    public void search(){
        toolbarEt.setVisibility(View.VISIBLE);
        toolbarEt.requestFocus();
        toolbarEt.setFocusable(true);
        KeyBordUtil.showKeyBord(toolbarEt,getContext());
    }

    @OnClick(R.id.toolbarFilter)
    public void filter(){
        showPopListView();
    }

    private void showPopListView() {
        //创建并显示popWindow
        initFilter();
        new CustomPopWindow.PopupWindowBuilder(getContext())
                .setView(contentView)
                //.enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()
                .showAsDropDown(toolbar, 0, 0);
    }

    View contentView;

    private void initFilter() {
        if (contentView == null) {
            contentView = LayoutInflater.from(getContext()).inflate(R.layout.filter_top, null);
            RecyclerView filterRv = contentView.findViewById(R.id.pop_filter_rv);
            filterRv.setLayoutManager(new GridLayoutManager(getContext(), 4));
            List<String> data = new ArrayList<>();
            data.add("全部");
            data.add("科目");
            data.add("公开");
            data.add("内部");
            data.add("全部");
            data.add("科目");
            data.add("公开");
            data.add("内部");
            data.add("全部");
            data.add("科目");
            data.add("公开");
            data.add("内部");
            data.add("全部");
            data.add("科目");
            data.add("公开");
            data.add("内部");
            FilterAdapter adapter = new FilterAdapter(data);
            filterRv.setAdapter(adapter);
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
}
