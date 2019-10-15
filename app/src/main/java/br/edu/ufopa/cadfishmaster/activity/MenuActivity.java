package br.edu.ufopa.cadfishmaster.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.fragments.CadastrarEspecieMenu;
import br.edu.ufopa.cadfishmaster.fragments.FragmentHomeMenu;
import br.edu.ufopa.cadfishmaster.fragments.FragmentMenuCadastrarPeixe;

public class MenuActivity extends AppCompatActivity {

    private FirebaseAuth auth =FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Cad Fish");
        getSupportActionBar().setElevation(0);



        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("Home", FragmentHomeMenu.class)
                        .add("Cadastrar Peixe", FragmentMenuCadastrarPeixe.class)
                        .add("Cadastrar Espécie", CadastrarEspecieMenu.class)
                        .create()
        );
        ViewPager viewPager = findViewById(R.id.viewpager2);
        viewPager.setAdapter( adapter );

        SmartTabLayout viewPagerTab = findViewById(R.id.smartTabLayout);
        viewPagerTab.setViewPager( viewPager );
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
