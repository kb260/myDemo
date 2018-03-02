
package com.panda.sharebike.ui.selfinfo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.panda.sharebike.R;
import com.panda.sharebike.api.Api;
import com.panda.sharebike.ui.base.BaseActivity;

/**
 * chongzhixieyiccccccchowsw eswjf hwe
 */
public class UserProtocolActivity extends BaseActivity {

    private ProgressDialog mProgressDlg;

    @Override
    protected void setUpView() {
        super.setUpView();
        initWebView();
    }

    @Override
    protected int getLayoutView() {
        return R.layout.activity_user_protocol;
    }

    private void initWebView() {
        final WebView mWebView = (WebView) findViewById(R.id.mweb_view);
        mProgressDlg = new ProgressDialog(this);
        mProgressDlg.setMessage("loading...");
        mWebView.loadUrl(Api.BASE_URL + "qbike/kqweb/user.html");
        //mWebView.loadUrl("https://www.baidu.com/");
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);//支持javascript
        settings.setUseWideViewPort(true);//适配屏幕
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);//支持放大缩小
        settings.setDisplayZoomControls(false);//隐藏放大缩小的按钮
        settings.setDomStorageEnabled(true);//支持Html5标签
        //启用js功能


        mWebView.setWebViewClient(new WebViewClient() {

                                      // 不在 onPageStart() 中去设置是因为设置完以后又loadUrl(url),之前设定的值就无效了
                                      // 当然,在 onPageFinished() 设置的话也得H5中在document.ready()之后才能去获取
                                      // 或者也可以考虑在 WebChromeClient 的 onProgressChanged() 方法中作设定

                                      @Override
                                      public void onPageFinished(WebView view, String url) {
                                          //  LogUtils.d("footTest", "onPageFinished " + url);
                                          // view.loadUrl("www.baidu.com");
                                      }
                                  }
        );
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 需要添加 mWv.canGoBack(),不然当返回到初始页面时,可能无法继续通过返回键关闭页面
                if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                    mWebView.goBack();
                    return true;
                }
                return false;
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                // title 是获取到的网页title,可以将之设置为webView所在页面的标题
                com.panda.sharebike.ui.selfinfo.UserProtocolActivity.this.setTitle(title);
            }

        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //更新进度条示数

                //这种方式我没看到效果...
                //MainActivity.this.setProgress(newProgress);

                //使用控件ProgressDialog来显示进度
                //但记得这种方式需要在error发生时也进行取消
                if (newProgress <= 90) {
                    mProgressDlg.setProgress(newProgress);
                } else {
                    mProgressDlg.dismiss();
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                // 加载某些网站的时候会报:ERR_CONNECTION_REFUSED,因此需要在这里取消进度条的显示
                Toast.makeText(com.panda.sharebike.ui.selfinfo.UserProtocolActivity.this, "error", Toast.LENGTH_SHORT).show();
                if (mProgressDlg.isShowing()) {
                    mProgressDlg.dismiss();
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (!mProgressDlg.isShowing()) {
                    mProgressDlg.show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (mProgressDlg.isShowing()) {
                    mProgressDlg.dismiss();
                }
            }
        });
//
    }

}