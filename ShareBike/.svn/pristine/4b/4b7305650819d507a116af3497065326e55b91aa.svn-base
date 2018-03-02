package com.panda.sharebike.ui.returnbike;

import android.view.View;

import com.panda.sharebike.R;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.selfinfo.UserShareBikeActivity;

/**
 * 单车待还
 */
public class ReturnBikeActivity extends BaseActivity {

    @Override
    protected int getLayoutView() {
        return R.layout.activity_return_bike;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        setRightIcon(R.drawable.icon_share, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(UserShareBikeActivity.class);
            }
        });
    }
}
