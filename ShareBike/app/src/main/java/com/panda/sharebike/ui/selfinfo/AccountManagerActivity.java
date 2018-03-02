package com.panda.sharebike.ui.selfinfo;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.panda.sharebike.R;
import com.panda.sharebike.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AccountManagerActivity extends BaseActivity {
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_account_manager;
    }

    @OnClick({R.id.rl_password, R.id.rl_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_password:
                startActivity(new Intent(this, UpdatePasswordActivity.class));
                break;
            case R.id.rl_phone:
                startActivity(new Intent(this, UpdatePhoneActivity.class));
                break;
        }
    }
}
