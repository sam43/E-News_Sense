package com.androidbelieve.drawerwithswipetabs.DrawerFragments.DrawerAdapter;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.DrawerFragments.Models.BanglaNewspaperDataModel;
import com.androidbelieve.drawerwithswipetabs.MainActivity;
import com.androidbelieve.drawerwithswipetabs.R;

import java.util.ArrayList;

public class BanglaNewspaperBaseAdapter extends RecyclerView.Adapter<BanglaNewspaperBaseAdapter.ViewHolder> {
    private ArrayList<BanglaNewspaperDataModel> banglaNewspaperDataModels = null;
    private Context mContext;

    public BanglaNewspaperBaseAdapter(ArrayList<BanglaNewspaperDataModel> banglaNewspaperDataModels, Context mContext) {
        this.banglaNewspaperDataModels = banglaNewspaperDataModels;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_bangla_newspaper, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final BanglaNewspaperDataModel banglaNewspaperDataModel = banglaNewspaperDataModels.get(i);

        viewHolder.text_newspaper.setText(banglaNewspaperDataModel.getName());
        viewHolder.image_newspaper.setImageDrawable(banglaNewspaperDataModel.getImge());
        viewHolder.id_newspaper.setText(Integer.toString(banglaNewspaperDataModels.get(i).getId()));
    }

    @Override
    public int getItemCount() {
        return banglaNewspaperDataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text_newspaper;
        private ImageView image_newspaper;
        private TextView id_newspaper;
        private ImageView share;
        private CardView cardView;

        public ViewHolder(View view) {
            super(view);

            text_newspaper = (TextView) view.findViewById(R.id.text_newspaper);
            id_newspaper= (TextView) view.findViewById(R.id.id_newspaper);
            image_newspaper = (ImageView) view.findViewById(R.id.image_newspaper);
            share = (ImageView) view.findViewById(R.id.share);
            cardView = (CardView) view.findViewById(R.id.card_view_bangla);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(mContext,id_newspaper.getText(), Toast.LENGTH_SHORT).show();
                    if(mContext instanceof MainActivity){
                        ((MainActivity)mContext).OpenFragments(Integer.parseInt(id_newspaper.getText().toString()));
                    }
                }
            });
        }
    }

}