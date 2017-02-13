package com.rrmsense.enewspaperonline.TopNews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rrmsense.enewspaperonline.AppConfig;
import com.rrmsense.enewspaperonline.BottomSheet.BottomSheetBaseActivity;
import com.rrmsense.enewspaperonline.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by sam43 on 1/25/17.
 */
public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.MyViewHolder> {

    ArrayList<TopFeedItem>feedItems = null;
    Context context;
    AppConfig appConfig;
    private Target mTarget;



    public TopNewsAdapter(Context context, ArrayList<TopFeedItem> feedItems, AppConfig appConfig) {

        this.feedItems = feedItems;
        this.context = context;
        this.appConfig = appConfig;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.single_list_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final TopFeedItem current = feedItems.get(position);

        holder.Title.setText(current.getTitle());
        holder.Description.setText(current.getDescription());
        holder.Date.setText(current.getPubDate());
/*        mTarget = new Target() {
            @Override
            public void onBitmapLoaded (Bitmap bitmap, Picasso.LoadedFrom from){

                appConfig.New_image_size(bitmap.getHeight(),bitmap.getWidth());
                bitmap = appConfig.getResizedBitmap(bitmap, appConfig.getIMAGE_NEW_WIDTH(), appConfig.getIMAGE_NEW_HEIGHT());
                holder.Thumbnail.setImageBitmap(bitmap);

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Bitmap noBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.no_image);
                appConfig.New_image_size(noBitmap.getHeight(),noBitmap.getWidth());
                noBitmap = appConfig.getResizedBitmap(noBitmap, appConfig.getIMAGE_NEW_WIDTH(), appConfig.getIMAGE_NEW_HEIGHT());
                holder.Thumbnail.setImageBitmap(noBitmap);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                holder.Thumbnail.setImageDrawable(ContextCompat.getDrawable(context, (R.drawable.loading)));

            }
        };*/
        try{
            Picasso.with(context).load(String.valueOf(current.getThumbnailUrl())).fit().centerInside().placeholder(R.drawable.loading).error(R.drawable.no_image).into(holder.Thumbnail);
        }catch (Exception e){

        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TopNewsDetails.class);
                intent.putExtra("Link", current.getLink());
                context.startActivity(intent);
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
            Title= (TextView) itemView.findViewById(R.id.title_top);
            Description= (TextView) itemView.findViewById(R.id.top_desc);
            Date= (TextView) itemView.findViewById(R.id.pub_top);
            Thumbnail= (ImageView) itemView.findViewById(R.id.top_image);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            share = (ImageView) itemView.findViewById(R.id.share_item);
        }
    }
}
