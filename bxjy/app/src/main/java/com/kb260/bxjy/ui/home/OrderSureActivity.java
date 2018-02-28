package com.kb260.bxjy.ui.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.kb260.bxjy.R;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Action;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/2
 */
public class OrderSureActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    /**
     *开启页面
     */
    public static void start(Context context, String id, String title,double money){
        Intent intent = new Intent(context,OrderSureActivity.class);
        intent.putExtra(Action.ID,id);
        intent.putExtra(Action.TITLE,title);
        intent.putExtra(Action.MONEY,money);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_sure;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar,this);
        toolbar.setTitle(R.string.home_orderSure_toolbar);
    }

    @OnClick(R.id.payType_ali)
    public void aliPay(){

    }

    @OnClick(R.id.payType_wx)
    public void wxPay(){

    }

    @OnClick(R.id.payType_bank)
    public void bankPay(){

    }
}
