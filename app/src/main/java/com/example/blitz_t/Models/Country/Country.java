package com.example.blitz_t.Models.Country;

public class Country {
    private String name;
    private String code_phone;

    public Country () {
    }

    public Country ( String name , String code_phone ) {
        this.name = name;
        this.code_phone = code_phone;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getCode_phone () {
        return code_phone;
    }

    public void setCode_phone ( String code_phone ) {
        this.code_phone = code_phone;
    }

    @Override
    public String toString () {
        return name;
    }
}
