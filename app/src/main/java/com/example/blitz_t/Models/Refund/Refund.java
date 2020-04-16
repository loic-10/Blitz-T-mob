package com.example.blitz_t.Models.Refund;

import com.example.blitz_t.Models.Credit.Credit;
import com.example.blitz_t.Models.Status.Status;

public class Refund {
    
    private String _id;
    private Credit credit;
    private String refund_date;
    private String planned_refund_date;
    private double amount;
    private Status.RefundStatus refund_status;

    public Refund () {
    }

    public Refund ( String _id , Credit credit , String refund_date , String planned_refund_date ,
                    double amount , Status.RefundStatus refund_status ) {
        this._id = _id;
        this.credit = credit;
        this.refund_date = refund_date;
        this.planned_refund_date = planned_refund_date;
        this.amount = amount;
        this.refund_status = refund_status;
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

    public String getRefund_date () {
        return refund_date;
    }

    public void setRefund_date ( String refund_date ) {
        this.refund_date = refund_date;
    }

    public String getPlanned_refund_date () {
        return planned_refund_date;
    }

    public void setPlanned_refund_date ( String planned_refund_date ) {
        this.planned_refund_date = planned_refund_date;
    }

    public double getAmount () {
        return amount;
    }

    public void setAmount ( double amount ) {
        this.amount = amount;
    }

    public Status.RefundStatus getRefund_status () {
        return refund_status;
    }

    public void setRefund_status ( Status.RefundStatus refund_status ) {
        this.refund_status = refund_status;
    }
}
