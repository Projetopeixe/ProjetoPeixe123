package br.edu.ufopa.cadfishmaster.activity.cadastro_especies;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import br.edu.ufopa.cadfishmaster.R;

public class CadastroDeEspeciesPasso1 extends AppCompatActivity {
    private Button  buttonNextespecie;
    private TextInputEditText especie;

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
                    Intent intent = new Intent(CadastroDeEspeciesPasso1.this, CadastroDeEspeciesPasso2.class);
                    intent.putExtra("especie", especiePeixe);
                    startActivity(intent);
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

