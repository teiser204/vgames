package gr.artibet.vgames;


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

import gr.artibet.vgames.models.Company;
import gr.artibet.vgames.models.Feature;
import gr.artibet.vgames.models.Genre;
import gr.artibet.vgames.models.Language;
import gr.artibet.vgames.models.Platform;


public class SearchFragment extends Fragment implements Spinner.OnTouchListener {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    List<Genre> mGenreList;
    List<Company> mCompanyList;
    List<Feature> mFeatureList;
    List<Platform> mPlatformList;
    List<Language> mLanguageList;

    // Widgets
    EditText mEditTextTitle;
    EditText mEditTextDesc;

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
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        // Get widgets
        mEditTextTitle = view.findViewById(R.id.search_title);
        mEditTextDesc = view.findViewById(R.id.search_description);

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

        // Get spinner views
        Spinner genreSpinner = view.findViewById(R.id.search_genre);
        Spinner companySpinner = view.findViewById(R.id.search_company);
        Spinner featureSpinner = view.findViewById(R.id.search_feature);
        Spinner platformSpinner = view.findViewById(R.id.search_platform);
        Spinner languageSpinner = view.findViewById(R.id.search_language);

        // Set adapters to spinners
        genreSpinner.setAdapter(genreAdapter);
        companySpinner.setAdapter(companyAdapter);
        featureSpinner.setAdapter(featureAdapter);
        platformSpinner.setAdapter(platformAdapter);
        languageSpinner.setAdapter(languageAdapter);

        // Hide soft keyboard on spinners touch
        genreSpinner.setOnTouchListener(this);
        companySpinner.setOnTouchListener(this);
        featureSpinner.setOnTouchListener(this);
        platformSpinner.setOnTouchListener(this);
        languageSpinner.setOnTouchListener(this);

        return view;

    }

    // ---------------------------------------------------------------------------------------
    // Hide soft keyboard on spinners touch
    // ---------------------------------------------------------------------------------------
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        InputMethodManager imm=(InputMethodManager)getActivity().getApplicationContext().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditTextTitle.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(mEditTextDesc.getWindowToken(), 0);
        return false;
    }

}
