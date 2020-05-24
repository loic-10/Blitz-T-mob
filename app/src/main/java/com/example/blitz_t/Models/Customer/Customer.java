package com.example.blitz_t.Models.Customer;


import com.example.blitz_t.Models.Agency.Agency;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.example.blitz_t.Models.Status.Status;

import java.util.Date;

public class Customer {

    private String _id;
    private Microfinance microfinance;
    private Member member;
    private Agency agency;
    private String profession;
    private Status.CustomerStatus customer_status;
    private String password;
    public String register_date;

    public Customer () {
    }

    public Customer ( String _id , Microfinance microfinance , Member member , Agency agency , String profession ,
                      Status.CustomerStatus customer_status , String password , String register_date ) {
        this._id = _id;
        this.microfinance = microfinance;
        this.member = member;
        this.agency = agency;
        this.profession = profession;
        this.customer_status = customer_status;
        this.password = password;
        this.register_date = register_date;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Microfinance getMicrofinance () {
        return microfinance;
    }

    public void setMicrofinance ( Microfinance microfinance ) {
        this.microfinance = microfinance;
    }

    public Member getMember () {
        return member;
    }

    public void setMember ( Member member ) {
        this.member = member;
    }

    public Agency getAgency () {
        return agency;
    }

    public void setAgency ( Agency agency ) {
        this.agency = agency;
    }

    public String getProfession () {
        return profession;
    }

    public void setProfession ( String profession ) {
        this.profession = profession;
    }

    public Status.CustomerStatus getCustomer_status () {
        return customer_status;
    }

    public void setCustomer_status ( Status.CustomerStatus customer_status ) {
        this.customer_status = customer_status;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    public String getRegister_date () {
        return register_date;
    }
}
