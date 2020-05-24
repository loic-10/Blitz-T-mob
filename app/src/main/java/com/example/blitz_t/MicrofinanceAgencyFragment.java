package com.example.blitz_t;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.blitz_t.Api.AgencyHelper;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Views.DesignApp;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MicrofinanceAgencyFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private Microfinance mMicrofinance;
    private Toolbar app_bar;
    private CollapsingToolbarLayout collapsing;
    private ImageView image_item_micro_finance;
    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {

        final View v = inflater.inflate(R.layout.activity_microfinance_agency , container , false);

        mMicrofinance = (Microfinance) Model.contentPreference(
                new Microfinance(),
                getString(R.string.SHARED_PREF_MICROFINANCE_SELECT),
                getString(R.string.PREFERENCE_FILE_KEY),
                Objects.requireNonNull(this.getActivity()));

        mActivity = this.getActivity();

        image_item_micro_finance = v.findViewById(R.id.image_item_micro_finance);

        mRecyclerView = v.findViewById(R.id.recycler_view_agencies);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        DesignApp.updateImage(v.getContext(), image_item_micro_finance, mMicrofinance.getImage(), null);

        app_bar = v.findViewById(R.id.app_bar);
        collapsing = v.findViewById(R.id.collapsing);
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
                    AgencyHelper.checkAgenciesMicrofinance(mRecyclerView, v.getContext(), newText, mMicrofinance, mActivity);
                    return true;
                }
            });
        }

        AgencyHelper.checkAgenciesMicrofinance(mRecyclerView, v.getContext(), "", mMicrofinance, mActivity);

        return v;

    }
}
