package br.edu.ufopa.cadfishmaster.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import br.edu.ufopa.cadfishmaster.R;

public class LoginUsuarioActivity extends AppCompatActivity {

    private TextView textoCad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);

        carregarComponentes();

        textoCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaCadastro();
            }
        });
    }

    public void carregarComponentes(){
        textoCad = findViewById(R.id.textViewCadastro);
    }

    public void abrirTelaCadastro(){
        Intent intent = new Intent(LoginUsuarioActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);

    }

}
