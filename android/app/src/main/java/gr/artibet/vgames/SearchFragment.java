package gr.artibet.vgames;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import gr.artibet.vgames.models.Company;
import gr.artibet.vgames.models.Feature;
import gr.artibet.vgames.models.Genre;
import gr.artibet.vgames.models.Language;
import gr.artibet.vgames.models.Platform;


public class SearchFragment extends Fragment {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    List<Genre> mGenreList;
    List<Company> mCompanyList;
    List<Feature> mFeatureList;
    List<Platform> mPlatformList;
    List<Language> mLanguageList;

    public SearchFragment() {
        // Required empty public constructor
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

    public void setLanuageList(List<Language> languageList) {
        mLanguageList = languageList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        // Create Adapters
        ArrayAdapter<Genre> genreAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mGenreList);
        ArrayAdapter<Company> companyAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mCompanyList);
        ArrayAdapter<Feature> featureAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mFeatureList);
        ArrayAdapter<Platform> platformAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mPlatformList);
        ArrayAdapter<Language> languageAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mLanguageList);

        // Set dropdown resources
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        companyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        featureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        platformAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Get spinner views
        Spinner genreSpinner = view.findViewById(R.id.search_genre);
        Spinner companysSpinner = view.findViewById(R.id.search_company);
        Spinner featureSpinner = view.findViewById(R.id.search_feature);
        Spinner platformSpinner = view.findViewById(R.id.search_platform);
        Spinner languageSpinner = view.findViewById(R.id.search_language);

        // Set adapters to spinners
        genreSpinner.setAdapter(genreAdapter);
        companysSpinner.setAdapter(companyAdapter);
        featureSpinner.setAdapter(featureAdapter);
        platformSpinner.setAdapter(platformAdapter);
        languageSpinner.setAdapter(languageAdapter);


        return view;

    }

}
