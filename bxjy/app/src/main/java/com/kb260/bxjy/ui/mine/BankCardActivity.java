package com.kb260.bxjy.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.kb260.bxjy.R;
import com.kb260.bxjy.adapter.BankCardAdapter;
import com.kb260.bxjy.adapter.BannerViewHolder;
import com.kb260.bxjy.model.entity.BankCard;
import com.kb260.bxjy.ui.account.RegisterFirstActivity;
import com.kb260.bxjy.ui.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/6
 */
public class BankCardActivity extends BaseActivity {
    @BindView(R.id.a_bankCard_toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_bankCard_rv)
    RecyclerView rv;

    BankCardAdapter adapter;
    List<BankCard> data;


    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, BankCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_bank_card;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initData();
        initRv();
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.wallet_bank);
    }

    private void initRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BankCardAdapter(data);
        addTop();
        rv.setAdapter(adapter);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add(new BankCard());
        data.add(new BankCard());
        data.add(new BankCard());
        data.add(new BankCard());
    }

    private void addTop() {
        View view = getLayoutInflater().inflate(R.layout.divider10, (ViewGroup) rv.getParent(), false);
        adapter.addHeaderView(view);
    }

    //确定
    @OnClick(R.id.a_bankCard_toolbarRight)
    public void add() {
        AddBankCardActivity.start(this);
    }
}
