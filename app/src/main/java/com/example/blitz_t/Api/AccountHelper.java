package com.example.blitz_t.Api;

import android.content.Context;

import com.example.blitz_t.Controllers.AccountPagerAdapter;
import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class AccountHelper {

    // --- CREATE AND SET ---

    public static void setAccount( Account account){
        new DB<Account>(account).setObject(account, account.get_id());
    }

    // --- GET ---

    public static DatabaseReference getAccounts(){
        return new DB<Account>(new Account()).getReference();
    }


    // --- DELETE ---

    public static void deleteAccount( String _id ){
        new DB<Account>(new Account()).removeObject(_id);
    }
}
