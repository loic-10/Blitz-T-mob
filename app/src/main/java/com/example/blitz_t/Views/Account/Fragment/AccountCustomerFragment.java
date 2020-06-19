package com.example.blitz_t.Views.Account.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.R;

import static com.example.blitz_t.Models.Model.checkAccountCustomer;

public class AccountCustomerFragment extends Fragment {

    private RecyclerView recycler_view_account;
    private View btn_new_account;
    private Customer mCustomer;
    private Microfinance mMicrofinance;
    private View view;


    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {

        view = inflater.inflate(R.layout.fragment_all_account , container , false);

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

        initView(view);

        initEvent(view);

        checkAccountCustomer( recycler_view_account, view.getContext(), mCustomer, mMicrofinance, getActivity());

        return view;
    }

    private void initEvent ( View view ) {
    }

    private void initView ( View view ) {
        recycler_view_account = view.findViewById(R.id.recycler_view_account);
        btn_new_account = view.findViewById(R.id.btn_new_account);

    }
}
