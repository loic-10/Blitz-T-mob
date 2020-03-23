package com.example.blitz_t.Models.Customer;

import java.util.ArrayList;
import java.util.Date;

    public class ModelCustomer {private static ArrayList<Customer> allCustomer(){

        ArrayList<Customer> customers = new ArrayList<>();

        customers.add(new Customer(1, 1, "u1",  new Date(1 / 2010), "commercant", "actif", "aa11"));
        customers.add(new Customer(2, 10, "u2",  new Date(1 / 2010), "vebdeur", "actif", "aa11"));
        customers.add(new Customer(3, 9, "u3",  new Date(1 / 2010), "taximan", "actif", "aa11"));
        customers.add(new Customer(4, 10, "u4",  new Date(1 / 2010), "directeur", "actif", "aa11"));
        customers.add(new Customer(5, 5, "u5",  new Date(1 / 2010), "proviseur", "actif", "aa11"));
        customers.add(new Customer(6, 6, "u6",  new Date(1 / 2010), "enseignant", "actif", "aa11"));
        customers.add(new Customer(7, 7, "u7",  new Date(1 / 2010), "senateur", "actif", "aa11"));
        customers.add(new Customer(8, 5, "u8",  new Date(1 / 2010), "livreur", "actif", "aa11"));
        customers.add(new Customer(9, 2, "u9",  new Date(1 / 2010), "journaliste", "actif", "aa11"));
        customers.add(new Customer(10, 1, "u10",  new Date(1 / 2010), "policier", "actif", "aa11"));

        return customers;
    }

    public ArrayList<Customer> searchCustomerFromMicrofinance(int id_microfinance){
        ArrayList<Customer> result = new ArrayList<>();
        for(Customer c : allCustomer()){
            if (c.getId_microfinance() == id_microfinance)
                result.add(c);
        }
        return result;
    }
}
