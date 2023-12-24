package com.example.forhadhossainmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SocialMediaDetails extends AppCompatActivity {

    WebView facebookView;
    Toolbar toolbar;
    String link = "https://www.facebook.com/FarhadHossainMP";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_details);

        //id define
        facebookView = findViewById(R.id.facebookView);

        // toolbar
        toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        
        // load webview url
        facebookView.loadUrl(link);
        WebSettings settings= facebookView.getSettings();
        settings.setJavaScriptEnabled(true);
        facebookView.setWebViewClient(new WebViewClient());
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);


        facebookView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                // Handle the error here
                facebookView.loadUrl("https://www.facebook.com/FarhadHossainMP"); // Load default URL
                Log.d("POST VIEW ERROR",error.toString());
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                // This method is called when a new page starts loading
//                        webProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // This method is called when the page finishes loading
//                        webProgress.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}