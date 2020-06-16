package com.example.blitz_t;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import static com.example.blitz_t.Models.Model.checkMicrofinances;

public class OthersMicrofinancesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private View view;
    private Toolbar app_bar;
    private CollapsingToolbarLayout collapsing;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.fragment_microfinance , container, false);

        mRecyclerView = view.findViewById(R.id.recycler_view_microfinance);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        app_bar = view.findViewById(R.id.app_bar);
        collapsing = view.findViewById(R.id.collapsing);
        collapsing.setTitle(getString(R.string.text_others_micro_finances));

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
                    checkMicrofinances(mRecyclerView, view.getContext(), newText, null, getActivity());
                    return true;
                }
            });
        }

        checkMicrofinances(mRecyclerView, view.getContext(), "", null, getActivity());

        return view;
    }

}
