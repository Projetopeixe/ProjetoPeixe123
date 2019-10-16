package br.edu.ufopa.cadfishmaster.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import br.edu.ufopa.cadfishmaster.config.Permissoes;
import br.edu.ufopa.cadfishmaster.fragments.CadastrarEspecieMenu;
import br.edu.ufopa.cadfishmaster.fragments.FragmentHomeMenu;
import br.edu.ufopa.cadfishmaster.fragments.FragmentMenuCadastrarPeixe;

public class MenuActivity extends AppCompatActivity {

    private FirebaseAuth auth =FirebaseAuth.getInstance();

    private  String[] permissoesNecessarias = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle("Cad Fish");
        getSupportActionBar().setElevation(0);

        Permissoes.validarPermissoes(permissoesNecessarias, this, 1);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissaoResultado: grantResults){

            if(permissaoResultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas!");
        builder.setMessage("Para realizar cadastro de peixes é necessário aceitar as permissões");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
