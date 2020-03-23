package com.example.blitz_t.Models.Microfinance;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.RequiresApi;

public class ModelMicrofinance {

    public static ArrayList<Microfinance> allMicrofinances(){
        ArrayList<Microfinance> microfinances = new ArrayList<>();
        microfinances.add(new Microfinance(1, "ACEP CAMEROUN", "https://lh3.googleusercontent.com/proxy/JUBjuIho4EYJwNTaRGJFUvB4kgr8s0zjcATL7J_7nIvYbZPemygKb0hgrN7k9xegUP1CavdB7oK-MrYDiVR04YSlWWSzS5NjPCKW_sIjUAgZgCTwQILHfMTdCb9h1icZMo_p9PT2LSt-gQ", "La microfinance qui ose...", "XAF"));
        microfinances.add(new Microfinance(2, "Advans Cameroun", "https://s3.amazonaws.com/afrimalin/images/photo/ce/79/78/ce7978d715e40f8e7ff64ff53ad9e395/469012.jpg", "Bienvenue chez Advans Cameroun", "XAF"));
        microfinances.add(new Microfinance(3, "FIGEC", "https://figec-sa.com/images/images/LOGO.jpg",
                "Les produits FIGEC sont conçus spécialement pour répondre aux attentes spécifiques des clients", "XAF"));
        microfinances.add(new Microfinance(4, "FINANCIAL HOUSE S.A", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQCfN7NDf6Jhakf78LvvxE8bD_LZAMkAY4p5GsgkUA0FsgQu2i4", "Nous sommes heureux de vous accueillir sur le site Internet de FINANCIAL HOUSE S.A.", "XAF"));
        return microfinances;
    }

    public static ArrayList<Microfinance> searchMicrofinance( String value){

        ArrayList<Microfinance> result = new ArrayList<>();
        for (Microfinance m : allMicrofinances()) {
            if ( m.getName().toLowerCase().contains(value.toLowerCase()) ) {
                result.add(m);
            }
        }
        return result;
    }

}
