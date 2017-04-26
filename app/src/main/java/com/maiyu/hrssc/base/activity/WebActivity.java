package com.maiyu.hrssc.base.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.maiyu.hrssc.R;
import com.maiyu.hrssc.base.ConstantValue;
import com.maiyu.hrssc.base.bean.DataCenter;
import com.maiyu.hrssc.base.bean.User;
import com.maiyu.hrssc.base.view.HeadView;

/**
 * 访问网页的公共activity
 *
 * @author xiongning
 *         <p/>
 *         2015-8-31
 */
public class WebActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {


    public static final int REQUEST_CODE_GO_TO_LOGIN = 1001;
    private WebView mWebView;
    private ProgressBar mProgressbar;
    private HeadView mHeadView;
    private String mTitleName;
    private String productId;
    private String url;
    private WebSettings webSettings;
    private SwipeRefreshLayout mSwipeLayout;
    private JavaScriptInterface mJSIImp;
    private String mProductShareData;
    private int mType;
    private boolean mIsShowShareGuide;
    private String mShareJSON;
    private String mShareArrayJSON;

    @Override
    public void createActivityImpl() {
        setContentView(R.layout.activity_webview);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initViews() {
        mHeadView = (HeadView) findViewById(R.id.head_view);
        mWebView = (WebView) findViewById(R.id.webview);
        mProgressbar = (ProgressBar) findViewById(R.id.ad_webview_ProgressBar);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);
        mSwipeLayout.setOnRefreshListener(this);


        webSettings = mWebView.getSettings();
        //支持javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//关键点  
        // 设置可以支持缩放 
        webSettings.setSupportZoom(false);
        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据
        webSettings.setLoadWithOverviewMode(true);

        // 设置出现缩放工具 
        webSettings.setBuiltInZoomControls(false);
        webSettings.setLoadsImagesAutomatically(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        //设置 缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setAppCacheEnabled(true);//启用localstorage本地存储api
        webSettings.setDatabaseEnabled(true);//启用html5数据库功能
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);


        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());

        User user = DataCenter.getInstance().getuser();
        mJSIImp = new JavaScriptInterface(user);

        Intent intent = getIntent();
        mType = intent.getIntExtra("type", 0);

        if (mType == ConstantValue.TYPE_ORDINARY) {// 普通url
            url = getIntent().getStringExtra("url");
            mTitleName = getIntent().getStringExtra("titleName");
            mHeadView.setTitle(mTitleName, true, false);
            mHeadView.setVisibility(View.GONE);
        } else {
            mHeadView.setTitle(mTitleName, true, false);
        }


        // mHeadView.setOnRightButtonClickListener(new OnRightButtonClickListner());
        mWebView.loadUrl(url);

       /* mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    mHeadView.setTitle(String.valueOf(msg.obj), true, false);
                }
                super.handleMessage(msg);
            }
        };*/
    }


    public class JavaScriptInterface {
        private User user;

        public JavaScriptInterface() {

        }

        public void setUser(User user) {
            this.user = user;
        }

        public JavaScriptInterface(User user) {
            this.user = user;
        }

        @JavascriptInterface
        public String getUserId() {
            String userId = String.valueOf(user.getId());
            return userId;
        }


        @JavascriptInterface
        public void closeThisActivity() {
            finish();
        }


        /**
         * 下载电子合同
         *
         * @param downloadURl
         */
        @JavascriptInterface
        public void downLoadEContract(String downloadURl) {
            Uri uri = Uri.parse(downloadURl);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }


    }


    @Override
    public void onRefresh() {
        // Log.e("xxx", Thread.currentThread().getName());  
        // UI Thread  
        mWebView.reload();
        mSwipeLayout.setRefreshing(false);


        //mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);  

    }

    @Override
    public void initData() {

    }

    @Override
    public void initOnClick(View v) {
        // TODO Auto-generated method stub

    }

    public class WebChromeClient extends android.webkit.WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressbar.setVisibility(View.GONE);
            } else {
                if (mProgressbar.getVisibility() == View.GONE) {
                    mProgressbar.setVisibility(View.VISIBLE);
                }
                mProgressbar.setProgress(newProgress);
            }

            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            // TODO Auto-generated method stub
            super.onReceivedTitle(view, title);
            // mHeadView.setTitle(title, true, false);
            /*  if ("产品购买".equals(title) || "绑卡认证".equals(title) || "收银台".equals(title)) {
                  mHeadView.setCloseTip(true);
              } else {
                  mHeadView.setCloseTip(false);
              }

              if ("收银台".equals(title)) {
                  DataCenter.getInstance().notifyHomeDataChange();
              }*/
        }

    }

    public class WebViewClient extends android.webkit.WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 拦截打电话的url，并执行打电话
            if (url.startsWith("tel:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(url));
                startActivity(intent);
            } else {
                view.loadUrl(url);
            }
            //return super.shouldOverrideUrlLoading(view, url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            // HintUitl.toastShort(WebActivity.this, "加载网页失败!");
            if (WebViewClient.ERROR_HOST_LOOKUP == errorCode || WebViewClient.ERROR_TIMEOUT == errorCode) {
                // 网络问题
                mWebView.loadUrl(" file:///android_asset/html/error.html ");
            } else {
                mWebView.loadUrl(" file:///android_asset/html/error1.html ");

            }

            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @TargetApi(Build.VERSION_CODES.M)
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if (WebViewClient.ERROR_HOST_LOOKUP == error.getErrorCode() || WebViewClient.ERROR_TIMEOUT == error.getErrorCode()) {
                // 网络问题
                mWebView.loadUrl(" file:///android_asset/html/error.html ");
            } else {
                mWebView.loadUrl(" file:///android_asset/html/error1.html ");

            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // String title = mWebView.getTitle();
            // mHeadView.setTitle(title, true, false);
           /* if ("产品购买".equals(title) || "收银台".equals(title)) {
                mHeadView.setCloseTip(true);
            } else {
                mHeadView.setCloseTip(false);
            }*/

            mSwipeLayout.setEnabled(false);


        }

    }


    /**
     * 按键响应，在WebView中查看网页时，按返回键的时候按浏览历史退回,如果不做此项处理则整个WebView返回退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {

           /* if ("提交成功".equals(mHeadView.getTitle())) {
                finish();
                return true;
            }

            if ("收银台".equals(mHeadView.getTitle())) {
                mHeadView.createDialogAndshow();
                return true;
            }
*/
            // 返回键退回
            mWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up
        // to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /* if (REQUEST_CODE_GO_TO_LOGIN == requestCode) {
            if (RESULT_OK == resultCode) {
                // 登录返回后，不会再有分享引导
                mIsShowShareGuide = false;
                // 登录后返回webview 刷新webview
                mWebView.reload();
            }
        }*/
        super.onActivityResult(requestCode, resultCode, data);
    }


}
