package br.edu.ufopa.cadfishmaster.activity.cadastro_peixe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.activity.cadastro_especies.CadastroDeEspeciesPasso1;

public class CadastroDePeixesPasso1 extends AppCompatActivity {

    private Button buttonNext;
    private TextInputEditText campoEspecie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_peixes_passo1);
        getSupportActionBar().setTitle("Cadastro de Peixe");

        buttonNext = (Button) findViewById(R.id.buttonProximoCadPPass1);
        campoEspecie = findViewById(R.id.campoEspecieCadPeixe);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String especie = campoEspecie.getText().toString();

                if (!especie.isEmpty()){

                    Intent intent = new Intent(getApplicationContext(), CadastroDePeixePasso2.class);
                    startActivity(intent);
                    finish();
                }else{

                    Toast.makeText(getApplicationContext(), "Informe a Espécie", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }


}
