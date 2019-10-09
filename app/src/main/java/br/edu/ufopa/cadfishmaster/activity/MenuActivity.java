package br.edu.ufopa.cadfishmaster.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.config.ConfiguracaoDB;

public class MenuActivity extends AppCompatActivity {

    private FirebaseAuth auth = ConfiguracaoDB.getFirebaseAutenticacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

}
