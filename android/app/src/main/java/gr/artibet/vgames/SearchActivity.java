package gr.artibet.vgames;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import gr.artibet.vgames.api.CompanyAPI;
import gr.artibet.vgames.api.FeatureAPI;
import gr.artibet.vgames.api.GenreAPI;
import gr.artibet.vgames.api.LanguageAPI;
import gr.artibet.vgames.api.PlatformAPI;
import gr.artibet.vgames.models.Company;
import gr.artibet.vgames.models.Feature;
import gr.artibet.vgames.models.Genre;
import gr.artibet.vgames.models.Language;
import gr.artibet.vgames.models.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    // ---------------------------------------------------------------------------------------
    // Constants for fetching status
    // ---------------------------------------------------------------------------------------
    private static final int FETCH_PENDING = 0;
    private static final int FETCH_SUCCESS = 1;
    private static final int FETCH_ERROR = 2;
    private static final int FETCH_EMPTY = 3;

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    List<Genre> mGenreList;
    List<Company> mCompanyList;
    List<Feature> mFeatureList;
    List<Platform> mPlatformList;
    List<Language> mLanguageList;

    // fetch status - 1 indicates successful fetching
    int mGenreFetchStatus = FETCH_PENDING;
    int mCompaniesFetchStatus = FETCH_PENDING;
    int mFeaturesFetchStatus = FETCH_PENDING;
    int mPlantformsFetchStatus = FETCH_PENDING;
    int mLanguagesFetchStatus = FETCH_PENDING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Set title
        getSupportActionBar().setTitle(getResources().getString(R.string.search_title));

        // if saveInstanceState isn't null activity resumed
        if (savedInstanceState == null) {
            // Set initially wait fragment
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.search_fragment_container, new WaitFragment(), null);
            ft.commit();
        }

        // Retrieve spinners' data
        fetchGenre();
        fetchCompanies();
        fetchFeatures();
        fetchPlatforms();
        fetchLanguages();

    }

    // ---------------------------------------------------------------------------------------
    // Create options menu
    // ---------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);

        return true;
    }

    // ---------------------------------------------------------------------------------------
    // Handle option menu actions
    // ---------------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
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

                }
                else {

                    // Gen genre list and add empty option
                    mGenreList = response.body();
                    Genre emptyGenre = new Genre(-1, "(" + getString(R.string.choose_genre) + ")");
                    mGenreList.add(0, emptyGenre);
                    mGenreFetchStatus = FETCH_SUCCESS;

                    // If all spinners have been fetched
                    // set search fragment
                    if (!isDataPending()) setSearchFragment();


                }

            }

            // Fetch error
            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {


            }
        });
    }

    // ---------------------------------------------------------------------------------------
    // Fetch companies
    // ---------------------------------------------------------------------------------------
    private void fetchCompanies() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CompanyAPI api = retrofit.create(CompanyAPI.class);

        Call<List<Company>> call = api.getCompanies();

        call.enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {

                // 404 or something
                if (!response.isSuccessful()) {


                }
                else {

                    // Gen company list and add empty option
                    mCompanyList = response.body();
                    Company emptyCompany = new Company(-1, "(" + getString(R.string.choose_company) + ")");
                    mCompanyList.add(0, emptyCompany);
                    mCompaniesFetchStatus = FETCH_SUCCESS;


                    // If all spinners have been fetched
                    // set search fragment
                    if (!isDataPending()) setSearchFragment();
                }

            }

            // Fetch error
            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {


            }
        });
    }

    // ---------------------------------------------------------------------------------------
    // Fetch features
    // ---------------------------------------------------------------------------------------
    private void fetchFeatures() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FeatureAPI api = retrofit.create(FeatureAPI.class);

        Call<List<Feature>> call = api.getFeatures();

        call.enqueue(new Callback<List<Feature>>() {
            @Override
            public void onResponse(Call<List<Feature>> call, Response<List<Feature>> response) {

                // 404 or something
                if (!response.isSuccessful()) {

                }
                else {

                    // Gen feature list and add empty option
                    mFeatureList = response.body();
                    mFeaturesFetchStatus = FETCH_SUCCESS;
                    Feature emptyFeature = new Feature(-1, "(" + getString(R.string.choose_feature) + ")");
                    mFeatureList.add(0, emptyFeature);

                    // If all spinners have been fetched
                    // set search fragment
                    if (!isDataPending()) setSearchFragment();

                }

            }

            // Fetch error
            @Override
            public void onFailure(Call<List<Feature>> call, Throwable t) {


            }
        });
    }


    // ---------------------------------------------------------------------------------------
    // Fetch platforms
    // ---------------------------------------------------------------------------------------
    private void fetchPlatforms() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PlatformAPI api = retrofit.create(PlatformAPI.class);

        Call<List<Platform>> call = api.getPlatforms();

        call.enqueue(new Callback<List<Platform>>() {
            @Override
            public void onResponse(Call<List<Platform>> call, Response<List<Platform>> response) {

                // 404 or something
                if (!response.isSuccessful()) {


                }
                else {

                    // Gen platform list and add empty option
                    mPlatformList = response.body();
                    mPlantformsFetchStatus = FETCH_SUCCESS;
                    Platform emptyPlatform = new Platform(-1, "(" + getString(R.string.choose_platform) + ")");
                    mPlatformList.add(0, emptyPlatform);

                    // If all spinners have been fetched
                    // set search fragment
                    if (!isDataPending()) setSearchFragment();

                }

            }

            // Fetch error
            @Override
            public void onFailure(Call<List<Platform>> call, Throwable t) {


            }
        });
    }

    // ---------------------------------------------------------------------------------------
    // Fetch languages
    // ---------------------------------------------------------------------------------------
    private void fetchLanguages() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LanguageAPI api = retrofit.create(LanguageAPI.class);

        Call<List<Language>> call = api.getLanguages();

        call.enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {

                // 404 or something
                if (!response.isSuccessful()) {


                }
                else {

                    // Gen language list and add empty option
                    mLanguageList = response.body();
                    mLanguagesFetchStatus = FETCH_SUCCESS;
                    Language emptyLanguage = new Language(-1, "(" + getString(R.string.choose_language) + ")");
                    mLanguageList.add(0, emptyLanguage);

                    // If all spinners have been fetched
                    // set search fragment
                    if (!isDataPending()) setSearchFragment();
                }

            }

            // Fetch error
            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {



            }
        });
    }

    // ---------------------------------------------------------------------------------------
    // Check if data is pending....
    // ---------------------------------------------------------------------------------------
    private boolean isDataPending() {
        if (mGenreFetchStatus == FETCH_PENDING ||
            mCompaniesFetchStatus == FETCH_PENDING ||
            mFeaturesFetchStatus == FETCH_PENDING ||
            mPlantformsFetchStatus == FETCH_PENDING ||
            mLanguagesFetchStatus == FETCH_PENDING) {

            return true;
        }
        else {
            return false;
        }
    }

    // ---------------------------------------------------------------------------------------
    // Replace wait fragment with search fragment
    // ---------------------------------------------------------------------------------------
    private void setSearchFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        SearchFragment searchFragment = new SearchFragment();

        // Set lists
        searchFragment.setGenreList(mGenreList);
        searchFragment.setCompanyList(mCompanyList);
        searchFragment.setFeatureList(mFeatureList);
        searchFragment.setPlatformList(mPlatformList);
        searchFragment.setLanguageList(mLanguageList);

        ft.replace(R.id.search_fragment_container,searchFragment, null);
        ft.commit();


    }
}
