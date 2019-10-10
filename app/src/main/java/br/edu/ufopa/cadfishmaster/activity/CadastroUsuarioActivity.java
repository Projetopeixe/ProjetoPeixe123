package br.edu.ufopa.cadfishmaster.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.config.ConfiguracaoDB;
import br.edu.ufopa.cadfishmaster.helper.Base64Custom;
import br.edu.ufopa.cadfishmaster.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private TextInputEditText campoNome, campoEmail, campoSenha, campoConfirmacaoSenha;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        setTitle("Cadastro de usuário");
        carregarComponentesCadastro();
    }

    public void carregarComponentesCadastro(){
        campoNome = findViewById(R.id.editTextNomeCadastro);
        campoEmail = findViewById(R.id.editTextSenhaCadastro);
        campoSenha = findViewById(R.id.editTextSenhaCadastro);
        campoConfirmacaoSenha = findViewById(R.id.editTextConfirmacaoCadastro);
    }

    public void validarCadastro(View view){
        String textNome = campoNome.getText().toString();
        String textEmail = campoEmail.getText().toString();
        String textSenha = campoSenha.getText().toString();
        String textConfirmacao = campoConfirmacaoSenha.getText().toString();

        if(!textNome.isEmpty()){
            if(!textEmail.isEmpty()){
                if(!textSenha.isEmpty()){
                    if(!textConfirmacao.isEmpty()){

                        Usuario usuario = new Usuario();
                        usuario.setNome(textNome);
                        usuario.setEmail(textEmail);
                        usuario.setSenha(textSenha);
                        cadastrarUsuario(usuario);

                    }else{
                        Toast.makeText(CadastroUsuarioActivity.this, "Preencha o campo CONFIRMAÇÃO SENHA", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(CadastroUsuarioActivity.this, "Preencha o campo SENHA", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(CadastroUsuarioActivity.this, "Preencha o campo EMAIL", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(CadastroUsuarioActivity.this, "Preencha o campo NOME", Toast.LENGTH_SHORT).show();
        }

    }

    public void cadastrarUsuario(final Usuario usuario){
        autenticacao = ConfiguracaoDB.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroUsuarioActivity.this, "", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(intent);
                    finish();
                    try{
                        String identificadorUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                        usuario.setId(identificadorUsuario);
                        usuario.salvar();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                    String excecao = "";

                    try {
                        throw task.getException();
                    }catch (FirebaseNetworkException e) {
                        excecao = "Por favor, conecte à Internet";
                    }catch (FirebaseAuthWeakPasswordException e) {
                        excecao = "Insira uma senha mais forte";
                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Por favor, insira um e-mail válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Usuário já existe";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                    }

                    Toast.makeText(CadastroUsuarioActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
