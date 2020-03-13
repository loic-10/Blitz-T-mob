package com.example.blitz_t.Models;


import android.os.Parcel;
import android.os.Parcelable;

public class Agences implements Parcelable {
    private int _id;
    private  int id_microfinance;
    private String ville;
    private  String heure_ouverture;
    private String heure_fermeture;
    private  String description;
    private String quartier;
    private int numero_tel;
    public double latitude;
    public double longitude;

    public Agences(int _id, int id_microfinance, String ville, String heure_ouverture, String heure_fermeture, String description, String quartier, int numero_tel, double latitude,double longitude) {
        this._id = _id;
        this.id_microfinance = id_microfinance;
        this.ville = ville;
        this.heure_ouverture = heure_ouverture;
        this.heure_fermeture=heure_fermeture;
        this.description = description;
        this.quartier = quartier;
        this.numero_tel = numero_tel;
        this.latitude=latitude;
        this.longitude=longitude;

    }

public Agences(int id_microfinance){

    this.id_microfinance = id_microfinance;
}

    protected Agences(Parcel in) {
        _id = in.readInt();
        id_microfinance=in.readInt();
        ville = in.readString();
        heure_ouverture = in.readString();
        heure_fermeture = in.readString();
        description = in.readString();
        quartier = in.readString();
        numero_tel = in.readInt();
        latitude=in.readDouble();
        longitude=in.readDouble();

    }

    public static final Creator<Agences> CREATOR = new Creator<Agences>() {
        @Override
        public Agences createFromParcel(Parcel in) {
            return new Agences(in);
        }

        @Override
        public Agences[] newArray(int size) {
            return new Agences[size];
        }
    };

    public int get_id() {
        return _id;
    }
    public int getId_microfinance(){
        return  id_microfinance;
    }
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getHeure_ouverture() {
        return heure_ouverture;
    }

    public void setHeure_ouverture(String heure_ouverture) {
        this.heure_ouverture = heure_ouverture;
    }

    public String getHeure_fermeture() {
        return heure_fermeture;
    }

    public void setHeure_fermeture(String heure_fermeture) {
        this.heure_fermeture = heure_fermeture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }



    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public int getNumero_tel() {
        return numero_tel;
    }

    public void setNumero_tel(int numero_tel) {
        this.numero_tel = numero_tel;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeInt(id_microfinance);
        dest.writeString(ville);
        dest.writeString(heure_ouverture);
        dest.writeString(heure_fermeture);
        dest.writeString(description);
        dest.writeString(quartier);
        dest.writeInt(numero_tel);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }


}