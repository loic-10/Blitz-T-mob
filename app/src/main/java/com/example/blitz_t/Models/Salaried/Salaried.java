package com.example.blitz_t.Models.Salaried;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Status.Status;

public class Salaried {

    private String _id;
    private Account employee_account;
    private Account employer_account;
    private double salary;
    private String hire_date;
    private String register_date;
    private Status.SalariedStatus salaried_status;
    private String payment_date_current_month;
    private String next_month_payment_date;
    private int number_days_for_payment;

    public Salaried () {
    }

    public Salaried ( String _id , Account employee_account , Account employer_account , double salary , String hire_date ,
                      String register_date , Status.SalariedStatus salaried_status , String payment_date_current_month ,
                      String next_month_payment_date , int number_days_for_payment ) {
        this._id = _id;
        this.employee_account = employee_account;
        this.employer_account = employer_account;
        this.salary = salary;
        this.hire_date = hire_date;
        this.register_date = register_date;
        this.salaried_status = salaried_status;
        this.payment_date_current_month = payment_date_current_month;
        this.next_month_payment_date = next_month_payment_date;
        this.number_days_for_payment = number_days_for_payment;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Account getEmployee_account () {
        return employee_account;
    }

    public void setEmployee_account ( Account employee_account ) {
        this.employee_account = employee_account;
    }

    public Account getEmployer_account () {
        return employer_account;
    }

    public void setEmployer_account ( Account employer_account ) {
        this.employer_account = employer_account;
    }

    public double getSalary () {
        return salary;
    }

    public void setSalary ( double salary ) {
        this.salary = salary;
    }

    public String getHire_date () {
        return hire_date;
    }

    public void setHire_date ( String hire_date ) {
        this.hire_date = hire_date;
    }

    public String getRegister_date () {
        return register_date;
    }

    public void setRegister_date ( String register_date ) {
        this.register_date = register_date;
    }

    public Status.SalariedStatus getSalaried_status () {
        return salaried_status;
    }

    public void setSalaried_status ( Status.SalariedStatus salaried_status ) {
        this.salaried_status = salaried_status;
    }

    public String getPayment_date_current_month () {
        return payment_date_current_month;
    }

    public void setPayment_date_current_month ( String payment_date_current_month ) {
        this.payment_date_current_month = payment_date_current_month;
    }

    public String getNext_month_payment_date () {
        return next_month_payment_date;
    }

    public void setNext_month_payment_date ( String next_month_payment_date ) {
        this.next_month_payment_date = next_month_payment_date;
    }

    public int getNumber_days_for_payment () {
        return number_days_for_payment;
    }

    public void setNumber_days_for_payment ( int number_days_for_payment ) {
        this.number_days_for_payment = number_days_for_payment;
    }
}
