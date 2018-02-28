package com.kb260.gxdk.view.base;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.kb260.gxdk.utils.Action;
import com.kb260.gxdk.utils.ToastUtil;
import com.kb260.gxdk.view.main.MainActivity;
import com.orhanobut.logger.Logger;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author KB260
 *         Created on  2017/10/30
 */
public class WebViewDataActivity extends BaseActivity {
    @BindView(R.id.webView_toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView_toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.webView_toolbarTitleRight)
    TextView toolbarTitleRight;
    @BindView(R.id.a_webView_webView)
    WebView webView;
    @BindView(R.id.a_webView_pb)
    ProgressBar progressBar;
    String url, title;

    /**
     * 开启页面
     */
    public static void start(Context context, String url, String title) {
        Intent intent = new Intent(context, WebViewDataActivity.class);
        intent.putExtra(Action.URL, url);
        intent.putExtra(Action.TOOLBAR_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        Intent intent = getIntent();
        url = intent.getStringExtra(Action.URL);
        title = intent.getStringExtra(Action.TOOLBAR_TITLE);
        toolbarTitle.setText(title);
        initWebView();
    }

    @Override
    public void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.start(WebViewDataActivity.this);
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


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
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

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //Logger.d(newProgress);
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }

        });
        webView.loadData(url, "text/html", "UTF-8");
    }



    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }*/


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清除网页访问留下的缓存
        //由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序.
        // webView.clearCache(true);

        //清除当前webview访问的历史记录
        //只会webview访问历史记录里的所有记录除了当前访问记录
        webView.clearHistory();

        //这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
        webView.clearFormData();
    }

}
