package br.edu.ufopa.cadfishmaster.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.helper.BancoController;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private TextInputEditText campoNome, campoEmail, campoSenha, campoConfirmacaoSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        setTitle("Cadastro de usuário");
        carregarComponentesCadastro();

    }

    public void carregarComponentesCadastro(){
        campoNome = findViewById(R.id.editTextNomeCadastro);
        campoEmail = findViewById(R.id.editTextEmailCadastro);
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
                        if(textSenha.equals(textConfirmacao)){
                            BancoController bd = new BancoController(this);

                            String resultado = bd.insereUsuario(textNome, textEmail, textSenha);

                            Toast.makeText(CadastroUsuarioActivity.this, resultado, Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(CadastroUsuarioActivity.this, "Senhas não conferem",Toast.LENGTH_SHORT).show();
                        }
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


}
