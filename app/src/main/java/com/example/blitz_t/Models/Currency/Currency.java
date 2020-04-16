package com.example.blitz_t.Models.Currency;

public class Currency {

    private String _id;
    private String currency_name;
    private String currency_symbol;

    public Currency () {
    }

    public Currency ( String _id , String currency_name , String currency_symbol ) {
        this._id = _id;
        this.currency_name = currency_name;
        this.currency_symbol = currency_symbol;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public String getCurrency_name () {
        return currency_name;
    }

    public void setCurrency_name ( String currency_name ) {
        this.currency_name = currency_name;
    }

    public String getCurrency_symbol () {
        return currency_symbol;
    }

    public void setCurrency_symbol ( String currency_symbol ) {
        this.currency_symbol = currency_symbol;
    }

    @Override
    public String toString () {
        return currency_name + " (" + currency_symbol + ")";
    }
}
