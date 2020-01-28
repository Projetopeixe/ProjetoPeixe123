package br.edu.ufopa.cadfishmaster.activity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.helper.BancoDAO;
import br.edu.ufopa.cadfishmaster.helper.DbHelper;
import br.edu.ufopa.cadfishmaster.model.Usuario;

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
                            Usuario usuario = new Usuario(textNome, textEmail, textSenha);
                            ContentValues cv = new ContentValues();
                            cv.put("nome", usuario.getNome());
                            cv.put("email", usuario.getEmail());
                            cv.put("senha", usuario.getSenha());

                            DbHelper db = new DbHelper(this);
                            long result = db.getWritableDatabase().insert(db.TABELA_USUARIOS, null, cv);

                            if(result == -1){
                                Toast.makeText(CadastroUsuarioActivity.this, "Erro ao cadastrar Usuário!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(CadastroUsuarioActivity.this, "Sucesso ao realizar cadastro!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                                startActivity(i);
                                finish();
                            }
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
