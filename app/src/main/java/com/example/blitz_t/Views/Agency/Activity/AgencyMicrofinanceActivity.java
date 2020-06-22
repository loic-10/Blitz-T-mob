package com.example.blitz_t.Views.Agency.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import com.example.blitz_t.Api.AgencyHelper;
import com.example.blitz_t.Models.Agency.Agency;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.DesignApp;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class AgencyMicrofinanceActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private Microfinance mMicrofinance;
    private Toolbar app_bar;
    private CollapsingToolbarLayout collapsing;
    private ImageView image_item_micro_finance;
    private Activity mActivity;

    static AgencyHelper sAgencyHelper = new AgencyHelper(new Agency());

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microfinance_agency);

        mMicrofinance = (Microfinance) Model.contentPreference(
                new Microfinance(),
                getString(R.string.SHARED_PREF_MICROFINANCE_SELECT),
                getString(R.string.PREFERENCE_FILE_KEY),
                this);

        mActivity = this;

        image_item_micro_finance = findViewById(R.id.image_item_micro_finance);

        mRecyclerView = findViewById(R.id.recycler_view_agencies);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DesignApp.updateImage(getApplicationContext(), image_item_micro_finance, mMicrofinance.getImage(), null);

        app_bar = findViewById(R.id.app_bar);
        collapsing = findViewById(R.id.collapsing);
        collapsing.setTitle(mMicrofinance.getNom());

        MenuItem searchItem = app_bar.getMenu().findItem(R.id.menu_search);
        if(searchItem != null){
            SearchView searchView = (SearchView) searchItem.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit ( String query ) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange ( String newText ) {
                    Model.checkAgenciesMicrofinance(mRecyclerView, getApplicationContext(), newText, mMicrofinance, mActivity);
                    return true;
                }
            });
        }

        Model.checkAgenciesMicrofinance(mRecyclerView, getApplicationContext(), "", mMicrofinance, mActivity);
    }
}
