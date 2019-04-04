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

import gr.artibet.vgames.adapters.CompanyAdapter;
import gr.artibet.vgames.models.Company;

public class CompaniesFragment extends Fragment {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    private List<Company> mCompanyList;

    private RecyclerView recyclerView;
    private CompanyAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    // ---------------------------------------------------------------------------------------
    // Default constructor
    // ---------------------------------------------------------------------------------------
    public CompaniesFragment() {
        mCompanyList = new ArrayList<Company>();
    }

    // ---------------------------------------------------------------------------------------
    // Set company list
    // ---------------------------------------------------------------------------------------
    public void setCompanyList(List<Company> companyList) {
        mCompanyList = companyList;
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
        View view =  inflater.inflate(R.layout.fragment_companies, container, false);

        // Initialize recycler view
        buildRecyclerView(view);

        return view;
    }

    // ---------------------------------------------------------------------------------------
    // Build recycler view
    // ---------------------------------------------------------------------------------------
    private void buildRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.companiesRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CompanyAdapter(mCompanyList);
        recyclerView.setAdapter(adapter);

        // Set item click listener
        adapter.setOnItemClickListener(new CompanyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Company company = mCompanyList.get(position);

                Intent intent = new Intent(getActivity(), ResultsActivity.class);
                intent.putExtra("TITLE", company.getDesc());
                intent.putExtra("QUERY", "games.json?company=" + company.getId());
                startActivity(intent);

            }
        });
    }

}
