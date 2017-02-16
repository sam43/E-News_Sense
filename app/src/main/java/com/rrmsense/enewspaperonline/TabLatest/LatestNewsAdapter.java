package com.rrmsense.enewspaperonline.TabLatest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.rrmsense.enewspaperonline.AppConfig;
import com.rrmsense.enewspaperonline.BottomSheet.BottomSheetBaseActivity;
import com.rrmsense.enewspaperonline.DrawerFragments.NewsDetails.NewsDetailsFragment;
import com.rrmsense.enewspaperonline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sam43 on 1/26/17.
 */

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.MyViewHolder>{

    ArrayList<LatestFeedItem> feedItems;
    Context cxt;
    AppConfig appConfig;
    //private Target mTarget;
    //Uri uri;


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


        //uri = Uri.parse("http://i.imgur.com/I6QPAk2.gif");



        /*Ion.with(cxt).load(current.getThumbnailUrl())
                .withBitmap()
                .animateGif(AnimateGifMode.ANIMATE)
                .error(R.drawable.no_pic)
                .intoImageView(holder.Thumbnail);*/

        Picasso.with(cxt).load(String.valueOf(current.getThumbnailUrl())).placeholder(R.drawable.white).fit().error(R.drawable.no_pic).into(holder.Thumbnail,new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                //do smth when picture is loaded successfully
                holder.pb.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onError() {
                //do smth when there is picture loading error
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                Intent intent = new Intent(cxt, BusinessNewsDetails.class);
                intent.putExtra("Link", current.getLink());
                cxt.startActivity(intent);*/
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
        ProgressBar pb;


        public MyViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.title_text);
            Description= (TextView) itemView.findViewById(R.id.description_text);
            Date= (TextView) itemView.findViewById(R.id.date_text);
            Thumbnail= (ImageView) itemView.findViewById(R.id.thumb_img);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            share = (ImageView) itemView.findViewById(R.id.share);
            pb = (ProgressBar) itemView.findViewById(R.id.pro_item);
            //Glide.with(cxt).load("http://i.imgur.com/I6QPAk2.gif").into(Thumbnail);
        }
    }
}