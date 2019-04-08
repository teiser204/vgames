package gr.artibet.vgames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ApiSettingsActivity extends AppCompatActivity {


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

        }

        return super.onOptionsItemSelected(item);
    }
}
