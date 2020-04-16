package com.example.blitz_t.Models.Guarantee;

import com.example.blitz_t.Models.Credit.Credit;
import com.example.blitz_t.Models.Status.Status;

public class Guarantee {

    private String _id;
    private Credit credit;
    private String description;
    private double valuation_amount;
    private String register_date;
    private Status.GuaranteeStatus guarantee_status;

    public Guarantee () {
    }

    public Guarantee ( String _id , Credit credit , String description , double valuation_amount , String register_date ,
                       Status.GuaranteeStatus guarantee_status ) {
        this._id = _id;
        this.credit = credit;
        this.description = description;
        this.valuation_amount = valuation_amount;
        this.register_date = register_date;
        this.guarantee_status = guarantee_status;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Credit getCredit () {
        return credit;
    }

    public void setCredit ( Credit credit ) {
        this.credit = credit;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription ( String description ) {
        this.description = description;
    }

    public double getValuation_amount () {
        return valuation_amount;
    }

    public void setValuation_amount ( double valuation_amount ) {
        this.valuation_amount = valuation_amount;
    }

    public String getRegister_date () {
        return register_date;
    }

    public void setRegister_date ( String register_date ) {
        this.register_date = register_date;
    }

    public Status.GuaranteeStatus getGuarantee_status () {
        return guarantee_status;
    }

    public void setGuarantee_status ( Status.GuaranteeStatus guarantee_status ) {
        this.guarantee_status = guarantee_status;
    }
}
