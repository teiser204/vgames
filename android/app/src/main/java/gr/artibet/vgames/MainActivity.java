package gr.artibet.vgames;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import gr.artibet.vgames.api.GameAPI;
import gr.artibet.vgames.models.Game;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        SearchView.OnQueryTextListener {

    // ---------------------------------------------------------------------------------------
    // Class members
    // ---------------------------------------------------------------------------------------
    private DrawerLayout mDrawer;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    // tab fragment containers
    private FragmentTop mFragmentTop = null;
    private GenreFragmentContainer mGenreFragmentContainer = null;
    private FeaturesFragmentContainer mFeaturesFragmentContainer = null;
    private CompaniesFragmentContainer mCompaniesFragmentContainer = null;
    private PlatformsFragmentContainer mPlatformsFragmentContainer = null;
    private LanguagesFragmentContainer mLanguagesFragmentContainer = null;



    // ---------------------------------------------------------------------------------------
    // onCreate override
    // ---------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);

        // Prepare navigation drawer
        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.drawer_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        // Create fragment containers
        mFragmentTop = new FragmentTop();
        mGenreFragmentContainer = new GenreFragmentContainer();
        mFeaturesFragmentContainer = new FeaturesFragmentContainer();
        mCompaniesFragmentContainer = new CompaniesFragmentContainer();
        mPlatformsFragmentContainer = new PlatformsFragmentContainer();
        mLanguagesFragmentContainer = new LanguagesFragmentContainer();


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    // ---------------------------------------------------------------------------------------
    // Options menu creation
    // ---------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Prepare searchView click listener
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Αναζήτηση τίτλου");
        searchView.setOnQueryTextListener(this);

        return true;
    }


    // ---------------------------------------------------------------------------------------
    // Drawer listeners
    // ---------------------------------------------------------------------------------------
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.nav_asearch:
                navSearch();
                break;

            case R.id.nav_exit:
                navExit();
                break;
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // ---------------------------------------------------------------------------------------
    // Search View listeners
    // ---------------------------------------------------------------------------------------
    @Override
    public boolean onQueryTextSubmit(String s) {

        // Start results activity
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("TITLE", getResources().getString(R.string.game_title) + ": '" + s + "'");
        intent.putExtra("QUERY", "games.json?title=" + s);
        startActivity(intent);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        // Do nothing
        return false;
    }


    // ---------------------------------------------------------------------------------------
    // Fragment management
    // ---------------------------------------------------------------------------------------
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);

        }


        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0: // Home fragment
                    return mFragmentTop;

                case 1: // Genre
                    return mGenreFragmentContainer;

                case 2: // Features
                    return mFeaturesFragmentContainer;

                case 3: // Companies
                    return mCompaniesFragmentContainer;

                case 4: // Platforms
                    return mPlatformsFragmentContainer;

                case 5: // Languages
                    return mLanguagesFragmentContainer;
            }

            return fragment;

        }

        @Override
        public int getCount() {
            return 6;
        }


    }


    // ---------------------------------------------------------------------------------------
    // Toast error message
    // ---------------------------------------------------------------------------------------
    public void showErrorToast(String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_error, (ViewGroup) findViewById(R.id.toast_root));

        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(message);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


    // ---------------------------------------------------------------------------------------
    // Back pressed override
    // ---------------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            confirmExit();
        }

    }

    // ---------------------------------------------------------------------------------------
    // gr.artibet.vgames.App exit confirmation
    // ---------------------------------------------------------------------------------------
    private void confirmExit() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_label);
        builder.setMessage(R.string.exit_application);

        // Exit button
        builder.setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        // Cancel button
        builder.setNegativeButton(R.string.cancel_exit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        // Show confirmation dialog
        builder.show();

    }


    // ---------------------------------------------------------------------------------------
    // Drawer menu items click implementations
    // ---------------------------------------------------------------------------------------

    // Exit app
    private void navExit() {
        confirmExit();
    }

    // Advanced search
    private void navSearch() {
        Toast.makeText(this, "Advanced Search", Toast.LENGTH_SHORT).show();
    }
}
