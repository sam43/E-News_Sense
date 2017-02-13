package com.rrmsense.enewspaperonline.DrawerFragments.NewsDetails;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.rrmsense.enewspaperonline.R;

public class BusinessNewsDetails extends AppCompatActivity {

    WebView web_bzns;
    ProgressBar pb_bzns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_news_details);

        web_bzns = (WebView) findViewById(R.id.webview_new);
        pb_bzns = (ProgressBar) findViewById(R.id.progressBar_new);

        web_bzns.setWebViewClient(new WebViewClient());
        Bundle bundle = getIntent().getExtras();
        web_bzns.loadUrl(bundle.getString("Link"));
    }

    public class WebViewClient extends android.webkit.WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pb_bzns.setVisibility(view.GONE);
        }
    }
}
