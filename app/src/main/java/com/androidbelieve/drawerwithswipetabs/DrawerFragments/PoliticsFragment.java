package com.androidbelieve.drawerwithswipetabs.DrawerFragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbelieve.drawerwithswipetabs.DrawerFragments.RssFeeds.PoliticsRssFeeds;
import com.androidbelieve.drawerwithswipetabs.MainActivity;
import com.androidbelieve.drawerwithswipetabs.R;

public class PoliticsFragment extends Fragment {

    RecyclerView recyclerView_politics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_politics, container, false);

        recyclerView_politics = (RecyclerView) v.findViewById(R.id.recyclerview_politics);
        PoliticsRssFeeds rssFeeds = new PoliticsRssFeeds(getActivity(), recyclerView_politics,((MainActivity) getActivity()).appConfig);
        rssFeeds.execute();

        return v;
    }

    @Override
    public void onResume() {

        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("World Politics");

    }
}
