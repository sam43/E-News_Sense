package com.rrmsense.enewspaperonline.DrawerFragments.DrawerAdapter;

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
import android.widget.TextView;

import com.rrmsense.enewspaperonline.AppConfig;
import com.rrmsense.enewspaperonline.BottomSheet.BottomSheetBaseActivity;
import com.rrmsense.enewspaperonline.DrawerFragments.Models.ProthomAloFeedItem;
import com.rrmsense.enewspaperonline.DrawerFragments.NewsDetails.NewsDetailsFragment;
import com.rrmsense.enewspaperonline.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sam43 on 2/7/17.
 */

public class ProthomAloNewsAdapter extends RecyclerView.Adapter<ProthomAloNewsAdapter.MyViewHolder>{

    ArrayList<ProthomAloFeedItem> feedItems;
    Context cxt;
    AppConfig appConfig;
    //private Target mTarget;


    public ProthomAloNewsAdapter(Context context, ArrayList<ProthomAloFeedItem> feedItems, AppConfig appConfig) {
        this.feedItems=feedItems;
        this.cxt=context;
        this.appConfig = appConfig;
    }
    @Override
    public ProthomAloNewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(cxt).inflate(R.layout.single_item_prothom_alo,parent,false);
        ProthomAloNewsAdapter.MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ProthomAloNewsAdapter.MyViewHolder holder, int position) {
        final ProthomAloFeedItem current=feedItems.get(position);

        holder.Title.setText(current.getTitle());
        holder.Date.setText(current.getPubDate());
        //Log.d("Image Size",holder.Thumbnail.getHeight()+","+ holder.Thumbnail.getWidth() );

/*        mTarget = new Target() {
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
        };*/

        Picasso.with(cxt).load(String.valueOf(current.getThumbnailUrl())).fit().centerInside().placeholder(R.drawable.loading).error(R.drawable.no_image).into(holder.Thumbnail);

        //appConfig.New_image_size(holder.Thumbnail.getHeight(), holder.Thumbnail.getWidth());

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
                        .addToBackStack(null)
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
        TextView Title,Date;
        ImageView Thumbnail, share;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.title_text);
            Date= (TextView) itemView.findViewById(R.id.date_text);
            Thumbnail= (ImageView) itemView.findViewById(R.id.thumb_img);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            share = (ImageView) itemView.findViewById(R.id.share);
        }
    }


}
