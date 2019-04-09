package gr.artibet.vgames;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import gr.artibet.vgames.api.ApiSettings;
import gr.artibet.vgames.models.Company;
import gr.artibet.vgames.models.Feature;
import gr.artibet.vgames.models.Genre;
import gr.artibet.vgames.models.Language;
import gr.artibet.vgames.models.Platform;


public class SearchFragment extends Fragment implements Spinner.OnTouchListener {

    // ---------------------------------------------------------------------------------------
    // Query parameters constants
    // ---------------------------------------------------------------------------------------
    private static final String PATH_GAMES = "games.json";
    private static final String QUERY_TITLE = "title";
    private static final String QUERY_DESCRIPTION = "desc";
    private static final String QUERY_YEAR_FROM = "year_from";
    private static final String QUERY_YEAR_TO = "year_to";
    private static final String QUERY_PRICE_FROM = "price_from";
    private static final String QUERY_PRICE_TO = "price_to";
    private static final String QUERY_GENRE = "genre";
    private static final String QUERY_COMPANY = "company";
    private static final String QUERY_FEATURE = "feature";
    private static final String QUERY_PLATFORM = "platform";
    private static final String QUERY_LANGUAGE = "language";

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    List<Genre> mGenreList;
    List<Company> mCompanyList;
    List<Feature> mFeatureList;
    List<Platform> mPlatformList;
    List<Language> mLanguageList;

    // Widgets
    EditText mTitleEditText;
    EditText mDescriptionEditText;
    EditText mReleaseYearFromEditText;
    EditText mReleaseYearToEditText;
    EditText mPriceFrom;
    EditText mPriceTo;
    Spinner mGenreSpinner;
    Spinner mCompanySpinner;
    Spinner mFeatureSpinner;
    Spinner mPlatformSpinner;
    Spinner mLanguageSpinner;

    public SearchFragment() {
        mGenreList = new ArrayList<>();
        mCompanyList = new ArrayList<>();
        mFeatureList = new ArrayList<>();
        mPlatformList = new ArrayList<>();
        mLanguageList = new ArrayList<>();
    }

    // ---------------------------------------------------------------------------------------
    // Setters
    // ---------------------------------------------------------------------------------------
    public void setGenreList(List<Genre> genreList) {
        mGenreList = genreList;
    }

    public void setCompanyList(List<Company> companyList) {
        mCompanyList = companyList;
    }

    public void setFeatureList(List<Feature> featureList) {
        mFeatureList = featureList;
    }

    public void setPlatformList(List<Platform> platformList) {
        mPlatformList = platformList;
    }

