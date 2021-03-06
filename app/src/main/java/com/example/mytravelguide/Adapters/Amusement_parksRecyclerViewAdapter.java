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
import java.util.zip.Inflater;

public class Amusement_parksRecyclerViewAdapter extends RecyclerView.Adapter<Amusement_parksRecyclerViewAdapter.ViewHolder> {
    private List<tourGuideData> tourGuideDataList;
    private Context context;

    public Amusement_parksRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setAmusementParkList(List<tourGuideData> tourGuideDataList){
        this.tourGuideDataList = tourGuideDataList;
        notifyItemChanged(0, tourGuideDataList.size());
    }

    @NonNull
    @Override
    public Amusement_parksRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.amusement_park_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Amusement_parksRecyclerViewAdapter.ViewHolder holder, int position) {
        tourGuideData tourGuideData = tourGuideDataList.get(position);
        String PosterLink = tourGuideData.getPoster();

        holder.business_Name.setText(tourGuideData.getBusiness_Name());
        holder.formatted_address.setText(tourGuideData.getAddress());
        holder.ratings.setText("Ratings: " +tourGuideData.getRatings());
        holder.Trading_Hours.setText("Hours: "+ tourGuideData.isOpen_Now());

        Picasso.get().load(PosterLink).placeholder(android.R.drawable.ic_btn_speak_now).into(holder.poster);
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
        ImageView poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            business_Name = itemView.findViewById(R.id.pharmacy_name);
            formatted_address = itemView.findViewById(R.id.pharmacy_addressId);
            Trading_Hours = itemView.findViewById(R.id.pharmacy_hoursId);
            ratings = itemView.findViewById(R.id.pharmacy_ratingsId);
            poster = itemView.findViewById(R.id.pharmacy_img);
        }
    }
}
