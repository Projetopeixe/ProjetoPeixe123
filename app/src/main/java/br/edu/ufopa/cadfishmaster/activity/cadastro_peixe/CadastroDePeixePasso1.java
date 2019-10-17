package br.edu.ufopa.cadfishmaster.activity.cadastro_peixe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.ImageDecoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import br.edu.ufopa.cadfishmaster.R;

public class CadastroDePeixePasso1 extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String[] PEIXES = new String[]{
            "Tambaqui", "Pacu", "Pirarucu", "Pirarara", "Tucunaré", "Piranha"
    };

    private Button buttonNext;
    private AutoCompleteTextView campoEspecie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_peixes_passo1);
        getSupportActionBar().setTitle("Cadastro de Peixe");

        final List<String> peixesArray = new ArrayList<>();
        db.collection("cities")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                peixesArray.add(String.valueOf(document.getData()));

                            }
                        } else {

                        }
                    }
                });



        final String[] peixes = getResources().getStringArray(R.array.peixes);
        ImageView imag = findViewById(R.id.btautocomplete);
        final AutoCompleteTextView editText = findViewById(R.id.campoEspecieCadPeixe);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.autocomplete, R.id.text_view_list_item, peixesArray); 
        editText.setAdapter(adapter);

        imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.showDropDown();
            }
        });

        buttonNext = (Button) findViewById(R.id.buttonProximoCadPPass1);
        campoEspecie = findViewById(R.id.campoEspecieCadPeixe);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String especie = campoEspecie.getText().toString();

                if (!especie.isEmpty()){

                    Intent intent = new Intent(getApplicationContext(), CadastroDePeixePasso2.class);
                    intent.putExtra("especie", especie);
                    startActivity(intent);
                    finish();
                }else{

                    Toast.makeText(getApplicationContext(), "Informe a Espécie", Toast.LENGTH_SHORT).show();
                }

            }

        });




    }


}
