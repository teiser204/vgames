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

import gr.artibet.vgames.adapters.GameAdapter;
import gr.artibet.vgames.models.Game;


public class ResultsFragment extends Fragment {

    // class members
    private List<Game> mGameList;

    private RecyclerView recyclerView;
    private GameAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // Required empty public constructor
    public ResultsFragment() {
        mGameList = new ArrayList<Game>();
    }

    // Set game list
    public void setGameList(List<Game> gameList) {
        mGameList = gameList;
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        // Initialize recycler view
        buildRecyclerView(view);

        return view;
    }

    // Build recycler view
    private void buildRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.resultsRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GameAdapter(getActivity(), this.mGameList);
        recyclerView.setAdapter(adapter);

        // Set item click listener
        adapter.setOnItemClickListener(new GameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Game game = mGameList.get(position);

                // Open game details activity
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("GAME_ID", game.getId());
                startActivity(intent);

            }
        });
    }

}
