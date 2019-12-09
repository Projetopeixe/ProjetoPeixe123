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
import com.google.firebase.firestore.FirebaseFirestore;

import br.edu.ufopa.cadfishmaster.R;

public class CadastroDeEspeciesPasso2 extends AppCompatActivity {


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button buttonCad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_especies_passo2);
        carregarComponentes();

        Bundle dados = getIntent().getExtras();
        if (dados != null){
            final String especie = dados.getString("especie");
            buttonCad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    inserirDados(especie);
                }
            });

            Toast.makeText(getApplicationContext(), "Passou = " + especie, Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Não Passou", Toast.LENGTH_SHORT).show();
        }


    }

    public void inserirDados(String especie){
        db.collection("especies peixes").document("HHZOwsFXytlghIOlxjHc")
                .set(especie)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Cadastrou", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Não Cadastrou", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void carregarComponentes(){
        buttonCad = findViewById(R.id.buttonCadEspecie);
    }
}
