package com.panda.sharebike.ui.selfinfo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.model.MessageEvent;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.util.setEditTextInhibitInputSpace;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 修改昵称
 */
public class UserChangeNickNameActivity extends BaseActivity {

    @BindView(R.id.et_change_name_user)
    EditText etChangeNameUser;

    @Override
    protected int getLayoutView() {
        return R.layout.activity_user_change_nick_name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initData() {
        setEditTextInhibitInputSpace.setEditTextInhibitInputSpace(etChangeNameUser);
        setEditTextInhibitInputSpace.setEditTextInhibitInputSpeChat(etChangeNameUser);
        setTvRightColor(getResources().getColor(R.color.tv_money_color));
        setTvRight("保存", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //执行保存操作
                if (TextUtils.isEmpty(etChangeNameUser.getText().toString()) || etChangeNameUser.getText().length() < 0) {
                    ToastUtils.showShort("请输入昵称");
                } else {
                    getHttpNickname(etChangeNameUser.getText().toString());
                }
            }
        });
    }

    /**
     * 修改昵称
     */
    private void getHttpNickname(String nickName) {
        Api.getInstance().getDefault().getNickName(Config.TOKEN, nickName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult>(new SubscriberListener<HttpResult>() {
                    @Override
                    public void onSuccess(HttpResult model) {
                        if (501 == model.getCode()) {
                            ToastUtils.showShort(model.getMsg());
                            return;
                        }
                        if (model.isOk()) {
                            EventBus.getDefault().post(new MessageEvent("nickNameChangeSuccess"));
                            UserChangeNickNameActivity.this.finish();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }, this, true));
    }
}
