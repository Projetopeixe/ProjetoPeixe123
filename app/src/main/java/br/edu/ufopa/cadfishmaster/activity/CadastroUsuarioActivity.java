package br.edu.ufopa.cadfishmaster.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.edu.ufopa.cadfishmaster.R;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private TextInputEditText campoNome, campoEmail, campoSenha, campoConfirmacaoSenha;
    private Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        setTitle("Cadastro de usuário");

        carregarComponentes();

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarUsuario();
            }
        });
    }

    public void carregarComponentes(){
        campoNome = (TextInputEditText) findViewById(R.id.editTextNomeCadastroUsuario);
        campoEmail = (TextInputEditText) findViewById(R.id.editTextEmailCadastroUsuario);
        campoSenha = (TextInputEditText) findViewById(R.id.editTextSenhaCadastroUsuario);
        campoConfirmacaoSenha = (TextInputEditText) findViewById(R.id.editTextSenhaConfirmacaoCadastroUsuario);
        botaoCadastrar = (Button) findViewById(R.id.btCadastrarUsuario);
    }

    public void cadastrarUsuario(){
        try {
            String nomeUser = campoNome.getText().toString();
            String emailUser = campoEmail.getText().toString();
            String senhaUser = campoSenha.getText().toString();
            String senhaConfirmUser = campoConfirmacaoSenha.getText().toString();

            if (!nomeUser.isEmpty()) {
                if (!emailUser.isEmpty()) {
                    if (!senhaUser.isEmpty()) {
                        if (!senhaConfirmUser.isEmpty()) {
                            if (senhaConfirmUser.equals(senhaUser)) {




                            } else {
                                Toast.makeText(getApplicationContext(), "Senhas não conferem", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Preencha o campo COFIRMAÇÃO DE SENHA", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Preencha o campo SENHA", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Preencha o campo EMAIL", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Preencha o campo NOME", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

}
