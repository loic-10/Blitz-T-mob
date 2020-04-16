package com.example.blitz_t.Models.Operation;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Customer.Customer;
import com.example.blitz_t.Models.Employee.Employee;
import com.example.blitz_t.Models.Status.Status;

public class Operation {

    private String _id;
    private Status.OperationType operation_type;
    private Employee employee;
    private Customer customer;
    private Account account;
    private double operation_value;
    private String description;
    private String operation_date;

    public Operation () {
    }

    public Operation ( String _id , Status.OperationType operation_type , Employee employee , Customer customer ,
                       Account account , double operation_value , String description , String operation_date ) {
        this._id = _id;
        this.operation_type = operation_type;
        this.employee = employee;
        this.customer = customer;
        this.account = account;
        this.operation_value = operation_value;
        this.description = description;
        this.operation_date = operation_date;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Status.OperationType getOperation_type () {
        return operation_type;
    }

    public void setOperation_type ( Status.OperationType operation_type ) {
        this.operation_type = operation_type;
    }

    public Employee getEmployee () {
        return employee;
    }

    public void setEmployee ( Employee employee ) {
        this.employee = employee;
    }

    public Customer getCustomer () {
        return customer;
    }

    public void setCustomer ( Customer customer ) {
        this.customer = customer;
    }

    public Account getAccount () {
        return account;
    }

    public void setAccount ( Account account ) {
        this.account = account;
    }

    public double getOperation_value () {
        return operation_value;
    }

    public void setOperation_value ( double operation_value ) {
        this.operation_value = operation_value;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }

    public String getOperation_date () {
        return operation_date;
    }

    public void setOperation_date ( String operation_date ) {
        this.operation_date = operation_date;
    }
}
