package com.example.blitz_t;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.blitz_t.Controllers.AccountPagerAdapter;
import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;

import static com.example.blitz_t.Models.Model.checkAccountCustomer;
import static com.example.blitz_t.Models.Model.checkTransactionCustomer;

public class TransactionAccountActivity extends AppCompatActivity {

    private ViewPager viewPager_account;
    private Account mAccount;
    private Customer mCustomer;
    private Microfinance mMicrofinance;
    private RecyclerView recycler_view_transaction;
    private ExtendedEditText text_search;
    private Activity mActivity;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_account);

        mActivity = this;

        this.setTitle(R.string.text_menu_transaction);

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mCustomer = (Customer) Model.contentPreference(
                new Customer(),
                getString(R.string.SHARED_PREF_CUSTOMER_LOGIN),
                getString(R.string.PREFERENCE_FILE_KEY),
                this);

        mAccount = (Account) Model.contentPreference(
                new Account(),
                getString(R.string.SHARED_PREF_ACCOUNT_SELECT),
                getString(R.string.PREFERENCE_FILE_KEY),
                this);

        mMicrofinance = (Microfinance) Model.contentPreference(
                new Microfinance(),
                getString(R.string.SHARED_PREF_MICROFINANCE_SELECT),
                getString(R.string.PREFERENCE_FILE_KEY),
                this);

        initView();
        initEvent();

        checkTransactionCustomer( recycler_view_transaction, getApplicationContext(), mCustomer, mMicrofinance, this, "", 0, mAccount);

        checkAccountCustomer( viewPager_account, this, this, mCustomer, mMicrofinance, mAccount);
    }

    private void initView () {
        viewPager_account = findViewById(R.id.viewPager_account);
        recycler_view_transaction = findViewById(R.id.recycler_view_transaction);
        text_search = findViewById(R.id.text_search);
    }

    private void initEvent () {
        text_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence s , int start , int count , int after ) {

            }

            @Override
            public void onTextChanged ( CharSequence s , int start , int before , int count ) {
                checkTransactionCustomer( recycler_view_transaction, getApplicationContext(), mCustomer, mMicrofinance, mActivity, s.toString(), 0, mAccount);
            }

            @Override
            public void afterTextChanged ( Editable s ) {

            }
        });
    }
}
