package br.edu.ufopa.cadfishmaster.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.config.ConfiguracaoDB;

public class MenuActivity extends AppCompatActivity {

    private FirebaseAuth auth =FirebaseAuth.getInstance();
    private ViewPager screenPager;
    ScreenViewAdapter screenViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Home");
        getSupportActionBar().setElevation(0);

        List<ScreenItem> list = new ArrayList<>();
        list.add(new ScreenItem("Cad Fish", "Cadastre peixes para pesca esportiva", R.drawable.logo));
        list.add(new ScreenItem("Cad Fish", "Cadastre uma espécie nova se você for um administrador!", R.drawable.peixe));

        screenPager = findViewById(R.id.screenView);
        screenViewAdapter = new ScreenViewAdapter(this, list);
        screenPager.setAdapter(screenViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuDeslogar:
                deslogarUsuario();

                Intent intent = new Intent(getApplicationContext(), LoginUsuarioActivity.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void deslogarUsuario(){
        auth.signOut();
    }

}
