package gr.artibet.vgames;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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
    private TextView mTvTitle;
    private ImageView mIvRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // if saveInstanceState isn't null activity resumed
        if (savedInstanceState == null) {
            // Set initially wait fragment
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_container, new WaitFragment(), null);
            ft.commit();
        }

        // Set refresh button listener
        mIvRefresh = findViewById(R.id.refreshResultButton);
        mIvRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchGames();
            }
        });

        // Get intent data
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("TITLE");
        mQuery = intent.getStringExtra("QUERY");

        // Set title
        mTvTitle = findViewById(R.id.results_title);
        mTvTitle.setText(mTitle);

        // Set back button on action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Retrieve Games
        fetchGames();

    }


    // retrieve games for mQuery
    private void fetchGames() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GameAPI gameAPI = retrofit.create(GameAPI.class);

        Call<List<Game>> call = gameAPI.getGames(mQuery);

        call.enqueue(new Callback<List<Game>>() {
            @Override
            public void onResponse(Call<List<Game>> call, Response<List<Game>> response) {
                if (!response.isSuccessful()) {

                    // Toast failure message
                    showErrorToast(getString(R.string.games_fetch_error));
                    mTvTitle.setText(mTitle);
                }

                // Change adapters data
                mGameList = response.body();

                // Set fragment results or no results
                if (findViewById(R.id.fragment_container) != null) {

                    Fragment fragment;

                    if (mGameList == null || mGameList.size() == 0) {
                        fragment = new NoResultsFragment();
                        mTvTitle.setText(mTitle + " (0)");
                    } else {
                        fragment = new ResultsFragment();
                        ((ResultsFragment) fragment).setGameList(mGameList);

                        // Set number of games into title
                        mTvTitle.setText(mTitle + " (" + mGameList.size() + ")");
                    }

                    // Set fragment
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_container, fragment, null);
                    ft.commit();
                }

            }

            @Override
            public void onFailure(Call<List<Game>> call, Throwable t) {

                // Toast failure message
                showErrorToast(getString(R.string.games_fetch_error));
                mTvTitle.setText(mTitle);
            }
        });
    }

    public void showErrorToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_error, (ViewGroup) findViewById(R.id.toast_root));

        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}

