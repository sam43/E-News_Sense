package com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaRssFeeds.BdNews24Rss;
import com.rrmsense.enewspaperonline.MainActivity;
import com.rrmsense.enewspaperonline.R;

public class BDNews24Fragment extends Fragment {

    RecyclerView recyclerView_bng24;
    BdNews24Rss rssFeeds;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bangla_bdnews24, container, false);

        recyclerView_bng24 = (RecyclerView) v.findViewById(R.id.recyclerview_bng24);

        rssFeeds = new BdNews24Rss(getActivity(), recyclerView_bng24, ((MainActivity) getActivity()).appConfig);
        rssFeeds.execute();

        return v;
    }


    @Override
    public void onPause() {
        super.onPause();
        if ((rssFeeds.progressDialog != null) && rssFeeds.progressDialog.isShowing())
            rssFeeds.progressDialog.dismiss();
        rssFeeds.progressDialog = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("BDNews 24");

    }
}
