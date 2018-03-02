package com.panda.sharebike.ui.selfinfo;

import android.os.Bundle;
import android.view.View;

import com.panda.sharebike.R;
import com.panda.sharebike.ui.base.BaseActivity;

public class UserChangeNameActivity extends BaseActivity {
    @Override
    protected int getLayoutView() {
        return R.layout.activity_change_name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }


    private void initData() {
        setTvRightColor(getResources().getColor(R.color.tv_money_color));
        setTvRight("保存", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //执行保存操作
            }
        });
    }

}
