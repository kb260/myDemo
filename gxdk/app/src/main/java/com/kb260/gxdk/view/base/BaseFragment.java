package com.kb260.gxdk.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kb260 on 2017/9/21.
 * Email: work260@outlook.com
 */

public abstract class BaseFragment extends Fragment{
    protected View rootView;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null){
            rootView = inflater.inflate(getLayoutResource(), container, false);
        }
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    public View getMyView(){
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //获取布局文件
    protected abstract int getLayoutResource();
    //初始化view
    protected abstract void initView();
}
