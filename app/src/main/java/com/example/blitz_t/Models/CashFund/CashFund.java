package com.example.blitz_t.Models.CashFund;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Employee.Employee;
import com.example.blitz_t.Models.Status.Status;

public class CashFund {

    private String _id;
    private Employee employee;
    private Account account;
    private String cash_fund_date;
    private Status.CaseActionType case_action_type;
    private double action_amount;
    private double total_amount;
    private String description;

    public CashFund () {
    }

    public CashFund ( String _id , Employee employee , Account account , String cash_fund_date , Status.CaseActionType case_action_type ,
                      double action_amount , double total_amount , String description ) {
        this._id = _id;
        this.employee = employee;
        this.account = account;
        this.cash_fund_date = cash_fund_date;
        this.case_action_type = case_action_type;
        this.action_amount = action_amount;
        this.total_amount = total_amount;
        this.description = description;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Employee getEmployee () {
        return employee;
    }

    public void setEmployee ( Employee employee ) {
        this.employee = employee;
    }

    public Account getAccount () {
        return account;
    }

    public void setAccount ( Account account ) {
        this.account = account;
    }

    public String getCash_fund_date () {
        return cash_fund_date;
    }

    public void setCash_fund_date ( String cash_fund_date ) {
        this.cash_fund_date = cash_fund_date;
    }

    public Status.CaseActionType getCase_action_type () {
        return case_action_type;
    }

    public void setCase_action_type ( Status.CaseActionType case_action_type ) {
        this.case_action_type = case_action_type;
    }

    public double getAction_amount () {
        return action_amount;
    }

    public void setAction_amount ( double action_amount ) {
        this.action_amount = action_amount;
    }

    public double getTotal_amount () {
        return total_amount;
    }

    public void setTotal_amount ( double total_amount ) {
        this.total_amount = total_amount;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }
}
