package com.androidbelieve.drawerwithswipetabs.DrawerFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbelieve.drawerwithswipetabs.DrawerFragments.RssFeeds.BusinessRssFeeds;
import com.androidbelieve.drawerwithswipetabs.DrawerFragments.RssFeeds.EntertainmentRssFeeds;
import com.androidbelieve.drawerwithswipetabs.DrawerFragments.RssFeeds.TechRssFeeds;
import com.androidbelieve.drawerwithswipetabs.MainActivity;
import com.androidbelieve.drawerwithswipetabs.R;


public class TechnologyFragment extends Fragment {

    RecyclerView recyclerView_sports;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_technology, container, false);
        recyclerView_sports = (RecyclerView) v.findViewById(R.id.recyclerview_sports);

        TechRssFeeds rssFeeds = new TechRssFeeds(getActivity(), recyclerView_sports,((MainActivity) getActivity()).appConfig);
        rssFeeds.execute();

        return v;
    }

    @Override
    public void onResume() {

        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("Technology");

    }
}
