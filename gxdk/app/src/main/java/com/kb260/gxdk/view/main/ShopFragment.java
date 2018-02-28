package com.kb260.gxdk.view.main;

import android.net.http.SslError;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.kb260.gxdk.R;
import com.kb260.gxdk.api.Api;
import com.kb260.gxdk.api.RxHelper;
import com.kb260.gxdk.api.RxSubscriber;
import com.kb260.gxdk.app.KBApplication;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.base.BaseFragment;
import com.kb260.gxdk.view.widget.dialog.BaseKBDialog;
import com.kb260.gxdk.view.widget.dialog.KBDialog;
import com.kb260.gxdk.view.widget.dialog.KBViewConvertListener;
import com.kb260.gxdk.view.widget.dialog.ViewHolder;
import com.orhanobut.logger.Logger;
import butterknife.BindView;

/**
 * @author  KB260
 * Created on  2017/11/22
 */
public class ShopFragment extends BaseFragment{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;

    @BindView(R.id.f_shop_webView)
    WebView webView;
    @BindView(R.id.f_shop_pb)
    ProgressBar progressBar;

    @Override
    protected int getLayoutResource() {
        return R.layout.f_shop;
    }

    @Override
    protected void initView() {
        initToolbar();
        initWebView();
        initData();
    }

    public void initToolbar() {
        toolbar.setNavigationOnClickListener(v -> {
            if (webView.canGoBack()) {
                webView.goBack();
            }
        });
        toolbarTitle.setText("商城");
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a();
            }
        });
    }


    private void initData(){
        Api.getDefault().selshop(KBApplication.token,KBApplication.userid)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<String>(getContext()) {
                    @Override
                    protected void success(String list) {
                        webView.loadUrl(list);
                    }

                    @Override
                    protected void error(String msg) {
                        Logger.d(msg);
                        ToastUtil.showError(msg);
                    }
                });
    }

    private void initWebView() {
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        webSettings.setJavaScriptEnabled(true);     // enable navigator.geolocation

        /*webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");     // enable Web Storage: localStorage, sessionStorage
        webSettings.setDomStorageEnabled(true);
        webView.requestFocus();
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);*/

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed();//接受证书

                //
                // (Message msg); 其他处理
                // 这行代码一定加上否则效果不会出现
                webView.getSettings().setJavaScriptEnabled(true);
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //Logger.d(newProgress);
                progressBar.setProgress(newProgress);
                if (newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                }
            }

        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //清除网页访问留下的缓存
        //由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序.
        if (webView !=null){
            webView.clearCache(true);

            //清除当前webview访问的历史记录
            //只会webview访问历史记录里的所有记录除了当前访问记录
            webView.clearHistory();

            //这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
            webView.clearFormData();
        }
    }

    public void a(){
        if (webView.canGoBack()) {
            webView.goBack();
        }else {
            showExit();
        }
    }

    public void showExit(){
        KBDialog.init()
                .setLayoutId(R.layout.confirm_layout)
                .setConvertListener(new KBViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseKBDialog dialog) {
                        holder.setVisibility(R.id.message, View.GONE);
                        holder.setText(R.id.title, "确定退出?");
                        holder.setOnClickListener(R.id.ok, v -> {
                            dialog.dismiss();
                            KBApplication.exitAppList();
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
