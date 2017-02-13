package com.rrmsense.enewspaperonline.TopNews;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.rrmsense.enewspaperonline.R;

/**
 * Created by sam43 on 1/25/17.
 */
public class TopNewsDetails extends AppCompatActivity{
    WebView webTop;
    ProgressBar pb_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_news_details);

        webTop = (WebView) findViewById(R.id.webview);
        pb_top = (ProgressBar) findViewById(R.id.progressBar_top);

        webTop.setWebViewClient(new WebViewClient());
        Bundle bundle = getIntent().getExtras();
        webTop.loadUrl(bundle.getString("Link"));

    }

    public class WebViewClient extends android.webkit.WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pb_top.setVisibility(view.GONE);
        }
    }
}
