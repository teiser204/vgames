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

import gr.artibet.vgames.api.ApiSettings;
import gr.artibet.vgames.api.CompanyAPI;
import gr.artibet.vgames.api.PlatformAPI;
import gr.artibet.vgames.models.Company;
import gr.artibet.vgames.models.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PlatformsFragmentContainer extends Fragment {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    private List<Platform> mPlatformList = null;
    private ImageView mRefreshButton;
    private FragmentManager mFragmentManager;

    // ---------------------------------------------------------------------------------------
    // Default constructor
    // ---------------------------------------------------------------------------------------
    public PlatformsFragmentContainer() {
        // Required empty public constructor
    }

    // ---------------------------------------------------------------------------------------
    // onCreateView
    // ---------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_platforms_container, container, false);

        mFragmentManager = getActivity().getSupportFragmentManager();

        if (savedInstanceState == null) {

            // Set initially wait fragment
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.add(R.id.platformsFragmentContainer, new WaitFragment(), null);
            ft.commit();
        }

        // Set refresh button click listener
        mRefreshButton = view.findViewById(R.id.refreshPlatformsButton);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchPlatforms();
            }
        });

        // Retrieve genre
        fetchPlatforms();

        return view;
    }

    // ---------------------------------------------------------------------------------------
    // Fetch companies
    // ---------------------------------------------------------------------------------------
    private void fetchPlatforms() {

        ApiSettings apiSettings = new ApiSettings(getActivity());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiSettings.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlatformAPI api = retrofit.create(PlatformAPI.class);

        Call<List<Platform>> call = api.getPlatforms(apiSettings.getPlatformsUrl());

        call.enqueue(new Callback<List<Platform>>() {
            @Override
            public void onResponse(Call<List<Platform>> call, Response<List<Platform>> response) {

                // 404 or something
                if (!response.isSuccessful()) {

                    // Set message fragment
                    FragmentTransaction ft = mFragmentManager.beginTransaction();
                    MessageFragment messageFragment = new MessageFragment();
                    messageFragment.setMessage(getString(R.string.platform_fetch_error));
                    ft.replace(R.id.platformsFragmentContainer, messageFragment, null);
                    ft.commit();
                }
                else {

                    mPlatformList = response.body();

                    if (mPlatformList == null || mPlatformList.size() == 0) {
                        FragmentTransaction ft = mFragmentManager.beginTransaction();
                        MessageFragment messageFragment = new MessageFragment();
                        messageFragment.setMessage(getString(R.string.no_platforms));
                        ft.replace(R.id.platformsFragmentContainer, messageFragment, null);
                        ft.commit();
                    } else {
                        FragmentTransaction ft = mFragmentManager.beginTransaction();
                        PlatformsFragment platformsFragment = new PlatformsFragment();
                        platformsFragment.setPlatformList(mPlatformList);
                        ft.replace(R.id.platformsFragmentContainer, platformsFragment, null);
                        ft.commit();
                    }

                }

            }

            // Fetch error
            @Override
            public void onFailure(Call<List<Platform>> call, Throwable t) {

                FragmentTransaction ft = mFragmentManager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setMessage(getString(R.string.platform_fetch_error));
                ft.replace(R.id.platformsFragmentContainer, messageFragment, null);
                ft.commit();

            }
        });
    }


}
