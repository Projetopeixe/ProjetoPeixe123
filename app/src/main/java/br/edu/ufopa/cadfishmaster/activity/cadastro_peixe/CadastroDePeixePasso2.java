package br.edu.ufopa.cadfishmaster.activity.cadastro_peixe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.activity.cadastro_especies.CadastroDeEspeciesPasso1;

public class CadastroDePeixePasso2 extends AppCompatActivity {

    private Button buttonNext;
    private Button buttonBack;
    private TextInputEditText campoPeso;
    private TextInputEditText campoTamanho;
    private TextInputEditText campoTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_peixe_passo2);
        getSupportActionBar().setTitle("Cadastro de Peixe");

        buttonNext = (Button) findViewById(R.id.buttonNextPasso2);
        buttonBack = (Button) findViewById(R.id.buttonBackPasso2);
        campoPeso = findViewById(R.id.campoPesoCadastroPeixe);
        campoTamanho = findViewById(R.id.campoTamCadastroPeixe);
        campoTag = findViewById(R.id.campoTagCadastroPeixe);


        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String peso = campoPeso.getText().toString();
                String tamanho = campoTamanho.getText().toString();
                String tag = campoTag.getText().toString();

                if(!peso.isEmpty()){
                    if(!tamanho.isEmpty()){
                        if(!tag.isEmpty()){

                            Bundle dados = getIntent().getExtras();
                            String especie = dados.getString("especie");

                            Intent intent = new Intent(getApplicationContext(), CadastroDePeixePasso3.class);
                            intent.putExtra("especie", especie);
                            intent.putExtra("peso", peso);
                            intent.putExtra("tamanho", tamanho);
                            intent.putExtra("tag", tag);

                            startActivity(intent);

                            finish();

                        }else{
                            Toast.makeText(getApplicationContext(), "Informe a Marca Tag", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "Informe o tamanho", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Informe o peso", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroDePeixePasso1.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
