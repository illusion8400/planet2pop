package com.spiffynet.planet2pop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.Menu;
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
        Toast.makeText(MainActivity.this, "Loading...", Toast.LENGTH_SHORT).show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.reload) {
            mywebView.reload();
            Toast.makeText(MainActivity.this, "Reload", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.quit) {
            System.exit(0);
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        if (mywebView != null && mywebView.canGoBack()) {
            mywebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}