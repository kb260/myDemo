package com.kb260.gxdk.view.me;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.utils.Contact;
import com.kb260.gxdk.utils.StringUtils;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseActivity;
import com.orhanobut.logger.Logger;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2017/11/1
 */
public class ChangeNicknameActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.a_me_meDetail_nickname_et)
    EditText etNickname;

    String nickname;

    public static void start(Activity context,String name) {
        Intent intent = new Intent(context, ChangeNicknameActivity.class);
        intent.putExtra(Contact.NICKNAME,name);
        context.startActivityForResult(intent, Contact.CHANGE_NICKNAME);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_nickname;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        initIntent();
    }

    private void initIntent() {
        Intent intent = getIntent();
        String name = intent.getStringExtra(Contact.NICKNAME);
        etNickname.setText(name);
        etNickname.setSelection(name.length());//将光标移至文字末尾
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText(R.string.a_me_meDetail_nickname_toolbar);
        initThisToolbarHaveBack(toolbar, this);
    }

    //保存
    @OnClick(R.id.a_me_meDetail_nickname_btSave)
    public void save() {
        if (getInput()){
            return;
        }
        Api.getDefault().upperon(KBApplication.token,KBApplication.userid,nickname,null,null)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(this) {
                    @Override
                    protected void success(String list) {
                        Intent intent = new Intent();
                        intent.putExtra(Contact.NICKNAME,nickname);
                        setResult(Contact.CHANGE_NICKNAME,intent);
                        finish();
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    //保存
    @OnClick(R.id.a_me_meDetail_nickname_ivClear)
    public void clear() {
        etNickname.setText("");
    }

    private boolean getInput() {
        nickname = etNickname.getText().toString().trim();
        if (StringUtils.isEmpty(nickname)) {
            ToastUtil.showInfo("请输入昵称！");
            return true;
        }
        return false;
    }

}
