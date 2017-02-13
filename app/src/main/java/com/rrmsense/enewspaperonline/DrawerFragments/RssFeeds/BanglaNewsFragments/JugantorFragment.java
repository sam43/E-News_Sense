package com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaRssFeeds.JugantorRss;
import com.rrmsense.enewspaperonline.MainActivity;
import com.rrmsense.enewspaperonline.R;

public class JugantorFragment extends Fragment {

    RecyclerView recyclerView_bng24;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bangla_bdnews24, container, false);

        recyclerView_bng24 = (RecyclerView) v.findViewById(R.id.recyclerview_bng24);

        JugantorRss rssFeeds = new JugantorRss(getActivity(), recyclerView_bng24,((MainActivity) getActivity()).appConfig);
        rssFeeds.execute();

        return v;
    }

    @Override
    public void onResume() {

        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("যুগান্তর");

    }
}
