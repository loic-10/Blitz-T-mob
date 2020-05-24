package com.example.blitz_t.Api;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.blitz_t.Controllers.MicrofinanceRecyclerAdapter;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerHelper {

    // --- CREATE AND SET ---

    public static void setCustomer( Customer customer){
        new DB<Customer>(customer).setObject(customer, customer.get_id());
    }

    // --- GET ---

    public static DatabaseReference getCustomers(){
        return new DB<Customer>(new Customer()).getReference();
    }

    // --- DELETE ---

    public static void deleteCustomer( String _id ){
        new DB<Customer>(new Customer()).removeObject(_id);
    }
}
