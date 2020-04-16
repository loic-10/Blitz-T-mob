package com.example.blitz_t.Models.Benefit;

import com.example.blitz_t.Models.Account.Account;

public class Benefit {

    private String _id;
    private String name;
    private String description_condition;
    private double percentage;
    private Account account;

    public Benefit () {
    }

    public Benefit ( String _id , String name , String description_condition , double percentage , Account account ) {
        this._id = _id;
        this.name = name;
        this.description_condition = description_condition;
        this.percentage = percentage;
        this.account = account;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getDescription_condition () {
        return description_condition;
    }

    public void setDescription_condition ( String description_condition ) {
        this.description_condition = description_condition;
    }

    public double getPercentage () {
        return percentage;
    }

    public void setPercentage ( double percentage ) {
        this.percentage = percentage;
    }

    public Account getAccount () {
        return account;
    }

    public void setAccount ( Account account ) {
        this.account = account;
    }
}
