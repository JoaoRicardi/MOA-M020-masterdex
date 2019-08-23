package com.example.masterdex.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.masterdex.R;

public class QuizActivity extends AppCompatActivity {
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        webView = findViewById(R.id.webview);

        webView.setWebViewClient(new WebViewClient());




        WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);




        webView.loadUrl("https://jrmoore117.github.io/Whos-That-Pokemon-Quiz/");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
