package com.example.farha.webviewcalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;
    @SuppressLint("JavascriptInterface")
    @Override
    @JavascriptInterface
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.web_view);
        webView.loadUrl("file:///android_asset/local_web_calculator.html");
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebAppInterface webAppInterface = new WebAppInterface(this);
        webView.addJavascriptInterface(webAppInterface,"Android");
    }

}
