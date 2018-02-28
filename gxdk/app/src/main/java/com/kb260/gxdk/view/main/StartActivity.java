package com.kb260.gxdk.view.main;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.kb260.gxdk.R;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.SPUtils;
import com.kb260.gxdk.view.account.LoginActivity;
import com.kb260.gxdk.view.base.BaseActivity;

/**
 * @author  KB260
 * Created on  2017/11/20
 */
public class StartActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_start;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        boolean isFirst = SPUtils.getInstance().getBoolean(Action.SP_IS_FIRST,true);
        if (isFirst){
            SPUtils.getInstance().put(Action.SP_IS_FIRST,false);
            GuideActivity.start(this);
            finish();
        }else {
            new Handler().postDelayed(() -> {
                MainActivity.start(StartActivity.this);
                finish();
            },1000);
        }


    }
}
