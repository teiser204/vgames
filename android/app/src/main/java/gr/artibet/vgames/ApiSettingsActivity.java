package gr.artibet.vgames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import gr.artibet.vgames.api.ApiSettings;
import gr.artibet.vgames.globals.Util;

public class ApiSettingsActivity extends AppCompatActivity {

    // ---------------------------------------------------------------------------------------
    // Class members - widgets
    // ---------------------------------------------------------------------------------------
    EditText mBaseUrlEditText;
    EditText mGamesEditText;
    EditText mRecentGamesLimitEditText;
    EditText mTopGamesRatingEditText;
    EditText mTopGamesLimitEditText;
    EditText mGenresEditText;
    EditText mCompaniesEditText;
    EditText mFeaturesEditText;
    EditText mPlatformsEditText;
    EditText mLanguagesEditText;


    // ---------------------------------------------------------------------------------------
    // onCreate
    // ---------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_settings);

        // Set title and back arrow
        getSupportActionBar().setTitle(getResources().getString(R.string.api_settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get widget references
        mBaseUrlEditText = findViewById(R.id.api_settings_baseurl);
        mGamesEditText = findViewById(R.id.api_settings_games);
        mRecentGamesLimitEditText = findViewById(R.id.api_settings_recent_games_limit);
        mTopGamesRatingEditText = findViewById(R.id.api_settings_top_games_rating);
        mTopGamesLimitEditText = findViewById(R.id.api_settings_top_games_limit);
        mGenresEditText = findViewById(R.id.api_settings_genre);
        mCompaniesEditText = findViewById(R.id.api_settings_companies);
        mFeaturesEditText = findViewById(R.id.api_settings_features);
        mPlatformsEditText = findViewById(R.id.api_settings_platforms);
        mLanguagesEditText = findViewById(R.id.api_settings_languages);

        // Load settings
        ApiSettings apiSettings = new ApiSettings(this);
        mBaseUrlEditText.setText(apiSettings.getBaseUrl());
        mGamesEditText.setText(apiSettings.getGames());
        mRecentGamesLimitEditText.setText(String.valueOf(apiSettings.getmRecentGamesLimit()));
        mTopGamesRatingEditText.setText(String.valueOf(apiSettings.getTopGamesRating()));
        mTopGamesLimitEditText.setText(String.valueOf(apiSettings.getmTopGamesLimit()));
        mGenresEditText.setText(apiSettings.getGenre());
        mCompaniesEditText.setText(apiSettings.getCompanies());
        mFeaturesEditText.setText(apiSettings.getFeatures());
        mPlatformsEditText.setText(apiSettings.getPlatforms());
        mLanguagesEditText.setText(apiSettings.getLanguages());

    }


    // ---------------------------------------------------------------------------------------
    // Options menu creation
    // ---------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.api_settings_menu, menu);

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

            // Save settings
            case R.id.action_api_settings_save:
                saveSettings();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    // ---------------------------------------------------------------------------------------
    // Save API settings int shared preferences
    // ---------------------------------------------------------------------------------------
    private void saveSettings() {
        ApiSettings apiSettings = new ApiSettings(this);

        apiSettings.setBaseUrl(mBaseUrlEditText.getText().toString());
        apiSettings.setGames(mGamesEditText.getText().toString());
        apiSettings.setRecentGamesLimit(Integer.parseInt(mRecentGamesLimitEditText.getText().toString()));
        apiSettings.setTopGamesRating(Float.parseFloat(mTopGamesRatingEditText.getText().toString()));
        apiSettings.setTopGamesLimit(Integer.parseInt(mTopGamesLimitEditText.getText().toString()));
        apiSettings.setGenre(mGenresEditText.getText().toString());
        apiSettings.setCompanies(mCompaniesEditText.getText().toString());
        apiSettings.setFeatures(mFeaturesEditText.getText().toString());
        apiSettings.setPlatforms(mPlatformsEditText.getText().toString());
        apiSettings.setLanguages(mLanguagesEditText.getText().toString());

        apiSettings.save();

        Util.successToast(this, getResources().getString(R.string.api_settings_saved));

    }
}
