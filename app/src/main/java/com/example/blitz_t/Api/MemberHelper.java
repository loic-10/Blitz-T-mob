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

public class MemberHelper extends DB<Member> {

    public MemberHelper ( Member data ) {
        super(data);
    }

    // --- CREATE AND SET ---

    public void setMember( Member member){
        setObject(member, member.get_id());
    }

    // --- GET ---

    public DatabaseReference getMembers(){
        return getReference();
    }

    // --- UPLOAD ---

    public UploadTask uploadImage( Uri imageUri, String child ){
        return super.uploadImage(imageUri, child);
    }

    public void deleteImage( String fullUrl ){
        super.deleteImage(fullUrl);
    }

    // --- DELETE ---

    public void deleteMember( String _id ){
        removeObject(_id);
    }
}
