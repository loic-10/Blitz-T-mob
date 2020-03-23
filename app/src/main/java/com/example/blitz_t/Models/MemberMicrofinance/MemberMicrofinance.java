package com.example.blitz_t.Models.MemberMicrofinance;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class MemberMicrofinance implements Parcelable {
    private int _id;
    private int id_membre;
    private int id_microfinance;
    private double rating;

    public MemberMicrofinance () {
    }

    public MemberMicrofinance ( int _id , int id_membre , int id_microfinance , double rating ) {
        this._id = _id;
        this.id_membre = id_membre;
        this.id_microfinance = id_microfinance;
        this.rating = rating;
    }

    protected MemberMicrofinance ( Parcel in ) {
        _id = in.readInt();
        id_membre = in.readInt();
        id_microfinance = in.readInt();
        rating = in.readDouble();
    }

    public static final Creator<MemberMicrofinance> CREATOR = new Creator<MemberMicrofinance>() {
        @Override
        public MemberMicrofinance createFromParcel ( Parcel in ) {
            return new MemberMicrofinance(in);
        }

        @Override
        public MemberMicrofinance[] newArray ( int size ) {
            return new MemberMicrofinance[size];
        }
    };

    public int get_id () {
        return _id;
    }

    public int getId_membre () {
        return id_membre;
    }

    public int getId_microfinance () {
        return id_microfinance;
    }

    public double getRating () {
        return rating;
    }

    public void setRating ( double rating ) {
        this.rating = rating;
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( !(o instanceof MemberMicrofinance) ) return false;
        MemberMicrofinance that = (MemberMicrofinance) o;
        return getId_membre() == that.getId_membre() &&
                getId_microfinance() == that.getId_microfinance();
    }

    @Override
    public int hashCode () {
        return Objects.hash(getId_membre() , getId_microfinance());
    }


    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel ( Parcel dest , int flags ) {
        dest.writeInt(_id);
        dest.writeInt(id_membre);
        dest.writeInt(id_microfinance);
        dest.writeDouble(rating);
    }
}
