package com.example.blitz_t.Models;



import java.util.ArrayList;


public class ModelAgences {
    public ArrayList<Agences> allAgences(){
        ArrayList<Agences> agences = new ArrayList<>();
        agences.add(new Agences(1,1,"Douala","08h","17h","L’Agence de Crédit pour l’Entreprise Privée au Cameroun (ACEP Cameroun SA) est une société anonyme au capital 1.440.000.000 F CFA dont le siège social est à Yaoundé au Cameroun.","Akwa", 222219870,4.050931,9.701771));
        agences.add(new Agences(2,2,"Yaoundé","08h30","17h30","L’Agence de Crédit pour l’Entreprise Privée au Cameroun (ACEP Cameroun SA) est une société anonyme au capital 1.440.000.000 F CFA dont le siège social est à Yaoundé au Cameroun.","Akwa", 222219870,4.050931,9.701771));
        return agences;
    }


    public ArrayList<Agences> searchAgences( int value){

        ArrayList<Agences> result = new ArrayList<>();
        for (Agences s : allAgences()) {
            if ( s.getId_microfinance() == value  ) {
                result.add(s);
            }
        }
        return result;
    }

}
