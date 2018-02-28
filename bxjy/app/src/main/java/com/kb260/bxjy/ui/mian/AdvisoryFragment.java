package com.kb260.bxjy.ui.mian;

import android.support.v7.widget.Toolbar;

import com.kb260.bxjy.R;
import com.kb260.bxjy.ui.base.BaseFragment;

import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2018/1/31
 */

public class AdvisoryFragment extends BaseFragment{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    
    @Override
    protected int getLayoutResource() {
        return R.layout.f_advisory;
    }

    @Override
    protected void initView() {
        toolbar.setTitle(R.string.advisory_toolbarLeft);
    }
}
