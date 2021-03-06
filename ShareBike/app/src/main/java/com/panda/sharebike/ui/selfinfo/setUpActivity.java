package com.panda.sharebike.ui.selfinfo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.EmptyUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.panda.sharebike.Config;
import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.api.HttpResult;
import com.panda.sharebike.api.Nsubscriber;
import com.panda.sharebike.ui.Iinterface.SubscriberListener;
import com.panda.sharebike.ui.base.BaseActivity;
import com.panda.sharebike.ui.login.LoginByPhoneActivity;
import com.panda.sharebike.ui.userguide.AboutUsActivity;
import com.panda.sharebike.ui.userguide.RechargeInstructionsActivity;

import butterknife.BindView;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 设置
 */
public class setUpActivity extends BaseActivity {

    @BindView(R.id.btn_log_off)
    Button btnLogOff;
    private boolean state = false;
    @Override
    protected int getLayoutView() {
        return R.layout.activity_set_up;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        state = getIntent().getBooleanExtra("state", false);
        if (EmptyUtils.isNotEmpty(state)) {
            if (state) {
                btnLogOff.setVisibility(View.VISIBLE);
            } else {
                btnLogOff.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 退出登录
     */
    private void loginOut() {
        Api.getInstance().getDefault().getLogout(Config.TOKEN)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Nsubscriber<HttpResult>(new SubscriberListener<HttpResult>() {
                    @Override
                    public void onSuccess(HttpResult model) {
                        if (model.getCode() == 200) {
                            //
//                            SharedPreferences sp = getSharedPreferences(N.SHARE_TOKEN, MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sp.edit();
//                            editor.putString(N.SHARE_TOKEN_KEY, null);
//                            editor.clear();
//                            editor.commit();//提交token，全局需要，这里是

                            Intent intent = new Intent(setUpActivity.this, LoginByPhoneActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            setUpActivity.this.finish();
                        }
                        if (model.getCode() == 401) {
                            ToastUtils.showShort("你尚未登录");
                        }

                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }, this, false));
    }


    @OnClick({R.id.btn_log_off, R.id.set_up_user, R.id.set_up_recharge, R.id.set_up_intruction, R.id.set_up_about_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_up_user:
                startActivity(UserProtocolActivity.class);
                break;
            case R.id.set_up_recharge:
                startActivity(RechargeProtocolActivity.class);
                break;
            case R.id.set_up_intruction:
                startActivity(RechargeInstructionsActivity.class);
                break;
            case R.id.set_up_about_us:
                startActivity(AboutUsActivity.class);
                break;
            case R.id.btn_log_off:
                loginOut();
                break;
        }
    }
}
