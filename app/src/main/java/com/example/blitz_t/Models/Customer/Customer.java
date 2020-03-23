package com.example.blitz_t.Models.Customer;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.blitz_t.Models.Model;

import java.util.Date;
import java.util.Objects;

public class Customer extends Model implements Parcelable {
    private int _id;
    private int id_microfinance;
    private  String code_client;
    private  String code_membre;
    private Date date_enregistrement;
    private  String profession;
    private  String stutus_client;
    private  String mot_de_passe;

    public Customer() {
    }

    public Customer(int _id, int id_microfinance, String code_membre, Date date_enregistrement,
                    String profession, String stutus_client, String mot_de_passe) {
        this._id = _id;
        this.id_microfinance = id_microfinance;
        this.code_client = generateCode("cli", _id, 7, '0');
        this.code_membre = code_membre;
        this.date_enregistrement = date_enregistrement;
        this.profession = profession;
        this.stutus_client = stutus_client;
        this.mot_de_passe = mot_de_passe;
    }

    protected Customer(Parcel in) {
        _id = in.readInt();
        id_microfinance = in.readInt();
        code_client = in.readString();
        code_membre = in.readString();
        date_enregistrement = new Date(in.readString());
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

    public int get_id() {
        return _id;
    }

    public int getId_microfinance() {
        return id_microfinance;
    }

    public String getCode_client() {
        return code_client;
    }

    public String getCode_membre() {
        return code_membre;
    }

    public Date getDate_enregistrement() {
        return date_enregistrement;
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
        return get_id() == customer.get_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_id());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(_id);
        parcel.writeInt(id_microfinance);
        parcel.writeString(code_client);
        parcel.writeString(code_membre);
        parcel.writeString(date_enregistrement.toString());
        parcel.writeString(profession);
        parcel.writeString(stutus_client);
        parcel.writeString(mot_de_passe);
    }
}
