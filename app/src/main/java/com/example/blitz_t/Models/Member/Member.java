package com.example.blitz_t.Models.Member;

import android.net.Uri;

import com.example.blitz_t.Models.City.City;
import com.example.blitz_t.Models.Country.Country;
import com.example.blitz_t.Models.Model;
import com.example.blitz_t.Models.Sex;

import java.util.Date;
import java.util.Objects;

public class Member extends Model{
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
    private Object uri_picture;
    private Date registration_date;
    private String password;

    public Member () {
    }

    public Member ( String _id , String full_name , String birth_date , String cni_number , String cni_copy , Sex sex , String phone_number ,
                    Country country , City city ,  String address , String profile_picture , Date registration_date , String password ) {
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

    public Member ( String _id , String full_name , String birth_date , String cni_number , String cni_copy , Sex sex , String phone_number ,
                    Country country , City city , String address , Object uri_picture , Date registration_date , String password ) {
        this._id = _id;
        this.full_name = full_name;
        this.birth_date = birth_date;
        this.cni_number = cni_number;
        this.cni_copy = cni_copy;
        this.sex = sex;
        this.phone_number = phone_number;
        this.country = country;
        this.city = city;
        this.address = address;
        this.uri_picture = uri_picture;
        this.registration_date = registration_date;
        this.password = password;
    }

    public Object getUri_picture () {
        return uri_picture;
    }

    public void setUri_picture ( Uri uri_picture ) {
        this.uri_picture = uri_picture;
    }

    public String get_id () {
        return _id;
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

    public City getCity_id () {
        return city;
    }

    public void setCity_id ( City city_id ) {
        this.city = city_id;
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

    public Date getRegistration_date () {
        return registration_date;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public String getPhone_number () {
        return phone_number;
    }

    public void setPhone_number ( String phone_number ) {
        this.phone_number = phone_number;
    }

    public City getCity () {
        return city;
    }

    public void setCity ( City city ) {
        this.city = city;
    }

    public Country getCountry () {
        return country;
    }

    public void setCountry ( Country country ) {
        this.country = country;
    }

    public void setRegistration_date ( Date registration_date ) {
        this.registration_date = registration_date;
    }

    @Override
    public boolean equals ( Object o ) {
        if ( this == o ) return true;
        if ( !(o instanceof Member) ) return false;
        Member member = (Member) o;
        return get_id().equals(member.get_id());
    }

    @Override
    public int hashCode () {
        return Objects.hash(get_id());
    }

    @Override
    public String toString () {
        return full_name;
    }
}
