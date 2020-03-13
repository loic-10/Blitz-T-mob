package com.example.blitz_t.Models.Customer;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

public class Customer implements Parcelable {
    private int id_client;
    private int id_microfinance;
    private  String code_client;
    private  String code_utilisateur;
    private Date date_enregistrement;
    private  String profession;
    private  String stutus_client;
    private  String mot_de_passe;

    public Customer(int id_client, int id_microfinance, String code_client, String code_utilisateur, Date date_enregistrement, String profession, String stutus_client, String mot_de_passe) {
        this.id_client = id_client;
        this.id_microfinance = id_microfinance;
        this.code_client = code_client;
        this.code_utilisateur = code_utilisateur;
        this.date_enregistrement = date_enregistrement;
        this.profession = profession;
        this.stutus_client = stutus_client;
        this.mot_de_passe = mot_de_passe;
    }

    public Customer() {
    }

    protected Customer(Parcel in) {
        id_client = in.readInt();
        id_microfinance = in.readInt();
        code_client = in.readString();
        code_utilisateur = in.readString();
        profession = in.readString();
        stutus_client = in.readString();
        mot_de_passe = in.readString();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public int getId_client() {
        return id_client;
    }

    public int getId_microfinance() {
        return id_microfinance;
    }


    public String getCode_client() {
        return code_client;
    }

    public void setCode_client(String code_client) {
        this.code_client = code_client;
    }

    public String getCode_utilisateur() {
        return code_utilisateur;
    }

    public void setCode_utilisateur(String code_utilisateur) {
        this.code_utilisateur = code_utilisateur;
    }

    public Date getDate_enregistrement() {
        return date_enregistrement;
    }

    public void setDate_enregistrement(Date date_enregistrement) {
        this.date_enregistrement = date_enregistrement;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getStutus_client() {
        return stutus_client;
    }

    public void setStutus_client(String stutus_client) {
        this.stutus_client = stutus_client;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    @Override
    public String toString() {
        return code_client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getId_client() == customer.getId_client();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_client());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id_client);
        parcel.writeInt(id_microfinance);
        parcel.writeString(code_client);
        parcel.writeString(code_utilisateur);
        parcel.writeString(profession);
        parcel.writeString(stutus_client);
        parcel.writeString(mot_de_passe);
    }
}
