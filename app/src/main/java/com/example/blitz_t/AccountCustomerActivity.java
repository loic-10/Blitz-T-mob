package com.example.blitz_t;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import com.example.blitz_t.Controllers.AccountPagerAdapter;
import com.example.blitz_t.Controllers.MakeTransactionDialog;
import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Status.Status;

import static com.example.blitz_t.Models.Model.checkAccountCustomer;

public class AccountCustomerActivity extends AppCompatActivity {

    private ViewPager viewPager_account;
    private AccountPagerAdapter mAdapter;
    private Account mAccount;
    private Customer mCustomer;
    private Microfinance mMicrofinance;
    private View btn_credit;
    private View btn_transaction;
    private View btn_make_deposit;
    private View btn_make_withdrawal;
    private View btn_make_transfer;
    private AccountCustomerActivity mAccountCustomerActivity;
    private Member mMember;
    private Activity mActivity;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_customer);

        this.setTitle(R.string.text_menu_account);

        mAccountCustomerActivity = this;
        mActivity = this;

        getWindow().setStatusBarColor(Color.TRANSPARENT);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

        initEvent();

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

        mMember = (Member) Model.contentPreference(
                new Member(),
                getString(R.string.SHARED_PREF_MEMBER_LOGIN),
                getString(R.string.PREFERENCE_FILE_KEY),
                this);

        mMicrofinance = (Microfinance) Model.contentPreference(
                new Microfinance(),
                getString(R.string.SHARED_PREF_MICROFINANCE_SELECT),
                getString(R.string.PREFERENCE_FILE_KEY),
                this);

        checkAccountCustomer( viewPager_account, this, this, mCustomer, mMicrofinance, mAccount);
    }

    private void initEvent () {
        btn_credit.setOnClickListener(mListener);
        btn_transaction.setOnClickListener(mListener);
        btn_make_deposit.setOnClickListener(mListener);
        btn_make_withdrawal.setOnClickListener(mListener);
        btn_make_transfer.setOnClickListener(mListener);
    }

    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick ( View v ) {
            Intent intent = null;
            MakeTransactionDialog dialog = new MakeTransactionDialog();
            switch (v.getId()){
                case R.id.btn_credit :
                    break;
                case R.id.btn_transaction :
                    intent = new Intent(getApplicationContext(), TransactionAccountActivity.class);
                    break;
                case R.id.btn_make_deposit :
                    dialog.showDialog(mAccountCustomerActivity , Status.TransactionType.deposit, mMember, mMicrofinance, mAccount, mCustomer, mActivity);
                    break;
                case R.id.btn_make_withdrawal :
                    dialog.showDialog(mAccountCustomerActivity , Status.TransactionType.withdrawal, mMember, mMicrofinance, mAccount, mCustomer, mActivity);
                    break;
                case R.id.btn_make_transfer :
                    dialog.showDialog(mAccountCustomerActivity , Status.TransactionType.transfer, mMember, mMicrofinance, mAccount, mCustomer, mActivity);
                    break;
            }

            if(intent != null){
                startActivity(intent);
            }
        }
    };

    private void initView () {
        viewPager_account = findViewById(R.id.viewPager_account);
        btn_credit = findViewById(R.id.btn_credit);
        btn_transaction = findViewById(R.id.btn_transaction);
        btn_make_deposit = findViewById(R.id.btn_make_deposit);
        btn_make_withdrawal = findViewById(R.id.btn_make_withdrawal);
        btn_make_transfer = findViewById(R.id.btn_make_transfer);

    }
}