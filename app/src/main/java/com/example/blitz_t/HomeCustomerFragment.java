package com.example.blitz_t;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.blitz_t.Controllers.AccountPagerAdapter;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
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

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_home , container , false);

        initView(view);

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

        checkAccountCustomer( mViewPager, view.getContext(), getActivity(), mCustomer, mMicrofinance, null);
        checkTransactionCustomer( recycler_view_transaction, view.getContext(), mCustomer, mMicrofinance, getActivity(), "", 4, null);

        return view;
    }

    private void initView ( View view ) {
        mViewPager = view.findViewById(R.id.viewPager_account);
        recycler_view_transaction = view.findViewById(R.id.recycler_view_transaction);
    }
}
