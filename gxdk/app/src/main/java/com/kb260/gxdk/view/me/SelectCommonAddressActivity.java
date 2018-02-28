package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.SelectCommonAddressAdapter;
import com.kb260.gxdk.utils.DividerItemDecoration;
import com.kb260.gxdk.view.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 */
public class SelectCommonAddressActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.a_selectCommonAddress_rv)
    RecyclerView rv;

    SelectCommonAddressAdapter adapter;
    List<String> data;


    public static void start(Context context){
        Intent intent = new Intent(context,SelectCommonAddressActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_common_address;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
    }

    private void initRv() {
        data = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this,R.drawable.divider)));

        adapter = new SelectCommonAddressAdapter(data);
        rv.setAdapter(adapter);

        adapter.setEmptyView(R.layout.empty_no_address, (ViewGroup) rv.getParent());
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_selectCommonAddress_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    //添加地址
    @OnClick(R.id.a_selectCommonAddress_btAddAddress)
    public void addCommonAddress(){
        AddAddressActivity.start(this);
    }
}
