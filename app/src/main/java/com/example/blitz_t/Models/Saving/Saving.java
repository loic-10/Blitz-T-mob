package com.example.blitz_t.Models.Saving;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Status.Status;

public class Saving {

    private String _id;
    private Account account;
    private int number_months;
    private double balance;
    private String saving_date;
    private Status.SavingStatus saving_status;

    public Saving () {
    }

    public Saving ( String _id , Account account , int number_months , double balance , String saving_date , Status.SavingStatus saving_status ) {
        this._id = _id;
        this.account = account;
        this.number_months = number_months;
        this.balance = balance;
        this.saving_date = saving_date;
        this.saving_status = saving_status;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Account getAccount () {
        return account;
    }

    public void setAccount ( Account account ) {
        this.account = account;
    }

    public int getNumber_months () {
        return number_months;
    }

    public void setNumber_months ( int number_months ) {
        this.number_months = number_months;
    }

    public double getBalance () {
        return balance;
    }

    public void setBalance ( double balance ) {
        this.balance = balance;
    }

    public String getSaving_date () {
        return saving_date;
    }

    public void setSaving_date ( String saving_date ) {
        this.saving_date = saving_date;
    }

    public Status.SavingStatus getSaving_status () {
        return saving_status;
    }

    public void setSaving_status ( Status.SavingStatus saving_status ) {
        this.saving_status = saving_status;
    }
}
