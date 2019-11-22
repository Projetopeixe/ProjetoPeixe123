package br.edu.ufopa.cadfishmaster.config;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

import br.edu.ufopa.cadfishmaster.model.Peixe;

public class ConfiguracaoDB {
    private static DatabaseReference database;
    private static FirebaseAuth auth;
    private static FirebaseFirestore db;

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

    public static  FirebaseFirestore getFirebaseFirestore(){
        if(db == null){
            db = FirebaseFirestore.getInstance();
        }
        return db;
    }

    public void saveNote(Map<String, Object> dados){
        db.collection("Peixe").document("Espécie do Peixe").set(dados)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

}
