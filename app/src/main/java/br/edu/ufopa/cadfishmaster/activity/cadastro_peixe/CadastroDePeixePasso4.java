package br.edu.ufopa.cadfishmaster.activity.cadastro_peixe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.config.ConfiguracaoDB;

public class CadastroDePeixePasso4 extends AppCompatActivity {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button finalizar;
    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_peixe_passo4);
        getSupportActionBar().setTitle("Cadastro de Peixe");

        finalizar = (Button) findViewById(R.id.buttonFinalizarCadPeix);
        Bundle dados = getIntent().getExtras();
        if(dados != null){
            String especie = dados.getString("especie");
            Double peso = dados.getDouble("peso");
            Double tamanho = dados.getDouble("tamanho");
            String tag = dados.getString("tag");

            String localizacao = dados.getString("localizacao");
            Map<String, Object> docData = new HashMap<>();
            docData.put("especie", especie);
            docData.put("peso", peso);
            docData.put("tamanho", tamanho);
            docData.put("tag", tag);
            docData.put("localizacao", localizacao);

            ConfiguracaoDB configuracaoDB = new ConfiguracaoDB();
            configuracaoDB.saveNote(docData);
        }





        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

}
