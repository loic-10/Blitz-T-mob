package com.example.blitz_t.Api;

import com.example.blitz_t.Models.City.City;
import com.example.blitz_t.Models.Country.Country;
import com.example.blitz_t.Models.Member.Member;
import com.example.blitz_t.Models.Sex;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class MemberHelper {
    private static final String COLLECTION_NAME = "members";

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    // --- CREATE ---

    public static Task<Void> createMember(  String _id, String full_name , String birth_date , String cni_number , String cni_copy ,
                                            Sex sex , String phone_number , Country country , City city , String address ,
                                            String profile_picture , Date registration_date , String password ) {

        Member member = new Member( _id, full_name , birth_date , cni_number , cni_copy ,sex , phone_number , country, city , address , profile_picture , registration_date , password  );

        return MemberHelper.getUsersCollection().document(_id).set(member);
    }

    // --- GET ---

    public static Task<DocumentSnapshot> getMember( String uid){
        return MemberHelper.getUsersCollection().document(uid).get();
    }

    // --- UPDATE ---

    public static Task<Void> updateUsername(String username, String uid) {
        return MemberHelper.getUsersCollection().document(uid).update("username", username);
    }

//    public static Task<Void> updateIsMentor(String uid, Boolean isMentor) {
//        return UserHelper.getUsersCollection().document(uid).update("isMentor", isMentor);
//    }

    // --- DELETE ---

    public static Task<Void> deleteUser(String uid) {
        return MemberHelper.getUsersCollection().document(uid).delete();
    }
}
