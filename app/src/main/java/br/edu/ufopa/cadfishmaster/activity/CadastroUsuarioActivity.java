package br.edu.ufopa.cadfishmaster.activity;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;

import javax.annotation.Nullable;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.helper.BancoDAO;
import br.edu.ufopa.cadfishmaster.helper.DbHelper;
import br.edu.ufopa.cadfishmaster.model.Usuario;
import de.hdodenhof.circleimageview.CircleImageView;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private TextInputEditText campoNome, campoEmail, campoSenha, campoConfirmacaoSenha;
    private ImageView imagemCamera, imagemArmazenamento;
    private static final int SELECAO_CAMERA = 100;
    private static final int SELECAO_GALERIA = 200;
    private  String[] permissoesNecessarias = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private CircleImageView fotoUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        setTitle("Cadastro de usuário");
        carregarComponentesCadastro();

        imagemCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(i, SELECAO_CAMERA);
                }
            }
        });

        imagemArmazenamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(i, SELECAO_GALERIA);
                }
            }
        });
    }

    public void carregarComponentesCadastro(){
        campoNome = findViewById(R.id.editTextNomeCadastro);
        campoEmail = findViewById(R.id.editTextEmailCadastro);
        campoSenha = findViewById(R.id.editTextSenhaCadastro);
        campoConfirmacaoSenha = findViewById(R.id.editTextConfirmacaoCadastro);
        imagemCamera = findViewById(R.id.imagemFotoUserCamera);
        imagemArmazenamento = findViewById(R.id.imagemFotoUserGaleria);
        fotoUser = findViewById(R.id.fotoUser);
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
                            byte[] imagem = convertToByte(fotoUser);
                            Usuario usuario = new Usuario(textNome, textEmail, textSenha, imagem);
                            try{
                                DbHelper db  = new DbHelper(this);
                                db.inserirDataUsuario(usuario);
                                sucessAoCadastrar();
                            }catch (Exception e){
                                Toast.makeText(getApplicationContext(), "Erro ao cadastraar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Bitmap imagem = null;

            try{
                switch (requestCode){
                    case SELECAO_CAMERA:
                        imagem = (Bitmap)data.getExtras().get("data");
                        break;
                    case SELECAO_GALERIA:
                        Uri localImagemSelecionada = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada);
                        break;
                }

                if(imagem != null){
                    fotoUser.setImageBitmap(imagem);

                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public byte[] convertToByte(CircleImageView imagem) {
        BitmapDrawable drawable = (BitmapDrawable) imagem.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imagemBytes[] = stream.toByteArray();
        return imagemBytes;
    }

    public void sucessAoCadastrar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sucesso ao realizar cadastro");
        builder.setCancelable(false);
        builder.setMessage("Usuário cadastrado com sucesso!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.create();
        builder.show();
    }
}
