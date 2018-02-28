package com.kb260.gxdk.view.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.GuideAdapter;
import com.kb260.gxdk.view.account.LoginActivity;
import com.kb260.gxdk.view.base.BaseActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

/**
 * @author  KB260
 * Created on  2017/11/20
 * 引导页
 */
public class GuideActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.a_guide_vp)
    ViewPager vp;
    @BindView(R.id.a_guide_ci)
    CircleIndicator ci;

    GuideAdapter guideAdapter;
    Button bt;
    List<View> list;

    /**
     * 开启页面
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, GuideActivity.class);
        context.startActivity(intent);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_guide,null,false);
        View view2 = inflater.inflate(R.layout.item_guide2,null,false);
        View view3 = inflater.inflate(R.layout.item_guide3,null,false);
        bt = view3.findViewById(R.id.item_guide_bt);
        bt.setOnClickListener(this);

        list = new ArrayList<>();
        list.add(view);
        list.add(view2);
        list.add(view3);
        guideAdapter = new GuideAdapter(list);

        vp.setAdapter(guideAdapter);
        ci.setViewPager(vp);
        guideAdapter.registerDataSetObserver(ci.getDataSetObserver());
    }

    @Override
    public void onClick(View view) {
        MainActivity.start(this);
        finish();
    }
}
