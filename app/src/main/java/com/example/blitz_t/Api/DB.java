package com.example.blitz_t.Api;

import android.net.Uri;

import com.example.blitz_t.Models.Microfinance.Microfinance;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import androidx.annotation.NonNull;

class DB<T> {

    private T data;

    DB ( T data ) {
        this.data = data;
    }

    private StorageReference getStorageReference (){
        return FirebaseStorage.getInstance().getReference("/" + this.data.getClass().getSimpleName() + "/");
    }

    DatabaseReference getReference (){
        return FirebaseDatabase.getInstance().getReference("/" + this.data.getClass().getSimpleName() + "/");
    }

    void getReferenceObject ( String _id , final ArrayList objects , final Object o ){
        getReference().equalTo(_id, "_id").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange ( @NonNull DataSnapshot dataSnapshot ) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if(data != null){
                        objects.add(data.getValue(o.getClass()));
                        break;
                    }
                }
            }

            @Override
            public void onCancelled ( @NonNull DatabaseError databaseError ) {

            }
        });
    }

    void setObject ( T data , String _id ){
        getReference().child(_id).setValue(data);
    }

    void removeObject ( String _id ){
        getReference().child(_id).removeValue();
    }

    UploadTask uploadImage ( Uri imageUri, String child ){
        if (imageUri == null) {
            return null;
        }
        String fileName = UUID.randomUUID().toString();
        StorageReference storageReference = getStorageReference();
        if( !child.isEmpty() ){
            return storageReference.child(child).child(fileName).putFile(imageUri);
        }
        return storageReference.child(fileName).putFile(imageUri);
    }

    void deleteImage ( String fullUrl ){
        FirebaseStorage.getInstance().getReferenceFromUrl(fullUrl).delete();
    }
}
