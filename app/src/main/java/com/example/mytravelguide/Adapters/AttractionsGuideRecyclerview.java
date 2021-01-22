package com.example.mytravelguide.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytravelguide.Model.tourGuideData;
import com.example.mytravelguide.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AttractionsGuideRecyclerview extends RecyclerView.Adapter<AttractionsGuideRecyclerview.ViewHolder> {
    private List<tourGuideData> tourGuideDataList;
    private Context context;

    public AttractionsGuideRecyclerview(Context context) {
        this.context = context;
    }

    public void setAttractionsGuidList(List<tourGuideData> tourGuideDataList) {
        this.tourGuideDataList = tourGuideDataList;
        notifyItemChanged(0, tourGuideDataList.size());
    }

    @NonNull
    @Override
    public AttractionsGuideRecyclerview.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.attractions_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionsGuideRecyclerview.ViewHolder holder, int position) {
        tourGuideData tourGuideData = tourGuideDataList.get(position);

        String PosterLink = tourGuideData.getPoster();
        holder.business_Name.setText(tourGuideData.getBusiness_Name());
        holder.formatted_address.setText(tourGuideData.getAddress());
        holder.ratings.setText(tourGuideData.getRatings());

        Picasso.get()
                .load(PosterLink).placeholder(android.R.drawable.ic_btn_speak_now).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        try {
            return tourGuideDataList.size();
        } catch (Exception e) {

        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView business_Name;
        TextView formatted_address;
        TextView ratings;
        ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            business_Name = itemView.findViewById(R.id.business_name);
            formatted_address = itemView.findViewById(R.id.buss_address);
            ratings = itemView.findViewById(R.id.buss_ratingsId);
            poster = itemView.findViewById(R.id.attractioImg);
        }
    }
}