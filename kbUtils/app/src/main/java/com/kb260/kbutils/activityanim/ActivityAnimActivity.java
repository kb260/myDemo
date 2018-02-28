package com.kb260.kbutils.activityanim;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kb260.kbutils.R;
import com.kb260.kbutils.adapter.MainAdapter;
import com.kb260.kbutils.utils.DividerItemDecoration;
import com.kb260.kbutils.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/1/9
 */
public class ActivityAnimActivity extends BaseActivity implements  BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.a_activityAnim_rv)
    RecyclerView rv;
    @BindView(R.id.a_activityAnim_bt)
    Button bt;
    MainAdapter adapter;
    List<String> data;

    /**
     *开启页面
     */
    public static void start(Context context){
        Intent intent = new Intent(context,ActivityAnimActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_anim;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
    }

    private void initRv() {
        data  = new ArrayList<>();
        data.add("使用overridePendingTransition");
        data.add("使用style定义activity切换动画");
        data.add("使用ActivityOptions代码");
        data.add("使用ActivityOptions通过style的方式");
        data.add("使用ActivityOptions组件过度动画");

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this,R.drawable.divide_deepr)));
        adapter = new MainAdapter(data);

        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        switch (position){
            case 0:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_top, R.anim.slide_in_top);
                break;
            case 1:
                Intent intent1 = new Intent(this, SecondActivity.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(this, ThirdActivity.class);
                startActivity(intent2, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case 3:
                Intent intent3 = new Intent(this, FourActivity.class);
                startActivity(intent3, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                //finishAfterTransition();
                break;
            case 4:

                break;
            default:
                break;
        }
    }

    @OnClick(R.id.a_activityAnim_bt)
    public void bt(){
        Intent intent4 = new Intent(this, FiveActivity.class);
        startActivity(intent4, ActivityOptions.makeSceneTransitionAnimation(this, bt, "shareNames").toBundle());
    }

}
