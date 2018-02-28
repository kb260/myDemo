package com.kb260.gxdk.view.me;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.adapter.MyCharacterApplicationLogLogAdapter;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.ApplicationLog;
import com.kb260.gxdk.entity.ApplicationLogNew;
import com.kb260.gxdk.entity.CharacterLogMultiItem;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import butterknife.BindView;

/**
 * @author KB260
 *         Created on  2017/11/28
 */
public class CharacterApplicationLogLogActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_characterApplicationLogLog_rv)
    RecyclerView rv;

    MyCharacterApplicationLogLogAdapter adapter;
    List<CharacterLogMultiItem> data;

    public static void start(Context context) {
        Intent intent = new Intent(context, CharacterApplicationLogLogActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_character_application_log_log;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initRv();
        initData();
    }

    private void initData() {
        Api.getDefault().record(KBApplication.token, KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<ApplicationLogNew>(this) {
                    @Override
                    protected void success(ApplicationLogNew list) {
                        data.clear();
                        if (list != null&& list.getRecord()!= null && list.getRecord().size() > 0 ) {
                            //Collections.reverse(list.getRecord());
                            for (ApplicationLog applicationLog : list.getRecord()){
                                switch (applicationLog.getStatus()) {
                                    case "1":
                                        data.add(new CharacterLogMultiItem(CharacterLogMultiItem.TYPE_DLS,applicationLog));
                                        break;
                                    case "2":
                                        data.add(new CharacterLogMultiItem(CharacterLogMultiItem.TYPE_YHJL,applicationLog));
                                        break;
                                    default:
                                        break;
                                }
                            }
                            adapter.setNewData(data);
                        } else {
                            adapter.setEmptyView(R.layout.empty_view, (ViewGroup) rv.getParent());
                        }

                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText("申请记录");
        initThisToolbarHaveBack(toolbar, this);
    }

    private void initRv() {
        data = new ArrayList<>();
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyCharacterApplicationLogLogAdapter(data);

        rv.setAdapter(adapter);
    }
}
