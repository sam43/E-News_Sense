package com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.rrmsense.enewspaperonline.DrawerFragments.Models.SelectNewspaper;
import com.rrmsense.enewspaperonline.MainActivity;
import com.rrmsense.enewspaperonline.R;


public class BanglaNewspaperFragment extends Fragment {

    SharedPreferences sharedPref;
    private FragmentTabHost mTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        View rootView = inflater.inflate(R.layout.fragment_bangla_newspaper, container, false);

        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().getLayoutParams().height = 140;

        mTabHost.addTab(mTabHost.newTabSpec("Bangla").setIndicator("বাংলা"),
                BanglaFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("English").setIndicator("English"),
                EnglishFragment.class, null);
        mTabHost.setCurrentTab(0);
        mTabHost.setCurrentTab(SelectNewspaper.NEWS_DETAILS);



        int selectedTab = sharedPref.getInt("SELECTED_TAB_BANGLA", 0);
        mTabHost.setCurrentTab(selectedTab);


        mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#a674e1")); // selected
        TextView tv = (TextView) mTabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
        tv.setTextColor(Color.parseColor("#ffffff"));

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {


                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("SELECTED_TAB_BANGLA",mTabHost.getCurrentTab());
                editor.apply();

                for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
                    mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#a674e1")); // unselected
                    TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#292929"));
                }

                mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#a674e1")); // selected
                TextView tv = (TextView) mTabHost.getCurrentTabView().findViewById(android.R.id.title); //for Selected Tab
                tv.setTextColor(Color.parseColor("#ffffff"));

            }
        });

        return rootView;
    }
    @Override
    public void onResume() {

        super.onResume();

        // Set title bar
        ((MainActivity) getActivity())
                .setActionBarTitle("Home");

    }
}
