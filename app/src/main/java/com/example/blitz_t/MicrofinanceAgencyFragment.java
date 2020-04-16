package com.example.blitz_t;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blitz_t.Controllers.MicrofinanceAdapter;
import com.example.blitz_t.Models.Microfinance.Microfinance;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MicrofinanceAgencyFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {

        View v = inflater.inflate(R.layout.activity_microfinance_agency , container , false);

        mRecyclerView = v.findViewById(R.id.recycler_view_agencies);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MicrofinanceAdapter adapter = new MicrofinanceAdapter(new ArrayList<Microfinance>(), getContext(), getActivity());
        mRecyclerView.setAdapter(adapter);

        return v;

    }
}
