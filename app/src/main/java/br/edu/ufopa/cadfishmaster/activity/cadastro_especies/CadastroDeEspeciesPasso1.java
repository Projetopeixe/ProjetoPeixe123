package br.edu.ufopa.cadfishmaster.activity.cadastro_especies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.helper.DbHelper;
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

                String especiePeixe = especie.getText().toString();
                if (especiePeixe.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Preenha o campo de espécie!", Toast.LENGTH_SHORT).show();
                }else{
                    DbHelper dbReference = new DbHelper(CadastroDeEspeciesPasso1.this);
                    SQLiteDatabase bd  = openOrCreateDatabase(dbReference.NOME_DB, MODE_PRIVATE, null);


                }
            }
        });
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
                finish();
            }
        });
        builder.create();
        builder.show();
    }
}