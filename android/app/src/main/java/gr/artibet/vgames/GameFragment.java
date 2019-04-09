package gr.artibet.vgames;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import gr.artibet.vgames.models.Feature;
import gr.artibet.vgames.models.Gallery;
import gr.artibet.vgames.models.Game;
import gr.artibet.vgames.models.Genre;
import gr.artibet.vgames.models.Language;
import gr.artibet.vgames.models.Platform;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    Game mGame = null;

    // ---------------------------------------------------------------------------------------
    // Default constructor
    // ---------------------------------------------------------------------------------------
    public GameFragment() {
        // Required empty public constructor
    }

    // ---------------------------------------------------------------------------------------
    // Set fragment game
    // ---------------------------------------------------------------------------------------
    public void setGame(Game game) {
        mGame = game;
    }

    // ---------------------------------------------------------------------------------------
    // On create view
    // ---------------------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_game, container, false);

        // Update UI
        if (mGame != null) updateUI(view);

        return view;
    }


    // ---------------------------------------------------------------------------------------
    // Update UI for fetched game
    // ---------------------------------------------------------------------------------------
    private void updateUI(View view) {

        // Get views
        ImageView ivImage = view.findViewById(R.id.ivImage);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvCompany = view.findViewById(R.id.tvCompany);
        TextView tvYear = view.findViewById(R.id.tvYear);
        TextView tvRating = view.findViewById(R.id.tvRating);
        RatingBar rbRating = view.findViewById(R.id.rbRating);
        TextView tvDesc = view.findViewById(R.id.tvDesc);
        TextView tvPrice = view.findViewById(R.id.tvPrice);
        TextView tvGenres = view.findViewById(R.id.tvGenres);
        TextView tvFeatures = view.findViewById(R.id.tvFeatures);
        TextView tvPlatforms = view.findViewById(R.id.tvPlatforms);
        TextView tvLanguages = view.findViewById(R.id.tvLanguages);
        Button btVisitPage = view.findViewById(R.id.btVisitPage);

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

        // Game Image Gallery
        if (mGame.getGalleryList() != null) {
            ArrayList<String> imageList = new ArrayList<>();
            for (Gallery gallery : mGame.getGalleryList()) {
                imageList.add(gallery.getImage());
            }
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            GalleryFragment galleryFragment = new GalleryFragment();
            galleryFragment.setImageList(imageList);
            ft.add(R.id.galleryContainer, galleryFragment, null);
            ft.commit();
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
                    Toast.makeText(getActivity(), getString(R.string.invalid_url),Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(uri);
                startActivity(i);
            }
        });

    }

}
