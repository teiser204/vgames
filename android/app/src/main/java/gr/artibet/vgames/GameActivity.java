package gr.artibet.vgames;

import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

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
    private Game mGame = null;

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

        // Fetch Game
        fetchGame();
    }

    // ---------------------------------------------------------------------------------------
    // Fetch game data from API
    // ---------------------------------------------------------------------------------------
    private void fetchGame() {
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

                    // Toast failure message
                    //showErrorToast(getString(R.string.games_fetch_error));
                    //mTvTitle.setText(mTitle);
                }

                // Get game object and update UI
                mGame = response.body();
                updateUI();

            }

            @Override
            public void onFailure(Call<Game> call, Throwable t) {

                // Toast failure message
                //showErrorToast(getString(R.string.games_fetch_error));
                //mTvTitle.setText(mTitle);
            }
        });
    }

    // ---------------------------------------------------------------------------------------
    // Update UI for fetched game
    // ---------------------------------------------------------------------------------------
    private void updateUI() {

        // Get views
        ImageView ivImage = findViewById(R.id.ivImage);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvCompany = findViewById(R.id.tvCompany);
        TextView tvYear = findViewById(R.id.tvYear);
        TextView tvRating = findViewById(R.id.tvRating);
        RatingBar rbRating = findViewById(R.id.rbRating);
        TextView tvDesc = findViewById(R.id.tvDesc);


        // Load image
        Glide.with(this)
                .load(mGame.getImage())
                .placeholder(R.drawable.ic_image_placeholder)
                .into(ivImage);

        // Set data
        tvTitle.setText(mGame.getTitle());
        tvCompany.setText(mGame.getCompany().getDesc());
        rbRating.setRating(mGame.getRating());
        tvRating.setText("(" + mGame.getRating() + ")");
        tvDesc.setText(mGame.getDesc());
        if (mGame.getYear() != null) {
            tvYear.setText("(" + mGame.getYear() + ")");
        }
        else {
            tvYear.setText("");
        }


    }
}
