package com.example.blitz_t.Views.Transaction.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.R;

import java.util.ArrayList;

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
    private SwipeRefreshLayout swipe_refresh_recycler_transaction;
    private MenuItem buttonItem;
    private Toolbar app_bar;
    private ArrayList<Account> mAccounts;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_account);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        mActivity = this;

        mAccounts = new ArrayList<>();

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

        if(buttonItem != null){
            buttonItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick ( MenuItem item ) {
                    finish();
                    return true;
                }
            });
        }

        initEvent();

        checkTransactionCustomer( recycler_view_transaction, getApplicationContext(), mCustomer, mMicrofinance, this, "", 0, mAccount , swipe_refresh_recycler_transaction);

        checkAccountCustomer( viewPager_account, this, this, mCustomer, mMicrofinance, mAccount, mAccounts);
    }

    private void initView () {
        viewPager_account = findViewById(R.id.viewPager_account);
        recycler_view_transaction = findViewById(R.id.recycler_view_transaction);
        text_search = findViewById(R.id.text_search);
        swipe_refresh_recycler_transaction = findViewById(R.id.swipe_refresh_recycler_transaction);
        app_bar = findViewById(R.id.toolbar);
        buttonItem = app_bar.getMenu().findItem(R.id.menu_return);
    }

    private void initEvent () {
        text_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged ( CharSequence s , int start , int count , int after ) {

            }

            @Override
            public void onTextChanged ( CharSequence s , int start , int before , int count ) {
                checkTransactionCustomer( recycler_view_transaction, getApplicationContext(), mCustomer, mMicrofinance, mActivity, s.toString(), 0, mAccount , swipe_refresh_recycler_transaction);
            }

            @Override
            public void afterTextChanged ( Editable s ) {

            }
        });

        swipe_refresh_recycler_transaction.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkTransactionCustomer( recycler_view_transaction, getApplicationContext(), mCustomer, mMicrofinance, mActivity, "", 0, mAccount , swipe_refresh_recycler_transaction);
            }
        });
    }
}
