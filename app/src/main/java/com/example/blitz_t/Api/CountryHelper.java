package com.example.blitz_t.Api;


import com.example.blitz_t.Models.Country.Country;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CountryHelper {
    private static final String COLLECTION_NAME = "/country/";

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getCountryCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    public static Task<Void> createCountry( String name, String code_phone) {

        Country country = new Country(name, code_phone);

        return getCountryCollection().document(name).set(country);
    }

    // --- GET ---

    public static Task<DocumentSnapshot> getCountries(){
        return getCountryCollection().document().get();
    }

    public static Task<DocumentSnapshot> getCountry( String name){
        return getCountryCollection().document(name).get();
    }

    // --- UPDATE ---

    public static Task<Void> updateCodePhoneCountry(String code_phone, String name) {
        return getCountryCollection().document(name).update("code_phone", code_phone);
    }

//    public static Task<Void> updateIsMentor(String uid, Boolean isMentor) {
//        return UserHelper.getUsersCollection().document(uid).update("isMentor", isMentor);
//    }

    // --- DELETE ---

    public static Task<Void> deleteCountry(String name) {
        return getCountryCollection().document(name).delete();
    }
}
