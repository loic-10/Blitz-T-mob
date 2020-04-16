package com.example.blitz_t.Models.Member;

import com.example.blitz_t.Models.City.City;
import com.example.blitz_t.Models.Country.Country;
import com.example.blitz_t.Models.Sex.Sex;

import java.util.Objects;

public class Member{
    private String _id;
    private String full_name;
    private String birth_date;
    private String cni_number;
    private String cni_copy;
    private Sex sex;
    private String phone_number;
    private Country country;
    private City city;
    private String address;
    private String profile_picture;
    private String registration_date;
    private String password;

    public Member () {
    }

    public Member ( String _id , String full_name , String birth_date , String cni_number , String cni_copy , Sex sex , String phone_number ,
                    Country country , City city ,  String address , String profile_picture , String registration_date , String password ) {
        this._id = _id;
        this.full_name = full_name;
        this.birth_date = birth_date;
        this.cni_number = cni_number;
        this.cni_copy = cni_copy;
        this.sex = sex;
        this.phone_number = phone_number;
        this.city = city;
        this.country = country;
        this.address = address;
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

    public String getFull_name () {
        return full_name;
    }

    public void setFull_name ( String full_name ) {
        this.full_name = full_name;
    }

    public String getBirth_date () {
        return birth_date;
    }

    public void setBirth_date ( String birth_date ) {
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

    public String getPhone_number () {
        return phone_number;
    }

    public void setPhone_number ( String phone_number ) {
        this.phone_number = phone_number;
    }

    public Country getCountry () {
        return country;
    }

    public void setCountry ( Country country ) {
        this.country = country;
    }

    public City getCity () {
        return city;
    }

    public void setCity ( City city ) {
        this.city = city;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress ( String address ) {
        this.address = address;
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
        if ( !(o instanceof Member) ) return false;
        Member member = (Member) o;
        return getPhone_number().equals(member.getPhone_number());
    }

    @Override
    public int hashCode () {
        return Objects.hash(getPhone_number());
    }

    @Override
    public String toString () {
        return full_name;
    }
}
