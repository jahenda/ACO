package com.jahendamercy.com.views.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.jahendamercy.com.MyWebViewClient;
import com.jahendamercy.com.R;

@SuppressWarnings("deprecation")
public class CommunicationFragment extends Activity {

    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_communication);

        this.webView = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        MyWebViewClient webViewClient = new MyWebViewClient(this);
        webView.setWebViewClient(webViewClient);

        webView.loadUrl("https://www.chances360.com");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


}
