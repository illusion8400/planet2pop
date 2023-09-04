package com.spiffynet.planet2pop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mywebView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String url = "https://ps2.live/";

        setContentView(R.layout.activity_main);
        mywebView = findViewById(R.id.webview);
        mywebView.setWebViewClient(new WebViewClient());
        mywebView.loadUrl(url);
        WebSettings webSettings = mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set up SwipeRefreshLayout for refreshing
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mywebView.reload();
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Reload", Toast.LENGTH_SHORT).show();
            }
        });

    } // end on create
    @Override
    public void onBackPressed() {
        if (mywebView != null && mywebView.canGoBack()) {
            mywebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}