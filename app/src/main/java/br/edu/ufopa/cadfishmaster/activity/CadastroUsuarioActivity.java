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
                Toast.makeText(getApplicationContext(), "Clicado", Toast.LENGTH_SHORT).show();
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

}
