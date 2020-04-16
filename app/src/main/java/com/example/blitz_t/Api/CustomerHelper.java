package com.example.blitz_t.Api;

import android.app.Activity;
import android.content.Context;

import com.example.blitz_t.Controllers.MicrofinanceAdapter;
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
    
    public static void setCustomer( Customer customer){
        new DB<Customer>(customer).setObject(customer, customer.get_id());
    }

    // --- GET ---

    public static DatabaseReference getCustomers(){
        return new DB<Customer>(new Customer()).getReference();
    }

    public static void checkMicrofinancesMember( final RecyclerView recyclerView, final Context context, final String name, final Member member, final Activity activity){
        final ArrayList<Microfinance> microfinances = new ArrayList<>();
        getCustomers().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Customer customer = data.getValue(Customer.class);
                    if(customer != null && customer.getMicrofinance().getNom().toLowerCase().contains(name.toLowerCase()) && customer.getMember().get_id().equals(member.get_id())){
                        microfinances.add(customer.getMicrofinance());
                    }
                }
                MicrofinanceAdapter adapter = new MicrofinanceAdapter(microfinances , context, activity);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Snackbar.make(recyclerView, R.string.text_operation_failed, Snackbar.LENGTH_LONG);
            }
        });
    }

    // --- DELETE ---

    public static void deleteCustomer( String _id ){
        new DB<Customer>(new Customer()).removeObject(_id);
    }
}
