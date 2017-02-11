package com.androidbelieve.drawerwithswipetabs.TabLatest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.AppConfig;
import com.androidbelieve.drawerwithswipetabs.BottomSheet.BottomSheetBaseActivity;
import com.androidbelieve.drawerwithswipetabs.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by sam43 on 1/26/17.
 */

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.MyViewHolder>{

    ArrayList<LatestFeedItem> feedItems;
    Context cxt;
    AppConfig appConfig;
    private Target mTarget;


    public LatestNewsAdapter(Context context, ArrayList<LatestFeedItem> feedItems,AppConfig appConfig) {
        this.feedItems=feedItems;
        this.cxt=context;
        this.appConfig = appConfig;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(cxt).inflate(R.layout.single_item_new,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final LatestFeedItem current=feedItems.get(position);

        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getPubDate());
        mTarget = new Target() {
            @Override
            public void onBitmapLoaded (Bitmap bitmap, Picasso.LoadedFrom from){

                appConfig.New_image_size(bitmap.getHeight(),bitmap.getWidth());
                bitmap = appConfig.getResizedBitmap(bitmap, appConfig.getIMAGE_NEW_WIDTH(), appConfig.getIMAGE_NEW_HEIGHT());
                holder.Thumbnail.setImageBitmap(bitmap);

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                 Bitmap noBitmap = BitmapFactory.decodeResource(cxt.getResources(), R.drawable.no_image);
                appConfig.New_image_size(noBitmap.getHeight(),noBitmap.getWidth());
                noBitmap = appConfig.getResizedBitmap(noBitmap, appConfig.getIMAGE_NEW_WIDTH(), appConfig.getIMAGE_NEW_HEIGHT());
                holder.Thumbnail.setImageBitmap(noBitmap);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                holder.Thumbnail.setImageDrawable(ContextCompat.getDrawable(cxt, (R.drawable.loading)));

            }
        };

        Picasso.with(cxt).load(String.valueOf(current.getThumbnailUrl())).into(mTarget);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cxt, LatestNewsDetails.class);
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

        if (feedItems != null)
            return feedItems.size();
        else
            return -1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title,Description,Date;
        ImageView Thumbnail, share;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.title_text);
            Description= (TextView) itemView.findViewById(R.id.description_text);
            Date= (TextView) itemView.findViewById(R.id.date_text);
            Thumbnail= (ImageView) itemView.findViewById(R.id.thumb_img);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            share = (ImageView) itemView.findViewById(R.id.share);
        }
    }
}