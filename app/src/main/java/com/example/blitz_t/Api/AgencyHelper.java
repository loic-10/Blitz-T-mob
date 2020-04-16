package com.example.blitz_t.Api;

import android.app.Activity;
import android.content.Context;

import com.example.blitz_t.Controllers.AgencyMicrofinanceAdapter;
import com.example.blitz_t.Controllers.MicrofinanceAdapter;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Agency.Agency;
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

public class AgencyHelper {
    
    // --- CREATE AND SET ---

    public static void setAgency( Agency agency){
        new DB<Agency>(agency).setObject(agency, agency.get_id());
    }

    // --- GET ---

    public static DatabaseReference getAgencies(){
        return new DB<Agency>(new Agency()).getReference();
    }

    public static void checkAgenciesMicrofinance( final RecyclerView recyclerView, final Context context, final String name, final Microfinance microfinance, final Activity activity){
        final ArrayList<Agency> agencies = new ArrayList<>();
        getAgencies().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Agency agency = data.getValue(Agency.class);
                    if(agency != null &&
                            (agency.getQuartier().toLowerCase().contains(name.toLowerCase()) ||
                                    agency.getCity().getName().toLowerCase().contains(name.toLowerCase())
                            ) &&
                            agency.getMicrofinance().get_id().equals(microfinance.get_id())){
                        agencies.add(agency);
                    }
                }
                AgencyMicrofinanceAdapter adapter = new AgencyMicrofinanceAdapter(agencies , context, activity);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {
                Snackbar.make(recyclerView, R.string.text_operation_failed, Snackbar.LENGTH_LONG);
            }
        });
    }

    // --- DELETE ---

    public static void deleteAgency( String _id ){
        new DB<Agency>(new Agency()).removeObject(_id);
    }
}
