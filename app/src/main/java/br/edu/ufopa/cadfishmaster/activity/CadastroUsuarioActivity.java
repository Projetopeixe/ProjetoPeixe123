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

        carregarComponentes();

    }

    public void carregarComponentes(){
        campoNome = (TextInputEditText) findViewById(R.id.editTextNomeCadastroUsuario);
        campoEmail = (TextInputEditText) findViewById(R.id.editTextEmailCadastroUsuario);
        campoSenha = (TextInputEditText) findViewById(R.id.editTextSenhaCadastroUsuario);
        campoConfirmacaoSenha = (TextInputEditText) findViewById(R.id.editTextSenhaConfirmacaoCadastroUsuario);

    }

    public void validarCadastro(View view){
        String textNome = campoNome.getText().toString();
        String textEmail = campoEmail.getText().toString();
        String textSenha = campoSenha.getText().toString();
        String textConfirmacao = campoConfirmacaoSenha.getText().toString();

        if(!textNome.isEmpty()){
            if (!textEmail.isEmpty()){
                if(!textSenha.isEmpty()){
                    if (!textConfirmacao.isEmpty()){
                        if (textConfirmacao.equals(textSenha)){
                            Usuario usuario = new Usuario();
                            usuario.setNome(textNome);
                            usuario.setEmail(textEmail);
                            usuario.setSenha(textSenha);
                            cadastrarUsuario(usuario);
                        }else{
                            Toast.makeText(CadastroUsuarioActivity.this, "Senhas não conferem!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(CadastroUsuarioActivity.this, "`Preencha o campo de Confirmação de Senha!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CadastroUsuarioActivity.this, "Preencha o campo Senha!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(CadastroUsuarioActivity.this, "Preencha o campo E-mail!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(CadastroUsuarioActivity.this, "Preencha o campo Nome!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarUsuario(final Usuario usuario){
        autenticacao = ConfiguracaoDB.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroUsuarioActivity.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CadastroUsuarioActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    try{
                        String identificadorUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                        usuario.setId(identificadorUsuario);
                        usuario.salvar();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else{
                    String excecao = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        excecao = "Insira uma senha mais forte";
                    }catch (FirebaseNetworkException e){
                        excecao = "Por favor, conecte-se à internet";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        excecao = "Por favor, insira um e-mail válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        excecao = "Usuário já existe";
                    }catch (Exception e){
                        excecao = "Erro ao cadastrar: " + e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroUsuarioActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
