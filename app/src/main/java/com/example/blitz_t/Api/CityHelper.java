package com.example.blitz_t.Api;

import com.example.blitz_t.Models.City.City;
import com.google.firebase.database.DatabaseReference;

public class CityHelper extends DB<City> {

    public CityHelper ( City data ) {
        super(data);
    }

    // --- CREATE AND SET ---

    public void setCity(City city){
        setObject(city, city.get_id());
    }

    // --- GET ---

    public DatabaseReference getCities(){
        return getReference();
    }

    // --- DELETE ---

    public void deleteCity( String _id ){
        removeObject(_id);
    }
}
