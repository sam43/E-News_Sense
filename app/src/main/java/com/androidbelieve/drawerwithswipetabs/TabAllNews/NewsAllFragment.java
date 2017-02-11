package com.androidbelieve.drawerwithswipetabs.TabAllNews;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.androidbelieve.drawerwithswipetabs.MainActivity;
import com.androidbelieve.drawerwithswipetabs.R;


public class NewsAllFragment extends Fragment {

    WebView webBangla;
    ProgressBar pb1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.news_all_layout, container, false);

        webBangla = (WebView) v.findViewById(R.id.bangla_webview);
        pb1 = (ProgressBar) v.findViewById(R.id.progressBar_bangla);

        webBangla.setWebViewClient(new WebViewClient());
        webBangla.loadUrl("http://www.bangladeshpapers.com/");
        return v;
    }

    public class WebViewClient extends android.webkit.WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pb1.setVisibility(view.GONE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onResume() {

        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("Home");

    }
}
