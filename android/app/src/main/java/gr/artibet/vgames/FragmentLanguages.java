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

import gr.artibet.vgames.adapters.LanguageAdapter;
import gr.artibet.vgames.api.LanguageAPI;
import gr.artibet.vgames.models.Language;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLanguages extends Fragment {

    private MainActivity mainActivity;
    private ImageView refreshButton;

    private RecyclerView recyclerView;
    private LanguageAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Language> languageList = new ArrayList<>();

    public FragmentLanguages() {
        // Required empty public constructor
    }

    public static FragmentLanguages newInstance() {
        FragmentLanguages fragment = new FragmentLanguages();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hold a reference to main activity
        mainActivity = (MainActivity)getActivity();

        // Fetch languages
        fetchLanguages();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_languages, container, false);

        // Set refresh button click listener
        refreshButton = view.findViewById(R.id.refreshLanguageListButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchLanguages();
            }
        });

        // Initialize recycler view
        buildRecyclerView(view);

        return view;
    }


    // Fetch genres from API
    private void fetchLanguages() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LanguageAPI languageAPI = retrofit.create(LanguageAPI.class);

        Call<List<Language>> call = languageAPI.getLanguages();

        call.enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {
                if (!response.isSuccessful()) {

                    // Toast failure message
                    mainActivity.showErrorToast(getString(R.string.language_fetch_error));
                    return;
                }

                // Change adapters data
                languageList.clear();
                for (Language language : response.body()) {
                    languageList.add(language);
                }
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {

                // Toast failure message
                mainActivity.showErrorToast(getString(R.string.language_fetch_error));
            }
        });
    }


    // Build recycler view
    private void buildRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.languageRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LanguageAdapter(this.languageList);
        recyclerView.setAdapter(adapter);

        // Set item click listener
        adapter.setOnItemClickListener(new LanguageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Language language = languageList.get(position);

                // Build filter part of url
                Intent intent = new Intent(getActivity(), ResultsActivity.class);
                intent.putExtra("ID", language.getId());
                intent.putExtra("TITLE", getResources().getString(R.string.language_title) + ": " + language.getDesc());
                startActivity(intent);
            }
        });
    }

}
