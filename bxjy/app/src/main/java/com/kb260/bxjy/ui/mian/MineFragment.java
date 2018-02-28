package com.kb260.bxjy.ui.mian;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.kb260.bxjy.R;
import com.kb260.bxjy.api.Api;
import com.kb260.bxjy.api.RxHelper;
import com.kb260.bxjy.api.RxSubscriber;
import com.kb260.bxjy.app.KbApplication;
import com.kb260.bxjy.model.EventData;
import com.kb260.bxjy.model.HomeMultiItem;
import com.kb260.bxjy.model.entity.LiveRecommendedData;
import com.kb260.bxjy.model.entity.PersonInfoData;
import com.kb260.bxjy.model.entity.UserData;
import com.kb260.bxjy.ui.base.BaseFragment;
import com.kb260.bxjy.ui.mine.MessageActivity;
import com.kb260.bxjy.ui.mine.MyClassActivity;
import com.kb260.bxjy.ui.mine.MyCollectActivity;
import com.kb260.bxjy.ui.mine.PersonalIfmActivity;
import com.kb260.bxjy.ui.mine.RemindActivity;
import com.kb260.bxjy.ui.mine.SetActivity;
import com.kb260.bxjy.ui.mine.WalletActivity;
import com.kb260.bxjy.utils.Action;
import com.kb260.bxjy.utils.GlideUtils;
import com.kb260.bxjy.utils.ToastUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author  KB260
 * Created on  2018/1/31
 */

public class MineFragment extends BaseFragment{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.f_mine_tvName)
    TextView tvName;
    @BindView(R.id.f_mine_civIcon)
    CircleImageView civIcon;

    @Override
    protected int getLayoutResource() {
        return R.layout.f_mine;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        toolbar.setTitle(R.string.mine_toolbarLeft);
        getUserData();
    }

    //个人信息
    @OnClick(R.id.f_mine_llPersonalIfm)
    public void personalIfm(){
        PersonalIfmActivity.start(getContext());
    }

    //设置
    @OnClick(R.id.f_mine_ivSet)
    public void set(){
        SetActivity.start(getContext());
    }

    //我的消息
    @OnClick(R.id.f_mine_ivMessage)
    public void message(){
        MessageActivity.start(getContext());
    }

    //我的收藏
    @OnClick(R.id.f_mine_llMyCollect)
    public void myCollect(){
        MyCollectActivity.start(getContext());
    }

    //钱包管理
    @OnClick(R.id.f_mine_llWallet)
    public void wallet(){
        WalletActivity.start(getContext());
    }

    //我的课程
    @OnClick(R.id.f_mine_llMyClass)
    public void myClass(){
        MyClassActivity.start(getContext());
    }

    //开播提醒
    @OnClick(R.id.f_mine_llLaunchReminder)
    public void launchReminder(){
        RemindActivity.start(getContext());
    }


    private void getUserData() {
        Api.getDefault().personInfo(KbApplication.token)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<PersonInfoData>(getContext()) {
                    @Override
                    protected void success(PersonInfoData list) {
                        if (list!=null){
                            tvName.setText(list.getName());
                            GlideUtils.showImgHead(getContext(),list.getHeadImg(),civIcon,R.mipmap.ic_launcher_round);
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showShout(msg);
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventData event) {
        switch (event.getType()){
            case Action.EVENT_TYPE_USER:
                getUserData();
                break;
            default:
                break;
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
