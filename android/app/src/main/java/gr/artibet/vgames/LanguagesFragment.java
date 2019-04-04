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

import gr.artibet.vgames.adapters.LanguageAdapter;
import gr.artibet.vgames.models.Language;

public class LanguagesFragment extends Fragment {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    private List<Language> mLanguageList;

    private RecyclerView recyclerView;
    private LanguageAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // ---------------------------------------------------------------------------------------
    // Default constructor
    // ---------------------------------------------------------------------------------------
    public LanguagesFragment() {
        mLanguageList = new ArrayList<Language>();
    }

    // ---------------------------------------------------------------------------------------
    // Set language list
    // ---------------------------------------------------------------------------------------
    public void setLanguageList(List<Language> languageList) {
        mLanguageList = languageList;
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
        View view =  inflater.inflate(R.layout.fragment_languages, container, false);

        // Initialize recycler view
        buildRecyclerView(view);

        return view;
    }

    // ---------------------------------------------------------------------------------------
    // Build recycler view
    // ---------------------------------------------------------------------------------------
    private void buildRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.languagesRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LanguageAdapter(mLanguageList);
        recyclerView.setAdapter(adapter);

        // Set item click listener
        adapter.setOnItemClickListener(new LanguageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Language language = mLanguageList.get(position);

                Intent intent = new Intent(getActivity(), ResultsActivity.class);
                intent.putExtra("TITLE", language.getDesc());
                intent.putExtra("QUERY", "games.json?language=" + language.getId());
                startActivity(intent);

            }
        });
    }

}
