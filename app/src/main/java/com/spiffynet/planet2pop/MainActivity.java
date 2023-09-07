package com.spiffynet.planet2pop;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private WebView mywebView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EditText usernameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://ps2.live/";

        mywebView = findViewById(R.id.webview);
        mywebView.setWebChromeClient(new WebChromeClient());
        mywebView.loadUrl(url);
        WebSettings webSettings = mywebView.getSettings();

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
    } // end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // reload
        if (item.getItemId() == R.id.reload) {
            mywebView.reload();
            Toast.makeText(MainActivity.this, "Reload", Toast.LENGTH_SHORT).show();
        }
        // quit
        else if(item.getItemId() == R.id.quit) {
            System.exit(0);
        }
        // user_search
        else if(item.getItemId() == R.id.user_search) {
//            setContentView(R.layout.get_user);
            Toast.makeText(MainActivity.this, "User Search", Toast.LENGTH_SHORT).show();
            usernameEditText = findViewById(R.id.user_name);
            String username = usernameEditText.getText().toString();
            String url = "https://ps2.fisu.pw/player/?name=" + username;
            mywebView.loadUrl(url);
        }
        // other options
        else {
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mywebView != null && mywebView.canGoBack()) {
            mywebView.goBack();
        } else {
            super.onBackPressed();;
        }
    }
}