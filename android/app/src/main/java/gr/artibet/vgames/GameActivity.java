package gr.artibet.vgames;

import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import gr.artibet.vgames.api.GameAPI;
import gr.artibet.vgames.models.Feature;
import gr.artibet.vgames.models.Game;
import gr.artibet.vgames.models.Genre;
import gr.artibet.vgames.models.Language;
import gr.artibet.vgames.models.Platform;
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
        TextView tvPrice = findViewById(R.id.tvPrice);
        TextView tvGenres = findViewById(R.id.tvGenres);
        TextView tvFeatures = findViewById(R.id.tvFeatures);
        TextView tvPlatforms = findViewById(R.id.tvPlatforms);
        TextView tvLanguages = findViewById(R.id.tvLanguages);
        Button btVisitPage = findViewById(R.id.btVisitPage);

        // Load image
        Glide.with(this)
                .load(mGame.getImage())
                .placeholder(R.drawable.ic_image_placeholder)
                .into(ivImage);

        // Set simple data
        tvTitle.setText(mGame.getTitle());
        tvCompany.setText(mGame.getCompany().getDesc());
        rbRating.setRating(mGame.getRating());
        tvRating.setText("(" + mGame.getRating() + ")");
        tvDesc.setText(mGame.getDesc());
        tvPrice.setText(String.format("%.2f €", mGame.getPrice()));

        // Game year
        if (mGame.getYear() != null) {
            tvYear.setText("(" + mGame.getYear() + ")");
        }
        else {
            tvYear.setText("");
        }

        // Genres
        String genres = "";
        if (mGame.getGenreList() != null) {
            for (Genre genre: mGame.getGenreList()) {
                genres += genre.getDesc() + ", ";
            }
            if (genres != "") {
                tvGenres.setText(genres.substring(0, genres.length()-2));
            }
        }
        else {
            tvGenres.setText("-");
        }

        // Features
        String features = "";
        if (mGame.getFeatureList() != null) {
            for (Feature feature: mGame.getFeatureList()) {
                features += feature.getDesc() + ", ";
            }
            if (features != "") {
                tvFeatures.setText(features.substring(0, features.length()-2));
            }
        }
        else {
            tvFeatures.setText("-");
        }

        // Platforms
        String platforms = "";
        if (mGame.getPlatformList() != null) {
            for (Platform platform: mGame.getPlatformList()) {
                platforms += platform.getDesc() + ", ";
            }
            if (platforms != "") {
                tvPlatforms.setText(platforms.substring(0, platforms.length()-2));
            }
        }
        else {
            tvPlatforms.setText("-");
        }

        // Languages
        String languages = "";
        if (mGame.getLanguageList() != null) {
            for (Language language: mGame.getLanguageList()) {
                languages += language.getDesc() + ", ";
            }
            if (languages != "") {
                tvLanguages.setText(languages.substring(0, languages.length()-2));
            }
        }
        else {
            tvLanguages.setText("-");
        }

        // Visit page listener
        btVisitPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = null;
                try {
                    uri = Uri.parse(mGame.getUrl());
                }
                catch(NullPointerException e) {
                    Toast.makeText(GameActivity.this, getString(R.string.invalid_url),Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(uri);
                startActivity(i);
            }
        });

    }
}
