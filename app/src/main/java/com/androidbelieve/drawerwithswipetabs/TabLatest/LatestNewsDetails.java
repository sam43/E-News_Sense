package com.androidbelieve.drawerwithswipetabs.TabLatest;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.androidbelieve.drawerwithswipetabs.R;

public class LatestNewsDetails extends AppCompatActivity {

    WebView web_new;
    ProgressBar pb_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_news_details);

        web_new = (WebView) findViewById(R.id.webview_new);
        pb_new = (ProgressBar) findViewById(R.id.progressBar_new);

        web_new.setWebViewClient(new WebViewClient());
        Bundle bundle = getIntent().getExtras();
        web_new.loadUrl(bundle.getString("Link"));

    }

    public class WebViewClient extends android.webkit.WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pb_new.setVisibility(view.GONE);
        }
    }
}
