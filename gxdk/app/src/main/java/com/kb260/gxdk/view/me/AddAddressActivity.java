package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.kb260.gxdk.R;
import com.kb260.gxdk.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * @date  2017/10/25
 */
public class AddAddressActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    public static void start(Context context){
        Intent intent = new Intent(context,AddAddressActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_address;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_addAddress_toolbar);
        initThisToolbarHaveBack(toolbar,this);
    }

    //完成
    @OnClick(R.id.a_addAddress_ok)
    public void ok(){
        finish();
    }
}
