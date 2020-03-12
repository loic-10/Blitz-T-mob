package com.example.blitz_t.Models;

import java.util.Date;
import java.util.Objects;

public class User {
    private String _id;
    private String user_code;
    private String full_name;
    private Date birth_date;
    private String cni_number;
    private String cni_copy;
    private Sex sex;
    private String phone_number_1;
    private String phone_number_2;
    private int country_id;
    private int city_id;
    private String adress;
    private String profile_picture;
    private String registration_date;
    private String password;

    public User () {
    }

    public User ( String _id , String user_code , String full_name , Date birth_date , String cni_number , String cni_copy ,
                  Sex sex , String phone_number_1 , String phone_number_2 , int country_id , int city_id , String adress ,
                  String profile_picture , String registration_date , String password ) {
        this._id = _id;
        this.user_code = user_code;
        this.full_name = full_name;
        this.birth_date = birth_date;
        this.cni_number = cni_number;
        this.cni_copy = cni_copy;
        this.sex = sex;
        this.phone_number_1 = phone_number_1;
        this.phone_number_2 = phone_number_2;
        this.country_id = country_id;
        this.city_id = city_id;
        this.adress = adress;
        this.profile_picture = profile_picture;
        this.registration_date = registration_date;
        this.password = password;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public String getUser_code () {
        return user_code;
    }

    public void setUser_code ( String user_code ) {
        this.user_code = user_code;
    }

    public String getFull_name () {
        return full_name;
    }

    public void setFull_name ( String full_name ) {
        this.full_name = full_name;
    }

    public Date getBirth_date () {
        return birth_date;
    }

    public void setBirth_date ( Date birth_date ) {
        this.birth_date = birth_date;
    }

    public String getCni_number () {
        return cni_number;
    }

    public void setCni_number ( String cni_number ) {
        this.cni_number = cni_number;
    }

    public String getCni_copy () {
        return cni_copy;
    }

    public void setCni_copy ( String cni_copy ) {
        this.cni_copy = cni_copy;
    }

    public Sex getSex () {
        return sex;
    }

    public void setSex ( Sex sex ) {
        this.sex = sex;
    }

    public String getPhone_number_1 () {
        return phone_number_1;
    }

    public void setPhone_number_1 ( String phone_number_1 ) {
        this.phone_number_1 = phone_number_1;
    }

    public String getPhone_number_2 () {
        return phone_number_2;
    }

    public void setPhone_number_2 ( String phone_number_2 ) {
        this.phone_number_2 = phone_number_2;
    }

    public int getCountry_id () {
        return country_id;
    }

    public void setCountry_id ( int country_id ) {
        this.country_id = country_id;
    }

    public int getCity_id () {
        return city_id;
    }

    public void setCity_id ( int city_id ) {
        this.city_id = city_id;
    }

    public String getAdress () {
        return adress;
    }

    public void setAdress ( String adress ) {
        this.adress = adress;
    }

    public String getProfile_picture () {
        return profile_picture;
    }

    public void setProfile_picture ( String profile_picture ) {
        this.profile_picture = profile_picture;
    }

    public String getRegistration_date () {
        return registration_date;
    }

    public void setRegistration_date ( String registration_date ) {
        this.registration_date = registration_date;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( !(o instanceof User) ) return false;
        User user = (User) o;
        return _id.equals(user._id);
    }

    @Override
    public int hashCode () {
        return Objects.hash(_id);
    }

    @Override
    public String toString () {
        return full_name;
    }
}
