package br.edu.ufopa.cadfishmaster.activity.cadastro_peixe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ufopa.cadfishmaster.R;

public class CadastroDePeixePasso3 extends AppCompatActivity {

    private Button buttonNext;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_peixe_passo3);
        getSupportActionBar().setTitle("Cadastro de Peixe");

        buttonNext = findViewById(R.id.buttonNextPasso3);
        buttonBack = findViewById(R.id.buttonBackPasso3);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroDePeixePasso4.class);
                startActivity(intent);
                finish();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroDePeixePasso2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
