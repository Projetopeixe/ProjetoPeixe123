package br.edu.ufopa.cadfishmaster.activity.cadastro_especies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ufopa.cadfishmaster.R;

public class CadastroDeEspeciesPasso1 extends AppCompatActivity {
    private Button  buttonNextespecie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_especies_passo1);
        buttonNextespecie = findViewById(R.id.buttonProximoEspecie);
        buttonNextespecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CadastroDeEspeciesPasso2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

