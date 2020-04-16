package com.example.blitz_t.Models.Microfinance;

import java.util.ArrayList;

public class ModelMicrofinance {

    public static ArrayList<Microfinance> allMicrofinances(){
        ArrayList<Microfinance> microfinances = new ArrayList<>();
        return microfinances;
    }

    public static ArrayList<Microfinance> searchMicrofinance( String value){

        ArrayList<Microfinance> result = new ArrayList<>();
        for (Microfinance m : allMicrofinances()) {
        }
        return result;
    }

}
