package br.edu.ufopa.cadfishmaster.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static  int VERSION = 1;
    public static String NOME_DB = "db_cadfish.db";
    public static String TABELA_USUARIOS = "usuarios";
    public static String TABELA_ESPECIES = "especies";
    public static String TABELA_PEIXES = "peixes";
    public static String TABELA_SUPER_USUARIOS  = "sp_usuarios";

    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlUsuarioPadrao = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIOS
                + "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " email TEXT NOT NULL, " +
                " senha TEXT NOT NULL " +
                ");";

        String sqlEspecieNova = "CREATE TABLE IF NOT EXISTS "+ TABELA_ESPECIES +
                " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL);";

        String sqlPeixe = "CREATE TABLE IF NOT EXISTS " + TABELA_PEIXES +
                " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " peso REAL NOT NULL, " +
                " tamanho REAL NOT NULL, " +
                " marca_tag TEXT NOT NULL, " +
                " localizacao TEXT NOT NULL)";

        String sqlSuperUsuarios = "CREATE TABLE IF NOT EXISTS " + TABELA_SUPER_USUARIOS +
                "(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT NOT NULL, " +
                " email TEXT NOT NULL, " +
                " senha TEXT NOT NULL);";

        try {
            db.execSQL(sqlUsuarioPadrao);
            db.execSQL(sqlEspecieNova);
            db.execSQL(sqlPeixe);
            db.execSQL(sqlSuperUsuarios);
            Log.i("INFO DB", "Sucesso ao criar a tabelas");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar tabea" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlUser = "DROP TABLE IF EXISTS " + TABELA_USUARIOS + ";";

        try{
            db.execSQL(sqlUser);
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao Atualizar App");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar app");
        }
    }
}