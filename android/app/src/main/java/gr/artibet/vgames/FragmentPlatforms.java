package gr.artibet.vgames;

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

import gr.artibet.vgames.adapters.PlatformAdapter;
import gr.artibet.vgames.api.PlatformAPI;
import gr.artibet.vgames.models.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPlatforms extends Fragment {

    private MainActivity mainActivity;
    private ImageView refreshButton;

    private RecyclerView recyclerView;
    private PlatformAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Platform> platformList = new ArrayList<>();

    public FragmentPlatforms() {
        // Required empty public constructor
    }

    public static FragmentPlatforms newInstance() {
        FragmentPlatforms fragment = new FragmentPlatforms();
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

        View view = inflater.inflate(R.layout.fragment_platforms, container, false);

        // Set refresh button click listener
        refreshButton = view.findViewById(R.id.refreshPlatformListButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchPlatforms();
            }
        });

        // Initialize recycler view
        recyclerView = view.findViewById(R.id.platformRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PlatformAdapter(this.platformList);
        recyclerView.setAdapter(adapter);
        fetchPlatforms();

        return view;
    }


    // Fetch genres from API
    private void fetchPlatforms() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlatformAPI platformAPI = retrofit.create(PlatformAPI.class);

        Call<List<Platform>> call = platformAPI.getPlatforms();

        call.enqueue(new Callback<List<Platform>>() {
            @Override
            public void onResponse(Call<List<Platform>> call, Response<List<Platform>> response) {
                if (!response.isSuccessful()) {

                    // Toast failure message
                    mainActivity.showErrorToast(getString(R.string.feature_fetch_error));
                    return;
                }

                // Change adapters data
                platformList = response.body();
                adapter.setPlatformList(platformList);
            }

            @Override
            public void onFailure(Call<List<Platform>> call, Throwable t) {

                // Toast failure message
                mainActivity.showErrorToast(getString(R.string.platform_fetch_error));
            }
        });
    }



}
