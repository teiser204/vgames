package gr.artibet.vgames;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import gr.artibet.vgames.adapters.GenreAdapter;
import gr.artibet.vgames.models.Genre;


public class GenreFragment extends Fragment {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    private List<Genre> mGenreList;

    private RecyclerView recyclerView;
    private GenreAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // ---------------------------------------------------------------------------------------
    // Default constructor
    // ---------------------------------------------------------------------------------------
    public GenreFragment() {
        mGenreList = new ArrayList<Genre>();
    }

    // ---------------------------------------------------------------------------------------
    // Set genre list
    // ---------------------------------------------------------------------------------------
    public void setGenreList(List<Genre> genreList) {
        mGenreList = genreList;
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
        View view =  inflater.inflate(R.layout.fragment_genre, container, false);

        // Initialize recycler view
        buildRecyclerView(view);

        return view;
    }

    // ---------------------------------------------------------------------------------------
    // Build recycler view
    // ---------------------------------------------------------------------------------------
    private void buildRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.genreRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GenreAdapter(mGenreList);
        recyclerView.setAdapter(adapter);

        // Set item click listener
        adapter.setOnItemClickListener(new GenreAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Genre genre = mGenreList.get(position);

                Intent intent = new Intent(getActivity(), ResultsActivity.class);
                intent.putExtra("TITLE", genre.getDesc());
                intent.putExtra("QUERY", "games.json?genre=" + genre.getId());
                startActivity(intent);

            }
        });
    }

}
