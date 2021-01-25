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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mytravelguide.Adapters.Amusement_parksRecyclerViewAdapter;
import com.example.mytravelguide.Model.tourGuideData;
import com.example.mytravelguide.R;
import com.example.mytravelguide.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Amusement_parkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Amusement_parkFragment extends Fragment {
    private RequestQueue requestQueue;
    private List<tourGuideData> tourGuideDataList;
    private Amusement_parksRecyclerViewAdapter amusement_parksRecyclerViewAdapter;
    RecyclerView amusementParkRecyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Amusement_parkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PharmacyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Amusement_parkFragment newInstance(String param1, String param2) {
        Amusement_parkFragment fragment = new Amusement_parkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        requestQueue = Volley.newRequestQueue(getContext());
        View view = inflater.inflate(R.layout.amusement_park, container, false);
        amusementParkRecyclerView = view.findViewById(R.id.amusement_park_recyclerView);
        amusementParkRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String searchItem = Constants.AmusementParks_URL;
        tourGuideDataList = new ArrayList<>();

        tourGuideDataList = getAmusementParks(searchItem);
        amusement_parksRecyclerViewAdapter = new Amusement_parksRecyclerViewAdapter(getActivity());
        amusementParkRecyclerView.setAdapter(amusement_parksRecyclerViewAdapter);
        return view;
    }

    private List<tourGuideData> getAmusementParks(String amusementParkURL) {

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                amusementParkURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                tourGuideDataList.clear();
                try {
                    JSONArray amusementParkArray = response.getJSONArray("results");
                    for (int i = 0; i < amusementParkArray.length(); i++) {
                        JSONObject getResultsObj = amusementParkArray.getJSONObject(i);
                        JSONArray getPhotosArray = getResultsObj.getJSONArray("photos");

                        for (int j = 0; j < getPhotosArray.length(); j++) {
                            JSONObject photosObj = getPhotosArray.getJSONObject(j);
                            String getPhotoReference = photosObj.getString("photo_reference");

                            String business_name = getResultsObj.getString("name");
                            String business_address = getResultsObj.getString("formatted_address");
                            String ratings = getResultsObj.getString("rating");
                            String opening_hours = "N/A";

                            if (!getResultsObj.isNull("opening_hours")) {
                                opening_hours = getResultsObj.getString("opening_hours");
                            }

                            tourGuideData tourGuideData = new  tourGuideData();
                            StringBuffer sb = new StringBuffer(Constants.photos_URL);
                            sb.append(getPhotoReference);
                            sb.append(Constants.API_KEY);

                            tourGuideData.setBusiness_Name(business_name);
                            tourGuideData.setAddress(business_address);
                            tourGuideData.setRatings(ratings);
                            tourGuideData.setOpen_Now(opening_hours);
                            tourGuideData.setPoster(sb.toString());
                            tourGuideDataList.add(tourGuideData);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                amusement_parksRecyclerViewAdapter.setAmusementParkList(tourGuideDataList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonReq);
        return tourGuideDataList;
    }
}