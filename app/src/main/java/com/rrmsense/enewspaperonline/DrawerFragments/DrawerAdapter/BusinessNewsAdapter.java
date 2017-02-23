package com.rrmsense.enewspaperonline.DrawerFragments.DrawerAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rrmsense.enewspaperonline.AppConfig;
import com.rrmsense.enewspaperonline.BottomSheet.BottomSheetBaseActivity;
import com.rrmsense.enewspaperonline.DrawerFragments.Models.DataModel;
import com.rrmsense.enewspaperonline.DrawerFragments.NewsDetails.BusinessNewsDetails;
import com.rrmsense.enewspaperonline.DrawerFragments.NewsDetails.NewsDetailsFragment;
import com.rrmsense.enewspaperonline.R;

import java.util.ArrayList;


/**
 * Created by sam43 on 1/28/17.
 */

public class BusinessNewsAdapter extends RecyclerView.Adapter<BusinessNewsAdapter.MyViewHolder>{

    ArrayList<DataModel> feedItems = null;
    Context cxt;
    AppConfig appConfig;
    //BottomSheetBaseActivity bznsFrag;

    public BusinessNewsAdapter(Context context, ArrayList<DataModel> feedItems, AppConfig appConfig) {
        this.feedItems = feedItems;
        this.cxt = context;
        this.appConfig = appConfig;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(cxt).inflate(R.layout.single_item_business,parent,false);
        BusinessNewsAdapter.MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final DataModel current = feedItems.get(position);

        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getPubDate());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailsFragment nd = new NewsDetailsFragment();
                Bundle b = new Bundle();
                b.putString("Link", current.getLink());
                nd.setArguments(b);
                ((FragmentActivity) cxt).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.containerView, nd)
                        .commit();
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    return;
                }
                Intent intent = new Intent(v.getContext(), BottomSheetBaseActivity.class);
                intent.putExtra("Link", current.getLink());
                ((Activity)v.getContext()).startActivity(intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        if (feedItems !=null) {
            return feedItems.size();
        }
        else {
            return -1;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title,Description,Date;
        ImageView share;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.title_bzns);
            Description= (TextView) itemView.findViewById(R.id.desc_bzns);
            Date= (TextView) itemView.findViewById(R.id.pubDate_bzns);
            share = (ImageView) itemView.findViewById(R.id.share_bzns);
            cardView= (CardView) itemView.findViewById(R.id.card_view_bzns);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                share.setVisibility(ImageView.INVISIBLE);
            }
        }
    }
}
