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
import com.example.mytravelguide.Adapters.AttractionsGuideRecyclerViewAdapter;
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
 * Use the {@link AttractionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttractionsFragment extends Fragment {

    private RequestQueue requestQueue;
    private List<tourGuideData> tourGuideDataList;
    private AttractionsGuideRecyclerViewAdapter attractionsGuideRecyclerviewAdapter;
    private RecyclerView recyclerView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AttractionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AttractionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AttractionsFragment newInstance(String param1, String param2) {
        AttractionsFragment fragment = new AttractionsFragment();
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
        View view = inflater.inflate(R.layout.fragment_attractions, container, false);
        recyclerView = view.findViewById(R.id.attractions_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String searchItem = Constants.Attractions_URL;
        tourGuideDataList = new ArrayList<>();

        tourGuideDataList = (ArrayList<tourGuideData>) getPlacesOfAttraction(searchItem.concat(Constants.API_KEY));
        attractionsGuideRecyclerviewAdapter = new AttractionsGuideRecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(attractionsGuideRecyclerviewAdapter);
        return view;
    }

    public List<tourGuideData> getPlacesOfAttraction(String attractionURL) {
        tourGuideDataList.clear();

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                attractionURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray attractionsArray = response.getJSONArray("results");
                    for (int i = 0; i < attractionsArray.length(); i++) {
                        JSONObject resultsObj = attractionsArray.getJSONObject(i);
                        JSONArray getPhotosArray = resultsObj.getJSONArray("photos");

                        for (int j = 0; j < getPhotosArray.length(); j++) {
                            JSONObject getPhotosReference = getPhotosArray.getJSONObject(j);
                            String Photo_reference = getPhotosReference.getString("photo_reference");

                            String business_name = resultsObj.getString("name");
                            String business_address = resultsObj.getString("formatted_address");
                            String ratings = resultsObj.getString("rating");
                            String opening_hours = "N/A";

                            if(!resultsObj.isNull("opening_hours")){
                               opening_hours = resultsObj.getString("opening_hours");
                            }

                            tourGuideData tourGuideData = new tourGuideData();
                            StringBuffer sb = new StringBuffer(Constants.photos_URL);
                            sb.append(Photo_reference);
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

                attractionsGuideRecyclerviewAdapter.setAttractionsGuidList(tourGuideDataList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.v("myError " + error.toString());
            }
        });
        requestQueue.add(jsonReq);
        return tourGuideDataList;
    }
}