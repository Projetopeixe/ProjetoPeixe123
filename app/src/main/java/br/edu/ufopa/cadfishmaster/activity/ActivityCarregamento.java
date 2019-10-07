package br.edu.ufopa.cadfishmaster.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import br.edu.ufopa.cadfishmaster.R;

public class ActivityCarregamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregamento);

        abriTelaLogin();

    }

    public void abriTelaLogin(){
        Intent intent = new Intent(getApplicationContext(), LoginUsuarioActivity.class);
        startActivity(intent);
        finish();
    }

}
