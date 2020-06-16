package com.example.blitz_t.Api;

import android.app.Activity;
import android.content.Context;

import com.example.blitz_t.Controllers.AgencyMicrofinanceRecyclerAdapter;
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

public class AgencyHelper extends DB<Agency> {

    public AgencyHelper ( Agency data ) {
        super(data);
    }

    // --- CREATE AND SET ---

    public void setAgency( Agency agency){
        setObject(agency, agency.get_id());
    }

    // --- GET ---

    public DatabaseReference getAgencies(){
        return getReference();
    }

    // --- DELETE ---

    public void deleteAgency( String _id ){
        removeObject(_id);
    }
}
