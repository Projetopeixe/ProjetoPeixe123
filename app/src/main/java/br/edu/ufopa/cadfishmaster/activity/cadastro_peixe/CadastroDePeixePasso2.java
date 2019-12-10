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
        carregarComponentes();

        final Bundle dados = getIntent().getExtras();
        if(dados != null){

           buttonNext.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Double pesoRec = Double.parseDouble(campoPeso.getText().toString());
                   Double tamanhoRec = Double.parseDouble(campoTamanho.getText().toString());
                   String tagRec = campoTag.getText().toString();
                   String especie = dados.getString("especieP").toString();

                   Intent intent = new Intent(CadastroDePeixePasso2.this, CadastroDePeixePasso3.class);
                   intent.putExtra("especieP2", especie);
                   intent.putExtra("pesoP", pesoRec);
                   intent.putExtra("tamanhoP", tamanhoRec);
                   intent.putExtra("tagP", tagRec);
                   startActivity(intent);
                   finish();

               }
           });
        }else{
           Toast.makeText(getApplicationContext(), "Erro ao passar dados", Toast.LENGTH_SHORT).show();
        }

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroDePeixePasso1.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void carregarComponentes(){
        buttonNext = (Button) findViewById(R.id.buttonNextPasso2);
        buttonBack = (Button) findViewById(R.id.buttonBackPasso2);
        campoPeso = findViewById(R.id.campoPesoCadastroPeixe);
        campoTamanho = findViewById(R.id.campoTamCadastroPeixe);
        campoTag = findViewById(R.id.campoTagCadastroPeixe);
    }

}
