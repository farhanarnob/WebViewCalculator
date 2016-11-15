package com.example.farha.webviewcalculator;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    MainActivity wa;
    String displayValue;
    CalculationUtilities calculationUtilities = new CalculationUtilities();


    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        wa = (MainActivity) c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void button_number_operator(final String value) {
        Toast.makeText(wa, value, Toast.LENGTH_SHORT).show();
        final String javaScriptCode = "screenValue('" + value + "')";
        wa.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                wa.webView.loadUrl("javascript:"+javaScriptCode);
            }
        });
    }

    @JavascriptInterface
    public void calculation(final String value) {

        displayValue = value;
        displayValue = calculationUtilities.calculate(displayValue);
        Toast.makeText(wa, displayValue, Toast.LENGTH_SHORT).show();
        final String javaScriptCode = "screenValueResult('" + displayValue + "')";
        wa.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                wa.webView.loadUrl("javascript:" + javaScriptCode);
            }
        });
    }
}