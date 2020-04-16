package com.example.blitz_t.Models.Language;

public class Language {

    private String _id;
    private String language_code;
    private String language_name;

    public Language () {
    }

    public Language ( String _id , String language_code , String language_name ) {
        this._id = _id;
        this.language_code = language_code;
        this.language_name = language_name;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public String getLanguage_code () {
        return language_code;
    }

    public void setLanguage_code ( String language_code ) {
        this.language_code = language_code;
    }

    public String getLanguage_name () {
        return language_name;
    }

    public void setLanguage_name ( String language_name ) {
        this.language_name = language_name;
    }
}
