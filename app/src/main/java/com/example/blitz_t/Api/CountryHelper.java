package com.example.blitz_t.Api;


import com.example.blitz_t.Models.Country.Country;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CountryHelper extends DB<Country> {

    public CountryHelper ( Country data ) {
        super(data);
    }

    // --- CREATE ---

    public void setCountry(Country country){
        setObject(country, country.get_id());
    }

    // --- GET ---

    public DatabaseReference getCountries(){
        return getReference();
    }

    // --- DELETE ---

    public void deleteCountry( String _id ){
        removeObject(_id);
    }
}
