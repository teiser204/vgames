package gr.artibet.vgames.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class ApiSettings {

    // ---------------------------------------------------------------------------------------
    // Query parameter keys
    // ---------------------------------------------------------------------------------------
    public static final String QUERY_TITLE = "title";
    public static final String QUERY_DESCRIPTION = "desc";
    public static final String QUERY_YEAR_FROM = "from_year";
    public static final String QUERY_YEAR_TO = "to_year";
    public static final String QUERY_PRICE_FROM = "min_price";
    public static final String QUERY_PRICE_TO = "max_price";
    public static final String QUERY_GENRE = "genre";
    public static final String QUERY_COMPANY = "company";
    public static final String QUERY_FEATURE = "feature";
    public static final String QUERY_PLATFORM = "platform";
    public static final String QUERY_LANGUAGE = "language";
    public static final String QUERY_LIMIT = "limit";
    public static final String QUERY_RATING_FROM = "from_rating";
    public static final String QUERY_ORDERING = "ordering";

    // ---------------------------------------------------------------------------------------
    // Keys to shared preferences
    // ---------------------------------------------------------------------------------------
    private static final String API_SHARED_PREFS = "apiSharedPrefs";
    private static final String BASE_URL = "apiBaseUrl";
    private static final String GAMES = "apiGames";
    private static final String RECENT_GAMES_LIMIT = "apiRecentGamesLimit";
    private static final String TOP_GAMES_RATING = "apiTopGamesRating";
    private static final String TOP_GAMES_LIMIT = "apiTopGamesLimit";
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
    private static final int mRecentGamesLimitDefault = 10;
    private static final float mTopGamesRatingDefault = 4.5f;
    private static final int mTopGamesLimitDefault = 10;
    private static final String mGenreDefault = "genres";
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
    private int mRecentGamesLimit;
    private float mTopGamesRating;
    private int mTopGamesLimit;
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
        mRecentGamesLimit = sharedPreferences.getInt(RECENT_GAMES_LIMIT, mRecentGamesLimitDefault);
        mTopGamesRating = sharedPreferences.getFloat(TOP_GAMES_RATING, mTopGamesRatingDefault);
        mTopGamesLimit = sharedPreferences.getInt(TOP_GAMES_LIMIT, mTopGamesLimitDefault);
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
        editor.putInt(RECENT_GAMES_LIMIT, mRecentGamesLimit);
        editor.putFloat(TOP_GAMES_RATING, mTopGamesRating);
        editor.putInt(TOP_GAMES_LIMIT, mTopGamesLimit);
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

    public int getmRecentGamesLimit() {
        return mRecentGamesLimit;
    }

    public float getTopGamesRating() {
        return mTopGamesRating;
    }

    public int getmTopGamesLimit() {
        return mTopGamesLimit;
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

    public void setRecentGamesLimit(int limit) { this.mRecentGamesLimit = limit; }

    public void setTopGamesRating(float topGamesRating) {
        this.mTopGamesRating = topGamesRating;
    }

    public void setTopGamesLimit(int limit) { this.mTopGamesLimit = limit; }

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
    // Recent games URL
    // ---------------------------------------------------------------------------------------
    public String getRecentGamesUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl()).buildUpon();
        builder.appendPath(mGames);
        builder.appendQueryParameter(QUERY_ORDERING, "-created_at");
        builder.appendQueryParameter(QUERY_LIMIT, String.valueOf(mRecentGamesLimit));

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Top games URL
    // ---------------------------------------------------------------------------------------
    public String getTopGamesUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl()).buildUpon();
        builder.appendPath(mGames);
        builder.appendQueryParameter(QUERY_RATING_FROM, String.valueOf(mTopGamesRating));
        builder.appendQueryParameter(QUERY_LIMIT, String.valueOf(mTopGamesLimit));
        builder.appendQueryParameter(QUERY_ORDERING, "-rating");

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Genre list URL
    // ---------------------------------------------------------------------------------------
    public String getGenreUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl()).buildUpon();
        builder.appendPath(mGenre);

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Feature list URL
    // ---------------------------------------------------------------------------------------
    public String getFeaturesUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl()).buildUpon();
        builder.appendPath(mFeatures);

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Company list URL
    // ---------------------------------------------------------------------------------------
    public String getCompaniesUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl()).buildUpon();
        builder.appendPath(mCompanies);

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Platform list URL
    // ---------------------------------------------------------------------------------------
    public String getPlatformsUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl()).buildUpon();
        builder.appendPath(mPlatforms);

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Language list URL
    // ---------------------------------------------------------------------------------------
    public String getLanguagesUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl()).buildUpon();
        builder.appendPath(mLanguages);

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Language list URL
    // ---------------------------------------------------------------------------------------
    public String getGamesUrl() {
        Uri.Builder builder = Uri.parse(getBaseUrl()).buildUpon();
        builder.appendPath(mGames);

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Language list URL
    // ---------------------------------------------------------------------------------------
    public String getGameDetailsUrl(int gameId) {
        Uri.Builder builder = Uri.parse(getBaseUrl()).buildUpon();
        builder.appendPath(mGames);
        builder.appendPath(String.valueOf(gameId));

        return builder.build().toString();

    }

    // ---------------------------------------------------------------------------------------
    // Search title url
    // ---------------------------------------------------------------------------------------
    public String getSearchTitleUrl(String token) {
        Uri.Builder builder = Uri.parse(getBaseUrl()).buildUpon();
        builder.appendPath(mGames);
        builder.appendQueryParameter(QUERY_TITLE, token);

        return builder.build().toString();

    }

}
