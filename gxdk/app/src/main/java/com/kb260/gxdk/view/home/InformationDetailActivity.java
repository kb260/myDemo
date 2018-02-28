package com.kb260.gxdk.view.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.ImageLoader;
import com.kb260.gxdk.view.base.BaseActivity;
import butterknife.BindView;

/**
 * @author  KB260
 * @date  2017/10/18
 */
public class InformationDetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_informationDetail_tvTime)
    TextView tvTime;
    @BindView(R.id.a_informationDetail_tvContent)
    TextView tvContent;
    @BindView(R.id.a_informationDetail_iv)
    ImageView iv;

    public static void start(Context context, String title,String content,String time,String imgUrl){
        Intent intent = new Intent(context,InformationDetailActivity.class);
        intent.putExtra(Action.TOOLBAR_TITLE,title);
        intent.putExtra(Action.CONTENT,content);
        intent.putExtra(Action.TIME,time);
        intent.putExtra(Action.IMAGE,imgUrl);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_information_detail;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
    }

    private void initIntent() {
    }

    @Override
    public void initToolbar() {
        Intent intent = getIntent();
        toolbarTitle.setText(intent.getStringExtra(Action.TOOLBAR_TITLE));
        initThisToolbarHaveBack(toolbar,this);
        String content = intent.getStringExtra(Action.CONTENT);
        String time = intent.getStringExtra(Action.TIME);
        String imgUrl = intent.getStringExtra(Action.IMAGE);
        tvTime.setText(time);
        tvContent.setText(content);
        ImageLoader.showImage(iv,imgUrl);

    }
}
