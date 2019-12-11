package br.edu.ufopa.cadfishmaster.activity.cadastro_especies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ThrowOnExtraProperties;

import java.util.HashMap;

import br.edu.ufopa.cadfishmaster.R;

public class CadastroDeEspeciesPasso2 extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Button buttonCad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_especies_passo2);
        carregarComponentes();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
                .build();
        db.setFirestoreSettings(settings);

        Bundle dados = getIntent().getExtras();
        if (dados != null){
            final String especie = dados.getString("especie");
            buttonCad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    HashMap<String, Object> especieValor = new HashMap<>();
                    especieValor.put("especie", especie);

                    Toast.makeText(getApplicationContext(), "Entrou", Toast.LENGTH_SHORT).show();

                    db.collection("especiespeixes").add(especieValor)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {

                                    Toast.makeText(getApplicationContext(), "Cadastrou", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Não cadastrou", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });

            Toast.makeText(getApplicationContext(), "Passou = " + especie, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Não Passou", Toast.LENGTH_SHORT).show();
        }


    }


    public void carregarComponentes(){
        buttonCad = findViewById(R.id.buttonCadEspecie);
    }
}
