package br.edu.ufopa.cadfishmaster.activity.cadastro_peixe;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import br.edu.ufopa.cadfishmaster.MapsActivity;
import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.helper.DbHelper;
import br.edu.ufopa.cadfishmaster.model.Peixe;
import de.hdodenhof.circleimageview.CircleImageView;

public class CadastroDePeixePasso3 extends AppCompatActivity {

    private Button buttonNext;
    private Button buttonBack;
    private ImageView pesquisarLocation;
    private CircleImageView fotoPeixe;
    private static final int SELECAO_CAMERA = 100;
    private static final int SELECAO_GALERIA = 200;
    private TextInputEditText localizacao;
    private LocationManager locationManager;
    private LocationListener locationListener;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_peixe_passo3);
        getSupportActionBar().setTitle("Cadastro de Peixe");
        carregarComponentes();


        final Bundle dados = getIntent().getExtras();

        if(dados != null){
            buttonNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String locationRec = localizacao.getText().toString();
                    String especieRec = dados.getString("especieP2");
                    Double tamanhoRec = dados.getDouble("tamanhoP");
                    Double pesoRec = dados.getDouble("pesoP");
                    String tagRec = dados.getString("tagP");

                    ContentValues cv = new ContentValues();
                    String sql = "INSERT INTO peixes VALUES(?,?,?,?,?,?);";
                    DbHelper dbHelper = new DbHelper(CadastroDePeixePasso3.this);
                    SQLiteDatabase bd = openOrCreateDatabase(dbHelper.NOME_DB, MODE_PRIVATE, null);
                    //bd.insert()
                    sucessoAoCadastrar();
                }
            });
        }else {
            Toast.makeText(getApplicationContext(), "Não funcionou", Toast.LENGTH_SHORT).show();
        }
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroDePeixePasso2.class);
                startActivity(intent);
                finish();
            }
        });

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Double lat = location.getLatitude();
                Double longi = location.getLongitude();
                DecimalFormat format = new DecimalFormat("###.000");
                String latitude = format.format(lat);
                String longitude = format.format(longi);
                localizacao.setText("Lat: " + latitude+ ", Long: " + longitude);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        /*
         * 1) Provedor da localizaÃ§Ã£o
         * 2) Tempo mÃ­nimo entre atualizacÃµes de localizaÃ§Ã£o (milesegundos)
         * 3) Distancia mÃ­nima entre atualizacÃµes de localizaÃ§Ã£o (metros)
         * 4) Location listener (para recebermos as atualizaÃ§Ãµes)
         * */
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0,
                    locationListener
            );
        }
    }

    public void carregarComponentes(){
        buttonNext = findViewById(R.id.buttonNextPasso3);
        buttonBack = findViewById(R.id.buttonBackPasso3);
        pesquisarLocation = findViewById(R.id.pesquisarLocation);
        localizacao = findViewById(R.id.editTextLocalizacao);
        fotoPeixe = findViewById(R.id.imagePeixe);
    }

    public void carregarArmazenamento(View view){

        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (i.resolveActivity(getPackageManager()) != null){
            startActivityForResult(i, SELECAO_GALERIA);
        }
    }

    public void carregarCamera(View view){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (i.resolveActivity(getPackageManager()) != null){
            startActivityForResult(i, SELECAO_CAMERA);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Bitmap imagem = null;
            try {
                switch (requestCode){
                    case SELECAO_CAMERA:
                        imagem = (Bitmap)data.getExtras().get("data");
                        break;
                    case SELECAO_GALERIA:
                        Uri localImagemSelecionada = data.getData();
                        imagem =  MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada);
                        break;
                }
                if(imagem != null){
                   fotoPeixe.setImageBitmap(imagem);

                    //recuperar
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                    byte[] dadosImagem = baos.toByteArray();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void abrirMapa(View view){
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
        finish();
    }

    public void sucessoAoCadastrar(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sucesso ao realizar cadastro");
        builder.setCancelable(false);
        builder.setMessage("O peixe foi cadastrado com sucesso!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.create();
        builder.show();
    }

    private void salvarImagemPasta(String conteudoArq){
        //Criar Pasta de arquivos
        File folder = new File(Environment.getExternalStorageDirectory() + "/TEMP_IMG");

        if(folder.exists()){
            folder.mkdir();
        }

        String nomeArquivo = "Teste";
        File arquivo = new File( Environment.getExternalStorageDirectory().getAbsolutePath(), "/TEMP_IMG" + nomeArquivo);
        try {
            FileOutputStream salvar = new FileOutputStream(arquivo);
            salvar.write(conteudoArq.getBytes());
            salvar.close();
            
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}