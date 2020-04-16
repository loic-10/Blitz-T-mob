package com.example.blitz_t.Api;

import com.example.blitz_t.Models.City.City;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CityHelper {
    private static final String COLLECTION_NAME = "/city/";

    // --- CREATE AND SET ---

    public static void setCity(City city){
        new DB<City>(city).setObject(city, city.get_id());
    }

    // --- GET ---

    public static DatabaseReference getCities(){
        return new DB<City>(new City()).getReference();
    }

    // --- DELETE ---

    public static void deleteCity( String _id ){
        new DB<City>(new City()).removeObject(_id);
    }

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getCityCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }
}
