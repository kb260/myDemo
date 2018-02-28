package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.model.MyProduct;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.view.base.BaseActivity;
import butterknife.BindView;
/**
 * @author  KB260
 * Created on  2017/11/15
 */
public class ProductDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_productDetail_llCarYear)
    LinearLayout llCarYear;
    @BindView(R.id.a_productDetail_llHouseAge)
    LinearLayout llHouseAge;
    @BindView(R.id.a_productDetail_llHouseType)
    LinearLayout llHouseType;
    @BindView(R.id.a_productDetail_llManType)
    LinearLayout llManType;
    @BindView(R.id.a_productDetail_llArea)
    LinearLayout llArea;
    @BindView(R.id.a_productDetail_llAreaClaim)
    LinearLayout llAreaClaim;
    @BindView(R.id.a_productDetail_llDoTheNature)
    LinearLayout llDoTheNature;
    @BindView(R.id.a_productDetail_tvType)
    TextView tvType;

    @BindView(R.id.a_productDetail_bank)
    TextView tvBank;
    @BindView(R.id.a_productDetail_branch)
    TextView tvBranch;
    @BindView(R.id.a_productDetail_day)
    TextView tvDay;
    @BindView(R.id.a_productDetail_carYear)
    TextView tvCarYear;
    @BindView(R.id.a_productDetail_houseAge)
    TextView tvHouseAge;
    @BindView(R.id.a_productDetail_manAge)
    TextView tvManAge;
    @BindView(R.id.a_productDetail_houseType)
    TextView tvHouseType;
    @BindView(R.id.a_productDetail_manType)
    TextView tvManType;
    @BindView(R.id.a_productDetail_area)
    TextView tvArea;
    @BindView(R.id.a_productDetail_areaClaim)
    TextView tvAreaClaim;
    @BindView(R.id.a_productDetail_maxMoney)
    TextView tvMaxMoney;
    @BindView(R.id.a_productDetail_rate)
    TextView tvRate;
    @BindView(R.id.a_productDetail_daTheNature)
    TextView tvDaTheNature;
    @BindView(R.id.a_productDetail_repaymentType)
    TextView tvRepaymentType;
    @BindView(R.id.a_productDetail_repaymentTime)
    TextView tvRepaymentTime;
    @BindView(R.id.a_productDetail_canDoArea)
    TextView tvCanDoArea;
    @BindView(R.id.a_productDetail_material)
    TextView tvMaterial;


    public static void start(Context context, MyProduct myProduct){
        Intent intent = new Intent(context,ProductDetailActivity.class);
        intent.putExtra(Action.PRODUCT_DATA,myProduct);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_product_detail;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
    }

    private void initIntent() {
        MyProduct myProduct = (MyProduct) getIntent().getSerializableExtra(Action.PRODUCT_DATA);
        switch (myProduct.getType()){
            case Action.PRODUCT_TYPE_HOUSE:
                llCarYear.setVisibility(View.GONE);
                llHouseAge.setVisibility(View.VISIBLE);
                llHouseType.setVisibility(View.VISIBLE);
                llManType.setVisibility(View.VISIBLE);
                llArea.setVisibility(View.VISIBLE);
                llAreaClaim.setVisibility(View.VISIBLE);
                llDoTheNature.setVisibility(View.VISIBLE);
                tvType.setText(R.string.a_productDetail_house);
                break;
            case Action.PRODUCT_TYPE_CAR:
                llCarYear.setVisibility(View.VISIBLE);
                llHouseAge.setVisibility(View.GONE);
                llHouseType.setVisibility(View.GONE);
                llManType.setVisibility(View.GONE);
                llArea.setVisibility(View.GONE);
                llAreaClaim.setVisibility(View.GONE);
                llDoTheNature.setVisibility(View.GONE);
                tvType.setText(R.string.a_productDetail_car);
                break;
            default:
                break;
        }
        updateUI(myProduct);
    }

    private void updateUI(MyProduct myProduct) {
        tvBank.setText(myProduct.getBank());
        tvBranch.setText(myProduct.getBranchname());
        tvDay.setText(myProduct.getCreditday());
        tvCarYear.setText(myProduct.getRoomage());
        tvHouseAge.setText(myProduct.getRoomage());
        tvManAge.setText(myProduct.getAge());
        tvHouseType.setText(myProduct.getPaytype());
        tvManType.setText(myProduct.getUsertype());
        tvArea.setText(myProduct.getArea());
        tvAreaClaim.setText(myProduct.getArea());
        tvMaxMoney.setText(myProduct.getCount());
        tvRate.setText(myProduct.getRate()+"厘~"+myProduct.getMaxrate()+"厘");
        tvDaTheNature.setText(myProduct.getIsproperty());
        tvRepaymentType.setText(myProduct.getPaytype());
        tvRepaymentTime.setText(myProduct.getPaytime());
        tvCanDoArea.setText(myProduct.getAddress());
        tvMaterial.setText(myProduct.getAddress());
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_productDetail_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }
}
