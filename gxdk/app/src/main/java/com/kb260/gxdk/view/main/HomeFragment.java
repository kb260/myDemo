package com.kb260.gxdk.view.main;

import android.content.Context;
import android.view.View;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.entity.BannerData;
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.GlideImageLoader;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.account.LoginActivity;
import com.kb260.gxdk.view.base.BaseFragment;
import com.kb260.gxdk.view.base.FindHouseActivity;
import com.kb260.gxdk.view.base.WebViewDataActivity;
import com.kb260.gxdk.view.gradmonad.FirstDraftProgramActivity;
import com.kb260.gxdk.view.gradmonad.OrderDetailActivity;
import com.kb260.gxdk.view.home.BorrowingTipsActivity;
import com.kb260.gxdk.view.home.CarApplicationFirstActivity;
import com.kb260.gxdk.view.home.CreditApplicationFirstActivity;
import com.kb260.gxdk.view.home.EvaluationActivity;
import com.kb260.gxdk.view.home.FirstApplicationLoanActivity;
import com.kb260.gxdk.view.home.NewsActivity;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author  KB260
 * Created on  2017/12/11
 */
public class HomeFragment extends BaseFragment implements OnBannerListener {
    Context context;
    @BindView(R.id.f_home_banner)
    Banner banner;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.f_home;
    }

    @Override
    protected void initView() {
        initBanner();
    }


    //房贷申请
    @OnClick(R.id.f_home_ivFdsq)
    public void ivFdsq(){
        if (KBApplication.userid != 0){
            FirstApplicationLoanActivity.start(context);
        }else {
            showPhone();
        }
    }

    //车贷申请
    @OnClick(R.id.f_home_ivCdsq)
    public void ivCdsq(){
        if (KBApplication.userid != 0){
            CarApplicationFirstActivity.start(context);
        }else {
            showPhone();
        }
    }

    //信贷申请
    @OnClick(R.id.f_home_ivXdsq)
    public void ivXdsq(){
        if (KBApplication.userid != 0){
            queryIsCan(Action.KIND_XD);
        }else {
            showPhone();
        }
    }

    //敬请期待
    @OnClick(R.id.f_home_ivJqqd)
    public void ivJqqd(){
       // WebViewDataActivity.start(getContext(),"","sddf");
        //FirstDraftProgramActivity.start(getActivity(),"3",2,"方式的广泛");
        /*if (KBApplication.userid != 0){
            queryIsCan(Action.KIND_DZD);
        }else {
            showPhone();
        }*/
    }

    //评估
    @OnClick(R.id.f_home_btPg)
    public void pg(){
        if (KBApplication.userid != 0){
            EvaluationActivity.start(context);
        }else {
            showPhone();
        }
    }

    //借款小窍门
    @OnClick(R.id.f_home_llJgxqm)
    public void jg(){
        if (KBApplication.userid != 0){
            BorrowingTipsActivity.start(context,"小窍门");
        }else {
            showPhone();
        }
    }

    //新闻资讯
    @OnClick(R.id.f_home_llXwzx)
    public void xw(){
        if (KBApplication.userid != 0){
            NewsActivity.start(context,"新闻资讯");
        }else {
            showPhone();
        }
    }

    private void initBanner() {
        List<String> bannerData = new ArrayList<>();
        banner.setImages(bannerData)
                .setImageLoader(new GlideImageLoader())
                .setOnBannerListener(HomeFragment.this);
        Api.getDefault().selroasting()
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<BannerData>>(getContext()) {
                    @Override
                    protected void success(List<BannerData> list) {
                        if (list != null){
                            for (BannerData bd : list){
                                bannerData.add(bd.getPicture());
                            }
                            banner.setImages(bannerData)
                            .start();
                        }
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void queryIsCan(int kind) {
        Api.getDefault().selsiroomcar(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(getContext()) {
                    @Override
                    protected void success(String list) {
                        if (list != null){
                            if (!list.equals("0")){
                                CreditApplicationFirstActivity.start(context,list, kind);
                            }else {
                                //CreditApplicationFirstActivity.start(context,list);
                                ToastUtil.showInfo("需要申请过房屋服务或车辆服务才能信用咨询！");
                            }
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
    public void OnBannerClick(int i) {
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (banner != null){
            if (hidden){
                banner.stopAutoPlay();
            }else {
                banner.startAutoPlay();
            }
        }
    }

    private void showPhone(){
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setVisibility(R.id.message, View.GONE);
                        holder.setText(R.id.title, "登录后才能查看更多！");
                        holder.setText(R.id.ok, "去登录");
                        holder.setOnClickListener(R.id.ok, v -> {
                            dialog.dismiss();
                            LoginActivity.start(getContext());
                            getActivity().finish();
                        });
                        holder.setOnClickListener(R.id.cancel, v -> {
                            dialog.dismiss();
                        });
                    }
                })
                .setOutCancel(false)
                .setMargin(60)
                .show(getFragmentManager());
    }
}
