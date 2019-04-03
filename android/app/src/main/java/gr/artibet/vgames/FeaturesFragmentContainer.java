package gr.artibet.vgames;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import gr.artibet.vgames.api.FeatureAPI;
import gr.artibet.vgames.api.GenreAPI;
import gr.artibet.vgames.models.Feature;
import gr.artibet.vgames.models.Genre;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeaturesFragmentContainer extends Fragment {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    private List<Feature> mFeatureList = null;
    private ImageView mRefreshButton;
    private FragmentManager mFragmentManager;

    // ---------------------------------------------------------------------------------------
    // Default constructor
    // ---------------------------------------------------------------------------------------
    public FeaturesFragmentContainer() {
        // Required empty public constructor
    }

    // ---------------------------------------------------------------------------------------
    // onCreateView
    // ---------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_features_container, container, false);

        mFragmentManager = getActivity().getSupportFragmentManager();

        if (savedInstanceState == null) {

            // Set initially wait fragment
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.add(R.id.featuresFragmentContainer, new WaitFragment(), null);
            ft.commit();
        }

        // Set refresh button click listener
        mRefreshButton = view.findViewById(R.id.refreshFeaturesButton);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchFeatures();
            }
        });

        // Retrieve genre
        fetchFeatures();

        return view;
    }

    // ---------------------------------------------------------------------------------------
    // Fetch features
    // ---------------------------------------------------------------------------------------
    private void fetchFeatures() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FeatureAPI api = retrofit.create(FeatureAPI.class);

        Call<List<Feature>> call = api.getFeatures();

        call.enqueue(new Callback<List<Feature>>() {
            @Override
            public void onResponse(Call<List<Feature>> call, Response<List<Feature>> response) {

                // 404 or something
                if (!response.isSuccessful()) {

                    // Set message fragment
                    FragmentTransaction ft = mFragmentManager.beginTransaction();
                    MessageFragment messageFragment = new MessageFragment();
                    messageFragment.setMessage(getString(R.string.feature_fetch_error));
                    ft.replace(R.id.featuresFragmentContainer, messageFragment, null);
                    ft.commit();
                }
                else {

                    mFeatureList = response.body();

                    if (mFeatureList == null || mFeatureList.size() == 0) {
                        FragmentTransaction ft = mFragmentManager.beginTransaction();
                        MessageFragment messageFragment = new MessageFragment();
                        messageFragment.setMessage(getString(R.string.no_features));
                        ft.replace(R.id.featuresFragmentContainer, messageFragment, null);
                        ft.commit();
                    } else {
                        FragmentTransaction ft = mFragmentManager.beginTransaction();
                        FeaturesFragment featuresFragment = new FeaturesFragment();
                        featuresFragment.setmFeatureList(mFeatureList);
                        ft.replace(R.id.featuresFragmentContainer, featuresFragment, null);
                        ft.commit();
                    }

                }

            }

            // Fetch error
            @Override
            public void onFailure(Call<List<Feature>> call, Throwable t) {

                FragmentTransaction ft = mFragmentManager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setMessage(getString(R.string.feature_fetch_error));
                ft.replace(R.id.featuresFragmentContainer, messageFragment, null);
                ft.commit();

            }
        });
    }


}
