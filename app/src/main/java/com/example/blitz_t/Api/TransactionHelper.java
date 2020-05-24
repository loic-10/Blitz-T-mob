package com.example.blitz_t.Api;

import android.app.Activity;
import android.content.Context;

import com.example.blitz_t.Controllers.TransactionRecyclerAdapter;
import com.example.blitz_t.Models.Transaction.Transaction;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionHelper {

    // --- CREATE AND SET ---

    public static void setTransaction( Transaction transaction){
        new DB<Transaction>(transaction).setObject(transaction, transaction.get_id());
    }

    // --- GET ---

    public static DatabaseReference getTransactions(){
        return new DB<Transaction>(new Transaction()).getReference();
    }

    // --- DELETE ---

    public static void deleteTransaction( String _id ){
        new DB<Transaction>(new Transaction()).removeObject(_id);
    }
}
