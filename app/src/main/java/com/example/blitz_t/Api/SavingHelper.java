package com.example.blitz_t.Api;

import com.example.blitz_t.Models.Saving.Saving;
import com.example.blitz_t.Models.Status.Status;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class SavingHelper extends DB<Saving> {

    public SavingHelper ( Saving data ) {
        super(data);
    }

    // --- CREATE AND SET ---

    public void setSaving( Saving saving){
        setObject(saving, saving.get_id());
    }

    //

    public void debitedSaving(Saving saving, double amount) throws Exception {
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

    public void creditedSaving(Saving saving, double amount)
    {
        saving.setBalance(saving.getBalance() + amount);
        setSaving(saving);
    }

    // --- GET ---

    public DatabaseReference getSavings(){
        return getReference();
    }

    public Saving getSaving( String _id)  {
        ArrayList<Saving> savings = new ArrayList<>();
        getReferenceObject(_id, savings , new Object());
        return savings.get(0);
    }

    public Saving getSavingInProgress( String id_account, ArrayList<Saving> savings){
        for (Saving saving : savings) {
            if(saving.getSaving_status().equals(Status.SavingStatus.in_progress) &&
                saving.getAccount().get_id().equals(id_account)){
                return saving;
            }
        }
        return null;
    }

    public void completedSavings(ArrayList<Saving> savings){
        getObjects(savings);
    }

    // --- DELETE ---

    public void deleteSaving( String _id ){
        removeObject(_id);
    }
}
