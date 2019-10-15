package br.edu.ufopa.cadfishmaster.activity.cadastro_peixe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import br.edu.ufopa.cadfishmaster.R;

public class CadastroDePeixePasso2 extends AppCompatActivity {

    private Button buttonNext;
    private Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_peixe_passo2);
        getSupportActionBar().setTitle("Cadastro de Peixe");

        buttonNext = (Button) findViewById(R.id.buttonNextPasso2);
        buttonBack = (Button) findViewById(R.id.buttonBackPasso2);
    }
}
