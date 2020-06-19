package com.example.blitz_t.Models.City;

import com.example.blitz_t.Models.Country.Country;

public class City{

    private String _id;
    private Country country;
    private String name;

    public City () {
    }

    public City ( String _id , Country country , String name ) {
        this._id = _id;
        this.country = country;
        this.name = name;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Country getCountry () {
        return country;
    }

    public void setCountry ( Country country ) {
        this.country = country;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    @Override
    public String toString () {
        return name + " (" + country.getName() + ")";
    }
}
