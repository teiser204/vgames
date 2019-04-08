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
import gr.artibet.vgames.api.LanguageAPI;
import gr.artibet.vgames.models.Company;
import gr.artibet.vgames.models.Language;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LanguagesFragmentContainer extends Fragment {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    private List<Language> mLanguageList = null;
    private ImageView mRefreshButton;
    private FragmentManager mFragmentManager;

    // ---------------------------------------------------------------------------------------
    // Default constructor
    // ---------------------------------------------------------------------------------------
    public LanguagesFragmentContainer() {
        // Required empty public constructor
    }

    // ---------------------------------------------------------------------------------------
    // onCreateView
    // ---------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_languages_container, container, false);

        mFragmentManager = getActivity().getSupportFragmentManager();

        if (savedInstanceState == null) {

            // Set initially wait fragment
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.add(R.id.languagesFragmentContainer, new WaitFragment(), null);
            ft.commit();
        }

        // Set refresh button click listener
        mRefreshButton = view.findViewById(R.id.refreshLanguagesButton);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchLanguages();
            }
        });

        // Retrieve genre
        fetchLanguages();

        return view;
    }

    // ---------------------------------------------------------------------------------------
    // Fetch languages
    // ---------------------------------------------------------------------------------------
    private void fetchLanguages() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.serres.gr/vgames/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LanguageAPI api = retrofit.create(LanguageAPI.class);

        Call<List<Language>> call = api.getLanguages();

        call.enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {

                // 404 or something
                if (!response.isSuccessful()) {

                    // Set message fragment
                    FragmentTransaction ft = mFragmentManager.beginTransaction();
                    MessageFragment messageFragment = new MessageFragment();
                    messageFragment.setMessage(getString(R.string.language_fetch_error));
                    ft.replace(R.id.languagesFragmentContainer, messageFragment, null);
                    ft.commit();
                }
                else {

                    mLanguageList = response.body();

                    if (mLanguageList == null || mLanguageList.size() == 0) {
                        FragmentTransaction ft = mFragmentManager.beginTransaction();
                        MessageFragment messageFragment = new MessageFragment();
                        messageFragment.setMessage(getString(R.string.no_languages));
                        ft.replace(R.id.languagesFragmentContainer, messageFragment, null);
                        ft.commit();
                    } else {
                        FragmentTransaction ft = mFragmentManager.beginTransaction();
                        LanguagesFragment languagesFragment = new LanguagesFragment();
                        languagesFragment.setLanguageList(mLanguageList);
                        ft.replace(R.id.languagesFragmentContainer, languagesFragment, null);
                        ft.commit();
                    }

                }

            }

            // Fetch error
            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {

                FragmentTransaction ft = mFragmentManager.beginTransaction();
                MessageFragment messageFragment = new MessageFragment();
                messageFragment.setMessage(getString(R.string.language_fetch_error));
                ft.replace(R.id.languagesFragmentContainer, messageFragment, null);
                ft.commit();

            }
        });
    }


}
