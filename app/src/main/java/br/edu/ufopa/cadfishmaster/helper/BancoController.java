package br.edu.ufopa.cadfishmaster.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;

public class BancoController {

    private SQLiteDatabase db;
    private DbHelper banco;

    public BancoController(Context context){
        banco = new DbHelper(context);
    }

    public String insereUsuario(String nome, String email, String senha){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("email", email);
        valores.put("senha", senha);

        resultado = db.insert(banco.TABELA_USUARIOS, null, valores);
        db.close();

        if(resultado == -1){
            return "Erro ao cadastrar usuário";
        }else{
            return "Usuário cadastrado com sucesso!";
        }
    }

    public String insereEspecie(String especie, ImageView icone){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("especie", especie);

        resultado = db.insert(banco.TABELA_ESPECIES, null, valores);
        db.close();

        if(resultado == -1){
            return "Erro ao cadastrar espécie";
        }else {
            return "Espécie cadastrada com sucesso";
        }
    }

    public String inserePeixe(String nome, Double peso, Double tamanho, String marca_tag, String localizacao){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put("nome", nome);
        valores.put("peso", peso);
        valores.put("tamanho", tamanho);
        valores.put("marca_tag", marca_tag);
        valores.put("localizacao", localizacao);

        resultado = db.insert(banco.TABELA_PEIXES, null, valores);
        db.close();

        if(resultado == -1){
            return "Erro ao cadastrar peixe";
        }else {
            return "Peixe cadastrado com sucesso";
        }
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos = {"nome", "email"};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA_USUARIOS,campos, null, null, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
}
