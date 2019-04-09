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

import gr.artibet.vgames.adapters.PlatformAdapter;
import gr.artibet.vgames.api.ApiSettings;
import gr.artibet.vgames.models.Platform;

public class PlatformsFragment extends Fragment {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    private List<Platform> mPlatformList;

    private RecyclerView recyclerView;
    private PlatformAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // ---------------------------------------------------------------------------------------
    // Default constructor
    // ---------------------------------------------------------------------------------------
    public PlatformsFragment() {
        mPlatformList = new ArrayList<Platform>();
    }

    // ---------------------------------------------------------------------------------------
    // Set platform list
    // ---------------------------------------------------------------------------------------
    public void setPlatformList(List<Platform> platformList) {
        mPlatformList = platformList;
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
        View view =  inflater.inflate(R.layout.fragment_platforms, container, false);

        // Initialize recycler view
        buildRecyclerView(view);

        return view;
    }

    // ---------------------------------------------------------------------------------------
    // Build recycler view
    // ---------------------------------------------------------------------------------------
    private void buildRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.platformsRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PlatformAdapter(mPlatformList);
        recyclerView.setAdapter(adapter);

        // Set item click listener
        adapter.setOnItemClickListener(new PlatformAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Platform platform = mPlatformList.get(position);

                ApiSettings apiSettings = new ApiSettings(getActivity());
                Uri.Builder builder = Uri.parse(apiSettings.getGamesUrl()).buildUpon();
                builder.appendQueryParameter("platform", String.valueOf(platform.getId()));

                Intent intent = new Intent(getActivity(), ResultsActivity.class);
                intent.putExtra("TITLE", platform.getDesc());
                intent.putExtra("QUERY", builder.build().toString());
                startActivity(intent);

            }
        });
    }

}
