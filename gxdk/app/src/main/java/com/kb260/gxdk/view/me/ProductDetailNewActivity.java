package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.ProductDetailAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.ProductDetailMultiItem;
import com.kb260.gxdk.model.EventData;
import com.kb260.gxdk.model.MyProduct;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/11/21
 */
public class ProductDetailNewActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_productDetailNew_rv)
    RecyclerView rv;

    ProductDetailAdapter adapter;
    List<ProductDetailMultiItem> data;
    MyProduct myProduct;

    public static void start(Context context, MyProduct myProduct){
        Intent intent = new Intent(context,ProductDetailNewActivity.class);
        intent.putExtra(Action.PRODUCT_DATA,myProduct);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail_new;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
        initRv();
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductDetailAdapter(data);
        rv.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
    }


    private void initIntent() {
        myProduct = (MyProduct) getIntent().getSerializableExtra(Action.PRODUCT_DATA);
        initData(myProduct);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_productDetail_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    private void initData(MyProduct myProduct) {
        data = new ArrayList<>();
        String type = "";
        switch (myProduct.getType()){
            case "1":
                type = "房屋服务";
                break;
            case "2":
                type = "车辆服务";
                break;
            default:
                break;
        }
        data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TYPE,type));
        data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"所属银行",myProduct.getBank()));
        data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"支行",myProduct.getBranchname()));
        data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"预计办理完成时间",myProduct.getCreditday()));
        data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"申请人年龄",myProduct.getAge()+"~"+myProduct.getMaxage()));

        switch (myProduct.getType()){
            case "1":
                data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"可做房龄",myProduct.getRoomage()));

                String fdlx = myProduct.getLoantype();
                data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"产品性质",fdlx));
                if (fdlx.equals("经营型")){
                    data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"经营场地",myProduct.getIsmanager()));
                    data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"用户类型",myProduct.getUsertype()));
                    if (myProduct.getUsertype().contains("股东")){
                        data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"占股比例",myProduct.getRatio()));
                    }
                }
                data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"房屋面积要求",myProduct.getArea()));
                data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"可做性质",myProduct.getIsproperty()));
                break;
            case "2":
                data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"车年限",myProduct.getMaxcarage()));
                data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"车公里数",myProduct.getCardistance()));
                break;
            default:
                break;
        }
        data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"最高额度",myProduct.getCount()));
        data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"月利率范围",myProduct.getRate()+"厘~"+myProduct.getMaxrate()+"厘"));
        data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"还款方式",myProduct.getPaytype()));
        data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"还款周期",myProduct.getPaytime()));
        data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"可做车牌",myProduct.getAddress()));
        data.add(new ProductDetailMultiItem(ProductDetailMultiItem.BUTTON_ONE));
        //data.add(new ProductDetailMultiItem(ProductDetailMultiItem.TEXT_KEY_VALUE,"所需材料",myProduct.getReserve()));
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        //删除
        delete(myProduct.getId());
    }


    private void delete(int id){
        Api.getDefault().delroomproduct(KBApplication.token,KBApplication.userid,id)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        ToastUtil.showSuccess("删除成功");
                        EventData eventData = new EventData(Action.EVENT_TYPE_MY_PRODUCT);
                        EventBus.getDefault().post(eventData);
                        finish();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

}
