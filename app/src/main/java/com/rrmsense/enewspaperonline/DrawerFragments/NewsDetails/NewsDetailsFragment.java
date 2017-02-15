package com.rrmsense.enewspaperonline.DrawerFragments.NewsDetails;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.rrmsense.enewspaperonline.DrawerFragments.Models.SelectNewspaper;
import com.rrmsense.enewspaperonline.MainActivity;
import com.rrmsense.enewspaperonline.R;


public class NewsDetailsFragment extends Fragment {

    WebView newsDetails;
    public ProgressBar pb;

    public NewsDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setCurrentFragment();
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news_details, container, false);
        newsDetails = (WebView) v.findViewById(R.id.webview_new);
        pb = (ProgressBar) v.findViewById(R.id.progressBar_new);

        WebSettings websettings = newsDetails.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setDomStorageEnabled(true);

        newsDetails.setWebViewClient(new MyWebViewClient());
        Bundle bundle = this.getArguments();

        if (bundle != null){
            newsDetails.loadUrl(bundle.getString("Link"));
        }
        return v;
    }

    public class MyWebViewClient extends android.webkit.WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            pb.setVisibility(View.GONE);
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    public void setCurrentFragment(){
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int backStackCount = sharedPref.getInt("BACK_STACK_COUNT", 0);
        int i = sharedPref.getInt("FRAGMENT_"+backStackCount, SelectNewspaper.nav_item_news);
        if(i!=SelectNewspaper.NEWS_DETAILS){
            editor.putInt("FRAGMENT_"+(backStackCount+1), SelectNewspaper.NEWS_DETAILS);
            editor.putInt("BACK_STACK_COUNT", (backStackCount+1));
            editor.apply();
        }

        //Log.d("CURRENT_FRAGMENT", String.valueOf(CURRENT_FRAGMENT));

    }
}
