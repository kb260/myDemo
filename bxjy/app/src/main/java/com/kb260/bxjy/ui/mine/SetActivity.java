package com.kb260.bxjy.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.kb260.bxjy.R;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.ToastUtil;
import com.kb260.bxjy.weight.dialog.ConfirmDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/6
 */
public class SetActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_set_tvClear)
    TextView tvClear;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, SetActivity.class);
        context.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar, this);
        toolbar.setTitle(R.string.set);
    }

    @OnClick({R.id.a_set_llSafe,R.id.a_set_llShow,R.id.a_set_llClear,R.id.a_set_llHelp,R.id.a_set_llDevice
            ,R.id.a_set_llAboutMe,R.id.a_set_llExit})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.a_set_llSafe:
                AccountSefeActivity.start(this);
                break;
            case R.id.a_set_llShow:
                break;
            case R.id.a_set_llClear:
                showClear();
                tvClear.setText("0M");
                ToastUtil.showShout("清理成功");
                break;
            case R.id.a_set_llHelp:
                break;
            case R.id.a_set_llDevice:
                break;
            case R.id.a_set_llAboutMe:
                break;
            case R.id.a_set_llExit:
                showExit();
                break;
            default:
                break;
        }
    }

    public void showExit(){
        ConfirmDialog.newInstance(Action.DIALOG_TYPE_EXIT)
                .setMargin(50)
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }

    public void showClear(){
        ConfirmDialog.newInstance(Action.DIALOG_TYPE_CLEAR)
                .setMargin(50)
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }
}
