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

import gr.artibet.vgames.api.CompanyAPI;
import gr.artibet.vgames.models.Company;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CompaniesFragmentContainer extends Fragment {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    private List<Company> mCompanyList = null;
    private ImageView mRefreshButton;
    private FragmentManager mFragmentManager;

    // ---------------------------------------------------------------------------------------
    // Default constructor
    // ---------------------------------------------------------------------------------------
    public CompaniesFragmentContainer() {
        // Required empty public constructor
    }

    // ---------------------------------------------------------------------------------------
    // onCreateView
    // ---------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_companies_container, container, false);

        mFragmentManager = getActivity().getSupportFragmentManager();

        if (savedInstanceState == null) {

            // Set initially wait fragment
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.add(R.id.companiesFragmentContainer, new WaitFragment(), null);
            ft.commit();
        }

        // Set refresh button click listener
        mRefreshButton = view.findViewById(R.id.refreshCompaniesButton);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchCompanies();
            }
        });

        // Retrieve genre
        fetchCompanies();

        return view;
    }

    // ---------------------------------------------------------------------------------------
    // Fetch companies
    // ---------------------------------------------------------------------------------------
    private void fetchCompanies() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.serres.gr/vgames/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CompanyAPI api = retrofit.create(CompanyAPI.class);

        Call<List<Company>> call = api.getCompanies();

        call.enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {

                // 404 or something
                if (!response.isSuccessful()) {

                    // Set message fragment
                    FragmentTransaction ft = mFragmentManager.beginTransaction();
                    MessageFragment messageFragment = new MessageFragment();
                    messageFragment.setMessage(getString(R.string.company_fetch_error));
                    ft.replace(R.id.companiesFragmentContainer, messageFragment, null);
                    ft.commit();
                }
                else {

                    mCompanyList = response.body();

                    if (mCompanyList == null || mCompanyList.size() == 0) {
                        FragmentTransaction ft = mFragmentManager.beginTransaction();
                        MessageFragment messageFragment = new MessageFragment();
                        messageFragment.setMessage(getString(R.string.no_companies));
                        ft.replace(R.id.companiesFragmentContainer, messageFragment, null);
                        ft.commit();
                    } else {
                        FragmentTransaction ft = mFragmentManager.beginTransaction();
                        CompaniesFragment companiesFragment = new CompaniesFragment();
                        companiesFragment.setCompanyList(mCompanyList);
                        ft.replace(R.id.companiesFragmentContainer, companiesFragment, null);
                        ft.commit();
                    }

                }

            }

            // Fetch error
            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {

                FragmentTransaction ft = mFragmentManager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setMessage(getString(R.string.company_fetch_error));
                ft.replace(R.id.companiesFragmentContainer, messageFragment, null);
                ft.commit();

            }
        });
    }


}