    public void setLanguageList(List<Language> languageList) {
        mLanguageList = languageList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Get widgets
        mTitleEditText = view.findViewById(R.id.search_title);
        mDescriptionEditText = view.findViewById(R.id.search_description);
        mReleaseYearFromEditText = view.findViewById(R.id.search_year_from);
        mReleaseYearToEditText = view.findViewById(R.id.search_year_to);
        mPriceFrom = view.findViewById(R.id.search_price_from);
        mPriceTo = view.findViewById(R.id.search_price_to);
        mGenreSpinner = view.findViewById(R.id.search_genre);
        mCompanySpinner = view.findViewById(R.id.search_company);
        mFeatureSpinner = view.findViewById(R.id.search_feature);
        mPlatformSpinner = view.findViewById(R.id.search_platform);
        mLanguageSpinner = view.findViewById(R.id.search_language);

        // Create Adapters
        ArrayAdapter<Genre> genreAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, mGenreList);
        ArrayAdapter<Company> companyAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, mCompanyList);
        ArrayAdapter<Feature> featureAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, mFeatureList);
        ArrayAdapter<Platform> platformAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, mPlatformList);
        ArrayAdapter<Language> languageAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, mLanguageList);

        // Set dropdown resources
        genreAdapter.setDropDownViewResource(R.layout.spinner_item);
        companyAdapter.setDropDownViewResource(R.layout.spinner_item);
        featureAdapter.setDropDownViewResource(R.layout.spinner_item);
        platformAdapter.setDropDownViewResource(R.layout.spinner_item);
        languageAdapter.setDropDownViewResource(R.layout.spinner_item);

        // Set adapters to spinners
        mGenreSpinner.setAdapter(genreAdapter);
        mCompanySpinner.setAdapter(companyAdapter);
        mFeatureSpinner.setAdapter(featureAdapter);
        mPlatformSpinner.setAdapter(platformAdapter);
        mLanguageSpinner.setAdapter(languageAdapter);

        // Hide soft keyboard on spinners touch
        mGenreSpinner.setOnTouchListener(this);
        mCompanySpinner.setOnTouchListener(this);
        mFeatureSpinner.setOnTouchListener(this);
        mPlatformSpinner.setOnTouchListener(this);
        mLanguageSpinner.setOnTouchListener(this);

        return view;

    }

    // ---------------------------------------------------------------------------------------
    // Hide soft keyboard on spinners touch
    // ---------------------------------------------------------------------------------------
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getActivity().getApplicationContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mTitleEditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mDescriptionEditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mReleaseYearFromEditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mReleaseYearToEditText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mPriceFrom.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mPriceTo.getWindowToken(), 0);
        return false;
    }


    // ---------------------------------------------------------------------------------------
    // Build search URL
    // ---------------------------------------------------------------------------------------
    public String getUrl() {

        // Count criteria - There should be at least one
        int nCriteria = 0;


        // Build Uri object
        ApiSettings apiSettings = new ApiSettings(getActivity());
        Uri.Builder builder = Uri.parse(apiSettings.getGamesUrl()).buildUpon();

        // Title
        String title = mTitleEditText.getText().toString();
        if (!title.isEmpty()) {
            nCriteria++;
            builder.appendQueryParameter(QUERY_TITLE, title);
        }

        // Description
        String description = mDescriptionEditText.getText().toString();
        if (!description.isEmpty()) {
            nCriteria++;
            builder.appendQueryParameter(QUERY_DESCRIPTION, description);
        }

        // Year from
        String yearFrom = mReleaseYearFromEditText.getText().toString();
        if (!yearFrom.isEmpty()) {
            nCriteria++;
            builder.appendQueryParameter(QUERY_YEAR_FROM, yearFrom);
        }

        // Year to
        String yearTo = mReleaseYearToEditText.getText().toString();
        if (!yearTo.isEmpty()) {
            nCriteria++;
            builder.appendQueryParameter(QUERY_YEAR_TO, yearTo);
        }

        // Price from
        String priceFrom = mPriceFrom.getText().toString();
        if (!priceFrom.isEmpty()) {
            nCriteria++;
            builder.appendQueryParameter(QUERY_PRICE_FROM, priceFrom);
        }

        // Price to
        String priceTo = mPriceTo.getText().toString();
        if (!priceTo.isEmpty()) {
            nCriteria++;
            builder.appendQueryParameter(QUERY_PRICE_TO, priceTo);
        }

        // Genre
        Genre genre = (Genre) mGenreSpinner.getSelectedItem();
        if (genre.getId() != -1) {
            nCriteria++;
            builder.appendQueryParameter(QUERY_GENRE, String.valueOf(genre.getId()));
        }

        // Company
        Company company = (Company) mCompanySpinner.getSelectedItem();
        if (company.getId() != -1) {
            nCriteria++;
            builder.appendQueryParameter(QUERY_COMPANY, String.valueOf(company.getId()));
        }

        // Feature
        Feature feature = (Feature) mFeatureSpinner.getSelectedItem();
        if (feature.getId() != -1) {
            nCriteria++;
            builder.appendQueryParameter(QUERY_FEATURE, String.valueOf(feature.getId()));
        }

        // Platform
        Platform platform = (Platform) mPlatformSpinner.getSelectedItem();
        if (platform.getId() != -1) {
            nCriteria++;
            builder.appendQueryParameter(QUERY_PLATFORM, String.valueOf(platform.getId()));
        }

        // Language
        Language language = (Language) mLanguageSpinner.getSelectedItem();
        if (language.getId() != -1) {
            nCriteria++;
            builder.appendQueryParameter(QUERY_LANGUAGE, String.valueOf(language.getId()));
        }


        // Return URL string
        if (nCriteria == 0) {
            return "";
        }
        String str = builder.build().toString();
        return builder.build().toString();

    }
}
