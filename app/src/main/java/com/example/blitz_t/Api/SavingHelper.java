package com.example.blitz_t.Api;

import com.example.blitz_t.Models.Saving.Saving;
import com.example.blitz_t.Models.Status.Status;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class SavingHelper {

    // --- CREATE AND SET ---

    public static void setSaving( Saving saving){
        new DB<Saving>(saving).setObject(saving, saving.get_id());
    }

    //

    public static void debitedSaving(Saving saving, double amount) throws Exception {
        saving.setBalance(saving.getBalance() - amount);

        if(saving.getBalance() >= 0)
        {
            setSaving(saving);
            // Resources.getSystem().getString(R.string.project_id);
        }
        else
        {
            throw new Exception("L'epargne ne peut etre negative!");
        }
    }

    public static void creditedSaving(Saving saving, double amount)
    {
        saving.setBalance(saving.getBalance() + amount);
        setSaving(saving);
    }

    // --- GET ---

    public static DatabaseReference getSavings(){
        return new DB<Saving>(new Saving()).getReference();
    }

    public static Saving getSaving( String _id)  {
        ArrayList<Saving> savings = new ArrayList<>();
        new DB<Saving>(new Saving()).getReferenceObject(_id, savings , new Object());
        return savings.get(0);
    }

    public static void getSavingInProgress( final String id_account, final ArrayList<Saving> savings){
        getSavings().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Saving saving = data.getValue(Saving.class);
                    if(saving.getSaving_status().equals(Status.SavingStatus.in_progress) &&
                        saving.getAccount().get_id().equals(id_account)){
                        savings.add(saving);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {

            }
        });
    }

    // --- DELETE ---

    public static void deleteSaving( String _id ){
        new DB<Saving>(new Saving()).removeObject(_id);
    }
}
