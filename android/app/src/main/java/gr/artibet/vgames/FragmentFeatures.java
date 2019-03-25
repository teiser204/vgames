package gr.artibet.vgames;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import gr.artibet.vgames.adapters.FeatureAdapter;
import gr.artibet.vgames.api.FeatureAPI;
import gr.artibet.vgames.models.Feature;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFeatures extends Fragment {

    private MainActivity mainActivity;
    private ImageView refreshButton;

    private RecyclerView recyclerView;
    private FeatureAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Feature> featureList = new ArrayList<>();

    public FragmentFeatures() {
        // Required empty public constructor
    }

    public static FragmentFeatures newInstance() {
        FragmentFeatures fragment = new FragmentFeatures();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hold a reference to main activity
        mainActivity = (MainActivity)getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_features, container, false);

        // Set refresh button click listener
        refreshButton = view.findViewById(R.id.refreshFeatureListButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchFeatures();
            }
        });

        // Initialize recycler view
        buildRecyclerView(view);
        fetchFeatures();

        return view;
    }


    // Fetch genres from API
    private void fetchFeatures() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FeatureAPI featureAPI = retrofit.create(FeatureAPI.class);

        Call<List<Feature>> call = featureAPI.getFeatures();

        call.enqueue(new Callback<List<Feature>>() {
            @Override
            public void onResponse(Call<List<Feature>> call, Response<List<Feature>> response) {
                if (!response.isSuccessful()) {

                    // Toast failure message
                    mainActivity.showErrorToast(getString(R.string.feature_fetch_error));
                    return;
                }

                // Change adapters data
                featureList = response.body();
                adapter.setFeatureList(featureList);
            }

            @Override
            public void onFailure(Call<List<Feature>> call, Throwable t) {

                // Toast failure message
                mainActivity.showErrorToast(getString(R.string.feature_fetch_error));
            }
        });
    }

    // Build recycler view
    private void buildRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.featureRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FeatureAdapter(featureList);
        recyclerView.setAdapter(adapter);

        // Set item click listener
        adapter.setOnItemClickListener(new FeatureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Feature feature = featureList.get(position);

                // Build filter part of url
                Intent intent = new Intent(getActivity(), SearchableActivity.class);
                intent.putExtra("ID", feature.getId());
                intent.putExtra("DESC", feature.getDesc());
                startActivity(intent);
            }
        });
    }


}
