package br.edu.ufopa.cadfishmaster.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfiguracaoDB {
    private static DatabaseReference database;
    private static FirebaseAuth auth;

    public static DatabaseReference getFirebaseDatabase(){
        if(database == null){
            database = FirebaseDatabase.getInstance().getReference();

        }
        return database;
    }


    public static FirebaseAuth getFirebaseAutenticacao(){

        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }

        return  auth;
    }



    /*public static FirebaseFirestore getFirebaseFirestore(){
        if (firebaseFirestore == null){
            firebaseFirestore = FirebaseFirestore.getInstance();
        }
        return firebaseFirestore;
    }*/

}
