package br.edu.ufopa.cadfishmaster.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.config.ConfiguracaoDB;
import br.edu.ufopa.cadfishmaster.config.Permissoes;
import br.edu.ufopa.cadfishmaster.model.Usuario;

public class LoginUsuarioActivity extends AppCompatActivity {

    private TextInputEditText campoEmail, campoSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario);
        carregarComponentes();
        //Validar Permissões

    }

    public void abrirTelaCadastro(View view){
        Intent intent = new Intent(getApplicationContext(), CadastroUsuarioActivity.class);
        startActivity(intent);
    }

    public void carregarComponentes(){
        campoEmail = findViewById(R.id.editTextLogarEmail);
        campoSenha = findViewById(R.id.editTextLogarSenha);
    }

    public void validarAutenticacao(View view){
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(!email.isEmpty()){
            if(!senha.isEmpty()){
                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setSenha(senha);
                logarUsuario(usuario);
            }else{
                Toast.makeText(LoginUsuarioActivity.this, "Preencha o campo SENHA", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(LoginUsuarioActivity.this, "Preencha o campo EMAIL", Toast.LENGTH_SHORT).show();
        }
    }

    public void logarUsuario(Usuario usuario){

<<<<<<< HEAD
        try{
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);
            String pesquisa = "SELECT email, senha FROM usuarios WHERE email = " + campoEmail.getText();
            Cursor cursor = bancoDados.rawQuery(pesquisa, null);

            int indiceEmail = cursor.getColumnIndex("email");
            int indiceSenha = cursor.getColumnIndex("senha");

            cursor.moveToFirst();
            while (cursor != null){

                String email = cursor.getString(indiceEmail);
                String senha = cursor.getString(indiceSenha);

                Log.i("RESULTADO - email: ", email + " senha: " + senha);

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();

        }
        
    }

    @Override
    protected void onStart() {
        super.onStart();
        if( usuarioAtual != null){
            abrirTelaPrincipal();
            fecharLogin();
        }
    }

    public void fecharLogin(){
        finish();
    }

    public void abrirTelaPrincipal(){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }


}
