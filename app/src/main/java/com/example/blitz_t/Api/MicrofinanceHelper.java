package com.example.blitz_t.Api;

import android.app.Activity;
import android.content.Context;

import com.example.blitz_t.Controllers.MicrofinanceRecyclerAdapter;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.R;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.snapshot.IndexedNode;

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

    public static Microfinance getMicrofinance( String _id){
        ArrayList<Microfinance> microfinances = new ArrayList<>();
        new DB<Microfinance>(new Microfinance()).getReferenceObject(_id, microfinances, new Microfinance());
        return microfinances.size() > 0 ? microfinances.get(0) : null;
    }

    // --- DELETE ---

    public static void deleteMicrofinance( String _id ){
        new DB<Microfinance>(new Microfinance()).removeObject(_id);
    }

}
