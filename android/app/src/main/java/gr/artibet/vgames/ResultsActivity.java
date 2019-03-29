package gr.artibet.vgames;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultsActivity extends AppCompatActivity {

    // class members
    private static FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get intent data
        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");

        // Set title
        TextView tvTitle = findViewById(R.id.results_title);
        tvTitle.setText(title);


        // Initialize fragment manager
        fm = getSupportFragmentManager();


        if (findViewById(R.id.fragment_container) != null) {

            // if saveInstanceState isn't null activity resumed
            if (savedInstanceState != null) {
                return;
            }

            FragmentTransaction ft = fm.beginTransaction();
            NoResultsFragment noResultsFragment = new NoResultsFragment();
            ft.add(R.id.fragment_container, noResultsFragment, null);
            ft.commit();

        }

        // Initialize view

        // Get search criteria from intent

        // If no results set fragment no results

        // else set fragment game list

        // Retrieve game list


    }

}
