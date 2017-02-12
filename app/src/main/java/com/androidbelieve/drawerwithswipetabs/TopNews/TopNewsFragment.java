package com.androidbelieve.drawerwithswipetabs.TopNews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbelieve.drawerwithswipetabs.MainActivity;
import com.androidbelieve.drawerwithswipetabs.R;


public class TopNewsFragment extends Fragment {

    RecyclerView recyclerView;
    TopRssFeed topRssFeed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.top_news_layout, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_bng);
        topRssFeed = new TopRssFeed(getActivity(), recyclerView,((MainActivity) getActivity()).appConfig);
        topRssFeed.execute();

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        if ((topRssFeed.progressDialog != null) && topRssFeed.progressDialog.isShowing())
            topRssFeed.progressDialog.dismiss();
            topRssFeed.progressDialog = null;
    }

    @Override
    public void onResume() {

        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("Home");

    }
}
