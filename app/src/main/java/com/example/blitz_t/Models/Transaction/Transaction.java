package com.example.blitz_t.Models.Transaction;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Employee.Employee;
import com.example.blitz_t.Models.Status.Status;

public class Transaction {

    private String _id;
    private Status.TransactionType transaction_type;
    private String transaction_date;
    private Employee sending_employee;
    private Employee validator_employee;
    private Account sending_account;
    private Account recipient_account;
    private double amount;
    private Status.TransactionStatus transaction_status;
    private int number_day_waiting;
    private String recipient_name;
    private String recipient_phone_number;
    private String recipient_cni_number;
    private String key_code;

    public Transaction () {
    }

    public Transaction ( String _id , Status.TransactionType transaction_type , String transaction_date , Employee sending_employee ,
                         Employee validator_employee , Account sending_account , Account recipient_account , double amount ,
                         Status.TransactionStatus transaction_status , int number_day_waiting , String recipient_name ,
                         String recipient_phone_number , String recipient_cni_number , String key_code ) {
        this._id = _id;
        this.transaction_type = transaction_type;
        this.transaction_date = transaction_date;
        this.sending_employee = sending_employee;
        this.validator_employee = validator_employee;
        this.sending_account = sending_account;
        this.recipient_account = recipient_account;
        this.amount = amount;
        this.transaction_status = transaction_status;
        this.number_day_waiting = number_day_waiting;
        this.recipient_name = recipient_name;
        this.recipient_phone_number = recipient_phone_number;
        this.recipient_cni_number = recipient_cni_number;
        this.key_code = key_code;
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

    public String getRecipient_name () {
        return recipient_name;
    }

    public void setRecipient_name ( String recipient_name ) {
        this.recipient_name = recipient_name;
    }

    public String getRecipient_phone_number () {
        return recipient_phone_number;
    }

    public void setRecipient_phone_number ( String recipient_phone_number ) {
        this.recipient_phone_number = recipient_phone_number;
    }

    public String getRecipient_cni_number () {
        return recipient_cni_number;
    }

    public void setRecipient_cni_number ( String recipient_cni_number ) {
        this.recipient_cni_number = recipient_cni_number;
    }

    public String getKey_code () {
        return key_code;
    }

    public void setKey_code ( String key_code ) {
        this.key_code = key_code;
    }
}
