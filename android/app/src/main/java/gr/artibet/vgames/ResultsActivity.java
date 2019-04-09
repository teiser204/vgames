package gr.artibet.vgames;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import gr.artibet.vgames.api.ApiSettings;
import gr.artibet.vgames.api.GameAPI;
import gr.artibet.vgames.models.Game;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultsActivity extends AppCompatActivity {

    // class members
    private String mTitle;
    private String mQuery;
    private List<Game> mGameList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get intent data
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("TITLE");
        mQuery = intent.getStringExtra("QUERY");

        // Set title and back arrow
        getSupportActionBar().setTitle(mTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Retrieve Games
        fetchGames();

    }

    // ---------------------------------------------------------------------------------------
    // Options menu creation
    // ---------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.results_menu, menu);

        return true;

    }


    // ---------------------------------------------------------------------------------------
    // Menu listeners
    // ---------------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            // Go back
            case android.R.id.home:
                onBackPressed();
                break;

            // Refresh results
            case R.id.action_refresh:
                fetchGames();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // ---------------------------------------------------------------------------------------
    // Retrive games from API
    // ---------------------------------------------------------------------------------------
    private void fetchGames() {

        // Set wait fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, new WaitFragment(), null);
        ft.commit();

        ApiSettings apiSettings = new ApiSettings(this);

        // Retrieve from API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiSettings.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GameAPI gameAPI = retrofit.create(GameAPI.class);

        Call<List<Game>> call = gameAPI.getGames(mQuery);

        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {

                if (!response.isSuccessful()) {

                    // Set error message fragment
                    MessageFragment mf = new MessageFragment();
                    mf.setMessage(getString(R.string.games_fetch_error));
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mf, null).commit();

                } else {

                    // Change adapters data
                    mGameList = response.body();

                    // Set fragment results or no results
                    if (findViewById(R.id.fragment_container) != null) {

                        Fragment fragment;

                        if (mGameList == null || mGameList.size() == 0) {
                            fragment = new MessageFragment();
                            getSupportActionBar().setTitle(mTitle + " (0)");
                        } else {
                            fragment = new ResultsFragment();
                            ((ResultsFragment) fragment).setGameList(mGameList);

                            // Set number of games into title
                            getSupportActionBar().setTitle(mTitle + " (" + mGameList.size() + ")");
                        }

                        // Set Results fragment
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.fragment_container, fragment, null);
                        ft.commit();
                    }

                }

            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {

                // Set error message fragment
                MessageFragment mf = new MessageFragment();
                mf.setMessage(getString(R.string.games_fetch_error));
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, mf, null).commit();
            }
        });
    }


}

