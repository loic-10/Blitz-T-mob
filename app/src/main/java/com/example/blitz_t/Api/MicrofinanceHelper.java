package com.example.blitz_t.Api;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

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

public class MicrofinanceHelper {
    
    public static void setMicrofinance( Microfinance microfinance){
        new DB<Microfinance>(microfinance).setObject(microfinance, microfinance.get_id());
    }

    // --- GET ---

    public static DatabaseReference getMicrofinances(){
        return new DB<Microfinance>(new Microfinance()).getReference();
    }

    public static void checkMicrofinances( final RecyclerView recyclerView, final Context context, final String name, final Member member, final Activity activity){
        if(member != null){
            CustomerHelper.checkMicrofinancesMember(recyclerView, context, name, member, activity);
            return;
        }
        final ArrayList<Microfinance> microfinances = new ArrayList<>();
        getMicrofinances().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Microfinance microfinance = data.getValue(Microfinance.class);
                    if(microfinance != null && microfinance.getNom().toLowerCase().contains(name.toLowerCase())){
                        microfinances.add(microfinance);
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

    public static void deleteMicrofinance( String _id ){
        new DB<Microfinance>(new Microfinance()).removeObject(_id);
    }

}
