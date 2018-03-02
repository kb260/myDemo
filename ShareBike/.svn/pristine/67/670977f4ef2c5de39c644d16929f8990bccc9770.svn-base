package com.panda.sharebike.ui.selfinfo;

import android.os.Bundle;
import android.view.View;

import com.panda.sharebike.R;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.userguide.BookingAndUnlockQuestionActivity;
import com.panda.sharebike.ui.userguide.CarRentalFeeAndDepositActivity;
import com.panda.sharebike.ui.userguide.RegisterAndAccountActivity;
import com.panda.sharebike.ui.userguide.ReturnCarRelatedActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户指南
 */
public class userGuideActivity extends BaseActivity {


    @Override
    protected int getLayoutView() {
        return R.layout.activity_user_guide;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_regist_account, R.id.rl_order_lock, R.id.rl_rent_money, R.id.rl_return_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_regist_account:
                startActivity(RegisterAndAccountActivity.class);
                break;
            case R.id.rl_order_lock:
                startActivity(BookingAndUnlockQuestionActivity.class);
                break;
            case R.id.rl_rent_money:
                startActivity(CarRentalFeeAndDepositActivity.class);
                break;
            case R.id.rl_return_about:
                startActivity(ReturnCarRelatedActivity.class);
                break;
        }
    }
}
