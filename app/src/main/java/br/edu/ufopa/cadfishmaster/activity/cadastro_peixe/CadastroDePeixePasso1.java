package br.edu.ufopa.cadfishmaster.activity.cadastro_peixe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.ufopa.cadfishmaster.R;
import br.edu.ufopa.cadfishmaster.helper.DbHelper;

public class CadastroDePeixePasso1 extends AppCompatActivity {

    private static final String[] PEIXES = new String[]{
            "Tambaqui", "Pacu", "Pirarucu", "Pirarara", "Tucunaré", "Piranha"
    };
    private Button buttonNext;
    private AutoCompleteTextView campoEspecie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_de_peixes_passo1);
        getSupportActionBar().setTitle("Cadastro de Peixe");

        final ArrayList<String> peixes = obterEspecies();

        ImageView imag = findViewById(R.id.btautocomplete);
        final AutoCompleteTextView editText = findViewById(R.id.campoEspeciePeixe);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.autocomplete, R.id.text_view_list_item, peixes);
        editText.setAdapter(adapter);

        imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.showDropDown();
            }
        });

        buttonNext = findViewById(R.id.buttonProximoCadPPass1);
        campoEspecie = findViewById(R.id.campoEspeciePeixe);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String especie = campoEspecie.getText().toString();
                if (!especie.isEmpty()){
                    Intent intent = new Intent(CadastroDePeixePasso1.this, CadastroDePeixePasso2.class);
                    intent.putExtra("especieP", especie);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Informe a Espécie", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public ArrayList<String> obterEspecies(){

        try{
            DbHelper db = new DbHelper(this);
            SQLiteDatabase database = openOrCreateDatabase(db.NOME_DB, MODE_PRIVATE, null);

            Cursor cursor = db.getNameEspecies();

            int indiceNome = cursor.getColumnIndex("nome");
            ArrayList<String> peixes = new ArrayList<>();
            cursor.moveToFirst();
            while (cursor.getCount() > 0){
                peixes.add(cursor.getString(indiceNome));
                cursor.moveToNext();
            }

            cursor.close();
            return peixes;

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
