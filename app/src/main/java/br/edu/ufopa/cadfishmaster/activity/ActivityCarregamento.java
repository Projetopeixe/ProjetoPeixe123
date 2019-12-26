package br.edu.ufopa.cadfishmaster.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import br.edu.ufopa.cadfishmaster.R;

public class ActivityCarregamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carregamento);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    SQLiteDatabase banco = openOrCreateDatabase("cadFishapp", MODE_PRIVATE, null);
                    //Create Table de Usuários
                   //banco.execSQL("CREATE TABLE IF NOT EXISTS usuarios()");


                }catch (Exception e){
                    e.printStackTrace();
                }

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
