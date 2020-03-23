package com.example.blitz_t.Api;

import com.example.blitz_t.Models.City.City;
import com.example.blitz_t.Models.Country.Country;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class CityHelper {
    private static final String COLLECTION_NAME = "/city/";

    // --- COLLECTION REFERENCE ---

    public static FirebaseDatabase getDatabase() {
        return FirebaseDatabase.getInstance();
    }

    public static DatabaseReference getReferenceCity(String value){
        return getDatabase().getReference(COLLECTION_NAME + value );
    }

    // --- CREATE ---

    public static void createCity( Country country , String name ) {
        City city = new City( country , name );
        getReferenceCity(name).setValue(city);
    }

    // --- GET ---

    public static List<City> getAllCities(){
        final List<City> cities = new ArrayList<>();

        getReferenceCity("").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if(child != null){
                        City city = child.getValue(City.class);
                        cities.add(city);
                    }
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {

            }
        });

        return cities;
    }

    public static List<City> getCitiesForCountry( final String country_name){
        final List<City> cities = new ArrayList<>();

        getReferenceCity("").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if(child != null){
                        City city = child.getValue(City.class);
                        if(city.getCountry().getName().equals(country_name)) {
                            cities.add(city);
                        }
                    }
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {

            }
        });

        return cities;
    }

    // --- UPDATE ---


    // --- DELETE ---

    public static void deleteCity(String name) {
        getReferenceCity(name).removeValue();
    }
}
