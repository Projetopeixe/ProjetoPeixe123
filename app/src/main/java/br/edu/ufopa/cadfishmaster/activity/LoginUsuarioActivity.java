package br.edu.ufopa.cadfishmaster.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.config.ConfiguracaoDB;
import br.edu.ufopa.cadfishmaster.config.Permissoes;
import br.edu.ufopa.cadfishmaster.model.Usuario;

public class LoginUsuarioActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao = FirebaseAuth.getInstance();
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
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    abrirTelaPrincipal();
                    finish();
                }else{
                    String excecao = "";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Usuário e senha não correspondem";
                    }catch (FirebaseAuthInvalidUserException e){
                        excecao = "Usuário não cadastrado";
                    }catch (Exception e){
                        excecao = "Erro ao logar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginUsuarioActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();
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
