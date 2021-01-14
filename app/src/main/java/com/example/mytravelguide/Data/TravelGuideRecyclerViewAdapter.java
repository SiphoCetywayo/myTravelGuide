package com.example.mytravelguide.Data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytravelguide.Model.tourGuideModel;
import com.example.mytravelguide.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TravelGuideRecyclerView extends RecyclerView.Adapter<TravelGuideRecyclerView.ViewHolder> {
    private List<tourGuideModel> tourGuideModelList;
    private Context mContext;

    public TravelGuideRecyclerView( Context context,List<tourGuideModel> tourGuideModels){
        this.tourGuideModelList = tourGuideModels;
        this.mContext = context;
    }

    @NonNull
    @Override
    public TravelGuideRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.hotels_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelGuideRecyclerView.ViewHolder holder, int position) {
        tourGuideModel tourGuideModel = tourGuideModelList.get(position);
        String PosterLink = tourGuideModel.getPoster();

        holder.business_Name.setText(tourGuideModel.getBusiness_Name());
        holder.formatted_address.setText(tourGuideModel.getAddress());
        holder.Trading_Hours.setText(tourGuideModel.isOpen_Now());
        holder.ratings.setText(tourGuideModel.getRatings());

        Picasso.get()
                .load(PosterLink)
                .placeholder(android.R.drawable.ic_btn_speak_now)
                .into(holder.Poster);
    }

    @Override
    public int getItemCount() {
        return tourGuideModelList.size();
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
