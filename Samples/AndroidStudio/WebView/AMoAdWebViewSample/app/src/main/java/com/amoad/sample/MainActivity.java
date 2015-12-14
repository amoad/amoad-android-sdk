package com.amoad.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.amoad.AMoAdView;
import com.amoad.AMoAdWeb;
import com.amoad.AdCallback;

public class MainActivity extends Activity {
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO AMoAdWebの利用を開始する
        AMoAdWeb.prepareAd(this);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO 広告ULRの場合遷移行うため
                if (AMoAdWeb.shouldOverrideUrlLoading(view, url)) {
                    return true;
                }
                return false;
            }
        });

        // 文字列からロードする(Android 5.0以降はIDFA連携とダイレクトストアができないため、ウェブからロードをお勧めします。)
        // loadHtmlText();

        // ファイルからロードする(Android 5.0以降はIDFA連携とダイレクトストアができないため、ウェブからロードをお勧めします。)
        // loadLocalFile();

        // ウェブからロードする
        loadHttpUrl();
    }

    private void loadHtmlText() {
        // TODO 管理画面から取得したタグを設定してください。
        StringBuilder html = new StringBuilder();
        html.append("<div class='amoad_frame sid_xxxxxxxxxx ...'></div>");
        html.append("<script src='http://.....js' ...'></script>");
        // TODO 「 http://example.com/ 」を適切なURLに置き換えてください。
        webView.loadDataWithBaseURL("http://example.com", html.toString(), "text/html", "utf-8", null);
    }

    private void loadLocalFile() {
        // TODO ad.htmlに管理画面から取得したアドタグを設定してください
        mWebView.loadUrl("file:///android_asset/ad.html");
    }

    private void loadHttpUrl() {
        // TODO ad.htmlに管理画面から取得したアドタグを設定してください
        mWebView.loadUrl("http://..../ad.html");
    }
}
