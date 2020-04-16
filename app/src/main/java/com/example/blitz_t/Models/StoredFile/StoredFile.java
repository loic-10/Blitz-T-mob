package com.example.blitz_t.Models.StoredFile;

import com.example.blitz_t.Models.Guarantee.Guarantee;
import com.example.blitz_t.Models.Status.Status;

public class StoredFile {
    
    private String _id;
    private String name;
    private String link_file;
    private Guarantee guarantee;
    private double valuation_amount;
    private String register_date;
    private Status.StorageStatus storage_status;

    public StoredFile () {
    }

    public StoredFile ( String _id , String name , String link_file , Guarantee guarantee , double valuation_amount ,
                        String register_date , Status.StorageStatus storage_status ) {
        this._id = _id;
        this.name = name;
        this.link_file = link_file;
        this.guarantee = guarantee;
        this.valuation_amount = valuation_amount;
        this.register_date = register_date;
        this.storage_status = storage_status;
    }

    public String get_id () {
        return _id;
    }

    public void set_id ( String _id ) {
        this._id = _id;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getLink_file () {
        return link_file;
    }

    public void setLink_file ( String link_file ) {
        this.link_file = link_file;
    }

    public Guarantee getGuarantee () {
        return guarantee;
    }

    public void setGuarantee ( Guarantee guarantee ) {
        this.guarantee = guarantee;
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

    public Status.StorageStatus getStorage_status () {
        return storage_status;
    }

    public void setStorage_status ( Status.StorageStatus storage_status ) {
        this.storage_status = storage_status;
    }
}
