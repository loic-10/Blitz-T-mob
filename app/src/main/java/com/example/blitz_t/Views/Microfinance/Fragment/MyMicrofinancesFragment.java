package com.example.blitz_t.Views.Microfinance.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.example.blitz_t.Models.Model.checkMicrofinances;

public class MyMicrofinancesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private View view;
    private Toolbar app_bar;
    private CollapsingToolbarLayout collapsing;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.fragment_microfinance , container, false);

        Member member = (Member) Model.contentPreference(
                new Member(),
                getString(R.string.SHARED_PREF_MEMBER_LOGIN),
                getString(R.string.PREFERENCE_FILE_KEY),
                getActivity());

        member = member == null ? new Member() : member;

        mRecyclerView = view.findViewById(R.id.recycler_view_microfinance);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        app_bar = view.findViewById(R.id.app_bar);
        collapsing = view.findViewById(R.id.collapsing);
        collapsing.setTitle(getString(R.string.text_my_micro_finances));

        MenuItem searchItem = app_bar.getMenu().findItem(R.id.menu_search);
        if(searchItem != null){
            SearchView searchView = (SearchView) searchItem.getActionView();
            final Member finalMember = member;
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit ( String query ) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange ( String newText ) {
                    checkMicrofinances(mRecyclerView, view.getContext(), newText, finalMember , getActivity());
                    return true;
                }
            });
        }

        checkMicrofinances(mRecyclerView, view.getContext(), "", member, getActivity());

        return view;
    }
    
}
