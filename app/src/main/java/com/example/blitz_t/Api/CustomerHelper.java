package com.example.blitz_t.Api;

import com.example.blitz_t.Models.Customer.Customer;
import com.google.firebase.database.DatabaseReference;

public class CustomerHelper extends DB<Customer> {

    private Customer mCustomer;

    public CustomerHelper ( Customer data ) {
        super(data);
        mCustomer = data;
    }

    // --- CREATE AND SET ---

    public static void setCustomer( Customer customer){
        new DB<Customer>(customer).setObject(customer, customer.get_id());
    }

    // --- GET ---

    public DatabaseReference getCustomers(){
        return getReference();
    }

    // --- DELETE ---

    public static void deleteCustomer( String _id ){
        new DB<Customer>(new Customer()).removeObject(_id);
    }
}
