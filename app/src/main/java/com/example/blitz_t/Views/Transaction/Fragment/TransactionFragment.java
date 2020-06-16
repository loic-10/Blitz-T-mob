package com.example.blitz_t.Views.Transaction.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

import static com.example.blitz_t.Models.Model.checkTransactionCustomer;

public class TransactionFragment extends Fragment {
    private Customer mCustomer;
    private Microfinance mMicrofinance;
    private RecyclerView recycler_view_transaction;
    private ExtendedEditText text_search;
    private SwipeRefreshLayout swipe_refresh_recycler_transaction;
    private View view;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        view = inflater.inflate(R.layout.fragment_transaction , container , false);


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

        checkTransactionCustomer( recycler_view_transaction, view.getContext(), mCustomer, mMicrofinance, getActivity(), "", 0, null , swipe_refresh_recycler_transaction);

        return view;
    }

    private void initEvent ( final View view ) {
        text_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence s , int start , int count , int after ) {

            }

            @Override
            public void onTextChanged ( CharSequence s , int start , int before , int count ) {
                checkTransactionCustomer( recycler_view_transaction, view.getContext(), mCustomer, mMicrofinance, getActivity(), s.toString(), 0, null , swipe_refresh_recycler_transaction);
            }

            @Override
            public void afterTextChanged ( Editable s ) {

            }
        });

        swipe_refresh_recycler_transaction.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkTransactionCustomer( recycler_view_transaction, view.getContext(), mCustomer, mMicrofinance, getActivity(), "", 0, null, swipe_refresh_recycler_transaction);
            }
        });

    }

    private void initView ( View view ) {
        recycler_view_transaction = view.findViewById(R.id.recycler_view_transaction);
        text_search = view.findViewById(R.id.text_search);
        swipe_refresh_recycler_transaction = view.findViewById(R.id.swipe_refresh_recycler_transaction);
    }
}
