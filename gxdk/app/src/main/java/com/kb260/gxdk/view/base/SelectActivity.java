package com.kb260.gxdk.view.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bigkoo.quicksidebar.QuickSideBarTipsView;
import com.bigkoo.quicksidebar.QuickSideBarView;
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.SelectAdapter;
import com.kb260.gxdk.adapter.SelectThreeAdapter;
import com.kb260.gxdk.adapter.SelectTwoAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.Car;
import com.kb260.gxdk.entity.Plate;
import com.kb260.gxdk.entity.PlateDetail;
import com.kb260.gxdk.model.EventData;
import com.kb260.gxdk.model.Information;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.DividerItemDecoration;
import com.kb260.gxdk.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * @date  2017/10/18
 */
public class SelectActivity extends BaseActivity implements OnQuickSideBarTouchListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.quickSideBarView)
    QuickSideBarView quickSideBarView;
    @BindView(R.id.quickSideBarTipsView)
    QuickSideBarTipsView quickSideBarTipsView;
    @BindView(R.id.a_select_rv)
    RecyclerView rv;
    @BindView(R.id.a_select_rvThree)
    RecyclerView rvThree;
    @BindView(R.id.a_select_rvTwo)
    RecyclerView rvTwo;
    @BindView(R.id.a_select_llTwo)
    LinearLayout llTwo;
    @BindView(R.id.a_select_llThree)
    LinearLayout llThree;

    SelectAdapter adapter;
    List<Plate> data;
    List<PlateDetail.CarlistBean> carListBeans;
    List<PlateDetail.CarlistBean.ListBean> listBeans;
    HashMap<String,Integer> letters = new HashMap<>();
    SelectTwoAdapter adapterTwo;
    SelectThreeAdapter adapterThree;

    String select1,select2,select3;

    public static void start(Activity context){
        Intent intent = new Intent(context,SelectActivity.class);
        context.startActivityForResult(intent,24);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_select;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initData1();
        initRv2();
        initRv3();
    }

    private void initData1() {
        /*Api.getDefault().selcarbrand()
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<Plate>>(this) {
                    @Override
                    protected void success(List<Plate> list) {
                        if (list != null && list.size()>0){
                            data = list;
                            initSide();
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });*/
    }

    private void initData2(String id,int position) {
        /*Api.getDefault().selcarbrandtype(id)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<PlateDetail>>(this) {
                    @Override
                    protected void success(List<PlateDetail> list) {
                        if (list.get(0).getCarlist() != null && list.get(0).getCarlist().size()>0){
                            carListBeans.addAll(list.get(0).getCarlist());
                            adapterTwo.setNewData(carListBeans);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });*/
    }

    private void initData3(int position) {
        if (carListBeans.get(position).getList() != null && carListBeans.get(position).getList().size()>0){
            listBeans.addAll(carListBeans.get(position).getList());
            //adapterThree.setNewData(listBeans);
        }
    }

    private void initSide() {
        //设置监听
        quickSideBarView.setOnQuickSideBarTouchListener(this);


        //设置列表数据和浮动header
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);

        ArrayList<String> customLetters = new ArrayList<>();
        /*customLetters.add("#");*/
        int position = 0;
        for(Plate car: data){
            String letter = car.getInitial();
            //如果没有这个key则加入并把位置也加入
            if(!letters.containsKey(letter)){
                letters.put(letter,position);
                customLetters.add(letter);
            }
            position++;
        }
        //不自定义则默认26个字母
        quickSideBarView.setLetters(customLetters);
        //adapter = new SelectAdapter(data);
        rv.setAdapter(adapter);

        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        rv.addItemDecoration(headersDecor);
        //addTop();

        adapter.setOnItemClickListener(this);
    }

    /*private void addTop(){
        adapter.addHeaderView(getLayoutInflater().inflate(R.layout.select_top, (ViewGroup) rv.getParent(), false));
    }*/

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_select_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    @Override
    public void onLetterChanged(String letter, int position, float y) {
        quickSideBarTipsView.setText(letter, position, y);
        //有此key则获取位置并滚动到该位置
        //rv.scrollToPosition(position);
        if(letters.containsKey(letter)) {
            rv.scrollToPosition(letters.get(letter));
        }
    }

    @Override
    public void onLetterTouching(boolean touching) {
        //可以自己加入动画效果渐显渐隐
        quickSideBarTipsView.setVisibility(touching? View.VISIBLE:View.INVISIBLE);
    }

    @OnClick(R.id.a_select_vTwo)
    public void clickRvTwo(){
        llTwo.setVisibility(View.GONE);
        llThree.setVisibility(View.GONE);
    }

    @OnClick(R.id.a_select_vThree)
    public void clickRvThree(){
        llThree.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter instanceof SelectAdapter){
            select1 = data.get(position).getName();
            llTwo.setVisibility(View.VISIBLE);
            initData2(data.get(position).getId(),position);
        }else if (adapter instanceof SelectTwoAdapter){
            select2 = carListBeans.get(position).getName();
            llThree.setVisibility(View.VISIBLE);
            initData3(position);
        }else if (adapter instanceof SelectThreeAdapter){
            select3 = listBeans.get(position).getName();
            llTwo.setVisibility(View.GONE);
            llThree.setVisibility(View.GONE);

            Intent intent = new Intent();
            String a = select1+" "+select2 +" "+select3;
            intent.putExtra(Action.SELECT_CAR,a);
            setResult(25,intent);

            EventData eventData = new EventData(Action.EVENT_TYPE_SELECT_CAR,a);
            EventBus.getDefault().post(eventData);

            finish();

        }
    }

    public void initRv2(){
        carListBeans = new ArrayList<>();
        rvTwo.setLayoutManager(new LinearLayoutManager(this));
        rvTwo.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this,R.drawable.divider)));
        //adapterTwo = new SelectTwoAdapter(carListBeans);
        rvTwo.setAdapter(adapterTwo);
        adapterTwo.setOnItemClickListener(this);

    }

    public void initRv3(){
        listBeans = new ArrayList<>();
        rvThree.setLayoutManager(new LinearLayoutManager(this));
        rvThree.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this,R.drawable.divider)));
        //adapterThree = new SelectThreeAdapter(listBeans);
        rvThree.setAdapter(adapterThree);
        adapterThree.setOnItemClickListener(this);
    }
}
