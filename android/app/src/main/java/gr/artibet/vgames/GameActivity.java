package gr.artibet.vgames;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import gr.artibet.vgames.api.GameAPI;
import gr.artibet.vgames.models.Game;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameActivity extends AppCompatActivity {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    private int mGameId;

    // ---------------------------------------------------------------------------------------
    // onCreate
    // ---------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Get intent data
        Intent intent = getIntent();
        mGameId = intent.getIntExtra("GAME_ID", 0);

        // Set title and back arrow
        getSupportActionBar().setTitle(R.string.game_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Fetch Game
        fetchGame();

    }

    // ---------------------------------------------------------------------------------------
    // Options menu creation
    // ---------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            // Go back
            case android.R.id.home:
                onBackPressed();
                break;

            // Refresh results
            case R.id.action_game_refresh:
                fetchGame();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // ---------------------------------------------------------------------------------------
    // Fetch game data from API
    // ---------------------------------------------------------------------------------------
    private void fetchGame() {

        // Set wait fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.game_fragment_container, new WaitFragment(), null);
        ft.commit();

        // Fetch game data from API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GameAPI gameAPI = retrofit.create(GameAPI.class);

        Call<Game> call = gameAPI.getGame(mGameId);

        call.enqueue(new Callback<Game>() {
            @Override
            public void onResponse(Call<Game> call, Response<Game> response) {
                if (!response.isSuccessful()) {

                    // Set error message fragment
                    MessageFragment mf = new MessageFragment();
                    mf.setMessage(getString(R.string.game_fetch_error));
                    getSupportFragmentManager().beginTransaction().replace(R.id.game_fragment_container, mf, null).commit();

                } else {

                    // Get game object set game fragment
                    Game game = response.body();
                    GameFragment gameFragment = new GameFragment();
                    gameFragment.setGame(game);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.game_fragment_container, gameFragment, null)
                            .commit();
                }

            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {

                // Set error message fragment
                MessageFragment mf = new MessageFragment();
                mf.setMessage(getString(R.string.game_fetch_error));
                getSupportFragmentManager().beginTransaction().replace(R.id.game_fragment_container, mf, null).commit();

            }
        });
    }


}
