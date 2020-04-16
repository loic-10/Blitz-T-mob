package com.example.blitz_t.Models.SocialPart;

import com.example.blitz_t.Models.Account.Account;
import com.example.blitz_t.Models.Status.Status;

public class SocialPart {

    private String _id;
    private Account account;
    private Status.SocialPartStatus social_part_status;
    private double amount;

    public SocialPart () {
    }

    public SocialPart ( String _id , Account account , Status.SocialPartStatus social_part_status , double amount ) {
        this._id = _id;
        this.account = account;
        this.social_part_status = social_part_status;
        this.amount = amount;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public Account getAccount () {
        return account;
    }

    public void setAccount ( Account account ) {
        this.account = account;
    }

    public Status.SocialPartStatus getSocial_part_status () {
        return social_part_status;
    }

    public void setSocial_part_status ( Status.SocialPartStatus social_part_status ) {
        this.social_part_status = social_part_status;
    }

    public double getAmount () {
        return amount;
    }

    public void setAmount ( double amount ) {
        this.amount = amount;
    }
}
