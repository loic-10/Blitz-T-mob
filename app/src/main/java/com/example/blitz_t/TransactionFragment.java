package com.example.blitz_t;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.blitz_t.Api.TransactionHelper;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

import static com.example.blitz_t.Models.Model.checkTransactionCustomer;

public class TransactionFragment extends Fragment {
    private Customer mCustomer;
    private Microfinance mMicrofinance;
    private RecyclerView recycler_view_transaction;
    private ExtendedEditText text_search;

    @Nullable
    @Override
    public View onCreateView ( @NonNull LayoutInflater inflater , @Nullable ViewGroup container , @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_transaction , container , false);


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

        checkTransactionCustomer( recycler_view_transaction, view.getContext(), mCustomer, mMicrofinance, getActivity(), "", 0, null);

        return view;
    }

    private void initEvent ( final View view ) {
        text_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence s , int start , int count , int after ) {

            }

            @Override
            public void onTextChanged ( CharSequence s , int start , int before , int count ) {
                checkTransactionCustomer( recycler_view_transaction, view.getContext(), mCustomer, mMicrofinance, getActivity(), s.toString(), 0, null);
            }

            @Override
            public void afterTextChanged ( Editable s ) {

            }
        });
    }

    private void initView ( View view ) {
        recycler_view_transaction = view.findViewById(R.id.recycler_view_transaction);
        text_search = view.findViewById(R.id.text_search);
    }
}
