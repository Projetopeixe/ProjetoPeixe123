package br.edu.ufopa.cadfishmaster.activity.cadastro_peixe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.model.Peixe;

public class CadastroDePeixePasso4 extends AppCompatActivity {

    private Button finalizar;
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_peixe_passo4);
        getSupportActionBar().setTitle("Cadastro de Peixe");

        Bundle dados = getIntent().getExtras();

        if (dados != null){
            String especie = dados.getString("especie");
            Double peso = dados.getDouble("peso");
            Double tamanho = dados.getDouble("tamanho");
            String tag = dados.getString("tag");

            Peixe peixe = new Peixe(especie, peso, tamanho, tag);
            peixe.setNome(especie);
            peixe.setPeso(peso);
            peixe.setTamanho(tamanho);
            peixe.setMarca_tag(tag);

            db.collection("peixes")
                    .add(peixe)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Sucesso ao cadastrar peixe", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Falha ao cadastrar peixe", Toast.LENGTH_SHORT).show();
                        }
                    });

        }

        finalizar = (Button) findViewById(R.id.buttonFinalizarCadPeix);

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
