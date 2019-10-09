package br.edu.ufopa.cadfishmaster.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import br.edu.ufopa.cadfishmaster.model.Usuario;

public class LoginUsuarioActivity extends AppCompatActivity {

    private TextView textoCad;
    private FirebaseAuth autenticacao;
    private Button buttonLogar;
    private TextInputEditText campoEmail, campoSenha;

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
        campoEmail = findViewById(R.id.editTextEmailLogin);
        campoSenha = findViewById(R.id.editTextSenhaLogin);
        autenticacao = ConfiguracaoDB.getFirebaseAutenticacao();
    }

    public void abrirTelaCadastro(){
        Intent intent = new Intent(LoginUsuarioActivity.this, CadastroUsuarioActivity.class);
        startActivity(intent);

    }

    public void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginUsuarioActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();
        if(usuarioAtual != null){
            abrirTelaPrincipal();
            fecharLogin();
        }
    }

    public void fecharLogin(){
        finish();
    }

    public void logarUsuario(final Usuario usuario){

        autenticacao.signInWithEmailAndPassword(usuario.getEmail(),
                usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    abrirTelaPrincipal();
                    finish();
                }else {
                    String excecao = "";

                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e){
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

    public void validarAutenticacao(View view){

        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(!email.isEmpty()){
            if(!senha.isEmpty()){

                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setSenha(senha);
                logarUsuario(usuario);
                //finish();


            }else{
                Toast.makeText(LoginUsuarioActivity.this, "Preencha o campo de Senha", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(LoginUsuarioActivity.this, "Preencha o campo de E-mail", Toast.LENGTH_SHORT).show();
        }
    }

}
