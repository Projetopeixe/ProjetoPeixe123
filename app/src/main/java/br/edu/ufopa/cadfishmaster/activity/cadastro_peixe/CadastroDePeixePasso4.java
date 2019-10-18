package br.edu.ufopa.cadfishmaster.activity.cadastro_peixe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ufopa.cadfishmaster.R;

public class CadastroDePeixePasso4 extends AppCompatActivity {

    private Button finalizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_peixe_passo4);
        getSupportActionBar().setTitle("Cadastro de Peixe");

        finalizar = (Button) findViewById(R.id.buttonFinalizarCadPeix);

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
