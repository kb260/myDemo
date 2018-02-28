package com.kb260.bxjy.ui.mine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.widget.EditText;
import com.kb260.bxjy.R;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.EventData;
import com.kb260.bxjy.ui.base.BaseActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.StringUtils;
import com.kb260.bxjy.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author  KB260
 * Created on  2018/2/5
 */
public class ChangeNicknameActivity extends BaseActivity {
    @BindView(R.id.a_changeNickname_toolbar)
    Toolbar toolbar;
    @BindView(R.id.a_changeNickname_tvNickname)
    EditText etNickname;

    String name;

    /**
     *开启页面
     */
    public static void start(Activity context){
        Intent intent = new Intent(context,ChangeNicknameActivity.class);
        context.startActivityForResult(intent, Action.START_ACTIVITY_CHANGE_NICKNAME);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_nickname;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        etNickname.setOnTouchListener((v, event) -> {
            // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
            Drawable drawable = etNickname.getCompoundDrawables()[2];
            //如果右边没有图片，不再处理
            if (drawable == null){
                return false;
            }
            //如果不是按下事件，不再处理
            if (event.getAction() != MotionEvent.ACTION_UP){
                return false;
            }
            if (event.getX() > etNickname.getWidth()
                    - etNickname.getPaddingRight()
                    - drawable.getIntrinsicWidth()){
                etNickname.setText("");
            }
            return false;
        });
    }

    @Override
    public void initToolbar() {
        super.initToolbar();
        initThisToolbarHaveBack(toolbar,this);
        toolbar.setTitle(R.string.changeNickname_toolbar);
    }

    //确定
    @OnClick(R.id.a_changeNickname_toolbarRight)
    public void sure(){
        if (checkInput()){
            Api.getDefault().resetName(KbApplication.token,name)
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<String>(this) {
                        @Override
                        protected void success(String list) {
                            EventBus.getDefault().post(new EventData(Action.EVENT_TYPE_USER));
                            ToastUtil.showShout("修改成功");
                            Intent intent = new Intent();
                            intent.putExtra(Action.START_ACTIVITY_CHANGE_NICKNAME_KEY,name);
                            setResult(Action.START_ACTIVITY_CHANGE_NICKNAME,intent);
                            finish();
                        }

                        @Override
                        protected void error(String msg) {
                            Logger.d(msg);
                            ToastUtil.showShout(msg);
                        }
                    });

        }
    }

    private boolean checkInput(){
        name = etNickname.getText().toString();
        if (StringUtils.isEmpty(name)){
            ToastUtil.showShout("请输入昵称");
            return false;
        }
        return true;
    }

}
