package com.androidbelieve.drawerwithswipetabs.DrawerFragments.RssFeeds.BanglaNewsFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbelieve.drawerwithswipetabs.DrawerFragments.Models.SelectNewspaper;
import com.androidbelieve.drawerwithswipetabs.DrawerFragments.RssFeeds.BanglaRssFeeds.BdNews24Rss;
import com.androidbelieve.drawerwithswipetabs.DrawerFragments.RssFeeds.BanglaRssFeeds.ProthomAloRss;
import com.androidbelieve.drawerwithswipetabs.MainActivity;
import com.androidbelieve.drawerwithswipetabs.R;

import java.net.InetAddress;

public class ProthomAloFragment extends Fragment {

    RecyclerView recyclerView_bng24;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bangla_bdnews24, container, false);

        if(isInternetAvailable()) {

            recyclerView_bng24 = (RecyclerView) v.findViewById(R.id.recyclerview_bng24);

            ProthomAloRss rssFeeds = new ProthomAloRss(getActivity(), recyclerView_bng24,((MainActivity) getActivity()).appConfig);
            rssFeeds.execute();
        }

        return v;
    }

    @Override
    public void onResume() {

        super.onResume();
/*        ((MainActivity) getActivity()).CURRENT_FRAGMENT = SelectNewspaper.PROTHOM_ALO;
        ((MainActivity) getActivity()).setCurrentFragment();*/

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("প্রথম আলো");

    }
    public boolean isInternetAvailable() {
        return  true;

    }
}
