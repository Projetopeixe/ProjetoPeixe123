package br.edu.ufopa.cadfishmaster.activity.cadastro_especies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import br.edu.ufopa.cadfishmaster.R;

public class CadastroDeEspeciesPasso1 extends AppCompatActivity {

    private Button  buttonNextespecie;
    private TextInputEditText especie;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_especies_passo1);
        carregarComponentes();

        buttonNextespecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String especiePeixe = especie.getText().toString();
                if (especiePeixe.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Preenha o campo de espécie!", Toast.LENGTH_SHORT).show();
                }else {
                    HashMap<Object, String> especie = new HashMap<>();
                    especie.put("especie", especiePeixe);

                    db.collection("especiespeixes").add(especie)
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "Ocorreu um erro", Toast.LENGTH_SHORT).show();
                                }
                            });
                    finish();
                }
            }
        });
    }

    public void carregarComponentes(){
        buttonNextespecie = findViewById(R.id.buttonProximoEspecie);
        especie = findViewById(R.id.especiePeixe);
    }

}