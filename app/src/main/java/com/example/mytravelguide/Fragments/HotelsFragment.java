package com.example.mytravelguide.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mytravelguide.Adapters.HotelGuideRecyclerViewAdapter;
import com.example.mytravelguide.Model.tourGuideData;
import com.example.mytravelguide.R;
import com.example.mytravelguide.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelsFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<tourGuideData> tourGuideDataList;
    private HotelGuideRecyclerViewAdapter hotelGuideRecyclerViewAdapter;
    private RequestQueue queue;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HotelsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RoomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HotelsFragment newInstance(String param1, String param2) {
        HotelsFragment fragment = new HotelsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        queue = Volley.newRequestQueue(getContext());
        View view = inflater.inflate(R.layout.fragment_hotels, container, false);
        recyclerView = view.findViewById(R.id.hotel_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String searchItem = Constants.Hotels_URL;
        tourGuideDataList = new ArrayList<>();

        tourGuideDataList = (ArrayList<tourGuideData>) getTourGuideModelList(searchItem);
        hotelGuideRecyclerViewAdapter = new HotelGuideRecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(hotelGuideRecyclerViewAdapter);
        return view;
    }


    public List<tourGuideData> getTourGuideModelList(String hotelSearch) {
        tourGuideDataList.clear();

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                hotelSearch, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String hotel_photos_url = Constants.hotels_photos_URL;
                    JSONArray hotelsArray = response.getJSONArray("results");
                    JSONObject getPhotos;
                    for (int i = 0; i <= hotelsArray.length(); i++) {
                        JSONObject resultsObj = hotelsArray.getJSONObject(i);
                        JSONArray getPhotosArray = resultsObj.getJSONArray("photos");

                        for (int j = 0; j < getPhotosArray.length(); j++) {
                            getPhotos = getPhotosArray.getJSONObject(j);
                            String photo_reference = getPhotos.getString("photo_reference");
                            String var = hotel_photos_url + ""+ photo_reference;
                            var = var.concat(Constants.API_KEY);

                            tourGuideData tourGuideData = new tourGuideData();
                            String business_name = resultsObj.getString("name");
                            String business_address = resultsObj.getString("formatted_address");
                            String ratings = resultsObj.getString("rating");
                            String opening_hours = "N/A";

                            if (!resultsObj.isNull("opening_hours")) {
                                opening_hours = resultsObj.getString("opening_hours");

                            } else {
                                tourGuideData.setOpen_Now(opening_hours);
                            }

                            tourGuideData.setPoster(var);
                            tourGuideData.setBusiness_Name(business_name);
                            tourGuideData.setAddress(business_address);
                            tourGuideData.setRatings(ratings);
                            tourGuideData.setOpen_Now(opening_hours);
                            tourGuideDataList.add(tourGuideData);

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                hotelGuideRecyclerViewAdapter.setTourGuideDataList(tourGuideDataList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.v("myError" + error.toString());

            }
        });
        queue.add(jsonReq);
        return tourGuideDataList;
    }

}