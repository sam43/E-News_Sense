package com.rrmsense.enewspaperonline.DrawerFragments.RssFeeds.BanglaNewsFragments;


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
import com.rrmsense.enewspaperonline.MainActivity;
import com.rrmsense.enewspaperonline.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BanglaFragment extends Fragment {

    //private int mColumnNumber;
    ArrayList<BanglaNewspaperDataModel> newspapers;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public BanglaFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bangla, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_newspaper_bangla);
        //int orientation=this.getResources().getConfiguration().orientation;
        /*if(orientation== Configuration.ORIENTATION_PORTRAIT){
            mColumnNumber = 2;

        }
        else{
            mColumnNumber = 4;
        }*/
        mLayoutManager = new GridLayoutManager(getActivity(),((MainActivity) getActivity()).appConfig.getCOLOMN()+1);
        mRecyclerView.setLayoutManager(mLayoutManager);

        newspapers = new ArrayList<>();
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.prothomalo), "প্রথম আলো", SelectNewspaper.PROTHOM_ALO));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.ittefaq), "ইত্তেফাক", SelectNewspaper.ITTEFAQ));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.aajkaal), "আজকাল", SelectNewspaper.AAJKAL));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.samakal), "সমকাল", SelectNewspaper.SAMAKAL));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.dailyinqilab), "ইনকিলাব", SelectNewspaper.INQILAB));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.mzamin), "মানবজমিন", SelectNewspaper.MANABZAMIN));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.jugantor), "যুগান্তর", SelectNewspaper.JUGANTOR));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.kalerkontho), "কালের কণ্ঠ", SelectNewspaper.KALER_KANTHA));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.bhorerkagoj), "ভোরের কাগজ", SelectNewspaper.VORER_KAGOJ));
        newspapers.add(new BanglaNewspaperDataModel(ContextCompat.getDrawable(getActivity(),R.drawable.amadershomoy), "আমাদের সময়", SelectNewspaper.AMADER_SOMOY));


        mAdapter = new BanglaNewspaperBaseAdapter(newspapers,getActivity());
        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        //mRecyclerView.addItemDecoration(new GridDividerItemDecoration(ContextCompat.getDrawable(getActivity(), R.drawable.line_divider), ContextCompat.getDrawable(getActivity(), R.drawable.line_divider), mColumnNumber));


        return view;
    }

}
