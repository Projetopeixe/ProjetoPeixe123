package br.edu.ufopa.cadfishmaster.config;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class ConfiguracaoBD extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String CadFish_BD = "bd_cadfish";



    public ConfiguracaoBD(@Nullable Context context) {
        super(context, CadFish_BD, null, VERSAO_BANCO);
        criarTabelas();
        banco();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
    }
    
    public void inserirUsuario(String nomeIn, String emailIn, String senhaIn){
        ContentValues values = new ContentValues();
        values.put("nome", nomeIn);
        values.put("email", emailIn);
        values.put("senha", senhaIn);
        banco().insert("usuarios", null, values);
    }

    public void inserirPeixe(String especieIn, double pesoIn, double tamanhoIn, String marcaTagIn, String location){
        banco().execSQL("INSERT INTO peixes(especie, peso, tamanho, marca_tag, posicao) VALUES(especieIn, pesoIn, tamanhoIn, marcaTagIn, location)");
    }


    public void criarTabelas(){
        banco().execSQL("CREATE TABLE IF NOT EXISTS usuarios(nome VARCHAR, email VARCHAR, senha VARCHAR)");
        banco().execSQL("CREATE TABLE IF NOT EXISTS peixes (especie VARCHAR, peso REAL, tamanho REAL, marca_tag VARCHAR, posicao VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public SQLiteDatabase banco (){
        try{
            SQLiteDatabase bancoDeDados = SQLiteDatabase.openOrCreateDatabase(CadFish_BD, null);
            return bancoDeDados;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }




}


