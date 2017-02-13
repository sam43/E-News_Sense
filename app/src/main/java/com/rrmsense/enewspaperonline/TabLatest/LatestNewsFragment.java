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

        if(rssFeed.isNetworkConnected()) {
            pd = new ProgressDialog(getActivity());
            pd.setMessage("Checking Internet Connection");
            pd.setCancelable(false);
            pd.show();
            pd.dismiss();

            Runnable pdRunnable = new Runnable() {
                @Override
                public void run() {

                    pd.dismiss();
                }
            };

            Handler pdDismiss = new Handler();
            pdDismiss.postDelayed(pdRunnable,2000);

        } else {
            new AlertDialog.Builder(getActivity()).setTitle("No Internet Connection!")
                    .setMessage("It looks like your internet connection is off. Please turn it on and try again")
                    .setCancelable(false)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                        }
                    }).setIcon(R.drawable.no_image).show();
        }

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
