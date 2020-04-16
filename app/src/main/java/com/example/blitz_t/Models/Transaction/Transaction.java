package com.example.blitz_t.Models.Transaction;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Employee.Employee;
import com.example.blitz_t.Models.Saving.Saving;
import com.example.blitz_t.Models.Status.Status;

public class Transaction {

    private String _id;
    private Status.TransactionType transaction_type;
    private Saving saving;
    private String transaction_date;
    private Employee sending_employee;
    private Employee validator_employee;
    private Account sending_account;
    private Account recipient_account;
    private double amount;
    private Status.TransactionStatus transaction_status;
    private int number_day_waiting;

    public Transaction () {
    }

    public Transaction ( String _id , Status.TransactionType transaction_type , Saving saving , String transaction_date ,
                         Employee sending_employee , Employee validator_employee , Account sending_account , Account recipient_account ,
                         double amount , Status.TransactionStatus transaction_status , int number_day_waiting ) {
        this._id = _id;
        this.transaction_type = transaction_type;
        this.saving = saving;
        this.transaction_date = transaction_date;
        this.sending_employee = sending_employee;
        this.validator_employee = validator_employee;
        this.sending_account = sending_account;
        this.recipient_account = recipient_account;
        this.amount = amount;
        this.transaction_status = transaction_status;
        this.number_day_waiting = number_day_waiting;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Status.TransactionType getTransaction_type () {
        return transaction_type;
    }

    public void setTransaction_type ( Status.TransactionType transaction_type ) {
        this.transaction_type = transaction_type;
    }

    public Saving getSaving () {
        return saving;
    }

    public void setSaving ( Saving saving ) {
        this.saving = saving;
    }

    public String getTransaction_date () {
        return transaction_date;
    }

    public void setTransaction_date ( String transaction_date ) {
        this.transaction_date = transaction_date;
    }

    public Employee getSending_employee () {
        return sending_employee;
    }

    public void setSending_employee ( Employee sending_employee ) {
        this.sending_employee = sending_employee;
    }

    public Employee getValidator_employee () {
        return validator_employee;
    }

    public void setValidator_employee ( Employee validator_employee ) {
        this.validator_employee = validator_employee;
    }

    public Account getSending_account () {
        return sending_account;
    }

    public void setSending_account ( Account sending_account ) {
        this.sending_account = sending_account;
    }

    public Account getRecipient_account () {
        return recipient_account;
    }

    public void setRecipient_account ( Account recipient_account ) {
        this.recipient_account = recipient_account;
    }

    public double getAmount () {
        return amount;
    }

    public void setAmount ( double amount ) {
        this.amount = amount;
    }

    public Status.TransactionStatus getTransaction_status () {
        return transaction_status;
    }

    public void setTransaction_status ( Status.TransactionStatus transaction_status ) {
        this.transaction_status = transaction_status;
    }

    public int getNumber_day_waiting () {
        return number_day_waiting;
    }

    public void setNumber_day_waiting ( int number_day_waiting ) {
        this.number_day_waiting = number_day_waiting;
    }
}
