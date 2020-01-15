package br.edu.ufopa.cadfishmaster.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.helper.DbHelper;

public class ActivityCarregamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregamento);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DbHelper db = new DbHelper(getApplicationContext());
                abriTelaInicialLogin();
            }
        }, 3000);

    }


    public void abriTelaInicialLogin(){
        Intent intent = new Intent(getApplicationContext(), LoginUsuarioActivity.class);
        startActivity(intent);
        finish();
    }
}