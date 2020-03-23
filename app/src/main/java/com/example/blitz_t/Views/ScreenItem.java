package com.example.blitz_t.Views;

public class ScreenItem {

    private String title, Description;
    private int ScreenImg;

    public ScreenItem ( String title , String description , int screenImg ) {
        this.title = title;
        Description = description;
        ScreenImg = screenImg;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle ( String title ) {
        this.title = title;
    }

    public String getDescription () {
        return Description;
    }

    public void setDescription ( String description ) {
        Description = description;
    }

    public int getScreenImg () {
        return ScreenImg;
    }

    public void setScreenImg ( int screenImg ) {
        ScreenImg = screenImg;
    }
}
