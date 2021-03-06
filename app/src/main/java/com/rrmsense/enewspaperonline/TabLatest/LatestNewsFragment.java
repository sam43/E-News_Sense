package com.rrmsense.enewspaperonline.TabLatest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rrmsense.enewspaperonline.MainActivity;
import com.rrmsense.enewspaperonline.R;


public class LatestNewsFragment extends Fragment {

    RecyclerView recyclerView_new;
    ProgressDialog pd;
    LatestRssFeed rssFeed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.latest_news_layout, container, false);
        recyclerView_new = (RecyclerView) v.findViewById(R.id.recyclerview_new);
        rssFeed = new LatestRssFeed(getActivity(), recyclerView_new,((MainActivity) getActivity()).appConfig);
        rssFeed.execute();




        /** Checking for internet and show dialog*/

        pd = new ProgressDialog(getActivity());
        return v;
    }

    @Override
    public void onResume() {

        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("Home");

    }
}
