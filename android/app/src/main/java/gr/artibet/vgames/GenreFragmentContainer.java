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

import java.util.List;

import gr.artibet.vgames.api.GameAPI;
import gr.artibet.vgames.api.GenreAPI;
import gr.artibet.vgames.models.Game;
import gr.artibet.vgames.models.Genre;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class GenreFragmentContainer extends Fragment {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    private List<Genre> mGenreList = null;
    private ImageView mRefreshButton;
    private FragmentManager mFragmentManager;

    public GenreFragmentContainer() {
        // Required empty public constructor
    }

    // ---------------------------------------------------------------------------------------
    // onCreate
    // ---------------------------------------------------------------------------------------
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // ---------------------------------------------------------------------------------------
    // onCreateView
    // ---------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_genre_container, container, false);

        mFragmentManager = getActivity().getSupportFragmentManager();

        if (savedInstanceState == null) {

            // Set initially wait fragment
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.add(R.id.genreFragmentContainer, new WaitFragment(), null);
            ft.commit();
        }

        // Set refresh button click listener
        mRefreshButton = view.findViewById(R.id.refreshGenreButton);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchGenre();
            }
        });

        // Retrieve genre
        fetchGenre();

        return view;
    }

    // ---------------------------------------------------------------------------------------
    // Fetch genre
    // ---------------------------------------------------------------------------------------
    private void fetchGenre() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GenreAPI api = retrofit.create(GenreAPI.class);

        Call<List<Genre>> call = api.getGenres();

        call.enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {

                // 404 or something
                if (!response.isSuccessful()) {

                    // Set message fragment
                    FragmentTransaction ft = mFragmentManager.beginTransaction();
                    MessageFragment messageFragment = new MessageFragment();
                    messageFragment.setMessage(getString(R.string.genre_fetch_error));
                    ft.replace(R.id.genreFragmentContainer, messageFragment, null);
                    ft.commit();
                }
                else {

                    mGenreList = response.body();

                    if (mGenreList == null || mGenreList.size() == 0) {
                        FragmentTransaction ft = mFragmentManager.beginTransaction();
                        MessageFragment messageFragment = new MessageFragment();
                        messageFragment.setMessage(getString(R.string.no_genres));
                        ft.replace(R.id.genreFragmentContainer, messageFragment, null);
                        ft.commit();
                    } else {
                        FragmentTransaction ft = mFragmentManager.beginTransaction();
                        GenreFragment genreFragment = new GenreFragment();
                        genreFragment.setGenreList(mGenreList);
                        ft.replace(R.id.genreFragmentContainer, genreFragment, null);
                        ft.commit();
                    }

                }

            }

            // Fetch error
            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {

                FragmentTransaction ft = mFragmentManager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setMessage(getString(R.string.genre_fetch_error));
                ft.replace(R.id.genreFragmentContainer, messageFragment, null);
                ft.commit();

            }
        });
    }

}
