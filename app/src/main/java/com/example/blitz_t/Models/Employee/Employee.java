package com.example.blitz_t.Models.Employee;

import com.example.blitz_t.Models.Agency.Agency;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Status.Status;

public class Employee {

    private String _id;
    private Member member;
    private Agency agency;
    private String password;
    private Status.EmployeeRole employee_role;
    private String hire_date;

    public Employee () {
    }

    public Employee ( String _id , Member member , Agency agency , String password , Status.EmployeeRole employee_role , String hire_date ) {
        this._id = _id;
        this.member = member;
        this.agency = agency;
        this.password = password;
        this.employee_role = employee_role;
        this.hire_date = hire_date;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
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

    public String getPassword () {
        return password;
    }

    public void setPassword ( String password ) {
        this.password = password;
    }

    public Status.EmployeeRole getEmployee_role () {
        return employee_role;
    }

    public void setEmployee_role ( Status.EmployeeRole employee_role ) {
        this.employee_role = employee_role;
    }

    public String getHire_date () {
        return hire_date;
    }

    public void setHire_date ( String hire_date ) {
        this.hire_date = hire_date;
    }
}
