package br.edu.ufopa.cadfishmaster.activity.cadastro_especies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.annotation.Nullable;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.activity.MenuActivity;
import br.edu.ufopa.cadfishmaster.helper.DbHelper;
import br.edu.ufopa.cadfishmaster.model.EspeciesPeixes;
import de.hdodenhof.circleimageview.CircleImageView;

public class CadastroDeEspeciesPasso1 extends AppCompatActivity {

    private Button  buttonNextespecie;
    private TextInputEditText especie;
    private CircleImageView fotoEspecie;
    private ImageView imagemCamera;
    private ImageView imageStorage;
    private static final int SELECAO_CAMERA = 100;
    private static final int SELECAO_GALERIA = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_especies_passo1);
        carregarComponentes();

        buttonNextespecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDados();
            }
        });

        imagemCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(i, SELECAO_CAMERA);
                }
            }
        });

        imageStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if(i.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(i, SELECAO_GALERIA);
                }
            }
        });
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
                    fotoEspecie.setImageBitmap(imagem);

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

    public void carregarComponentes(){
        buttonNextespecie = findViewById(R.id.buttonProximoEspecie);
        especie = findViewById(R.id.especiePeixe);
        fotoEspecie = (CircleImageView) findViewById(R.id.imageEspecie);
        imagemCamera = (ImageView) findViewById(R.id.imageCamera);
        imageStorage  = (ImageView) findViewById(R.id.imageStorage);
    }
    public void sucessAoCadastrar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sucesso ao realizar cadastro");
        builder.setCancelable(false);
        builder.setMessage("A espécie foi cadastrada com sucesso!");
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

    public void salvarDados(){
        String especiePeixe = especie.getText().toString();
        if (especiePeixe.isEmpty()){
            Toast.makeText(getApplicationContext(), "Preencha o campo de espécie!", Toast.LENGTH_SHORT).show();
        }else{
            byte[] imagem = convertToByte(fotoEspecie);
            EspeciesPeixes newEspecie = new EspeciesPeixes(especiePeixe, imagem);

            try{
                DbHelper db = new DbHelper(this);
                db.inserirDataEspecie(newEspecie.getNome(), newEspecie.getImagem());
                sucessAoCadastrar();
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Erro ao cadastraar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}