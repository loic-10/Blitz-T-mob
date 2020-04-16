package com.example.blitz_t.Models.Credit;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Status.Status;

public class Credit {

    private String _id;
    private Status.CreditType credit_type;
    private String file_opening_date;
    private String validity_date;
    private int number_months;
    private Account account;
    private double amount_borrowed;
    private double amount_reimbursed;
    private Status.CreditStatus credit_status;

    public Credit () {
    }

    public Credit ( String _id , Status.CreditType credit_type , String file_opening_date , String validity_date , int number_months ,
                    Account account , double amount_borrowed , double amount_reimbursed , Status.CreditStatus credit_status ) {
        this._id = _id;
        this.credit_type = credit_type;
        this.file_opening_date = file_opening_date;
        this.validity_date = validity_date;
        this.number_months = number_months;
        this.account = account;
        this.amount_borrowed = amount_borrowed;
        this.amount_reimbursed = amount_reimbursed;
        this.credit_status = credit_status;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Status.CreditType getCredit_type () {
        return credit_type;
    }

    public void setCredit_type ( Status.CreditType credit_type ) {
        this.credit_type = credit_type;
    }

    public String getFile_opening_date () {
        return file_opening_date;
    }

    public void setFile_opening_date ( String file_opening_date ) {
        this.file_opening_date = file_opening_date;
    }

    public String getValidity_date () {
        return validity_date;
    }

    public void setValidity_date ( String validity_date ) {
        this.validity_date = validity_date;
    }

    public int getNumber_months () {
        return number_months;
    }

    public void setNumber_months ( int number_months ) {
        this.number_months = number_months;
    }

    public Account getAccount () {
        return account;
    }

    public void setAccount ( Account account ) {
        this.account = account;
    }

    public double getAmount_borrowed () {
        return amount_borrowed;
    }

    public void setAmount_borrowed ( double amount_borrowed ) {
        this.amount_borrowed = amount_borrowed;
    }

    public double getAmount_reimbursed () {
        return amount_reimbursed;
    }

    public void setAmount_reimbursed ( double amount_reimbursed ) {
        this.amount_reimbursed = amount_reimbursed;
    }

    public Status.CreditStatus getCredit_status () {
        return credit_status;
    }

    public void setCredit_status ( Status.CreditStatus credit_status ) {
        this.credit_status = credit_status;
    }
}
