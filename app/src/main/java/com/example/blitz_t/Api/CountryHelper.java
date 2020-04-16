package com.example.blitz_t.Api;


import com.example.blitz_t.Models.Country.Country;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CountryHelper {
    private static final String COLLECTION_NAME = "/country/";

    // --- COLLECTION REFERENCE ---

    // Write a message to the database

    public static CollectionReference getCountryCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    public static void setCountry(Country country){
        new DB<Country>(country).setObject(country, country.get_id());
    }

    // --- GET ---

    public static DatabaseReference getCountries(){
        return new DB<Country>(new Country()).getReference();
    }

    // --- DELETE ---

    public static void deleteCountry( String _id ){
        new DB<Country>(new Country()).removeObject(_id);
    }
}
