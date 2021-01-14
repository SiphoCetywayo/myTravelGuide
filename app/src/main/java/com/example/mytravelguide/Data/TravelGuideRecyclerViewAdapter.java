package com.example.mytravelguide.Data;

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

import java.util.ArrayList;
import java.util.List;

public class TravelGuideRecyclerViewAdapter extends RecyclerView.Adapter<TravelGuideRecyclerViewAdapter.ViewHolder> {
    private ArrayList<tourGuideData> tourGuideDataList;
    private Context mContext;

    public TravelGuideRecyclerViewAdapter(Context context, ArrayList<tourGuideData> tourGuideDataList){
        this.tourGuideDataList = tourGuideDataList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public TravelGuideRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.hotels_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelGuideRecyclerViewAdapter.ViewHolder holder, int position) {
        tourGuideData tourGuideData = tourGuideDataList.get(position);
        String PosterLink = tourGuideData.getPoster();

        holder.business_Name.setText(tourGuideData.getBusiness_Name());
        holder.formatted_address.setText(tourGuideData.getAddress());
        holder.Trading_Hours.setText(tourGuideData.isOpen_Now());
        holder.ratings.setText(tourGuideData.getRatings());

        Picasso.get()
                .load(PosterLink)
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.Poster);
    }

    @Override
    public int getItemCount() {
        return tourGuideDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView business_Name;
        TextView formatted_address;
        TextView Trading_Hours;
        TextView ratings;
        ImageView Poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            business_Name = itemView.findViewById(R.id.businessTitleId);
            formatted_address = itemView.findViewById(R.id.addressId);
            Trading_Hours = itemView.findViewById(R.id.hoursId);
            ratings = itemView.findViewById(R.id.ratingsId);
            Poster = itemView.findViewById(R.id.hotelImg);
        }
    }
}
