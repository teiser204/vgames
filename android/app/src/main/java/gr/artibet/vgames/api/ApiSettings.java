package gr.artibet.vgames.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class ApiSettings {

    // ---------------------------------------------------------------------------------------
    // Keys to shared preferences
    // ---------------------------------------------------------------------------------------
    private static final String API_SHARED_PREFS = "apiSharedPrefs";
    private static final String BASE_URL = "apiBaseUrl";
    private static final String GAMES = "apiGames";
    private static final String GAME_DETAILS = "apiGameDetails";
    private static final String TOP_GAMES = "apiTopGames";
    private static final String TOP_GAMES_RATING = "apiTopGamesRating";
    private static final String GENRE = "apiGenre";
    private static final String COMPANIES = "apiCompanies";
    private static final String FEATURES = "apiFeatures";
    private static final String PLATFORMS = "apiPlatforms";
    private static final String LANGUAGES = "apiLanguages";

    // ---------------------------------------------------------------------------------------
    // Default values
    // ---------------------------------------------------------------------------------------
    private static final String mBaseUrlDefault = "https://www.serres.gr/vgames/";
    private static final String mGamesDefault = "games";
    private static final String mGameDetailsDefault = "games/{id}";
    private static final String mTopGamesDefault = "top_games";
    private static final float mTopGamesRatingDefault = 4.5f;
    private static final String mGenreDefault = "genre";
    private static final String mCompaniesDefault = "companies";
    private static final String mFeaturesDefault = "features";
    private static final String mPlatformsDefault = "platforms";
    private static final String mLanguagesDefault = "languages";

    // ---------------------------------------------------------------------------------------
    // Class members keys to shared preferences
    // ---------------------------------------------------------------------------------------
    private Context mContext;
    private String mBaseUrl;
    private String mGames;
    private String mGameDetails;
    private String mTopGames;
    private float mTopGamesRating;
    private String mGenre;
    private String mCompanies;
    private String mFeatures;
    private String mPlatforms;
    private String mLanguages;

    // ---------------------------------------------------------------------------------------
    // Default constructor - Load preferences
    // ---------------------------------------------------------------------------------------
    public ApiSettings(Context context) {

        mContext = context;

        // Load shared preferences - set defaults if none exist
        SharedPreferences sharedPreferences = context.getSharedPreferences(API_SHARED_PREFS, Context.MODE_PRIVATE);
        mBaseUrl = sharedPreferences.getString(BASE_URL, mBaseUrlDefault);
        mGames = sharedPreferences.getString(GAMES, mGamesDefault);
        mGameDetails = sharedPreferences.getString(GAME_DETAILS, mGameDetailsDefault);
        mTopGames = sharedPreferences.getString(TOP_GAMES, mTopGamesDefault);
        mTopGamesRating = sharedPreferences.getFloat(TOP_GAMES_RATING, mTopGamesRatingDefault);
        mGenre = sharedPreferences.getString(GENRE, mGenreDefault);
        mCompanies = sharedPreferences.getString(COMPANIES, mCompaniesDefault);
        mFeatures = sharedPreferences.getString(FEATURES, mFeaturesDefault);
        mPlatforms = sharedPreferences.getString(PLATFORMS, mPlatformsDefault);
        mLanguages = sharedPreferences.getString(LANGUAGES, mLanguagesDefault);

    }

    // ---------------------------------------------------------------------------------------
    // Save current settings
    // ---------------------------------------------------------------------------------------
    public void save() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(API_SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(BASE_URL, mBaseUrl);
        editor.putString(GAMES, mGames);
        editor.putString(GAME_DETAILS, mGameDetails);
        editor.putString(TOP_GAMES, mTopGames);
        editor.putFloat(TOP_GAMES_RATING, mTopGamesRating);
        editor.putString(GENRE, mGenre);
        editor.putString(COMPANIES, mCompanies);
        editor.putString(FEATURES, mFeatures);
        editor.putString(PLATFORMS, mPlatforms);
        editor.putString(LANGUAGES, mLanguages);

        editor.apply();

    }

    // ---------------------------------------------------------------------------------------
    // Getters
    // ---------------------------------------------------------------------------------------

    public String getBaseUrl() {

        // Add trailing '/' if not exist
        if (mBaseUrl.endsWith("/")) return mBaseUrl;
        else return new String(mBaseUrl + "/");

    }

    public String getGames() {
        return mGames;
    }

    public String getGameDetails() {
        return mGameDetails;
    }

    public String getTopGames() {
        return mTopGames;
    }

    public float getTopGamesRating() {
        return mTopGamesRating;
    }

    public String getGenre() {
        return mGenre;
    }

    public String getCompanies() {
        return mCompanies;
    }

    public String getFeatures() {
        return mFeatures;
    }

    public String getPlatforms() {
        return mPlatforms;
    }

    public String getLanguages() {
        return mLanguages;
    }


    // ---------------------------------------------------------------------------------------
    // Setters
    // ---------------------------------------------------------------------------------------

    public void setBaseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    public void setGames(String games) {
        this.mGames = games;
    }

    public void setGameDetails(String gameDetails) {
        this.mGameDetails = gameDetails;
    }

    public void setTopGames(String topGames) {
        this.mTopGames = topGames;
    }

    public void setTopGamesRating(float topGamesRating) {
        this.mTopGamesRating = topGamesRating;
    }

    public void setGenre(String genre) {
        this.mGenre = genre;
    }

    public void setCompanies(String companies) {
        this.mCompanies = companies;
    }

    public void setFeatures(String features) {
        this.mFeatures = features;
    }

    public void setPlatforms(String platforms) {
        this.mPlatforms = platforms;
    }

    public void setLanguages(String languages) {
        this.mLanguages = languages;
    }


    // ---------------------------------------------------------------------------------------
    // Top games URL
    // ---------------------------------------------------------------------------------------
    public String getTopGamesUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl() + mTopGames).buildUpon();
        builder.appendQueryParameter("rating", String.valueOf(mTopGamesRating));

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Genre list URL
    // ---------------------------------------------------------------------------------------
    public String getGenreUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl() + mGenre).buildUpon();

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Feature list URL
    // ---------------------------------------------------------------------------------------
    public String getFeaturesUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl() + mFeatures).buildUpon();

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Company list URL
    // ---------------------------------------------------------------------------------------
    public String getCompaniesUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl() + mCompanies).buildUpon();

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Platform list URL
    // ---------------------------------------------------------------------------------------
    public String getPlatformsUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl() + mPlatforms).buildUpon();

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Language list URL
    // ---------------------------------------------------------------------------------------
    public String getLanguagesUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl() + mLanguages).buildUpon();

        return builder.build().toString();

    }

}
