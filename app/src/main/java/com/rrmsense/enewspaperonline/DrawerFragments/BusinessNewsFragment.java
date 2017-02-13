package com.rrmsense.enewspaperonline.DrawerFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BusinessRssFeeds;
import com.rrmsense.enewspaperonline.MainActivity;
import com.rrmsense.enewspaperonline.R;

public class BusinessNewsFragment extends Fragment {

    RecyclerView recyclerView_bzns;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.business_news_layout, container, false);

        recyclerView_bzns = (RecyclerView) v.findViewById(R.id.recyclerview_business);

        BusinessRssFeeds rssFeeds = new BusinessRssFeeds(getActivity(), recyclerView_bzns,((MainActivity) getActivity()).appConfig);
        rssFeeds.execute();

        return v;
    }

    @Override
    public void onResume() {

        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("Business");

    }
}
