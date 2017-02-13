package com.rrmsense.enewspaperonline.DrawerFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.OddRssFeeds;
import com.rrmsense.enewspaperonline.MainActivity;
import com.rrmsense.enewspaperonline.R;

public class OddNewsFragment extends Fragment {

    RecyclerView recyclerView_odd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_odd_news, container, false);
        recyclerView_odd = (RecyclerView) v.findViewById(R.id.recyclerview_odd);
        OddRssFeeds rssFeeds = new OddRssFeeds(getActivity(), recyclerView_odd,((MainActivity) getActivity()).appConfig);
        rssFeeds.execute();

        return v;
    }

    @Override
    public void onResume() {

        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("Epic News");

    }
}
