package com.example.blitz_t;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blitz_t.Controllers.AccountAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class HomeCustomerFragment extends Fragment {

    ViewPager mViewPager;
    AccountAdapter mAdapter;
    ArrayList mAccounts;
    WaveSwipeRefreshLayout swipe_refresh_layout_transaction;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_home , container , false);

        mAccounts = new ArrayList<>();
        swipe_refresh_layout_transaction = view.findViewById(R.id.swipe_refresh_layout_transaction);
        mAdapter = new AccountAdapter(mAccounts, getContext());
        mViewPager = view.findViewById(R.id.viewPager_account);

        /* if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mViewPager.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            mViewPager.setLayoutManager(new GridLayoutManager(this, 4));
        } */

        mViewPager.setAdapter(mAdapter);

        mViewPager.setPadding(50, 0, 50, 0);

        swipe_refresh_layout_transaction.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh () {
                swipe_refresh_layout_transaction.setRefreshing(false);
            }
        });

        return view;
    }
}
