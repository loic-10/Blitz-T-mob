package com.example.blitz_t.Controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blitz_t.AccountCustomerActivity;
import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Status.Status;
import com.example.blitz_t.R;
import com.example.blitz_t.Views.DesignApp;
import com.example.blitz_t.Views.Login.Customer.LoginCustomerActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class AccountPagerAdapter extends PagerAdapter {

    private List<Account> mAccounts;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private Activity mActivity;
    private Microfinance mMicrofinance;

    public AccountPagerAdapter ( List<Account> accounts , Context context  , Activity activity ) {
        mAccounts = accounts;
        mContext = context;
        mActivity = activity;
        mMicrofinance = (Microfinance) Model.contentPreference(
                new Microfinance(),
                mContext.getString(R.string.SHARED_PREF_MICROFINANCE_SELECT),
                mContext.getString(R.string.PREFERENCE_FILE_KEY),
                (ContextWrapper) mContext);
    }

    @Override
    public int getCount () {
        return mAccounts.size();
    }

    @Override
    public boolean isViewFromObject ( @NonNull View view , @NonNull Object object ) {
        return view.equals(object);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public Object instantiateItem ( @NonNull ViewGroup container , int position ) {
        mLayoutInflater = LayoutInflater.from(mContext);
        View view = mLayoutInflater.inflate(R.layout.item_account, container, false);

        final Account account = mAccounts.get(position);

        ImageView image_view_status_account  = view.findViewById(R.id.image_view_status_account);
        ImageView img_micro_finance  = view.findViewById(R.id.img_micro_finance);
        TextView text_balance_account = view.findViewById(R.id.text_balance_account);
        TextView text_code_account = view.findViewById(R.id.text_code_account);
        TextView text_account_type = view.findViewById(R.id.text_account_type);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick ( View v ) {
            Intent intent = new Intent(mContext , AccountCustomerActivity.class);
            Model.saveFormPreference(account , mContext.getString(R.string.SHARED_PREF_ACCOUNT_SELECT) ,
                    mContext.getString(R.string.PREFERENCE_FILE_KEY) , mActivity);
            mContext.startActivity(intent);
            }
        });

        DesignApp.updateImage(mContext , img_micro_finance, mMicrofinance.getImage() , null);

        image_view_status_account.setImageResource(account.getAccount_status().equals(Status.AccountStatus.activated) ?
                R.drawable.ic_lock_open_black_24dp :
                R.drawable.ic_lock_outline_black_24dp);

        text_balance_account.setText(account.getBalance() + " " + mMicrofinance.getCurrency().getCurrency_symbol());
        text_code_account.setText(account.get_id());
        text_account_type.setText(account.getAccount_type().toString());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem ( @NonNull ViewGroup container , int position , @NonNull Object object ) {
        container.removeView((View) object);
    }
}
