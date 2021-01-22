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

public class RestaurantsGuideRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantsGuideRecyclerViewAdapter.ViewHolder> {
    private List<tourGuideData> tourGuideDataList;
    private Context context;

    public RestaurantsGuideRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setRestaurantGuideList(List<tourGuideData> tourGuideDataList) {
        this.tourGuideDataList = tourGuideDataList;
        notifyItemChanged(0, tourGuideDataList.size());
    }

    @NonNull
    @Override
    public RestaurantsGuideRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.restaurants_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsGuideRecyclerViewAdapter.ViewHolder holder, int position) {
        tourGuideData tourGuideData = tourGuideDataList.get(position);
        String PosterLink = tourGuideData.getPoster();

        try {

            holder.business_Name.setText(tourGuideData.getBusiness_Name());
            holder.formatted_address.setText(tourGuideData.getAddress());
            holder.Trading_Hours.setText("Hours: " + tourGuideData.isOpen_Now());
            holder.ratings.setText("Ratings: " + tourGuideData.getRatings());

            Picasso.get().load(PosterLink).placeholder(android.R.drawable.ic_btn_speak_now).into(holder.Poster);

        }catch (Exception e){
            e.printStackTrace();
        }

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
        TextView Trading_Hours;
        TextView ratings;
        ImageView Poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            business_Name = itemView.findViewById(R.id.titleID);
            formatted_address = itemView.findViewById(R.id.restaurant_addressId);
            Trading_Hours = itemView.findViewById(R.id.restaurant_hoursId);
            ratings = itemView.findViewById(R.id.restaurant_ratingsId);
            Poster = itemView.findViewById(R.id.restaurantsImg);
        }
    }
}
