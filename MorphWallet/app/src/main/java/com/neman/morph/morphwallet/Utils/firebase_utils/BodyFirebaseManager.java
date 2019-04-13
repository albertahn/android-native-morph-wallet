package com.neman.morph.morphwallet.Utils.firebase_utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;


public class BodyFirebaseManager {

    FirebaseDatabase fDatabase;

    FirebaseStorage storage;
    StorageReference storageReference;
    Context context;
    private int number = 0;

    public BodyFirebaseManager(Context context){

            FirebaseApp.initializeApp(context);
            //fDatabase = FirebaseDatabase.getInstance();

        //myRef.setValue("Hello, World!");

    }


    public void updateTransaction(){
        fDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = fDatabase.getReference("/transaction/");
        number++;
        myRef.setValue("madeit");
    }

    public void uploadImage(Uri filePath, final Context context) {

        if(filePath != null)
        {

            Log.d("filePath: ", ""+filePath);
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
            final ProgressDialog progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(context, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(context, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }


}
