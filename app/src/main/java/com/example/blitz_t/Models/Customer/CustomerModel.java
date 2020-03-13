package com.example.blitz_t.Models.Customer;

import com.example.blitz_t.Models.Microfinance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerModel {

    private static ArrayList<Customer> allCustumer(){

        ArrayList<Customer> customers = new ArrayList<>();

        customers.add(new Customer(1, 1, "c1", "u1",  new Date(10/10/2010), "commercant", "actif", "aa11"));
        customers.add(new Customer(2, 10, "c2", "u2",  new Date(10/10/2010), "vebdeur", "actif", "aa11"));
        customers.add(new Customer(3, 9, "c3", "u3",  new Date(10/10/2010), "taximan", "actif", "aa11"));
        customers.add(new Customer(4, 10, "c4", "u4",  new Date(10/10/2010), "directeur", "actif", "aa11"));
        customers.add(new Customer(5, 5, "c5", "u5",  new Date(10/10/2010), "proviseur", "actif", "aa11"));
        customers.add(new Customer(6, 6, "c6", "u6",  new Date(10/10/2010), "enseignant", "actif", "aa11"));
        customers.add(new Customer(7, 7, "c7", "u7",  new Date(10/10/2010), "senateur", "actif", "aa11"));
        customers.add(new Customer(8, 5, "c8", "u8",  new Date(10/10/2010), "livreur", "actif", "aa11"));
        customers.add(new Customer(9, 2, "c9", "u9",  new Date(10/10/2010), "journaliste", "actif", "aa11"));
        customers.add(new Customer(10, 1, "c10", "u10",  new Date(10/10/2010), "policier", "actif", "aa11"));

        return customers;
    }

    public ArrayList<Customer>searchMicrofinance(int idMicrofinance){
        ArrayList<Customer> result = new ArrayList<>();
        for(Customer c : allCustumer()){
            if (c.getId_microfinance() == idMicrofinance)
                result.add(c);
        }
        return result;
    }
}
