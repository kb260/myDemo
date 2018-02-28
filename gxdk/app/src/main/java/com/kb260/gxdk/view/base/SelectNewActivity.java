package com.kb260.gxdk.view.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import com.kb260.gxdk.entity.NewPlate;
import com.kb260.gxdk.entity.PlateDetailNew;
import com.kb260.gxdk.entity.PlateDetailNewSection;
import com.kb260.gxdk.entity.PlateDetailThree;
import com.kb260.gxdk.entity.PlateDetailThreeSection;
import com.kb260.gxdk.model.EventData;
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
 * Created on  2017/12/5
 */
public class SelectNewActivity extends BaseActivity implements OnQuickSideBarTouchListener, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
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
    List<NewPlate.Bean> data;
    List<PlateDetailNewSection> carListBeans;
    List<PlateDetailThreeSection> listBeans;
    HashMap<String,Integer> letters = new HashMap<>();
    SelectTwoAdapter adapterTwo;
    SelectThreeAdapter adapterThree;

    String select1,select2,select3;

    public static void start(Activity context){
        Intent intent = new Intent(context,SelectNewActivity.class);
        context.startActivityForResult(intent,24);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_select;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initPlate();
        initRv2();
        initRv3();
    }

    private void initPlate() {
        Api.getDefault().selcarbrand()
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<NewPlate>(this) {
                    @Override
                    protected void success(NewPlate list) {
                        if (list != null){
                            data = new ArrayList<>();
                            data.addAll(list.getA());
                            data.addAll(list.getB());
                            data.addAll(list.getC());
                            data.addAll(list.getD());
                            data.addAll(list.getF());
                            data.addAll(list.getG());
                            data.addAll(list.getH());
                            data.addAll(list.getJ());
                            data.addAll(list.getK());
                            data.addAll(list.getL());
                            data.addAll(list.getM());
                            data.addAll(list.getN());
                            data.addAll(list.getO());
                            data.addAll(list.getP());
                            data.addAll(list.getQ());
                            data.addAll(list.getR());
                            data.addAll(list.getS());
                            data.addAll(list.getT());
                            data.addAll(list.getW());
                            data.addAll(list.getX());
                            data.addAll(list.getY());
                            data.addAll(list.getZ());
                            initSide();
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void initData2(String id,int position) {
        Api.getDefault().selcarbrandtype(id)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<PlateDetailNew>(this) {
                    @Override
                    protected void success(PlateDetailNew list) {
                        if (list.getPinpai_list()!= null && list.getPinpai_list().size()>0){
                            for (PlateDetailNew.PinpaiListBean bean :list.getPinpai_list()){
                                carListBeans.add(new PlateDetailNewSection(true,bean.getPpname()));
                                for (PlateDetailNew.PinpaiListBean.XilieBean xilieBean : bean.getXilie()){
                                    carListBeans.add(new PlateDetailNewSection(xilieBean));
                                }
                            }
                            adapterTwo.setNewData(carListBeans);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void initData3(String id){
        Api.getDefault().selcarlist(id)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<PlateDetailThree>(this) {
                    @Override
                    protected void success(PlateDetailThree list) {
                        if (list.getData()!= null && list.getData().size()>0){
                            for (PlateDetailThree.DataBean dataBean :list.getData()){
                                listBeans.add(new PlateDetailThreeSection(true,dataBean.getPyear()+"款"));
                                for (PlateDetailThree.DataBean.ChexingListBean chexingListBean : dataBean.getChexing_list()){
                                    listBeans.add(new PlateDetailThreeSection(chexingListBean));
                                }
                            }
                            adapterThree.setNewData(listBeans);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    /*private void initData3(int position) {
        if (carListBeans.get(position).getXilie() != null && carListBeans.get(position).getXilie().size()>0){
            listBeans.addAll(carListBeans.get(position).getXilie());
            adapterThree.setNewData(listBeans);
        }
    }*/

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
        for(NewPlate.Bean car: data){
            String letter = car.getPin();
            //如果没有这个key则加入并把位置也加入
            if(!letters.containsKey(letter)){
                letters.put(letter,position);
                customLetters.add(letter);
            }
            position++;
        }
        //不自定义则默认26个字母
        quickSideBarView.setLetters(customLetters);
        adapter = new SelectAdapter(data);
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
            select1 = data.get(position).getBig_ppname();
            llTwo.setVisibility(View.VISIBLE);
            initData2(data.get(position).getId(),position);
        }
    }

    public void initRv2(){
        carListBeans = new ArrayList<>();
        rvTwo.setLayoutManager(new LinearLayoutManager(this));
        rvTwo.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this,R.drawable.divider)));
        adapterTwo = new SelectTwoAdapter(carListBeans);
        rvTwo.setAdapter(adapterTwo);
        adapterTwo.setOnItemChildClickListener(this);

    }

    public void initRv3(){
        listBeans = new ArrayList<>();
        rvThree.setLayoutManager(new LinearLayoutManager(this));
        rvThree.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this,R.drawable.divider)));
        adapterThree = new SelectThreeAdapter(listBeans);
        rvThree.setAdapter(adapterThree);
        adapterThree.setOnItemChildClickListener(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter instanceof SelectTwoAdapter){
            if (((SelectTwoAdapter) adapter).getItem(position).t!=null){
                String id = ((SelectTwoAdapter) adapter).getItem(position).t.getXlid();
                if (id != null){
                    select2 = carListBeans.get(position).t.getXlname();
                    llThree.setVisibility(View.VISIBLE);
                    initData3(id);
                }
            }
        }else if (adapter instanceof SelectThreeAdapter){
            select3 = listBeans.get(position).t.getCxname();
            llTwo.setVisibility(View.GONE);
            llThree.setVisibility(View.GONE);

            Intent intent = new Intent();
            String a = select1+" "+select2 +" "+select3;
            String id = ((SelectThreeAdapter) adapter).getItem(position).t.getId();
            intent.putExtra(Action.SELECT_CAR,a);
            intent.putExtra(Action.SELECT_CAR_ID,id);
            setResult(25,intent);

            EventData eventData = new EventData(Action.EVENT_TYPE_SELECT_CAR,a,id);
            EventBus.getDefault().post(eventData);
            finish();
        }
    }
}
