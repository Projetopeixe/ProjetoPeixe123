package br.edu.ufopa.cadfishmaster.config;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import br.edu.ufopa.cadfishmaster.R;

public class ConfiguracaoBD extends AppCompatActivity { 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao_bd);
    }

    public void criarBD(){

        try{

            //Criação do banco de dados

            SQLiteDatabase bancoDeDados = openOrCreateDatabase("CadFish", MODE_PRIVATE, null);

            //Criar tabela

            bancoDeDados.execSQL("CREATE TABLE IF NOT EXISTS usuarios (nome VARCHAR, email VARCHAR, senha VARCHAR ) ");
            bancoDeDados.execSQL("CREATE TABLE IF NOT EXISTS peixes (especie VARCHAR, peso REAL, tamanho REAL, marca_tag VARCHAR, posicao VARCHAR)");

            //Iserindo dados
            //bancoDeDados.execSQL("INSERT INTO usuarios(nome, email, senha) VALUES ('Luis', 'luisveras@gamil.com', '123')");

            //recuperar usuarios
            //Cursor cursor =  bancoDeDados.rawQuery("", null);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void pesquisar(){

    }

    public void inserirDados(){

    }
}

