package com.example.blitz_t.Models.Avalist;

import com.example.blitz_t.Models.Guarantee.Guarantee;

public class Avalist {

    private String _id;
    private Guarantee guarantee;
    private String fullname;
    private String profession;
    private String description;
    private double number_months;

    public Avalist () {
    }

    public Avalist ( String _id , Guarantee guarantee , String fullname , String profession , String description , double number_months ) {
        this._id = _id;
        this.guarantee = guarantee;
        this.fullname = fullname;
        this.profession = profession;
        this.description = description;
        this.number_months = number_months;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Guarantee getGuarantee () {
        return guarantee;
    }

    public void setGuarantee ( Guarantee guarantee ) {
        this.guarantee = guarantee;
    }

    public String getFullname () {
        return fullname;
    }

    public void setFullname ( String fullname ) {
        this.fullname = fullname;
    }

    public String getProfession () {
        return profession;
    }

    public void setProfession ( String profession ) {
        this.profession = profession;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }

    public double getNumber_months () {
        return number_months;
    }

    public void setNumber_months ( double number_months ) {
        this.number_months = number_months;
    }
}
