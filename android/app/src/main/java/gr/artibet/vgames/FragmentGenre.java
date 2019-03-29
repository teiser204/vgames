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

import gr.artibet.vgames.api.GenreAPI;
import gr.artibet.vgames.adapters.GenreAdapter;
import gr.artibet.vgames.models.Genre;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentGenre extends Fragment {

    private MainActivity mainActivity;
    private ImageView refreshButton;

    private RecyclerView recyclerView;
    private GenreAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Genre> genreList = new ArrayList<>();

    public FragmentGenre() {
        // Required empty public constructor
    }

    public static FragmentGenre newInstance() {
        FragmentGenre fragment = new FragmentGenre();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hold a reference to main activity
        mainActivity = (MainActivity)getActivity();

        // Fetch genres
        fetchGenres();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_genre, container, false);

        // Set refresh button click listener
        refreshButton = view.findViewById(R.id.refreshGenreListButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchGenres();
            }
        });

        // Initialize recycler view
        buildRecyclerView(view);

        return view;
    }


    // Fetch genres from API
    private void fetchGenres() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GenreAPI genreAPI = retrofit.create(GenreAPI.class);

        Call<List<Genre>> call = genreAPI.getGenres();

        call.enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                if (!response.isSuccessful()) {

                    // Toast failure message
                    mainActivity.showErrorToast(getString(R.string.genre_fetch_error));
                    return;
                }

                // Change adapters data
                genreList.clear();
                for (Genre genre : response.body()) {
                    genreList.add(genre);
                }
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {

                // Toast failure message
                mainActivity.showErrorToast(getString(R.string.genre_fetch_error));
            }
        });
    }

    // Build recycler view
    private void buildRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.genreRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GenreAdapter(this.genreList);
        recyclerView.setAdapter(adapter);

        // Set item click listener
        adapter.setOnItemClickListener(new GenreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Genre genre = genreList.get(position);

                // Build filter part of url
                Intent intent = new Intent(getActivity(), ResultsActivity.class);
                intent.putExtra("TITLE", genre.getDesc());
                intent.putExtra("QUERY", "games.json?genre=" + genre.getId());
                startActivity(intent);
            }
        });
    }


}
