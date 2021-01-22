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

public class HotelGuideRecyclerViewAdapter extends RecyclerView.Adapter<HotelGuideRecyclerViewAdapter.ViewHolder> {
    private List<tourGuideData> tourGuideDataList;
    private Context mContext;

    public HotelGuideRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    public void setTourGuideDataList(List<tourGuideData> tourGuideDataList) {
        this.tourGuideDataList = tourGuideDataList;
        notifyItemChanged(0, tourGuideDataList.size());
    }

    @NonNull
    @Override
    public HotelGuideRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.hotels_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelGuideRecyclerViewAdapter.ViewHolder holder, int position) {
        tourGuideData tourGuideData = tourGuideDataList.get(position);
        String PosterLink = tourGuideData.getPoster();

        holder.business_Name.setText(tourGuideData.getBusiness_Name());
        holder.formatted_address.setText(tourGuideData.getAddress());
        holder.Trading_Hours.setText("Hours" + tourGuideData.isOpen_Now());
        holder.ratings.setText("Ratings: " + tourGuideData.getRatings());


        Picasso.get().load(PosterLink).placeholder(android.R.drawable.ic_btn_speak_now).into(holder.Poster);
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

            business_Name = itemView.findViewById(R.id.businessTitleId);
            formatted_address = itemView.findViewById(R.id.addressId);
            Trading_Hours = itemView.findViewById(R.id.hoursId);
            ratings = itemView.findViewById(R.id.ratingsId);
            Poster = itemView.findViewById(R.id.attractioImg);
        }
    }
}
