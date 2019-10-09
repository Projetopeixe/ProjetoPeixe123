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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuDeslogar:

                deslogarUsuario();

                Intent intent = new Intent(MenuActivity.this, LoginUsuarioActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void deslogarUsuario(){
        auth.signOut();
    }

}
