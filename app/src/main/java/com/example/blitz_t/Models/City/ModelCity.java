package com.example.blitz_t.Models.City;

import java.util.ArrayList;

public class ModelCity {

    public static ArrayList<City> allCities(){
        ArrayList<City> cities = new ArrayList<>();
//        cities.add(new City(1, 1, "Bertoua"));
//        cities.add(new City(2, 1, "Douala"));
//        cities.add(new City(3, 1, "Dschang"));
//        cities.add(new City(4, 2, "Yamoussoukro"));
//        cities.add(new City(5, 3, "Paris"));
//        cities.add(new City(6, 3, "Lyon"));
//        cities.add(new City(7, 1, "Yaounde"));
//        cities.add(new City(8, 3, "Rennes"));
//        cities.add(new City(9, 2, "Abidjan"));
        return cities;
    }

    public ArrayList<City> searchCityFromCountry(int id_country){

        ArrayList<City> result =  new  ArrayList <> ();
//        for ( City c : allCities()) {
//            if (c.getId_country() == id_country) {
//                result.add(c);
//            }
//        }
        return result ;
    }

}
