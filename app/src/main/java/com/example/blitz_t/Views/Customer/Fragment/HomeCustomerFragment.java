package com.example.blitz_t.Views.Customer.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.*;
import android.view.ViewGroup;
import com.example.blitz_t.Controllers.AccountPagerAdapter;
import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.Account.Fragment.AccountCustomerFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import static com.example.blitz_t.Models.Model.checkAccountCustomer;
import static com.example.blitz_t.Models.Model.checkTransactionCustomer;

public class HomeCustomerFragment extends Fragment {

    private ViewPager mViewPager;
    private AccountPagerAdapter mAdapter;
    private ArrayList mAccounts;
    private Customer mCustomer;
    private Microfinance mMicrofinance;
    private RecyclerView recycler_view_transaction;
    private SwipeRefreshLayout swipe_refresh_recycler_transaction;
    private View button_more_account;

    private View view;

    private FragmentActivity mFragmentActivity;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.fragment_home , container , false);

        mFragmentActivity = getActivity();

        initView(view);

        initEvent();

        mCustomer = (Customer) Model.contentPreference(
                new Customer(),
                getString(R.string.SHARED_PREF_CUSTOMER_LOGIN),
                getString(R.string.PREFERENCE_FILE_KEY),
                getActivity());

        mMicrofinance = (Microfinance) Model.contentPreference(
                new Microfinance(),
                getString(R.string.SHARED_PREF_MICROFINANCE_SELECT),
                getString(R.string.PREFERENCE_FILE_KEY),
                getActivity());

        checkAccountCustomer( mViewPager, view.getContext(), getActivity(), mCustomer, mMicrofinance, null, new ArrayList<Account>());
        checkTransactionCustomer( recycler_view_transaction, view.getContext(), mCustomer, mMicrofinance, getActivity(), "", 4, null , swipe_refresh_recycler_transaction);

        return view;
    }

    private void initEvent () {
        swipe_refresh_recycler_transaction.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkTransactionCustomer( recycler_view_transaction, view.getContext(), mCustomer, mMicrofinance, getActivity(), "", 4, null, swipe_refresh_recycler_transaction);
            }
        });

        button_more_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {

                mFragmentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new AccountCustomerFragment()).commit();
                mFragmentActivity.setTitle(getString(R.string.text_menu_account).toUpperCase() + " | " + mMicrofinance.getNom());

                NavigationView navigationView = mFragmentActivity.findViewById(R.id.nav_view);

                navigationView.setCheckedItem(R.id.nav_account);

            }
        });
    }

    private void initView ( View view ) {
        mViewPager = view.findViewById(R.id.viewPager_account);
        recycler_view_transaction = view.findViewById(R.id.recycler_view_transaction);
        swipe_refresh_recycler_transaction = view.findViewById(R.id.swipe_refresh_recycler_transaction);
        button_more_account = view.findViewById(R.id.button_more_account);
    }
}
