package br.edu.ufopa.cadfishmaster.activity.cadastro_peixe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
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
        carregarComponentes();

        Bundle dados = getIntent().getExtras();
        if(dados != null){
            String especieRec = dados.getString("especieP3");
            String tagRec = dados.getString("tagP2");
            String localizacaoRec = dados.getString("localizacaoP");
            Double tamanhoRec = dados.getDouble("tamanhoP2");
            Double pesoRec = dados.getDouble("pesoP2");

            Peixe peixe = new Peixe(especieRec, pesoRec, tamanhoRec, tagRec, localizacaoRec);
            db.collection("peixes").add(peixe)
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
        }else {
            Toast.makeText(getApplicationContext(), "Erro ao passar dados entre telas!", Toast.LENGTH_SHORT).show();
        }
    }

    public void carregarComponentes(){
        finalizar = (Button) findViewById(R.id.buttonFinalizarCadPeix);
    }
}
