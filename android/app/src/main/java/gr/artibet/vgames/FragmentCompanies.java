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

import gr.artibet.vgames.adapters.CompanyAdapter;
import gr.artibet.vgames.api.CompanyAPI;
import gr.artibet.vgames.models.Company;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCompanies extends Fragment {

    private MainActivity mainActivity;
    private ImageView refreshButton;

    private RecyclerView recyclerView;
    private CompanyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Company> companyList = new ArrayList<>();

    public FragmentCompanies() {
        // Required empty public constructor
    }

    public static FragmentCompanies newInstance() {
        FragmentCompanies fragment = new FragmentCompanies();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hold a reference to main activity
        mainActivity = (MainActivity)getActivity();

        // Fetch companies
        fetchCompanies();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_companies, container, false);

        // Set refresh button click listener
        refreshButton = view.findViewById(R.id.refreshCompaniesButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchCompanies();
            }
        });

        // Initialize recycler view
        buildRecyclerView(view);

        return view;
    }


    // Fetch genres from API
    private void fetchCompanies() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CompanyAPI companyAPI = retrofit.create(CompanyAPI.class);

        Call<List<Company>> call = companyAPI.getCompanies();

        call.enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                if (!response.isSuccessful()) {

                    // Toast failure message
                    mainActivity.showErrorToast(getString(R.string.company_fetch_error));
                    return;
                }

                // Change adapters data
                companyList.clear();
                for (Company company : response.body()) {
                    companyList.add(company);
                }
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {

                // Toast failure message
                mainActivity.showErrorToast(getString(R.string.company_fetch_error));
            }
        });
    }

    // Build recycler view
    private void buildRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.companiesRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CompanyAdapter(this.companyList);
        recyclerView.setAdapter(adapter);

        // Set item click listener
        adapter.setOnItemClickListener(new CompanyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Company company = companyList.get(position);

                // Build filter part of url
                Intent intent = new Intent(getActivity(), ResultsActivity.class);
                intent.putExtra("TITLE", company.getDesc());
                intent.putExtra("QUERY", "games.json?company=" + company.getId());
                startActivity(intent);

            }
        });
    }


}
