package com.example.blitz_t.Api;

import android.net.Uri;

import com.example.blitz_t.Models.Member.Member;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class MemberHelper {

    // --- CREATE AND SET ---

    public static void setMember( Member member){
        new DB<Member>(member).setObject(member, member.get_id());
    }

    // --- GET ---

    public static DatabaseReference getMembers(){
        return new DB<Member>(new Member()).getReference();
    }

    // --- UPLOAD ---

    public static UploadTask uploadImage( Uri imageUri, String child ){
        return new DB<Member>(new Member()).uploadImage(imageUri, child);
    }

    public static void deleteImage( String fullUrl ){
        new DB<Member>(new Member()).deleteImage(fullUrl);
    }

    // --- DELETE ---

    public static void deleteMember( String _id ){
        new DB<Member>(new Member()).removeObject(_id);
    }
}
