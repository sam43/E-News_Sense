package com.androidbelieve.drawerwithswipetabs.DrawerFragments.DrawerAdapter;

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
import com.androidbelieve.drawerwithswipetabs.DrawerFragments.Models.OddDataModel;
import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.TabLatest.LatestNewsDetails;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by sam43 on 1/29/17.
 */

public class OddNewsAdapter extends RecyclerView.Adapter<OddNewsAdapter.MyViewHolder>{

    ArrayList<OddDataModel> feedItems = null;
    Context cxt;
    AppConfig appConfig;
    private Target mTarget;

    public OddNewsAdapter(Context context, ArrayList<OddDataModel> feedItems, AppConfig appConfig) {
        this.feedItems = feedItems;
        this.cxt = context;
        this.appConfig = appConfig;
    }

    @Override
    public OddNewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(cxt).inflate(R.layout.single_list_item,parent,false);
        OddNewsAdapter.MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final OddDataModel current = feedItems.get(position);

        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getPubDate());
        mTarget = new Target() {
            @Override
            public void onBitmapLoaded (Bitmap bitmap, Picasso.LoadedFrom from){

                appConfig.New_image_size(bitmap.getHeight(),bitmap.getWidth());
                bitmap = appConfig.getResizedBitmap(bitmap, appConfig.getIMAGE_NEW_WIDTH(), appConfig.getIMAGE_NEW_HEIGHT());
                holder.thumb.setImageBitmap(bitmap);

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Bitmap noBitmap = BitmapFactory.decodeResource(cxt.getResources(), R.drawable.no_image);
                appConfig.New_image_size(noBitmap.getHeight(),noBitmap.getWidth());
                noBitmap = appConfig.getResizedBitmap(noBitmap, appConfig.getIMAGE_NEW_WIDTH(), appConfig.getIMAGE_NEW_HEIGHT());
                holder.thumb.setImageBitmap(noBitmap);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                holder.thumb.setImageDrawable(ContextCompat.getDrawable(cxt, (R.drawable.loading)));

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
        return feedItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title,Description,Date;
        ImageView thumb, share;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            Title= (TextView) itemView.findViewById(R.id.title_top);
            Description= (TextView) itemView.findViewById(R.id.top_desc);
            Date= (TextView) itemView.findViewById(R.id.pub_top);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            thumb = (ImageView) itemView.findViewById(R.id.top_image);
            share = (ImageView) itemView.findViewById(R.id.share_item);
        }
    }
}
