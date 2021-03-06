package gr.artibet.vgames;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import gr.artibet.vgames.api.ApiSettings;
import gr.artibet.vgames.api.GameAPI;
import gr.artibet.vgames.models.Game;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RecentFragment extends Fragment {

    // Class members
    private List<Game> mGameList = null;
    private ImageView mRefreshButton;
    private FragmentManager mFragmentManager;

    // Default contractor
    public RecentFragment() {
        // Required empty public constructor
        mGameList = new ArrayList<>();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent, container, false);

        mFragmentManager = getActivity().getSupportFragmentManager();

        if (savedInstanceState == null) {

            // Set initially wait fragment
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.add(R.id.recentFragmentContainer, new WaitFragment(), null);
            ft.commitAllowingStateLoss();
        }

        // Set refresh button click listener
        mRefreshButton = view.findViewById(R.id.refreshRecentGamesButton);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchRecentGames();
            }
        });

        // Retrieve Games
        fetchRecentGames();

        return view;
    }

    // ---------------------------------------------------------------------------------------
    // Fetch top games
    // ---------------------------------------------------------------------------------------
    private void fetchRecentGames() {

        ApiSettings apiSettings = new ApiSettings(getActivity());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiSettings.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GameAPI gameAPI = retrofit.create(GameAPI.class);

        Call<List<Game>> call = gameAPI.getGames(apiSettings.getRecentGamesUrl());

        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {

                // 404 or something
                if (!response.isSuccessful()) {

                    // Set message fragment
                    FragmentTransaction ft = mFragmentManager.beginTransaction();
                    MessageFragment messageFragment = new MessageFragment();
                    messageFragment.setMessage(getString(R.string.games_fetch_error));
                    ft.replace(R.id.recentFragmentContainer, messageFragment, null);
                    ft.commitAllowingStateLoss();
                }
                else {

                    mGameList = response.body();

                    if (mGameList == null || mGameList.size() == 0) {
                        FragmentTransaction ft = mFragmentManager.beginTransaction();
                        MessageFragment messageFragment = new MessageFragment();
                        messageFragment.setMessage(getString(R.string.no_results_text));
                        ft.replace(R.id.recentFragmentContainer, messageFragment, null);
                        ft.commitAllowingStateLoss();
                    } else {
                        FragmentTransaction ft = mFragmentManager.beginTransaction();
                        ResultsFragment resultsFragment = new ResultsFragment();
                        resultsFragment.setGameList(mGameList);
                        ft.replace(R.id.recentFragmentContainer, resultsFragment, null);
                        ft.commitAllowingStateLoss();
                    }

                }

            }

            // Fetch error
            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {

                FragmentTransaction ft = mFragmentManager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setMessage(getString(R.string.games_fetch_error));
                ft.replace(R.id.recentFragmentContainer, messageFragment, null);
                ft.commitAllowingStateLoss();

            }
        });
    }

}
