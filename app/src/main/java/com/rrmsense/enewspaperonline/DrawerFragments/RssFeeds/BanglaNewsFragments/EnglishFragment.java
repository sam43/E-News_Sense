package com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rrmsense.enewspaperonline.DrawerFragments.DrawerAdapter.BanglaNewspaperBaseAdapter;
import com.rrmsense.enewspaperonline.DrawerFragments.Models.BanglaNewspaperDataModel;
import com.rrmsense.enewspaperonline.DrawerFragments.Models.SelectNewspaper;
import com.rrmsense.enewspaperonline.R;

import java.util.ArrayList;


public class EnglishFragment extends Fragment {

    ArrayList<BanglaNewspaperDataModel> newspapers;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mColumnNumber;



    public EnglishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_english, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_newspaper_english);
        int orientation=this.getResources().getConfiguration().orientation;
        if(orientation== Configuration.ORIENTATION_PORTRAIT){
            mColumnNumber = 2;

        }
        else{
            mColumnNumber = 4;
        }
        mLayoutManager = new GridLayoutManager(getActivity(),mColumnNumber);
        mRecyclerView.setLayoutManager(mLayoutManager);

        newspapers = new ArrayList<>();
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.theindependentbd), "The Independent", SelectNewspaper.INDEPENDENT));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.newagebd), "New Age", SelectNewspaper.NEWAGE));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.observerbd), "The Daily Observer", SelectNewspaper.OBSERVER));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.thedailynewnation), "The New Nation", SelectNewspaper.NEW_NATION));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.bdnews24), "bdnews24.com", SelectNewspaper.BDNEWS_24));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.dhakatribune), "Dhaka Tribune", SelectNewspaper.DHAKA_TRIBUNE));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.thefinancialexpress), "The Financial Express", SelectNewspaper.FINANCIAL_EXPRESS));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.dailysun), "The Daily Sun", SelectNewspaper.DAILY_SUN));

        mAdapter = new BanglaNewspaperBaseAdapter(newspapers,getActivity());
        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        //mRecyclerView.addItemDecoration(new GridDividerItemDecoration(ContextCompat.getDrawable(getActivity(), R.drawable.line_divider), ContextCompat.getDrawable(getActivity(), R.drawable.line_divider), mColumnNumber));

        return view;
    }


}
