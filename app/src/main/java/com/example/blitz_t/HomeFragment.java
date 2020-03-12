package com.example.blitz_t;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blitz_t.Controllers.AccountAdapter;
import com.example.blitz_t.Models.Account;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

public class HomeFragment extends Fragment {

    ViewPager mViewPager;
    AccountAdapter mAdapter;
    ArrayList mAccounts;
    SwipeRefreshLayout swipe_refresh_layout_transaction;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_home , container , false);

        mAccounts = new ArrayList<>();
        mAccounts.add(new Account(R.drawable.background, "Compte epargne", "30 500 XAF"));
        mAccounts.add(new Account(R.drawable.background, "Compte courant", "30 500 XAF"));
        mAccounts.add(new Account(R.drawable.background, "Compte courant", "30 500 XAF"));
        mAccounts.add(new Account(R.drawable.background, "Compte epargne", "30 500 XAF"));
        mAccounts.add(new Account(R.drawable.background, "Compte epargne", "30 500 XAF"));
        swipe_refresh_layout_transaction = view.findViewById(R.id.swipe_refresh_layout_transaction);
        mAdapter = new AccountAdapter(mAccounts, getContext());
        mViewPager = view.findViewById(R.id.viewPager_account);
        mViewPager.setAdapter(mAdapter);

        mViewPager.setPadding(50, 0, 50, 0);

        swipe_refresh_layout_transaction.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh () {
                swipe_refresh_layout_transaction.setRefreshing(false);
            }
        });

        return view;
    }
}
