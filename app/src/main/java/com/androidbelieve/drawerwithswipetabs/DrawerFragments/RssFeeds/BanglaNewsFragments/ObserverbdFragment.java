package com.androidbelieve.drawerwithswipetabs.DrawerFragments.RssFeeds.BanglaNewsFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbelieve.drawerwithswipetabs.DrawerFragments.RssFeeds.BanglaRssFeeds.ObserverbdRss;
import com.androidbelieve.drawerwithswipetabs.DrawerFragments.RssFeeds.BanglaRssFeeds.TheindependentbdRss;
import com.androidbelieve.drawerwithswipetabs.MainActivity;
import com.androidbelieve.drawerwithswipetabs.R;

public class ObserverbdFragment extends Fragment {

    RecyclerView recyclerView_bng24;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bangla_bdnews24, container, false);

        recyclerView_bng24 = (RecyclerView) v.findViewById(R.id.recyclerview_bng24);

        ObserverbdRss rssFeeds = new ObserverbdRss(getActivity(), recyclerView_bng24,((MainActivity) getActivity()).appConfig);
        rssFeeds.execute();

        return v;
    }

    @Override
    public void onResume() {

        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("The Observer");

    }
}