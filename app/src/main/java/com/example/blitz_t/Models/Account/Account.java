package com.example.blitz_t.Models.Account;

import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Status.Status;

public class Account {

    private String _id;
    private Customer customer;
    private Status.TypeSavingAccountOwner type_saving_account_owner;
    private Status.AccountType account_type;
    private String company_name;
    private double balance;
    private Status.AccountStatus account_status;
    private String register_date;

    public Account () {
    }

    public Account ( String _id , Customer customer , Status.TypeSavingAccountOwner type_saving_account_owner ,
                     Status.AccountType account_type , String company_name , double balance ,
                     Status.AccountStatus account_status , String register_date ) {
        this._id = _id;
        this.customer = customer;
        this.type_saving_account_owner = type_saving_account_owner;
        this.account_type = account_type;
        this.company_name = company_name;
        this.balance = balance;
        this.account_status = account_status;
        this.register_date = register_date;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Customer getCustomer () {
        return customer;
    }

    public void setCustomer ( Customer customer ) {
        this.customer = customer;
    }

    public Status.TypeSavingAccountOwner getType_saving_account_owner () {
        return type_saving_account_owner;
    }

    public void setType_saving_account_owner ( Status.TypeSavingAccountOwner type_saving_account_owner ) {
        this.type_saving_account_owner = type_saving_account_owner;
    }

    public Status.AccountType getAccount_type () {
        return account_type;
    }

    public void setAccount_type ( Status.AccountType account_type ) {
        this.account_type = account_type;
    }

    public String getCompany_name () {
        return company_name;
    }

    public void setCompany_name ( String company_name ) {
        this.company_name = company_name;
    }

    public double getBalance () {
        return balance;
    }

    public void setBalance ( double balance ) {
        this.balance = balance;
    }

    public Status.AccountStatus getAccount_status () {
        return account_status;
    }

    public void setAccount_status ( Status.AccountStatus account_status ) {
        this.account_status = account_status;
    }

    public String getRegister_date () {
        return register_date;
    }

    public void setRegister_date ( String register_date ) {
        this.register_date = register_date;
    }


}
