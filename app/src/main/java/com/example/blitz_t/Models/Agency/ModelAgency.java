package com.example.blitz_t.Models.Agency;

import java.util.ArrayList;

public class ModelAgency {

    public static ArrayList<Agency> allAgencies () {
        ArrayList<Agency> agencies =  new  ArrayList <> ();
        agencies.add(new Agency( 1 , 1 , " Douala " , " 08h " , " 17h " ,
                " L'Agence de Crédit pour l'Entreprise Privée au Cameroun (ACEP Cameroun SA) est une société anonyme au capital 1.440.000.000 F CFA dont le siège social est à Yaoundé au Cameroun. " ,
                " Akwa " , "+237 222219870" , 4.050931 , 9.701771 ));
        agencies.add( new Agency ( 2 , 2 , " Yaoundé " , " 08h30 " , " 17h30 " ,
                " L'Agence de Crédit pour l'Entreprise Privée au Cameroun (ACEP Cameroun SA) est une société anonyme au capital 1.440.000.000 F CFA dont le siège social est à Yaoundé au Cameroun. " ,
                " Akwa " , "+237 222219870" , 4.050931 , 9.701771 ));
        agencies.add( new Agency ( 3 , 1 , " Yaoundé " , " 08h30 " , " 17h30 " ,
                " L'Agence de Crédit pour l'Entreprise Privée au Cameroun (ACEP Cameroun SA) est une société anonyme au capital 1.440.000.000 F CFA dont le siège social est à Yaoundé au Cameroun. " ,
                " Akwa " , "+237 222219870" , 4.050931 , 9.701771 ));

        return agencies;
    }


    public static ArrayList<Agency> searchAgenciesMicrofinance (int id_microfinance) {

        ArrayList<Agency> result =  new  ArrayList <> ();
        for ( Agency a : allAgencies()) {
            if (a.getId_microfinance() == id_microfinance) {
                result.add(a);
            }
        }
        return result ;
    }

    public static ArrayList<Agency> searchAgency (String value) {

        ArrayList<Agency> result =  new  ArrayList <> ();
        for ( Agency a : allAgencies()) {
            if (a.getVille().toLowerCase().contains(value.toLowerCase()) ||
                    a.getQuartier().toLowerCase().contains(value.toLowerCase()) ||
                    a.getDescription().toLowerCase().contains(value.toLowerCase()) ||
                    a.getNumero_tel().toLowerCase().contains(value.toLowerCase())) {
                result.add(a);
            }
        }
        return result ;
    }
}
