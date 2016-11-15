package com.example.farha.webviewcalculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    WebSettings webSettings;
    ConnectivityManager cm;
    Button button;

    @SuppressLint("JavascriptInterface")
    @Override
    @JavascriptInterface
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.web_view);
        button = (Button) findViewById(R.id.goto_local);
        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() == null) {
            button.setVisibility(View.GONE);
            setTitle("Calculator from Local Resource");
            webView.loadUrl("file:///android_asset/local_web_calculator.html");
        } else {
            setTitle("Calculator from Online");
            webView.loadUrl("http://172.16.7.26/android_test/index.html");
        }
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebAppInterface webAppInterface = new WebAppInterface(this);
        webView.addJavascriptInterface(webAppInterface,"Android");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.GONE);
                setTitle("Calculator from Local Resource");
                webView.loadUrl("file:///android_asset/local_web_calculator.html");
            }
        });
    }

}
