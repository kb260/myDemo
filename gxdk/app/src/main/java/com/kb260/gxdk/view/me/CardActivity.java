package com.kb260.gxdk.view.me;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.daimajia.swipe.util.Attributes;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.CardAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.model.Card;
import com.kb260.gxdk.utils.DividerItemDecoration;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 *         Created on  2017/11/13
 */
public class CardActivity extends BaseActivity {
    @BindView(R.id.card_toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_card_rv)
    RecyclerView rv;
    @BindView(R.id.a_card_empty)
    LinearLayout empty;

    List<Card> datas;
    CardAdapter adapter;

    public static void start(Activity context) {
        Intent intent = new Intent(context, CardActivity.class);
        context.startActivityForResult(intent, 1);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_card;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
        initData();
    }

    private void initRv() {
        datas = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CardAdapter(datas);
        // Item Decorator:
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider)));
        adapter.setMode(Attributes.Mode.Single);
        rv.setAdapter(adapter);

        /**/
    }

    @Override
    public void initToolbar() {
        initThisToolbarHaveBack(toolbar, this);
    }

    //添加银行卡
    @OnClick(R.id.a_card_ivAddCard)
    public void addCard() {
        AddCardActivity.start(this);
    }

    private void initData() {
        Api.getDefault().selbank(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<Card>>(this) {
                    @Override
                    protected void success(List<Card> list) {
                        if (list != null && list.size() > 0) {
                            datas.clear();
                            datas.addAll(list);
                            adapter.notifyDataSetChanged();
                        } else {
                            empty.setVisibility(View.VISIBLE);
                            rv.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void delCard(int id) {
        Api.getDefault().delbank(KBApplication.token,KBApplication.userid, id)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {

                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 24){
            initData();
        }
    }
}
