package com.example.blitz_t.Models.Agency;

import com.example.blitz_t.Models.City.City;
import com.example.blitz_t.Models.Microfinance.Microfinance;

public class Agency {

    private String _id;
    private Microfinance microfinance;
    private City city;
    private String quartier;
    private double latitude;
    private double longitude;
    private String description_position;
    private String numero_telephone;
    private String heure_ouverture;
    private String heure_fermeture;
    private String image;

    public Agency () {
    }

    public Agency ( String _id , Microfinance microfinance , City city , String quartier , double latitude ,
                    double longitude , String description_position , String numero_telephone , String heure_ouverture ,
                    String heure_fermeture , String image ) {
        this._id = _id;
        this.microfinance = microfinance;
        this.city = city;
        this.quartier = quartier;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description_position = description_position;
        this.numero_telephone = numero_telephone;
        this.heure_ouverture = heure_ouverture;
        this.heure_fermeture = heure_fermeture;
        this.image = image;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Microfinance getMicrofinance () {
        return microfinance;
    }

    public void setMicrofinance ( Microfinance microfinance ) {
        this.microfinance = microfinance;
    }

    public City getCity () {
        return city;
    }

    public void setCity ( City city ) {
        this.city = city;
    }

    public String getQuartier () {
        return quartier;
    }

    public void setQuartier ( String quartier ) {
        this.quartier = quartier;
    }

    public double getLatitude () {
        return latitude;
    }

    public void setLatitude ( double latitude ) {
        this.latitude = latitude;
    }

    public double getLongitude () {
        return longitude;
    }

    public void setLongitude ( double longitude ) {
        this.longitude = longitude;
    }

    public String getDescription_position () {
        return description_position;
    }

    public void setDescription_position ( String description_position ) {
        this.description_position = description_position;
    }

    public String getNumero_telephone () {
        return numero_telephone;
    }

    public void setNumero_telephone ( String numero_telephone ) {
        this.numero_telephone = numero_telephone;
    }

    public String getHeure_ouverture () {
        return heure_ouverture;
    }

    public void setHeure_ouverture ( String heure_ouverture ) {
        this.heure_ouverture = heure_ouverture;
    }

    public String getHeure_fermeture () {
        return heure_fermeture;
    }

    public void setHeure_fermeture ( String heure_fermeture ) {
        this.heure_fermeture = heure_fermeture;
    }

    public String getImage () {
        return image;
    }

    public void setImage ( String image ) {
        this.image = image;
    }

    @Override
    public String toString () {
        return quartier + ", " + city.getName();
    }
}