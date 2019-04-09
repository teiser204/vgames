package gr.artibet.vgames;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import gr.artibet.vgames.adapters.FeatureAdapter;
import gr.artibet.vgames.api.ApiSettings;
import gr.artibet.vgames.models.Feature;


public class FeaturesFragment extends Fragment {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    private List<Feature> mFeatureList;

    private RecyclerView recyclerView;
    private FeatureAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // ---------------------------------------------------------------------------------------
    // Default constructor
    // ---------------------------------------------------------------------------------------
    public FeaturesFragment() {
        mFeatureList = new ArrayList<Feature>();
    }

    // ---------------------------------------------------------------------------------------
    // Set feature list
    // ---------------------------------------------------------------------------------------
    public void setFeatureList(List<Feature> featureList) {
        mFeatureList = featureList;
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    // ---------------------------------------------------------------------------------------
    // onCreateView
    // ---------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_features, container, false);

        // Initialize recycler view
        buildRecyclerView(view);

        return view;
    }

    // ---------------------------------------------------------------------------------------
    // Build recycler view
    // ---------------------------------------------------------------------------------------
    private void buildRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.featuresRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FeatureAdapter(mFeatureList);
        recyclerView.setAdapter(adapter);

        // Set item click listener
        adapter.setOnItemClickListener(new FeatureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Feature feature = mFeatureList.get(position);

                ApiSettings apiSettings = new ApiSettings(getActivity());
                Uri.Builder builder = Uri.parse(apiSettings.getGamesUrl()).buildUpon();
                builder.appendQueryParameter("feature", String.valueOf(feature.getId()));

                Intent intent = new Intent(getActivity(), ResultsActivity.class);
                intent.putExtra("TITLE", feature.getDesc());
                intent.putExtra("QUERY", builder.build().toString());
                startActivity(intent);

            }
        });
    }

}
