package com.example.blitz_t.Models.City;

import com.example.blitz_t.Models.Country.Country;

public class City{
    private Country country;
    private String name;

    public City () {
    }

    public City ( Country country , String name ) {
        this.country = country;
        this.name = name;
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
        return name;
    }
}
