package com.androidbelieve.drawerwithswipetabs.DrawerFragments.DrawerAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidbelieve.drawerwithswipetabs.AppConfig;
import com.androidbelieve.drawerwithswipetabs.BottomSheet.BottomSheetBaseActivity;
import com.androidbelieve.drawerwithswipetabs.DrawerFragments.BusinessNewsFragment;
import com.androidbelieve.drawerwithswipetabs.DrawerFragments.Models.DataModel;
import com.androidbelieve.drawerwithswipetabs.DrawerFragments.NewsDetails.BusinessNewsDetails;
import com.androidbelieve.drawerwithswipetabs.MainActivity;
import com.androidbelieve.drawerwithswipetabs.R;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import ch.tutti.android.bottomsheet.BottomSheetActivity;
import ch.tutti.android.bottomsheet.BottomSheetChooserActivity;


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
                Intent intent = new Intent(cxt, BusinessNewsDetails.class);
                intent.putExtra("Link", current.getLink());
                cxt.startActivity(intent);
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BottomSheetBaseActivity.class);
                intent.putExtra("Link", current.getLink());
                ((Activity)v.getContext()).startActivity(intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        return feedItems.size();
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
        }
    }
}